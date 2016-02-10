/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.Deposit;
import com.yahoo.petermwenda83.bean.money.PocketMoney;
import com.yahoo.petermwenda83.bean.money.Withdraw;

/**
 * @author peter
 *
 */
public interface SchoolPMoneyDAO {
	
	/**
	 * 
	 * @param studedntUuid
	 * @return
	 */
	public PocketMoney getMoney(String StudentUuid);
	/**
	 * 
	 * @param studedntUuid
	 * @return
	 */
	
	public boolean studentExist(String StudentUuid);
	
	/**
	 * 
	 * @param money
	 * @param amount
	 * @return
	 */
	public boolean hasBalance(PocketMoney money,double amount);
	 /**
	  * 
	  * @param money
	  * @param amount
	  * @return
	  */
	public boolean addBalance(PocketMoney money,double amount);
	 /**
	  * 
	  * @param money
	  * @param amount
	  * @return
	  */
	public boolean deductBalance(PocketMoney money,double amount);
	  /**
	   * 
	   * @param studedntUuid
	   * @return
	   */
	public List<Withdraw> getWithdrawList(String StudentUuid);
	  /**
	   * 
	   * @param studedntUuid
	   * @return
	   */
	public List<Deposit> getDepositList(String StudentUuid);
	
	
	

}
