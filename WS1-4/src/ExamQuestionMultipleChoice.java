/**
 *  A class for an ExamQuestionMultipleChoice that extends the class ExamQuestion 
 *  with the following additional field variables:
 *  possibleAnswers - a list of possible answers to an ExamQuestionMultipleChoice
 *  correctAnswers - a list of element indexes + 1 from within possibleAnswers. These 
 *  values represent the positions of correct answers to ExamQuestionMultipleChoice. 
 *  We add 1 to reflect the position humans would usually start counting from (i.e. 
 *  element 0 becomes 1). 
 *  The method mark() takes an ArrayList of provided answers and evaluates this to 
 *  determine a mark to return. 
 * @author lxf736
 * @version 2017-11-15
 */

import java.util.ArrayList;

public class ExamQuestionMultipleChoice extends ExamQuestion {
	
	private ArrayList<String> possibleAnswers;
	private ArrayList<Integer> correctAnswers;

	/** 
	 * @param questionText is the text of the ExamQuestion as a String
	 * @param maximalMark is the maximal mark for a correct answer to the ExamQuestion as an int
	 * @param possibleAnswers are the possibleAnswers to ExamQuestionSimpleChoice as an ArrayList
	 * @param correctAnswers are the correctAnswer indexes from the ArrayList possibleAnswers + 1
	 */
	public ExamQuestionMultipleChoice(String questionText, int maximalMark, 
			ArrayList<String> possibleAnswers, ArrayList<Integer> correctAnswers) {
		super(questionText, maximalMark);
		this.possibleAnswers = possibleAnswers;
		this.correctAnswers = correctAnswers;
	}

	/**
     *  Takes a provided ArrayList of answers and evaluates the elements against the ExamQuestionMultipleChoice
     *  correctAnswers field variable. The return value is computed by scaling the difference between the number of 
     *  correct answers and the number of incorrect answers to the maximal points before rounding to the next int.
     *  @param answersProvided is the ArrayList of elements to be evaluated against correctAnswers
     *  @return the scaled mark as an int.
     */
	public int mark(ArrayList<Integer> answersProvided) {
		//initialise ArrayList correctIndex to store correct answers once accounted for
		ArrayList<Integer> correctIndex = new ArrayList<Integer>();
		int correct = 0;
		int incorrect = 0;
		
		for (int i=0; i < answersProvided.size(); i++) {
			/*increment correct counter if the current element being evaluated is a correct answer
			and has not been recorded as correct already. To enable this, correct answers are 
			stored in correctIndex once accounted for*/
			if (correctAnswers.contains(answersProvided.get(i)) && !correctIndex.contains(answersProvided.get(i))) {
				correct ++;
				correctIndex.add(answersProvided.get(i));
			/*only increment incorrect counter if the element is truly incorrect - i.e. it has not already been
			 recorded as correct*/
			} else if (!correctIndex.contains(answersProvided.get(i))) {
				incorrect ++;
			}
		}
		
		//determine mark to return based off of above evaluation of answersProvided.
		if (correct == correctAnswers.size() && incorrect == 0) {
			return this.getMaximalMark();
		} else if (!(incorrect >= correct) || incorrect == 0 ) {
			double proportionalMark = (double) this.getMaximalMark() / this.correctAnswers.size();
			double mark = (correct * proportionalMark) - (incorrect * proportionalMark);
			return (int) Math.round(mark);
		} else {
			return 0;
		}
	}
	
	/** 
     *  toString defines how to print an ExamQuestionMultipleChoice
     *  @return  the print type of an ExamQuestionMultipleChoice
     */
	public String toString(){
		return super.toString() + System.lineSeparator() + "Possible answers are: " 
				+ possibleAnswers + System.lineSeparator() +
				"Correct answer positions are: " + correctAnswers;
	}

}