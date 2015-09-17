/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam.results;

import com.yahoo.petermwenda83.contoller.exam.Exam;

/**
 * @author peter
 *
 */
public class FinalResult extends Exam{
	

	/**
	 * 
	 */
	public FinalResult() {
		super();
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Final Exam Results");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid()=");
		builder.append(getUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getPoints()=");
		builder.append(getPoints());
		builder.append(", getGrade()=");
		builder.append(getGrade());
		builder.append(", getPosition()=");
		builder.append(getPosition());
		builder.append(", getRemarks()=");
		builder.append(getRemarks());
		builder.append("]");
		return builder.toString(); 
		}
	
}
