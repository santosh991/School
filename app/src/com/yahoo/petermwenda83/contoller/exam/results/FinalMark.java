/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam.results;

import com.yahoo.petermwenda83.contoller.exam.Exam;

/**
 * @author peter
 *
 */
public class FinalMark extends Exam {

	/**
	 * 
	 */
	public FinalMark() {
		super();
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Final Exam Marks");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid()=");
		builder.append(getUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getMarks()=");
		builder.append(getMarks());
		builder.append(", getGrade()=");
		builder.append(getGrade());
		builder.append("]");
		return builder.toString(); 
		}
	
}
