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
	private int outof ;
	private String description ; //CAT1,CAT2,CAT3 ,MAIN1,MOCK1 etc
	private String examno;
	/**
	 * 
	 */
	public ExamType() {
          super();
		
		examtype = "";
		term = "";
		year = "";
		clasz = "";
		outof = 0;
		description = "";
		examno = "";
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
	 * @return the outof
	 */
	public int getOutof() {
		return outof;
	}

	/**
	 * @param outof the outof to set
	 */
	public void setOutof(int outof) {
		this.outof = outof;
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
	

	/**
	 * @return the examno
	 */
	public String getExamno() {
		return examno;
	}

	/**
	 * @param examno the examno to set
	 */
	public void setExamno(String examno) {
		this.examno = examno;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exam Type");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", examtype =");
		builder.append(examtype);
		builder.append(", term =");
		builder.append(term);
		builder.append(", year =");
		builder.append(year);
		builder.append(", clasz =");
		builder.append(clasz);
		builder.append(", outof =");
		builder.append(outof);
		builder.append(", description =");
		builder.append(description);
		builder.append(", examno =");
		builder.append(examno);
		builder.append("]");
		return builder.toString(); 
		}

}
