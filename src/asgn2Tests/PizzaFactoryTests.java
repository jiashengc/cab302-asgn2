package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Jia Sheng Chong (n9901990)
 * 
 */
public class PizzaFactoryTests {
	
	private Pizza testPizza;
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
	LocalTime time1;
	LocalTime time2;
	LocalTime overTime;
	LocalTime beforeTime;
	
	double percision = 0.0001;
	
	@Before @Test
	public void setup() throws PizzaException {
		time1 = LocalTime.parse("19:00:00", dateTimeFormatter);
		time2 = LocalTime.parse("19:20:00", dateTimeFormatter);
		overTime = LocalTime.parse("23:30:00", dateTimeFormatter);
		beforeTime = LocalTime.parse("18:30:00", dateTimeFormatter);
	}
	
	@Test
	public void getPizzaCreateMargherita() throws PizzaException {
		String code = "PZM";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
		assertEquals("Margherita", testPizza.getPizzaType());
	}
	
	@Test
	public void getPizzaCreateVegetarian() throws PizzaException {
		String code = "PZV";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
		assertEquals("Vegetarian", testPizza.getPizzaType());
	}
	
	@Test
	public void getPizzaCreateMeatLovers() throws PizzaException {
		String code = "PZL";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
		assertEquals("Meat Lovers", testPizza.getPizzaType());
	}
	
	@Test (expected=PizzaException.class)
	public void getPizzaWithInvalidCode() throws PizzaException {
		String code = "PLL";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
	}
	
	@Test (expected=PizzaException.class)
	public void getPizzaWithEmptyString() throws PizzaException {
		String code = "";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
	}
	
	@Test (expected=PizzaException.class)
	public void getPizzaWithNumbers() throws PizzaException {
		String code = "123";
		testPizza = PizzaFactory.getPizza(code, 1, time1, time2);
	}
	
}
