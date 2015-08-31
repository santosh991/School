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
package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.student.StudentSuper;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentRelative extends StudentSuper{

	private String relativeName;
	private String relativePhone;
	private String nationalID;
	//private String studentUuid; 

	
	public StudentRelative() {
		super();
		relativeName = "";
		relativePhone = "";
		nationalID = "";
		//studentUuid = "";
		
	}

	
	/**
	 * @return the relativeName
	 */
	public String getRelativeName() {
		return relativeName;
	}


	/**
	 * @param relativeName the relativeName to set
	 */
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}


	/**
	 * @return the relativePhone
	 */
	public String getRelativePhone() {
		return relativePhone;
	}


	/**
	 * @param relativePhone the relativePhone to set
	 */
	public void setRelativePhone(String relativePhone) {
		this.relativePhone = relativePhone;
	}


	/**
	 * @return the nationalID
	 */
	public String getNationalID() {
		return nationalID;
	}


	/**
	 * @param nationalID the nationalID to set
	 */
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}


	/**
	 * @return the studentUuid
	 */
	public String getStudentsUuid() {
		return getStudentUuid();
	}



	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentsUuid(String studentUuid) {
		setStudentUuid(studentUuid);
	}



	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentRelative");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", relativeName = ");
		builder.append(relativeName);
		builder.append(", relativePhone =");
		builder.append(relativePhone);
		builder.append(", nationalID =");
		builder.append(nationalID);
		builder.append(",getStudentsUuid() =");
		builder.append(getStudentUuid());
		builder.append("]");
		return builder.toString(); 
		}
		
}
