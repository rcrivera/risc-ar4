package icom4215_phase1.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadInputFile{

	private static BufferedReader input;
	
	public ReadInputFile(String filename) throws FileNotFoundException{
		input = new BufferedReader(new FileReader(filename)); 
	}
	 
	public static String readNextLine() throws IOException{
		return input.readLine();
	}	
}