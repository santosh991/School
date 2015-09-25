/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import java.util.Date;

/**
 * @author peter
 *
 */
public class Withdraw extends Money{
	
	private Date withdrawdate;
	/**
	 * 
	 */
	public Withdraw() {
		super();
		
		withdrawdate = new Date();
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
		builder.append("Pocket Money");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(", getAmount()=");
		builder.append(getAmount());
		builder.append(", withdrawdate =");
		builder.append(withdrawdate);
		builder.append("]");
		return builder.toString(); 
		}
}
