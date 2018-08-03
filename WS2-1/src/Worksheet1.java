/** @author 
 * lxf736
 * This class contains the solution for Worksheet1
 */

public class Worksheet1 {
	
	// Exercise 1
	
	/**
	 * raises a non-negative integer m to the power of a non-negative integer n
	 * @param m is the non-negative integer to be raised by n
	 * @param n is the power param m is to be raised by
	 * @return the result of m raised by n as an int
	 */
	public static int power(int m, int n) {
		if (n == 0) { 
			return 1; 
		} else {
			return m * power(m, n - 1);
		}
	}

	/**
	 * an efficient method to raise a non-negative integer m to the power of 
	 * a non-negative integer n
	 * @param m is the non-negative integer to be raised by n
	 * @param n is the power param m is to be raised by
	 * @return the result of m raised by n as an int
	 */
	public static int fastPower(int m, int n) {
		if (n == 0) {
			return 1;
		} else if (n % 2 == 0) {
			return fastPower(m, n/2) * fastPower(m, n/2);
		} else {
		  return m * fastPower(m, n -1);
		}
	}

	// Exercise 2
	
	/**
	 * Takes a List of integers "a" and returns a new List with all the elements 
	 * of "a" with sign negated
	 * @param a is the List of integers to be negated
	 * @return a List containing all elements of param a sign negated
	 */
	public static List<Integer> negateAll(List<Integer> a) {
		if (a.isEmpty()) {
			throw new IllegalArgumentException("Trying to negate an empty List");
		} else if (a.getTail().isEmpty()) {
			return new List<Integer>(a.getHead() * -1, a.getTail());
		} else {
			return new List<Integer>(a.getHead() * -1, negateAll(a.getTail()));
		}
	}

	// Exercise 3

	/**
	 * Takes an integer x and finds it's position within List a. The method throws as
	 * IllegalArgumentException if x is not present within List a
	 * @param x is the integer to be found within param a
	 * @param a is the List containing param x
	 * @return the position of the element within a containing int x. throws as
	 * IllegalArgumentException if x is not present within a
	 */
	public static int find(int x, List<Integer> a) {
		if (a.isEmpty()) {
			throw new IllegalArgumentException("x is not present within List a");
		} else if (a.getHead() == x) {
			return 0;
		} else {
			return 1 + find(x, a.getTail());
		}
	}

	// Exercise 4

	/**
	 * takes a List of integers and returns true if all elements are positive. Otherwise 
	 * returns false
	 * @param a is the List of integers to be evaluated
	 * @return true if all elements of a are positive, otherwise return false
	 */
	public static boolean allPositive(List<Integer> a) {
		if (a.isEmpty()) {
			return true;
		} else if (a.getHead() < 0) {
			return false;
		} else {
			return allPositive(a.getTail());
		}
	}

	// Exercise 5

	/**
	 * takes a List of integers "a" and returns a new List containing only the positive integers 
	 * present in a. Returns the results in same relative order
	 * @param a is the List of integers to search for positive integers
	 * @return a new List containing all positive integers present in param a. Returns the results
	 * in the same relative order
	 */
	public static List<Integer> positives(List<Integer> a) {
		if (a.isEmpty()) {
			return a;
		} else if (a.getHead() > 0) {
			return new List<Integer>(a.getHead(), positives(a.getTail()));
		} else {
			return positives(a.getTail());
		}
	}

	// Exercise 6

	/**
	 * takes a List of integers "a" and evaluates whether the List is sorted in ascending order
	 * Duplicate copies of elements are permitted so long as they are grouped together. Returns true
	 * if elements of a are are sorted in ascending order, otherwise returns false
	 * @param a is the List of integers to be evaluated for sortedness
	 * @return true if elements of param a are sorted in ascending order. Otherwise return false
	 */
	public static boolean sorted(List<Integer> a) {
		if (a.isEmpty() || a.getTail().isEmpty()) {
			return true;
		} else if (a.getHead() > a.getTail().getHead()) {
			return false;
		} else {
			return sorted(a.getTail());
		}
	}

	// Exercise 7

	/**
	 * takes 2 sorted Lists of integers ("a" and "b" - both of which must be sorted in ascending order) 
	 * and returns  a new sorted List containing all elements of both Lists. The new List is also 
	 * sorted in ascending order
	 * @param a is a List of integers sorted in ascending order to be merged with param b
	 * @param b is a List of integers sorted in ascending order to be merged with param a
	 * @return a List containing all elements of param a and b sorted in ascending order
	 */
	public static List<Integer> merge(List<Integer> a, List<Integer> b) {
		if (a.isEmpty() && b.isEmpty()) {
			return new List<Integer>();
		} else if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} else if (a.getHead() > b.getHead()) {
			return new List<Integer>(b.getHead(), merge(a, b.getTail()));
		} else {
			return new List<Integer>(a.getHead(), merge(a.getTail(), b));
		}
	}

	// Exercise 8

	/**
	 * takes a sorted List of integers "a" (must be sorted in ascending order) and returns a new List
	 * only containing the unique elements of param a (i.e. removes any duplicate entries). The new List
	 * is also sorted in ascending order
	 * @param a is the List from which only unique entries are returned (must be sorted in ascending order).
	 * @return a new List containing the unique elements present within param a. The List is returned in
	 * ascending order.
	 */
	static List<Integer> removeDuplicates(List<Integer> a) {
		if (a.isEmpty() || a.getTail().isEmpty()) {
			return a;
		} else if (a.getHead() == a.getTail().getHead()) {
			return removeDuplicates(new List<Integer>(a.getTail().getHead(), a.getTail().getTail()));
		} else {
			return new List<Integer>(a.getHead(), removeDuplicates(a.getTail()));
		}
	}

		

}
