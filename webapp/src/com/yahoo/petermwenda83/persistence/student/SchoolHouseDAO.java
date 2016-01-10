

package com.yahoo.petermwenda83.persistence.student;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentHouse;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolHouseDAO {
	

	
	   /**
	    * 
	    * @param house
	    * @return whether StudentHouse was got successfully
	    */
	public StudentHouse getHouse(String studentuuid);
	   
		/**
		 * 
		 * @param studentSuper
		 * @param studentHouse 
		 * 
		 */
		public boolean putHouse(StudentHouse studentHouse);
		
		/**
		  * 
		  * @param studentSuper
		  * @param studentHouse
		  * 
		  */
		public boolean updatetHouse(StudentHouse studentHouse,String studentuuid);
		
		/**
		   * 
		   * @param studentSuper
		   * @param studentHouse
		   * 
		   */
		public boolean deleteHouse(StudentHouse studentHouse);
		
		  /**
	     * 
	     * @return AllHouses
	     */
	  public List<StudentHouse> getHouseList();

}
