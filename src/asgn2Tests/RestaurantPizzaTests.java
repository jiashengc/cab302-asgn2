package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	
	PizzaRestaurant PR;
	PizzaRestaurant PR2;
	ArrayList<Pizza> pizzas;
	
	@Before
	public void setup() throws CustomerException, PizzaException, LogHandlerException {
		PR = new PizzaRestaurant();
		PR2 = new PizzaRestaurant();
		PR.processLog("logs/20170101.txt");
	}
	
	@Test
	public void testNumPizzaOrders() {
		System.out.println(PR.getNumPizzaOrders());
		assertEquals(3, PR.getNumPizzaOrders());
	}
	
	@Test
	public void testNumPizzaOrdersNegative() throws PizzaException {
		if (PR.getNumCustomerOrders() < 0) {
			throw new PizzaException("There cannot be negative pizzas!");
		}
	}
}
