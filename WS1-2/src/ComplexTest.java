import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * lxf736 tests for Ex3.
 * @author lxf736
 * @version 2017-10-18
 * 
 */

public class ComplexTest {
	
	private Complex testComplex, testComplex2, testComplex3, testComplex4;
	private final static double EPSILON = 1e-6;
	@Before
	public void setup() {
		testComplex = new Complex (1,5);
		testComplex2 = new Complex (3,1);
		testComplex3 = new Complex (4,6);
		testComplex4 = new Complex (-26,26);
	}
	
	@Test
	public void test1 () {
		assertEquals(1, testComplex.getRealPart(), EPSILON);
		assertEquals(5, testComplex.getImaginaryPart(), EPSILON);
	}
	
	@Test
	public void test2() {
		assertEquals("1.0 + 5.0i", testComplex.toString());
		
	}
	
	@Test
	public void test3 () {
		assertEquals(testComplex3.toString(), testComplex.add(testComplex2).toString());
	}
	
	@Test
	public void test4 () {
		assertEquals(testComplex4.toString(), testComplex.multiply(testComplex3).toString());
	}
	
	@Test
	public void test5 () {
		assertEquals("-25.0 + 31.0i", testComplex4.add(testComplex).toString());
	}
	
	@Test
	public void test6 () {
		assertEquals("-104.0 + 52.0i", testComplex4.multiply(testComplex2).toString());
	}
	

}
