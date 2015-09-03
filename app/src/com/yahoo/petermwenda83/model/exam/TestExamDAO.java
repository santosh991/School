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
import java.util.Date;
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
public class TestExamDAO {

	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	

    //FOR MAIN SUBJECT MARKS
   	final String MAIN_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
   	
   	final String MAIN_STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd",
			     MAIN_STU_UUID_NEW="A5151A2D-3D63-4092-BC67-AC23A2ED2606";
			    
   	
   	final String MAIN_TYPE_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2F",
		         MAIN_TYPE_UUID_NEW = "802ae460-a850-4a50-b2f0-58f5ed2d6bf9";
		         
   	
	final String MAIN_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			     MAIN_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
			     
   	
    
    final String MAIN_MARK = "78",
		         MAIN_MARK_NEW = "NEW78",
		         MAIN_MARK_UPDATE = "UPDATE78" ;
    
    final String MAIN_PERCENT = "86",
	             MAIN_PERCENT_NEW = "NEW86",
	             MAIN_PERCENT_UPDATE = "UPDATE86" ;
    
    final String MAIN_GRADE = "A-",
    		     MAIN_GRADE_NEW = "NEWA-",
    		     MAIN_GRADE_UPDATE = "UPDATEA-";
    
    final Date MAIN_DATE = new Date(new Long("1417633242000"));
    final Date MAIN_DATE_NEW = new Date(new Long("1417633242000"));
    final Date MAIN_DATE_UPDATE = new Date(new Long("1417633242000"));
    
    
  
   
	    //FOR CAT SUBJECT MARKS
     
        final String CAT_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
        
        final String CAT_STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd",
  			         CAT_STU_UUID_NEW="A5151A2D-3D63-4092-BC67-AC23A2ED2606";
  			        
        
        final String CAT_TYPE_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2F",
		             CAT_TYPE_UUID_NEW = "802ae460-a850-4a50-b2f0-58f5ed2d6bf9";
		             
        
        final String CAT_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			         CAT_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
			        
    

		final String CAT_MARK = "28",
			         CAT_MARK_NEW = "NEW28",
			         CAT_MARK_UPDATE = "UPDATE28" ;
	
		  final String CAT_SUBMARK = "34", 
	                   CAT_SUBMARK_NEW = "NEW34",
	                   CAT_SUBMARK_UPDATE = "UPDATE34";  
		
		
		final String CAT_PERCENT = "88",
		             CAT_PERCENT_NEW = "NEW88",
		             CAT_PERCENT_UPDATE = "UPDATE88" ;
		
		 final String CAT_GRADE = "A-",
    		          CAT_GRADE_NEW = "NEWA-",
    		          CAT_GRADE_UPDATE = "UPDATEA-";
		
		
		 final Date CAT_DATE = new Date(new Long("1417633242000"));
		 final Date CAT_DATE_NEW = new Date(new Long("1417633242000"));
		 final Date CAT_DATE_UPDATE = new Date(new Long("1417633242000"));
		
	
	
	//EXAM RESULTS
	final String MAIN_RESULT_UUID = "204916f3-346d-4297-bdfa-82215820fff5";
	
	
	 
	 final String MAIN_RESULT_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			      MAIN_RESULT_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
				
	 
	 final String MAIN_RESULT_STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd",
		          MAIN_RESULT_STU_UUID_NEW="A5151A2D-3D63-4092-BC67-AC23A2ED2606";
                 
	 
	 final String MAIN_RESULT_TOTALS = "877",
	              MAIN_RESULT_TOTALS_NEW = "NEW877",
	              MAIN_RESULT_TOTALS_UPDATE = "NEW877";
	 
	 final String MAIN_RESULT_POINTS = "56",
			      MAIN_RESULT_POINTS_NEW = "NEW56",
			      MAIN_RESULT_POINTS_UPDATE = "UPDATE56";
	 
	 final String MAIN_RESULT_GRADE = "C+",
		          MAIN_RESULT_GRADE_NEW = "NEWC+",
		          MAIN_RESULT_GRADE_UPDATE = "UPDATEC+";
	 
	 final String MAIN_RESULT_POSITION = "5",
	              MAIN_RESULT_POSITION_NEW = "NEW5",
	              MAIN_RESULT_PPOSITION_UPDATE = "UPDATE5";
	
	 
	 final String MAIN_RESULT_REMARKS = "Good",
                  MAIN_RESULT_REMARKS_NEW = "NEWGood",
                  MAIN_RESULT_REMARKS_UPDATE = "UPDATEGood";
         
    
	 final Date   MAIN_RESULT_DATE = new Date(new Long("1417633242000"));
	 final Date   MAIN_RESULT_DATE_NEW = new Date(new Long("1417633242000"));
	 final Date   MAIN_RESULT_DATE_UPDATE = new Date(new Long("1417633242000"));
	
	 //CAT RESULTS
	 final String CAT_RESULT_UUID = "a3c4dbbe-320c-464e-8b18-f6c90987e1d0";
		
	 
	 
