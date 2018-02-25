package predictive;

/**
* Class with main method. For each argument provided to main method the static signatureToWords() 
* is called from class PredictivePrototype.
* @author lxf736
* @version 2018-02-05
*/

public class Sigs2WordsProto {
	
	public static void main (String[] args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i] + ": " +
			PredictivePrototype.signatureToWords(args[i]));
		} 
	}

}
