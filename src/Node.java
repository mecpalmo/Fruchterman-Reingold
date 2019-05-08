
//klasa wierzcholka

public class Node {

	private int id; // id do rozrozniania wierzcholkow
	private double x; // polozenie na osi x
	private double y; // polozenie na osi y
	private double fx; //wartoœæ wektora x si³y wp³ywaj¹cej na wierzcho³ek 
	private double fy; //wartoœæ wektora y si³y wp³ywaj¹cej na wierzcho³ek
	
	Node(){
		x = 0;
		y = 0;
		id = 0;
		fx = 0;
		fy = 0;
	}
	
	Node(int ID, double X, double Y){
		x = X;
		y = Y;
		id = ID;
		fx = 0;
		fy = 0;
	}
	
	Node(Node node){
		x = node.x();
		y = node.y();
		id = node.id();
		fx = node.fx();
		fy = node.fy();
	}
	
	public void setX(double X) {
		x = X;
	}
	
	public void setY(double Y) {
		y = Y;
	}
	
	public void setID(int ID) {
		id = ID;
	}
	
	public int id() {
		return id;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	public double fx() {
		return fx;
	}
	
	public double fy() {
		return fy;
	}
	
	public void setfy(double y) {
		fy = y;
	}
	
	public void setfx(double x) {
		fx = x;
	}
	
	public void resetForces() {
		fy = 0;
		fx = 0;
	}
	
	public void applyForces(double t) {
		x = x + (fx/Math.abs(fx))*Math.min(Math.abs(fx),t);
		y = y + (fy/Math.abs(fy))*Math.min(Math.abs(fy),t);
	}
}
