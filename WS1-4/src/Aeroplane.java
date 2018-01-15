/**
 *  A class for an Aeroplane that extends the class Aircraft 
 *  with the following additional field variable:
 *  range - the range of an Aeroplane in kilometres.
 * @author lxf736
 * @version 2017-11-14
 */

public class Aeroplane extends Aircraft {
	
	private double range;
	
	/** 
	 * @param maxSpeed is the maximum speed in kilometres per hour as a double
	 * @param maxWeight is the maximum weight in kilogrammes as a double
	 * @param maxPersons is the maximum number of people as an int
	 * @param range is the range in kilometres as a double
	 */
	public Aeroplane (double maxSpeed, double maxWeight, int maxPersons, double range) {
		super(maxSpeed, maxWeight, maxPersons);
		this.range = range;
	}

	/** 
     *  @return the range of Aeroplane as double
     */
	public double getRange() {
		return range;
	}

	/**
     *  sets the range of Aeroplane
     *  @param  range for the changed range value
     */
	public void setRange (double range) {
		this.range = range;
	}

    /** 
     *  toString defines how to print an Aeroplane
     *  @return  the print type of an Aeroplane
     */
	public String toString () {
		return super.toString() + " It has a range of " + range +
				" km.";
	}
	
}
