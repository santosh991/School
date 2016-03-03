/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

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
	public boolean addAmount(String schoolAccountUuid,String studentUuid,double amount,String Status);
	 /**
	  * 
	  * @param studentAmount
	  * @param amount
	  * @return
	  */
	public boolean deductAmount(String schoolAccountUuid,String studentUuid,double amount);
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param Status
	 * @return
	 */
	public boolean changeStatus(String Status,String schoolAccountUuid, String studentUuid);
	
	 /**
	  * 
	  * @param schoolAccountUuid
	  * @return
	  */
	public List<StudentAmount> getAmountList(String schoolAccountUuid);
	

}
