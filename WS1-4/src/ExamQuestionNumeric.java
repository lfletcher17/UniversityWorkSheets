/**
 *  A class for an ExamQuestionNumeric that extends the class ExamQuestion 
 *  with the following additional field variable:
 *  answer - the answer to the ExamQuestionNumeric
 *  The method mark() takes a provided answer and compares it with the field variable answer. 
 *  If these are equal, the maximalMark is returned as an int else 0 is returned..
 * @author lxf736
 * @version 2017-11-15
 */

public class ExamQuestionNumeric extends ExamQuestion {
	
	private int answer;

	/** 
	 * @param questionText is the text of the ExamQuestion as a String
	 * @param maximalMark is the maximal mark for a correct answer to the ExamQuestion as an int
	 * @param answer is the answer to ExamQuestionNumeric as an int
	 */
	public ExamQuestionNumeric(String questionText, int maximalMark, int answer) {
		super(questionText, maximalMark);
		this.answer = answer;
	}
	
	/**
     *  Takes a provided value as an int and compares it with the ExamQuestionNumeric answer,
     *  if these are equal the ExamQuestionNumeric maximalMark is returned as an int. If they are
     *  not equal 0 is returned.
     *  @param value is the value to be compared with answer
     *  @return int maximaMark if param value is equal to answer else return int 0 
     */
	public int mark (int value) {
		if (value == answer) {
			return this.getMaximalMark();
		} else {
			return 0;
		}
	}
	
	/** 
     *  toString defines how to print an ExamQuestionNumeric
     *  @return  the print type of an ExamQuestionNumeric
     */
	public String toString(){
		return super.toString() + " Correct answer is: " + answer;
	}
	
}
