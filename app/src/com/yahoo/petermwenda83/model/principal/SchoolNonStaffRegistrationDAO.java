/**
 * 
 */
package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.Employees;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;

/**
 * @author peter
 *
 */
public interface SchoolNonStaffRegistrationDAO {
	
	 /**
	  * 
	  * @param uuid
	  * @return the Employees
	  */
	public Employees getNtStaff(String uuid);
	/**
	  * 
	  * @param uuid
	  * @return the Employees
	  */
	public Employees getNtstaffPos(String uuid);
	 
	
	/**
	 * 
	 * @return all not teaching staff
	 */
	public List<NTstaff> getAllNTstaff();
	  /**
	   * 
	   * @return
	   */
	public List<NTPosition> getAllNTPosition();
	

}
