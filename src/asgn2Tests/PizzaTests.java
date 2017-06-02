package asgn2Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Jia Sheng Chong
 *
 */
public class PizzaTests {
	
	Pizza margPizza;
	Pizza meatPizza;
	Pizza vegaPizza;
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
	LocalTime time1;
	LocalTime time2;
	LocalTime time3;
	LocalTime time4;
	LocalTime time4b;
	LocalTime overTime;
	LocalTime beforeTime;
	
	double percision = 0.0001;
	
	@Before
	public void setup() throws PizzaException {
		time1 = LocalTime.parse("19:00:00", dateTimeFormatter);
		time2 = LocalTime.parse("19:20:00", dateTimeFormatter);
		time3 = LocalTime.parse("19:40:00", dateTimeFormatter);
		time4 = LocalTime.parse("20:00:00", dateTimeFormatter);
		time4b = LocalTime.parse("20:01:00", dateTimeFormatter);
		overTime = LocalTime.parse("23:30:00", dateTimeFormatter);
		beforeTime = LocalTime.parse("18:30:00", dateTimeFormatter);
		
		margPizza = new MargheritaPizza(1, time1, time2);
		meatPizza = new MeatLoversPizza(1, time2, time3);
		vegaPizza = new VegetarianPizza(1, time3, time4);	
			
	}
	
	/*
	 * This section is to test for quantity
	 */
	
	@Test
	public void pizzaNormalQuantity() throws PizzaException {
		margPizza = new MargheritaPizza(1, time1, time2);
		meatPizza = new MeatLoversPizza(2, time2, time3);
		vegaPizza = new VegetarianPizza(5, time3, time4);
	}
	
	@Test(expected=PizzaException.class)
	public void pizzaZeroQuantity() throws PizzaException {
		margPizza = new MargheritaPizza(0, time1, time2);
		meatPizza = new MeatLoversPizza(0, time2, time3);
		vegaPizza = new VegetarianPizza(0, time3, time4);
	}
	
	@Test (expected=PizzaException.class)
	public void pizzaTooManyQuantity() throws PizzaException {
		margPizza = new MargheritaPizza(11, time1, time2);
		meatPizza = new MeatLoversPizza(12, time2, time3);
		meatPizza = new MeatLoversPizza(50, time3, time4);
	}
	
	@Test (expected=PizzaException.class)
	public void pizzaNegativeQuantity() throws PizzaException {
		margPizza = new MargheritaPizza(-1, time1, time2);
		meatPizza = new MeatLoversPizza(-5, time2, time3);
		meatPizza = new MeatLoversPizza(-10, time3, time4);
	}
	
	/*
	 * This section is to test for orderTime
	 *  and deliveryTime
	 */
	
	@Test (expected=PizzaException.class)
	public void pizzaBeforeOrderTime() throws PizzaException {
		margPizza = new MargheritaPizza(1, beforeTime, time2);
		meatPizza = new MeatLoversPizza(1, beforeTime, time3);
		vegaPizza = new VegetarianPizza(1, beforeTime, time4);
	}
	
	@Test (expected=PizzaException.class)
	public void pizzaAfterOrderTime() throws PizzaException {
		margPizza = new MargheritaPizza(1, overTime, time2);
		meatPizza = new MeatLoversPizza(1, overTime, time3);
		vegaPizza = new VegetarianPizza(1, overTime, time4);

	}
	
	@Test (expected=PizzaException.class)
	public void pizzaDeliveryTimeNotOverTenMinutes() throws PizzaException {
		margPizza = new MargheritaPizza(1, time4, time4b);
		meatPizza = new MeatLoversPizza(1, time4, time4b);
		vegaPizza = new VegetarianPizza(1, time4, time4b);
	}
	
	@Test (expected=PizzaException.class)
	public void pizzaDeliveryTimeBeforeOpeningTime() throws PizzaException {
		margPizza = new MargheritaPizza(1, time2, beforeTime);
		meatPizza = new MeatLoversPizza(1, time3, beforeTime);
		vegaPizza = new VegetarianPizza(1, time4, beforeTime);

	}
	
	@Test (expected=PizzaException.class)
	public void pizzaDeveliveryTimeBeforeOrderTime() throws PizzaException {
		margPizza = new MargheritaPizza(1, time2, time1);
		meatPizza = new MeatLoversPizza(1, time3, time2);
		vegaPizza = new VegetarianPizza(1, time4, time3);
	}
	
