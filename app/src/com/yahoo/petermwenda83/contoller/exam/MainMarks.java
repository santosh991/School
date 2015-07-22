/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam;

/**
 * @author peter
 *
 */
public class MainMarks extends Exam  {

	/**
	 * 
	 */
	public MainMarks() {
		super();
	}
	/**
	 * @return the mainMarksUuid
	 */
	public String getMainMarksUuid() {
		return getExamUuid();
	}
	/**
	 * @param mainMarksUuid the mainMarksUuid to set
	 */
	public void setMainMarksUuid(String mainMarksUuid) {
		setExamUuid(mainMarksUuid);
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MainMarks [getUuid()=");
		builder.append(getUuid());
		builder.append(", getSubjectUuid()=");
		builder.append(getSubjectUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getExamTypeUuid()=");
		builder.append(getExamTypeUuid());
		builder.append(", getSubjectcode()=");//
		builder.append(getSubjectcode());
		builder.append(", getSubmitdate()=");
		builder.append(getSubjectcode());
		builder.append(", getMainMarksUuid()=");
		builder.append(getMainMarksUuid());
		builder.append(", getMarks()=");
		builder.append(getMarks());
		builder.append(", getSubmitdate()=");
		builder.append(getSubmitdate());
		builder.append("]");
		return builder.toString();
	}

}
