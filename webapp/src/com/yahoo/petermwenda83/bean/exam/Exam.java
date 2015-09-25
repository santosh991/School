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
package com.yahoo.petermwenda83.bean.exam;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Exam extends AllBean{


	private String ExamUuid;
	private String ExamName;

	
	
	/**
	 * 
	 */
	protected Exam() {
		super();
		ExamName ="";
	}
	
	
	/**
	 * @return the examUuid
	 */
	public String getExamUuid() {
		return ExamUuid;
	}


	/**
	 * @param examUuid the examUuid to set
	 */
	public void setExamUuid(String examUuid) {
		ExamUuid = examUuid;
	}

	/**
	 * @return the examName
	 */
	public String getExamName() {
		return ExamName;
	}


	/**
	 * @param examName the examName to set
	 */
	public void setExamName(String examName) {
		ExamName = examName;
	}


	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exam [ getUuid() =");
		builder.append(getUuid());
		builder.append(", ExamUuid=");
		builder.append(ExamUuid);
		builder.append("]");
		return builder.toString(); 
		}
	
}
