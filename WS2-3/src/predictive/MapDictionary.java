package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *  A class for MapDictionary which provides predictive text functionality. The functionality 
 *  is based upon the requirements of a pre-touch screen mobile phone (i.e. a phone with a keypad style 
 *  interface where the key '2' corresponds to characters a, b, c). MapDictionary is constructed from
 *  a HashMap<String, HashSet<String>>. This HashMap is then used as a means to return HashSets of words
 *  that correspond to a provided signature (this is done using the instance method signatureToWords().
 *  Please note, MapDictionary is in essence a more efficient version of PredictivePrototype. As such
 *  some static methods from PredictivePrototype are used here where efficiencies are not gained by
 *  adopting this approach - specifically wordToSignature() and isValidWord().
 *  MapDictionary consists of the following field variable:
 * 	words- a HashMap <String, HashSet<String>> which is an implementation of Map that stores word signatures as
 *  a key together with a corresponding HashSet of corresponding words.
 * @author lxf736
 * @version 2018-02-05
 */
public class MapDictionary implements Dictionary {
	
	private Map <String, HashSet<String>> words;
	
	/** 
	 * @param filepath is the path of a file which is read line by line to construct an object
	 * of MapDictionary. Specifically for each line that is read a check is performed to discover whether
	 * an entry exists in the HashMap for the given lines' corresponding signature (which acts as the key within the
	 * HashMap). If an entry already exist for the given signature, the word is added to the corresponding HashSet.
	 * Otherwise a new entry is created within the HashMap. This HashMap is then used within MapDictionary
	 * to provide predictive text functionality.
	 */
	public MapDictionary(String filePath) {
		words = new HashMap<String, HashSet<String>>();
		try {
			Scanner s = new Scanner(new File(filePath));
			while(s.hasNextLine()) {
				String currentLine = s.nextLine().toLowerCase();
				if (PredictivePrototype.isValidWord(currentLine)) {
					String currentSig = PredictivePrototype.wordToSignature(currentLine);
					HashSet<String> matchesForSig = new HashSet<String>();
					if (!words.containsKey(currentSig)) {
						matchesForSig.add(currentLine);
						words.put(currentSig, matchesForSig);
					} else {
						matchesForSig = words.get(currentSig);
						matchesForSig.add(currentLine);
						words.put(currentSig, matchesForSig);
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File");
			e.printStackTrace();
		}
	}

	/**
     *  takes a String (param "signature") and finds the corresponding HashSet of dictionary words 
     *  within the MapDictionary.
     *  @param signature is the String representation of a signature which is used to identify corresponding
     *  words within field variable 'words'.
     *  @return a HashSet of all words found with the provided signature
     */
	public Set<String> signatureToWords(String signature) {
        if (!words.containsKey(signature)) {
            return Collections.emptySet();
        } else {
            return words.get(signature);
        }
	}

	public String wordToSignature(String string) {
		return PredictivePrototype.wordToSignature(string);
	}

}
