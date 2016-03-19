/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class ClosingBalance extends StorableBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567171203936746871L;
	
	String schoolAccountUuid = "";
	String studentUuid = "";
	String term = "";
	String year = "";
    double closingAmount = 0;

	/**
	 * 
	 */
	public ClosingBalance() {
		
		schoolAccountUuid = "";
		studentUuid = "";
		term = "";
		year = "";
		closingAmount = 0.0;
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
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return studentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
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
	 * @return the closingAmount
	 */
	public double getClosingAmount() {
		return closingAmount;
	}


	/**
	 * @param closingAmount the closingAmount to set
	 */
	public void setClosingAmount(double closingAmount) {
		this.closingAmount = closingAmount;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Closing Balance");
		builder.append("[getUuid() = ");
		builder.append(getUuid());
		builder.append(", schoolAccountUuid =");
		builder.append(schoolAccountUuid);
		builder.append(", studentUuid =");
		builder.append(studentUuid);
		builder.append(", term =");
		builder.append(term);
		builder.append(", year =");
		builder.append(year);
		builder.append(", closingAmount =");
		builder.append(closingAmount);
		builder.append("]");
		return builder.toString(); 
		}

}
