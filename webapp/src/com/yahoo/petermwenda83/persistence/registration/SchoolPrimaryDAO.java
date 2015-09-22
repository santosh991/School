/**
 * 
 */
package com.yahoo.petermwenda83.persistence.registration;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.Primary;

/**
 * @author peter
 *
 */
public interface SchoolPrimaryDAO {
	  /**
	   * 
	   * @param StudentUuid
	   * @return Student Primary School Details
	   */
	public Primary get(String StudentUuid);
	   /**
	    * 
	    * @param primary
	    * @return Whether Student Primary School Details has been put
	   * successfully 
	    */
	public boolean put(Primary primary);
	  /**
	   * 
	   * @param primary
	   * @param StudentUuid
	   * @return Whether Student Primary School Details has been 
	   * successfully edited
	   */
	public boolean edit(Primary primary,String StudentUuid);
	   /**
	    * 
	    * @param primary
	    * @param StudentUuid
	    * @return Whether Student Primary School Details has been
	   * successfully deleted
	    */
	public boolean delete(Primary primary,String StudentUuid);
	    /**
	     * 
	     * @return List of all Students Primary Details
	     */
	public List<Primary> getAllPrimary();

}
