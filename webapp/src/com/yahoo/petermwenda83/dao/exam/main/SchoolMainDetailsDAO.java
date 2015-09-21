/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.main;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.cat.CatDetails;
import com.yahoo.petermwenda83.bean.exam.main.MainDetails;

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
	 * @param MainUuid
	 * @param RoomNameUuid
	 * @param SubjectUuid
	 * @return
	 */
	public List<MainDetails> getAllMainDetail(String MainUuid, String RoomNameUuid, String SubjectUuid,String Term);
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
