
/**
 * Klasa reprezentuj¹ca obiekt krawêdzi
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
	 * @param ID numer identyfikacyjny krawêdzi
	 * @param STARTID ID wierzcho³ka bêd¹cego pocz¹tkiem krawêdzi
	 * @param ENDID ID wierzcho³ka bêd¹cego koñcem krawêdzi
	 */
	Edge(int ID, int STARTID, int ENDID){
		id = ID;
		startId = STARTID;
		endId = ENDID;
	}
	
	/**
	 * konstruktor krawêdzi o takich samych parametrach co krawêdz podana jako parametr
	 * @param edge obiekt z którego kopiujemy parametry do utworzenia nowej krawedzi
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
	 * Ustawienie id wierzcho³ka bêd¹cego pocz¹tkiem krawêdzi
	 * @param ID id wierzcho³ka
	 */
	public void setStartID(int ID){
		startId = ID;
	}
	
	/**
	 * Ustawienie id wierzcho³ka bêd¹cego koñcem krawêdzi
	 * @param ID id wierzcho³ka
	 */
	public void setEndID(int ID) {
		endId = ID;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho³ka bêd¹cego pocz¹tkiem krawêdzi
	 */
	public int getStartID() {
		return startId;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho³ka bêd¹cego koñcem krawêdzi
	 */
	public int getEndID() {
		return endId;
	}
	
	/**
	 * 
	 * @param graph Obiekt Grafu do którego nale¿y dana krawêdz
	 * @return zwraca d³ugoœæ krawêdzi
	 */
	public double getLength(Graph graph) {
		double X = Math.abs(graph.getNode(startId).x() - graph.getNode(endId).x());
		double Y = Math.abs(graph.getNode(startId).y() - graph.getNode(endId).y());
		double length = Math.sqrt((X*X)+(Y*Y));
		return length;
	}
}
