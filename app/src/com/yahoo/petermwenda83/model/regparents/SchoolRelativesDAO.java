/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import java.util.List;

import com.yahoo.petermwenda83.contoller.guardian.StudentRelative;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolRelativesDAO {
	
	 /**
	  * 
	  * @param uuid
	  * @return StudentRelative
	  */
	public StudentRelative getStudentRelative(StudentRelative Relative);
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean putStudentRelative(StudentRelative relative);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean editStudentRelative(StudentRelative relative);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean deleteStudentRelative(StudentRelative relative);
	
	
	 /**
	  * 
	  * @return AllStudentRelative
	  */
	public List<StudentRelative> getAllStudentRelative();

}
