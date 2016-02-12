/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import java.util.List;

import com.yahoo.petermwenda83.bean.staff.Staff;

/**
 * @author peter
 *
 */
public interface SchoolStaffDAO {
	
	public Staff getStaff(String schoolAccountUuid,String Uuid);
	
	public Staff getStaffByUsername(String schoolAccountUuid,String username);
	
	public Staff getStaffByPosition(String schoolAccountUuid,String PositionUuid);
	
	public boolean putStaff(Staff staff);
	
	public boolean updateStaff(Staff staff);
	
	public boolean deleteStaff(Staff staff);
	
	public List<Staff> getStaffList(String schoolAccountUuid); 

}
