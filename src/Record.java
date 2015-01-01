import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Record {
	
	private Map<String, String> currentRecord = new HashMap<String, String>();
	private String fileName;
	
	public Record(){}
	
	public Record(String fleNme){
		this.fileName = fleNme;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public Map<String, String> getCurrentRecord(){
		return this.currentRecord;
	}
	
	public void setCurrentRecord(Map<String,String> toMap){
		this.currentRecord = toMap;
	}
	
	public void createKeys(ArrayList<String> toMap){
		
		Iterator<String> iter = toMap.iterator();
		String currentNode;
		while(iter.hasNext()){
			currentNode = iter.next();
			this.currentRecord.put(toMap.indexOf(currentNode) + "", currentNode);
		}
		
	}
	
	public void createKeysOfIntersectedList(String fileNameToIntersect) throws Exception{
		
		FileReader reader = new FileReader();
		ArrayList<String> recordColumnNames = reader.tildaDelimatedToArrayList(reader.readFileFirstLine(reader.getFileScanner(this.fileName)));
		ArrayList<String> columnNamesForIntersect = reader.tildaDelimatedToArrayList(reader.readFileFirstLine(reader.getFileScanner(fileNameToIntersect)));
		ColumnNamesList toMap = new ColumnNamesList(recordColumnNames);
		this.currentRecord = toMap.intersectColumnNames(columnNamesForIntersect);
		
	}
	
	public void setValues(ArrayList<String> toMap){
		
		Iterator<String> iter = this.currentRecord.keySet().iterator();
		String currentNode;
		String indexValue;
		while(iter.hasNext()){
			currentNode = iter.next();
			indexValue = toMap.get(Integer.parseInt(currentNode));
			this.currentRecord.put(currentNode, indexValue);
		}
		
	}
	
	public boolean recordIsEqual(Map<String, String> otherRecord){
		
		Iterator<String> iter = this.currentRecord.keySet().iterator();
		boolean toReturn = true;
		String key;
		
		while(iter.hasNext()){
			
			key = iter.next();
			if(!(otherRecord.containsValue(this.currentRecord.get(key)))){
				toReturn = false;
			}
			
		}
		
		return toReturn;
		
	}
	
	/**
	 * For testing purposes only
	 */
	public void printKeysAndValues(){
		
		Iterator<String> iter = this.currentRecord.keySet().iterator();
		String currentKey;
		while(iter.hasNext()){
			currentKey = iter.next();
			System.out.println(currentKey + " -- " + this.currentRecord.get(currentKey));
		}
		
	}

}
