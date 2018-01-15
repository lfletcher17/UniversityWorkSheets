import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * lxf736 tests for Ex3.
 * @author lxf736
 * @version 2017-10-31
 * 
 */

public class ContainsTest {
	
    @Test
    public void test1() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(110,111));
        ArrayList<Integer> actual = Contains.allIntegersWith(110, 112, 1);
			
        assertEquals(expected, actual);
			
    }
    
    @Test
    public void test2() {
    	
    		ArrayList<Integer> expected = new ArrayList<Integer>
    			(Arrays.asList());
    		ArrayList<Integer> actual = Contains.allIntegersWith(0, 8, 9);
    		
    		assertEquals(expected, actual);
    	
    }
    
    @Test
    public void test3() {
    	
    		ArrayList<Integer> expected = new ArrayList<Integer>
    			(Arrays.asList(9,19,29,39,49,59,69,79,89,90,91,92,93,94,95,96,97,98,99));
    		ArrayList<Integer> actual = Contains.allIntegersWith(0, 100, 9);
    		
    		assertEquals(expected, actual);
    	
    }
    
    @Test
    public void test4() {
    	
    		ArrayList<Integer> expected = new ArrayList<Integer>
    			(Arrays.asList(-56,-46,-36,-26,-16,-6));
    		ArrayList<Integer> actual = Contains.allIntegersWith(-56, 6, 6);
    		
    		assertEquals(expected, actual);
    	
    }
    
    @Test
    public void test5() {
    	
    	ArrayList<Integer> expected = new ArrayList<Integer>
			(Arrays.asList(0,10));
    		ArrayList<Integer> actual = Contains.allIntegersWith(0, 11, 0);
    		
    		assertEquals(expected, actual);
    	
    }
    
    @Test
    public void test6() {
    	
    	ArrayList<Integer> expected = new ArrayList<Integer>
			(Arrays.asList(30,31,32,33,34,35,36,37,38));
    		ArrayList<Integer> actual = Contains.allIntegersWith(30, 39, 3);
    		
    		assertEquals(expected, actual);
    	
    }

}
