/**
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;

/**
 * @author peter
 *
 */
public interface TeacherExamDAO {
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
public ExamType getExamType(String uuid);
/**
 * 
 * @param uuid
 * @return
 */
public MainMarks getMainMarks(String uuid);
/**
 * 
 * @param uuid
 * @return
 */
public MainResults getExamResults(String uuid);

/**
 * 
 * @param uuid
 * @return
 */
public CatMarks getCatMarks(String uuid);
/**
 * 
 * @param uuid
 * @return
 */
public CatResults getCatResults(String uuid);
	
	 /**
	  * 
	  * @param exam
	  * @param uuid
	  * @return
	  */
	 
	public Exam putExamType(String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public Exam putExamMarks(Exam exam,String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public Exam putExamResults(Exam exam,String uuid);
	  /**
	   * 
	   * @param exam
	   * @param uuid
	   * @return
	   */
	public Exam editExamType(String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public Exam editExamMarks(Exam exam,String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public Exam editExamResults(Exam exam,String uuid);
	    /**
	     * 
	     * @param uuid
	     * @return
	     */
	public boolean deleteExamType(String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteExamMarks(String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteExamResults(String uuid);
	
}
