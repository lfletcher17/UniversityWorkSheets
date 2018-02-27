/**
 *  A class which extends Observable to provide a Model Separation for MapDictionary and the corresponding 
 *  View class. DictionaryModel is constructed from a MapDictionary 'dictionary' which provides the underlying
 *  dictionary functionality, an ArrayList 'message' which stores the current message as input via the View,
 *  a String 'signature' which stores the current Signature of a word as it is input via the View, an ArrayList 
 *  'currentWords' which stores the words corresponding to field variable 'signature' and an int 'count'
 *  which is used to calculate which word within the ArrayList 'currentWords' should be displayed via the View.
 *  MapDictionary consists of the following field variables:
 *  dictionary - a MapDictionary which provides the underlying predictive text functionality
 *  message - an ArrayList which stores the current message as input via the View
 *  signature - a String that stores the current Signature of a word as it is input via the View
 *  currentWords - an ArrayList that stores the words corresponding to field variable 'signature'
 *  count - an int that is used to calculate which word within the ArrayList 'currentWords' should be displayed 
 *  via the View
 * @author lxf736
 * @version 2018-02-17
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import predictive.MapDictionary;

public class DictionaryModel extends Observable implements DictionaryModelInterface {
	
    private MapDictionary dictionary;
    private ArrayList<String> message;
    private String signature;
    private ArrayList<String> currentWords;
    private int count;

	/** 
	 * @param dictionaryFile is the path of a file which is used to instantiate the 
	 * dictionary field variable. Constructor creates a Model separation to be used between 
	 * MapDictionary and corresponding View class
	 */
    public DictionaryModel(String dictionaryFile) throws java.io.IOException {
        dictionary = new MapDictionary(dictionaryFile);
        message = new ArrayList<String>();
        currentWords = new ArrayList<String>();
        signature = "";
        count = 0;
    }

	/**  
	 * Constructor creates a Model separation to be used between MapDictionary and 
	 * corresponding View class using default file path "/usr/share/dict/words"
	 */
    public DictionaryModel() throws java.io.IOException {
        dictionary = new MapDictionary("/usr/share/dict/words");
        message = new ArrayList<String>();
        currentWords = new ArrayList<String>();
        signature = "";
        count = 0;
    }

	/**  
	 * Returns a list of the words typed in so far, including the last word which has 
	 * not yet been accepted.
	 * @return an ArrayList<String> containing all elements of field variable message and
	 * the latest word that is currently being input (i.e. not yet accepted)
	 */
    public List<String> getMessage() {
    		ArrayList<String> temp = new ArrayList<String>();
    		if (!message.isEmpty()) {
    			temp.addAll(message);
    		}
        if (!currentWords.isEmpty()) {
        		temp.add(currentWords.get(count));
        }
        return temp;
    }
    
    /**
     * Adds a numeric key that has been typed in by the user. Updates currentWords 
     * with possible matches for new signature (i.e. signature including latest key)
     */
    public void addCharacter(char key) {
    		signature = signature + key;
    		currentWords.clear();
    		currentWords.addAll(dictionary.signatureToWords(signature));
    		count = 0;
    		setChanged();
        notifyObservers();
    }
    
    /**
     * Removes the last character to have been input. This updates currentWords to match the new
     * signature (i.e. signature minus last character to have been input). Moves on to previous word
     * once a 'currentWord' has been fully erased.
     */
    public void removeLastCharacter() {
    		if (count == -1) {
    			message.remove(message.size()-1);
    			count = 0;
    		}
    		if (signature.length() > 0) {
    			signature = signature.substring(0, signature.length()-1);
    			currentWords.clear();
        		currentWords.addAll(dictionary.signatureToWords(signature));
        		count = 0;
    		} else if (message.size() > 0 && signature.length() <= 0) {
    			signature = dictionary.wordToSignature(message.get(message.size()-1));
    	        count = -1;
    		} 
    		setChanged();
        notifyObservers();
    }
    
    /**
     * Cycles through the possible matches for the current signature, i.e. changes the current 
     * word to the next match contained within currentWords. Cycles back to the first match once
     * currentWords has been exhausted.
     */
    public void nextMatch() {
    		if (currentWords.size() > 0) {
    			count = (count + 1) % currentWords.size();
	    		setChanged();
	        notifyObservers();
    		}
    }

    /**
     * Accepts the current matched word and adds it to the composed message (by adding the currently
     * displayed entry from currentWords to message)
     */
	public void acceptWord() {
		if (!currentWords.isEmpty()) {	
			message.add(currentWords.get(count));
			currentWords.clear();
			signature = "";
			setChanged();
	        notifyObservers();
		} else {
			message.add(" ");
		}
	}
    //
}