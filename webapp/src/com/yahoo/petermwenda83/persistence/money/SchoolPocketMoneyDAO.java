/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.PocketMoney;

/**
 * @author peter
 *
 */
public interface SchoolPocketMoneyDAO {
	/**
	 * 
	 * @param studentUuid
	 * @return
	 */
	public PocketMoney getBalance(String studentUuid);
	 /**
	  * 
	  * @return
	  */
	public List<PocketMoney> getBalanceList();

}
