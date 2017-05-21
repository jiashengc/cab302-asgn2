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
	
	@Test
	public void testPopulatePizzaDataset() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile1);
		System.out.println(pizzas);
	}
	
	@Test
	public void testCreatePizza() throws PizzaException, LogHandlerException {
		String str = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
}
