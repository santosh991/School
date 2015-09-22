/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.pocket.Deposit;
import com.yahoo.petermwenda83.bean.money.pocket.Money;

/**
 * @author peter
 *
 */
public interface StudentPocketDepositDAO {
	/**
	 * 
	 * @param StudentUuid
	 * @return whether pocket money balance for a student
	 */
	public Deposit getPocketMoney(String StudentUuid);
	 /**
	  * 
	  * @param pMoney
	  * @return whether a student account has balance or not
	  */
	public boolean hasBalance(Money Money,Double bal);
	 /**
	  * 
	  * @param pMoney
	  * @return whether student balance has been added or not
	  */
	public boolean addBalance(Money pMoney,Double bal);
	   /**
	    * 
	    * @param pMoney
	    * @return whether student balance has been deducted or not
	    */
	public boolean deductBalance(Money pMoney,Double bal);
	  /**
	   * 
	   * @return List of all balances for all students
	   */
	public List<Deposit> getAllBalanaces();

}
