import java.util.ArrayList;
import java.util.List;

//Klasa bêd¹ca grafem

public class Graph {

	private List<Node> nodeList = new ArrayList<>();
	private List<Edge> edgeList = new ArrayList<>();
	
	Graph(){
		
	}
	
	public Node getNode(int i) {
		return nodeList.get(i);
	}
	
	public Edge getEdge(int i) {
		return edgeList.get(i);
	}
	
	public int nodeAmount() {
		return nodeList.size();
	}
	
	public int edgeAmount() {
		return edgeList.size();
	}
	
	public void addNode() {
		int index = nodeList.size();
		nodeList.add(new Node(index,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
	}
	
	public void addNode(Node node) {
		nodeList.add(node);
	}
	
	public void addEdge(int StartId, int EndId) {
		int index = edgeList.size();
		edgeList.add(new Edge(index,StartId,EndId));
	}
	
	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}
	
	public void removeNode() {
		nodeList.remove(nodeList.size()-1);
	}
	
	public void removeEdge() {
		edgeList.remove(edgeList.size()-1);
	}
	
	public void generateRandomGraph(int nodes, float edgeProbability) {
		if(nodes>0) {
		generateRandomNodes(nodes);
		}
		if(nodes>1) {
		generateRandomEdges(nodes,edgeProbability);
		}
	}
	
	public void generateRandomNodes(int amount) {
		while(nodeList.size()>0) {
			nodeList.remove(nodeList.size()-1);
		}
		for(int i=0;i<amount;i++) {
			nodeList.add(new Node(i,(float)Math.random()*Data.Dimension,(float)Math.random()*Data.Dimension));
			//generuje po³o¿enie w postaci float w zakresie od 0 do Data.Dimension (niezale¿ne od rozmiaru okna)
		}
	}
	
	private void generateRandomEdges(int nodeAmount, float edgeProbability) {
		while(edgeList.size()>0) {
			edgeList.remove(edgeList.size()-1);
		}
		int index = 0;
		for(int i=0;i<nodeAmount-1;i++) {
			boolean edgeFree = true; //parametr pomocniczy do zachowania spójnoœci grafu
			for(int j=i+1;j<nodeAmount;j++) {
				if(Math.random()<=edgeProbability) {
					edgeList.add(new Edge(index,i,j));
					index++;
					edgeFree = false;
				}
			}
			if(edgeFree) {
				edgeList.add(new Edge(index,i,(int)(Math.random()*nodeAmount)));
				index++;
				edgeFree = false;
			} // to wszystko do zmiany spójnoœæ grafu inaczej trzeba sprawdziæ
		}
	}
}
