/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.main.Main;

/**
 * @author peter
 *
 */
public interface SchoolMainDAO {
	/**
	 * 
	 * @param mainname
	 * @return
	 */
	public Main get(String mainname);
	  /**
	   * 
	   * @param main
	   * @return
	   */
	public boolean put(Main main);
	   /**
	    * 
	    * @param main
	    * @return
	    */
	public boolean delete(Main main);
	    /**
	     * 
	     * @return
	     */
	public List<Main> getAllMain();

}
