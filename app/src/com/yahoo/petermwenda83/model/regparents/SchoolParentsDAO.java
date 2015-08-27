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
     * @return Student Parent
     */
	public StudentParent getStudentParent(String Uuid); 
	 /**
	  * 
	  * @param FatherId
	  * @return Student parent
	  */
	public StudentParent getParentByFatherId(String FatherId); 
	  /**
	   * 
	   * @param MotherID
	   * @return Student Parent
	   */
	public StudentParent getParentByMotherId(String MotherID); 
	
	    /**
	     * 
	     * @param studentSuper
	     * @param parent
	     * @return Whether Parent details were put successfully
	     *
	     */
	public boolean putStudentParent(StudentParent parent);
	
	 
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * @return Whether Parent details were edited successfully
	  */
	public boolean ediStudentParent(StudentParent parent,String StudentUuid);
	
	 
	 /**
	  * 
	  * @param studentSuper
	  * @param parent
	  * @return Whether Parent details were deleted successfully
	  */
	public boolean deleteStudentParent(StudentParent parent);
	
	
	  /**
	   * 
	   * @return List of All Student's Parent
	   */
	public List<StudentParent> getAllStudentParent();
	
	
}
