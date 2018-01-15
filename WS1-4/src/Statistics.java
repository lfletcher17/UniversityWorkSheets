import java.util.function.Function;

/**
 * A class Statistics containing a method that returns the mean of an array of doubles after 
 * applying a provided function to each element of the array, together with a method that 
 * returns the standard deviation of an array of doubles after applying a provided function
 * to each element of the array.  
 * @author lxf736
 * @version 2017-11-14
 *
 */

public class Statistics {
	
	/** 
	 *  Method to return the mean of an array as a double after function 'f'
	 *  has been applied to each element of the array.  
     *  @param f is the Function to be applied to each element of the array.
     *  @param argumentValues is the array of doubles of which f will be applied to. The mean of this
     *  array is then returned.
     *  @return the mean of the array argumentValues after f has been applied 
     *  to each element as a double.
     */
	public static double mean (Function<Double, Double> f, double[] argumentValues) {
		double total = 0;
		for (int i = 0; i < argumentValues.length; i++) {
			total = total + f.apply(argumentValues[i]);
		}
		return total / argumentValues.length;
	}

	/** 
	 *  Method to return the standard deviation of an array as a double after function 'f'
	 *  has been applied to each element of the array.  
     *  @param f is the Function to be applied to each element of the array.
     *  @param argumentValues is the array of doubles of which f will be applied to. The standard deviation 
     *  of this array is then returned.
     *  @return the standard deviation of the array argumentValues after f has been applied 
     *  to each element as a double.
     */
	public static double standardDeviation (Function<Double, Double> f, double[] argumentValues) {
		double mean = mean(f, argumentValues);
		double temp = 0;
		for (int i = 0; i < argumentValues.length; i++) {
			temp = temp + (f.apply(argumentValues[i]) - mean) * (f.apply(argumentValues[i]) - mean);
		}
		return Math.sqrt(temp/argumentValues.length);
	}

}
