import java.awt.Color;

/**
 * Klasa przechowuj�ca parametry statyczne w celu umo�liwienia dost�pu do nich z dowolnego miejsca programu
 *
 */
public class Data {
	
	/**
	 * D�ugo�� boku kwadratowego okna prezentuj�cego graf (w pikselach)
	 */
	public static final int WindowSize = 600;
	/**
	 * D�ugo�� boku kwadratowej p�aszczyzny na kt�rej istnieje graf (nie ma nic wsp�lnego z rozmiarami okien programu)
	 */
	public static final int Dimension = 100;

	/**
	 * Obiekt grafu pocz�tkowego
	 */
	public static Graph StartGraph = new Graph();
	
	/**
	 * kolor t�a okna grafu
	 */
	public static final Color Background = Color.WHITE;
	/**
	 * kolor wierzcho�ka grafu
	 */
	public static final Color NodeColor = Color.BLUE;
	/**
	 * kolor kraw�dzi grafu
	 */
	public static final Color EdgeColor = Color.BLACK;
	
	/**
	 * grubo�� wierzcho�ka rysowanego
	 */
	public static final int NodeSize = 6;
	
	/**
	 * domy�lna warto�� oddzia�ywania granic p�aszczyzny prezentacji [%]
	 */
	public static int wallForce = 10;
	/**
	 * domy�lna warto�� mno�nika parametru k [%]
	 */
	public static int kFactor = 100;
	/**
	 * domy�lna warrto�� szybko�ci redukcji temperatury
	 */
	public static int tempDecrease = 2;
	/**
	 * domy�lna warto�� liczby iteracji
	 */
	public static int iterations = 300;
	
}
