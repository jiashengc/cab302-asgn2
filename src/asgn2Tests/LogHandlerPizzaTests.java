package asgn2Tests;

import java.util.ArrayList;

import org.junit.*;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	
	ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	Pizza pizza1;
	Pizza pizza2;
	String goodFile1;
	String goodFile2;
	String goodFile3;
	String corruptFile1;
	String corruptFile2;
	String corruptFile3;
	String corruptFile4;
	String invalidFile1;
	String invalidFile2;
	String invalidFile3;
	String invalidFile4;
	String str;
	
	@Before
	public void setup() {
		String log = "logs/";
		
		// Initialize good files
		goodFile1 = log + "20170101.txt";
		goodFile2 = log + "20170102.txt";
		goodFile3 = log + "20170103.txt";
		
		// Initialize corrupted files
		corruptFile1 = log + "corrupt1.txt";
		corruptFile2 = log + "corrupt2.txt";
		corruptFile3 = log + "corrupt3.txt";
		corruptFile4 = log + "corrupt4.txt";
		
		// Initialize invalid files
		invalidFile1 = log + "invalid1.txt";
		invalidFile2 = log + "invalid2.txt";
		invalidFile3 = log + "invalid3.txt";
		invalidFile4 = log + "invalid4.txt";
	}
	
	// Test for testPopulatePizzaDataset functions
	@Test
	public void testPopulatePizzaDataset1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile1);
	}
	
	@Test
	public void testPopulatePizzaDataset2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile2);
	}
	
	@Test
	public void testPopulatePizzaDataset3() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptFile1);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptFile2);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt3() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt4() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptFile4);
	}
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile1);
	}
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile2);
	}
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid3() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile3);
	}
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid4() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile4);
	}
	
	
	// Tests for testCreatePizza function
	@Test
	public void testCreatePizza1() throws PizzaException, LogHandlerException {
		str = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void testCreatePizza2() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00,Casey Jonesss,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=LogHandlerException.class) 
	public void testCreatePizza3() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00,Casey Jonesss,0123456789,DVC,5,5,PZV";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void testCreatePizza4() throws PizzaException, LogHandlerException {
		str = "18:00:00,17:20:00,Casey Jonesss,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void testCreatePizza5() throws PizzaException, LogHandlerException {
		str = "18:00:00,18:20:00,Casey Jones,0123456789,DVC,5,5,ZZZ,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void testCreatePizza6() throws PizzaException, LogHandlerException {
		str = "18:00:00,18:59:00,Casey Jonesss,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}

	@Test(expected=LogHandlerException.class)
	public void testCreatePizza7() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00,Casey Jonesss,0123456789,DVC,5,5,PZV";
		pizza1 = LogHandler.createPizza(str);
	}
	
}
