
//klasa wierzcholka

public class Node {

	private int id; // id do rozrozniania wierzcholkow
	private double x; // polozenie na osi x
	private double y; // polozenie na osi y
	private double fx; //warto�� wektora x si�y wp�ywaj�cej na wierzcho�ek 
	private double fy; //warto�� wektora y si�y wp�ywaj�cej na wierzcho�ek
	
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
	
	public void applyForces() {
		x = x + fx;
		y = y + fy;
	}
}
