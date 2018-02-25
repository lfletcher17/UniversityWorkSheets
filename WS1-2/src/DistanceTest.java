import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * lxf736 tests for Ex3.
 * @author lxf736
 * @version 2017-10-18
 * 
 */

public class DistanceTest {
	
	private Distance testDistance;
	private Distance testDistance2;
	@Before
	public void setup() {
		testDistance = new Distance(10);
		testDistance2 = new Distance(999);
	}
	
	@Test
	public void test1 () {
		assertEquals(10, testDistance.getKilometres(), 0.00000001);
	}
	
	@Test
	public void test2 () {
		assertEquals(10000, testDistance.getMetres(), 0.00000001);
	}
	
	@Test
	public void test3 () {
		assertEquals(6.213727366498068, testDistance.getMiles(), 0.00000001);
	}
	
	@Test
	public void test4 () {
		assertEquals(10936.1601650366, testDistance.getYards(), 0.00000001);
	}
	
	@Test
	public void test5 () {
		assertEquals(999, testDistance2.getKilometres(), 0.00000001);
	}
	
	@Test
	public void test6 () {
		assertEquals(999000, testDistance2.getMetres(), 0.00000001);
		assertEquals(620.751363913157, testDistance2.getMiles(), 0.00000001);
		assertEquals(1092522.4004871564, testDistance2.getYards(), 0.00000001);
	}

}
