import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class RecordEvaluator {
	
	public Record table1;
	public Record table2;
	public String output;
	
	public RecordEvaluator(String fileName1, String fileName2, String outputFile){
		
		table1 = new Record(fileName1);
		table2 = new Record(fileName2);
		this.output = outputFile;
		
	}
	
	/*
	 * Joins column names
	 */
	public void naturalJoinColumnNames() throws Exception{
		
		try{
		this.table1.createKeysOfIntersectedList(this.table2.getFileName());
		this.table2.createKeysOfIntersectedList(this.table1.getFileName());
		}
		catch(Exception e){
			System.out.println("File name does not exist");
		}
		
	}
	
	public ArrayList<String> combineArrayList(ArrayList<String> list1, ArrayList<String> list2){
		
		ArrayList<String> matches = new ArrayList<String>();
		String currentValue;
		matches = list1;
		Iterator<String> iter = list2.iterator();
		while(iter.hasNext()){
			currentValue = iter.next();
			if(!matches.contains(currentValue)){
				matches.add(currentValue);
			}
		}
		
		return matches;
		
	}
	
	public BufferedWriter configOutputFile() throws IOException{
		FileWriter outStream = new FileWriter(this.output + ".txt");
		BufferedWriter out = new BufferedWriter(outStream);
		return out;
	}
	
	/*
	 * Natural join. Nested Loop.
	 */
	public void nestedLoopJoin() throws Exception{
		
		FileReader table1Reader = new FileReader();
		FileReader table2Reader = new FileReader();
		Scanner table1Scanner = table1Reader.getFileScanner(table1.getFileName());
		Scanner table2Scanner = table2Reader.getFileScanner(table2.getFileName());//Handle file i/o
		String currentTable1Record;
		String currentTable2Record;
		ArrayList<String> table1Columns = table1Reader.tildaDelimatedToArrayList(table1Reader.readInRecord(table1Scanner));
		ArrayList<String> table2Columns = table2Reader.tildaDelimatedToArrayList(table2Reader.readInRecord(table2Scanner));//scan in column names
		ColumnNamesList columnNamePrinter= new ColumnNamesList(table1Columns);//get columns name for intersect
		table1.setValues(table1Columns);//set values of table 1 record
		table2.setValues(table2Columns);//set values of table 2 record
		BufferedWriter outputWriter = configOutputFile();
		outputWriter.write(table1Reader.saveArrayList(columnNamePrinter.joinColumnValues(table2Columns)));//Print matching column names
		
		/*
		 * nested loop
		 */
		while(table1Scanner.hasNext()){
			currentTable1Record = table1Scanner.nextLine();
			table1.setValues(table1Reader.tildaDelimatedToArrayList(currentTable1Record));//Set values of record
			table2Scanner = table2Reader.getFileScanner(table2.getFileName());
			while(table2Scanner.hasNext()){
				currentTable2Record = table2Scanner.nextLine();
				table2.setValues(table2Reader.tildaDelimatedToArrayList(currentTable2Record));//Set values of record
				//System.out.println(currentTable2Record);
				if(table1.recordIsEqual(table2.getCurrentRecord())){/**THIS NEEDS TO GET FIXED**/
					/**if(table2Scanner.hasNext()){
						currentTable2Record = currentTable2Record.substring(1, currentTable2Record.length()) + "\n";
					}
					else{
						currentTable2Record = currentTable2Record.substring(1, currentTable2Record.length());
					}**/
					table1Columns = table1Reader.tildaDelimatedToArrayList(currentTable1Record);
					table2Columns = table2Reader.tildaDelimatedToArrayList(currentTable2Record);
					table1Columns = combineArrayList(table1Columns, table2Columns);
					outputWriter.write(table1Reader.saveArrayList(table1Columns));
				}
			}
		}
		
		outputWriter.close();
		
	}
	
	
	/*
	 * Merge Join.
	 */
	public void mergeJoin() throws Exception{
		
			
			FileReader table1Reader = new FileReader();
			FileReader table2Reader = new FileReader();
			Scanner table1Scanner = table1Reader.getFileScanner(table1.getFileName());
			Scanner table2Scanner = table2Reader.getFileScanner(table2.getFileName());//Handle file i/o
			String currentTable1Record;
			String currentTable2Record;
			ArrayList<String> table1Columns = table1Reader.tildaDelimatedToArrayList(table1Reader.readInRecord(table1Scanner));
			ArrayList<String> table2Columns = table2Reader.tildaDelimatedToArrayList(table2Reader.readInRecord(table2Scanner));//scan in column names
			ColumnNamesList columnNamePrinter= new ColumnNamesList(table1Columns);//get columns name for intersect
			table1.setValues(table1Columns);//set values of table 1 record
			table2.setValues(table2Columns);//set values of table 2 record
			BufferedWriter outputWriter = configOutputFile();
			outputWriter.write(table1Reader.saveArrayList(columnNamePrinter.joinColumnValues(table2Columns)));//Print matching column names
			boolean matches = true;
			
			/*
			 * merege loop
			 */
			currentTable2Record = table2Scanner.nextLine();
			while(table1Scanner.hasNext()){
				matches = true;
				currentTable1Record = table1Scanner.nextLine();
				table1.setValues(table1Reader.tildaDelimatedToArrayList(currentTable1Record));//Set values of record
				while(matches && table2Scanner.hasNext()){
					table2.setValues(table2Reader.tildaDelimatedToArrayList(currentTable2Record));//Set values of record
					if(!table1.recordIsEqual(table2.getCurrentRecord())){
						matches = false;
					}
					else{
					table1Columns = table1Reader.tildaDelimatedToArrayList(currentTable1Record);
					table2Columns = table2Reader.tildaDelimatedToArrayList(currentTable2Record);
					table1Columns = combineArrayList(table1Columns, table2Columns);
					outputWriter.write(table1Reader.saveArrayList(table1Columns));
					currentTable2Record = table2Scanner.nextLine();
					if(!table2Scanner.hasNext()){//So messy but works (for the most part)
						table1Columns = table1Reader.tildaDelimatedToArrayList(currentTable1Record);
						table2Columns = table2Reader.tildaDelimatedToArrayList(currentTable2Record);
						table1Columns = combineArrayList(table1Columns, table2Columns);
						outputWriter.write(table1Reader.saveArrayList(table1Columns));
					}
					}
					
				}
			}
						
			
			outputWriter.close();
			
		}
	
	

}
