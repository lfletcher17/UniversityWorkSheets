package predictive;

/**
 *  A class for PredictivePrototype containing static methods which can be used
 *  to provide basic predictive text functionality. The functionality is based upon
 *  the requirements of a pre-touch screen mobile phone (i.e. a phone with a keypad style 
 *  interface where the key '2' corresponds to characters a, b, c).
 *  PredictivePrototype works by associating a String 'word' with a corresponding String 'signature'.
 *  A given words signature (as a String) can be computed using the wordToSignature() method. Conversely
 *  a Set of words associated to a given signature can be computed using the signatureToWords() method.
 *  signatureToWords() scans a file of words and will return all words that match the provided signature
 *  as a HashSet. PredictivePrototype also contains the method isValidWord() which is used to check that words
 *  computed in the signatureToWords() method (i.e. found in the words file) comply with the restrictions of
 *  this functionality - namely that all words are comprised of only alphabetic characters. 
 * @author lxf736
 * @version 2018-02-05
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PredictivePrototype {
	
	/**
     *  takes a String 'word' and computes the associated signature, returning it as a String. 
     *  i.e. the String "hello" would return a signature of 43556. This is because h is found on 
     *  the key 4, e is found on the key 3 etc.). Any non alphabetic characters found in param 'word'
     *  are replaced with a blank space (i.e. " ")
     *  @param word is the String whose corresponding signature is to be calculated
     *  @return the signature of param word as a String
     */
	public static String wordToSignature(String word) {
		 /* 
		 Within the method we use a StringBuffer object (buffWord) which we append our resulting 
	     signature to as the method processes (i.e. each time a signature character is identified, it
	     is appended to buffWord). This is then returned as a String. The reason for adopting this approach
	     is that String itself is read only, when appending to a String, a temporary StringBuffer is created
	     to handle the concatenation. This can become very costly performance wise particularly in cases such as
	     this where multiple concatenations are likely. By creating our own StringBuffer we can concatenate directly 
	     from the object each time. 
	     */
		StringBuffer buffWord = new StringBuffer();
		word = word.toLowerCase();
		for (int i=0; i < word.length(); i++) {
			if (word.charAt(i) > 96 && word.charAt(i) < 100) {
				buffWord.append("2");
			} else if (word.charAt(i) > 99 && word.charAt(i) < 103) {
				buffWord.append("3");
			} else if (word.charAt(i) > 102 && word.charAt(i) < 106) {
				buffWord.append("4");
			} else if (word.charAt(i) > 105 && word.charAt(i) < 109) {
				buffWord.append("5");
			} else if (word.charAt(i) > 108 && word.charAt(i) < 112) {
				buffWord.append("6");
			} else if (word.charAt(i) > 111 && word.charAt(i) < 116) {
				buffWord.append("7");
			} else if (word.charAt(i) > 115 & word.charAt(i) < 119) {
				buffWord.append("8");
			} else if (word.charAt(i) > 118 & word.charAt(i) < 123) {
				buffWord.append("9");
			} else {
				buffWord.append(" ");
			}
		}
		return buffWord.toString();
	}
	
	/**
     *  takes a String (param "signature") and computes all corresponding words that are 
     *  found in a given file. The path of the file is set within the method itself and can only be 
     *  changed by changing the value of "String filename" within the method body itself. Method returns
     *  a HashSet containing all of the words found in the file with the signature provided.
     *  @param signature is the String representation of a signature which is used to identify corresponding
     *  words within the file in question.
     *  @return a HashSet of all words found with the provided signature
     */
	public static Set<String> signatureToWords(String signature) {
		/*
		This implementation is inefficient. Each time that we wish to find a list of words associated to
        a given signature we have to scan a file of words. Adopting an alternative approach whereby
        we scan a file of words once and store them in memory would be far more efficient as once the words
        are in memory they can be accessed directly. This would mean a significant portion of the processing 
        within this method could be done once while the desired functionality could then be utilised
        for as long as the words are kept in memory at a far less expensive processing cost.
		 */
		Set<String> resultSet = new HashSet<String>();
		String fileName = "/usr/share/dict/words";
		Scanner s;
		try {
			s = new Scanner(new File(fileName));
			while (s.hasNextLine()) {
				String currentLine = s.nextLine().toLowerCase();
				if (isValidWord(currentLine)) {
					if (wordToSignature(currentLine).equals(signature)) {
						resultSet.add(currentLine);
					}
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error Reading File");
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
     *  takes a String (param "word") and checks that the word is valid. Within the context of this
     *  method valid means that param word does not contain any non-alphabetic characters. Returns
     *  true if param word does not contain non-alphabetic characters, otherwise returns false
     *  @param word is the word checked for it's validity
     *  @return true if param word is valid, otherwise return false
     */
	public static boolean isValidWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) < 97 || word.charAt(i) > 122) {
				return false;
			}
		}
		return true;
	}	

}
