/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import java.util.Date;

/**
 * @author peter
 *
 */
public class Deposit extends Money {
	private Date depositedate;

	/**
	 * 
	 */
	public Deposit() {
		super();
		depositedate = new Date();
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
		builder.append("Pocket Money");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(", getAmount() =");
		builder.append(getAmount());
		builder.append(", depositedate =");
		builder.append(depositedate);
		builder.append("]");
		return builder.toString(); 
		}
}
