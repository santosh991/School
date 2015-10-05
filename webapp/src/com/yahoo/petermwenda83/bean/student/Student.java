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

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Student extends StudentSuper  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7544635242162355411L;


	/**
	 * 
	 */
	public Student() {
		super();
	}
	
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Students[ id = ");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(",getFirstname() =");
		builder.append(getFirstname());
		builder.append(",getLastname() =");
		builder.append(getLastname());
		builder.append(",getSurname() =");
		builder.append(getSurname());
		builder.append(",getAdmno() =");
		builder.append(getAdmno());
		builder.append(",getGender() =");
		builder.append(getGender());
		builder.append(",getYear() =");
		builder.append(getYear());
		builder.append(",getDOB() =");
		builder.append(getDOB());
		builder.append(",getBcertno() =");
		builder.append(getBcertno()); 
		builder.append(",getRegDate() =");
		builder.append(getRegDate());
		builder.append(", getSysUser() =");
		builder.append(getSysUser());
		builder.append(",getSchoolAccountUuid() =");
		builder.append(getSchoolAccountUuid());
		builder.append("]");
		return builder.toString(); 
		}


}
