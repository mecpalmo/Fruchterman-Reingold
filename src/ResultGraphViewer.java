import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ResultGraphViewer extends JFrame{

	private GraphPanel panel;
	
	//parametry algorytmu
	private double k;
	
	ResultGraphViewer(){
		
		super("Graf Fruchtermana-Reingolda");
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		this.setSize(new Dimension(insets.left + insets.right + Data.WindowSize, insets.top + insets.bottom + Data.WindowSize));
		//setSize(Data.WindowSize,Data.WindowSize);
		setResizable(false);
		setVisible(true);
		Data.EndGraph = new Graph();
		copyGraph();
		panel = new GraphPanel();
		add(panel,BorderLayout.CENTER);
		//okno na srodku ekranu
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}
	
	
	private class GraphPanel extends JPanel{
		
		GraphPanel(){
			setSize(Data.WindowSize,Data.WindowSize);
			setLayout(null);
			setBackground(Color.WHITE);
			initAlgorithm();
		}
		
		private void drawGraph() {
			repaint();
		}

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
		
		//funkcja inicjuj¹ca wykonanie algorytmu
		private void initAlgorithm() {
			
			double area = Data.Dimension*Data.Dimension;
			k = Math.sqrt(area/Data.EndGraph.nodeAmount());
			double t = Data.Dimension/10;
			double speed;
			double gravity;
			int N = 100; //liczba iteracji algorytmu na razie wstêpnie w ten sposób
			
			for(int u=0;u<N;u++) {
			
				//si³y odpychaj¹ce dla wszystkich wierzcho³ków
				for(int i=0;i<Data.EndGraph.nodeAmount();i++) {
					Data.EndGraph.getNode(i).resetForces();
					for(int j=0;j<Data.EndGraph.nodeAmount();j++) {
						if(j!=i) {
							double dx = Data.EndGraph.getNode(j).x() - Data.EndGraph.getNode(i).x();
							double dy = Data.EndGraph.getNode(j).y() - Data.EndGraph.getNode(i).y();
							Data.EndGraph.getNode(i).setfx(Data.EndGraph.getNode(i).fx()+frep(dx));
							Data.EndGraph.getNode(i).setfy(Data.EndGraph.getNode(i).fy()+frep(dy));
						}
					}
				}
			
				//si³y przyci¹gaj¹ce dla wszystkich wierzcho³ków
				for(int i=0;i<Data.EndGraph.edgeAmount();i++) {
					double dx = Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).x() - Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).x();
					double dy = Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).y() - Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).y();
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).setfx(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).fx() - (dx/Math.abs(dx))*fatr(dx) );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).setfy(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getStartID()).fy() - (dy/Math.abs(dy))*fatr(dy) );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).setfx(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).fx() + (dx/Math.abs(dx))*fatr(dx) );
					Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).setfy(Data.EndGraph.getNode(Data.EndGraph.getEdge(i).getEndID()).fy() + (dy/Math.abs(dy))*fatr(dy) );
				}
				
				//ograniczenie si³ mog¹cych wywaliæ wierzcho³ki poza dostêpne pole oraz zastosowanie si³
				for(int i=0; i<Data.EndGraph.nodeAmount();i++) {
					//tu nie ogarniam sk¹d siê bierze t i jak siê je ch³odzi potem
					Data.EndGraph.getNode(i).applyForces();
				}
				
				//drawGraph();
				
			}
			
			drawGraph();
		}
		
		//si³a przyci¹gaj¹ca ma wartoœæ dodatni¹
		private double fatr(double x) {
			double fx = x*x/k;
			return fx;
		}
		
		//si³a odpychaj¹ca ma wartoœæ ujemn¹
		private double frep(double x) {
			double fx = -k*k/x;
			return fx;
		}
	}
	
	private void copyGraph() {
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
