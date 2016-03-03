package com.yahoo.petermwenda83.bean.money;

import com.yahoo.petermwenda83.bean.StorableBean;

public class StudentAmount extends StorableBean{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 5316568587701096638L;
	private String schoolAccountUuid ;
    private String studentUuid;
    private double amount ;
    private String status;
  

	public StudentAmount() {
		schoolAccountUuid = "";
		studentUuid = "";
		amount = 0.0;
		status = "";
	}
	
	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return schoolAccountUuid;
	}

	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		this.schoolAccountUuid = schoolAccountUuid;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
    
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Student Fee");
		builder.append("[getUuid() = ");
		builder.append(getUuid());
		builder.append(", schoolAccountUuid =");
		builder.append(schoolAccountUuid);
		builder.append(", studentUuid =");
		builder.append(studentUuid);
		builder.append(", amount =");
		builder.append(amount);
		builder.append(", status =");
		builder.append(status);
		builder.append("]");
		return builder.toString(); 
		}


}
