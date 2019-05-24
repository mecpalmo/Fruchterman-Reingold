import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasa Realizuj¹ca okno wyœwietlania grafu wraz z wykonaniem algorytmu Fruchtermana-Reingolda
 *
 */
@SuppressWarnings("serial")
public class ResultGraphViewer extends JFrame{

	/**
	 * Obiekt Panelu zawartego w oknie
	 */
	private GraphPanel panel;
	/**
	 * obiekt klasy Timer pozwalaj¹cy na dzia³anie w równych odstêpach czasu 
	 */
	private Timer timer;
	
	/**
	 * optymalna odleg³oœæ miêdzy wierzcho³kami
	 */
	private double k;
	/**
	 * temperatura
	 */
	private double t;
	/**
	 * mno¿nik parametru k
	 */
	private double C = (double)Data.kFactor/100;
	/**
	 * odstêp czasu miêdzy iteracjami algorytmu [ms]
	 */
	int space = 100;
	/**
	 * liczba iteracji algorytmu
	 */
	int N = Data.iterations;
	/**
	 * licznik iteracji algorytmu (parametr pomocniczy)
	 */
	int iter;
	/**
	 * parametr przekszta³caj¹cy szybkoœæ redukcji temperatury na parametr funkcji cool()
	 */
	double cooler = (double)Data.tempDecrease/100;
	/**
	 * wartoœæ zapobiegaj¹ca dzieleniu przez zero
	 */
	double margin = 0.00001;
	/**
	 * wartoœæ si³y pochodz¹cej od œcianek w postaci u³amka
	 */
	double wallForce = (double)Data.wallForce/100;
	
	/**
	 * Obiekt Grafu, na którym wykonywany jest algorytm Fruchtermana Reingolda
	 */
	private Graph EndGraph;
	
	/**
	 * konstruktor okna
	 */
	ResultGraphViewer(){
		
		super("Graf Fruchtermana-Reingolda");
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		this.setSize(new Dimension(insets.left + insets.right + Data.WindowSize, insets.top + insets.bottom + Data.WindowSize));
		setResizable(false);
		setVisible(true);
		
		copyGraph();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //okno na srodku ekranu komputera
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		N = Data.iterations;
		C = (double)Data.kFactor/100;
		cooler = (double)Data.tempDecrease/100;
		wallForce = (double)Data.wallForce/100;
		
		panel = new GraphPanel(); //generacja panelu
		add(panel,BorderLayout.CENTER);
		panel.initAlgorithm();
		
	}
	
	/**
	 * Klasa Panelu dla okna
	 *
	 */
	private class GraphPanel extends JPanel{
		
		/**
		 * konstruktor Panelu
		 */
		GraphPanel(){
			setSize(Data.WindowSize,Data.WindowSize);
			setLayout(null);
			setBackground(Color.WHITE);
			drawGraph();
		}
		
		/**
		 * Wywo³anie ponownego rysowania
		 */
		private void drawGraph() {
			repaint();
		}

		/**
		 * Komponent realizuj¹cy rysowanie wszystkich elementów grafu
		 */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setColor(Data.NodeColor);
	        for(int i=0; i<EndGraph.nodeAmount(); i++) {
	        	g2d.fillOval((int)(EndGraph.getNode(i).x()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2),(int)(EndGraph.getNode(i).y()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2), Data.NodeSize, Data.NodeSize);
	        }
	        g2d.setColor(Data.EdgeColor);
	        for(int i=0; i<EndGraph.edgeAmount(); i++) {
	        	g2d.drawLine((int)(EndGraph.getNode(EndGraph.getEdge(i).getStartID()).x()*Data.WindowSize/Data.Dimension), (int)(EndGraph.getNode(EndGraph.getEdge(i).getStartID()).y()*Data.WindowSize/Data.Dimension), (int)(EndGraph.getNode(EndGraph.getEdge(i).getEndID()).x()*Data.WindowSize/Data.Dimension), (int)(EndGraph.getNode(EndGraph.getEdge(i).getEndID()).y()*Data.WindowSize/Data.Dimension));
	        }
		}
		
		/**
		 * inicjacja wykonania algorytmu
		 */
		public void initAlgorithm() {
			
			drawGraph();
			double area = Data.Dimension*Data.Dimension;
			k = C*Math.sqrt(area/EndGraph.nodeAmount());
			t = Data.Dimension/10;
			iter = 0;
			int nodes = EndGraph.nodeAmount();
			int edges = EndGraph.edgeAmount();
			timer = new Timer();
	        timer.scheduleAtFixedRate(new ScheduleTask(), 500, space);
			
		}
		
		/**
		 * Klasa TimerTask realizuj¹ca funkcjê run w równych odstêpach czasu
		 *
		 */
		private class ScheduleTask extends TimerTask{

