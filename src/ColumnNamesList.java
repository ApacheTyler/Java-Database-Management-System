import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ColumnNamesList {
	
	private ArrayList<String> tableColumns;
	
	
	

	ColumnNamesList(ArrayList<String> drivingTable){
		
		this.tableColumns = drivingTable;
		
	}
	
	public ArrayList<String> getTableColumns(){
		return this.tableColumns;
	}
	
	public void setTableColumns(ArrayList<String> tableColumns) {
		this.tableColumns = tableColumns;
	}
		
	
	public ArrayList<String> joinColumnValues(ArrayList<String> tableColumnList){
		Iterator<String> myList = this.tableColumns.iterator();
		Iterator<String> otherList = tableColumnList.iterator();
		ArrayList<String> matches = new ArrayList<String>();
		String currentValue;
		
		while(myList.hasNext()){
			currentValue = myList.next();
			matches.add(currentValue);
		}
		while(otherList.hasNext()){
			currentValue = otherList.next();
			if(!this.tableColumns.contains(currentValue)){
				matches.add(currentValue);
			}
		}
	
		
		return matches;
	}
	
	public Map<String,String> intersectColumnNames(ArrayList<String> tableColumnList){
		
		Iterator<String> myList = this.tableColumns.iterator();
		String currentValue;
		Map<String, String> matches = new HashMap<String, String>();
		
		while(myList.hasNext()){
			currentValue = myList.next();
			if(tableColumnList.contains(currentValue)){
				matches.put(this.tableColumns.indexOf(currentValue) + "", currentValue);
			}
		}
		
		return matches;
	}

}
