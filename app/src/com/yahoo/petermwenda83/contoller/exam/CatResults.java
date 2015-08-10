/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class CatResults extends Exam  {
	private String CatResultsUuid;
	/**
	 * 
	 */
	public CatResults() {
		CatResultsUuid = "";
	}
	/**
	 * @return the catResultsUuid
	 */
	public String getCatResultsUuid() {
		return CatResultsUuid;
	}
	/**
	 * @param catResultsUuid the catResultsUuid to set
	 */
	public void setCatResultsUuid(String catResultsUuid) {
		CatResultsUuid = catResultsUuid;
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
		builder.append(", CatResultsUuid =");
		builder.append("CatResultsUuid");
		builder.append("]");
		return builder.toString(); 
		}
}
