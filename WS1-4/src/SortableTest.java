import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SortableTest {
	
	public static final double EPSILON = 1e-6;
	
	private Sortable s1 = new Car(150, "LC09MBX", "BMW"),
	s2 = new Car(110, "VK58DZS", "VW"),
	s3 = new Car(130, "CY11AEC", "MINI"),
	s4 = new Customer("Alice", 1000, "10 Highlea"),
	s5 = new Customer("Diane", 400, "29 The Oaklands"),
	s6 = new Customer("Dean", 60.0, "42 Mary Vale Road");
	
	@Test
	public void test1() {
		
		double expected = 150.0;
		double actual = s1.compareValue();
		
		assertEquals(expected, actual, EPSILON);
	}
	
	@Test
	public void test2() {
		
		double expected = 1000;
		double actual = s4.compareValue();
		
		assertEquals(expected, actual, EPSILON);
	}
	
	@Test
	public void test3() {
		
		Sortable[] a = {s1, s2, s3};
		
		Sortable[] expected = {s2, s3, s1} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		
		Sortable[] a = {s4, s5, s6};
		
		Sortable[] expected = {s6, s5, s4} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		
		Sortable[] a = {s1, s2, s3, s4, s5, s6};
		
		Sortable[] expected = {s6, s2, s3, s1, s5, s4} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void test6() {
		
		Sortable[] a = {s6, s2, s3, s1, s5, s4};
		
		Sortable[] expected = {s6, s2, s3, s1, s5, s4} ;
		Sortable[] actual = Sorting.quickSort(a);
		
		assertArrayEquals(expected, actual);
	}
	
}


