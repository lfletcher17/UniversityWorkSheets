/**
 * A class for a Complex number created from a real number and imaginary number.
 * Complex consists of the following field variables:
 * 	realPart - the realPart of the Complex number
 *	imaginaryPart - the imaginary part of the Complex number
 * @author lxf736
 * @version 2017-10-18
 *
 */

public class Complex {
	
	private double realPart;
	private double imaginaryPart;
	
	/**
	 * @param real is the realPart as double
	 * @param imaginary is the imaginaryPart as double
	 */
	public Complex (double real, double imaginary) {
		realPart = real;
		imaginaryPart = imaginary;
	}

	/** 
     *  @return the realPart as Double
     */
	public double getRealPart() {
		return realPart;
	}

	/** 
     *  @return the imaginaryPart as Double
     */
	public double getImaginaryPart() {
		return imaginaryPart;
	}
	
    /** 
     *  toString defines how to print a Complex number
     *  @return  the print type of a Complex number
     */
	public String toString() {
		return realPart + " + " + imaginaryPart + "i"; 
	}
	
	/** 
	 *  Method to add a Complex number to this Complex number.
     *  @param complex is the Complex number you would like to add to this Complex number
     *  @return the result of this Complex number plus the provided Complex number
     */
	public Complex add(Complex summand) {
		return new Complex ((this.realPart + summand.realPart) , (this.imaginaryPart + summand.imaginaryPart));
	}
	
	/** 
	 *  Method to multiply a Complex number with this Complex number.
     *  @param complex is the Complex number you would like to multiply with this Complex number
     *  @return the result of this Complex number multiplied by the provided Complex number
     */
	public Complex multiply(Complex factor) {
		return new Complex ((this.realPart * factor.realPart - this.imaginaryPart * factor.imaginaryPart) , 
				(this.realPart * factor.imaginaryPart + factor.realPart * this.imaginaryPart));
	}

}
