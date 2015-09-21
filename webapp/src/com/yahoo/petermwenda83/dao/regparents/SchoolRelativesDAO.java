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
package com.yahoo.petermwenda83.dao.regparents;

import java.util.List;

import com.yahoo.petermwenda83.bean.guardian.StudentRelative;

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
	public StudentRelative getStudentRelative(String StudentUuid);
	
	 /**
	  * 
	  * @param NationalID
	  * @return StudentRelative
	  */
	public StudentRelative getStudentRelativeByNationalID(String NationalID);
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  *  @return whether StudentRelative details were put successfully 
	  * 
	  */
	public boolean putStudentRelative(StudentRelative relative);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * @return whether StudentRelative details were edited successfully 
	  * 
	  */
	public boolean editStudentRelative(StudentRelative relative,String StudentUuid);
	
	
	 /**
	  * 
	  * @param studentSuper
	  * @param relative
	  * @return whether StudentRelative details were deleted successfully 
	  * 
	  */
	public boolean deleteStudentRelative(StudentRelative relative);
	
	
	 /**
	  * 
	  * @return List of All Student's Relative
	  */
	public List<StudentRelative> getAllStudentRelative();

}
