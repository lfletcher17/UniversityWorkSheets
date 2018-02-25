package predictive;

/**
* Class with main method which creates an object of ListDictionary. For each argument
* provided to main method the signatureToWords() method is called on the constructed ListDictionary object.
* @author lxf736
* @version 2018-02-05
*/

public class Sigs2WordsList {
	
	/*Timings highlighting efficiency savings using Sigs2WordsList:
	args of 2, 3, 4, 5, 6, 7, 8, 9, 22, 33, 44, 55, 66, 77, 88, 99
	Sigs2WordsProto real	0m3.820s
	Sigs2WordsList real	0m0.799s */
	public static void main (String [] args) {
		ListDictionary ld = new ListDictionary("/usr/share/dict/words");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i] + ": " +
			ld.signatureToWords(args[i]));
		}
	}

}
