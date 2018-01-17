import static org.junit.Assert.*;

import org.junit.Test;

/** @author 
 *  lxf736
 * This class contains the test cases for Worksheet1 solutions.
 */

public class Worksheet1Test {

	@Test
	public void test1() {
		
		int expected = 1000
		int actual = power(10, 3);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void test2() {
		
		String expected = "public Var (String typeOfVar, String nameOfVar) {\n    "
				+ "this.typeOfVar = typeOfVar;\n    this.nameOfVar = nameOfVar;\n}"; 
		expected = expected.replaceAll("(?m)^", "\t");
		
		String actual = varClass.makeConstructor();
		
		assertEquals(expected, actual);
		
	}
}
