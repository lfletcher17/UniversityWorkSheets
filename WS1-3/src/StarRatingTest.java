import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * lxf736 tests for Ex2.
 * @author lxf736
 * @version 2017-10-31
 * 
 */

public class StarRatingTest {
	
	@Rule public final ExpectedException exception = ExpectedException.none();
	
    @Test
    public void test1() {
		
        double rating = 0.99;
		
        exception.expect(IllegalArgumentException.class);
        StarRating.interpret(rating);
    }
	
    @Test
    public void test2() {
		
        double rating = 1;
		
        String expected = "CRAP";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
	 
    @Test
    public void test3() {
		
        double rating = 3.9;
		
        String expected = "CRAP";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test4() {
		
        double rating = 4;
		
        String expected = "OK";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test5() {
		
        double rating = 4.49;
		
        String expected = "OK";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test6() {
		
        double rating = 4.5;
		
        String expected = "EXCELLENT";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test7() {
		
        double rating = 4.9;
		
        String expected = "EXCELLENT";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test8() {
		
        double rating = 5;
		
        String expected = "[HAS ONLY ONE REVIEW]";
        String actual = StarRating.interpret(rating);
		
        assertEquals(expected, actual);
    }
    
    @Test
    public void test9() {
		
        double rating = 5.00001;
		
        exception.expect(IllegalArgumentException.class);
        StarRating.interpret(rating);
    }
    

}
