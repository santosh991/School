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
	    /**
	 * 
	 */
	private static final long serialVersionUID = 5531058462201649828L;
		private String firstname;
		private String lastname;
		private String surname;
		private String admno;
		private String gender;
		private String form;
		private String year;		
		private String DOB;
		private String bcertno;
		private Date RegDate;
		private String ClassRoomUuid;
		private String subjectUuid;
		private String StudentUuid;
		private String subjectcode;
		private String SchoolAccountUuid;
		private String SysUser;
		
	
	public StudentSuper() {
		firstname = "";
		lastname = "";
		surname = "";
		admno = "";
		gender ="";
		form = "";
		year = "";
		DOB = "";
		bcertno = "";
		RegDate = new Date();
		ClassRoomUuid = "";
		subjectUuid = "";
		StudentUuid = "";
		subjectcode = "";
		SchoolAccountUuid = "";
		SysUser = "";
		
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the regDate
	 */
	public Date getRegDate() {
		return RegDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}

	/**
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return ClassRoomUuid;
	}

	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		ClassRoomUuid = classRoomUuid;
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
		return StudentUuid;
	}

	/**
	 * @param studenttUuid the studenttUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		StudentUuid = studentUuid;
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

	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return SchoolAccountUuid;
	}

	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		SchoolAccountUuid = schoolAccountUuid;
	}

	/**
	 * @return the sysUser
	 */
	public String getSysUser() {
		return SysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(String sysUser) {
		SysUser = sysUser;
	}

	
	
} 
