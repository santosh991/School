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

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestExamListsDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	private ExamListsDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamListsDAO#getAllCatMarks()}.
	 */
	@Ignore
	@Test
	public void testGetAllCatMarks() {
		store = new ExamListsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatMarks> list = store.getAllCatMarks();	
		for (CatMarks l : list) {
			System.out.println(l);
			
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamListsDAO#getAllCatResults()}.
	 */
	//@Ignore
	@Test
	public void testGetAllCatResults() {
		store = new ExamListsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatResults> list = store.getAllCatResults();	
		for (CatResults l : list) {
			System.out.println(l);
			
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamListsDAO#getAllMainMarks()}.
	 */
	@Ignore
	@Test
	public void testGetAllMainMarks() {
		store = new ExamListsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainMarks> list = store.getAllMainMarks();	
		for (MainMarks l : list) {
			System.out.println(l);
			
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamListsDAO#getAllMainResults()}.
	 */
	@Ignore
	@Test
	public void testGetAllMainResults() {
		store = new ExamListsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainResults> list = store.getAllMainResults();	
		for (MainResults l : list) {
			System.out.println(l);
			
		}
	}

}
