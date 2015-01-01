
public class Driver {
	
	/**
	 * Presumed that a custom driver will be used. No UI included. All methods required in Algebra class. 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
		Algebra myAlgebra = new Algebra();
		myAlgebra.naturalInnerJoin("FlightskillsSorted", "PilotsSorted", "PilotSkills", "nl");
		myAlgebra.displayTable("PilotSkills");
		
	}

}
