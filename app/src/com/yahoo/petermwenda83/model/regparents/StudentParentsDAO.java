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
 * @author peter
 *
 */
public interface StudentParentsDAO {
    /**
     * 
     * @param uuid
     * @return
     */
	public StudentParent getStudentParent(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return
	  */
	public StudentRelative getStudentRelative(String uuid);
	 /**
	  * 
	  * @param uuid
	  * @return
	  */
	public StudentSponser getStudentSponser(String uuid);
	    /**
	     * 
	     * @param studentSuper
	     * @param parent
	     * @return
	     */
	public boolean putStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * @return
	  */
	public boolean putStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * @return
	  */
	public boolean putStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * @return
	  */
	public boolean ediStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * @return
	  */
	public boolean editStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * @return
	  */
	public boolean editStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * @return
	  */
	public boolean deleteStudentParent(StudentSuper studentSuper,StudentParent parent);
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * @return
	  */
	public boolean deleteStudentRelative(StudentSuper studentSuper,StudentRelative relative);
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * @return
	  */
	public boolean deleteStudentSponser(StudentSuper studentSuper, StudentSponser sponser);
	  /**
	   * 
	   * @return
	   */
	public List<StudentParent> getAllStudentParent();
	 /**
	  * 
	  * @return
	  */
	public List<StudentRelative> getAllStudentRelative();
	 /**
	  * 
	  * @return
	  */
	public List<StudentSponser> getAllStudentSponser();
	
}
