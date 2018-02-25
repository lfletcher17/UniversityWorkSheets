/** @author 
 * lxf736
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 implements Worksheet2Interface {
    // Exercise 1

	/**
	 * takes a tree of Integers "t" and returns a new Tree<Integer> with all the elements 
	 * of "t" sign negated
	 * @param t is the Tree<Integer> to be negated
	 * @return a Tree<Integer> containing all elements of param t sign negated
	 */
    public static Tree<Integer> negateAll(Tree<Integer> t) {
    		if (t.isEmpty()) {
    			return t;
    		} else {
    			return new Tree<Integer> (t.getValue() * -1, negateAll(t.getLeft()), negateAll(t.getRight()));
    		}
    }

    // Exercise 2

	/**
	 * takes a Tree of Integers "a" and returns true if all elements are positive (i.e. >=0). 
	 * Otherwise returns false
	 * @param a is the Tree<Integer> to be evaluated
	 * @return true if all elements of a are positive (i.e. >= 0), otherwise return false
	 */
    public static boolean allPositive(Tree<Integer> a) {
		if (a.isEmpty()) {
			return true;
		} else if (a.getValue() < 0) {
			return false;
		} else {
			return allPositive(a.getLeft()) && allPositive(a.getRight());
		}
    }

    // Exercise 3

    /**
     * takes a provided Tree<E> "t" and returns a tree that is the mirror image of "t"
     * along the left-right axis
     * @param t is the Tree<E> to be mirrored along the left-right axis 
     * @return a Tree<E> that mirrors param "t" along the left-right axis
     */
    public static<E> Tree<E> mirror(Tree<E> t) {
		if (t.isEmpty()) {
			return t;
		} else {
			return new Tree<E> (t.getValue(), mirror(t.getRight()), mirror(t.getLeft()));
		}
	}
    

    // Exercise 4

    /**
     * takes a provided Tree<E> "t" and traverses its nodes in postorder storing the elements in a list. 
     * The elements of t are then returned in postorder as a List<E>.
     * @param t is the Tree<E> to be traversed / converted to List<E> in postorder
     * @return the elements of t in postorder as a List<E>
     */
    public static<E> List<E> postorder(Tree<E> t) {
    		if (t.isEmpty()) {
    			return new List<E>();
    		} else {
    			return addtoend(append(postorder(t.getLeft()), postorder(t.getRight())), t.getValue());
    		}
    }
    
    /**
     * TAKEN FROM ListOps.java - provided via Canvas
     * takes a List<E> "a" and appends to a second List<E> "b".
     * @param a is the List<E> to be appended to
     * @param b is the List<E> to append to param a
     * @return a new List<E> which represents param b appended to param a
     */
    static<E> List<E> append(List<E> a, List<E> b) {
        if (a.isEmpty())
            return b;
        else
            return new List<E>(a.getHead(), append(a.getTail(), b));
    }
    
    /**
     * TAKEN FROM ListOps.java - provided via Canvas
     * takes a List<E> "a" and an object of type<E> "x" and returns a new List 
     * where "x" becomes a new element at the end of "a"
     * @param a is the List<E> to add param x to the end of
     * @param x is the element to add to the end of param a
     * @return a new List<E> with x as the "end" element of a
     */
    static<E> List<E> addtoend(List<E> a, E x) {
        return append(a, new List<E>(x, new List<E>()));
    }

	// Exercise 5

    /**
     * takes a tree of Integers "a" and determines whether it is a binary search tree. Returns
     * true if "a" is a binary search tree otherwise returns false
     * @param a is the Tree<Integer> to be evaluated
     * @return true if param a is a binary search tree, otherwise returns false
     */
    public static boolean isSearchTree(Tree<Integer> a) {
		if (a.isEmpty() || a.getHeight() == 1) {
			return true;
		} else if (!a.getLeft().isEmpty() && !a.getRight().isEmpty()) {
			if (a.getLeft().getValue() < a.getValue() && a.getRight().getValue() > a.getValue()) {
				return isSearchTree(a.getLeft()) && isSearchTree(a.getRight());
			} else {
				return false;
			}
		} else if (a.getLeft().isEmpty() && !a.getRight().isEmpty()) {
			if (a.getRight().getValue() > a.getValue()) {
				if(!a.getRight().getLeft().isEmpty()){
					if(a.getRight().getLeft().getValue() > a.getValue()) {
						return isSearchTree(a.getLeft()) && isSearchTree(a.getRight());
					} else {
						return false;
					}
				} else {
					return isSearchTree(a.getLeft()) && isSearchTree(a.getRight());
				} 
			} else {
				return false;
			}
		} else if (!a.getLeft().isEmpty() && a.getRight().isEmpty()) {
			if (a.getLeft().getValue() < a.getValue()) {
				if(!a.getLeft().getRight().isEmpty())
					if (a.getLeft().getRight().getValue() < a.getValue()) {
						return isSearchTree(a.getLeft()) && isSearchTree(a.getRight());
					} else {
						return false;
					}
				else { 
					return isSearchTree(a.getLeft()) && isSearchTree(a.getRight());
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
    }

    // Exercise 6

    /**
     * takes a Tree of Integers (which must satisfy the characteristics of a binary search tree) and prints
     * the values in descending order
     * @param a is the Tree<Integer> (which must satisfy the characteristics of a binary search tree)
     * whose values are to be printed in descending order
     */
    public static void printDescending(Tree<Integer> a) {
    		if (a.isEmpty()){
    			return;
    		} else {
    			printDescending(a.getRight());
	        System.out.print(a.getValue() + " ");
	        printDescending(a.getLeft());
	    }
    }

	// Exercise 7

    /**
     * takes a non - empty Tree of Integers "a" (which must satisfy the characteristics of a binary search tree)
     * and returns the maximum value present in the Tree<Integer>. Throws an Illegal Argument Exception if
     * param a is empty
     * @param a is the Tree<Integer> (which must satisfy the characteristics of a binary search tree)
     * whose maximum value is to be returned
     * @return the maximum value present within param a. Throws an Illegal Argument Exceptions if param a is
     * empty
     */
    public static int max(Tree<Integer> a) {
    		if (a.isEmpty()) {
    			throw new IllegalArgumentException("Provided Binary Search Tree is empty");
    		} else if (a.getRight().isEmpty()) {
    			return a.getValue();
    		} else {
    			return max(a.getRight());
    		}
    }

    // Exercise 8

    /**
     * takes a Tree of Integers "a" (which must satisfy the characteristics of a binary search tree)
     * and finds int x within the Tree. A new Tree is constructed whereby the value of x is deleted and
     * the Tree is reconstructed to satisfy the characteristics of a binary search tree
     * @param a is the Tree<Integer> from which param x is to be deleted
     * @param x is the int value to be deleted from param a
     * @return a newly constructed Tree<Integer>(which still satisfies the characteristics of a 
     * binary search tree) that no longer contains the value of param x
     */
    public static Tree<Integer> delete(Tree<Integer> a, int x) {
    		if (a.isEmpty() || (a.getLeft().isEmpty() && a.getRight().isEmpty())) {
    			return new Tree<Integer>();
    		} else if (x > a.getValue()) {
    			return new Tree<Integer>(a.getValue(), a.getLeft(), delete(a.getRight(), x));	
    		} else if (x < a.getValue()) {
    			return new Tree<Integer>(a.getValue(), delete(a.getLeft(), x), a.getRight());
    		} else {
    			return new Tree<Integer>(max(a.getLeft()), delete(a.getLeft(), x), a.getRight());
    		}
    }

	// Exercise 9

    /**
     * takes a Tree<E> "a" and determines whether the Tree is height balanced. Returns true if 
     * the Tree is height balanced, otherwise returns false
     * @param a is the Tree<E> to be checked for height balance.
     * @return true if param a is height balanced. Otherwise return false.
     */
    public static<E> boolean isHeightBalanced(Tree<E> a) {
    		if (a.isEmpty() || a.getHeight() == 1) {
			return true;
    		} else if (heightDiff(a) == -1 || heightDiff(a) == 0 || heightDiff(a) == 1) {
    			return isHeightBalanced(a.getLeft()) && isHeightBalanced(a.getRight());
    		} else {
    			return false;
    		}
  
    }
    
    /**
     * takes a Tree<E> and calculates the difference in height between the left and right subtree returning
     * the value as an int. Please note the height is returned as the height of right subtree minus the height of
     * the left subtree. i.e. if right subtree is of height 2 and left is of height 1, 1 will be returned. 
     * Conversely if right is of height 1 and left is of height 2, -1 will be returned. For Empty Trees and 
     * Trees of height 1, 0 is returned
     * @param a is the Tree<E> whose height difference is returned
     * @return the height difference of the left and right subtree of param a as an int
     */
    public static<E> int heightDiff(Tree<E> a) {
    		if (a.isEmpty() || a.getHeight() == 1) {
    			return 0;
    		} else {
    			return a.getRight().getHeight() - a.getLeft().getHeight();
    		}
    }

    /*
	// Exercise 10

    public static Tree<Integer> insertHB(int x, Tree<Integer> a) {
				return new Tree<Integer> (x);
    }
    
    public static Tree<Integer> singleRotation(Tree<Integer> a ){
    		return new Tree<Integer>(a.getRight().getValue(), 
    				new Tree<Integer>(a.getValue(), a.getLeft(), a.getRight().getLeft()),
    				a.getRight().getRight());
    }
    
    public static Tree<Integer> doubleRotation(Tree<Integer> a ){
		return new Tree<Integer>(a.getRight().getLeft().getValue(), 
				new Tree<Integer>(a.getValue(), a.getLeft(), a.getRight().getLeft().getLeft()),
				new Tree<Integer>(a.getRight().getValue(), a.getRight().getLeft().getRight(),a.getRight().getRight()));
    }
    
    public static Tree<Integer> deleteHB(Tree<Integer> a, int x) {
				return new Tree();
    }
    */
    
}


