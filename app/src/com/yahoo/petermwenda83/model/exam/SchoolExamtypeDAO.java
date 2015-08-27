/**
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.ExamType;

/**
 * @author peter
 *
 */
public interface SchoolExamtypeDAO {
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

/**
 * 
 * @param examtype
 * @param clasz
 * @param description
 * @return
 */
public ExamType get(String examtype,String clasz,String description);

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
public boolean editExamType(ExamType type,String uuid);

/**
 * 
 * @param uuid
 * 
 */
public boolean deleteExamType(ExamType type);
/**
 * 
 * @return
 */
public List <ExamType> getAllExamtype();

}
