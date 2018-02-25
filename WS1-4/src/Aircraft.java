/**
 * A class for an Aircraft created from an Aircraft's max speed, max weight and max persons.
 * Aircraft consists of the following field variables:
 * 	maxSpeed - the maximum speed of the Aircraft in kilometres per hour
 *	maxWeight - the maximum weight an Aircraft can hold in kilogrammes
 *	maxPersons - the maximum number of people an Aircraft can hold
 * @author lxf736
 * @version 2017-11-14
 */

public class Aircraft {
	
	private double maxSpeed;
	private double maxWeight;
	private int maxPersons;
	
	/** 
	 * @param maxSpeed is the maximum speed in kilometres per hour as a double
	 * @param maxWeight is the maximum weight in kilogrammes as a double
	 * @param maxPersons is the maximum number of people as an int
	 */
	public Aircraft (double maxSpeed, double maxWeight, int maxPersons) {
		this.maxSpeed = maxSpeed;
		this.maxWeight = maxWeight;
		this.maxPersons = maxPersons;
	}
	
	/** 
     *  @return the maxSpeed of Aircraft as double
     */
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	/**
     *  sets the maxSpeed of Aircraft
     *  @param  maxSpeed for the changed maxSpeed value
     */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	/** 
     *  @return the maxWeight of Aircraft as double
     */
	public double getMaxWeight() {
		return maxWeight;
	}
	
	/**
     *  sets the maxWeightof Aircraft
     *  @param  maxWeight for the changed maxWeight value
     */
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}
	
	/** 
     *  @return the maxPersons of Aircraft as int
     */
	public int getMaxPersons() {
		return maxPersons;
	}
	
	/**
     *  sets the maxPersons of Aircraft
     *  @param  maxPersons for the changed maxPersons value
     */
	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}
	
    /** 
     *  toString defines how to print an Aircraft
     *  @return  the print type of an Aircraft
     */
	public String toString() {
		String punit = maxPersons == 1 ? " person" : " persons";
		return "The aircraft has a maximal speed of " + maxSpeed + " km/h and a maximal weight of " + 
				maxWeight + " kg. It can carry " + maxPersons  + punit + ".";
	}

}