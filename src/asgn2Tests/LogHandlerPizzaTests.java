package asgn2Tests;

import org.junit.*;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	
	@Before
	public void setup() {
		String log = "log/";
		
		// Initialize good files
		String goodFile1 = log + "20170101.txt";
		String goodFile2 = log + "20170102.txt";
		String goodFile3 = log + "20170103.txt";
		
		// Initialize corrupted files
		String corruptFile1 = log + "corrupt1.txt";
		String corruptFile2 = log + "corrupt2.txt";
		String corruptFile3 = log + "corrupt3.txt";
		String corruptFile4 = log + "corrupt4.txt";
		
		// Initialize invalid files
		String invalidFile1 = log + "invalid1.txt";
		String invalidFile2 = log + "invalid2.txt";
		String invalidFile3 = log + "invalid3.txt";
		String invalidFile4 = log + "invalid4.txt";
	}
	
	
	
}
