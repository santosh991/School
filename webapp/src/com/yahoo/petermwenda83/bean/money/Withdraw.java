
/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/
package com.yahoo.petermwenda83.bean.money;

import java.util.Date;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class Withdraw extends StorableBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7496104242563750614L;
	private String studentUuid;
	private Date withdrawdate;
	private double amountWithdrawn;
	/**
	 * 
	 */
	public Withdraw() {
		super();
		studentUuid = "";
		withdrawdate = new Date();
		amountWithdrawn = 0.0;
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
	 * @return the withdrawdate
	 */
	public Date getWithdrawdate() {
		return withdrawdate;
	}


	/**
	 * @param withdrawdate the withdrawdate to set
	 */
	public void setWithdrawdate(Date withdrawdate) {
		this.withdrawdate = new Date(withdrawdate.getTime());
	}




	/**
	 * @return the amountWithdrawn
	 */
	public double getAmountWithdrawn() {
		return amountWithdrawn;
	}


	/**
	 * @param amountWithdrawn the amountWithdrawn to set
	 */
	public void setAmountWithdrawn(double amountWithdrawn) {
		this.amountWithdrawn = amountWithdrawn;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(",Student's Pocket Money Withdraw");
		builder.append("[getUuid() = ");
		builder.append(getUuid());
		builder.append(", studentUuid =");
		builder.append(studentUuid);
		builder.append(", withdrawdate =");
		builder.append(withdrawdate);
		builder.append(", amountWithdrawn =");
		builder.append(amountWithdrawn);
		builder.append("]");
		return builder.toString(); 
		}
}
