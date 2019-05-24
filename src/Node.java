
/**
 * Klasa reprezentuj¹ca obiekt wierzcho³ka
 *
 */
public class Node {

	private int id; // id do rozrozniania wierzcholkow
	private double x; // polozenie na osi x
	private double y; // polozenie na osi y
	private double fx; //wartoœæ wektora x si³y wp³ywaj¹cej na wierzcho³ek 
	private double fy; //wartoœæ wektora y si³y wp³ywaj¹cej na wierzcho³ek
	
	/**
	 * domyœlny konstruktor
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
	 * @param ID numer identyfikacyjny wierzcho³ka
	 * @param X wspó³rzêdna na osi OX
	 * @param Y wspó³rzêdna na osi OY
	 */
	Node(int ID, double X, double Y){
		x = X;
		y = Y;
		id = ID;
		fx = 0;
		fy = 0;
	}
	
	/**
	 * konstruktor wierzcho³ka o takich samych parametrach co wierzcho³ek podany jako parametr
	 * @param node obiekt z którego kopiujemy parametry do utworzenia nowego wierzcho³ka
	 */
	Node(Node node){
		x = node.x();
		y = node.y();
		id = node.id();
		fx = node.fx();
		fy = node.fy();
	}
	
	/**
	 * Ustawienie wspó³rzêdnej wierzcho³ka na osi OX
	 * @param X wspó³rzêdna na osi OX
	 */
	public void setX(double X) {
		x = X;
	}
	
	/**
	 * Ustawienie wspó³rzêdnej wierzcho³ka na osi OY
	 * @param Y wspó³rzêdna na osi OY
	 */
	public void setY(double Y) {
		y = Y;
	}
	
	/**
	 * Ustawienie id wierzcho³ka
	 * @param ID id wierzcho³ka
	 */
	public void setID(int ID) {
		id = ID;
	}
	
	/**
	 * 
	 * @return zwraca id wierzcho³ka
	 */
	public int id() {
		return id;
	}
	
	/**
	 * 
	 * @return zwraca wspó³rzêdn¹ na osi OX
	 */
	public double x() {
		return x;
	}
	
	/**
	 * 
	 * @return zwraca wspó³rzêdn¹ na osi OX
	 */
	public double y() {
		return y;
	}
	
	/**
	 * 
	 * @return zwraca wartoœæ wektora x si³y jaka dzia³a na wierzcho³ek
	 */
	public double fx() {
		return fx;
	}
	
	/**
	 * 
	 * @return zwraca wartoœæ wektora y si³y jaka dzia³a na wierzcho³ek
	 */
	public double fy() {
		return fy;
	}
	
	/**
	 * Ustawienie wartoœci wektora y si³y dzia³aj¹cej na wierzcho³ek
	 * @param y wartoœæ wektora y si³y 
	 */
	public void setfy(double y) {
		fy = y;
	}
	
	/**
	 * Ustawienie wartoœci wektora x si³y dzia³aj¹cej na wierzcho³ek
	 * @param x wartoœæ wektora x si³y
	 */
	public void setfx(double x) {
		fx = x;
	}
	
	/**
	 * zresetowanie wartoœci si³ dzia³aj¹cych na wierzcho³ek do zera
	 */
	public void resetForces() {
		fy = 0;
		fx = 0;
	}
	
	/**
	 * Zastosowanie si³ dzia³aj¹cych na wierzcho³ek na jego wspó³rzêdne
	 * @param t temperatura wynikaj¹ca z algorytmu FruchtermanaReingolda
	 */
	public void applyForces(double t) {
		x = x + (fx/Math.abs(fx))*Math.min(Math.abs(fx),t);
		y = y + (fy/Math.abs(fy))*Math.min(Math.abs(fy),t);
	}
}
