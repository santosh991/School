/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import java.util.Date;


/**
 * @author peter
 *
 */
public class Student extends StudentSuper  {

	/**
	 * 
	 */
	public Student() {
		super();
	}
	
	
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return getFirstname();
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		setFirstname(firstname); 
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return getLastname();
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		setLastname(lastname);
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return getSurname();
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		setSurname(surname);
	}

	/**
	 * @return the admno
	 */
	public String getAdmno() {
		return getAdmno();
	}

	/**
	 * @param admno the admno to set
	 */
	public void setAdmno(String admno) {
		setAdmno(admno);
	}

	/**
	 * @return the form
	 */
	public String getForm() {
		return getForm();
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(String form) {
		setForm(form);
	}

	/**
	 * @return the clasz
	 */
	public String getClasz() {
		return getClasz();
	}

	/**
	 * @param clasz the clasz to set
	 */
	public void setClasz(String clasz) {
		setClasz(clasz);
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return getYear();
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		setYear(year);
	}

	/**
	 * @return the dOB
	 */
	public String getDOB() {
		return getDOB();
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(String dOB) {
		setDOB(dOB);
	}

	/**
	 * @return the bcertno
	 */
	public String getBcertno() {
		return getBcertno();
	}

	/**
	 * @param bcertno the bcertno to set
	 */
	public void setBcertno(String bcertno) {
		setBcertno(bcertno);
	}

	/**
	 * @return the admissiondate
	 */
	public Date getAdmissiondate() {
		return getAdmissiondate();
	}

	/**
	 * @param admissiondate the admissiondate to set
	 */
	public void setAdmissiondate(Date admissiondate) {
		setAdmissiondate(admissiondate);
	}
	
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Students [ getUuid() =");
		builder.append(getUuid());
		builder.append(",getFirstname() =");
		builder.append(getFirstname());
		builder.append(",getLastname() =");
		builder.append(getLastname());
		builder.append(",getSurname() =");
		builder.append(getSurname());
		builder.append(",getAdmno() =");
		builder.append(getAdmno());
		builder.append(",getForm() =");
		builder.append(getForm());
		builder.append(",getYear() =");
		builder.append(getYear());
		builder.append(",getClasz() =");
		builder.append(getClasz());
		builder.append(",getDOB() =");
		builder.append(getDOB());
		builder.append(",getBcertno() =");
		builder.append(getBcertno()); 
		builder.append(",getAdmissiondate() =");
		builder.append(getAdmissiondate());
		builder.append("]");
		return builder.toString(); 
		}


}