	 final String CAT_RESULT_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			      CAT_RESULT_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
				 
	 
	 final String CAT_RESULT_STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd",
		          CAT_RESULT_STU_UUID_NEW="A5151A2D-3D63-4092-BC67-AC23A2ED2606";
                  
	 
	 
	 final String CAT_RESULT_TOTALS = "500",
	              CAT_RESULT_TOTALS_NEW = "NEW500",
	              CAT_RESULT_TOTALS_UPDATE = "UPDATE500";
	 
	 final String CAT_RESULT_POINTS = "60",
			      CAT_RESULT_POINTS_NEW = "NEW60",
			      CAT_RESULT_POINTS_UPDATE = "UPDATE60";
	 
	 final String CAT_RESULT_GRADE = "C-",
		          CAT_RESULT_GRADE_NEW = "NEWC-",
		          CAT_RESULT_GRADE_UPDATE = "UPDATEC-";
	 
	 final String CAT_RESULT_POSITION = "10",
	              CAT_RESULT_POSITION_NEW = "NEW10",
	              CAT_RESULT_PPOSITION_UPDATE = "UPDATE10";
	
	 
	 final String CAT_RESULT_REMARKS = "Poor",
                  CAT_RESULT_REMARKS_NEW = "NEWPoor",
                  CAT_RESULT_REMARKS_UPDATE = "UPDATEPoor";
         
    
	 final Date   CAT_RESULT_DATE = new Date(new Long("1417633242000"));
	 final Date   CAT_RESULT_DATE_NEW = new Date(new Long("1417633242000"));
	 final Date   CAT_RESULT_DATE_UPDATE = new Date(new Long("1417633242000"));
	
