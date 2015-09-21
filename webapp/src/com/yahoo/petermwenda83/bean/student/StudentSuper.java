/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.bean.student;

import java.util.Date;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentSuper extends AllBean{
	    private String firstname;
		private String lastname;
		private String surname;
		private String admno;
		private String form;
		private String year;		
		private String DOB;
		private String bcertno;
		private Date admissiondate;
		private String roomnameUuid;
		private String subjectUuid;
		private String StudenttUuid;
		private String subjectcode;
		
		
	
	public StudentSuper() {
		firstname = "";
		lastname = "";
		surname = "";
		admno = "";
		form = "";
		year = "";
		DOB = "";
		bcertno = "";
		admissiondate = new Date();
		roomnameUuid = "";
		subjectUuid = "";
		StudenttUuid = "";
		subjectcode = "";
		
		
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


	/**
	 * @return the roomnameUuid
	 */
	public String getRoomnameUuid() {
		return roomnameUuid;
	}

	/**
	 * @param roomnameUuid the roomnameUuid to set
	 */
	public void setRoomnameUuid(String roomnameUuid) {
		this.roomnameUuid = roomnameUuid;
	}

	/**
	 * @return the subjectUuid
	 */
	public String getSubjectUuid() {
		return subjectUuid;
	}

	/**
	 * @param subjectUuid the subjectUuid to set
	 */
	public void setSubjectUuid(String subjectUuid) {
		this.subjectUuid = subjectUuid;
	}

	/**
	 * @return the studenttUuid
	 */
	public String getStudentUuid() {
		return StudenttUuid;
	}

	/**
	 * @param studenttUuid the studenttUuid to set
	 */
	public void setStudentUuid(String studenttUuid) {
		StudenttUuid = studenttUuid;
	}

	/**
	 * @return the subjectcode
	 */
	public String getSubjectcode() {
		return subjectcode;
	}

	/**
	 * @param subjectcode the subjectcode to set
	 */
	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	
	
} 
