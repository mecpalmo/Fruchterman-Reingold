import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Menu extends JFrame{

	private JButton initFirstGraph, initSecondGraph, generateGraph, loadGraph, saveGraph;
	private JSlider EdgeSlider;
	private JSlider NodeSlider;
	private JLabel label, label2;
	
	private int buttonx = 100, buttony = 30;
	private int sizex = 250, sizey = 400;
	
	private int nodeAmount = 5, edgeProbability = 50;
	
	Menu(){
		super("Test Alg. F-R");
		setSize(sizex,sizey);
		setResizable(false);
		MenuPanel menuPanel = new MenuPanel();
		add(menuPanel);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class MenuPanel extends JPanel{
		
		MenuPanel(){
			setSize(sizex,sizey);
			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
			setBackground(Color.GRAY);
			this.add(Box.createVerticalGlue());
			initButtons();
			this.add(Box.createVerticalGlue());
			initSliders();
			this.add(Box.createVerticalGlue());
		}
		
		
		private void initButtons() {
			
			Font font = new Font("Arial",Font.BOLD,11);
			
			loadGraph = new JButton("Wczytaj graf z pliku");
			loadGraph.addActionListener(actList);
			loadGraph.setSize(buttonx,buttony);
			loadGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
			loadGraph.setFont(font);
			add(loadGraph);
			this.add(Box.createVerticalGlue());
			
			generateGraph = new JButton("Stwórz losowy graf");
			generateGraph.addActionListener(actList);
			generateGraph.setSize(buttonx,buttony);
			generateGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
			generateGraph.setFont(font);
			add(generateGraph);
			this.add(Box.createVerticalGlue());
			
			saveGraph = new JButton("Zapisz graf");
			saveGraph.addActionListener(actList);
			saveGraph.setSize(buttonx,buttony);
			saveGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
			saveGraph.setFont(font);
			saveGraph.setEnabled(false);
			add(saveGraph);
			this.add(Box.createVerticalGlue());
			
			initFirstGraph = new JButton("Poka¿ graf pocz¹tkowy");
			initFirstGraph.addActionListener(actList);
			initFirstGraph.setSize(buttonx,buttony);
			initFirstGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
			initFirstGraph.setFont(font);
			initFirstGraph.setEnabled(false);
			add(initFirstGraph);
			this.add(Box.createVerticalGlue());
			
			initSecondGraph = new JButton("Wygeneruj graf FR");
			initSecondGraph.addActionListener(actList);
			initSecondGraph.setSize(buttonx,buttony);
			initSecondGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
			initSecondGraph.setFont(font);
			initSecondGraph.setEnabled(false);
			add(initSecondGraph);
			
		}
	
		private void initSliders() {
			
			label = new JLabel("Prawdopodobieñstwo krawêdzi: "+edgeProbability+"%");
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label);
			
			EdgeSlider = new JSlider(SwingConstants.HORIZONTAL,10,100,edgeProbability);
			EdgeSlider.setMajorTickSpacing(10);
			EdgeSlider.setPaintTicks(true);
			EdgeSlider.setSnapToTicks(true);
			EdgeSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					edgeProbability = EdgeSlider.getValue();
					label.setText("Prawdopodobieñstwo krawêdzi: "+edgeProbability+"%");
				}
			});
			add(EdgeSlider);
			
			label2 = new JLabel("Liczba wierzcho³ków: "+nodeAmount);
			label2.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label2);
			
			NodeSlider = new JSlider(SwingConstants.HORIZONTAL,1,50,5);
			NodeSlider.setMajorTickSpacing(1);
			NodeSlider.setPaintTicks(true);
			NodeSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					nodeAmount = NodeSlider.getValue();
					label2.setText("Liczba wierzcho³ków: "+nodeAmount);
				}
			});
			add(NodeSlider);
		}
		
	}
	
	protected void showBaseGraph() {
		
		BaseGraphViewer graphViewer = new BaseGraphViewer();
		
	}

	protected void loadGraphFromFile() {
		
		//wczytanie grafu z pliku, najlepiej osobn¹ klas¹
		//odblokowanie guzików initFirstGrahp i initSecondGraph i saveGraph
		GraphLoader loader = new GraphLoader();
		boolean loaded = loader.loadGraph();
		if(loaded) {
			initFirstGraph.setEnabled(true);
			initSecondGraph.setEnabled(true);
			saveGraph.setEnabled(true);
		}
		
	}

	protected void createRandomGraph() {
		
		float prob = (float)(edgeProbability)/100;
		Data.StartGraph.generateRandomGraph(nodeAmount, prob);
		//edgeProbability trzeba podaæ jako u³amek
		initFirstGraph.setEnabled(true);
		initSecondGraph.setEnabled(true);
		saveGraph.setEnabled(true);
		
	}

	protected void showResultGraph() {
		
		//generacja nowego okna wyœwietlaj¹cego rezultat algorytmu
		//osobna klasa okna która bêdzie mia³a osobn¹ klasê do wykonania algorytmu
		
	}
	
	protected void saveGraphToFile() {
		
		//zapisanie grafu do pliku tekstowego osobn¹ klas¹ 
		
	}
	
	private ActionListener actList = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==initFirstGraph) {
				showBaseGraph();
			}
			if(e.getSource()==initSecondGraph) {
				showResultGraph();
			}
			if(e.getSource()==generateGraph) {
				createRandomGraph();
			}
			if(e.getSource()==loadGraph) {
				loadGraphFromFile();
			}
			if(e.getSource()==saveGraph) {
				saveGraphToFile();
			}
		}
	};

}
