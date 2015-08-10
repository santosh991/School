/**
*Language: Java,
*Database: MySQL/Progress.
*OS: Windows,Linux
*Developer: Peter Mwenda,+254718953974, mwendapeter72@gmail.com
*Requirements: computer with 1GB RAM or above,40GB HHD or above, process speed 1.4GHtz or above.
*/

package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.Employees;
import com.yahoo.petermwenda83.contoller.staff.teaching.Teacher;

/**
* @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 */
public interface StaffRegistrationDAO {
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public Employees getStaff(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return
	  */
	public Employees getNtStaff(String uuid);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public Employees getstaffPos(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return
	  */
	public Employees getNtstaffPos(String uuid);

	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean putStaff(Employees emp);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean putstaffPOss(Employees emp);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean editStaff(Employees emp);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean editStaffPos(Employees emp);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteStaf(Employees emp,String uuid);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteStaffPOs(Employees emp, String uuid);
	/**
	 * 
	 * @return teaching staff
	 */
	public List<Teacher> getAllTeacher();
	
	/**
	 * 
	 * @return all not teaching staff
	 */
	public List<Teacher> getAllNTstaff();
	
	

}
