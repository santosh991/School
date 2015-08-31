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

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.ExamType;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
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
