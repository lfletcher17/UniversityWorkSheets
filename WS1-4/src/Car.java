/**
 * A class for a Car created from the Car's max speed, car number and make.
 * Car implements Sortable, that is, we provide an implementation for the method 
 * compareValue(). This is done  by returning the maximum speed of the Car.
 * Car consists of the following field variables:
 *  maxSpeed - the maximum speed of the Car in kilometres per hour
 *	carNumber - the identifying number of the Car
 *	make - the make of the Car
 * @author lxf736
 * @version 2017-11-15 
 */

public class Car implements Sortable {
	
	private double maxSpeed;
	private String carNumber;
	private String make;

	/** 
	 * @param maxSpeed is the maximum speed of the Car in kilometres per hour as a double
	 * @param carNumber is the identifying number of the Car as a String
	 * @param make is the make of the Car as a String
	 */
	public Car(double maxSpeed, String carNumber, String make) {
		this.maxSpeed = maxSpeed;
		this.carNumber = carNumber;
		this.make = make;
	}
	
    /** 
     *  The Car class implements the Sortable interface, hence we need
     *  this explicit implementation for compareValue().
     *  @return The max speed of the Car.
     */
	public double compareValue(){
		return maxSpeed;
	}
	
    /** 
     *  toString defines how to print a Car
     *  @return  the print type of a Car
     */
	public String toString() {
		return String.format(" Max Speed: %d, Car Number: %s, Make: %s ", 
                maxSpeed,
                carNumber,
                make);
	}

}
