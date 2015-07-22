/**
 * 
 */
package com.yahoo.petermwenda83.contoller.money;

import java.util.Date;

import com.yahoo.petermwenda83.contoller.student.Student;

/**
 * @author peter
 *
 */
public class Deposit extends Student {
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
