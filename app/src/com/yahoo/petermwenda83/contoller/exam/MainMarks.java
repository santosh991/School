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
package com.yahoo.petermwenda83.contoller.exam;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class MainMarks extends Exam  {

	/**
	 * 
	 */
	public MainMarks() {
		super();
	}
	/**
	 * @return the mainMarksUuid
	 */
	public String getMainMarksUuid() {
		return getExamUuid();
	}
	/**
	 * @param mainMarksUuid the mainMarksUuid to set
	 */
	public void setMainMarksUuid(String mainMarksUuid) {
		setExamUuid(mainMarksUuid);
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Main Exam Subject Marks [getUuid()=");
		builder.append(getUuid());
		builder.append(", getSubjectUuid()=");
		builder.append(getSubjectUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getExamTypeUuid()=");
		builder.append(getExamTypeUuid());
		builder.append(", getMarks()=");
		builder.append(getMarks());
		builder.append(", getPercent()=");
		builder.append(getPercent());
		builder.append(", getGrade()=");
		builder.append(getGrade());
		builder.append(", getSubmitdate()=");
		builder.append(getSubmitdate());
		builder.append("]");
		return builder.toString();
	}

}
