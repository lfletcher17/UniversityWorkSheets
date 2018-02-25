package predictive;

/**
 *  A class for ListDictionary which provides predictive text functionality. The functionality 
 *  is based upon the requirements of a pre-touch screen mobile phone (i.e. a phone with a keypad style 
 *  interface where the key '2' corresponds to characters a, b, c). ListDictionary is constructed from
 *  an ArrayList<WordSig>. This ArrayList is then used as a means to calculate TreeSets of words
 *  that correspond to a provided signature (this is done using the instance method signatureToWords().
 *  Please note, ListDictionary is in essence a more efficient version of PredictivePrototype. As such
 *  some static methods from PredictivePrototype are used here where efficiencies are not gained by
 *  adopting this approach - specifically wordToSignature() and isValidWord().
 *  ListDictionary consists of the following field variable:
 * 	words- an ArrayList<WordSig> which is a sorted ArrayList of words and their signatures.
 * @author lxf736
 * @version 2018-02-05
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ListDictionary implements Dictionary {
	
	private ArrayList<WordSig> words;
	
	/** 
	 * @param filepath is the path of a file which is read line by line to construct an object
	 * of ListDictionary. Specifically for each line that is read an object of type WordSig is constructed
	 * and added to the field variable words (which is an ArrayList<WordSig>). This is then used within ListDictionary
	 * to provide predictive text functionality.
	 */
	public ListDictionary(String filePath) {
		words = new ArrayList<WordSig>();
		try {
			Scanner s = new Scanner(new File(filePath));
			while(s.hasNextLine()) {
				String currentLine = s.nextLine().toLowerCase();
				if (PredictivePrototype.isValidWord(currentLine)) {
					words.add(new WordSig(currentLine, PredictivePrototype.wordToSignature(currentLine)));
				}
			}
			Collections.sort(words);
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File");
			e.printStackTrace();
		}
	}
	

	/**
     *  takes a String (param "signature") and computes all corresponding words that are 
     *  found within the field variable 'words'. Method returns a TreeSet of Strings containing 
     *  all of the words found in the field variable 'words' with the signature provided.
     *  @param signature is the String representation of a signature which is used to identify corresponding
     *  words within field variable 'words'.
     *  @return a TreeSet of all words found with the provided signature
     */
	public Set<String> signatureToWords(String signature) {
		Set<String> resultSet = new TreeSet<String>();
		int count = 0;
		int firstIndex = Collections.binarySearch(words, new WordSig("", signature));
		if (firstIndex < 0) {
			return resultSet;
		} else {
			resultSet.add(words.get(firstIndex).getWord());
			while (words.get(firstIndex).compareTo(words.get(firstIndex + count)) == 0 && firstIndex + count < words.size() - 1) {
				resultSet.add(words.get(firstIndex + count).getWord());
				count ++;
			}
			count = 0;
			while (words.get(firstIndex).compareTo(words.get(firstIndex - count)) == 0 && firstIndex - count > 0) {
				resultSet.add(words.get(firstIndex - count).getWord());
				count ++;
			}
			return resultSet;
		}
	}
	

	

	

}
