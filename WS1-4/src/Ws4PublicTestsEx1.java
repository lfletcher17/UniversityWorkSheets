import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexandros Evangelidis
 */
public class Ws4PublicTestsEx1 {

	private Aircraft aircraft1, aircraft2, aircraft3;
	private Aeroplane aeroplane;
        private HotAirBalloon hotAirBaloon1, hotAirBaloon2;
	private final static double EPSILON = 1e-6;

	@Before
	public void setUp() {
		aircraft1 = new Aircraft(900.0, 1499, 600);
		aircraft2 = new Aeroplane(900.0, 1499, 600, 1300);
		aircraft3 = new HotAirBalloon(300.0, 400, 5, 90);

		aeroplane = new Aeroplane(900.0, 1499, 600, 1300);
		hotAirBaloon1 = new HotAirBalloon(300.0, 400, 5, 90);
		hotAirBaloon2 = new HotAirBalloon(300.0, 400, 0, 90);
	}

	// public test
	@Test
	public void test1() {

		String expected1 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons.";
		String actual1 = aircraft1.toString();

		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons. It has a range of 1300.0 km.";
		String actual2 = aircraft2.toString();

		assertEquals(expected2, actual2);

		String expected3 = "The aircraft has a maximal speed of 300.0 km/h and a maximal weight of 400.0 kg. It can carry 5 persons."
				+ " It has a gas temperature of maximally 90.0\u00B0C.";
		String actual3 = aircraft3.toString();

		assertEquals(expected3, actual3);
	}

	// public test
	@Test
	public void test2() {

		aircraft1.setMaxPersons(1);
		aircraft3.setMaxPersons(aircraft1.getMaxPersons());

		String expected1 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 1 person.";
		String actual1 = aircraft1.toString();

		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons. It has a range of 1300.0 km.";
		String actual2 = aircraft2.toString();

		assertEquals(expected2, actual2);

		String expected3 = "The aircraft has a maximal speed of 300.0 km/h and a maximal weight of 400.0 kg. It can carry 1 person."
				+ " It has a gas temperature of maximally 90.0\u00B0C.";
		String actual3 = aircraft3.toString();

		assertEquals(expected3, actual3);

	}

	// public test
	@Test
	public void test3() {

		aircraft2.setMaxSpeed(1400);
		aircraft3.setMaxSpeed(204.2);

		double expected1 = 1400;
		double actual1 = aircraft2.getMaxSpeed();

		assertEquals(expected1, actual1, EPSILON);

		double expected2 = 204.2;
		double actual2 = aircraft3.getMaxSpeed();

		assertEquals(expected2, actual2, EPSILON);

		aircraft2.setMaxPersons(2);

		String expected3 = "The aircraft has a maximal speed of 1400.0 km/h and a maximal weight of 1499.0 kg. It can carry 2 persons."
				+ " It has a range of 1300.0 km.";
		String actual3 = aircraft2.toString();

		assertEquals(expected3, actual3);

	}

	//public test
	@Test
	public void test4() {
		aeroplane.setRange(300);
		hotAirBaloon1.setGasTemperature(200);

		double expectedRange = 300;
		double actualRange = aeroplane.getRange();

		assertEquals(expectedRange, actualRange, EPSILON);

		double expectedTemp = 200;
		double actualTemp = hotAirBaloon1.getGasTemperature();

		assertEquals(expectedTemp, actualTemp, EPSILON);

		String expected1 = "The aircraft has a maximal speed of 900.0 km/h and a maximal weight of 1499.0 kg."
				+ " It can carry 600 persons. It has a range of 300.0 km.";
		String actual1 = aeroplane.toString();

		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 300.0 km/h and a maximal weight of 400.0 kg. It can carry 5 persons."
				+ " It has a gas temperature of maximally 200.0\u00B0C.";
		String actual2 = hotAirBaloon1.toString();

		assertEquals(expected2, actual2);

                String expected3 = "The aircraft has a maximal speed of 300.0 km/h and a maximal weight of 400.0 kg. It can carry 0 persons."
				+ " It has a gas temperature of maximally 90.0\u00B0C.";
		String actual3 = hotAirBaloon2.toString();

		assertEquals(expected3, actual3);

	}

}