			/**
			 * wykonanie iteracji algorytmu uruchamiane co sta³¹ jednostkê czasu
			 */
			@Override
			public void run() {
				
				//si³y odpychaj¹ce dla wszystkich wierzcho³ków
				for(int i=0;i<EndGraph.nodeAmount();i++) {
					EndGraph.getNode(i).resetForces();
					for(int j=0;j<EndGraph.nodeAmount();j++) {
						if(j!=i) {
							double dx = EndGraph.getNode(j).x() - EndGraph.getNode(i).x();
							double dy = EndGraph.getNode(j).y() - EndGraph.getNode(i).y();
							double d = Math.sqrt((dx*dx)+(dy*dy));
							if (d==0) {
								d=margin;
							}
							double f = frep(d);
							//poni¿sze funkcje s¹ trochê inaczej ni¿ w pseudokodzie bo tak jest krócej a znaki i tak siê zgadzaj¹
							EndGraph.getNode(i).setfx(EndGraph.getNode(i).fx()+(dx/d)*f);
							EndGraph.getNode(i).setfy(EndGraph.getNode(i).fy()+(dy/d)*f);
							
						}
						
						//dodajemy odpychanie od œcianek
						double a = wallForce;
						double fx = a*frep(EndGraph.getNode(i).x());
						double fdimx = a*frep(Data.Dimension-EndGraph.getNode(i).x());
						double fy = a*frep(EndGraph.getNode(i).y());
						double fdimy = a*frep(Data.Dimension-EndGraph.getNode(i).y());
						EndGraph.getNode(i).setfx(EndGraph.getNode(i).fx()-fx+fdimx);
						EndGraph.getNode(i).setfy(EndGraph.getNode(i).fy()-fy+fdimy);
					}
				}
			
				//si³y przyci¹gaj¹ce dla wszystkich wierzcho³ków
				for(int i=0;i<EndGraph.edgeAmount();i++) {
					double dx = EndGraph.getNode(EndGraph.getEdge(i).getStartID()).x() - EndGraph.getNode(EndGraph.getEdge(i).getEndID()).x();
					double dy = EndGraph.getNode(EndGraph.getEdge(i).getStartID()).y() - EndGraph.getNode(EndGraph.getEdge(i).getEndID()).y();
					double d = Math.sqrt((dx*dx)+(dy*dy));
					if (d==0) {
						d=margin;
					}
					double f = fatr(d);
					EndGraph.getNode(EndGraph.getEdge(i).getStartID()).setfx(EndGraph.getNode(EndGraph.getEdge(i).getStartID()).fx() - (dx/d)*f );
					EndGraph.getNode(EndGraph.getEdge(i).getStartID()).setfy(EndGraph.getNode(EndGraph.getEdge(i).getStartID()).fy() - (dy/d)*f );
					EndGraph.getNode(EndGraph.getEdge(i).getEndID()).setfx(EndGraph.getNode(EndGraph.getEdge(i).getEndID()).fx() + (dx/d)*f );
					EndGraph.getNode(EndGraph.getEdge(i).getEndID()).setfy(EndGraph.getNode(EndGraph.getEdge(i).getEndID()).fy() + (dy/d)*f );
				}
				
				//ograniczenie si³ mog¹cych wywaliæ wierzcho³ki poza dostêpne pole oraz zastosowanie si³
				for(int i=0; i<EndGraph.nodeAmount();i++) {
					
					EndGraph.getNode(i).applyForces(t);
					
					//zapobieganie wychodzeniu poza pole
					if(EndGraph.getNode(i).x()<0+margin) {
						EndGraph.getNode(i).setX(0+margin);
					}
					if(EndGraph.getNode(i).x()>Data.Dimension-margin) {
						EndGraph.getNode(i).setX(Data.Dimension-margin);
					}
					if(EndGraph.getNode(i).y()<0+margin) {
						EndGraph.getNode(i).setY(0+margin);
					}
					if(EndGraph.getNode(i).y()>Data.Dimension-margin) {
						EndGraph.getNode(i).setY(Data.Dimension-margin);
					}
				}
				
				drawGraph();
				
				cool();
				iter++;
				
				if(iter>=N) {
					timer.cancel();
				}
				
			}
			
		}
		
		/**
		 * 
		 * @param x odleg³oœæ miêdzy obiektami
		 * @return zwraca wartoœæ si³y przyci¹gaj¹cej
		 */
		private double fatr(double x) {
			double fx = x*x/k;
			return fx;
		}
		
		/**
		 * 
		 * @param x odleg³oœæ miêdzy obiektami
		 * @return zwraca wartoœæ si³y odpychaj¹cej
		 */
		private double frep(double x) {
			double fx = -k*k/x;
			return fx;
		}
		
		/**
		 * redukcja temperatury, czyli zmniejszenie parametru t
		 */
		private void cool() {
			t = t/(cooler+1.00);
		}
	}
	
	/**
	 * skopiowanie grafu pocz¹tkowego do grafu na którym bêdzie wykonywany algorytm w celu zachowania stanu pocz¹tkowego
	 */
	private void copyGraph() {
		EndGraph = new Graph();
		
		while(EndGraph.nodeAmount()>0) {
			EndGraph.removeNode();
		}
		
		while(EndGraph.edgeAmount()>0) {
			EndGraph.removeEdge();
		}
		
		for(int i=0;i<Data.StartGraph.nodeAmount();i++) {
			EndGraph.addNode(new Node(Data.StartGraph.getNode(i)));
		}
		
		for(int i=0;i<Data.StartGraph.edgeAmount();i++) {
			EndGraph.addEdge(new Edge(Data.StartGraph.getEdge(i)));
		}
	}
}
