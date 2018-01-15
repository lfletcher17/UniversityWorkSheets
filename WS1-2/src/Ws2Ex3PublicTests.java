import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**Public tests for Ex3.
 * 
 * @author David McDonald
 * 
 */

public class Ws2Ex3PublicTests {

	private Distance d1, d2;

	@Before
	public void setUp() {
		d1 = new Distance(5.3);
		d2 = new Distance(2.76);
	}

	@Test
	public void test1() {
		double expected = 5.3;
		assertEquals(expected, d1.getKilometres(), 0.00000001);
	}

	@Test
	public void test2() {
		double expected = 1.7149887531534664;
		assertEquals(expected, d2.getMiles(), 0.00000001);
	}

	@Test
	public void test3() {

		double expected = 5300;
		assertEquals(expected, d1.getMetres(), 0.000001);
	}

	@Test
	public void test4() {

		double expected = 3018.380205550101;
		assertEquals(expected, d2.getYards(),  0.000001);
	}

	@Test
	public void test5() {

		double expected = 2760;
		assertEquals(expected, d2.getMetres(), 0.000001);
	}

	@Test
	public void test6() {

		double expected = 5796.164887469397;
		assertEquals(expected, d1.getYards(),  0.000001);
	}

}
