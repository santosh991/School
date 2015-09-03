/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolExamDAO {
	

/**
 * 
 * @param uuid
 * 
 */
public MainMarks getMainMarks(String StudentUuid,String Subjectuuid,String ExamTypeUuid);
/**
 * 
 * @param uuid
 * 
 */
public MainResults getExamResults(String StudentUuid,String Subjectuuid);

/**
 * 
 * @param uuid
 * 
 */
public CatMarks getCatMarks(String StudentUuid,String Subjectuuid,String ExamTypeUuid);
/**
 * 
 * @param uuid
 * 
 */
public CatResults getCatResults(String StudentUuid,String Subjectuuid);
	
	 
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
	public boolean editExamMarks(Exam exam);
	
	 
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteExamMarks(Exam exam);
	/**
	 * 
	 * @param uuid
	 * 
	 */
	public boolean deleteExamResults(Exam exam);
	
	
	
}
