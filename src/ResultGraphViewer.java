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

//Okno w kt�rym algorytm jest wykonywany i prezentowany jego wynik
@SuppressWarnings("serial")
public class ResultGraphViewer extends JFrame{

	private GraphPanel panel;
	private Timer timer;
	
	//parametry algorytmu
	private double k; //optymalna odleg�o�� mi�dzy wierzcho�kami
	private double t; //temperatura
	int space = 100; //odst�p czasu mi�dzy iteracjami algorytmu (sta�y) (potem uzale�ni si� go od rozmiaru grafu)
	int N = 100; //liczba iteracji algorytmu na razie wst�pnie w ten spos�b (potem mo�na uzale�ni� liczb� iteracji od temperatury)
	int iter; //licznik iteracji algorytmu
	double margin = 0.001;
	
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
		
		panel = new GraphPanel(); //generacja panelu
		add(panel,BorderLayout.CENTER);
		panel.initAlgorithm();
		
	}
	
	
	private class GraphPanel extends JPanel{
		
		//konstruktor panelu
		GraphPanel(){
			setSize(Data.WindowSize,Data.WindowSize);
			setLayout(null);
			setBackground(Color.WHITE);
			drawGraph();
		}
		
		//funkcja inicjuj�ca od�wie�enie obrazu
		private void drawGraph() {
			repaint();
		}

		//funkcja rysuj�ca graf
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setColor(Data.NodeColor);
	        for(int i=0; i<Data.EndGraph.nodeAmount(); i++) {
	        	g2d.fillOval((int)(Data.EndGraph.getNode(i).x()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2),(int)(Data.EndGraph.getNode(i).y()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2), Data.NodeSize, Data.NodeSize);
	        }
	        g2d.setColor(Data.EdgeColor);
	        for(int i=0; i<Data.EndGraph.edgeAmount(); i++) {
	        	g2d.drawLine((int)(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).x()*Data.WindowSize/Data.Dimension), (int)(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).y()*Data.WindowSize/Data.Dimension), (int)(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).x()*Data.WindowSize/Data.Dimension), (int)(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).y()*Data.WindowSize/Data.Dimension));
	        }
		}
		
		//funkcja inicjuj�ca wykonanie algorytmu
		public void initAlgorithm() {
			
			drawGraph();
			double area = Data.Dimension*Data.Dimension;
			k = Math.sqrt(area/Data.EndGraph.nodeAmount());
			t = Data.Dimension/10;
			iter = 0;
			int nodes = Data.EndGraph.nodeAmount();
			int edges = Data.EndGraph.edgeAmount();
			timer = new Timer();
	        timer.scheduleAtFixedRate(new ScheduleTask(), 500, space);
			
		}
		
		//algorytm zostaje umieszczony w timerze aby m�g� by� wykonywany co r�wne odst�py czasu w celu �ledzenia przebiegu
		private class ScheduleTask extends TimerTask{

			@Override
			public void run() {
				
				//si�y odpychaj�ce dla wszystkich wierzcho�k�w
				for(int i=0;i<Data.EndGraph.nodeAmount();i++) {
					Data.EndGraph.getNode(i).resetForces();
					for(int j=0;j<Data.EndGraph.nodeAmount();j++) {
						if(j!=i) {
							double dx = Data.EndGraph.getNode(j).x() - Data.EndGraph.getNode(i).x();
							double dy = Data.EndGraph.getNode(j).y() - Data.EndGraph.getNode(i).y();
							double d = Math.sqrt((dx*dx)+(dy*dy));
							double f = frep(d);
							//poni�sze funkcje s� troch� inaczej ni� w pseudokodzie bo tak jest kr�cej a znaki i tak si� zgadzaj�
							Data.EndGraph.getNode(i).setfx(Data.EndGraph.getNode(i).fx()+(dx/d)*f);
							Data.EndGraph.getNode(i).setfy(Data.EndGraph.getNode(i).fy()+(dy/d)*f);
							
						}
						
						//dodajemy odpychanie od �cianek
						double fx = frep(Data.EndGraph.getNode(i).x());
						double fdimx = frep(Data.Dimension-Data.EndGraph.getNode(i).x());
						double fy = frep(Data.EndGraph.getNode(i).y());
						double fdimy = frep(Data.Dimension-Data.EndGraph.getNode(i).x());
						Data.EndGraph.getNode(i).setfx(Data.EndGraph.getNode(i).fx()-fx+fdimx);
						Data.EndGraph.getNode(i).setfy(Data.EndGraph.getNode(i).fy()-fy+fdimy);
					}
				}
			
				//si�y przyci�gaj�ce dla wszystkich wierzcho�k�w
				for(int i=0;i<Data.EndGraph.edgeAmount();i++) {
					double dx = Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).x() - Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).x();
					double dy = Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).y() - Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).y();
					double d = Math.sqrt((dx*dx)+(dy*dy));
					double f = fatr(d);
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).setfx(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).fx() - (dx/d)*f );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).setfy(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).fy() - (dy/d)*f );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).setfx(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).fx() + (dx/d)*f );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).setfy(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).fy() + (dy/d)*f );
				}
				
				//ograniczenie si� mog�cych wywali� wierzcho�ki poza dost�pne pole oraz zastosowanie si�
				for(int i=0; i<Data.EndGraph.nodeAmount();i++) {
					
					Data.EndGraph.getNode(i).applyForces(t);
					
					//zapobieganie wychodzeniu poza pole
					if(Data.EndGraph.getNode(i).x()<0+margin) {
						Data.EndGraph.getNode(i).setX(0+margin);
					}
					if(Data.EndGraph.getNode(i).x()>Data.Dimension-margin) {
						Data.EndGraph.getNode(i).setX(Data.Dimension-margin);
					}
					if(Data.EndGraph.getNode(i).y()<0+margin) {
						Data.EndGraph.getNode(i).setY(0+margin);
					}
					if(Data.EndGraph.getNode(i).y()>Data.Dimension-margin) {
						Data.EndGraph.getNode(i).setY(Data.Dimension-margin);
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
		
		//si�a przyci�gaj�ca ma warto�� dodatni�
		private double fatr(double x) {
			double fx = x*x/k;
			return fx;
		}
		
		//si�a odpychaj�ca ma warto�� ujemn�
		private double frep(double x) {
			double fx = -k*k/x;
			return fx;
		}
		
		//funkcja ch�odz�ca temperatura nie mo�e za szybko spada�
		private void cool() {
			t = t/1.02;
		}
	}
	
	//przekopiowanie grafu pocz�tkowego do obiektu EndGraph
	//na kt�rym b�dzie wykonywany algorytm, aby zapami�ta� pocz�tkowy
	private void copyGraph() {
		Data.EndGraph = new Graph();
		
		while(Data.EndGraph.nodeAmount()>0) {
			Data.EndGraph.removeNode();
		}
		
		while(Data.EndGraph.edgeAmount()>0) {
			Data.EndGraph.removeEdge();
		}
		
		for(int i=0;i<Data.StartGraph.nodeAmount();i++) {
			Data.EndGraph.addNode(new Node(Data.StartGraph.getNode(i)));
		}
		
		for(int i=0;i<Data.StartGraph.edgeAmount();i++) {
			Data.EndGraph.addEdge(new Edge(Data.StartGraph.getEdge(i)));
		}
	}
}
