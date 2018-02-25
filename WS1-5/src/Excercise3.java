/**
 * A class containing a method sieve() that takes an int max and returns an array of int containing
 * all of the prime numbers up to the provided int (and including if it is a prime itself)
 * @author lxf736
 * @version 2017-11-28
 */

public class Excercise3 {
	
	/** 
	 *  Method to return all of the prime numbers below (and including if prime) a provided value
     *  @param max all int below (and including if prime) this max will be returned.
     *  @return all of the prime numbers below (and including if prime) provided max as an array of int
     */
	public static int[] sieve(int max) {
		//initialise variables
		boolean [] sieve = new boolean [max + 1];
		int falseCount = 2;
		
		//first set all indices of 2 or greater to true
		for (int i = 0; i < max + 1; i++) {
			if (i < 2) {
				sieve[i] = false;
			} else {
				sieve[i] = true;
			}
		}
		
		/*loop all indices (starting at 2 as 0 and 1 are already false) of sieve until i is 
		no longer less than the square root of max. Nested loop iterates through multiples of the 
		current indices setting them to false (as they are not prime numbers) */
		for (int i = 2; i < Math.sqrt(max); i++) {
			for (int j = i + i; j <= sieve.length - 1; j = j+i) {
				if (sieve[j] == true) {
					sieve[j] = false;
					falseCount ++;
				}
			}
		}
		
		/*return the indices of sieve that are set to true as an array of int (result).
		This is the list of prime numbers between 1 and n (including n if n is prime)*/
		int [] result = new int [sieve.length - falseCount];
		int j = 0;
		for (int i = 0; i < sieve.length; i++) {
			if (sieve[i] == true) {
				result[j] = i;
				j++;
			}
		}
		return result;
	}

}
