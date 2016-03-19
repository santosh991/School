/**
 * 
 */
package com.yahoo.petermwenda83.bean.othermoney;

import com.yahoo.petermwenda83.bean.StorableBean;

/**  
 * @author peter
 *
 */
public class StudentOtherMoniesClosingBal extends StorableBean{
	

	
	   String studentUuid;
	   String otherstypeUuid;
	   double balance;
	   

	/**
	 * 
	 */
	public StudentOtherMoniesClosingBal() {
		   studentUuid = "";
		   otherstypeUuid = "";
		   balance = 0;
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
	 * @return the otherstypeUuid
	 */
	public String getOtherstypeUuid() {
		return otherstypeUuid;
	}



	/**
	 * @param otherstypeUuid the otherstypeUuid to set
	 */
	public void setOtherstypeUuid(String otherstypeUuid) {
		this.otherstypeUuid = otherstypeUuid;
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



	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Student Other Monies");
		builder.append(",[getUuid() = ");
		builder.append(getUuid());
		builder.append(", studentUuid =");
		builder.append(studentUuid); 
		builder.append(", otherstypeUuid =");
		builder.append(otherstypeUuid); 
		builder.append(", balance =");
		builder.append(balance); 
		builder.append("]");
		return builder.toString(); 
		}
	
	   /**
		 * 
		 */
		private static final long serialVersionUID = -378744821948773894L;

}
