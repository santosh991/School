/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.regparents;

import java.util.List;

import com.yahoo.petermwenda83.bean.guardian.StudentSponsor;

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
	public StudentSponsor getStudentSponsor(String StudentUuid);
	
	  /**
	   * 
	   * @param NationalID
	   * @return StudentSponsor
	   */
	public StudentSponsor getStudentSponsorById(String NationalID);
	
	/**
	  * 
	  * @param studentSuper
	  * @param sponser
	  *
	  */
	public boolean putStudentSponsor(StudentSponsor sponser);
	
	/**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean editStudentSponsor(StudentSponsor sponsor,String StudentUuid);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param sponser
	  * 
	  */
	public boolean deleteStudentSponsor(StudentSponsor sponser);
	
	
	 /**
	  * 
	  * @return AllStudentSponser
	  */
	public List<StudentSponsor> getAllStudentSponsor();
	

}
