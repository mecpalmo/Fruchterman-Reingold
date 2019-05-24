import java.util.ArrayList;
import java.util.List;


/**
 * Klasa reprezentuj¹ca obiekt Grafu
 *
 */
public class Graph {

	/**
	 * lista wierzcho³ków
	 */
	private List<Node> nodeList = new ArrayList<>();
	/**
	 * lista krawêdzi
	 */
	private List<Edge> edgeList = new ArrayList<>();
	
	/**
	 * domyœlny konstruktor
	 */
	Graph(){
		
	}
	
	/**
	 * 
	 * @param i id wierzcho³ka
	 * @return zwraca obiekt wierzcho³ka
	 */
	public Node getNode(int i) {
		return nodeList.get(i);
	}
	
	/**
	 * 
	 * @param i id krawêdzi
	 * @return zwraca obiekt krawêdzi
	 */
	public Edge getEdge(int i) {
		return edgeList.get(i);
	}
	
	/**
	 * 
	 * @return zwraca liczbê wierzcho³ków
	 */
	public int nodeAmount() {
		return nodeList.size();
	}
	
	/**
	 * 
	 * @return zwraca liczbê krawêdzi
	 */
	public int edgeAmount() {
		return edgeList.size();
	}
	
	/**
	 * dodanie wierzcho³ka o losowych wspó³rzêdnych
	 */
	public void addNode() {
		int index = nodeList.size();
		nodeList.add(new Node(index,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
	}
	
	/**
	 * Dodanie wierzcho³ka podanego w parametrze funkcji
	 * @param node wierzcho³ek
	 */
	public void addNode(Node node) {
		nodeList.add(node);
	}
	
	/**
	 * Dodanie krawêdzi o wierzcho³kach podanych w parametrach
	 * @param StartId id wierzcho³ka bêd¹cego pocz¹tkiem krawêdzi
	 * @param EndId id wierzcho³ka bêd¹cego koñcem krawêdzi
	 */
	public void addEdge(int StartId, int EndId) {
		int index = edgeList.size();
		edgeList.add(new Edge(index,StartId,EndId));
	}
	
	/**
	 * Dodanie krawêdzi podanej w parametrze funkcji
	 * @param edge
	 */
	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}
	
	/**
	 * usuniêcie wierzcho³ka z koñca listy
	 */
	public void removeNode() {
		nodeList.remove(nodeList.size()-1);
	}
	
	/**
	 * usuniêcie krawêdzi z koñca listy
	 */
	public void removeEdge() {
		edgeList.remove(edgeList.size()-1);
	}
	
	/**
	 * Generacja losowego grafu
	 * @param nodes liczba wierzcho³ków
	 * @param edgeProbability prawdopodobieñstwo wyst¹pienia krawêdzi
	 */
	public void generateRandomGraph(int nodes, float edgeProbability) {
		if(nodes>0) {
		generateRandomNodes(nodes);
		}
		if(nodes>1) {
		generateRandomEdges(nodes,edgeProbability);
		}
	}
	
	/**
	 * Tworzenie losowych wierzcho³ków
	 * @param amount liczba wierzcho³ków
	 */
	public void generateRandomNodes(int amount) {
		while(nodeList.size()>0) {
			nodeList.remove(nodeList.size()-1);
		}
		for(int i=0;i<amount;i++) {
			nodeList.add(new Node(i,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
			//generuje po³o¿enie w postaci float w zakresie od 0 do Data.Dimension (niezale¿ne od rozmiaru okna)
		}
	}
	
	/**
	 * Tworzenie losowych krawêdzi
	 * @param nodeAmount liczba wierzcho³ków
	 * @param edgeProbability prawdopodobieñstwo wyst¹pienia krawêdzi
	 */
	private void generateRandomEdges(int nodeAmount, float edgeProbability) {
		while(edgeList.size()>0) {
			edgeList.remove(edgeList.size()-1);
		}
		int index = 0;
		for(int i=0;i<nodeAmount-1;i++) {
			for(int j=i+1;j<nodeAmount;j++) {
				if(Math.random()<=edgeProbability) {
					edgeList.add(new Edge(index,i,j));
					index++;
				}
			}
		}
	}
}
