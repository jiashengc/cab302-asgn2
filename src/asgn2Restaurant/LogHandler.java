package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Ji Su Choi (n9678166) and Jia Sheng Chong (n9901990)
 *
 */
public class LogHandler {
	
	final static String COMMA = ",";

	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = null;
			
			// For every new line, read from the file,
			// create a new customer
			// and push it into the array list
			while ((line=br.readLine()) != null) {
				Customer customer = createCustomer(line);
				customers.add(customer);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new LogHandlerException("File cannot be read.");
		}
		
		return customers;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = null;
			
			// For each new lines, read from the file, create a new pizza
			// and put it into the array list
			while ((line=br.readLine()) != null) {
				Pizza pizza = createPizza(line);
				pizzas.add(pizza);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new LogHandlerException("File cannot be read.");
		}
		
		return pizzas;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
		
		// Split the line into different sections
		String[] currentCustomer = line.split(COMMA);
		
		// Setup variables
		String name;
		String mobileNumber;
		String code;
		int locationX;
		int locationY;
		
		// Set the variables through the currentCustomer array
		try {
			name = currentCustomer[2];
			mobileNumber = currentCustomer[3];
			code = currentCustomer[4];
			locationX = Integer.parseInt(currentCustomer[5]);
			locationY = Integer.parseInt(currentCustomer[6]);
		} catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new LogHandlerException("Setting customer variables failed.");
		}
		
		// Create a customer through the customer factory
		Customer createdCustomer;
		try {
			createdCustomer = CustomerFactory.getCustomer(code, name, mobileNumber, locationX, locationY);
		} catch(CustomerException e) {
			throw e;
		}
		
		return createdCustomer;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		
		// Split the line into different sections
		String[] currentInformation = line.split(COMMA);
				
		// Setup fields
		LocalTime orderTime;
		LocalTime deliveryTime;
	    String pizzaCode;
		int quantity;
				
		// Set the variables through the currentCustomer array
		try {
			orderTime = LocalTime.parse(currentInformation[0]);
			deliveryTime = LocalTime.parse(currentInformation[1]); 
			pizzaCode = currentInformation[7];
			quantity = Integer.parseInt(currentInformation[8]);
			
		} catch(ArrayIndexOutOfBoundsException e) {
				//e.printStackTrace();
				throw new LogHandlerException("Setting pizza fields failed.");
		}
				
		// Create a pizza through the pizza factory
		Pizza newPizza;
		try {
			newPizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
		} catch(PizzaException e) {
			throw e;
		}
				
		return newPizza;
	}

}
