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
package com.yahoo.petermwenda83.persistence.registration;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.Location;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolLocationDAO {
	

	/**
	   * 
	   * @param location
	   * @return whether Location was got successfully
	   */
	public Location getStudent(String studentuuid);
	
	 /**
	   * 
	   * @param studentSuper
	   * @param location
	   * 
	   */
	
	public boolean putStudent(Location location);
	
	 /**
	    * 
	    * @param studentSuper
	    * @param location
	    * 
	    */
	public boolean editStudent(Location location,String studentuuid);
	/**
	    * 
	    * @param studentSuper
	    * @param location
	    * 
	    */
	public boolean deleteStudent(Location location);
	/**
	 * 
	 * @return all student location
	 */
	public List<Location> getAllLocation();

}
