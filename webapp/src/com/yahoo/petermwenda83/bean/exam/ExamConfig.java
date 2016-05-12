/**
 * 
 */
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class ExamConfig extends StorableBean{
	
	/**
	 * 
	 */
	
	private String schoolAccountUuid;
	private String Term;
	private String Year;
	private double termAmount;
	private String Exam;
	private String ExamMode;

	/**
	 * 
	 */
	public ExamConfig() {
		schoolAccountUuid = "";
		Term = "";
		Year = "";
		termAmount = 0;
		Exam = "";
		ExamMode = "";
	}

	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return schoolAccountUuid;
	}

	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		this.schoolAccountUuid = schoolAccountUuid;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return Term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		Term = term;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return Year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		Year = year;
	}

	/**
	 * @return the termAmount
	 */
	public double getTermAmount() {
		return termAmount;
	}

	/**
	 * @param termAmount the termAmount to set
	 */
	public void setTermAmount(double termAmount) {
		this.termAmount = termAmount;
	}

	/**
	 * @return the exam
	 */
	public String getExam() {
		return Exam;
	}

	/**
	 * @param exam the exam to set
	 */
	public void setExam(String exam) {
		Exam = exam;
	}

	/**
	 * @return the examMode
	 */
	public String getExamMode() {
		return ExamMode;
	}

	/**
	 * @param examMode the examMode to set
	 */
	public void setExamMode(String examMode) {
		ExamMode = examMode;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("ExamConfigDAO [ getUuid() = ");
		builder.append(getUuid());
		builder.append(", schoolAccountUuid=");
		builder.append(schoolAccountUuid);
		builder.append(", Term=");
		builder.append(Term);//termAmount
		builder.append(", Year=");
		builder.append(Year);
		builder.append(", termAmount=");
		builder.append(termAmount);
		builder.append(", Exam=");
		builder.append(Exam);
		builder.append(", ExamMode=");
		builder.append(ExamMode);
		builder.append("]");
		return builder.toString(); 
		}
	
	
	private static final long serialVersionUID = -2906872526805032876L;
}
