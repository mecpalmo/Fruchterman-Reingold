import java.io.FileNotFoundException;
import java.util.Formatter;

import javax.swing.JOptionPane;

public class GraphSaver {

	private Formatter myFormatter;
	
	GraphSaver(){
		
	}
	
	public void saveGraph() {
		
		String fileName = JOptionPane.showInputDialog("Podaj nazwê pliku do zapisu (bez rozszerzenia)");
		fileName = fileName + ".txt";
		String newline = System.getProperty("line.separator");
		
		try {
			myFormatter = new Formatter(fileName);
			
			for(int i=0;i<Data.StartGraph.nodeAmount();i++) {
				
				String line = "";
				for(int j=0;j<Data.StartGraph.edgeAmount();j++) {
					if(Data.StartGraph.getEdge(j).startId()==i || Data.StartGraph.getEdge(j).endId()==i) {
						line = line + "1";
					}else {
						line = line + "0";
					}
				}
				
				myFormatter.format(line);
				if(i!=Data.StartGraph.nodeAmount()-1) {
					myFormatter.format(newline);
				}
				
			}
			
			myFormatter.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B³¹d zapisu");
		}
		
	}
	
}
