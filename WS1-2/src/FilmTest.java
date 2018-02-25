import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * lxf736 tests for Ex1.
 * @author lxf736
 * @version 2017-10-12
 * 
 */

public class FilmTest {
	
	private Film testFilm;
	@Before
	public void setUp() {
		testFilm = new Film("Blade Runner 2049", 2017, 163);
	}
	
	@Test
	public void test1() {
		assertEquals("Film title: Blade Runner 2049, Film year: 2017, Film length: 163", testFilm.toString()) ;
	}
 
	@Test
	public void test2() {
		testFilm.setLength(120);
		
		assertEquals("Blade Runner 2049", testFilm.getTitle());
		assertEquals(2017, testFilm.getYear());
		assertEquals(120, testFilm.getLength());
	}
	
	@Test
	public void test3() {
		testFilm.setLength(10);
		
		assertEquals("Blade Runner 2049", testFilm.getTitle());
		assertEquals(2017, testFilm.getYear());
		assertEquals(10, testFilm.getLength());
	}
	
	@Test
	public void test4() {
		testFilm.setLength(1);
		
		assertEquals("Blade Runner 2049", testFilm.getTitle());
		assertEquals(2017, testFilm.getYear());
		assertEquals(1, testFilm.getLength());
	}
	
	@Test
	public void test5() {
		testFilm.setLength(99);
		
		assertEquals("Blade Runner 2049", testFilm.getTitle());
		assertEquals(2017, testFilm.getYear());
		assertEquals(99, testFilm.getLength());
	}
	
	@Test
	public void test6() {
		testFilm.setLength(2000);
		
		assertEquals("Blade Runner 2049", testFilm.getTitle());
		assertEquals(2017, testFilm.getYear());
		assertEquals(2000, testFilm.getLength());
	}
	
}
