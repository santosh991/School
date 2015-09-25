/**
 * 
 */
package com.yahoo.petermwenda83.bean.money;

import com.yahoo.petermwenda83.bean.student.StudentSuper;

/**
 * @author peter
 *
 */
public class Money extends StudentSuper {
	private double amount;

	/**
	 * 
	 */
	public Money() {
		super();
		amount = 0.0;
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

  }
