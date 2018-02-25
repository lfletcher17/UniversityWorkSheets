/**
 * A class for a Customer created from the Customer's name, total money spent and address.
 * Customer implements Sortable, that is, we provide an implementation for the method 
 * compareValue(). This is done  by returning the total money spent by the Customer.
 * Customer consists of the following field variables:
 *  name - the name of the Customer
 *	totalMoneySpent - the total money spent by the Customer
 *	address - the address of the Customer
 * @author lxf736
 * @version 2017-11-15 
 */

public class Customer implements Sortable {

	private String name;
	private double totalMoneySpent;
	private String address;
	
	/** 
	 * @param name is the name of the Customer as a String
	 * @param totalMoneySpent is the total money spent by the customer as a double
	 * @param address is the address of the Customer as a String
	 */
	public Customer(String name, double totalMoneySpent, String address) {
		this.name = name;
		this.totalMoneySpent = totalMoneySpent;
		this.address = address;
	}
	
    /** 
     *  The Customer class implements the Sortable interface, hence we need
     *  this explicit implementation for compareValue().
     *  @return The total money spent by the Customer 
     */
	public double compareValue(){
		return totalMoneySpent;
	}
	
    /** 
     *  toString defines how to print a Customer
     *  @return  the print type of a Customer
     */
	public String toString() {
		return String.format(" Name: %s, Total Money Spent: %d, Address: %s ", 
                name,
                totalMoneySpent,
                address);
	}

}