	@Test (expected=PizzaException.class)
	public void pizzaDeliveryTimeAfterAnHour() throws PizzaException {
		margPizza = new MargheritaPizza(1, time1, time4);
		meatPizza = new MeatLoversPizza(1, time1, time4);
		vegaPizza = new VegetarianPizza(1, time1, time4);
	}
	/*
	 * This section to check getCostPerPizza method
	 */
	
	@Test
	public void pizzaGetCostPerPizza() throws PizzaException {
		assertEquals(1.5, margPizza.getCostPerPizza(), percision);
		assertEquals(5.5, vegaPizza.getCostPerPizza(), percision);
		assertEquals(5, meatPizza.getCostPerPizza(), percision);
	}
	
	/*
	 * This section is to check getPricePerPizza method
	 */
	
	@Test
	public void pizzaGetPricePerPizza() throws PizzaException {
		assertEquals(8, margPizza.getPricePerPizza(), percision);
		assertEquals(10, vegaPizza.getPricePerPizza(), percision);
		assertEquals(12, meatPizza.getPricePerPizza(), percision);
	}
	
	/*
	 * This section is to check getOrderCost method
	 */
	
	@Test
	public void pizzaGetOrderCost() throws PizzaException {		
		assertEquals(1.5, margPizza.getCostPerPizza(), percision);
		assertEquals(5.5, vegaPizza.getCostPerPizza(), percision);
		assertEquals(5, meatPizza.getCostPerPizza(), percision);
	}
	
	@Test
	public void pizzaGetOrderCostTwo() throws PizzaException {
		margPizza = new MargheritaPizza(2, time1, time2);
		meatPizza = new MeatLoversPizza(2, time2, time3);
		vegaPizza = new VegetarianPizza(2, time3, time4);
	
		assertEquals(3.0, margPizza.getOrderCost(), percision);
		assertEquals(11.0, vegaPizza.getOrderCost(), percision);
		assertEquals(10, meatPizza.getOrderCost(), percision);
	}
	
	@Test
	public void pizzaGetOrderCostMax() throws PizzaException {
		margPizza = new MargheritaPizza(10, time1, time2);
		meatPizza = new MeatLoversPizza(10, time2, time3);
		vegaPizza = new VegetarianPizza(10, time3, time4);
		
		assertEquals(15, margPizza.getOrderCost(), percision);
		assertEquals(55, vegaPizza.getOrderCost(), percision);
		assertEquals(50, meatPizza.getOrderCost(), percision);
	}
	
	/*
	 * This section is for getOrderPrice method
	 */
	
	@Test
	public void pizzaGetOrderPrice() throws PizzaException {
		assertEquals(8, margPizza.getOrderPrice(), percision);
		assertEquals(10, vegaPizza.getOrderPrice(), percision);
		assertEquals(12, meatPizza.getOrderPrice(), percision);
	}
	
	@Test
	public void pizzaGetOrderPriceTwo() throws PizzaException {
		margPizza = new MargheritaPizza(2, time1, time2);
		meatPizza = new MeatLoversPizza(2, time2, time3);
		vegaPizza = new VegetarianPizza(2, time3, time4);
		
		assertEquals(16, margPizza.getOrderPrice(), percision);
		assertEquals(20, vegaPizza.getOrderPrice(), percision);
		assertEquals(24, meatPizza.getOrderPrice(), percision);
	}
	
	@Test
	public void pizzaGetOrderPriceMax() throws PizzaException {
		margPizza = new MargheritaPizza(10, time1, time2);
		meatPizza = new MeatLoversPizza(10, time2, time3);
		vegaPizza = new VegetarianPizza(10, time3, time4);

		assertEquals(80, margPizza.getOrderPrice(), percision);
		assertEquals(100, vegaPizza.getOrderPrice(), percision);
		assertEquals(120, meatPizza.getOrderPrice(), percision);
	}
	
	/*
	 * This is for the getOrderProfit method
	 */
	
	@Test
	public void pizzaGetOrderProfit() throws PizzaException {
		assertEquals(6.5, margPizza.getOrderProfit(), percision);
		assertEquals(4.5, vegaPizza.getOrderProfit(), percision);
		assertEquals(7, meatPizza.getOrderProfit(), percision);
	}
	
