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
public class StudentSubject extends StudentSuper{
	   public StudentSubject(){
		   super();
	   }

	@Override
	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("StudentSubject [ getUuid() =");
	builder.append(getUuid());
	builder.append(", getStudentUuid() =");
	builder.append(getStudentUuid());
	builder.append(", getSubjectUuid() =");
	builder.append(getSubjectUuid());
	builder.append(", getRoomnameUuid() =");
	builder.append(getRoomnameUuid());
	
	builder.append("]");
	return builder.toString(); 
	}
}
