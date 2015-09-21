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
package com.yahoo.petermwenda83.contoller.exam.cat;

import com.yahoo.petermwenda83.contoller.exam.Exam;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class CatResults extends Exam  {
	
	public CatResults(){
		super();
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Cat Exam results");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getSubjectUuid()=");
		builder.append(getSubjectUuid());
		builder.append(", getStudentUuid()=");
		builder.append(getStudentUuid());
		builder.append(", getTotal()=");
		builder.append(getTotal());
		builder.append(", getPoints()=");
		builder.append(getPoints());
		builder.append(", getGrade()=");
		builder.append(getGrade());
		builder.append(", getPosition()=");
		builder.append(getPosition());
		builder.append(", getRemarks()=");
		builder.append(getRemarks());
		builder.append("]");
		return builder.toString(); 
		}
}
