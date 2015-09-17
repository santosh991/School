/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.cat;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.cat.CatDetails;

/**
 * @author peter
 *
 */
public interface SchoolCatDetailsDAO {
	
	public CatDetails get(String mainUuid,String roomNameUuid,String subjectUuid);
	/**
	 * 
	 */
	public boolean put(CatDetails catdetals);
	/**
	 * 
	 * @param catdetals
	 * @return
	 */
	public boolean edit(CatDetails catdetals);
	/**
	 * 
	 * @param catdetals
	 * @return
	 */
	public boolean delete(CatDetails catdetals);
	/**
	 * 
	 * @param term
	 * @return
	 */
	
	public List<CatDetails> getAllCatDetails(String term);
	/**
	 * 
	 * @return
	 */
	public List<CatDetails> getAllCatDetails();
	
	

}
