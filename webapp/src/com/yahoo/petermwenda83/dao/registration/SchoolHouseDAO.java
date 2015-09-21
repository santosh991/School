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

import com.yahoo.petermwenda83.bean.student.House;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolHouseDAO {
	

	
	   /**
	    * 
	    * @param house
	    * @return whether House was got successfully
	    */
	public House getStudent(String studentuuid);
	   
		/**
		 * 
		 * @param studentSuper
		 * @param house 
		 * 
		 */
		public boolean putStudent(House house);
		
		/**
		  * 
		  * @param studentSuper
		  * @param house
		  * 
		  */
		public boolean editStudent(House house,String studentuuid);
		
		/**
		   * 
		   * @param studentSuper
		   * @param house
		   * 
		   */
		public boolean deleteStudent(House house);
		
		  /**
	     * 
	     * @return AllHouses
	     */
	  public List<House> getAllHouse();

}
