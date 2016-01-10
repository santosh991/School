/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * @author peter
 *
 */
public class PocketMoney extends StorableBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentUuid;
	private double Balance;

	/**
	 * 
	 */
	public PocketMoney() {
		studentUuid = "";
		Balance = 0.0;
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
		return Balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		Balance = balance;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Balance");
		builder.append("[getUuid() = ");
		builder.append(getUuid());
		builder.append(", studentUuid =");
		builder.append(studentUuid);
		builder.append(", Balance =");
		builder.append(Balance);
		builder.append("]");
		return builder.toString(); 
		}
}
