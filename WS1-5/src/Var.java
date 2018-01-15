/**
 *  A class for a Var which represents a Java variable.  
 *  Var consists of the following field variables:
 *  typeOfVar - the data type of the variable (i.e. String is the data type of typeOfVar)
 *  nameOfVar - the name of the variable (i.e. nameOfVar is a variable name)
 * @author lxf736
 * @version 2017-11-27
 */

public class Var {
	
	private String typeOfVar;
	private String nameOfVar;
	
	/** 
     * @param typeOfVar is the data type of the Var as a String (i.e. "String")
     * @param nameOfVar is the name of the Var as a String (i.e. "nameOfVar")
	 */
	public Var (String typeOfVar, String nameOfVar) {
		this.typeOfVar = typeOfVar;
		this.nameOfVar = nameOfVar;
	}
	
	/** 
     *  @return the typeOfVar of Var as String
     */
	public String getTypeOfVar () {
		return typeOfVar;
	}
	
	/** 
     *  @return the nameOfVar of Var as String
     */
	public String getNameOfVar () {
		return nameOfVar;
	}
	
    /** 
     *  toString defines how to print a Var
     *  @return  the print type of a Var
     */
	public String toString () {
		return typeOfVar + " " + nameOfVar;
	}

}
