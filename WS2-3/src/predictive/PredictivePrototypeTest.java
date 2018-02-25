package predictive;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PredictivePrototypeTest {
	
	@Test
	public void test1() {
		
		String expected1 = "2";
		String expected2 = "9999643866";
		String expected3 = "43556";
		
		String actual1 = PredictivePrototype.wordToSignature("a");
		String actual2 = PredictivePrototype.wordToSignature("zyzzogeton");
		String actual3 = PredictivePrototype.wordToSignature("hello");
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		
	}
	
	@Test
	public void test2() {
		
		Set<String> expected1 = new HashSet<String>();
		expected1.add("a");
		expected1.add("b");
		expected1.add("c");
		
		Set<String> expected2 = new HashSet<String>();
		expected2.add("zyzzogeton");
		
		Set<String> expected3 = new HashSet<String>();
		expected3.add("gekko");
		expected3.add("hello");
		
		Set<String> expected4 = new HashSet<String>();
		
		Set<String> actual1 = PredictivePrototype.signatureToWords("2");
		Set<String> actual2 = PredictivePrototype.signatureToWords("9999643866");
		Set<String> actual3 = PredictivePrototype.signatureToWords("43556");
		Set<String> actual4 = PredictivePrototype.signatureToWords("1000000000000000000000");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		
	}
	
	@Test
	public void test3() {
		
		String expected1 = " ";
		String expected2 = " 43556 ";
		
		String actual1 = PredictivePrototype.wordToSignature(" ");
		String actual2 = PredictivePrototype.wordToSignature("!hello!");
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		
	}
	
}
