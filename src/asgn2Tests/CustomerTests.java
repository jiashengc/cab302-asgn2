package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;



/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	// TO DO
	Customer driverCustomer;
	Customer droneCustomer;
	Customer pickupCustomer;
	
	String name1;
	String name2;
	String name3;
	
	String mobileNumber1;
	String mobileNumber2;
	String mobileNumber3;
	
	int locationX1;
	int locationY1;
	int locationX2;
	int locationY2;
	int locationX3;
	int locationY3;
		
	@Before
	public void setup() throws CustomerException{
		name1 = "JiSuChoi";
		name2 = "Rebekah";
		name3 = "NaraeChoi";
		
		mobileNumber1 = "0101234567";
		mobileNumber2 = "0102345678";
		mobileNumber3 = "0103456789";
		
		locationX1 = 5;
		locationY1 = 6;
		locationX2 = 4; 
		locationY2 = 3;
		locationX3 = 0;
		locationY3 = 0;
		
		driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3, locationY3);
	}
	
	
	@Test
	public void name() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer("JiSuChoi", mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer("NaRaeChoi", mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer("SeulGiChoi", mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class)
	public void emptyName() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer("", mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer("", mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer("", mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test (expected=CustomerException.class)
	public void shortName() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer("c", mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer("ff", mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer("ccc", mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test(expected=CustomerException.class)
	public void tooLongName() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer("aaaaaaaaaabbbbbbbbbbc", mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer("bbbbbbbbbbccccccccccd", mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer("ccccccccccdddddddddde", mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class)
	public void whitespaceWithName() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer("Ju Ch J", mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(" H H", mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer("J J C ", mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void mobileDigits() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, "0101234567", locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, "0000111122", locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, "0001112223", locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class)
	public void notStartWithZero() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, "1101234567", locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, "2000111122", locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, "3001112223", locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class)
	public void overTenDigints() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, "0101234567123", locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, "0000111122123", locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, "0001112223121", locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class)
	public void lessTenDigints() throws CustomerException{
		try{
			try{
			driverCustomer = new DriverDeliveryCustomer(name1, "01123", locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, "011123", locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, "0113121", locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}	driverCustomer = new DriverDeliveryCustomer(name1, "01123", locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, "011123", locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, "0113121", locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test 
	public void normalLocation() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, 5, 5);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, 2, 2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, 0, 0);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void pickUpCustomerSetsToDifferentLocations() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, 3, 3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void locationXIsTooFarPositiveNumber() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, 11, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void locationXIsTooFarNegativeNumber() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX2, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, -14, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void locationYIsTooFarPositiveNumber() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, 14);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3, locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void locationYIsTooFarNegativeNumber() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, -14);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected=CustomerException.class) 
	public void locationXAndYAreTooFar() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, 15, 15);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, -15, -15);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void getCustomerNames() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertEquals("JiSuChoi", driverCustomer.getName());
		assertEquals("Rebekah", droneCustomer.getName());
		assertEquals("NaraeChoi", pickupCustomer.getName());	
	}
	
	@Test
	public void getCustomerMobileNumbers() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertEquals("0101234567", driverCustomer.getMobileNumber());
		assertEquals("0102345678", droneCustomer.getMobileNumber());
		assertEquals("0103456789", pickupCustomer.getMobileNumber());	
	}
	
	@Test
	public void getCustomerTypes() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertEquals("Driver Delivery", driverCustomer.getCustomerType());
		assertEquals("Drone Delivery", droneCustomer.getCustomerType());
		assertEquals("Pick Up", pickupCustomer.getCustomerType());	
	}
	
	@Test
	public void getLocationX() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertEquals(5, driverCustomer.getLocationX());
		assertEquals(4, droneCustomer.getLocationX());
		assertEquals(0, pickupCustomer.getLocationX());	
	}
	
	@Test
	public void getLocationY() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		assertEquals(6, driverCustomer.getLocationY());
		assertEquals(3, droneCustomer.getLocationY());
		assertEquals(0, pickupCustomer.getLocationY());	
	}
	
	@Test
	public void getDeliveryDistance() throws CustomerException{
		try{
			driverCustomer = new DriverDeliveryCustomer(name1, mobileNumber1, locationX1, locationY1);
			droneCustomer = new DroneDeliveryCustomer(name2, mobileNumber2, locationX2, locationY2);
			pickupCustomer = new PickUpCustomer(name3, mobileNumber3, locationX3,locationY3);
		}catch(CustomerException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		double driver = driverCustomer.getDeliveryDistance();
		double drone = droneCustomer.getDeliveryDistance();
		double pickup = pickupCustomer.getDeliveryDistance();
					
	}
	
}
