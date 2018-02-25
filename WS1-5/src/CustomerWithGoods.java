/**
 *  A class for a CustomerWithGoods that extends the class Customer
 *  with the following additional field variable:
 *  goods - the Goods purchased by a Customer.
 *  The method buy() takes a provided Good and adds it the the Customers ArrayList of Goods.
 *  The method valueOfGoods() returns the total value of all Goods the Customer has ever bought.
 * @author lxf736
 * @version 2017-11-27
 */

import java.util.ArrayList;

public class CustomerWithGoods extends Customer {
	
	private ArrayList<Good> goods;
	
	/** 
     * @param customerName The name of the Customer as a String.
     * @param address The postal address of the Customer as a String.
     * @param telephoneNumber A telephone number of the Customer as a String.
	 * @param goods are the Goods purchased by the Customer as an ArrayList of Goods
	 */
	public CustomerWithGoods (String customerName, String address,
                    String telephoneNumber, ArrayList<Good> goods) {
		super(customerName, address, telephoneNumber);
		this.goods = goods;
	}

    /**
     *  Getter for the Goods purchased by a Customer
     *  @return The Goods purchased by a Customer as an ArrayList
     */
	public ArrayList<Good> getGoods() {
		return goods;
	}
	
	/**
	 * Adds a Good to the Customers ArrayList of purchased Goods
	 * @param good is the Good to be added to the ArrayList of the Customers Goods
	 */
	public void buy (Good good) {
		goods.add(good);
	}
	
	/**
	 * Computes the total value of all Goods the Customer has ever bought
	 * @return the total value of all Goods purchased by the Customer as an int
	 */
	public int valueOfGoods() {
		int value = 0;
		for (int i = 0; i < goods.size(); i++) {
			value = value + goods.get(i).getPrice();
		}
		return value;
	}
	
    /** 
     *  toString defines how to print a CustomerWithGoods
     *  @return  the print type of a CustomerWithGoods
     */
	public String toString() {
		return this.getCustomerName() + " has made the following purchases: " + goods.toString();
	}

}
