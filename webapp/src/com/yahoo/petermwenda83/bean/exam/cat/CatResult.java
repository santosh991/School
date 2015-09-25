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
package com.yahoo.petermwenda83.bean.exam.cat;

import com.yahoo.petermwenda83.bean.exam.Exam;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class CatResult extends Exam  {
	
	    private String StudentUuid;
	    private String ExamDetailUuid;
	    private double CatPoint;
	    private String Grade;
	    private String Remark;
	    private int Position;
	    
	public CatResult(){
		super();
		StudentUuid ="";
	    ExamDetailUuid ="";
	    CatPoint =0;
	    Grade ="";
	    Remark ="";
	    Position =0;
	}
	
	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return StudentUuid;
	}

	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		StudentUuid = studentUuid;
	}

	/**
	 * @return the examDetailUuid
	 */
	public String getExamDetailUuid() {
		return ExamDetailUuid;
	}

	/**
	 * @param examDetailUuid the examDetailUuid to set
	 */
	public void setExamDetailUuid(String examDetailUuid) {
		ExamDetailUuid = examDetailUuid;
	}

	/**
	 * @return the catPoint
	 */
	public double getCatPoint() {
		return CatPoint;
	}

	/**
	 * @param catPoint the catPoint to set
	 */
	public void setCatPoint(double catPoint) {
		CatPoint = catPoint;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return Grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		Grade = grade;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return Remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return Position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		Position = position;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Cat Result");
		builder.append("[getUuid()=");
		builder.append(getUuid()); 
		builder.append(",StudentUuid=");
		builder.append(StudentUuid);
		builder.append(",ExamDetailUuid=");
		builder.append(ExamDetailUuid);
		builder.append(", CatPoint=");
		builder.append(CatPoint);
		builder.append(", Grade=");
		builder.append(Grade);
		builder.append(",Remark=");
		builder.append(Remark);
		builder.append(",Position=");
		builder.append(Position);
		builder.append("]");
		return builder.toString(); 
		}
}
