/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import java.util.List;

import com.yahoo.petermwenda83.contoller.guardian.StudentSponsor;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolSponsorsDAO {
	

	 /**
	  * 
	  * @param uuid
	  * @return StudentSponsor
	  */
	public StudentSponsor getStudentSponser(StudentSponsor Sponser);
	
	/**
	  * 
	  * @param studentSuper
	  * @param sponser
	  *
	  */
	public boolean putStudentSponser(StudentSponsor sponser);
	
	/**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean editStudentSponser(StudentSponsor sponser);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean deleteStudentSponser(StudentSponsor sponser);
	
	
	 /**
	  * 
	  * @return AllStudentSponser
	  */
	public List<StudentSponsor> getAllStudentSponser();
	

}
