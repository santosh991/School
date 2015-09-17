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
package com.yahoo.petermwenda83.model.exam.cat;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.cat.CatResults;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolCatResultsDAO {
	
	/**
	 * 
	 * @param StudentUuid
	 * @return
	 */
	public CatResults getCatResults(String StudentUuid);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean hasCatResult(Exam exam,Double Total,Double Points);
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean addCatMark(Exam exam,Double Total,Double Points);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean deductCatMark(Exam exam,Double Total,Double Points);
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean deleteCatMark(Exam exam);
	
	 /**
	  * 
	  * @return Lists of all Students CatResults
	  */
	public List<CatResults> getAllCatResults();
	

}
