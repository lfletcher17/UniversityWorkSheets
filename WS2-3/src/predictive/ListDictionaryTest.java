package predictive;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ListDictionaryTest {
	
	@Test
	public void test1() {
		
		ListDictionary ld = new ListDictionary("/usr/share/dict/words");

		
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
		
		Set<String> actual1 = ld.signatureToWords("2");
		Set<String> actual2 = ld.signatureToWords("9999643866");
		Set<String> actual3 = ld.signatureToWords("43556");
		Set<String> actual4 = ld.signatureToWords("1000000000000000000000");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		
	}

}

