/**
 *  A class for a Good consisting of the name of the Good and the Price.
 *  Good consists of the following field variables:
 *  name - the name of the Good
 *  price - the price of the Good
 * @author lxf736
 * @version 2017-11-27
 */

public class Good {
	
	private String name;
	private int price;
	
	/** 
     * @param name is the name of the Good as a String
     * @param price is the price of the Good as an int
	 */
	public Good (String name, int price) {
		this.name = name;
		this.price = price;
	}

    /**
     *  Getter for the name of the Good
     *  @return The name of the Good as a String
     */
	public String getName() {
		return name;
	}

    /**
     *  Getter for the price of the Good
     *  @return The price of the Good as an int
     */
	public int getPrice() {
		return price;
	}
	
    /** 
     *  toString defines how to print a Good
     *  @return  the print type of a Good
     */
	public String toString () {
		return name + " for £" + price;
	}

}
