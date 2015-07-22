/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam;


/**
 * @author peter
 *
 */
public class ExamType extends Exam{

	private String examtype ;//CAT or MAIN
	private String year ;
	private String term ;
	private String clasz ;
	private String description ; //CAT1,CAT2,CAT3 ,MAIN1,MOCK1 etc
	/**
	 * 
	 */
	public ExamType() {
          super();
		
		examtype = "";
		term = "";
		year = "";
		clasz = "";
		description = "";
	}
	
	/**
	 * @return the examtype
	 */
	public String getExamtype() {
		return examtype;
	}

	/**
	 * @param examtype the examtype to set
	 */
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return the clasz
	 */
	public String getClasz() {
		return clasz;
	}

	/**
	 * @param clasz the clasz to set
	 */
	public void setClasz(String clasz) {
		this.clasz = clasz;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exam Type");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", examtype =");
		builder.append("examtype");
		builder.append(", term =");
		builder.append("term");
		builder.append(", year =");
		builder.append("year");
		builder.append(", clasz =");
		builder.append("clasz");
		builder.append(", description =");
		builder.append("description");
		builder.append("]");
		return builder.toString(); 
		}

}
