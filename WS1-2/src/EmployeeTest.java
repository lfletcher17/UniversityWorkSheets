import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * lxf736 tests for Ex3.
 * @author lxf736
 * @version 2017-10-18
 * 
 */

public class EmployeeTest {
	
	private Employee testEmployee;
	@Before
	public void setup() {
		testEmployee = new Employee("Alice", 18.68, 150);
	}
	
	@Test
	public void test1 () {
		assertEquals("Alice", testEmployee.getName());
		assertEquals(18.68, testEmployee.getHourlySalary(), 0.00000001);
		assertEquals(150, testEmployee.getNumberOfHours());
	}
	
	@Test
	public void test2 () {
		testEmployee.setName("Diane");
		testEmployee.setHourlySalary(57.70);
		testEmployee.setNumberOfHours(160);
		assertEquals("Diane", testEmployee.getName());
		assertEquals(57.70, testEmployee.getHourlySalary(), 0.00000001);
		assertEquals(160, testEmployee.getNumberOfHours());
	}
	
	@Test
	public void test3 () {
		assertEquals(1961.3999999999999, testEmployee.monthlySalary(30), 0.00000001);
	}
	
	@Test
	public void test4 () {
		testEmployee.increaseSalary(3.5);
		assertEquals(19.3338, testEmployee.getHourlySalary() , 0.00000001);
	}
	
	@Test
	public void test5 () {
		assertEquals(0, testEmployee.monthlySalary(100), 0.00000001);
	}
	
	@Test
	public void test6 () {
		assertEquals(-560.3999999999999, testEmployee.monthlySalary(120), 0.00000001);
	}
	

}
