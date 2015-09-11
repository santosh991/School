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
package com.yahoo.petermwenda83.contoller.money;

import java.util.Date;

import com.yahoo.petermwenda83.contoller.student.StudentSuper;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Deposit extends StudentSuper {
	 private String	uuid;
	 private String studentUuid; 
     private double  ammount; 
    private Date depositedate; 
	/**
	 * 
	 */
	public Deposit() {
		super();
		uuid ="";
		studentUuid ="";
		ammount = 0.0;
		depositedate  = new Date();
	}
	

	/**
	 * @return the uuid
	 */
	@Override
	public String getUuid() {
		return uuid;
	}


	/**
	 * @param uuid the uuid to set
	 */
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	/**
	 * @return the studentUuid
	 */
	@Override
	public String getStudentUuid() {
		return studentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	@Override
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
	}


	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}


	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}


	/**
	 * @return the depositedate
	 */
	public Date getDepositedate() {
		return depositedate;
	}


	/**
	 * @param depositedate the depositedate to set
	 */
	public void setDepositedate(Date depositedate) {
		this.depositedate = depositedate;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Users");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", uuid =");
		builder.append("uuid");
		builder.append(", studentUuid =");
		builder.append("studentUuid");
		builder.append(", ammount =");
		builder.append("ammount");
		builder.append(", depositedate =");
		builder.append("depositedate");
		builder.append("]");
		return builder.toString(); 
		}

}
