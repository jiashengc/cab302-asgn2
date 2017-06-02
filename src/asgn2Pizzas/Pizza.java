package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime; 
	private String type; 
	private double price;
	
	private double orderCost;
	private double orderPrice;
	private double costPerPizza;
	private double orderProfit;
	
	private double margheritaToppingsPrice;
	private double vegetarianToppingsPrice;
	private double meatLoversToppingsPrice;
	
	
			
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		// TO DO
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;

		
		// Check if quantity meets the requirement
		// Must be from 1 to 10
		int checkQuantity = quantity;		
		if( checkQuantity <= 0 || checkQuantity > 10 ) throw new PizzaException("The quantity should be eitehr at least 1 or less than equal to 10 ");
		
		// Check if orderTime meets the requirement
		// Must be between 17 : 00 and 23 : 00
		LocalTime checkTime1 = orderTime;
		int hours1 = checkTime1.getHour();
		if(hours1 < 19 || hours1 > 23)  {
			throw new PizzaException("Invalid order time.");
		}
		
		/*
		// Check if deliveryTime meets the requirement
		LocalTime checkTime2 = deliveryTime;
		int hours2 = checkTime2.getHour();
		int minute2 = checkTime2.getMinute();
		int second2 = checkTime2.getSecond();
		if( (hours2 < 0 || hours2 > 23) || (minute2 < 0 || minute2 > 59) || (second2 < 0 || second2 > 59) ) throw new PizzaException("");
		*/
	
		
		// Check if type meets the requirement
		// Must be "Margherita", "Vegetarian", or Meat Lovers"
		String checkPizzaType = type;
		boolean margherita = checkPizzaType.equals("Margherita"); 
		boolean vegetarian = checkPizzaType.equals("Vegetarian"); 
		boolean meatLovers = checkPizzaType.equals("Meat Lovers"); 
		if( !margherita && !vegetarian && !meatLovers) throw new PizzaException("Invaild pizza type. It should be one of the pizza types"
				+ "(Margherita, Vegetarian, or Meat Lovers).");
		
		// Check if price meets the requirement
		double checkPrice = price;
		int margheritaPrice = 8;
		int vegetarianPrice = 10;
		int meatLoversPrice = 12;
		if( (checkPrice != margheritaPrice) && (checkPrice != vegetarianPrice) && checkPrice != meatLoversPrice) throw new PizzaException("Invalid pizza price. It should be"
				+ "one of the prices(margherita 8, vegetarian 10, or meatLovers 12)");
		
						
	}

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		// TO DO
		if(price == 8){
			margheritaToppingsPrice = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost();
			costPerPizza = margheritaToppingsPrice;
		}else if(price == 10){
			vegetarianToppingsPrice = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost() + PizzaTopping.EGGPLANT.getCost( ) + 
				PizzaTopping.MUSHROOM.getCost() + PizzaTopping.CAPSICUM.getCost();
			costPerPizza = vegetarianToppingsPrice;
		}else if(price == 12){
			meatLoversToppingsPrice = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost() + PizzaTopping.BACON.getCost() + PizzaTopping.PEPPERONI.getCost() +
				PizzaTopping.SALAMI.getCost();
			costPerPizza = meatLoversToppingsPrice;
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		calculateCostPerPizza();
		return costPerPizza;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		// TO DO
		return price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		// TO DO
		orderCost = costPerPizza * quantity;
		return orderCost;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		// TO DO
		orderPrice = price * quantity;
		
		return orderPrice;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		// TO DO
		orderProfit = orderPrice - orderCost;
		return orderProfit;
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		boolean cheese = topping.equals(PizzaTopping.CHEESE);
		boolean tomato = topping.equals(PizzaTopping.TOMATO);
		boolean bacon  = topping.equals(PizzaTopping.BACON);
		boolean salami = topping.equals(PizzaTopping.SALAMI);
		boolean pepperoni = topping.equals(PizzaTopping.PEPPERONI);
		boolean capsicum = topping.equals(PizzaTopping.CAPSICUM);
		boolean mushroom = topping.equals(PizzaTopping.MUSHROOM);
		boolean eggplant = topping.equals(PizzaTopping.EGGPLANT);
		
		switch(getPizzaType()) {
			case "Margherita":
				if (tomato || cheese) {
					return true;
				} else {
					return false;
				}
			case "Vegetarian":
					if (tomato || cheese || eggplant || mushroom | capsicum) {
						return true;
					} else {
						return false;
					}
			case "Meat Lovers":
					if (tomato || cheese || bacon || pepperoni || salami) {
						return true;
					} else {
						return false;
					}
			default: 
					return false;
		
		}
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		// TO DO
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		// TO DO
		return type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
