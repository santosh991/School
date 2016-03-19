/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.ClosingBalance;

/**
 * @author peter
 *
 */
public interface SchoolClosingBalanceDAO {
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param studentUuid
	 * @return
	 */
	public ClosingBalance getClosingBalanceByStudentId(String schoolAccountUuid,String studentUuid,String Term,String Year);
	 /**
	  * 
	  * @param schoolAccountUuid
	  * @return
	  */
	public List<ClosingBalance> getClosingBalanceList(String schoolAccountUuid,String Term,String Year);
	 /**
	  * 
	  * @param closingBalance
	  * @return
	  */
	public boolean putClosingBalance (ClosingBalance closingBalance); 

}
