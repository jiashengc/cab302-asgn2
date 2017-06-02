package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Jia Sheng Chong (n9901990)
 *
 */
public class RestaurantPizzaTests {
	
	PizzaRestaurant PRDummy;
	ArrayList<PizzaRestaurant> PR;
	ArrayList<PizzaRestaurant> PRInvalid;
	ArrayList<PizzaRestaurant> PRCorrupt;
	ArrayList<Pizza> pizzas;
	
	double percision = 0.0001;
	
	
	@Before
	public void setup() throws CustomerException, PizzaException, LogHandlerException {
		String l = "logs/";
		
		// Initialize the PizzaRestaurant ArrayList
		PRDummy = new PizzaRestaurant();
		PR = new ArrayList<PizzaRestaurant>();
		PRInvalid = new ArrayList<PizzaRestaurant>();
		PRCorrupt = new ArrayList<PizzaRestaurant>();
		
		// Initialize the pizza
		pizzas = new ArrayList<Pizza>();
		
		// Initialize all the .txt files for testing
		for (int i = 0; i < 3; i+=1) {
			PizzaRestaurant temp = new PizzaRestaurant();
			PR.add(temp);
			PRInvalid.add(temp);
			PRCorrupt.add(temp);
		}
		
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			PR.get(i-1).processLog(l + "2017010" + no + ".txt");
		}
	}
	
	// Test if adding invalid or corrupted files is okay
	@Test(expected=PizzaException.class)
	public void testProcessInvalidFiles() throws CustomerException, PizzaException, LogHandlerException {
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			PRInvalid.get(i).processLog("logs/" + "invalid" + no + ".txt");
		}
	}
	
	@Test(expected=LogHandlerException.class)
	public void testProcessCorruptedFiles() throws CustomerException, PizzaException, LogHandlerException {
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			PRCorrupt.get(i).processLog("logs/" + "corrupt" + no + ".txt");
		}
	}
	
	// Tests for getNumPizzaOrders function
	@Test
	public void testGetNumPizzaOrders1() {
		assertEquals(3, PR.get(0).getNumPizzaOrders());
	}
	
	@Test
	public void testGetNumPizzaOrders2() {
		assertEquals(10, PR.get(1).getNumPizzaOrders());
	}
	
	@Test
	public void testGetNumPizzaOrders3() {
		assertEquals(100, PR.get(2).getNumPizzaOrders());
	}
	
	@Test
	public void testGetNumPizzaOrdersNegative() throws PizzaException {
		if (PR.get(0).getNumPizzaOrders() < 0) {
			throw new PizzaException("There cannot be negative pizzas!");
		}
	}
	
	// Tests for getPizzaByIndex function
	@Test
	public void testGetPizzaByIndex1() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		assertEquals(Pizza, PR.get(0).getPizzaByIndex(0));
	}
	
	@Test
	public void testGetPizzaByIndex2() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1");
		assertEquals(Pizza, PR.get(0).getPizzaByIndex(1));
	}
	
	@Test
	public void testGetPizzaByIndex3() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3");
		assertEquals(Pizza, PR.get(0).getPizzaByIndex(2));
	}
	
	@Test
	public void testGetPizzaByIndex4() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
		assertEquals(Pizza, PR.get(1).getPizzaByIndex(9));
	}
	
	@Test
	public void testGetPizzaByIndex5() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("20:12:00,20:22:00,Liam Chen,0939084409,DVC,2,-3,PZV,7");
		assertEquals(Pizza, PR.get(2).getPizzaByIndex(68));
	}
	
	@Test
	public void testGetPizzaByIndex6() throws PizzaException, LogHandlerException {
		Pizza Pizza = LogHandler.createPizza("20:13:00,20:29:00,Oliver Lee,0631900709,DVC,8,-5,PZM,9");
		assertEquals(Pizza, PR.get(2).getPizzaByIndex(84));
	}
	
	@Test (expected=PizzaException.class)
	public void testGetPizzaByIndexNoPizzas() throws PizzaException {
		PRDummy.getPizzaByIndex(0);
	}
	
	@Test (expected=PizzaException.class)
	public void testGetPizzaByIndexNoPizzas2() throws PizzaException {
		PRDummy.getPizzaByIndex(50);
	}
	
	// Tests for getTotalProfit function
	@Test
	public void testGetTotalProfit1() throws PizzaException {
		assertEquals(36.5, PR.get(0).getTotalProfit(), percision);
	}
	
	@Test
	public void testGetTotalProfit2() throws PizzaException {
		assertEquals(316.5, PR.get(1).getTotalProfit(), percision);
	}
	
	// Tests for resetDetails function
	@Test (expected=PizzaException.class)
	public void testResetDetailsWithGetPizzaByIndex1() throws PizzaException {
		PR.get(0).resetDetails();
		Pizza test = PR.get(0).getPizzaByIndex(0);
	}
	
	@Test (expected=PizzaException.class)
	public void testResetDetailsWithGetPizzaByIndex2() throws PizzaException {
		PR.get(1).resetDetails();
		Pizza test = PR.get(1).getPizzaByIndex(0);
	}
	
	@Test (expected=PizzaException.class)
	public void testResetDetailsWithGetPizzaByIndex3() throws PizzaException {
		PR.get(2).resetDetails();
		Pizza test = PR.get(2).getPizzaByIndex(0);
	}
	
	@Test
	public void testResetDetailsWithGetPizzaOrders1() throws PizzaException {
		PR.get(0).resetDetails();
		assertEquals(0, PR.get(0).getNumPizzaOrders());
	}
	
	@Test
	public void testResetDetailsWithGetPizzaOrders2() throws PizzaException {
		PR.get(1).resetDetails();
		assertEquals(0, PR.get(1).getNumPizzaOrders());
	}
	
	@Test
	public void testResetDetailsWithGetPizzaOrders3() throws PizzaException {
		PR.get(2).resetDetails();
		assertEquals(0, PR.get(2).getNumPizzaOrders());
	}
	
	@Test
	public void testResetDetailsWithGetTotalProfit1() throws PizzaException {
		PR.get(0).resetDetails();
		assertEquals(0, PR.get(0).getTotalProfit(), percision);
	}
	
	@Test
	public void testResetDetailsWithGetTotalProfit2() throws PizzaException {
		PR.get(1).resetDetails();
		assertEquals(0, PR.get(1).getTotalProfit(), percision);
	}
	
	@Test
	public void testResetDetailsWithGetTotalProfit3() throws PizzaException {
		PR.get(2).resetDetails();
		assertEquals(0, PR.get(2).getTotalProfit(), percision);
	}
}
