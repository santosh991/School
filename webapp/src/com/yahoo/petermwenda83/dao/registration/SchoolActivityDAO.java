/**##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without approval of from
 * ###### owner.#############################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.dao.registration;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.Activity;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolActivityDAO {

	 /**
	  * 
	  * @param activity
	  * @return 
	  */
	public Activity getStudent(String studentuuid);
	

	/**
	 * 
	 * @param studentSuper
	 * @param uuid
	 * 
	 */
	public boolean putStudent(Activity activity);
	

	/**
	 * 
	 * @param studentSuper
	 * 
	 */
	public boolean editStudent(Activity activity,String studentuuid);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteStudent(Activity activity);
	

	   /**
	    * @return AllStudents
	    */
	public List<Activity> getAllActivity();
	

}
