/**
 * 
 */
package com.yahoo.petermwenda83.persistence.employee;

import java.util.List;

import com.yahoo.petermwenda83.bean.employee.TeacherSubject;

/**
 * @author peter
 *
 */
public interface SchoolTeacherSubject {
	/**
	 * 
	 * @return subjects taught  by a teacher
	 */
	public TeacherSubject get();
	/**
	 * 
	 * @param TeacherSubject
	 * @return Whether TeacherSubject was put successfully 
	 */
	public boolean get(TeacherSubject TeacherSubject);
	/**
	 * 
	 * @param TeacherSubject
	 * @return Whether Teacher's Subject was updated successfully 
	 */
	public boolean update(TeacherSubject TeacherSubject);
	/**
	 * 
	 * @param TeacherSubject
	 * @return Whether Teacher's Subject was deleted successfully 
	 */
	public boolean delete(TeacherSubject TeacherSubject);
	/**
	 * 
	 * @return List of all Teacher's Subject
	 */
	public List<TeacherSubject> getAllTeacherSubject();

}
