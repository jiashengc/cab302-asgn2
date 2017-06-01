package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Ji Su Choi (n9678166)
 *
 */
public class CustomerFactoryTests {
	// TO DO
	private Customer testCustomer;
	
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	String type;
	
	@Before
	public void setup() throws CustomerException{
		name = "JiSuChoi";
		mobileNumber = "0416768948";
		locationX = 5;
		locationY = 5;
	}
	
	@Test
	public void createsPickUpCustomer() throws CustomerException{
		type = "PUC";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, 0, 0);
		assertEquals("Pick Up", testCustomer.getCustomerType());
	}
	
	@Test
	public void createsDroneCustomer() throws CustomerException{
		type = "DNC";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
		assertEquals("Drone Delivery", testCustomer.getCustomerType());
	}
	
	@Test
	public void createsDriverCustomer() throws CustomerException{
		type = "DVC";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
		assertEquals("Driver Delivery", testCustomer.getCustomerType());
	}
	
	@Test(expected=CustomerException.class)
	public void invalideTypeWithLetters() throws CustomerException{
		type = "ABC";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
	}
	
	@Test(expected=CustomerException.class)
	public void invalideTypeWithNumbers() throws CustomerException{
		type = "123";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
	}
	
	@Test(expected=CustomerException.class)
	public void invalideTypeWithEmpty() throws CustomerException{
		type = "";
		testCustomer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
	}
	
}
