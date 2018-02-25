/**
 * A class for a Film created from a Film title, year and length.
 * Film consists of the following field variables:
 * 	title - the title of the Film
 *	year - the year in which the Film was released
 *	length - the length of the Film in minutes
 * @author lxf736
 * @version 2017-10-05
 *
 */

public class Film {

	private String title;
	private int year;
	private int length;
	
	/** 
	 * @param title is the title as String
	 * @param year is the year as int
	 * @param length is the length as int
	 */
	public Film (String	title, int year, int length) {
		this.title 	= title;
		this.year	= year;
		this.length	= length;
	}

	/** 
     *  @return the title of Film as String
     */
	public String getTitle() {
		return this.title;
	}

	/** 
     *  @return the year of Film as int
     */
	public int getYear() {
		return this.year;
	}

	/** 
     *  @return the Length of Film as int
     */
	public int getLength() {
		return this.length;
	}

	/**
     *  sets the Length of Film
     *  @param  newLength for the changed Length value
     */
	public void setLength (int newLength) {
		this.length = newLength;
	}
	
    /** 
     *  toString defines how to print a Film
     *  @return  the print type of a Film
     */
	public String toString() {
		return "Film title: " + this.title +
		", Film year: " + this.year +
		", Film length: " + this.length;
	}
				
}
