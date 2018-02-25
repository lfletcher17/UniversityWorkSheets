/**
 * A class StarRating containing a method to interpret star ratings provided as a double.
 * If the double provided is within a valid range (greater than 1 and not greater than 5) 
 * a corresponding String is returned, if however the value is invalid an IllegalArgumentException is thrown. 
 * @author lxf736
 * @version 2017-10-31
 *
 */
public class StarRating {

	/** 
	 *  Method to return a String after processing a rating provided as a double.  
	 *  The rating must be a valid value (greater than 1, not greater than 5), otherwise an 
	 *  IllegalArgumentException is thrown.
     *  @param rating is the rating as a double which is to be interpreted as a String.
     *  @return the rating as an interpreted String.
     */
	public static String interpret (double rating) {
		if (rating >= 1 && rating < 4){
			return "CRAP";
		} else if (rating >= 4 && rating < 4.5) {
			return "OK";
		} else if (rating >= 4.5 && rating < 5) {
			return "EXCELLENT";
		} else if (rating == 5){
			return "[HAS ONLY ONE REVIEW]";
		} else {
			throw new IllegalArgumentException("Please provide a value that is greater than "
					+ "1 and that is not greater than 5");
		}
	}
	
}
