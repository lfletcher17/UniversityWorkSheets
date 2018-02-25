/**
 * A class for a Distance created from a Distance in kilometres.
 * Distance consists of the following field variable:
 * 	kilometres - the Distance in kilometres
 * @author lxf736
 * @version 2017-10-17
 *
 */

public class Distance {
	
	private double kilometres;
	
	/**
	 * @param kilometres is the Distance in kilometres as double
	 */
	public Distance (double km) {
		kilometres = km;
	}

	/** 
     *  @return the Distance in kilometres as double
     */
	public double getKilometres() {
		return kilometres;
	}
	
	/** 
     *  @return the Distance in miles as double
     */
	public double getMiles() {
		return kilometres / 1.60934;
	}
	
	/** 
     *  @return the Distance in metres as double
     */
	public double getMetres() {
		return kilometres / 0.001;
	}
	
	/** 
     *  @return the Distance in yards as double
     */
	public double getYards() {
		return (kilometres / 1.60934) * 1760;
	}

}