/**
 * A class for an ExamQuestion created from an ExamQuestion's questionText and maximalMark.
 * ExamQuestion consists of the following field variables:
 * 	questionTest - the text of the ExamQuestion
 *	maximalMark - the maximal mark available for a correct answer
 * @author lxf736
 * @version 2017-11-15
 */

public class ExamQuestion {
	
	private String questionText;
	private int maximalMark;

	/** 
	 * @param questionText is the text of the ExamQuestion as a String
	 * @param maximalMark is the maximal mark for a correct answer to the ExamQuestion as an int
	 */
	public ExamQuestion(String questionText, int maximalMark) {
		this.questionText = questionText;
		this.maximalMark = maximalMark;
	}

	/** 
     *  @return the questionText of ExamQuestion as String
     */
	public String getQuestionText() {
		return questionText;
	}

	/**
     *  sets the questionText of ExamQuestion
     *  @param  questionText for the changed questionText value
     */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/** 
     *  @return the maximalMark of ExamQuestion as int
     */
	public int getMaximalMark() {
		return maximalMark;
	}

	/**
     *  sets the maximalMark of ExamQuestion
     *  @param  maximalMark for the changed maximalMark value
     */
	public void setMaximalMark(int maximalMark) {
		this.maximalMark = maximalMark;
	}

    /** 
     *  toString defines how to print an ExamQuestion
     *  @return  the print type of an ExamQuestion
     */
	public String toString() {
		return "Question (Maximal mark: " + maximalMark + "): " + questionText;
	}
}
