/**
 *  A class containing a main method to demonstrate the functionality of classes produced
 *  for WS1-5 Ex1
 *  @author lxf736
 *  @version 2017-11-27
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerBaseMain {
	
	public static void main (String args[]) {
		
		//Initialise variables
		Good good1 = new Good("apples", 2);
		Good good2 = new Good("bannanas", 1);
		Good good3 = new Good("avacados", 4);
		Good good4 = new Good("kiwis", 3);
		
		ArrayList<Good> aliceGoods = new ArrayList<Good>(Arrays.asList(good2, good2, good2, good2));
		ArrayList<Good> steveGoods = new ArrayList<Good>(Arrays.asList(good3, good3));
		ArrayList<Good> jackGoods = new ArrayList<Good>(Arrays.asList(good1, good2, good3, good2));
		ArrayList<Good> dianeGoods = new ArrayList<Good>(Arrays.asList(good2, good2, good3, good4));
		
		CustomerWithGoods alice = new CustomerWithGoods ("Alice", "1 Fake Street", 
				"01234567890", aliceGoods);
		CustomerWithGoods steve = new CustomerWithGoods ("Steve", "2 Fake Street", 
				"01234567891", steveGoods);
		CustomerWithGoods jack = new CustomerWithGoods ("Jack", "3 Fake Street", 
				"01234567892", jackGoods);
		CustomerWithGoods diane = new CustomerWithGoods ("Diane", "4 Fake Street", 
				"01234567893", dianeGoods);
		
		ArrayList<CustomerWithGoods> customers = new ArrayList<CustomerWithGoods>(Arrays.asList(alice, 
				steve, jack));
		
		CustomerBase cb = new CustomerBase (customers);
		
		//Print the names of all Customers in CustomerBase
		System.out.println("All Customers names: ");
		for (CustomerWithGoods el: customers) {
			System.out.println(el.getCustomerName());
		}
		
		//Print all of the Customers and their purchased Goods from CustomerBase
		System.out.println("\nAll Customers and Goods purchased: ");
		for (CustomerWithGoods el: customers) {
			System.out.println(el.toString());
		}
		
		//Add a Customer to CustomerBase, another Customer buys an extra Good
		//then print all of the Customers and their purchased Goods from CustomerBase
		cb.addCustomer(diane);
		System.out.println("\nAdded " + diane.getCustomerName() + " to CustomerBase");
		alice.buy(good2);
		System.out.println(alice.getCustomerName() + " purchased " + good2 
				+ "\n\nAll Customers and Goods purchased is now: ");
		for (CustomerWithGoods el: customers) {
			System.out.println(el.toString());
		}
		
		//Print average Customer loyalty and list of Loyal Customers
		System.out.println("\nAverage Customer loyalty equates to the purchase of " + cb.averageLoyalty() + " Goods");
		System.out.println("Loyal Customers: ");
		for (CustomerWithGoods el: cb.filterLoyal()) {
			System.out.println(el.getCustomerName() + " bought " +
					el.getGoods().size() + " Goods.");
		}
		
		//Print average Customer value and list of valued Customers
		System.out.println("\nAverage amount spent equates to £" + cb.averageValue());
		System.out.println("Valued Customers: ");
		for (CustomerWithGoods el: cb.filterValued()) {
			System.out.println(el.getCustomerName() + " spent £" +
					el.valueOfGoods());
		}
			
	}

}
