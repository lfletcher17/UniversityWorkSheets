/**
 *  A class for a CustomerBase which contains all Customers of a company together with the goods 
 *  they ever ordered. CustomerBase consists of the following field variable:
 *  allCustomers - the Customers of a company and all the Goods they have ever ordered.
 *  The method addCustomer() adds a Customer and all the Goods they have ever ordered
 *  to the CustomerBase.
 *  The method filterLoyal() collects from the CustomerBase those Customers who have an 
 *  above average number of orders.
 *  The method filterValued() collects from the CustomerBase all those Customers for whom the 
 *  value of all their orders combined is above average.
 *  The methods averageLoyalty() and averageValue() calculate the average number of orders per Customer
 *  and the average total value of Customers orders per Customer respectively.
 * @author lxf736
 * @version 2017-11-27
 */

import java.util.ArrayList;

public class CustomerBase {
	
	private ArrayList <CustomerWithGoods> allCustomers;
	
	/** 
     * @param customers are all of the CustomerWithGoods of a company as an ArrayList
	 */
	public CustomerBase (ArrayList <CustomerWithGoods> customers) {
		this.allCustomers = customers;
	}
	
	/**
	 * Adds a CustomerWithGoods to the CustomerBases ArrayList of allCustomers
	 * @param customer is the CustomerWithGoods to be added to the ArrayList of the CustomerBases Customers
	 */
	public void addCustomer (CustomerWithGoods customer) {
		this.allCustomers.add(customer);
	}
	
	/**
	 * collects from the CustomerBase those Customers who have an above average number of orders.
	 * @return CustomerWithGoods who have an above average number of orders as an ArrayList
	 */
	public ArrayList <CustomerWithGoods> filterLoyal() {
		double average = this.averageLoyalty();
		ArrayList <CustomerWithGoods> loyal = new ArrayList <CustomerWithGoods>();
		for (CustomerWithGoods el: allCustomers) {
			if (el.getGoods().size() > average) {
				loyal.add(el);
			}
		}
		return loyal;
	}
	
	/**
	 * collects from the CustomerBase all those Customers for whom the value of all their orders combined 
	 * is above average.
	 * @return CustomerWithGoods who have an above average total value of orders as an ArrayList
	 */
	public ArrayList <CustomerWithGoods> filterValued() {
		double average = this.averageValue();
		ArrayList <CustomerWithGoods> valued = new ArrayList <CustomerWithGoods>();
		for (CustomerWithGoods el: allCustomers) {
			if (el.valueOfGoods() > average) {
				valued.add(el);
			}
		}
		return valued;
	}
	
	/**
	 * calculates the average number of orders per Customer
	 * @return the average number of orders per Customer as a double
	 */
	public double averageLoyalty() {
		double totalOrders = 0;
		double average = 0;
		for (CustomerWithGoods el: allCustomers) {
			totalOrders = totalOrders + el.getGoods().size();
		}
		average = totalOrders / allCustomers.size();
		return average;
	}
	
	/**
	 * calculates the average total value of Customers orders per Customer
	 * @return the average total value of Customers orders as a double
	 */
	public double averageValue() {
		double totalValue = 0;
		double average = 0;
		for (CustomerWithGoods el: allCustomers) {
			totalValue = totalValue + el.valueOfGoods();
		}
		average = totalValue / allCustomers.size();
		return average;
	}


}
