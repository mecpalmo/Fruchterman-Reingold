import java.awt.Color;

//tutaj bêd¹ znajdowa³y siê wszystkie dane grafu w postaci
//static aby z ka¿dego miejsca programu mo¿na by³o siê do
//nich dostaæ

//s¹ tutaj równie¿ ogólne niezmienne parametry maj¹ce wp³yw na dzia³anie programu

public class Data {
	
	public static final int WindowSize = 600;
	public static final int Dimension = 100;

	public static Graph StartGraph = new Graph();
	public static Graph EndGraph = new Graph();
	
	public static final Color Background = Color.WHITE;
	public static final Color NodeColor = Color.BLUE;
	public static final Color EdgeColor = Color.BLACK;
	
	public static final int NodeSize = 6;
	public static final int EdgeWidth = 1; // nieu¿ywane
	
	public static int wallForce = 10; //w procentach %
}
