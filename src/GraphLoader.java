import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Klasa realizuj¹ca wczytanie grafu z pliku
 *
 */
public class GraphLoader {
	
	/**
	 * zmienna przechowuj¹ca odpowiedz na pytanie czy poprawnie wczytano graf z pliku
	 */
	private boolean loaded;
	
	/**
	 * konstruktor
	 */
	GraphLoader(){
		loaded = false;
		Data.StartGraph = new Graph();
	}
	
	//funkcja wczytuj¹ca graf z pliku, zwraca boolean czy siê uda³o
	/**
	 * Wczytanie z grafu pliku
	 * @return zwraca odpowiedz na pytanie czy wczytatnie przebieg³o pomyœlnie
	 */
	public boolean loadGraph() {
		
		String fileName = "";
		fileName = JOptionPane.showInputDialog("Podaj nazwê pliku");
		
		BufferedReader reader;
		FileReader feader;
		int lines = 0;
		try {
			feader = new FileReader(fileName);
			reader = new BufferedReader(feader);
			while (reader.readLine() != null) lines++;
			reader.close();
			System.out.println("lines: "+lines);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B³¹d wczytywania");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B³¹d wczytywania");
		}
		
		Data.StartGraph.generateRandomNodes(lines);
		
		try {
			feader = new FileReader(fileName);
			reader = new BufferedReader(feader);
			int columns = reader.readLine().length();
			System.out.println("columns: "+columns);
			reader.close();
			feader = new FileReader(fileName);
			reader = new BufferedReader(feader);
			char[][] matrix = new char[columns][lines];
			if(lines!=0 && columns!=0) {
				for(int i=0;i<lines;i++) {
					String line = reader.readLine();
					for(int j=0;j<columns;j++) {
						matrix[j][i] = line.charAt(j);
					}
				}
				
				for(int i=0;i<columns;i++) {
					boolean haveStart = false;
					boolean haveEnd = false;
					int StartId = 0;
					int EndId = 0;
					for(int j=0;j<lines;j++) {
						if(!haveStart) {
							if(matrix[i][j]=='1') {
								StartId = j;
								haveStart = true;
								System.out.println("StartId: "+j);
							}
						}else {
							if(!haveEnd) {
								if(matrix[i][j]=='1') {
									EndId = j;
									haveEnd = true;
									System.out.println("EndId: "+j);
								}
							}else {
								break;
							}
						}
					}
					Data.StartGraph.addEdge(StartId, EndId);
				}
				loaded = true;
			}else {
				JOptionPane.showMessageDialog(null, "B³¹d wczytywania");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B³¹d wczytywania");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "B³¹d wczytywania");
		}
		 
		return loaded;
	}
}
