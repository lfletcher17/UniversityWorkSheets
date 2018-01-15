import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * @author David Mcdonald
 */
public class Ws3PublicTestsEx3 {

    //public test
    @Test
    public void test1() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(23, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 43));
        ArrayList<Integer> actual = Contains.allIntegersWith(23, 53, 3);
			
        assertEquals(expected, actual);
			
    }
		
    //public test
    @Test
    public void test2() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList());
        ArrayList<Integer> actual = Contains.allIntegersWith(23, 26, 7);
			
        assertEquals(expected, actual);
			
    }
		
    //public test
    @Test
    public void test3() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(7, 17));
        ArrayList<Integer> actual = Contains.allIntegersWith(-5, 27, 7);
			
        assertEquals(expected, actual);
			
    }

    //public test
    @Test
    public void test4() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(-47, -37, -27, -17, -7));
        ArrayList<Integer> actual = Contains.allIntegersWith(-50, -6, 7);
			
        assertEquals(expected, actual);
			
    }
		
    //public test
    @Test
    public void test5() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(100, 101, 102, 103, 104, 105, 106, 107, 108, 109));
        ArrayList<Integer> actual = Contains.allIntegersWith(91, 110, 0);
			
        assertEquals(expected, actual);
			
    }

    //public test
    @Test
    public void test6() {

        ArrayList<Integer> expected = new ArrayList<Integer>
            (Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90));
        ArrayList<Integer> actual = Contains.allIntegersWith(0, 100, 0);
			
        assertEquals(expected, actual);
			
    }
    

	
}
