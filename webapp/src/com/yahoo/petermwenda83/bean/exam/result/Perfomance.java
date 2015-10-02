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

import java.util.Date;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Perfomance extends AllBean{
    
	    /**
	 * 
	 */
	private static final long serialVersionUID = 7599496918489745339L;
		private String StudentUuid;
	    private String  ExamDetailUuid;
	    private double Cat1;
	    private double Cat2;
	    private double  Paper1;
	    private double  Paper2;
	    private double  Paper3;
	    private String SysUser;
	    private Date  SubmitDate;
	/**
	 * 
	 */
	public Perfomance() {
		super();
		    StudentUuid ="";
		    ExamDetailUuid ="";
		    Cat1 = 0;
		    Cat2 =0;
		    Paper1 =0;
		    Paper2 =0;
		    Paper3 =0;
		    SysUser ="";
		    SubmitDate = new Date(); 
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
	 * @return the cat1
	 */
	public double getCat1() {
		return Cat1;
	}

	/**
	 * @param cat1 the cat1 to set
	 */
	public void setCat1(double cat1) {
		Cat1 = cat1;
	}

	/**
	 * @return the cat2
	 */
	public double getCat2() {
		return Cat2;
	}

	/**
	 * @param cat2 the cat2 to set
	 */
	public void setCat2(double cat2) {
		Cat2 = cat2;
	}

	/**
	 * @return the paper1
	 */
	public double getPaper1() {
		return Paper1;
	}

	/**
	 * @param paper1 the paper1 to set
	 */
	public void setPaper1(double paper1) {
		Paper1 = paper1;
	}

	/**
	 * @return the paper2
	 */
	public double getPaper2() {
		return Paper2;
	}

	/**
	 * @param paper2 the paper2 to set
	 */
	public void setPaper2(double paper2) {
		Paper2 = paper2;
	}

	/**
	 * @return the paper3
	 */
	public double getPaper3() {
		return Paper3;
	}

	/**
	 * @param paper3 the paper3 to set
	 */
	public void setPaper3(double paper3) {
		Paper3 = paper3;
	}

	/**
	 * @return the sysUser
	 */
	public String getSysUser() {
		return SysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(String sysUser) {
		SysUser = sysUser;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return SubmitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		SubmitDate = submitDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Perfomance [getUuid()=");
		builder.append(getUuid());
		builder.append(",StudentUuid=");
		builder.append(StudentUuid);
		builder.append(",ExamDetailUuid=");
		builder.append(ExamDetailUuid);
		builder.append(",Cat1=");
		builder.append(Cat1);
		builder.append(",Cat2=");
		builder.append(Cat2);
		builder.append(",Paper1=");
	    builder.append(Paper1);
		builder.append(",Paper2=");
		builder.append(Paper2);
		builder.append(",Paper3=");
		builder.append(Paper3);
		builder.append(",SysUser=");
		builder.append(SysUser);
		builder.append(",SubmitDate=");
		builder.append(SubmitDate);
		builder.append("]");
		return builder.toString();
	}

}
