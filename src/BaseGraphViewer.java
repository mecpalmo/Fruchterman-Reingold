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

/**
 * Klasa Realizuj¹ca okno wyœwietlania pocz¹tkowego stanu grafu
 *
 */
@SuppressWarnings("serial")
public class BaseGraphViewer extends JFrame{

	/**
	 * Obiekt panelu bêd¹cego zawartoœci¹ okna
	 */
	private GraphPanel panel;
	
	/**
	 * Konstruktor Okna
	 */
	BaseGraphViewer(){
		super("Graf pocz¹tkowy");
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		this.setSize(new Dimension(insets.left + insets.right + Data.WindowSize, insets.top + insets.bottom + Data.WindowSize));
		//setSize(Data.WindowSize,Data.WindowSize);
		panel = new GraphPanel();
		add(panel,BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		//okno na srodku ekranu
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Klasa Panelu dla okna
	 */
	private class GraphPanel extends JPanel{
		
		/**
		 * Konstruktor Panelu
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
	        for(int i=0; i<Data.StartGraph.nodeAmount(); i++) {
	        	g2d.fillOval((int)(Data.StartGraph.getNode(i).x()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2),(int)(Data.StartGraph.getNode(i).y()*Data.WindowSize/Data.Dimension)-(Data.NodeSize/2), Data.NodeSize, Data.NodeSize);
	        }
	        g2d.setColor(Data.EdgeColor);
	        for(int i=0; i<Data.StartGraph.edgeAmount(); i++) {
	        	g2d.drawLine((int)(Data.StartGraph.getNode(Data.StartGraph.getEdge(i).getStartID()).x()*Data.WindowSize/Data.Dimension), (int)(Data.StartGraph.getNode(Data.StartGraph.getEdge(i).getStartID()).y()*Data.WindowSize/Data.Dimension), (int)(Data.StartGraph.getNode(Data.StartGraph.getEdge(i).getEndID()).x()*Data.WindowSize/Data.Dimension), (int)(Data.StartGraph.getNode(Data.StartGraph.getEdge(i).getEndID()).y()*Data.WindowSize/Data.Dimension));
	        }
		}
	}
	///////////////////////////////////
}
