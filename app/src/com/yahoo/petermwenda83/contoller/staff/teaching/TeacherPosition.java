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
package com.yahoo.petermwenda83.contoller.staff.teaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TeacherPosition  extends Employees {
	/**
	 * 
	 */
	public TeacherPosition() {
		super();

	}

	

	/**
	 * @return the teacherUuid
	 */
	public String getTeacherUuid() {
		return getEmployeeUuid();
	}

	/**
	 * @param teacherUuid the teacherUuid to set
	 */
	public void setTeacherUuid(String teacherUuid) {
		setEmployeeUuid(teacherUuid);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Teacher Position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(", getTeacherUuid() =");
		builder.append(getTeacherUuid());
		builder.append(", getPosition() =");
		builder.append(getPosition());
		builder.append(", getSalary() =");
		builder.append(getSalary());
		builder.append("]"); 
		return builder.toString();
	}
}
