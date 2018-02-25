import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * @author 
 * lxf736
 *
 */

public class Worksheet2Test {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void Excercise1Test1() {
		
		Tree<Integer> tExpected = new Tree<Integer> ();
		
		Tree<Integer> tActual = new Tree<Integer> ();
		tActual = Worksheet2.negateAll(tActual);
		
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise1Test2() {
		//providing following tree:
		//		-1
		//null		null
		//expecting back
		//		1
		//null		null
		
		Tree<Integer> tExpected = new Tree<Integer> (1);
		
		Tree<Integer> tActual = new Tree<Integer> (-1);
		tActual = Worksheet2.negateAll(tActual);
		
		assertTrue(tActual.equals(tExpected));
	
	}
	
	@Test
	public void Excercise1Test3() {
		// feeding in the following tree
		//		1
		//-2			null
		//expecting back
		//		-1
		//2			null
		
		Tree<Integer> tExpected = new Tree<Integer> (2);
		tExpected = new Tree<Integer> (-1, tExpected, new Tree<Integer>());
		
		Tree<Integer> tActual = new Tree<Integer> (-2);
		tActual = new Tree<Integer> (1, tActual, new Tree<Integer>());
		tActual = Worksheet2.negateAll(tActual);
		
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise1Test4() {
		// feeding in the following tree
		//		1
		//-2			3
		//expecting back
		//		-1
		//2			-3	
		
		Tree<Integer> tExpected = new Tree<Integer> (2);
		tExpected = new Tree<Integer> (-1, tExpected, new Tree<Integer>(-3));
		
		Tree<Integer> tActual = new Tree<Integer> (-2);
		tActual = new Tree<Integer> (1, tActual, new Tree<Integer>(3));
		tActual = Worksheet2.negateAll(tActual);
		
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise1Test5() {
		// feeding in the following tree
		//		-2
		//-3			-1
		//expecting back
		//		2
		//3			1	
		
		Tree<Integer> tExpected = new Tree<Integer> (3);
		tExpected = new Tree<Integer> (2, tExpected, new Tree<Integer>(1));
		
		Tree<Integer> tActual = new Tree<Integer> (-3);
		tActual = new Tree<Integer> (-2, tActual, new Tree<Integer>(-1));
		tActual = Worksheet2.negateAll(tActual);
		
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test 
	public void Excercise2Test1() {
		
		Tree <Integer> tActual = new Tree<Integer>();
		assertTrue(Worksheet2.allPositive(tActual));
		
	}
	
	@Test
	public void Excercise2Test2() {
		// feeding in the following tree
		//		-2
		//-3			-1
		//expecting false to be returned	
		
		Tree<Integer> tActual = new Tree<Integer> (-3);
		tActual = new Tree<Integer> (-2, tActual, new Tree<Integer>(-1));
		assertFalse(Worksheet2.allPositive(tActual));
		
	}
	
	@Test
	public void Excercise2Test3() {
		// feeding in the following tree
		//		2
		//1			3
		//expecting true to be returned	
		
		Tree<Integer> tActual = new Tree<Integer> (1);
		tActual = new Tree<Integer> (2, tActual, new Tree<Integer>(3));
		assertTrue(Worksheet2.allPositive(tActual));
		
	}
	
	@Test
	public void Excercise2Test4() {
		// feeding in the following tree
		//		1
		//-3			2
		//expecting false to be returned	
		
		Tree<Integer> tActual = new Tree<Integer> (-3);
		tActual = new Tree<Integer> (1, tActual, new Tree<Integer>(2));
		assertFalse(Worksheet2.allPositive(tActual));

	}
	
	@Test
	public void Excercise3Test1() {
		
		Tree<String> tExpected = new Tree<String>();
		Tree<String> tActual = new Tree<String>();
		
		tActual = Worksheet2.mirror(tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise3Test2() {
		// providing following tree:
		//		5
		//null			null
		
		Tree<String> tExpected = new Tree<String>("5");
		Tree<String> tActual = new Tree<String>("5");
		
		tActual = Worksheet2.mirror(tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise3Test3() {
		// providing following tree:
		//		5
		//3			8
		//expecting back:
		//		5
		//8			3
		
		Tree<String> tExpected = new Tree<String>("8");
		tExpected = new Tree<String> ("5", tExpected, new Tree<String>());
		
		Tree<String> tActual = new Tree<String>("8");
		tActual = new Tree<String> ("5", new Tree<String>(), tActual);
		
		tActual = Worksheet2.mirror(tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise3Test4() {
		// providing following tree:
		//		5
		//3			8
		//expecting back:
		//		5
		//8			3
		
		Tree<String> tExpected = new Tree<String>("8");
		tExpected = new Tree<String> ("5", tExpected, new Tree<String>("3"));
		
		Tree<String> tActual = new Tree<String>("3");
		tActual = new Tree<String> ("5", tActual, new Tree<String>("8"));
		
		tActual = Worksheet2.mirror(tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise3Test5() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		//		5
		//	8		3
		//	  6 	  4	 1
		
		Tree<String> tLeftExpected = new Tree<String>();
		tLeftExpected = new Tree<String> ("8", tLeftExpected, new Tree<String>("6"));
		Tree<String> tRightExpected = new Tree<String>("4");
		tRightExpected = new Tree<String> ("3", tRightExpected, new Tree<String>("1"));
		Tree<String> tExpected = new Tree<String>("5", tLeftExpected, tRightExpected);
		
		Tree<String> tLeftActual = new Tree<String>("1");
		tLeftActual = new Tree<String> ("3", tLeftActual, new Tree<String>("4"));
		Tree<String> tRightActual = new Tree<String>("6");
		tRightActual = new Tree<String> ("8", tRightActual, new Tree<String>());
		Tree<String> tActual = new Tree<String> ("5", tLeftActual, tRightActual);
		
		tActual = Worksheet2.mirror(tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise4Test1() {

		List<String> expectedList = new List<String>();
		
		Tree<String> tActual = new Tree<String> ();
		
		List<String> result = Worksheet2.postorder(tActual);
		assertTrue(result.equals(expectedList));

	}
	
	@Test
	public void Excercise4Test2() {
		// providing following tree:
		//		5
		//	3		8
		//1	   	  	   9
		//expecting back:
		// {1,3,9,8,5}
		
		List<String> expectedList = new List<String>("5", new List<String>());
		expectedList = new List<String>("8", expectedList);
		expectedList = new List<String>("9", expectedList);
		expectedList = new List<String>("3", expectedList);
		expectedList = new List<String>("1", expectedList);
		
		Tree<String> tLeftActual = new Tree<String>("1");
		tLeftActual = new Tree<String> ("3", tLeftActual, new Tree<String>());
		Tree<String> tRightActual = new Tree<String>();
		tRightActual = new Tree<String> ("8", tRightActual, new Tree<String>("9"));
		Tree<String> tActual = new Tree<String> ("5", tLeftActual, tRightActual);
		
		List<String> result = Worksheet2.postorder(tActual);
		assertTrue(result.equals(expectedList));
		
	}
	
	@Test
	public void Excercise4Test3() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		// {}
		
		List<String> expectedList = new List<String>("5", new List<String>());
		expectedList = new List<String>("8", expectedList);
		expectedList = new List<String>("6", expectedList);
		expectedList = new List<String>("3", expectedList);
		expectedList = new List<String>("4", expectedList);
		expectedList = new List<String>("1", expectedList);
		
		Tree<String> tLeftActual = new Tree<String>("1");
		tLeftActual = new Tree<String> ("3", tLeftActual, new Tree<String>("4"));
		Tree<String> tRightActual = new Tree<String>("6");
		tRightActual = new Tree<String> ("8", tRightActual, new Tree<String>());
		Tree<String> tActual = new Tree<String> ("5", tLeftActual, tRightActual);
		
		List<String> result = Worksheet2.postorder(tActual);
		assertTrue(result.equals(expectedList));
		
	}
	
	@Test
	public void Excercise5Test1() {
		
		Tree<Integer> actualTree = new Tree<Integer>();
		assertTrue(Worksheet2.isSearchTree(actualTree));
		
	}
	
	@Test
	public void Excercise5Test2() {
		// feeding in the following tree
		//		-2
		//-3			-1
		//expecting true to be returned	

		Tree<Integer> tActual = new Tree<Integer> (-3);
		tActual = new Tree<Integer> (-2, tActual, new Tree<Integer>(-1));
		assertTrue(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise5Test3() {
		// feeding in the following tree
		//		2
		//3			-1
		//expecting false to be returned	

		Tree<Integer> tActual = new Tree<Integer> (3);
		tActual = new Tree<Integer> (2, tActual, new Tree<Integer>(-1));
		assertFalse(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise5Test4() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		// true
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
		
		assertTrue(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise5Test5() {
		// providing following tree:
		//		5
		//	3		8
		//7	  4 	  6
		//expecting back:
		// false
		
		Tree<Integer> tLeftActual = new Tree<Integer>(7);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
		
		assertFalse(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise5Test6() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  	   6
		//expecting back:
		// false
		
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, new Tree<Integer>(), tRightActual);
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
		
		assertFalse(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise5Test7() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  9
		//expecting back:
		// false
		
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(9);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
		
		assertFalse(Worksheet2.isSearchTree(tActual));
		
	}
	
	@Test
	public void Excercise6Test1() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		// {8,6,5,4,3,1}
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
				
		Worksheet2.printDescending(tActual);
		
	}
	
	@Test
	public void Excercise7Test1() {
		
		Tree<Integer> testTree = new Tree<Integer>();
		exception.expect(IllegalArgumentException.class);
		Worksheet2.max(testTree);
		
	}
	
	@Test
	public void Excercise7Test2() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		// 8
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
				
		int actual = Worksheet2.max(tActual);
		assertEquals(actual, 8);
		
	}
	
	@Test
	public void Excercise8Test1() {
		
		Tree<Integer> tExpected = new Tree<Integer>();
		
		Tree<Integer> tActual = new Tree<Integer>();
		
		tActual = Worksheet2.delete(tActual, 3);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise8Test2() {
		// providing following tree:
		//	3	
		//1	  4 	 
		//expecting back:
		//	1		
		//	  4 
		
		Tree<Integer> tExpected = new Tree<Integer>();
		tExpected = new Tree<Integer> (1, tExpected, new Tree<Integer>(4));
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer> (3, tActual, new Tree<Integer>(4));

		tActual = Worksheet2.delete(tActual, 3);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise8Test3() {
		// providing following tree:
		//	3	
		//1	  4 	 
		//expecting back:
		//	3		
		//1	   
		
		Tree<Integer> tExpected = new Tree<Integer>(1);
		tExpected = new Tree<Integer> (3, tExpected, new Tree<Integer>());
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer> (3, tActual, new Tree<Integer>(4));

		tActual = Worksheet2.delete(tActual, 4);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise8Test4() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		//		5
		//	1		8
		//	  4   6
		
		Tree<Integer> tLeftExpected = new Tree<Integer>();
		tLeftExpected = new Tree<Integer> (1, tLeftExpected, new Tree<Integer>(4));
		Tree<Integer> tRightExpected = new Tree<Integer>(6);
		tRightExpected = new Tree<Integer> (8, tRightExpected, new Tree<Integer>());
		Tree<Integer> tExpected = new Tree<Integer>(5, tLeftExpected, tRightExpected);
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);

		tActual = Worksheet2.delete(tActual, 3);
		assertTrue(tActual.equals(tExpected));
		
	}
 
	@Test
	public void Excercise8Test5() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  6
		//expecting back:
		//		5
		//	3		6
		//1	  4   
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(1);
		tLeftExpected = new Tree<Integer> (3, tLeftExpected, new Tree<Integer>(4));
		Tree<Integer> tRightExpected = new Tree<Integer>();
		tRightExpected = new Tree<Integer> (6, tRightExpected, new Tree<Integer>());
		Tree<Integer> tExpected = new Tree<Integer>(5, tLeftExpected, tRightExpected);
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, tRightActual);
		
		//System.out.println("actual before delete: " +tActual);
		//System.out.println("epected: "+ tExpected);
		tActual = Worksheet2.delete(tActual, 8);
		//System.out.println("actual after delete: " +tActual);
		assertTrue(tActual.equals(tExpected));
		
	}
	
	@Test
	public void Excercise9Test1() {
		
		Tree<Integer> test = new Tree<Integer>();
		assertTrue(Worksheet2.isHeightBalanced(test));
		
	}
	
	@Test
	public void Excercise9Test2() {
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer> (3, tActual, new Tree<Integer>(4));
		assertTrue(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	@Test
	public void Excercise9Test3() {
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer> (3, tActual, new Tree<Integer>());
		assertTrue(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	@Test
	public void Excercise9Test4() {
		// providing following tree:
		//		5
		//	3		8
		//1	  4 	  
		//Expecting True
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, new Tree<Integer>(8));
		assertTrue(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	@Test
	public void Excercise9Test5() {
		// providing following tree:
		//		5
		//	3		
		//1	  4 	  
		//Expecting False
		
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tLeftActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>(4));
		Tree<Integer> tActual = new Tree<Integer> (5, tLeftActual, new Tree<Integer>());
		assertFalse(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	@Test
	public void Excercise9Test6() {
		// providing following tree:
		//		5
		//			8
		//   	  6	  9
		//Expecting False
		
		Tree<Integer> tRightActual = new Tree<Integer>(6);
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>(9));
		Tree<Integer> tActual = new Tree<Integer> (5, new Tree<Integer>(), tRightActual);
		assertFalse(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	@Test
	public void Excercise9Test7() {
		// providing following tree:
		//		5
		//	3	   8
		// 1  	  	  9
		//Expecting False
		
		Tree<Integer> tRightActual = new Tree<Integer>();
		tRightActual = new Tree<Integer> (8, tRightActual, new Tree<Integer>(9));
		Tree<Integer> tLeftActual = new Tree<Integer>(1);
		tRightActual = new Tree<Integer> (3, tLeftActual, new Tree<Integer>());
		Tree<Integer> tActual = new Tree<Integer> (5, new Tree<Integer>(), tRightActual);
		assertFalse(Worksheet2.isHeightBalanced(tActual));
		
	}
	
	/*
	@Test
	public void Excercise10InsertTest1() {
		
		Tree<Integer> tExpected = new Tree<Integer>(1);
		assertTrue(tExpected.equals(Worksheet2.insertHB(1, new Tree<Integer>())));
		
	}
	
	@Test
	public void Excercise10InsertTest2() {
		//providing following tree:
		//		2
		//1			3
		//expecting back:
		//		2
		//1			3
		//				4
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(1);
		Tree<Integer> tRightExpected = new Tree<Integer>(4);
		tRightExpected = new Tree<Integer>(3, new Tree<Integer>(), tRightExpected);
		Tree<Integer> tExpected = new Tree<Integer>(2, tLeftExpected, tRightExpected);
		//System.out.println("Expected: \n" + tExpected);
		
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer>(2, tActual, new Tree<Integer>(3));
		assertTrue(Worksheet2.insertHB(4, tActual).equals(tExpected));
		//System.out.println("Actual: \n" + tActual);
		//System.out.println("Actual after: \n" + Worksheet2.insertHB(4, tActual));
		
	}
	
	@Test
	public void Excercise10InsertTest3() {
		//providing following tree:
		//		4
		//	1		5
		//     2
		//expecting back:
		//		4
		//  2		5
		//1	  3			
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(1);
		Tree<Integer> tRightExpected = new Tree<Integer>(4);
		tRightExpected = new Tree<Integer>(3, new Tree<Integer>(), tRightExpected);
		Tree<Integer> tExpected = new Tree<Integer>(2, tLeftExpected, tRightExpected);
		//System.out.println("Expected: \n" + tExpected);
		
		
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer>(2, tActual, new Tree<Integer>(3));
		assertTrue(Worksheet2.insertHB(4, tActual).equals(tExpected));
		//System.out.println("Actual: \n" + tActual);
		//System.out.println("Actual after: \n" + Worksheet2.insertHB(4, tActual));
		
	}
	
	@Test
	public void Excercise10InsertTest4() {
		//providing following tree:
		//		3
		//1			4
		//expecting back:
		//		3
		//	1		4
		//	  2			
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(1, new Tree<Integer>(), new Tree<Integer>(2));
		Tree<Integer> tExpected = new Tree<Integer>(3, tLeftExpected, new Tree<Integer>(4));
			
		Tree<Integer> tActual = new Tree<Integer>(1);
		tActual = new Tree<Integer>(3, tActual, new Tree<Integer>(4));
		assertTrue(Worksheet2.insertHB(2, tActual).equals(tExpected));
		
	}
	
	@Test
	public void Excercise10InsertTest5() {
		//providing following tree:
		//		4
		//	1		5
		//	  2	  	
		//expecting back:
		//		4
		//	2		5
		//1	  3
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(2, new Tree<Integer>(1), new Tree<Integer>(3));
		Tree<Integer> tExpected = new Tree<Integer>(4, tLeftExpected, new Tree<Integer>(5));
			
		Tree<Integer> tActual = new Tree<Integer>(1, new Tree<Integer>(), new Tree<Integer>(2));
		tActual = new Tree<Integer>(4, tActual, new Tree<Integer>(5));
		assertTrue(Worksheet2.insertHB(3, tActual).equals(tExpected));
		
	}
	
	@Test
	public void Excercise10InsertTest6() {
		//providing following tree:
		//		4
		//	3		5
		//1	  	  	
		//expecting back:
		//		4
		//	2		5
		//1	  3
		
		Tree<Integer> tLeftExpected = new Tree<Integer>(2, new Tree<Integer>(1), new Tree<Integer>(3));
		Tree<Integer> tExpected = new Tree<Integer>(4, tLeftExpected, new Tree<Integer>(5));
		System.out.println(tExpected);
			
		Tree<Integer> tActual = new Tree<Integer>(3, new Tree<Integer>(1), new Tree<Integer>());
		tActual = new Tree<Integer>(4, tActual, new Tree<Integer>(5));
		System.out.println(tActual);
		tActual = Worksheet2.insertHB(2, tActual);
		System.out.println(tActual);
		System.out.println(Worksheet2.heightDiff(tActual));
		
	}
	*/
		
}
