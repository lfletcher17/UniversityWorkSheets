import java.util.ArrayList;
import java.util.Collections;

/**
 * A class ArrayMethods containing methods that return the minimum, maximum, mean and median elements
 * of a 2-dimensional array of doubles respectively.
 * @author lxf736
 * @version 2017-10-31
 *
 */

public class ArrayMethods {
	
	/** 
	 *  Method to return the value of the minimum value element of a 2-dimensional array of doubles.   
     *  @param a is a 2 dimensional array of doubles from which to find the minimum value.
     *  @return the value of the lowest value element of the array 'a' as a double.
     */
	public static double min(double [] [] a) {
		try {
			double minValue = a[0][0];
	        for (int i = 0; i < a.length; i++) {
	            for (int j = 0; j < a[i].length; j++) {
	                if (a[i][j] < minValue) {
	                    minValue = a[i][j];
	                }
	            }
	        }
	        return minValue ;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
    }

	/** 
	 *  Method to return the value of the maximum value element of a 2-dimensional array of doubles.   
     *  @param a is a 2 dimensional array of doubles from which to find the maximum value.
     *  @return the value of the maximum value element of the array 'a' as a double.
     */
	public static double max(double [] [] a) {
		try {
			double maxValue = a[0][0];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j <a[i].length; j++) {
					if (a[i][j] > maxValue) {
						maxValue = a[i][j];
					}
				}
			}
			return maxValue;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/** 
	 *  Method to return the mean value of the elements of a 2-dimensional array of doubles.   
     *  @param a is a 2 dimensional array of doubles from which to find the mean value.
     *  @return the mean value of the elements of array 'a' as a double.
     */
	public static double mean(double [] [] a) {
		try {
			double total = 0;
			int counter = 0;
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					total = total + a[i][j];
					counter++;
					}
				}
			double mean = total / counter;
			return mean;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/** 
	 *  Method to return the median value of the elements of a 2-dimensional array of doubles.   
     *  @param a is a 2 dimensional array of doubles from which to find the median value.
     *  @return the median value of the elements of array 'a' as a double.
     */
	public static double median(double [] [] a) {
		try {
			ArrayList <Double> allElements = new ArrayList <Double>();
			double median;
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++)  {
					allElements.add(a[i][j]);
				}
			}
			Collections.sort(allElements);
			if (allElements.size() % 2 == 0) {
				median = (allElements.get(allElements.size() /2) + allElements.get(allElements.size() /2 -1)) /2;
				return median;
			} else {
				System.out.println(allElements.size());
				median = allElements.get(Math.round(allElements.size() /2));
				return median;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}


}
