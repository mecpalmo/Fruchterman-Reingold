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

/**
 * Klasa realizuj¹ca okno g³ównego Menu
 *
 */
@SuppressWarnings("serial")
public class Menu extends JFrame{

	/**
	 * guzik uruchamiaj¹cy okno pokazuj¹ce graf pocz¹tkowy
	 */
	private JButton initFirstGraph;
	/**
	 * guzik uruchamiaj¹cy okno pokazuj¹ce dzia³anie algorytmu Fruchtermana Reingolda
	 */
	private JButton initSecondGraph;
	/**
	 * guzik uruchamiaj¹cy generacjê grafu losowego
	 */
	private JButton generateGraph;
	/**
	 * guzik uruchamiaj¹cy wczytanie grafu z pliku
	 */
	private JButton loadGraph;
	/**
	 * guzik uruchamiaj¹cy zapis grafu do pliku
	 */
	private JButton saveGraph;
	
	/**
	 * suwaki reguluj¹cy prawdopodobieñstwo wyst¹pienia krawêdzi
	 */
	private JSlider EdgeSlider;
	/**
	 * suwak reguluj¹cy liczbê wierzcho³ków grafu losowego
	 */
	private JSlider NodeSlider;
	/**
	 * suwak reguluj¹cy oddzia³ywanie œcianek p³aszczyzny prezentacji 
	 */
	private JSlider WallForceSlider;
	/**
	 * suwak reguluj¹cy mno¿nik parametru k
	 */
	private JSlider KFactorSlider;
	/**
	 * suwak reguluj¹cy szybkoœæ redukcji temperatury
	 */
	private JSlider TemperatureSlider;
	/**
	 * suwak reguluj¹cy liczbê iteracji
	 */
	private JSlider IterationSlider;
	
	/**
	 * komunikat tekstowy
	 */
	private JLabel label;
	/**
	 * komunikat tekstowy
	 */
	private JLabel label2;
	/**
	 * komunikat tekstowy
	 */
	private JLabel label3;
	/**
	 * komunikat tekstowy
	 */
	private JLabel label4;
	/**
	 * komunikat tekstowy
	 */
	private JLabel label5;
	/**
	 * komunikat tekstowy
	 */
	private JLabel label6;

	/**
	 * szerokoœæ guzików
	 */
	private int buttonx = 100;
	/**
	 * wysokoœæ guzików
	 */
	private int buttony = 30;
	/**
	 * szerokoœæ okna menu
	 */
	private int sizex = 300;
	/**
	 * wysokoœæ okna menu
	 */
	private int sizey = 600;
	
	/**
	 * domyœlna liczba wierzcho³ków
	 */
	private int nodeAmount = 5;
	/**
	 * domyœlna wartoœæ prawdopodobieñstwa wyst¹pienia krawêdzi
	 */
	private int edgeProbability = 50;
	
