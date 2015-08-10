/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.model.exam;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
import com.yahoo.petermwenda83.contoller.student.StudentSuper;
import com.yahoo.petermwenda83.contoller.student.Subject;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface TeacherExamDAO {
	
	/**
	 * 
	 * @param uuid
	 * 
	 */
public ExamType getExamType(String uuid);

  /**
   * 
   * @param examno
   * 
   */
public ExamType getExamTypes(String examno);


public ExamType get(String examtype,String clasz,String description);
/**
 * 
 * @param uuid
 * 
 */
public MainMarks getMainMarks(String uuid);
/**
 * 
 * @param uuid
 * 
 */
public MainResults getExamResults(String uuid);

/**
 * 
 * @param uuid
 * 
 */
public CatMarks getCatMarks(String uuid);
/**
 * 
 * @param uuid
 * 
 */
public CatResults getCatResults(String uuid);
	
	 /**
	  * 
	  * @param exam
	  * @param uuid
	  *
	  */
	 
	public boolean putExamType(ExamType examType);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * 
	 */
	public boolean putExamMarks(Exam exam);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * 
	 */
	public boolean putExamResults(Exam exam);
	  /**
	   * 
	   * @param exam
	   * @param uuid
	   * 
	   */
	public boolean editExamType(ExamType type,String uuid);
	/**
	 * 
	 * @param exam
	 * @param uuid
	 * 
	 */
	public boolean editExamMarks(Exam exam,StudentSuper studentSuper,Subject subject);
	
	    /**
	     * 
	     * @param uuid
	     * 
	     */
	public boolean deleteExamType(ExamType type);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteExamMarks(Exam exam,String uuid);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteExamResults(Exam exam,String uuid);
	
	public List <ExamType> getAllExamtype();
	
}
