
/**
 * Klasa reprezentuj�ca obiekt kraw�dzi
 *
 */
public class Edge {

	private int id; // id do rozpoznawania krawedzi
	private int startId; // id wierzcholka poczatkowego
	private int endId; // id wierzcholka koncowego
	
	/**
	 * domyslny konstruktor
	 */
	Edge(){
		
	}
	
	/**
	 * konstruktor z parametrami liczbowymi
	 * @param ID numer identyfikacyjny kraw�dzi
	 * @param STARTID ID wierzcho�ka b�d�cego pocz�tkiem kraw�dzi
	 * @param ENDID ID wierzcho�ka b�d�cego ko�cem kraw�dzi
	 */
	Edge(int ID, int STARTID, int ENDID){
		id = ID;
		startId = STARTID;
		endId = ENDID;
	}
	
	/**
	 * konstruktor kraw�dzi o takich samych parametrach co kraw�dz podana jako parametr
	 * @param edge obiekt z kt�rego kopiujemy parametry do utworzenia nowej krawedzi
	 */
	Edge(Edge edge){
		id = edge.id();
		startId = edge.getStartID();
		endId = edge.getEndID();
	}
	
	/**
	 * 
	 * @return zwraca id krawedzi
	 */
	public int id() {
		return id;
	}
	
	/**
	 * Ustawienie id wierzcho�ka b�d�cego pocz�tkiem kraw�dzi
	 * @param ID id wierzcho�ka
	 */
	public void setStartID(int ID){
		startId = ID;
	}
	
	/**
	 * Ustawienie id wierzcho�ka b�d�cego ko�cem kraw�dzi
	 * @param ID id wierzcho�ka
	 */
	public void setEndID(int ID) {
		endId = ID;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho�ka b�d�cego pocz�tkiem kraw�dzi
	 */
	public int getStartID() {
		return startId;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho�ka b�d�cego ko�cem kraw�dzi
	 */
	public int getEndID() {
		return endId;
	}
	
	/**
	 * 
	 * @param graph Obiekt Grafu do kt�rego nale�y dana kraw�dz
	 * @return zwraca d�ugo�� kraw�dzi
	 */
	public double getLength(Graph graph) {
		double X = Math.abs(graph.getNode(startId).x() - graph.getNode(endId).x());
		double Y = Math.abs(graph.getNode(startId).y() - graph.getNode(endId).y());
		double length = Math.sqrt((X*X)+(Y*Y));
		return length;
	}
}
