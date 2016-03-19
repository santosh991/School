package com.yahoo.petermwenda83.bean.othermoney;

import com.yahoo.petermwenda83.bean.StorableBean;

public class OtherMonies extends StorableBean{
	
	
	  String studentUuid;
	  double amount;
	  String Status;

	public OtherMonies() {
		 studentUuid = "";
		 amount = 0;
		 Status = "";
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
		return Status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}



	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Other Monies");
		builder.append(",[getUuid() = ");
		builder.append(getUuid());
		builder.append(", studentUuid =");
		builder.append(studentUuid); 
		builder.append(", amount =");
		builder.append(amount); 
		builder.append(", Status =");
		builder.append(Status); 
		builder.append("]");
		return builder.toString(); 
		}
	
	     /**
		 * 
		 */
		private static final long serialVersionUID = -8114021537377719331L;

}
