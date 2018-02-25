package predictive;

public class Sigs2WordsMap {

/**
* Class with main method which creates an object of MapDictionary. For each argument
* provided to main method the signatureToWords() method is called on the constructed MapDictionary object.
* @author lxf736
* @version 2018-02-12
*/
	
	/*Timings highlighting efficiency:
	args of 2, 3, 4, 5, 6, 7, 8, 9, 22, 33, 44, 55, 66, 77, 88, 99
	Sigs2WordsProto real	0m3.820s
	Sigs2WordsList real	0m0.799s
	Sigs2WordsMap real 0m0.852 */
	public static void main (String [] args) {
		MapDictionary md = new MapDictionary("/usr/share/dict/words");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i] + ": " +
			md.signatureToWords(args[i]));
		}
	}
	
}



