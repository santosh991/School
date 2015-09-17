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
import com.yahoo.petermwenda83.contoller.exam.cat.CatMarks;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
   public interface SchoolCatSubMarkDAO {
	/**
	 * 
	 * @param StudentUuid
	 * @param Subjectuuid
	 * @return
	 */
	public CatMarks getCatMark(String StudentUuid,String Subjectuuid);
	
	/**
	 * 
	 * @param exam
	 * @return
	 */
	public boolean hasCatMark(Exam exam,Double Marks);
	/**
	 * 
	 * @param exam
	 * @return
	 */
		public boolean addCatMark(Exam exam,Double Marks,Double Points);
		/**
		 * 
		 * @param exam
		 * @return
		 */
		public boolean editCatMark(Exam exam,Double Marks,Double Points);
		
		/**
		 * 
		 * @param exam
		 * @return
		 */
		public boolean deleteCatMark(Exam exam);
		

		/**
		 * 
		 * @return Lists of all Students CatMarks
		 */
		public List<CatMarks> getAllCatSubMark();
		


}
