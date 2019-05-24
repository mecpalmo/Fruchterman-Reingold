import java.util.ArrayList;
import java.util.List;


/**
 * Klasa reprezentuj�ca obiekt Grafu
 *
 */
public class Graph {

	/**
	 * lista wierzcho�k�w
	 */
	private List<Node> nodeList = new ArrayList<>();
	/**
	 * lista kraw�dzi
	 */
	private List<Edge> edgeList = new ArrayList<>();
	
	/**
	 * domy�lny konstruktor
	 */
	Graph(){
		
	}
	
	/**
	 * 
	 * @param i id wierzcho�ka
	 * @return zwraca obiekt wierzcho�ka
	 */
	public Node getNode(int i) {
		return nodeList.get(i);
	}
	
	/**
	 * 
	 * @param i id kraw�dzi
	 * @return zwraca obiekt kraw�dzi
	 */
	public Edge getEdge(int i) {
		return edgeList.get(i);
	}
	
	/**
	 * 
	 * @return zwraca liczb� wierzcho�k�w
	 */
	public int nodeAmount() {
		return nodeList.size();
	}
	
	/**
	 * 
	 * @return zwraca liczb� kraw�dzi
	 */
	public int edgeAmount() {
		return edgeList.size();
	}
	
	/**
	 * dodanie wierzcho�ka o losowych wsp�rz�dnych
	 */
	public void addNode() {
		int index = nodeList.size();
		nodeList.add(new Node(index,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
	}
	
	/**
	 * Dodanie wierzcho�ka podanego w parametrze funkcji
	 * @param node wierzcho�ek
	 */
	public void addNode(Node node) {
		nodeList.add(node);
	}
	
	/**
	 * Dodanie kraw�dzi o wierzcho�kach podanych w parametrach
	 * @param StartId id wierzcho�ka b�d�cego pocz�tkiem kraw�dzi
	 * @param EndId id wierzcho�ka b�d�cego ko�cem kraw�dzi
	 */
	public void addEdge(int StartId, int EndId) {
		int index = edgeList.size();
		edgeList.add(new Edge(index,StartId,EndId));
	}
	
	/**
	 * Dodanie kraw�dzi podanej w parametrze funkcji
	 * @param edge
	 */
	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}
	
	/**
	 * usuni�cie wierzcho�ka z ko�ca listy
	 */
	public void removeNode() {
		nodeList.remove(nodeList.size()-1);
	}
	
	/**
	 * usuni�cie kraw�dzi z ko�ca listy
	 */
	public void removeEdge() {
		edgeList.remove(edgeList.size()-1);
	}
	
	/**
	 * Generacja losowego grafu
	 * @param nodes liczba wierzcho�k�w
	 * @param edgeProbability prawdopodobie�stwo wyst�pienia kraw�dzi
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
	 * Tworzenie losowych wierzcho�k�w
	 * @param amount liczba wierzcho�k�w
	 */
	public void generateRandomNodes(int amount) {
		while(nodeList.size()>0) {
			nodeList.remove(nodeList.size()-1);
		}
		for(int i=0;i<amount;i++) {
			nodeList.add(new Node(i,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
			//generuje po�o�enie w postaci float w zakresie od 0 do Data.Dimension (niezale�ne od rozmiaru okna)
		}
	}
	
	/**
	 * Tworzenie losowych kraw�dzi
	 * @param nodeAmount liczba wierzcho�k�w
	 * @param edgeProbability prawdopodobie�stwo wyst�pienia kraw�dzi
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
