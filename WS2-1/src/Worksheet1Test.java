import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** @author 
 *  lxf736
 * This class contains the test cases for Worksheet1 solutions.
 */

public class Worksheet1Test {
	
	@Rule
	 public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void test1() {
		
		int expected = 1;
		int actual = Worksheet1.power(0, 0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test2() {
		
		int expected = 4;
		int actual = Worksheet1.power(2, 2);
		assertEquals(expected, actual);
		
	}
		
	@Test
	public void test3() {
		
		int expected = 1;
		int actual = Worksheet1.fastPower(0, 0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test4() {
		
		int expected = 4;
		int actual = Worksheet1.fastPower(2, 2);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test5() {
		
		List<Integer> actual = new List <Integer> ();
		
		exception.expect(IllegalArgumentException.class);
		actual = Worksheet1.negateAll(actual);
		
	}
	
	@Test
	public void test6() {
		
		List<Integer> expected = new List <Integer> ();
		expected = new List <Integer> (1, expected);
		expected = new List <Integer> (-2, expected);
		
		List<Integer> actual = new List <Integer> ();
		actual = new List <Integer> (-1, actual);
		actual = new List <Integer> (2, actual);
		actual = Worksheet1.negateAll(actual);
		
		boolean result = List.equals(expected, actual);
		assertEquals(true, result);
		
	}
	
	@Test
	public void test7() {
		
		List actual = new List <Integer> ();
		
		exception.expect(IllegalArgumentException.class);
		int result = Worksheet1.find(1, actual);
		
	}
	
	@Test
	public void test8() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(3, test);
		test = new List<Integer>(6, test);
		
		int expected = 0;
		int actual = Worksheet1.find(6, test);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test9() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(3, test);
		test = new List<Integer>(6, test);
		
		int expected = 1;
		int actual = Worksheet1.find(3, test);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test10() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(3, test);
		test = new List<Integer>(6, test);
		
		int expected = 2;
		int actual = Worksheet1.find(2, test);
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	public void test11() {
		
		List<Integer> test = new List<Integer>();


		boolean actual = Worksheet1.allPositive(test);
		assertEquals(true, actual);	
		
	}
	
	@Test
	public void test12() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(0, test);
		test = new List<Integer>(6, test);

		boolean actual = Worksheet1.allPositive(test);
		assertEquals(true, actual);	
		
	}
	
	@Test
	public void test13() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(-2, test);
		test = new List<Integer>(6, test);

		boolean actual = Worksheet1.allPositive(test);
		assertEquals(false, actual);	
		
	}
	
	@Test
	public void test14() {
		
		List<Integer> expected = new List<Integer>();

		List<Integer> test = new List<Integer>();
		
		List<Integer> actual = Worksheet1.positives(test);

		boolean result = List.equals(expected, actual);
		assertEquals(true, result);
		
	}
	
	@Test
	public void test15() {
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(6, expected);
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(-4, test);
		test = new List<Integer>(0, test);
		test = new List<Integer>(6, test);
		
		List<Integer> actual = Worksheet1.positives(test);

		boolean result = List.equals(expected, actual);
		assertEquals(true, result);
		
	}
	
	@Test
	public void test16() {
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(4, expected);
		expected = new List<Integer>(6, expected);
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(4, test);
		test = new List<Integer>(6, test);
		
		List<Integer> actual = Worksheet1.positives(test);

		boolean result = List.equals(expected, actual);
		assertEquals(true, result);
		
	}
	
	@Test
	public void test17() {
		
		List<Integer> test = new List<Integer>();
		
		boolean actual = Worksheet1.sorted(test);
		assertEquals(true, actual);
		
	}
		
	@Test
	public void test18() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(6, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(1, test);
		
		boolean actual = Worksheet1.sorted(test);
		assertEquals(true, actual);
		
	}
	
	@Test
	public void test19() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(2, test);
		test = new List<Integer>(1, test);
		test = new List<Integer>(6, test);
		
		boolean actual = Worksheet1.sorted(test);
		assertEquals(false, actual);
		
	}
	

	@Test
	public void test20() {
		
		List<Integer> test = new List<Integer>();
		
		List<Integer> test2 = new List<Integer>();
		
		List<Integer> expected = new List<Integer>();
		
		List<Integer>actual = Worksheet1.merge(test, test2);
		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
	@Test
	public void test21() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(6, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(1, test);
		
		List<Integer> test2 = new List<Integer>();
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(6, expected);
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(1, expected);

		List<Integer>actual = Worksheet1.merge(test, test2);
		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
	@Test
	public void test22() {
		
		List<Integer> test = new List<Integer>();
		
		List<Integer> test2 = new List<Integer>();
		test2 = new List<Integer>(6, test2);
		test2 = new List<Integer>(2, test2);
		test2 = new List<Integer>(1, test2);
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(6, expected);
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(1, expected);

		List<Integer>actual = Worksheet1.merge(test, test2);
		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
	@Test
	public void test23() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(7, test);
		test = new List<Integer>(5, test);
		test = new List<Integer>(1, test);
		
		List<Integer> test2 = new List<Integer>();
		test2 = new List<Integer>(6, test2);
		test2 = new List<Integer>(2, test2);
		test2 = new List<Integer>(1, test2);
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(7, expected);
		expected = new List<Integer>(6, expected);
		expected = new List<Integer>(5, expected);
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(1, expected);
		expected = new List<Integer>(1, expected);

		List<Integer>actual = Worksheet1.merge(test, test2);
		
		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
	
	@Test
	public void test24() {
		
		List<Integer> test = new List<Integer>();

		List<Integer> expected = new List<Integer>();

		List<Integer>actual = Worksheet1.removeDuplicates(test);

		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
	@Test
	public void test25() {
		
		List<Integer> test = new List<Integer>();
		test = new List<Integer>(6, test);
		test = new List<Integer>(6, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(2, test);
		test = new List<Integer>(1, test);
		test = new List<Integer>(1, test);
		
		List<Integer> expected = new List<Integer>();
		expected = new List<Integer>(6, expected);
		expected = new List<Integer>(2, expected);
		expected = new List<Integer>(1, expected);
		
		List<Integer>actual = Worksheet1.removeDuplicates(test);
		boolean result = List.equals(expected, actual);
		assertEquals(result, true);
		
	}
	
}
