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
package com.yahoo.petermwenda83.dao.exam.main;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.exam.main.MainMarks;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
 public interface SchoolMainSubMarkDAO {
	
 /**
  * 
  * @param StudentUuid
  * @param Subjectuuid
  * @return
  */
  public MainMarks getMainMark(String StudentUuid,String Subjectuuid);
	 
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean hasMainMark(Exam exam,Double Marks);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean addMainMark(Exam exam,Double Percent,Double Points);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean editMainMark(Exam exam,Double Percent,Double Points);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean deleteMainMark(Exam exam);
	
	 /**
	  * 
	  * @return Lists of all Students MainMarks
	  */
	public List<MainMarks> getAllMainSubMark();
	
	
	
}
