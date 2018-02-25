import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArrayMethodsTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private final static double EPSILON = 1e-6;

	@Test
	public void test1() {

		double[][] a = { { 0, 0, 0 }, { 0, 0, 0 } };

		double expectedMin = 0;
		double expectedMax = 0;
		double expectedMean = 0;
		double expectedMedium = 0;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}
	
	@Test
	public void test2() {

		double[][] a = { { 0, 0, 0 }, { 0, 0 } };

		double expectedMin = 0;
		double expectedMax = 0;
		double expectedMean = 0;
		double expectedMedium = 0;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}
	
	@Test
	public void test3() {

		double[][] a = { { 1000, 2000, 3000 }, { 4000} };
		System.out.println(a.length);

		double expectedMin = 1000;
		double expectedMax = 4000;
		double expectedMean = 2500;
		double expectedMedium = 2500;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}
	
	@Test
	public void test4() {

		double[][] a = { { -57, 0, 57 }, { 3, 0, -3 } };

		double expectedMin = -57;
		double expectedMax = 57;
		double expectedMean = 0;
		double expectedMedium = 0;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}
	
	@Test
	public void test5() {

		double[][] a = { {} };
		exception.expect(IllegalArgumentException.class);
		ArrayMethods.min(a);
		
		exception.expect(IllegalArgumentException.class);
		ArrayMethods.max(a);
		
		exception.expect(IllegalArgumentException.class);
		ArrayMethods.mean(a);
		
		exception.expect(IllegalArgumentException.class);
		ArrayMethods.median(a);
	}
	
	@Test
	public void test6() {

		double[][] a = { { 1, 100, 1000 }, { 100, 1000, 10, 100000 } };

		double expectedMin = 1;
		double expectedMax = 100000;
		double expectedMean = 14601.57142857143;
		double expectedMedium = 100;

		assertEquals(expectedMin, ArrayMethods.min(a), EPSILON);
		assertEquals(expectedMax, ArrayMethods.max(a), EPSILON);
		assertEquals(expectedMean, ArrayMethods.mean(a), EPSILON);
		assertEquals(expectedMedium, ArrayMethods.median(a), EPSILON);
	}

}
