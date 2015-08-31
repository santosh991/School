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

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestExamtypeDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String TYPE_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2F",//DAF7EC32-EA25-7D32-8708-2CC132446A2F
    		TYPE_UUID_NEW = "e65ebcd2-31df-47df-96b2-0a104bda136f";
    final String EXAM_TYPE = "CAT",
    		EXAM_TYPE_NEW = "MAIN",
    		EXAM_TYPE_UPDATE = "UPADTE" ;
    final String EXAM_TERM = "one",
    		EXAM_TERM_NEW = "two",
    		EXAM_TERM_UPDATE = "three" ;
    final String EXAM_CLASS = "Form 1 A" ,
    		EXAM_CLASS_UPDATE = "Form 4 N";
    final String EXAM_YEAR = "2015",
    		EXAM_YEAR_UPDATE = "2014" ;
    final String EXAM_DESCRI = " Cat 1",
    		EXAM_DESCRI_NEW = " Cat 2",
    		EXAM_DESCRI_UPDATE = " Cat UPDATE" ;
    
    final String EXAM_MARKS_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
	final String STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd";//type uuid DAF7EC32-EA25-7D32-8708-2CC132446A2F
	final String SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E";//D0F7EC32-EA25-7D32-8708-2CC132446A2E
	final String MARKS = "78", MARKS2 = "60",MARKS3 = "93";                                       
	final Date EXAM_DATE = new Date(new Long("1417633242000"));
	
	final String RESULTS_UUID = "204916f3-346d-4297-bdfa-82215820fff5";
	final String RESULTS_TOTALS = "877";
	final String RESULTS_POINTS = "78";
	final String RESULTS_GRADE = "A-";
	final String RESULTS_POSITION = "1";
	final String RESULTS_REMARKS = "Exellent";
	final Date RESULTS_DATE = new Date(new Long("1417633242000"));
	
	final String CAT_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
	
	final String CAT_RESULTS = "a3c4dbbe-320c-464e-8b18-f6c90987e1d0";
	final String CAT_TOTALS ="500";
	final String CAT_POINTS = "60";
	final String CAT_GRADE = "C-";
	final String CAT_REMARKS = "fair";
	final String OUTOF = "30";
	
	final String CEXAMNO = "EN65116",CEXAMNO2 = "EN65773";
	
	private ExamtypeDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getExamType(java.lang.String)}.
	 */
	@Test
	public void testGetExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.getExamType(TYPE_UUID);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getClasz(),EXAM_CLASS);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		assertEquals(examType.getExamno(),CEXAMNO);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getExamTypes(java.lang.String)}.
	 */
	@Test
	public void testGetExamTypes() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.getExamTypes(CEXAMNO);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getClasz(),EXAM_CLASS);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		assertEquals(examType.getExamno(),CEXAMNO);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#get(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGet() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.get(EXAM_TYPE, EXAM_CLASS, EXAM_DESCRI);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getClasz(),EXAM_CLASS);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		//assertEquals(examType.getExamno(),CEXAMNO);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#putExamType(com.yahoo.petermwenda83.contoller.exam.ExamType)}.
	 */
	@Test
	public void testPutExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
    	String Uuid = examType.getUuid();
    	//System.out.println(Uuid);
		examType.setUuid(Uuid);
		examType.setExamtype(EXAM_TYPE_NEW);
		examType.setTerm(EXAM_TERM_NEW);
	    examType.setYear(EXAM_YEAR);
		examType.setClasz(EXAM_CLASS);
		examType.setOutof(OUTOF); 
	    examType.setDescription(EXAM_DESCRI_NEW);
	    examType.setExamno(CEXAMNO);
	    assertTrue(store.putExamType(examType));
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#editExamType(com.yahoo.petermwenda83.contoller.exam.ExamType, java.lang.String)}.
	 */
	@Test
	public void testEditExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType type = new ExamType();//
		type.setExamtype(EXAM_TYPE_UPDATE);
		type.setTerm(EXAM_TERM_UPDATE);
		type.setYear(EXAM_YEAR_UPDATE);
		type.setClasz(EXAM_CLASS_UPDATE);
		type.setOutof(OUTOF); 
		type.setDescription(EXAM_DESCRI_UPDATE);
		type.setExamno(CEXAMNO2);
	    assertTrue(store.editExamType(type, TYPE_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#editExamType(com.yahoo.petermwenda83.contoller.exam.ExamType, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType type = new ExamType();
		String uuid = "08e6abb2-e1f6-4b84-b3f6-39e4ba80fe39";
		type.setUuid(uuid);
		assertTrue(store.deleteExamType(type));
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getAllExamtype()}.
	 */
	@Test
	public void testGetAllExamtype() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ExamType> list = store.getAllExamtype();	
		for (ExamType l : list) {
			System.out.println(l);
			
		}
	}

}
