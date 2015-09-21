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
package com.yahoo.petermwenda83.model.exam.main;

import java.util.List;

import com.yahoo.petermwenda83.contoller.exam.Exam;
import com.yahoo.petermwenda83.contoller.exam.main.MainResults;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolMainResultsDAO {
	/**
	 * 
	 * @param StudentUuid
	 * @return
	 */
	public MainResults getMainResult(String StudentUuid);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean hasMainResult(Exam exam,Double Total,Double Points);
	
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean addMainResult(Exam exam,Double Total,Double Points);
	

	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean deductMainResult(Exam exam,Double Total,Double Points);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean deleteMainResult(Exam exam);
	

	 /**
	  * 
	  * @return Lists of all Students MainResults
	  */
	public List<MainResults> getAllMainResults();
	
	 
	


}
