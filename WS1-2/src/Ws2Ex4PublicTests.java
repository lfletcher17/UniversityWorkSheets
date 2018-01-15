import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Public tests for Ex4.
 *
 * @author Alexandros Evangelidis
 * @version Changed version of 2017-10-12
 */

public class Ws2Ex4PublicTests {

	private Employee emp1;
	private final static double EPSILON = 1e-6;


	@Before
	public void setUp() {
		emp1 = new Employee("John", 10, 40);
	}

	@Test
	public void test1() {

		emp1.setName("Jim");
		emp1.setHourlySalary(30.4);
		emp1.setNumberOfHours(29);

		assertEquals("Jim", emp1.getName());
		assertEquals(30.4, emp1.getHourlySalary(), EPSILON);
		assertEquals(29, emp1.getNumberOfHours());
	}

	@Test
	public void test2() {

		emp1.setHourlySalary(40.4);
		emp1.setNumberOfHours(29);

		assertEquals(937.28, emp1.monthlySalary(20), EPSILON);

		String expected = "John has an hourly salary of 40.4 \u00a3 and has worked last month for 29 hours.";

		assertEquals(expected, emp1.toString());
	}

	@Test
	public void test3() {

		emp1.setHourlySalary(30.5);
		emp1.setNumberOfHours(52);

		String expected = "John has an hourly salary of 30.5 \u00a3 and has worked last month for 52 hours.";

		assertEquals(expected, emp1.toString());

		assertEquals(1427.4, emp1.monthlySalary(10), EPSILON);

		emp1.increaseSalary(5.4);

		assertEquals(32.147, emp1.getHourlySalary(), EPSILON);
	}

	@Test
	public void test4() {

		emp1.setHourlySalary(30.5);
		emp1.setNumberOfHours(52);

		String expected = "John has an hourly salary of 30.5 \u00a3 and has worked last month for 52 hours.";
		assertEquals(expected, emp1.toString());

		emp1.increaseSalary(5.4);
		emp1.increaseSalary(10.5);
		
		assertEquals(1847.166619, emp1.monthlySalary(0), EPSILON);

                assertEquals(35.522434999999994, emp1.getHourlySalary(), EPSILON);
	}

	@Test
	public void test5() {

		emp1.setHourlySalary(0);
		emp1.setNumberOfHours(0);

		emp1.increaseSalary(0);
		emp1.increaseSalary(0);
		assertEquals(0.0, emp1.monthlySalary(0), EPSILON);

		String expected = "John has an hourly salary of 0.0 \u00a3 and has worked last month for 0 hours.";

		assertEquals(expected, emp1.toString());

	}

}
