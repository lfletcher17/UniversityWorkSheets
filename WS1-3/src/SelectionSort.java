/**
 * A class SelectionSort containing an implementation of selectionSort.
 * selectionSort is a simple sorting algorithm which sorts an array of int 
 * by processing once through the array, always selecting the smallest element 
 * and swapping it with the element currently under consideration.
 * @author lxf736
 * @version 2017-10-31
 *
 */
public class SelectionSort {
	
	/** 
	 *  Method to sort an array by processing once through the array, always selecting 
	 *  the smallest element and swapping it with the element currently under consideration.
     *  @param numbers is the array of int that you would like to sort. 
     *  @return the array numbers sorted from smallest to largest int.
     */
	public static int[] selectionSort (int[] numbers) {
		int temp;
		for (int i=0; i < numbers.length - 1; i++) {
			for (int j = i+1; j < numbers.length; j++)
				if (numbers [j] < numbers [i]) {
					temp = numbers [j];
					numbers [j] = numbers [i];
					numbers [i] = temp;
			}
		}
		return numbers;
	}

}
