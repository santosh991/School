package com.yahoo.petermwenda83.model.exam.cat;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.cat.Cat;

public interface SchoolCatDAO {
	/**
	 * 
	 * @param catname
	 * @return
	 */
	public Cat get( String catname);
	  /**
	   * 
	   * @param cat
	   * @return
	   */
	public boolean put(Cat cat);
	   /**
	    * 
	    * @param cat
	    * @return
	    */
	public boolean delete(Cat cat);
	    /**
	     * 
	     * @return
	     */
	public List<Cat> getAllCats();

}
