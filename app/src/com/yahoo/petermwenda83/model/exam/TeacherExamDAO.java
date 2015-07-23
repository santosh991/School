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
import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.contoller.student.Subject;

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
	 
	public boolean putExamType(ExamType examType);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public boolean putExamMarks(Exam exam);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public boolean putExamResults(Exam exam);
	  /**
	   * 
	   * @param exam
	   * @param uuid
	   * @return
	   */
	public boolean editExamType(ExamType type,String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * @return
	 */
	public boolean editExamMarks(Exam exam,Student student,Subject subject);
	
	    /**
	     * 
	     * @param uuid
	     * @return
	     */
	public boolean deleteExamType(ExamType type);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteExamMarks(Exam exam,String uuid);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean deleteExamResults(Exam exam,String uuid);
	
}
