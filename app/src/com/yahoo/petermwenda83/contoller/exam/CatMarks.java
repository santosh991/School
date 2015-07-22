package com.yahoo.petermwenda83.contoller.exam;

public class CatMarks extends Exam  {

	public CatMarks() {
		super();
		
	}
	/**
	 * @return the catMarksUuid
	 */
	public String getCatMarksUuid() {
		return getExamUuid();
	}
	/**
	 * @param catMarksUuid the catMarksUuid to set
	 */
	public void setCatMarksUuid(String catMarksUuid) {
		setExamUuid(catMarksUuid);
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatMarks [getUuid()=");
		builder.append(getUuid());
		builder.append(", getSubjectUuid()=");
		builder.append(getSubjectUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getExamTypeUuid()=");
		builder.append(getExamTypeUuid());
		builder.append(", getSubjectcode()=");
		builder.append(getSubjectcode());
		builder.append(", getSubmitdate()=");
		builder.append(getSubmitdate());
		builder.append(", getCatMarksUuid()=");
		builder.append(getCatMarksUuid());
		builder.append(", getSubmitdate()=");
		builder.append(getSubmitdate());
		builder.append("]");
		return builder.toString();
		}

}
