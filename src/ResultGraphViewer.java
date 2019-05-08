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
		Data.EndGraph = Data.StartGraph;
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
			drawGraph();
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
			double k = Math.sqrt(area/Data.EndGraph.nodeAmount());
			double t = Data.Dimension/10;
			double speed;
			double gravity;
			
			
		}
	}
	
}
