/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam;

import java.util.Date;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class Exam extends AllBean{
	private String subjectUuid ;
	private String studentUuid ;
	private String ExamTypeUuid ;
	private String Subjectcode;
	private double marks ;
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
	public Exam() {
		super();
		subjectUuid = "";
		studentUuid = "";
		ExamTypeUuid = "";
		Subjectcode = "";
		marks = 0.0;
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
	 * @return the examTypeUuid
	 */
	public String getExamTypeUuid() {
		return ExamTypeUuid;
	}


	/**
	 * @param examTypeUuid the examTypeUuid to set
	 */
	public void setExamTypeUuid(String examTypeUuid) {
		ExamTypeUuid = examTypeUuid;
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
