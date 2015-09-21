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
public class Subject extends StudentSuper{
	
	private String subjectname;
	private String subjectcategory;
	
	
    public Subject(){
    	super();
    	
    	subjectname = "";
    	subjectcategory = "";
    	
	}


	/**
	 * @return the subjectname
	 */
	public String getSubjectname() {
		return subjectname;
	}

	/**
	 * @param subjectname the subjectname to set
	 */
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	/**
	 * @return the subjectcategory
	 */
	public String getSubjectcategory() {
		return subjectcategory;
	}

	/**
	 * @param sujectcategory the subjectcategory to set
	 */
	public void setSubjectcategory(String subjectcategory) {
		this.subjectcategory = subjectcategory;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("SubjectUi [ getUuid() =");
		builder.append(getUuid());
		builder.append("getSubjectcode()=");
		builder.append(getSubjectcode());
		builder.append(", subjectname =");
		builder.append(subjectname);
		builder.append("subjectcategory=");
		builder.append(subjectcategory);
		builder.append("]");
		return builder.toString(); 
		}
}

