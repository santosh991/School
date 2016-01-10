/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.Deposit;


/**
 * @author peter
 *
 */
public interface SchoolDepositDAO {
	
	/**
	 * 
	 * @param deposit
	 * @param amount
	 * @return
	 */
	public boolean hasBalance(Deposit deposit,Double amount);
	/**
	 * 
	 * @param deposit
	 * @param amount
	 * @return
	 */
	public boolean addBalance(Deposit deposit,Double amount);
	 /**
	  * 
	  * @return
	  */
	public List<Deposit> getDepositeList();

}
