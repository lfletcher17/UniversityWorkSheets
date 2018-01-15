public class ArrayNonNeg {
	 private int[] a;
     //invariant:
     //   a != null
     //   every a[i] (0 <= i < a.length) is >= 0
    
    /** 
     * Constructor.
     * Initializes a to an array of length elements,
     *   with each a[i] == i.
     * @param length value for length of array
     * @throws IllegalArgumentException unless length >= 0 
     */
    public ArrayNonNeg(int length){
    		if (length >= 0) {
	    		a = new int [length];
	    		int n = 0;
	    		/* loop invariant:
	    		*   0 <= n <= a.length
	    		*   a.length == n
	    		*/
	    		while (n < a.length) {
	    			a[n] = n;
	    			n++;
	    		}
    		} else {
    			throw new IllegalArgumentException("Invalid length: length must be >= 0");
    		}
    }
    	
}