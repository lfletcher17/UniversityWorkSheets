/** @author 
 * lxf736
 * This class contains the solution for Worksheet1
 */

public class Worksheet1 {

  // Exercise 1

  public static int power(int m, int n) {
	  if (int n == 0) {
		  return 1; 
	  } else {
		  return m * power(m, n - 1)
	  }
	}

  public static int fastPower(int m, int n) {
	  if (int n == 0) {
		  return 1;
	  } else if (n % 2 == 0) {
		  return power(m, n/2) * power(m, n/2);
	  } else {
		  m * power(m, n -1);
	  }
	}

  // Exercise 2

	public static List<Integer> negateAll(List<Integer> a) {
			return new List<Integer>(); //replace this by your code
	}

  // Exercise 3

	public static int find(int x, List<Integer> a) {
			return 0; //replace this by your code
	}

	// Exercise 4

	public static boolean allPositive(List<Integer> a) {
			return true; //replace this by your code
	}

	// Exercise 5

	public static List<Integer> positives(List<Integer> a) {
			return new List<Integer>(); //replace this by your code
	}

	// Exercise 6

	public static boolean sorted(List<Integer> a) {
			return true; //replace this by your code
	}

	// Exercise 7

	public static List<Integer> merge(List<Integer> a, List<Integer> b) {
			return new List<Integer>(); //replace this by your code
	}

	// Exercise 8

	static List<Integer> removeDuplicates(List<Integer> a) {
			return new List<Integer>(); //replace this by your code
	}

}
