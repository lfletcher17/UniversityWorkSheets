import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alexandros Evangelidis and David McDonald
 *
 */
public class Ws4PublicTestsEx4 {
	
        private ExamQuestion question1, question2;
        private ExamQuestionNumeric numericQuestion1,numericQuestion2,numericQuestion3;
	private ExamQuestionSimpleChoice simpleChoiceQuestion1,simpleChoiceQuestion2,simpleChoiceQuestion3;
	private ExamQuestionMultipleChoice multipleChoiceQuestion1,multipleChoiceQuestion2;
	
	@Before
	public void setup(){
		question1 = new ExamQuestion("What is 5 times 3?", 10);
		question2 = new ExamQuestion("What is 1 plus 1?", 100);
		
		numericQuestion1 = new ExamQuestionNumeric("What is 5 times 3?", 10, 15);
		numericQuestion2 = new ExamQuestionNumeric("What is 1 plus 1?", 100, 2);
		numericQuestion3 = new ExamQuestionNumeric("What is 2 minus 1?", 200, 1);
		
		ArrayList<String> simpleChoiceAnswers1 = new ArrayList<String>(Arrays.asList("4","5","10","20"));
		simpleChoiceQuestion1 = new ExamQuestionSimpleChoice("What is 3 plus 2?", 10, simpleChoiceAnswers1, 2);
		
		ArrayList<String> simpleChoiceAnswers2 = new ArrayList<String>(Arrays.asList("1","2","3","4","5","-1"));
		simpleChoiceQuestion2 = new ExamQuestionSimpleChoice("What is 2 plus 3?", 100, simpleChoiceAnswers2, 5);
		
		ArrayList<String> simpleChoiceAnswers3 = new ArrayList<String>(Arrays.asList("Melbourne","Canberra","Sydney","Adelaide"));
		simpleChoiceQuestion3 = new ExamQuestionSimpleChoice("Which is the capital of Australia ?", 100, simpleChoiceAnswers3, 2);
		
		ArrayList<String> possibleAnswers1 = new ArrayList<>(Arrays.asList("-2","0","1","2"));
		ArrayList<Integer> correctAnswers1 = new ArrayList<>(Arrays.asList(1, 4));
		multipleChoiceQuestion1 = new ExamQuestionMultipleChoice("x * x = 4", 10, possibleAnswers1, correctAnswers1);
		
		ArrayList<String> possibleAnswers2 = new ArrayList<>(Arrays.asList("0","-1","1","2"));
		ArrayList<Integer> correctAnswers2 = new ArrayList<>(Arrays.asList(1,3, 4));
		multipleChoiceQuestion2 = new ExamQuestionMultipleChoice("x*x*x-3*x*x+2*x = 0", 10, possibleAnswers2, correctAnswers2);
	}

	//public test
        @Test
        public void test1() {
                int actual1 =  question1.getMaximalMark();
                int actual2 =  question2.getMaximalMark();

                int expected1 = 10;
                int expected2 = 100;

                assertEquals(expected1,actual1);
                assertEquals(expected2,actual2);

                String actual3 = question1.getQuestionText();
                String expected3 = "What is 5 times 3?";

                String actual4 = question2.getQuestionText();
                String expected4 = "What is 1 plus 1?";

                assertEquals(expected3,actual3);
                assertEquals(expected4,actual4);

        }
                
	//public test
        @Test
	public void test2(){
		
		int expected1 = 10;
		int actual1 = numericQuestion1.mark(15);
		
		int expected2 = 100;
		int actual2 = numericQuestion2.mark(2);
		
		int expected3 = 200;
		int actual3 = numericQuestion3.mark(1);
		
		int expected4 = 0;
		int actual4 = numericQuestion3.mark(100);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		
	}
	
	//public test
	@Test
	public void test3() {
		
		int expected = 0;
		int actual = numericQuestion1.mark(16);
		
		assertEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test4() {
		
		int expected1 = 10;
		int actual1 = simpleChoiceQuestion1.mark(2);
		int expected2 = 100;
		int actual2 = simpleChoiceQuestion2.mark(5);
		
		int expected3 = 100;
		int actual3 = simpleChoiceQuestion3.mark(2);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		
	}
	
	//public test
	@Test
	public void test5() {
		
		int expected = 0;
		int actual = simpleChoiceQuestion1.mark(3);
		
		assertEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test6() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 4));
		
		int expected = 10;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test7() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 3));
		
		int expected = 0;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}

    	//public test
	@Test
	public void test8() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 1));
		
		int expected = 5;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}

	//public test
	@Test
	public void test9() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 3, 4));
		
		int expected = 5;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	//public test
	@Test
	public void test10() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(2, 3));
		
		int expected = 0;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}

	//public test
	@Test
	public void test11(){
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1,3,4));
		
		int expected = 10;
		int actual = multipleChoiceQuestion2.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	

	//public test
	@Test
	public void test12() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1,2,3,4));
		
		int expected = 7;
		int actual = multipleChoiceQuestion2.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	

	//public test
	@Test
	public void test13() {
		
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(2));
		
		int expected = 0;
		int actual = multipleChoiceQuestion2.mark(givenAnswers);
		
		assertEquals(expected, actual);
		
	}

	//public test
	@Test
	public void test14() {
		
                ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(2, 1, 4, 3));
		
		int expected = 7;
		int actual = multipleChoiceQuestion2.mark(givenAnswers);
		
		assertEquals(expected, actual);
		
	}
    
}
