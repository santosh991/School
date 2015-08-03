package com.yahoo.petermwenda83.contoller.student;

import java.util.Date;

import com.yahoo.petermwenda83.contoller.AllBean;


public class StudentSuper extends AllBean{
	    private String firstname;
		private String lastname;
		private String surname;
		private String admno;
		private String form;
		private String clasz;
		private int year;		
		private String DOB;
		private String bcertno;
		private Date admissiondate;
	
	public StudentSuper() {
		firstname = "";;
		lastname = "";
		surname = "";
		admno = "";
		form = "";
		clasz = "";
		year = 0;
		DOB = "";
		bcertno = "";
		admissiondate = new Date();
		
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the admno
	 */
	public String getAdmno() {
		return admno;
	}

	/**
	 * @param admno the admno to set
	 */
	public void setAdmno(String admno) {
		this.admno = admno;
	}

	/**
	 * @return the form
	 */
	public String getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(String form) {
		this.form = form;
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
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the dOB
	 */
	public String getDOB() {
		return DOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(String dOB) {
		DOB = dOB;
	}

	/**
	 * @return the bcertno
	 */
	public String getBcertno() {
		return bcertno;
	}

	/**
	 * @param bcertno the bcertno to set
	 */
	public void setBcertno(String bcertno) {
		this.bcertno = bcertno;
	}

	/**
	 * @return the admissiondate
	 */
	public Date getAdmissiondate() {
		return admissiondate;
	}

	/**
	 * @param admissiondate the admissiondate to set
	 */
	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
	}
	 
	
} 
