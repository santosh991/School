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
package com.yahoo.petermwenda83.bean.money;

import java.util.Date;

import com.yahoo.petermwenda83.bean.student.StudentSuper;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Withdraw extends StudentSuper{
	 private String	uuid;
	 private String  studentUuid;
	 private double  balance ; 
	 private Date  withdrawdate ;
	/**
	 * 
	 */
	public Withdraw()  {
		super();
		uuid = "";
		studentUuid = "";
		balance = 0.0;
		withdrawdate = new Date();
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
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}


	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}


	/**
	 * @return the withdrawdate
	 */
	public Date getWithdrawdate() {
		return withdrawdate;
	}


	/**
	 * @param withdrawdate the withdrawdate to set
	 */
	public void setWithdrawdate(Date withdrawdate) {
		this.withdrawdate = withdrawdate;
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
		builder.append(", balance =");
		builder.append("balance");
		builder.append(", withdrawdate =");
		builder.append("withdrawdate");
		builder.append("]");
		return builder.toString(); 
		}

}
