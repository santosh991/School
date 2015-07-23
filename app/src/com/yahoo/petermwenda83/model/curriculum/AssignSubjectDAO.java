/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.model.curriculum;

import java.util.List;

import com.yahoo.petermwenda83.contoller.student.StudentSubject;
import com.yahoo.petermwenda83.contoller.student.Subject;



/**
 * @author peter
 * @author <h1>mwendapeter72@gmail.com </h1>
 * @author <h1>migwindungu0@gmail.com </h1>
 *
 */
public interface AssignSubjectDAO {
	/**
	 * @param uuid
	 * @return
	 */
	public  Subject getSubject(String uuid);

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public  StudentSubject getStudentSubject(String uuid);
	 /**
	  * 
	  * @param subject
	  * @return
	  */
	public boolean putSubject(Subject subject);
	/**
	 * 
	 * @param subject
	 * @return
	 */
	public boolean putStudentSubject(StudentSubject suject);
	/**
	 * 
	 * @param subject
	 * @return
	 */
	public boolean editSubject(Subject subject,String uuid);
	

	/**
	 * 
	 * @param subject
	 * @return
	 */
	public boolean deleteSubject(StudentSubject suject);
	
	/**
	 * 
	 * @param subject
	 * @return
	 */
	public boolean deleteStudent(Subject subject);
	/**
	 * 
	 * @return
	 */
	public List<StudentSubject> getAllStudentSubject();
	/**
	 * 
	 * @return
	 */
	public List<Subject> getAllSubjects();
	
	
		
	

	
	
	
	

}
