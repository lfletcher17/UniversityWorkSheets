/**
 *  A class for an ExamQuestionSimpleChoice that extends the class ExamQuestion 
 *  with the following additional field variables:
 *  possibleAnswers - a list of possible answers to an ExamQuestionSimpleChoice
 *  correctAnswer - the index of the correct answer to ExamQuestionSimpleChoice within the
 *  	ArrayList possibleAnswers + 1. We add 1 to reflect the position humans would 
 *  	usually start counting from (i.e. element 0 becomes 1).
 *  The method mark() takes a provided answer and compares it with the field variable correctAnswer. 
 *  If these are equal, the maximalMark is returned as an int else 0 is returned.
 * @author lxf736
 * @version 2017-11-15
 */

import java.util.ArrayList;

public class ExamQuestionSimpleChoice extends ExamQuestion {
	
	private ArrayList<String> possibleAnswers;
	private int correctAnswer;

	/** 
	 * @param questionText is the text of the ExamQuestion as a String
	 * @param maximalMark is the maximal mark for a correct answer to the ExamQuestion as an int
	 * @param possibleAnswers are the possibleAnswers to ExamQuestionSimpleChoice as an ArrayList
	 * @param correctAnswer is the correctAnswer index within the ArrayList possibleAnswers + 1
	 */
	public ExamQuestionSimpleChoice(String questionText, int maximalMark, ArrayList<String> possibleAnswers, int correctAnswer) {
		super(questionText, maximalMark);
		this.possibleAnswers = possibleAnswers;
		this.correctAnswer = correctAnswer;
	}
	
	/**
     *  Takes a provided value as an int and compares it with the ExamQuestionSimpleChoice correctAnswer,
     *  if these are equal the ExamQuestionSimpleChoice maximalMark is returned as an int. If they are
     *  not equal 0 is returned.
     *  @param value is the value to be compared with correctAnswer
     *  @return int maximalMark if param value is equal to correctAnswer else return int 0 
     */
	public int mark (int value) {
		if (value == correctAnswer) {
			return this.getMaximalMark();
		} else {
			return 0;
		}
	}
	
	/** 
     *  toString defines how to print an ExamQuestionSimpleChoice
     *  @return  the print type of an ExamQuestionSimpleChoice
     */
	public String toString(){
		return super.toString() + System.lineSeparator() + "Possible answers are: " 
				+ possibleAnswers + System.lineSeparator() +
				"Correct answer position is: " + correctAnswer;
	}

}