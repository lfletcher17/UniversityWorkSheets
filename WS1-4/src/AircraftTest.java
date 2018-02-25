import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AircraftTest {
	
	private Aircraft aircraft1, aircraft2;
	private Aeroplane aeroplane1, aeroplane2;
	private HotAirBalloon hotAirBalloon1, hotAirBalloon2;
	private final static double EPSILON = 1e-6;
	

	@Before
	public void setUp() {
		aircraft1 = new Aircraft(750, 2000, 500);
		aircraft2 = new Aircraft(1000, 300, 2);
		aeroplane1 = new Aeroplane(750, 2000, 500, 1000);
		aeroplane2 = new Aeroplane(1000, 300, 2, 2000);
		hotAirBalloon1 = new HotAirBalloon(250, 450, 5, 90);
		hotAirBalloon2 = new HotAirBalloon(300, 400, 1, 80);
	}
	
	@Test
	public void test1() {

		String expected1 = "The aircraft has a maximal speed of 750.0 km/h and a maximal weight of 2000.0 kg."
				+ " It can carry 500 persons.";
		String actual1 = aircraft1.toString();
		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 1000.0 km/h and a maximal weight of 300.0 kg."
				+ " It can carry 2 persons.";
		String actual2 = aircraft2.toString();
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void test2() {
		
		double expectedMaxSpeed = 650;
		aircraft1.setMaxSpeed(650);
		assertEquals(expectedMaxSpeed, aircraft1.getMaxSpeed(), EPSILON);
		
		double expectedMaxWeight = 2200;
		aircraft1.setMaxWeight(2200);
		assertEquals(expectedMaxWeight, aircraft1.getMaxWeight(), EPSILON);
		
		int expectedMaxPersons = 600;
		aircraft1.setMaxPersons(600);
		assertEquals(expectedMaxPersons, aircraft1.getMaxPersons());
		
		String expected1 = "The aircraft has a maximal speed of 650.0 km/h and a maximal weight of 2200.0 kg."
				+ " It can carry 600 persons.";
		String actual1 = aircraft1.toString();
		assertEquals(expected1, actual1);
	}
	
	@Test
	public void test3() {

		String expected1 = "The aircraft has a maximal speed of 750.0 km/h and a maximal weight of 2000.0 kg."
				+ " It can carry 500 persons. It has a range of 1000.0 km.";
		String actual1 = aeroplane1.toString();
		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 1000.0 km/h and a maximal weight of 300.0 kg."
				+ " It can carry 2 persons. It has a range of 2000.0 km.";
		String actual2 = aeroplane2.toString();
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void test4() {
		
		double expectedMaxSpeed = 650;
		aeroplane1.setMaxSpeed(650);
		assertEquals(expectedMaxSpeed, aeroplane1.getMaxSpeed(), EPSILON);
		
		double expectedMaxWeight = 2200;
		aeroplane1.setMaxWeight(2200);
		assertEquals(expectedMaxWeight, aeroplane1.getMaxWeight(), EPSILON);
		
		int expectedMaxPersons = 600;
		aeroplane1.setMaxPersons(600);
		assertEquals(expectedMaxPersons, aeroplane1.getMaxPersons());
		
		double expectedRange = 800;
		aeroplane1.setRange(800);
		assertEquals(expectedRange, aeroplane1.getRange(), EPSILON);
		
		String expected1 = "The aircraft has a maximal speed of 650.0 km/h and a maximal weight of 2200.0 kg."
				+ " It can carry 600 persons. It has a range of 800.0 km.";
		String actual1 = aeroplane1.toString();
		assertEquals(expected1, actual1);
	}
	
	@Test
	public void test5() {

		String expected1 = "The aircraft has a maximal speed of 250.0 km/h and a maximal weight of 450.0 kg."
				+ " It can carry 5 persons. It has a gas temperature of maximally 90.0\u00B0C.";
		String actual1 = hotAirBalloon1.toString();
		assertEquals(expected1, actual1);

		String expected2 = "The aircraft has a maximal speed of 300.0 km/h and a maximal weight of 400.0 kg."
				+ " It can carry 1 person. It has a gas temperature of maximally 80.0\u00B0C.";
		String actual2 = hotAirBalloon2.toString();
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void test6() {
		
		double expectedMaxSpeed = 275;
		hotAirBalloon1.setMaxSpeed(275);
		assertEquals(expectedMaxSpeed, hotAirBalloon1.getMaxSpeed(), EPSILON);
		
		double expectedMaxWeight = 425;
		hotAirBalloon1.setMaxWeight(425);
		assertEquals(expectedMaxWeight, hotAirBalloon1.getMaxWeight(), EPSILON);
		
		int expectedMaxPersons = 6;
		hotAirBalloon1.setMaxPersons(6);
		assertEquals(expectedMaxPersons, hotAirBalloon1.getMaxPersons());
		
		double expectedRange = 85;
		hotAirBalloon1.setGasTemperature(85);
		assertEquals(expectedRange, hotAirBalloon1.getGasTemperature(), EPSILON);
		
		String expected1 = "The aircraft has a maximal speed of 275.0 km/h and a maximal weight of 425.0 kg."
				+ " It can carry 6 persons. It has a gas temperature of maximally 85.0\u00B0C.";
		String actual1 = hotAirBalloon1.toString();
		assertEquals(expected1, actual1);
	}

}