	private ExamDAO store;
	
	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getMainMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetMainMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks mainMarks = new MainMarks();
		mainMarks = store.getMainMarks(MAIN_STU_UUID,MAIN_SUB_UUID,MAIN_TYPE_UUID);
		assertEquals(mainMarks.getUuid(),MAIN_UUID);
		assertEquals(mainMarks.getStudentUuid(),MAIN_STU_UUID);
		assertEquals(mainMarks.getExamTypeUuid(),MAIN_TYPE_UUID);
		assertEquals(mainMarks.getSubjectUuid(),MAIN_SUB_UUID);
		assertEquals(mainMarks.getMarks(),MAIN_MARK);
		assertEquals(mainMarks.getPercent(),MAIN_PERCENT);
		assertEquals(mainMarks.getGrade(),MAIN_GRADE);
		//assertEquals(mainMarks.getSubmitdate(),MAIN_DATE);
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mainResults = new MainResults();
		mainResults = store.getExamResults(MAIN_RESULT_STU_UUID, MAIN_RESULT_SUB_UUID); 
		assertEquals(mainResults.getUuid(),MAIN_RESULT_UUID);
		assertEquals(mainResults.getSubjectUuid(),MAIN_RESULT_SUB_UUID);
		assertEquals(mainResults.getStudentUuid(),MAIN_RESULT_STU_UUID);
		assertEquals(mainResults.getTotal(),MAIN_RESULT_TOTALS);
		assertEquals(mainResults.getPoints(),MAIN_RESULT_POINTS);
		assertEquals(mainResults.getGrade(),MAIN_RESULT_GRADE);
		assertEquals(mainResults.getPosition(),MAIN_RESULT_POSITION);
		assertEquals(mainResults.getRemarks(),MAIN_RESULT_REMARKS);
		//assertEquals(mainResults.getSubmitdate(),MAIN_RESULTS_DATE);
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatMarks catMarks = new CatMarks();
		catMarks = store.getCatMarks(CAT_STU_UUID, CAT_SUB_UUID, CAT_TYPE_UUID);
		assertEquals(catMarks.getUuid(),CAT_UUID);
		assertEquals(catMarks.getStudentUuid(),CAT_STU_UUID);
		assertEquals(catMarks.getExamTypeUuid(),CAT_TYPE_UUID);
		assertEquals(catMarks.getSubjectUuid(),CAT_SUB_UUID);
		assertEquals(catMarks.getMarks(),CAT_MARK);
		assertEquals(catMarks.getSubmark(),CAT_SUBMARK);
		assertEquals(catMarks.getPercent(),CAT_PERCENT);
		//assertEquals(catMarks.getSubmitdate(),CAT_DATE);
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults catResults = new CatResults();
		catResults = store.getCatResults(CAT_RESULT_STU_UUID, CAT_RESULT_SUB_UUID); 
		assertEquals(catResults.getUuid(),CAT_RESULT_UUID);
		assertEquals(catResults.getStudentUuid(),CAT_RESULT_STU_UUID);
		assertEquals(catResults.getSubjectUuid(),CAT_RESULT_SUB_UUID);
		assertEquals(catResults.getTotal(),CAT_RESULT_TOTALS);
		assertEquals(catResults.getPoints(),CAT_RESULT_POINTS);
		assertEquals(catResults.getGrade(),CAT_RESULT_GRADE);
		assertEquals(catResults.getPosition(),CAT_RESULT_POSITION);
		assertEquals(catResults.getRemarks(),CAT_RESULT_REMARKS);
		//assertEquals(catResults.getSubmitdate(),CAT_RESULT_DATE);
	}

	

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		CatMarks catm = new CatMarks();
		
		catm.setStudentUuid(CAT_STU_UUID_NEW);
		catm.setExamTypeUuid(CAT_TYPE_UUID_NEW);
		catm.setSubjectUuid(CAT_SUB_UUID_NEW);
		catm.setMarks(CAT_MARK_NEW);
		catm.setSubmark(CAT_SUBMARK_NEW);
		catm.setPercent(CAT_PERCENT_NEW); 
		catm.setGrade(CAT_GRADE_NEW); 
		catm.setSubmitdate(CAT_DATE_NEW);
		assertTrue(store.putExamMarks(catm));
		
		MainMarks mainm = new MainMarks();
	
		mainm.setStudentUuid(MAIN_STU_UUID_NEW);
		mainm.setExamTypeUuid(MAIN_TYPE_UUID_NEW);
		mainm.setSubjectUuid(MAIN_SUB_UUID_NEW);
		mainm.setMarks(MAIN_MARK_NEW);
		mainm.setPercent(MAIN_PERCENT_NEW);
		mainm.setGrade(MAIN_GRADE_NEW); 
		mainm.setSubmitdate(MAIN_DATE_NEW);
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
		mainr.setStudentUuid(MAIN_RESULT_STU_UUID_NEW); 
		mainr.setSubjectUuid(MAIN_RESULT_SUB_UUID_NEW); 
		mainr.setTotal(MAIN_RESULT_TOTALS_NEW);
		mainr.setPoints(MAIN_RESULT_POINTS_NEW);
		mainr.setGrade(MAIN_RESULT_GRADE_NEW);
		mainr.setPosition(MAIN_RESULT_POSITION_NEW);
		mainr.setRemarks(MAIN_RESULT_REMARKS_NEW);
		mainr.setSubmitdate(MAIN_RESULT_DATE);
		assertTrue(store.putExamResults(mainr));
		
		CatResults catr = new CatResults();
		catr.setStudentUuid(CAT_RESULT_STU_UUID_NEW);
		catr.setSubjectUuid(CAT_RESULT_SUB_UUID_NEW); 		
		catr.setTotal(CAT_RESULT_TOTALS_NEW);
		catr.setPoints(CAT_RESULT_POINTS_NEW);
		catr.setGrade(CAT_RESULT_GRADE_NEW);
		catr.setPosition(CAT_RESULT_POSITION_NEW);
		catr.setRemarks(CAT_RESULT_REMARKS_NEW);
		catr.setSubmitdate(CAT_RESULT_DATE);
		assertTrue(store.putExamResults(catr));
	}


	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#editExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		CatMarks catm = new CatMarks();
		catm.setMarks(CAT_MARK_UPDATE);
		catm.setSubmitdate(CAT_DATE_UPDATE);
		
		catm.setStudentUuid(CAT_STU_UUID_NEW);
		catm.setSubjectUuid(CAT_SUB_UUID_NEW); 
		catm.setExamTypeUuid(CAT_TYPE_UUID_NEW);
		assertTrue(store.editExamMarks(catm));
		
		MainMarks mainm = new MainMarks(); 
		mainm.setMarks(MAIN_MARK_UPDATE);
		mainm.setSubmitdate(MAIN_DATE_UPDATE);
		
		mainm.setStudentUuid(MAIN_STU_UUID_NEW);
		mainm.setSubjectUuid(MAIN_SUB_UUID_NEW); 
		assertTrue(store.editExamMarks(mainm));
		
	}

	/**
	 * LoginScreen method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);

		CatMarks catm = new CatMarks();
		catm.setStudentUuid(CAT_STU_UUID_NEW);
		catm.setSubjectUuid(CAT_SUB_UUID_NEW); 
		catm.setExamTypeUuid(CAT_TYPE_UUID_NEW);
		assertTrue(store.deleteExamMarks(catm));
		
		MainMarks mainm = new MainMarks(); 
		mainm.setStudentUuid(MAIN_STU_UUID_NEW);
		mainm.setSubjectUuid(MAIN_SUB_UUID_NEW); 
		mainm.setExamTypeUuid(MAIN_TYPE_UUID_NEW);
		assertTrue(store.deleteExamMarks(mainm));
	}

	/**
	 * LoginScreen method for {@link com.yahooCatMarks.petermwenda83.model.exam.ExamDAO#deleteExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mainr = new MainResults();
		mainr.setStudentUuid(MAIN_RESULT_STU_UUID_NEW);
		mainr.setSubjectUuid(MAIN_RESULT_SUB_UUID_NEW);
		assertTrue(store.deleteExamResults(mainr));
		
		CatResults catr = new CatResults();
		catr.setStudentUuid(CAT_RESULT_STU_UUID_NEW);
		catr.setSubjectUuid(CAT_RESULT_SUB_UUID_NEW);
		assertTrue(store.deleteExamResults(catr));
	}
	
	

}
