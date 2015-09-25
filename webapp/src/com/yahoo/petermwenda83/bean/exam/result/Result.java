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
package com.yahoo.petermwenda83.bean.exam.result;

import com.yahoo.petermwenda83.bean.exam.Exam;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Result extends Exam {
	   private String StudentUuid;
	   private String ExamDetailUuid;
	   private double MPoint;
	   private String Grade;
	   private String Remark;
	   private int  Position;
	/**
	 * 
	 */
	public Result() {
		super();
		  StudentUuid ="";
		  ExamDetailUuid ="";
		  MPoint =0;
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
	 * @return the mPoint
	 */
	public double getMPoint() {
		return MPoint;
	}

	/**
	 * @param mPoint the mPoint to set
	 */
	public void setMPoint(double mPoint) {
		MPoint = mPoint;
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
		builder.append("Result");
		builder.append("[getUuid()=");
		builder.append(getUuid()); 
		builder.append(",StudentUuid=");
		builder.append(StudentUuid);
		builder.append(",ExamDetailUuid=");
		builder.append(ExamDetailUuid);
		builder.append(", MPoint=");
		builder.append(MPoint);
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