	@Test
	public void pizzaGetOrderProfitTwo() throws PizzaException {
		margPizza = new MargheritaPizza(2, time1, time2);
		meatPizza = new MeatLoversPizza(2, time2, time3);
		vegaPizza = new VegetarianPizza(2, time3, time4);
		
		assertEquals(13, margPizza.getOrderProfit(), percision);
		assertEquals(9, vegaPizza.getOrderProfit(), percision);
		assertEquals(14, meatPizza.getOrderProfit(), percision);
	}
	
	@Test
	public void pizzaGetOrderProfitMax() throws PizzaException {
		margPizza = new MargheritaPizza(10, time1, time2);
		meatPizza = new MeatLoversPizza(10, time2, time3);
		vegaPizza = new VegetarianPizza(10, time3, time4);
		assertEquals(65, margPizza.getOrderProfit(), percision);
		assertEquals(45, vegaPizza.getOrderProfit(), percision);
		assertEquals(70, meatPizza.getOrderProfit(), percision);
	}
	
	/*
	 * This section is for containsTopping method
	 */
	
	@Test
	public void pizzaContainsToppingTomato() throws PizzaException {
		PizzaTopping topping = PizzaTopping.TOMATO;
		assertEquals(true, margPizza.containsTopping(topping));
		assertEquals(true, vegaPizza.containsTopping(topping));
		assertEquals(true, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingCheese() throws PizzaException {
		PizzaTopping topping = PizzaTopping.CHEESE;
		assertEquals(true, margPizza.containsTopping(topping));
		assertEquals(true, vegaPizza.containsTopping(topping));
		assertEquals(true, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingBacon() throws PizzaException {
		PizzaTopping topping = PizzaTopping.BACON;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(false, vegaPizza.containsTopping(topping));
		assertEquals(true, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingSalami() throws PizzaException {
		PizzaTopping topping = PizzaTopping.SALAMI;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(false, vegaPizza.containsTopping(topping));
		assertEquals(true, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingPepperoni() throws PizzaException {
		PizzaTopping topping = PizzaTopping.PEPPERONI;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(false, vegaPizza.containsTopping(topping));
		assertEquals(true, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingCapsicum() throws PizzaException {
		PizzaTopping topping = PizzaTopping.CAPSICUM;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(true, vegaPizza.containsTopping(topping));
		assertEquals(false, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingMushroom() throws PizzaException {
		PizzaTopping topping = PizzaTopping.MUSHROOM;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(true, vegaPizza.containsTopping(topping));
		assertEquals(false, meatPizza.containsTopping(topping));
	}
	
	@Test
	public void pizzaContainsToppingEggplant() throws PizzaException {
		PizzaTopping topping = PizzaTopping.EGGPLANT;
		assertEquals(false, margPizza.containsTopping(topping));
		assertEquals(true, vegaPizza.containsTopping(topping));
		assertEquals(false, meatPizza.containsTopping(topping));
	}
	
	/*
	 * This section is for getQuantity method
	 */
	
	@Test
	public void pizzaGetQuantity() throws PizzaException {
		assertEquals(1, margPizza.getQuantity());
		assertEquals(1, vegaPizza.getQuantity());
		assertEquals(1, meatPizza.getQuantity());
	}
	
	@Test
	public void pizzaGetQuantityTwo() throws PizzaException {
		margPizza = new MargheritaPizza(2, time1, time2);
		meatPizza = new MeatLoversPizza(2, time2, time3);
		vegaPizza = new VegetarianPizza(2, time3, time4);
		assertEquals(2, margPizza.getQuantity());
		assertEquals(2, vegaPizza.getQuantity());
		assertEquals(2, meatPizza.getQuantity());
	}
	
	@Test
	public void pizzaGetQuantityMax() throws PizzaException {
		margPizza = new MargheritaPizza(10, time1, time2);
		meatPizza = new MeatLoversPizza(10, time2, time3);
		vegaPizza = new VegetarianPizza(10, time3, time4);

		assertEquals(10, margPizza.getQuantity());
		assertEquals(10, vegaPizza.getQuantity());
		assertEquals(10, meatPizza.getQuantity());
	}
	
	/*
	 * This section is for getPizzaType method
	 */
	
	@Test
	public void pizzaGetPizzaType() throws PizzaException {
		assertEquals("Margherita", margPizza.getPizzaType());
		assertEquals("Vegetarian", vegaPizza.getPizzaType());
		assertEquals("Meat Lovers", meatPizza.getPizzaType());
	}
}
