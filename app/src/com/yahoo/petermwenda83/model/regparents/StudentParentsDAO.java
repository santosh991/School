/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import java.util.List;

import com.yahoo.petermwenda83.contoller.guardian.StudentParent;
import com.yahoo.petermwenda83.contoller.guardian.StudentRelative;
import com.yahoo.petermwenda83.contoller.guardian.StudentSponser;
import com.yahoo.petermwenda83.contoller.student.StudentSuper;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface StudentParentsDAO {
    /**
     * 
     * @param uuid
     * @return StudentParent
     */
	public StudentParent getStudentParent(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return StudentRelative
	  */
	public StudentRelative getStudentRelative(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return StudentSponser
	  */
	public StudentSponser getStudentSponser(String uuid);
	    /**
	     * 
	     * @param studentSuper
	     * @param parent
	     *
	     */
	public boolean putStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean putStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  *
	  */
	public boolean putStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * 
	  */
	public boolean ediStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean editStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean editStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * 
	  */
	public boolean deleteStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * 
	  */
	public boolean deleteStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean deleteStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	  /**
	   * 
	   * @return AllStudentParent
	   */
	public List<StudentParent> getAllStudentParent();
	 /**
	  * 
	  * @return AllStudentRelative
	  */
	public List<StudentRelative> getAllStudentRelative();
	 /**
	  * 
	  * @return AllStudentSponser
	  */
	public List<StudentSponser> getAllStudentSponser();
	
}
