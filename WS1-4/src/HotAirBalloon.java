/**
 *  A class for a HotAirBalloon that extends the class Aircraft 
 *  with the following additional field variable:
 *  gasTemperature - the gas temperature in Celsius of a HotAirBalloon.
 * @author lxf736
 * @version 2017-11-14
 */

public class HotAirBalloon extends Aircraft {
	
	private double gasTemperature;
	
	/** 
	 * @param maxSpeed is the maximum speed in kilometres per hour as a double
	 * @param maxWeight is the maximum weight in kilogrammes as a double
	 * @param maxPersons is the maximum number of people as an int
	 * @param gasTemperature is the gasTemperature in Celsius as a double
	 */
	public HotAirBalloon (double maxSpeed, double maxWeight, int maxPersons, double gasTemperature) {
		super(maxSpeed, maxWeight, maxPersons);
		this.gasTemperature = gasTemperature;
	}

	/** 
     *  @return the gasTemperature of HotAirBalloon as double
     */
	public double getGasTemperature() {
		return gasTemperature;
	}

	/**
     *  sets the gasTemperature of Aeroplane
     *  @param  gasTemperature for the changed gasTemperature value
     */
	public void setGasTemperature(double gasTemperature) {
		this.gasTemperature = gasTemperature;
	}

    /** 
     *  toString defines how to print a HotAirBallon
     *  @return  the print type of a HotAirBalloon
     */
	public String toString () {
		return super.toString() + " It has a gas temperature of maximally " + gasTemperature +
				"\u00b0C.";
	}
	
}
