/**
 * 
 */
package com.yahoo.petermwenda83.persistence.subject;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentSubject;

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
	  * @param suject
	  * @return
	  */
	public  List<StudentSubject> getStudentSubject(StudentSubject suject,String Class);
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
