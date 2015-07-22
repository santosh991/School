/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam;


/**
 * @author peter
 *
 */
public class MainResults extends Exam {
   
	private String MainMarksUuid;
	
	/**
	 * 
	 */
	public MainResults() {
		super();
		MainMarksUuid = "";
	}
 
	
	/**
	 * @return the mainMarksUuid
	 */
	public String getMainMarksUuid() {
		return MainMarksUuid;
	}


	/**
	 * @param mainMarksUuid the mainMarksUuid to set
	 */
	public void setMainMarksUuid(String mainMarksUuid) {
		MainMarksUuid = mainMarksUuid;
	}


	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exam Totals");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getSubjectUuid()=");
		builder.append(getSubjectUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getExamTypeUuid()=");
		builder.append(getExamTypeUuid());
		builder.append(", getTotal()=");
		builder.append(getTotal());
		builder.append(", getPoints()=");
		builder.append(getPoints());
		builder.append(", getGrade()=");
		builder.append(getGrade());
		builder.append(", getPosition()=");
		builder.append(getPosition());
		builder.append(", getRemarks()=");
		builder.append(getRemarks());
		builder.append(", getSubmitdate()=");
		builder.append(getSubmitdate());
		builder.append(", MainMarksUuid =");
		builder.append("MainMarksUuid");
		builder.append("]");
		return builder.toString(); 
		}
}
