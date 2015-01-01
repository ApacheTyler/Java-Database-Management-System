import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class FileReader {
	
	private final int MAX_STR_LNGTH = 20;
	
	public Scanner getFileScanner(String fileName) throws Exception{
		
		File fileToRead = new File(fileName + ".txt");
		
		Scanner scan = new Scanner(fileToRead);
		return scan;
		
	}
	
	public String readFileFirstLine(Scanner scan) throws Exception{
		
		return scan.nextLine();
		
	}
	
	public String readInRecord(Scanner scan){
		
		return scan.nextLine();
		
	}
	
	/**
	 * Convert tilda delimted string to arrayList
	 * @param tildaDelimtedLine
	 * @return toReturn (arrayList duh!)
	 */
	public ArrayList<String> tildaDelimatedToArrayList(String tildaDelimtedLine){
		
		ArrayList<String> toReturn = new ArrayList<String>(Arrays.asList(tildaDelimtedLine.split("~")));
		return toReturn;
		
	}
	
	public String formatString(String input){
		
		int difference = this.MAX_STR_LNGTH - input.length();
		
		for(int i = 0; i < difference; ++i){
			input += " ";
		}
		
		return input;
	}
	
	/**
	 * For testing purposes only
	 * @param toPrint
	 * @return toReturn(String reprsentation of an arrayList)
	 */
	public String printArrayList(ArrayList<String> toPrint){
		
		Iterator<String> iter = toPrint.iterator();
		String toReturn = "";
		
		while(iter.hasNext()){
			toReturn += formatString(iter.next());
		}
		
		return toReturn;
		
	}
	
	/**
	 * For testing purposes only
	 * @param toPrint
	 * @return toReturn(String reprsentation of an arrayList)
	 */
	public String saveArrayList(ArrayList<String> toPrint){
		
		Iterator<String> iter = toPrint.iterator();
		String toReturn = "";
		
		while(iter.hasNext()){
			toReturn += iter.next() + "~";
		}
		
		toReturn = toReturn.substring(0, toReturn.length()-1) + "\n";//Remove last tilda
		
		return toReturn;
		
	}
	
	
}
