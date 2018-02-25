package predictive;

/**
 *  A class for WordSig which is created from a WordSig's word and corresponding signature.
 *  WordSig implements Comparable and defines it's own compareTo method 
 *  WordSig consists of the following field variables:
 * 	word- a String which represents the word of WordSig
 * 	signature- a String which represents the corresponding signature of word
 * @author lxf736
 * @version 2018-02-05
 */

public class WordSig implements Comparable<WordSig>{
	
	private String word;
	private String signature;
	
	/** 
	 * @param word is the word of the WordSig as a String
	 * @param signature is the corresponding  signature of param word as a String.
	 */
	public WordSig(String word, String signature) {
		this.word = word;
		this.signature = signature;
	}
	
	/**
	 * compares this WordSig with param ws.
	 * @param ws is the WordSig to compare with this WordSig.
	 */
	@Override
	public int compareTo(WordSig ws) {
		return this.signature.compareTo(ws.signature);
	}

	/** 
     *  @return the word of WordSig as a String
     */
	public String getWord() {
		return word;
	}

	/** 
     *  @return the signature of WordSig as a String
     */
	public String getSignature() {
		return signature;
	}

}
