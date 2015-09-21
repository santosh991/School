/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.main.MainDetails;

/**
 * @author peter
 *
 */
public interface SchoolMainDetailsDAO {
	
	public MainDetails get(String mainUuid,String roomNameUuid,String subjectUuid);
	/**
	 * 
	 * @param maindetails
	 * @return
	 */
	public boolean put(MainDetails maindetails);
	/**
	 * 
	 * @param maindetails
	 * @return
	 */
	public boolean edit(MainDetails maindetails);
	/**
	 * 
	 * @param maindetails
	 * @return
	 */
	public boolean delete(MainDetails maindetails);
	/**
	 * 
	 * @param term
	 * @return
	 */
	
	public List<MainDetails> getAllMainDetails(String term);
	 /**
	  * 
	  * @return
	  */
	public List<MainDetails> getAllMainDetails();

}
