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

import java.util.Date;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Exam extends AllBean{
	private String subjectUuid ;
	private String studentUuid ;
	private String Subjectcode;
	private double marks ;
	private double submark ;
	private double percent ;
	private double total ;
	private double points;
	private String  grade ;
	private int  position ;
	private String remarks ;
	private Date submitdate ;
	
	private String ExamUuid ;//either MainResultsUuid or CatResultsuuid/CatMarksUuid or MainMarksUuid
	
	
	/**
	 * 
	 */
	protected Exam() {
		super();
		subjectUuid = "";
		studentUuid = "";
		Subjectcode = "";
		marks = 0.0;
		submark = 0.0;
		percent =0.0;
		total = 0.0;
		points = 0.0;
		grade = "";
		position = 0;
		remarks = "";
		submitdate = new Date();
		
		ExamUuid = "";
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
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return studentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
	}

	/**
	 * @return the subjectcode
	 */
	public String getSubjectcode() {
		return Subjectcode;
	}


	/**
	 * @param subjectcode the subjectcode to set
	 */
	public void setSubjectcode(String subjectcode) {
		Subjectcode = subjectcode;
	}



	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}


	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the marks
	 */
	public double getMarks() {
		return marks;
	}


	/**
	 * @param marks the marks to set
	 */
	public void setMarks(double marks) {
		this.marks = marks;
	}


	/**
	 * @return the submark
	 */
	public double getSubmark() {
		return submark;
	}


	/**
	 * @param submark the submark to set
	 */
	public void setSubmark(double submark) {
		this.submark = submark;
	}


	/**
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}


	/**
	 * @param percent the percent to set
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}


	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}


	/**
	 * @return the points
	 */
	public double getPoints() {
		return points;
	}


	/**
	 * @param points the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}


	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}


	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}


	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * @return the submitdate
	 */
	public Date getSubmitdate() {
		return submitdate;
	}


	/**
	 * @param submitdate the submitdate to set
	 */
	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
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

	
}
