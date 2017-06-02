package asgn2Tests;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Ji Su Choi (n9678166)
 */
public class LogHandlerCustomerTests {
	// TO DO
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	Customer customer1;
	Customer customer2;
	Customer customer3;
	String correctFile1;
	String correctFile2;
	String correctFile3;
	String incorrectFile1;
	String incorrectFile2;
	String incorrectFile3;
	String incorrectFile4;
	String invalidFile1;
	String invalidFile2;
	String invalidFile3;
	String invalidFile4;
	String text;
	
	@Before
	public void setup(){
		String log = "logs/";
		
		// Initialize correct files.
		correctFile1 = log + "20170101.txt";
		correctFile2 = log + "20170102.txt";
		correctFile3 = log + "20170103.txt";

		// Initialize incorrect files
		incorrectFile1 = log + "incorrect1.txt";
		incorrectFile2 = log + "incorrect2.txt";
		incorrectFile3 = log + "incorrect3.txt";
		incorrectFile4 = log + "incorrect4.txt";
				
		// Initialize invalid files
		invalidFile1 = log + "invalid1.txt";
		invalidFile2 = log + "invalid2.txt";
		invalidFile3 = log + "invalid3.txt";
		invalidFile4 = log + "invalid4.txt";
		
	}
	
	// Test for testPopulateCustomerDataset functions
	@Test
	public void testPopulateCustomerDataset1() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(correctFile1);
	}
	
	@Test
	public void testPopulateCustomerDataset2() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(correctFile2);
	}
	
	@Test
	public void testPopulateCustomerDataset3() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(correctFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithIncorrectFile1()  throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(incorrectFile1);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithIncorrectFile2()  throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(incorrectFile2);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithIncorrectFile3()  throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(incorrectFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithIncorrectFile4()  throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(incorrectFile4);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithInvalid1() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(invalidFile1);
	} 
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithInvalid2() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(invalidFile2);
	} 
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithInvalid3() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(invalidFile3);
	} 
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithInvalid4() throws CustomerException, LogHandlerException{
		customers = LogHandler.populateCustomerDataset(invalidFile4);
	} 
	
	// Tests for testCreateCustomer function.
	@Test
	public void testCreateCustomer1() throws CustomerException, LogHandlerException{
		text = "19:00:00,19:30:00,Rebekah,0123456789,DVC,2,2,PZV,1";
		customer1 = LogHandler.createCustomer(text);
	} 
	
	@Test
	public void testCreateCustome2() throws CustomerException, LogHandlerException{
		text = "19:00:00,19:30:00,Rebekah,0123456789,PUC,0,0,PZM,1";
		customer2 = LogHandler.createCustomer(text);
	} 
	
	@Test
	public void testCreateCustomer3() throws CustomerException, LogHandlerException{
		text = "19:00:00,19:30:00,Rebekah,0123456789,DNC,2,2,PZL,1";
		customer3 = LogHandler.createCustomer(text);
	} 
	
	@Test(expected=CustomerException.class) 
	public void testCreateCustomerWithInvaildDocumentFormatted1() throws CustomerException, LogHandlerException {
		text = "19:00:00,19:30:00,Rebekah,0123456789,NNN,2,2,PZL,1";
		customer3 = LogHandler.createCustomer(text);
	}
	
	@Test(expected=CustomerException.class) 
	public void testCreateCustomerWithInvaildDocumentFormatted2() throws CustomerException, LogHandlerException {
		text = "19:00:00,19:30:00,Rebekah,0123456789,DNC,15,16,PZL,1";
		customer3 = LogHandler.createCustomer(text);
	}
	
	@Test(expected=CustomerException.class) 
	public void testCreateCustomerWithInvaildDocumentFormatted3() throws CustomerException, LogHandlerException {
		text = "19:00:00,19:30:00,Rebekah,0123456789,PUC,1,1,PZL,1";
		customer3 = LogHandler.createCustomer(text);
	}
}
