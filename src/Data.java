import java.awt.Color;

/**
 * Klasa przechowuj¹ca parametry statyczne w celu umo¿liwienia dostêpu do nich z dowolnego miejsca programu
 *
 */
public class Data {
	
	/**
	 * D³ugoœæ boku kwadratowego okna prezentuj¹cego graf (w pikselach)
	 */
	public static final int WindowSize = 600;
	/**
	 * D³ugoœæ boku kwadratowej p³aszczyzny na której istnieje graf (nie ma nic wspólnego z rozmiarami okien programu)
	 */
	public static final int Dimension = 100;

	/**
	 * Obiekt grafu pocz¹tkowego
	 */
	public static Graph StartGraph = new Graph();
	
	/**
	 * kolor t³a okna grafu
	 */
	public static final Color Background = Color.WHITE;
	/**
	 * kolor wierzcho³ka grafu
	 */
	public static final Color NodeColor = Color.BLUE;
	/**
	 * kolor krawêdzi grafu
	 */
	public static final Color EdgeColor = Color.BLACK;
	
	/**
	 * gruboœæ wierzcho³ka rysowanego
	 */
	public static final int NodeSize = 6;
	
	/**
	 * domyœlna wartoœæ oddzia³ywania granic p³aszczyzny prezentacji [%]
	 */
	public static int wallForce = 10;
	/**
	 * domyœlna wartoœæ mno¿nika parametru k [%]
	 */
	public static int kFactor = 100;
	/**
	 * domyœlna warrtoœæ szybkoœci redukcji temperatury
	 */
	public static int tempDecrease = 2;
	/**
	 * domyœlna wartoœæ liczby iteracji
	 */
	public static int iterations = 300;
	
}
