package com.yahoo.petermwenda83.persistence.exam.cat;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.cat.Cat;

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
