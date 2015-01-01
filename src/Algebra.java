
public class Algebra {
	
	public void displayTable(String fileName) throws Exception{
		
		FilePrinter print = new FilePrinter();
		print.displayTable(fileName);
		
	}
	
	public void nestedLoopJoin(String drivingTable, String innerTable, String outputFile) throws Exception{
		RecordEvaluator eval = new RecordEvaluator(drivingTable, innerTable, outputFile);
		eval.naturalJoinColumnNames();
		eval.nestedLoopJoin();
	}
	
	public void mergeJoin(String drivingTable, String innerTable, String outputFile) throws Exception{
		RecordEvaluator eval = new RecordEvaluator(drivingTable, innerTable, outputFile);
		eval.naturalJoinColumnNames();
		eval.nestedLoopJoin();
	}
	
	public void naturalInnerJoin(String fileName1, String fileName2, String outputFile, String joinAlg) throws Exception{
		
		String normalizedAlg = joinAlg.toLowerCase();
		if(normalizedAlg.equals("nl")){
			nestedLoopJoin(fileName1, fileName2, outputFile);
		}
		else if(normalizedAlg.equals("sm")){
			mergeJoin(fileName1, fileName2, outputFile);
		}
		else{
			throw new Exception();
		}
		
	}
	

}
