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
package com.yahoo.petermwenda83.persistence.principal;

import java.util.List;

import com.yahoo.petermwenda83.bean.staff.nonteaching.Workers;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolWorkersRegistrationDAO {
	
	 /**
	  * 
	  * @param Uuid
	  * @return the Workers
	  */
	public Workers getWorker(String Uuid);
	/**
	 * 
	 * @param worker
	 * @return Whether Worker was put successfully 
	 */
	public boolean putWorker(Workers worker);
	  /**
	   * 
	   * @param worker
	   * @param Uuid
	   * @return Whether Workers was edited successfully 
	   */
	public boolean editWorker(Workers worker,String Uuid);
     /**
      * 
      * @param worker
      * @param Uuid
      * @return Whether Workers was deleted successfully 
      */
	public boolean deleteWorker(Workers worker,String Uuid);
	
	/**
	 * 
	 * @return List of all Workers 
	 */
	public List<Workers> getAllWorker();
	 
	

}
