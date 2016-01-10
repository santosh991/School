

/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/
package com.yahoo.petermwenda83.persistence.exam;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.Perfomance;

/**
 * @author peter
 *
 */
public interface SchoolPerfomanceDAO {
	
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param classRoomUuid
	 * @param studentUuid
	 * @return
	 */
	public List<Perfomance> getPerformance(String schoolAccountUuid,String classRoomUuid,String studentUuid); 
	
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param classRoomUuid
	 * @param studentUuid
	 * @return
	 */
	public Perfomance getPerformanceObject(String studentUuid);  
	
	   /**
	    * 
	    * @param perfomance
	    * @return
	    */
	public boolean deletePerfomance(Perfomance perfomance);
	   /**
	    * 
	    * @param schoolAccountUuid
	    * @return
	    */
	public List<Perfomance> getPerfomanceList(String schoolAccountUuid,String classRoomUuid);
	
	
	public List<Perfomance> getPerfomanceListDistinct(String schoolAccountUuid,String classRoomUuid);
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @param ClassesUuid
	 * @return
	 */
	public List<Perfomance> getClassPerfomanceList(String schoolAccountUuid,String ClassesUuid);
	
	
}
