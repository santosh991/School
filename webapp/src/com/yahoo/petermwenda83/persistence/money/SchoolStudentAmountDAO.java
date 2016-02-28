/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import com.yahoo.petermwenda83.bean.money.StudentAmount;

/**
 * @author peter
 *
 */
public interface SchoolStudentAmountDAO {
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param studentUuid
	 * @return
	 */
	public StudentAmount getStudentAmount(String schoolAccountUuid,String studentUuid);
	 /**
	  * 
	  * @param studentAmount
	  * @param amount
	  * @return
	  */
	public boolean hasAmount(String schoolAccountUuid,String studentUuid,double amount);
	/**
	 * 
	 * @param studentAmount
	 * @param amount
	 * @return
	 */
	public boolean addAmount(String schoolAccountUuid,String studentUuid,double amount);
	 /**
	  * 
	  * @param studentAmount
	  * @param amount
	  * @return
	  */
	public boolean deductAmount(String schoolAccountUuid,String studentUuid,double amount);
	

}
