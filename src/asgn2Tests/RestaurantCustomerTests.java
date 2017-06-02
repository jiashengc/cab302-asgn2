package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Ji Su Choi (n9678166)
 */
public class RestaurantCustomerTests {
	// TO DO
	PizzaRestaurant PRDummy;
	ArrayList<PizzaRestaurant> pizzaRestaurant;
	ArrayList<PizzaRestaurant> invalidPizzaRestaurant;
	ArrayList<PizzaRestaurant> incorrectPizzaRestaurant;
	ArrayList<Customer> customers;
	
	@Before
	public void setup() throws CustomerException, PizzaException, LogHandlerException {
		String log = "logs/";
		
		// Initialize the PizzaRestaurant ArrayList
		PRDummy = new PizzaRestaurant();
		pizzaRestaurant = new ArrayList<PizzaRestaurant>();
		invalidPizzaRestaurant = new ArrayList<PizzaRestaurant>();
		incorrectPizzaRestaurant = new ArrayList<PizzaRestaurant>();
		
		// Initialize the customer
		customers = new ArrayList<Customer>();
		
		// Initialize all the .txt files for testing
		for (int i = 0; i < 3; i+=1) {
			PizzaRestaurant temp = new PizzaRestaurant();
			pizzaRestaurant.add(temp);
			invalidPizzaRestaurant.add(temp);
			incorrectPizzaRestaurant.add(temp);
		}
				
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			pizzaRestaurant.get(i-1).processLog(log + "2017010" + no + ".txt");
		}
	}
	
	// Test if inputting invalid or corrupted files is fine or not.
	@Test(expected=LogHandlerException.class)
	public void testProcessInvalidFiles() throws CustomerException, PizzaException, LogHandlerException {
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			invalidPizzaRestaurant.get(i).processLog("logs/" + "invalid" + no + ".txt");
		}
	}
	
	@Test(expected=LogHandlerException.class)
	public void testProcessIncorrectFiles() throws CustomerException, PizzaException, LogHandlerException {
		for (int i = 1; i <= 3; i+=1) {
			String no = Integer.toString(i);
			incorrectPizzaRestaurant.get(i).processLog("logs/" + "incorrect" + no + ".txt");
		}
	}
	
	// Tests for getNumPizzaOrders function
	@Test
	public void testGetNumCustomerOrders1() {
		assertEquals(3, pizzaRestaurant.get(0).getNumCustomerOrders());
	}
		
	@Test
	public void testGetNumCustomerOrders2() {
		assertEquals(10, pizzaRestaurant.get(1).getNumCustomerOrders());
	}
		
	@Test
	public void testGetNumCustomerOrders3() {
		assertEquals(100, pizzaRestaurant.get(2).getNumCustomerOrders());
	}
		
	@Test
	public void testGetNumCustomerOrdersNegative() throws CustomerException {
		if (pizzaRestaurant.get(0).getNumCustomerOrders() <= 0) {
			throw new CustomerException("There cannot be customers less than 0.");
		}
	}
	
	// Tests for getCustomerByIndex function.
	@Test
	public void testGetCustomerByIndex1() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		assertEquals(customer, pizzaRestaurant.get(0).getCustomerByIndex(0));
	}
	
	@Test
	public void testGetCustomerByIndex2() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1");
		assertEquals(customer, pizzaRestaurant.get(0).getCustomerByIndex(1));
	}
	
	@Test
	public void testGetCustomerByIndex3() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3");
		assertEquals(customer, pizzaRestaurant.get(0).getCustomerByIndex(2));
	}
	
	@Test
	public void testGetCustomerByIndex4() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
		assertEquals(customer, pizzaRestaurant.get(1).getCustomerByIndex(9));
	}
	
	@Test
	public void testGetCustomerByIndex5() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("20:12:00,20:22:00,Liam Chen,0939084409,DVC,2,-3,PZV,7");
		assertEquals(customer, pizzaRestaurant.get(2).getCustomerByIndex(68));
	}
	
	@Test
	public void testGetCustomerByIndex6() throws CustomerException, LogHandlerException {
		Customer customer = LogHandler.createCustomer("20:13:00,20:29:00,Oliver Lee,0631900709,DVC,8,-5,PZM,9");
		assertEquals(customer, pizzaRestaurant.get(2).getCustomerByIndex(84));
	}
	
	@Test (expected=CustomerException.class)
	public void testGetCustomerByIndexNoCustomer1() throws CustomerException {
		PRDummy.getCustomerByIndex(0);
	}
	
	@Test (expected=CustomerException.class)
	public void testGetCustomerByIndexNoCustomer2() throws CustomerException {
		PRDummy.getCustomerByIndex(50);
	}
	
	// Tests for getTotalDeliveryDistance function
	
	@Test
	public void testGetTotalDeliveryDistance1() throws CustomerException {
		double test = pizzaRestaurant.get(0).getTotalDeliveryDistance();
		assertEquals(12.65, test, 0.000001);
	}
	
	@Test
	public void testGetTotalDeliveryDistance2() throws PizzaException {
		double test = pizzaRestaurant.get(1).getTotalDeliveryDistance();
		assertEquals(27.96, test, 0.000001);
	}
	
	// Tests for resetDetails function
	@Test (expected=CustomerException.class)
	public void testResetDetailsWithGetCustomerByIndex1() throws CustomerException {
		pizzaRestaurant.get(0).resetDetails();
		Customer test = pizzaRestaurant.get(0).getCustomerByIndex(0);
	}
	
	@Test (expected=CustomerException.class)
	public void testResetDetailsWithGetCustomerByIndex2() throws CustomerException {
		pizzaRestaurant.get(1).resetDetails();
		Customer test = pizzaRestaurant.get(1).getCustomerByIndex(0);
	}
	
	@Test (expected=CustomerException.class)
	public void testResetDetailsWithGetCustomerByIndex3() throws CustomerException {
		pizzaRestaurant.get(2).resetDetails();
		Customer test = pizzaRestaurant.get(2).getCustomerByIndex(0);
	}
	
	@Test
	public void testResetDetailsWithGetCustomerOrders1() throws CustomerException {
		pizzaRestaurant.get(0).resetDetails();
		assertEquals(0, pizzaRestaurant.get(0).getNumCustomerOrders());
	}
	
	@Test
	public void testResetDetailsWithGetCustomerOrders2() throws CustomerException {
		pizzaRestaurant.get(1).resetDetails();
		assertEquals(0, pizzaRestaurant.get(1).getNumCustomerOrders());
	}
	
	@Test
	public void testResetDetailsWithGetCustomerOrders3() throws CustomerException {
		pizzaRestaurant.get(2).resetDetails();
		assertEquals(0, pizzaRestaurant.get(2).getNumCustomerOrders());
	}
	
	@Test
	public void testResetDetailsWithGetTotalDistance1() throws CustomerException {
		pizzaRestaurant.get(0).resetDetails();
		double test = pizzaRestaurant.get(0).getTotalDeliveryDistance();
		assertEquals(0, test, 0.000001);
	}
	
	@Test
	public void testResetDetailsWithGetTotalDistance2() throws CustomerException {
		pizzaRestaurant.get(1).resetDetails();
		double test =  pizzaRestaurant.get(1).getTotalDeliveryDistance();
		assertEquals(0, test, 0.000001);
	}
	
	@Test
	public void testResetDetailsWithGetTotalDistance3() throws CustomerException {
		pizzaRestaurant.get(2).resetDetails();
		double test = pizzaRestaurant.get(2).getTotalDeliveryDistance();
		assertEquals(0, test, 0.000001);
	}
	
}
