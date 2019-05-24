
/**
 * Klasa reprezentuj�ca obiekt wierzcho�ka
 *
 */
public class Node {

	private int id; // id do rozrozniania wierzcholkow
	private double x; // polozenie na osi x
	private double y; // polozenie na osi y
	private double fx; //warto�� wektora x si�y wp�ywaj�cej na wierzcho�ek 
	private double fy; //warto�� wektora y si�y wp�ywaj�cej na wierzcho�ek
	
	/**
	 * domy�lny konstruktor
	 */
	Node(){
		x = 0;
		y = 0;
		id = 0;
		fx = 0;
		fy = 0;
	}
	
	/**
	 * konstruktor
	 * @param ID numer identyfikacyjny wierzcho�ka
	 * @param X wsp�rz�dna na osi OX
	 * @param Y wsp�rz�dna na osi OY
	 */
	Node(int ID, double X, double Y){
		x = X;
		y = Y;
		id = ID;
		fx = 0;
		fy = 0;
	}
	
	/**
	 * konstruktor wierzcho�ka o takich samych parametrach co wierzcho�ek podany jako parametr
	 * @param node obiekt z kt�rego kopiujemy parametry do utworzenia nowego wierzcho�ka
	 */
	Node(Node node){
		x = node.x();
		y = node.y();
		id = node.id();
		fx = node.fx();
		fy = node.fy();
	}
	
	/**
	 * Ustawienie wsp�rz�dnej wierzcho�ka na osi OX
	 * @param X wsp�rz�dna na osi OX
	 */
	public void setX(double X) {
		x = X;
	}
	
	/**
	 * Ustawienie wsp�rz�dnej wierzcho�ka na osi OY
	 * @param Y wsp�rz�dna na osi OY
	 */
	public void setY(double Y) {
		y = Y;
	}
	
	/**
	 * Ustawienie id wierzcho�ka
	 * @param ID id wierzcho�ka
	 */
	public void setID(int ID) {
		id = ID;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho�ka
	 */
	public int id() {
		return id;
	}
	
	/**
	 * 
	 * @return zwraca wsp�rz�dn� na osi OX
	 */
	public double x() {
		return x;
	}
	
	/**
	 * 
	 * @return zwraca wsp�rz�dn� na osi OX
	 */
	public double y() {
		return y;
	}
	
	/**
	 * 
	 * @return zwraca warto�� wektora x si�y jaka dzia�a na wierzcho�ek
	 */
	public double fx() {
		return fx;
	}
	
	/**
	 * 
	 * @return zwraca warto�� wektora y si�y jaka dzia�a na wierzcho�ek
	 */
	public double fy() {
		return fy;
	}
	
	/**
	 * Ustawienie warto�ci wektora y si�y dzia�aj�cej na wierzcho�ek
	 * @param y warto�� wektora y si�y 
	 */
	public void setfy(double y) {
		fy = y;
	}
	
	/**
	 * Ustawienie warto�ci wektora x si�y dzia�aj�cej na wierzcho�ek
	 * @param x warto�� wektora x si�y
	 */
	public void setfx(double x) {
		fx = x;
	}
	
	/**
	 * zresetowanie warto�ci si� dzia�aj�cych na wierzcho�ek do zera
	 */
	public void resetForces() {
		fy = 0;
		fx = 0;
	}
	
	/**
	 * Zastosowanie si� dzia�aj�cych na wierzcho�ek na jego wsp�rz�dne
	 * @param t temperatura wynikaj�ca z algorytmu FruchtermanaReingolda
	 */
	public void applyForces(double t) {
		x = x + (fx/Math.abs(fx))*Math.min(Math.abs(fx),t);
		y = y + (fy/Math.abs(fy))*Math.min(Math.abs(fy),t);
	}
}
