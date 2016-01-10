/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import java.util.List;

import com.yahoo.petermwenda83.bean.staff.ClassTeacher;

/**
 * @author peter
 *
 */
public interface SchoolClassTeacherDAO {
	
	public ClassTeacher getClassTeacher(String TeacherUuid);
	
	public boolean putClassTeacher(ClassTeacher Teacher);
	
	public boolean updateClassTeacher(ClassTeacher Teacher);
	
	public boolean deleteClassTeacher(ClassTeacher Teacher);
	
	public List<ClassTeacher> getClassTeacherList();

}
