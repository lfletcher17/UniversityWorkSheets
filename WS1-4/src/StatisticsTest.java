import static org.junit.Assert.assertEquals;
import java.util.function.Function;
import org.junit.Test;

public class StatisticsTest {
	
	private final static double EPSILON = 1e-6;
	
	@Test
	public void test1() {

		Function<Double, Double> f1 = x -> x / 2;
		double[] a = { 2, 4, 6, 8, 10 };

		double expectedMean = 3;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 1.4142135623731;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	@Test
	public void test2() {

		Function<Double, Double> f1 = x -> x / 2;
		double[] a = { -2, -4, -6, -8, -10 };

		double expectedMean = -3;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 1.4142135623731;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	@Test
	public void test3() {

		Function<Double, Double> f1 = x -> x;
		double[] a = { 2, 4, 6, 8, 10 };

		double expectedMean = 6;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 2.8284271247462;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	@Test
	public void test4() {

		Function<Double, Double> f1 = x -> Math.abs(x);
		double[] a = {1,-1};

		double expectedMean = 1;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	@Test
	public void test5() {

		Function<Double, Double> f1 = x -> x*x*x;
		double[] a = {2, 3};

		double expectedMean = 17.5;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 9.5;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	@Test
	public void test6() {

		double y = 2.5;
		Function<Double, Double> f1 = x -> x*y;
		double[] a = {17, 30};

		double expectedMean = 58.75;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 16.25;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}
	
	

}