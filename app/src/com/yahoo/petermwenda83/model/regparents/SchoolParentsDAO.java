/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import java.util.List;

import com.yahoo.petermwenda83.contoller.guardian.StudentParent;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolParentsDAO {
    /**
     * 
     * @param uuid
     * @return StudentParent
     */
	public StudentParent getStudentParent(StudentParent Parent);
	
	    /**
	     * 
	     * @param studentSuper
	     * @param parent
	     *
	     */
	public boolean putStudentParent(StudentParent parent);
	
	 
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * 
	  */
	public boolean ediStudentParent(StudentParent parent);
	
	 
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * 
	  */
	public boolean deleteStudentParent(StudentParent parent);
	
	
	  /**
	   * 
	   * @return AllStudentParent
	   */
	public List<StudentParent> getAllStudentParent();
	
	
}
