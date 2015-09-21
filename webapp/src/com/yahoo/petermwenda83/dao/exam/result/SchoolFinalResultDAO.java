/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.result;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;

/**
 * @author peter
 *
 */
public interface SchoolFinalResultDAO {
	 /**
	  * 
	  * @param StudentUuid
	  * @return
	  */
	public FinalResult get(String StudentUuid);
	
	 /**
	  * 
	  * @param finalResult
	  * @return
	  */
	public boolean hasPoints (Exam exam,double Points);
	  /**
	   * 
	   * @param finalResult
	   * @return
	   */
	public boolean addPoints (Exam exam,double Points);
	
	 /**
	  * 
	  * @param exam
	  * @return
	  */
	public boolean deductPoints (Exam exam,double Points);
	   /**
	    * 
	    * @return
	    */
	public List<FinalResult> getAllFinalResult();

}
