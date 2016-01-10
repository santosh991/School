/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import java.util.List;

import com.yahoo.petermwenda83.bean.staff.TeacherSubClass;

/**
 * @author peter
 *
 */
public interface SchoolTeacherSubClassDAO {
	/**
	 * 
	 * @param teacherUuid
	 * @return
	 */
	public TeacherSubClass getSubjectClass(String teacherUuid);
	 /**
	  * 
	  * @param subClass
	  * @return
	  */
	public boolean putSubjectClass(TeacherSubClass subClass);
	  /**
	   * 
	   * @param subClass
	   * @return
	   */
	public boolean updateSubjectClass(TeacherSubClass subClass);
	  /**
	   * 
	   * @param subClass
	   * @return
	   */
	public boolean deleteSubjectClass(TeacherSubClass subClass);
	  /**
	   * 
	   * @return
	   */
	public List<TeacherSubClass> getSubjectClassList();

}
