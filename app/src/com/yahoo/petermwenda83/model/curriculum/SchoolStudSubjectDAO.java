/**
 * 
 */
package com.yahoo.petermwenda83.model.curriculum;

import java.util.List;

import com.yahoo.petermwenda83.contoller.student.StudentSubject;

/**
 * @author peter
 *
 */
public interface SchoolStudSubjectDAO {
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public  StudentSubject getStudentSubject(String Uuid);
	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean putStudentSubject(StudentSubject suject);
	
	  /**
	   * 
	   * @param subject
	   * @param uuid
	   * @return
	   */
	public boolean editSubject(StudentSubject subject,String uuid);
	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean deleteSubject(StudentSubject suject);
	
	/**
	 * 
	 * @return AllStudentSubject
	 */
	public List<StudentSubject> getAllStudentSubject();

}
