import java.util.ArrayList;

/**
 * A class Contains containing a method that returns an ArrayList of all those integers
 * in a range between and inclusive of a 'from' integer, and up to and exclusive of a 'to' integer 
 * that contain a specified digit.  
 * @author lxf736
 * @version 2017-10-31
 *
 */

public class Contains {
	
	/** 
	 *  Method to return an ArrayList of all those integers in a range between and inclusive 
	 *  of a 'from' integer, and up to and exclusive of a 'to' integer that contain a specified 
	 *  'containedDigit'.  
     *  @param from is the integer 'from' which the range will begin (inclusive of).
     *  @param to is the integer 'to' where the range will end (exclusive of).
     *  @param containedDigit is the digit that will be checked for in the designated range as an integer.
     *  @return an ArrayList of all integers containing the specified digit within the given range.
     */
	public static ArrayList <Integer> allIntegersWith (int from, int to, int containedDigit) {
		ArrayList <Integer> result = new ArrayList <Integer>();
		int j;
		for (int i = from; i < to; i++) {
			j = Math.abs(i);
			if (j % 10 == containedDigit) {
				result.add(i);
			}
			else {
				j = j / 10;
				while (j > 0) {
					if (j % 10 == containedDigit) {
						result.add(i);
						break;
					}
					j = j / 10;
				}
			}	
		}
		return result;
	}

}
