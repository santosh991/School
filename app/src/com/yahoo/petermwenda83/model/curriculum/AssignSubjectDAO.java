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
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface AssignSubjectDAO {
	/**
	 * @param uuid
	 * 
	 */
	public  Subject getSubject(String uuid);
	  /**
	   * 
	   * @param subjectcode
	   * 
	   */
	public  Subject getSubjects(String subjectcode);

	/**
	 * 
	 * @param uuid
	 * 
	 */
	public  StudentSubject getStudentSubject(String uuid);
	 /**
	  * 
	  * @param subject
	  * 
	  */
	public boolean putSubject(Subject subject);
	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean putStudentSubject(StudentSubject suject);
	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean editSubject(Subject subject,String uuid);
	

	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean deleteSubject(StudentSubject suject);
	
	/**
	 * 
	 * @param subject
	 * 
	 */
	public boolean deleteStudent(Subject subject);
	/**
	 * 
	 * @return AllStudentSubject
	 */
	public List<StudentSubject> getAllStudentSubject();
	/**
	 * 
	 * @return AllSubjects
	 */
	public List<Subject> getAllSubjects();
	
	
	

}
