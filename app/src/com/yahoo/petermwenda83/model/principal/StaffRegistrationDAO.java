/**
*Language: Java,
*Database: MySQL/Progress.
*OS: Windows,Linux
*Developer: Peter Mwenda,+254718953974, mwendapeter72@gmail.com
*Requirements: computer with 1GB RAM or above,40GB HHD or above, process speed 1.4GHtz or above.
*/

package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;
import com.yahoo.petermwenda83.contoller.staff.teaching.Position;
import com.yahoo.petermwenda83.contoller.staff.teaching.Teacher;

/**
 * @author peter
 *@author mwendapeter@gmail.com 
 */
public interface StaffRegistrationDAO {
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Teacher getTeacher(Position position,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public Teacher getNTstaff(NTPosition ntposition, String uuid);

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean putTeacher(Teacher teacher,Position position,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean putNTstaff(NTstaff ntstaff,NTPosition ntposition,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean editTeacher(Teacher teacher,Position position,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean editNTstaff(NTstaff ntstaff,NTPosition ntposition,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteTeacher(Teacher teacher,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteNTstaff(NTstaff ntstaff, String uuid);
	/**
	 * 
	 * @return
	 */
	public List<Teacher> getTeacher();
	
	/**
	 * 
	 * @return
	 */
	public List<Teacher> getNTstaff();
	
	

}
