import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;


public class FilePrinter {
	
	final FileReader reader = new FileReader();

	public File getTextFile(String fileName){
		
		File fileToRead = new File(fileName + ".txt");
		return fileToRead;
		
	}
	
	public ArrayList<String> printColumnUnderlines(ArrayList<String> toPrint){//Stopped here
		
		Iterator<String> iter = toPrint.iterator();
		String currentNode;
		String lines = "-";
		ArrayList<String> linesToPrint = new ArrayList<String>();
		while(iter.hasNext()){
			lines = "-";
			currentNode = iter.next();
			for(int i = 0; i < currentNode.length(); ++i){
				lines += "-";
			}
			linesToPrint.add(lines);
		}
		
		return linesToPrint;
		
	}
	
	
	public void displayTable(String tableFileName) throws Exception{
		
		Scanner scan = new Scanner(getTextFile(tableFileName));
		ArrayList<String> tempPrint = new ArrayList<String>();
		
		tempPrint = reader.tildaDelimatedToArrayList(scan.nextLine());
		System.out.println(reader.printArrayList(tempPrint));
		
		System.out.println(reader.printArrayList(printColumnUnderlines(tempPrint)));
				
		while(scan.hasNextLine()){
			tempPrint = reader.tildaDelimatedToArrayList(scan.nextLine());
			System.out.println(reader.printArrayList(tempPrint));
		}
				
	}
	
}
