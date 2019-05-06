
// klasa kraw�dz

public class Edge {

	private int id; // id do rozpoznawania krawedzi
	private int startId; // id wierzcholka poczatkowego
	private int endId; // id wierzcholka koncowego
	
	Edge(){
		
	}
	
	Edge(int ID, int STARTID, int ENDID){
		id = ID;
		startId = STARTID;
		endId = ENDID;
	}
	
	public int id() {
		return id;
	}
	
	public int startId() {
		return startId;
	}
	
	public int endId() {
		return endId;
	}
	
	public void setStartID(int ID){
		startId = ID;
	}
	
	public void setEndID(int ID) {
		endId = ID;
	}
	
	public int getStartID() {
		return startId;
	}
	
	public int getEndID() {
		return endId;
	}
	
	public float getLength(Graph graph) {
		float X = Math.abs(graph.getNode(startId).x() - graph.getNode(endId).x());
		float Y = Math.abs(graph.getNode(startId).y() - graph.getNode(endId).y());
		float length = (float) Math.sqrt((X*X)+(Y*Y));
		return length;
	}
}
