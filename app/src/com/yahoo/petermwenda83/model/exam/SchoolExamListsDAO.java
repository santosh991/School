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

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SchoolExamListsDAO {
	/**
	 * 
	 * @return Lists of all Students CatMarks
	 */
	public List<CatMarks> getAllCatMarks();
	 /**
	  * 
	  * @return Lists of all Students CatResults
	  */
	public List<CatResults> getAllCatResults();
	 /**
	  * 
	  * @return Lists of all Students MainMarks
	  */
	public List<MainMarks> getAllMainMarks();
	 /**
	  * 
	  * @return Lists of all Students MainResults
	  */
	public List<MainResults> getAllMainResults();

}
