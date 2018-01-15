import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * @author David McDonald
 *
 */
public class Ws4PublicTestsEx3 {
	
	public static final double EPSILON = 1e-6;
	
	private Sortable s1, s2, s3, s4, s5, s6;
	
	//public test
	@Test
	public void test1() {
		
		s1 = new Car(100.0, "VX05 ZFG", "Peugeot");
		
		double expected = 100.0;
		double actual = s1.compareValue();
		
		assertEquals(expected, actual, EPSILON);
	}
	
	//public test
	@Test
	public void test2() {
		
		s2 = new Car(150.0, "FG61 CWF", "BMW");
		
		double expected = 150.0;
		double actual = s2.compareValue();
		
		assertEquals(expected, actual, EPSILON);
	}
	
	//public test
	@Test
	public void test3() {
		
		s4 = new Customer("David", 16.0, "24 Bristol Street");
		
		double expected = 16.0;
		double actual = s4.compareValue();
		
		assertEquals(expected, actual, EPSILON);
	}
	
	//public test
	@Test
	public void test4() {
		
		s1 = new Car(100.0, "VX05 ZFG", "Peugeot");
		s2 = new Car(150.0, "FG61 CWF", "BMW");
		s3 = new Car(78.0, "WF11 YHC", "Vauxhall");
		
		Sortable[] a = {s1, s2, s3};
		
		Sortable[] expected = {s3, s1, s2} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test5() {
		
		s4 = new Customer("David", 100.0, "24 Bristol Street");
		s5 = new Customer("Emily", 150.50, "1 London Street");
		s6 = new Customer("Dory", 60.0, "42 Wallaby Way");
		
		Sortable[] a = {s4, s5, s6};
		
		Sortable[] expected = {s6, s4, s5} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}

	//public test
	@Test
	public void test6() {
		
		s1 = new Car(100.0, "VX05 ZFG", "Peugeot");
		s2 = new Car(150.0, "FG61 CWF", "BMW");
		s3 = new Car(78.0, "WF11 YHC", "Vauxhall");
		s4 = new Customer("David", 100.0, "24 Bristol Street");
		s5 = new Customer("Emily", 150.50, "1 London Street");
		s6 = new Customer("Dory", 60.0, "42 Wallaby Way");
		
		Sortable[] a = {s1, s2, s3, s4, s5, s6};
		
		Sortable[] expected = {s6, s3, s1, s4, s2, s5} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test7() {
		
		s1 = new Car(96.0, "VX05 ZFG", "Peugeot");
		s2 = new Car(220.50, "FG61 CWF", "BMW");
		s3 = new Car(178, "WF11 YHC", "Vauxhall");
		
		Sortable[] a = {s1, s2, s3};
		
		Sortable[] expected = {s1, s3, s2} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test8() {
		
		s4 = new Customer("David", 16.0, "24 Bristol Street");
		s5 = new Customer("Emily", 47.0, "1 London Street");
		s6 = new Customer("Dory", 568.70, "42 Wallaby Way");
		
		Sortable[] a = {s4, s5, s6};
		
		Sortable[] expected = {s4, s5, s6} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test9() {
		
		s1 = new Car(96.0, "VX05 ZFG", "Peugeot");
		s2 = new Car(220.50, "FG61 CWF", "BMW");
		s3 = new Car(178, "WF11 YHC", "Vauxhall");
		s4 = new Customer("David", 16.0, "24 Bristol Street");
		s5 = new Customer("Emily", 47.0, "1 London Street");
		s6 = new Customer("Dory", 568.70, "42 Wallaby Way");
		
		Sortable[] a = {s1, s2, s3, s4, s5, s6};
		
		Sortable[] expected = {s4, s5, s1, s3, s2, s6} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
		
	}


}