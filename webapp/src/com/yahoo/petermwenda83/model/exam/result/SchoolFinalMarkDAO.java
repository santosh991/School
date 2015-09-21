/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.result;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.results.FinalMark;

/**
 * @author peter
 *
 */
public interface SchoolFinalMarkDAO {
	 /**
	  * 
	  * @param StudentUuid
	  * @return
	  */
	public FinalMark get(String StudentUuid,String SubjectUuid);
	
	/**
	 * 
	 * @param finalMark
	 * @return
	 */
	public boolean hasMark (Exam exam,double Marks);
	
	  /**
	   * 
	   * @param finalMark
	   * @return
	   */
	public boolean addMark (Exam exam,double Marks);
	
	 /**
	  * 
	  * @param exam
	  * @return
	  */
	public boolean deductMark (Exam exam,double Marks);
	   /**
	    * 
	    * @return
	    */
	public List<FinalMark> getAllFinalMark();

}
