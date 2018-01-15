import static org.junit.Assert.assertEquals;
import java.util.function.Function;
import org.junit.Test;

/**
 * @author Alexandros Evangelidis
 *
 */
public class Ws4PublicTestsEx2 {

	private final static double EPSILON = 1e-6;

	// public test
	@Test
	public void test1() {

		Function<Double, Double> f1 = x -> x * x;
		double[] a = { 1, 2, 3, 4, 5 };

		double expectedMean = 11;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 8.6486993;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}

	// public test
	@Test
	public void test2() {

		Function<Double, Double> f1 = x -> x * 0;
		double[] a = { 5, 2 };

		double expectedMean = 0;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}

	// public test
	@Test
	public void test3() {

		Function<Double, Double> f1 = x -> Math.sin(x);
		double[] a = { 4, 2, 16, 2, 3, 5, 1, 2, 3, 5, 32, 1 };

		double expectedMean = 0.19016221551;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0.72464265321;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}

	// public test
	@Test
	public void test4() {

		Function<Double, Double> f1 = x -> Math.cos(x);
		double[] a = { -4, 2, -16, 2, -3, 12, 5, -25, 32, 1 };

		double expectedMean = 0.005965535155;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0.7371118816040;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);

	}

	// public test
	@Test
	public void test5() {

		Function<Double, Double> f1 = x -> Math.cos(x);
		double[] a = { 0, 0, 0, 0, 0 };

		double expectedMean = 1;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);
	}

	// public test
	@Test
	public void test6() {

		Function<Double, Double> f1 = x -> Math.cos(Math.sin(x));
		double[] a = { -1, -1 };

		double expectedMean = 0.6663667453;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 0;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);
	}

	// public test
	@Test
	public void test7() {

		Function<Double, Double> f1 = x -> Math.pow(x, 0.5);
		double[] a = new double[1000000];

		for (int i = 0; i < a.length; i++) {
			a[i] = i % 100;
		}

		double expectedMean = 6.61462947103;
		double actualMean = Statistics.mean(f1, a);
		assertEquals(expectedMean, actualMean, EPSILON);

		double expectedStd = 2.3972227599;
		double actualStd = Statistics.standardDeviation(f1, a);
		assertEquals(expectedStd, actualStd, EPSILON);
	}

}