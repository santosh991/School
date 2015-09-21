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
public class ExamType extends Exam{

	private String examuuid;
	private String examname;
	private String roomnameuuid;
	private String term;
	private String year;
	private int outof;
	
	/**
	 * 
	 */
	public ExamType() {
          super();
		
        examuuid = "";
        examname ="";
        roomnameuuid = "";
        term = "";
        year = "";
        outof = 0;
		
	}

	/**
	 * @return the examuuid
	 */
	public String getExamuuid() {
		return examuuid;
	}

	/**
	 * @param examuuid the examuuid to set
	 */
	public void setExamuuid(String examuuid) {
		this.examuuid = examuuid;
	}

	/**
	 * @return the examname
	 */
	public String getExamname() {
		return examname;
	}

	/**
	 * @param examname the examname to set
	 */
	public void setExamname(String examname) {
		this.examname = examname;
	}

	/**
	 * @return the roomnameuuid
	 */
	public String getRoomnameuuid() {
		return roomnameuuid;
	}

	/**
	 * @param roomnameuuid the roomnameuuid to set
	 */
	public void setRoomnameuuid(String roomnameuuid) {
		this.roomnameuuid = roomnameuuid;
	}
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
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
	 * @return the outof
	 */
	public int getOutof() {
		return outof;
	}

	/**
	 * @param outof the outof to set
	 */
	public void setOutof(int outof) {
		this.outof = outof;
	}
	

}