	/**
	 * konstruktor okna
	 */
	Menu(){
		super("Test Alg. F-R");
		setSize(sizex,sizey);
		setResizable(false);
		MenuPanel menuPanel = new MenuPanel();
		add(menuPanel);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Klasa Panelu okna
	 *
	 */
	private class MenuPanel extends JPanel{
		
		/**
		 * konstruktor Panelu
		 */
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
		
		/**
		 * inicjacja guziczków (elementów menu)
		 */
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
	
		/**
		 * inicjacja suwaczków (elementów menu)
		 */
		private void initSliders() {
			
			label = new JLabel("Prawdopodobieñstwo krawêdzi: "+edgeProbability+"%");
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label);
			
			EdgeSlider = new JSlider(SwingConstants.HORIZONTAL,0,100,edgeProbability);
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
			
			NodeSlider = new JSlider(SwingConstants.HORIZONTAL,1,100,nodeAmount);
			NodeSlider.setMajorTickSpacing(1);
			NodeSlider.setPaintTicks(true);
			NodeSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					nodeAmount = NodeSlider.getValue();
					label2.setText("Liczba wierzcho³ków: "+nodeAmount);
				}
			});
			add(NodeSlider);
			
			this.add(Box.createVerticalGlue());
			label3 = new JLabel("Oddzia³ywanie œcianek: "+Data.wallForce+"%");
			label3.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label3);
			
			WallForceSlider = new JSlider(SwingConstants.HORIZONTAL,0,100,Data.wallForce);
			WallForceSlider.setMajorTickSpacing(10);
			WallForceSlider.setPaintTicks(true);
			WallForceSlider.setSnapToTicks(true);
			WallForceSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					Data.wallForce = WallForceSlider.getValue();
					label3.setText("Oddzia³ywanie œcianek: "+Data.wallForce+"%");
				}
			});
			add(WallForceSlider);
			
			label4 = new JLabel("Mno¿nik parametru k: "+(double)Data.kFactor/100);
			label4.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label4);
			
			KFactorSlider = new JSlider(SwingConstants.HORIZONTAL,50,300,Data.kFactor);
			KFactorSlider.setMajorTickSpacing(10);
			KFactorSlider.setPaintTicks(true);
			KFactorSlider.setSnapToTicks(true);
			KFactorSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					Data.kFactor = KFactorSlider.getValue();
					label4.setText("Mno¿nik parametru k: "+(double)Data.kFactor/100);
				}
			});
			add(KFactorSlider);
			
			label5 = new JLabel("szybkoœæ spadku temperatury: "+Data.tempDecrease);
			label5.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label5);
			
			TemperatureSlider = new JSlider(SwingConstants.HORIZONTAL,0,100,Data.tempDecrease);
			TemperatureSlider.setMajorTickSpacing(1);
			TemperatureSlider.setPaintTicks(true);
			TemperatureSlider.setSnapToTicks(true);
			TemperatureSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					Data.tempDecrease = TemperatureSlider.getValue();
					label5.setText("szybkoœæ spadku temperatury: "+Data.tempDecrease);
				}
			});
			add(TemperatureSlider);
			
			label6 = new JLabel("Liczba Iteracji: "+Data.iterations);
			label6.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(label6);
			
			IterationSlider = new JSlider(SwingConstants.HORIZONTAL,0,500,Data.iterations);
			IterationSlider.setMajorTickSpacing(20);
			IterationSlider.setPaintTicks(true);
			IterationSlider.setSnapToTicks(true);
			IterationSlider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					Data.iterations = IterationSlider.getValue();
					label6.setText("Liczba Iteracji: "+Data.iterations);
				}
			});
			add(IterationSlider);
		}
		
	}
	
	/**
	 * uruchomienie okna wyœwietlaj¹cego pocz¹tkowy obraz grafu
	 */
	protected void showBaseGraph() {
		
		BaseGraphViewer graphViewer = new BaseGraphViewer();
		
	}

	/**
	 * Uruchomienie wczytania grafu z pliku
	 * przy pomyœlnym skutku oblokowuje guziki, pocz¹tkowo zablokowane z powodu braku grafu pocz¹tkowego
	 */
	protected void loadGraphFromFile() {

		GraphLoader loader = new GraphLoader();
		boolean loaded = loader.loadGraph();
		if(loaded) { //odblokowanie guzików initFirstGrahp i initSecondGraph i saveGraph
			initFirstGraph.setEnabled(true);
			initSecondGraph.setEnabled(true);
			saveGraph.setEnabled(true);
		}
		
	}

	/**
	 * generacja grafu losowego
	 */
	protected void createRandomGraph() {
		
		float prob = (float)(edgeProbability)/100;
		Data.StartGraph.generateRandomGraph(nodeAmount, prob);
		//edgeProbability trzeba podaæ jako u³amek
		initFirstGraph.setEnabled(true);
		initSecondGraph.setEnabled(true);
		saveGraph.setEnabled(true);
		
	}

	/**
	 * Uruchomienie okna wyœwietlaj¹cego graf z zastosowaniem algorytmu Fruchtermana-Reingolda
	 */
	protected void showResultGraph() {
		
		ResultGraphViewer graphViewer = new ResultGraphViewer();
		
	}
	
	/**
	 * Uruchomienie zapisu grafu do pliku
	 */
	protected void saveGraphToFile() {
		
		GraphSaver saver = new GraphSaver();
		saver.saveGraph();
		
	}
	
	/**
	 * Obiekt przypisuj¹cy dzia³anie guzikom w menu
	 */
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
