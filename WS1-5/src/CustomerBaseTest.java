import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class CustomerBaseTest {
	
	private Good good1, good2, good3, good4;
	private ArrayList<Good> customer1Goods, customer2Goods, customer3Goods, customer4Goods;
	private CustomerWithGoods customerWithGoods1, customerWithGoods2, customerWithGoods3, customerWithGoods4;
	private ArrayList<CustomerWithGoods> customers;
	private CustomerBase cb;
	private final static double EPSILON = 1e-6;
	
	@Before
	public void setup() {
		good1 = new Good("Good1", 2);
		good2 = new Good("Good2", 1);
		good3 = new Good("Good3", 4);
		good4 = new Good("Good4", 3);
		customer1Goods = new ArrayList<Good>(Arrays.asList(good2, good2, good2, good2));
		customer2Goods = new ArrayList<Good>(Arrays.asList(good3, good3));;
		customer3Goods = new ArrayList<Good>(Arrays.asList(good1, good2, good3, good2));
		customer4Goods = new ArrayList<Good>(Arrays.asList(good2, good2, good3, good4));
		customerWithGoods1 = new CustomerWithGoods ("Alice", "1 Fake Street", 
				"01234567890", customer1Goods);
		customerWithGoods2 = new CustomerWithGoods ("Steve", "2 Fake Street", 
				"01234567891", customer2Goods);
		customerWithGoods3 = new CustomerWithGoods ("Jack", "3 Fake Street", 
				"01234567892", customer3Goods);
		customerWithGoods4 = new CustomerWithGoods ("Diane", "4 Fake Street", 
				"01234567893", customer4Goods);
		customers = new ArrayList<CustomerWithGoods>(Arrays.asList(customerWithGoods1, 
				customerWithGoods2, customerWithGoods3));
		cb = new CustomerBase (customers);
	}
	
	@Test
	public void test1() {
		
		cb.addCustomer(customerWithGoods4);
		double expected = 3.5;
		
		double actual = cb.averageLoyalty();
		
		assertEquals(expected, actual, EPSILON);
		
	}
	
	@Test
	public void test2() {
		
		cb.addCustomer(customerWithGoods4);
		customerWithGoods1.buy(good2);
		double expected = 3.75;
		
		double actual = cb.averageLoyalty();
		
		assertEquals(expected, actual, EPSILON);
		
	}
	
	@Test
	public void test3() {
		
		cb.addCustomer(customerWithGoods4);
		double expected = 7.25;
		
		double actual = cb.averageValue();
		
		assertEquals(expected, actual, EPSILON);
		
	}
	
	@Test
	public void test4() {
		
		cb.addCustomer(customerWithGoods4);
		customerWithGoods1.buy(good2);
		double expected = 7.5;
		
		double actual = cb.averageValue();
		
		assertEquals(expected, actual, EPSILON);
		
	}
	
	@Test
	public void test5() {
		
		ArrayList<CustomerWithGoods> expected = new ArrayList<CustomerWithGoods>
		(Arrays.asList(customerWithGoods1, customerWithGoods3, customerWithGoods4));
		
		cb.addCustomer(customerWithGoods4);
		ArrayList<CustomerWithGoods> actual = cb.filterLoyal();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test6() {
		
		ArrayList<CustomerWithGoods> expected = new ArrayList<CustomerWithGoods>
		(Arrays.asList(customerWithGoods2, customerWithGoods3, customerWithGoods4));
		
		cb.addCustomer(customerWithGoods4);
		ArrayList<CustomerWithGoods> actual = cb.filterValued();
		
		assertEquals(expected, actual);
		
	}

}
