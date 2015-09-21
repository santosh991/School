/**##########################################################
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

package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.teaching.Teachers;

/**
* @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 */
public interface SchoolTeacherRegistrationDAO {
	/**
	 * 
	 * @param uuid
	 * @return The Teacher
	 */
	public Teachers getTeacher(String uuid);
  /**
  * 
  * @param Teacher
  * @return Whether Teacher was put successfully 
  */
	public boolean putTeacher(Teachers Teacher);
	/**
	 * 
	 * @param Teacher
	 * @param Uuid
	 * @return Whether Teacher was edited successfully 
	 */
	public boolean editTeacher(Teachers Teacher,String Uuid);
	/**
	 * 
	 * @param Teacher
	 * @param uuid
	 * @return Whether Teacher was deleted successfully 
	 */
	public boolean deleteTeacher(Teachers Teacher,String uuid);
	
	/**
	 * 
	 * @return List of all Teachers
	 */
	public List<Teachers> getAllTeachers();
	
	
	
	

}
