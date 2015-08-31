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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.CatMarks;
import com.yahoo.petermwenda83.contoller.exam.CatResults;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;
import com.yahoo.petermwenda83.contoller.exam.MainResults;
import com.yahoo.petermwenda83.contoller.student.StudentSuper;
import com.yahoo.petermwenda83.contoller.student.Subject;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestExamDAO {

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
	
	private ExamDAO store;
	
	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getMainMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetMainMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks mainMarks = new MainMarks();
		mainMarks = store.getMainMarks(EXAM_MARKS_UUID);
		assertEquals(mainMarks.getUuid(),EXAM_MARKS_UUID);
		assertEquals(mainMarks.getStudentUuid(),STU_UUID);
		assertEquals(mainMarks.getExamTypeUuid(),TYPE_UUID);
		assertEquals(mainMarks.getSubjectUuid(),SUB_UUID);
		assertEquals(mainMarks.getMarks(),MARKS);
		//assertEquals(mainMarks.getSubmitdate(),EXAM_DATE);
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mainResults = new MainResults();
		mainResults = store.getExamResults(RESULTS_UUID);
		assertEquals(mainResults.getUuid(),RESULTS_UUID);
		assertEquals(mainResults.getSubjectUuid(),SUB_UUID);
		assertEquals(mainResults.getStudentUuid(),STU_UUID);
		assertEquals(mainResults.getTotal(),RESULTS_TOTALS);
		assertEquals(mainResults.getPoints(),RESULTS_POINTS);
		assertEquals(mainResults.getGrade(),RESULTS_GRADE);
		assertEquals(mainResults.getPosition(),1);
		assertEquals(mainResults.getRemarks(),RESULTS_REMARKS);
		//assertEquals(mainResults.getSubmitdate(),RESULTS_DATE);
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatMarks catMarks = new CatMarks();
		catMarks = store.getCatMarks(CAT_UUID);
		assertEquals(catMarks.getUuid(),CAT_UUID);
		assertEquals(catMarks.getStudentUuid(),STU_UUID);
		assertEquals(catMarks.getExamTypeUuid(),TYPE_UUID);
		assertEquals(catMarks.getSubjectUuid(),SUB_UUID);
		assertEquals(catMarks.getMarks(),MARKS);
		//assertEquals(catMarks.getSubmitdate(),RESULTS_DATE);
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults catResults = new CatResults();
		catResults = store.getCatResults(CAT_RESULTS);
		assertEquals(catResults.getUuid(),CAT_RESULTS);
		assertEquals(catResults.getStudentUuid(),STU_UUID);
		assertEquals(catResults.getSubjectUuid(),SUB_UUID);
		assertEquals(catResults.getTotal(),CAT_TOTALS);
		assertEquals(catResults.getPoints(),CAT_POINTS);
		assertEquals(catResults.getGrade(),CAT_GRADE);
		assertEquals(catResults.getPosition(),"1");
		assertEquals(catResults.getRemarks(),CAT_REMARKS);
		//assertEquals(catResults.getSubmitdate(),RESULTS_DATE);
	}

	

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		CatMarks catm = new CatMarks();
		
		catm.setStudentUuid(STU_UUID);
		catm.setExamTypeUuid(TYPE_UUID);
		catm.setSubjectUuid(SUB_UUID);
		catm.setMarks(MARKS2);
		catm.setSubmitdate(EXAM_DATE);
		assertTrue(store.putExamMarks(catm));
		
		MainMarks mainm = new MainMarks();
	
		mainm.setStudentUuid(STU_UUID);
		mainm.setExamTypeUuid(TYPE_UUID);
		mainm.setSubjectUuid(SUB_UUID);
		mainm.setMarks(MARKS2);
		mainm.setSubmitdate(EXAM_DATE);
		assertTrue(store.putExamMarks(mainm));
		
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamResults(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mainr = new MainResults();
		mainr.setStudentUuid(STU_UUID); 
		mainr.setSubjectUuid(SUB_UUID); 
		mainr.setTotal(RESULTS_TOTALS);
		mainr.setPoints(RESULTS_POINTS);
		mainr.setGrade(RESULTS_GRADE);
		mainr.setPosition(RESULTS_POSITION);
		mainr.setRemarks(RESULTS_REMARKS);
		mainr.setSubmitdate(EXAM_DATE);
		assertTrue(store.putExamResults(mainr));
		
		CatResults catr = new CatResults();
		catr.setStudentUuid(STU_UUID);
		catr.setSubjectUuid(SUB_UUID); 		
		catr.setTotal(RESULTS_TOTALS);
		catr.setPoints(RESULTS_POINTS);
		catr.setGrade(RESULTS_GRADE);
		catr.setPosition(RESULTS_POSITION);
		catr.setRemarks(RESULTS_REMARKS);
		catr.setSubmitdate(EXAM_DATE);
		assertTrue(store.putExamResults(catr));
	}


	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#editExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		StudentSuper studentSuper = new StudentSuper();
		studentSuper.setUuid(STU_UUID); 
		
		Subject subject = new Subject();
		subject.setUuid(SUB_UUID); 
		
		CatMarks catm = new CatMarks();
		catm.setMarks(MARKS3);
		catm.setSubmitdate(EXAM_DATE);
		assertTrue(store.editExamMarks(catm, studentSuper, subject));
		
		MainMarks mainm = new MainMarks(); 
		mainm.setMarks(MARKS3);
		mainm.setSubmitdate(EXAM_DATE);
		assertTrue(store.editExamMarks(mainm, studentSuper, subject));
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}
	
	

}
