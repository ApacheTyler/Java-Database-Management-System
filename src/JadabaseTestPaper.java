import java.util.ArrayList;
import java.util.Map;


public class JadabaseTestPaper {
	
	public void currentTest() throws Exception{
		
		RecordEvaluator eval = new RecordEvaluator("pilotssorted2", "flightskillssorted2", "test4");
		eval.naturalJoinColumnNames();
		eval.nestedLoopJoin();
		Algebra x = new Algebra();
		x.displayTable("test4");
		
	}
	
	public void testArrayListToMap() throws Exception{
		FileReader reader = new FileReader();
		ArrayList<String> toMap = reader.tildaDelimatedToArrayList(reader.readFileFirstLine(reader.getFileScanner("pilots")));
		Record recordMap = new Record();
		recordMap.createKeys(toMap);
		recordMap.printKeysAndValues();
	}
	
	public void testListIntersectToMap() throws Exception{
		FileReader reader = new FileReader();
		Record rec = new Record();
		ArrayList<String> flightSkills = reader.tildaDelimatedToArrayList(reader.readFileFirstLine(reader.getFileScanner("Flightskills")));
		ArrayList<String> pilots = reader.tildaDelimatedToArrayList(reader.readFileFirstLine(reader.getFileScanner("pilots")));
		ColumnNamesList test = new ColumnNamesList(pilots);
		Map<String,String> toPrint = test.intersectColumnNames(flightSkills);
		rec.setCurrentRecord(toPrint);
		rec.printKeysAndValues();
	}

}
