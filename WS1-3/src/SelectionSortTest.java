import static org.junit.Assert.*;
import org.junit.Test;

/**
 * lxf736 tests for Ex1.
 * @author lxf736
 * @version 2017-10-31
 * 
 */

public class SelectionSortTest {
	
    @Test 
    public void test1() {
		
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }
    
    @Test 
    public void test2() {
		
        int[] a = {0, -1, -2, -3, -4, -5, -6, -7, -8};
		
		
        int[] expected = {-8, -7, -6, -5, -4, -3, -2, -1, 0};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }
    
    @Test 
    public void test3() {
		
        int[] a = {0, -1, -2, 3, -4, 5, -6, 7, -8, Integer.MIN_VALUE};
		
		
        int[] expected = {Integer.MIN_VALUE, -8, -6, -4, -2, -1, 0, 3, 5, 7};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }
    
    @Test 
    public void test4() {
		
        int[] a = {Integer.MIN_VALUE, Integer.MAX_VALUE};
		
		
        int[] expected = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }
    
    @Test 
    public void test5() {
		
        int[] a = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
		
		
        int[] expected = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }
    
    @Test 
    public void test6() {
		
        int[] a = {15000, 14999, 1000000, 258976, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
		
		
        int[] expected = {Integer.MIN_VALUE, 0, 14999, 15000, 258976, 1000000, Integer.MAX_VALUE};
        int[] actual = SelectionSort.selectionSort(a);
		
        assertArrayEquals(expected, actual);
    }

}
