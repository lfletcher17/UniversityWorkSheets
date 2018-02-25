import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class ExamQuestionTest {
	
	private ExamQuestion question1;
	private ExamQuestionNumeric numericQuestion1;
	private ExamQuestionSimpleChoice simpleChoiceQuestion1, simpleChoiceQuestion2;
	private ExamQuestionMultipleChoice multipleChoiceQuestion1;
	
	@Before
	public void setup(){
		question1 = new ExamQuestion("x*5 = 25?", 10);
		numericQuestion1 = new ExamQuestionNumeric("x*5 = 25?", 10, 5);
		ArrayList<String> simpleChoiceAnswers1 = new ArrayList<String>(Arrays.asList("4","5","10","20"));
		simpleChoiceQuestion1 = new ExamQuestionSimpleChoice("2+3 = ?", 10, simpleChoiceAnswers1, 2);
		ArrayList<String> simpleChoiceAnswers2 = new ArrayList<String>(Arrays.asList("5","2","7","10","40","-3"));
		simpleChoiceQuestion2 = new ExamQuestionSimpleChoice("x*5 = 25?", 10, simpleChoiceAnswers2, 1);
		ArrayList<String> possibleAnswers1 = new ArrayList<>(Arrays.asList("-2","0","2","3"));
		ArrayList<Integer> correctAnswers1 = new ArrayList<>(Arrays.asList(1, 3));
		multipleChoiceQuestion1 = new ExamQuestionMultipleChoice("x * x = 4", 10, possibleAnswers1, correctAnswers1);
	}
	
	@Test
	public void test1() {
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 3));
		
		int expected = 10;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1));
		
		int expected = 5;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 2, 3));
		
		int expected = 5;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 2));
		
		int expected = 0;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		ArrayList<Integer> givenAnswers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		
		int expected = 0;
		int actual = multipleChoiceQuestion1.mark(givenAnswers);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test6() {
		
		assertEquals("Question (Maximal mark: 10): 2+3 = ?\n" + 
				"Possible answers are: [4, 5, 10, 20]\n" + 
				"Correct answer position is: 2", simpleChoiceQuestion1.toString());
	}
	
	@Test
	public void test7() {
		int expected1 = 10;
		int actual1 = simpleChoiceQuestion2.mark(1);
		int expected2 = 0;
		int actual2 = simpleChoiceQuestion2.mark(4);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void test8() {
		
		assertEquals("Question (Maximal mark: 10): x*5 = 25? Correct answer is: 5", 
				numericQuestion1.toString());
		
	}
	
	@Test
	public void test9() {
		int expected1 = 10;
		int actual1 = numericQuestion1.mark(5);
		int expected2 = 0;
		int actual2 = numericQuestion1.mark(4);
		
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void test10() {
		int actual1 =  question1.getMaximalMark();
        int expected1 = 10;
        String actual2 = question1.getQuestionText();
        String expected2 = "x*5 = 25?";

        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
	}


}