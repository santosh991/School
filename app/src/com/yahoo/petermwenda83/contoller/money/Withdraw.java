/**
 * 
 */
package com.yahoo.petermwenda83.contoller.money;

import java.util.Date;

import com.yahoo.petermwenda83.contoller.student.StudentSuper;


/**
 * @author peter
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
	public String getUuid() {
		return uuid;
	}


	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
