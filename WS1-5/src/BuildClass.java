/**
 *  A class BuildClass which uses field variables className and fields to builds Strings representing
 *  components of a Class. 
 *  BuildClass consists of the following field variables:
 *  className - the name of the Class to be built
 *  fields - the field variables of the Class to be built
 *  The method makeFields() builds the field variables of the Class
 *  The method makeConstructor() builds the constructor of the Class
 *  The method makeGetters() builds the getters of the Class
 *  The method makeSetters() builds the setters of the Class
 *  The method buildClass() combines the above methods together with the 
 *  Class header and a closing "}"
 * @author lxf736
 * @version 2017-11-27
 */

import java.util.ArrayList;

public class BuildClass {
	
	private String className;
	private ArrayList<Var> fields;
	
	/** 
     * @param className is the name of the Class to be built as a String
     * @param fields are the field variables of the Class to be built as an ArrayList
	 */
	public BuildClass (String className, ArrayList<Var> fields) {
		this.className = className;
		this.fields = fields;
	}
	
	/**
	 * builds the field variables of the Class
	 * @return field variables of the Class as a correctly formatted String
	 */
	public String makeFields () {
		String result = "";
		for (Var el: fields) {
		    result = result + "private " + el.toString() + ";";
			result = result + "\n";
		}
		return result.replaceAll("(?m)^", "\t");
	}
	
	/**
	 * builds the constructor of the Class
	 * @return constructor of the Class as a correctly formatted String
	 */
	public String makeConstructor () {
		String result = "public " + className + " (";
		for (int i = 0; i < fields.size(); i++) {
			result = result + fields.get(i).toString();
			if (i + 1 == fields.size()) {
				result = result + ")";
			} else {
				result = result + ", ";
			}
		}
		result = result + " {";
		for (Var el: fields) {
			result = result + "\n    this." 
				+ el.getNameOfVar() + " = " + el.getNameOfVar() + ";";
		}
		result = result + "\n}";
		return result.replaceAll("(?m)^", "\t");
	}
	
	/**
	 * builds the getters of the Class
	 * @return getters of the Class as a correctly formatted String
	 */
	public String makeGetters () {
		String result = "";
		for (Var el: fields) {
			result = result + "public " + el.getTypeOfVar() + " get" 
				+ el.getNameOfVar().substring(0, 1).toUpperCase() + el.getNameOfVar().substring(1) 
				+ " () {\n    return " + el.getNameOfVar() + ";" 
				+ "\n}\n\n";
		}
		return result.replaceAll("(?m)^", "\t");
	}
	
	/**
	 * builds the setters of the Class
	 * @return setters of the Class as a correctly formatted String
	 */
	public String makeSetters() {
		String result = "";
		for (Var el: fields) {
			result = result + "public void set" 
				+ el.getNameOfVar().substring(0,1).toUpperCase() + el.getNameOfVar().substring(1)
				+ " (" + el.toString() + ") {\n    this." 
				+ el.getNameOfVar() + " = " + el.getNameOfVar() + ";\n}\n\n";
		}
		return result.replaceAll("(?m)^", "\t");
	}
	
	/**
	 * builds the Class by calling the necessary methods and formatting in to a cohesive String
	 * @return fully built Class as a correctly formatted String
	 */
	public String buildClass () {
		return "public class " + className + " {\n"
		+ "\n" + makeFields() + "\n" + makeConstructor() +
		"\n\n" + makeGetters() + makeSetters() +"}";
	}

}
