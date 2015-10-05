/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.detail;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.ExamDetail;

/**
 * @author peter
 *
 */
public interface SchoolExamDetail {
	  /**
	   * 
	   * @param SchoolAccountUuid
	   * @return Exam Details for a School
	   */
	public ExamDetail get(String SchoolAccountUuid);
	 /**
	  * 
	  * @param SchoolAccountUuid
	  * @param Term
	  * @param Year
	  * @return Exam Details for a School given term and year
	  */
	public ExamDetail get(String SchoolAccountUuid,String Term,String Year);
	 /**
	  * 
	  * @param examDetail
	  * @return Whether Exam Details was put successfully 
	  */
	public boolean put(ExamDetail examDetail);
	 /**
	  * 
	  * @param examDetail
	  * @return Whether Exam Details was updated successfully 
	  */
	public boolean update(ExamDetail examDetail);
	 /**
	  * 
	  * @param examDetail
	  * @return Whether Exam Details was deleted successfully 
	  */
	public boolean delete(ExamDetail examDetail);
	 /**
	  * 
	  * @return List of all Exam Details for a school
	  */
	public List<ExamDetail> getAllExamdetails(String SchoolAccountUuid);


}
