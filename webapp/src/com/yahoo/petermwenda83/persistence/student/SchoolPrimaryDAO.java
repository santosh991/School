

package com.yahoo.petermwenda83.persistence.student;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentPrimary;

/**
 * @author peter
 *
 */
public interface SchoolPrimaryDAO {
	  /**
	   * 
	   * @param StudentUuid
	   * @return Student StudentPrimary SchoolAccount Details
	   */
	public StudentPrimary getPrimary(String StudentUuid);
	   
	public boolean putPrimary(StudentPrimary Primary);
	  
	public boolean updatePrimary(StudentPrimary Primary);
	   
	public boolean deletePrimary(StudentPrimary Primary);
	   
	public List<StudentPrimary> getAllPrimary();

}
