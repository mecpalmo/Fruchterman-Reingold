
//klasa wierzcholka

public class Node {

	private int id; // id do rozrozniania wierzcholkow
	private float x; // polozenie na osi x
	private float y; // polozenie na osi y
	
	Node(){
		x = 0;
		y = 0;
		id = 0;
	}
	
	Node(int ID, float X, float Y){
		x = X;
		y = Y;
		id = ID;
	}
	
	public void setX(float X) {
		x = X;
	}
	
	public void setY(float Y) {
		y = Y;
	}
	
	public void setID(int ID) {
		id = ID;
	}
	
	public int id() {
		return id;
	}
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
}
