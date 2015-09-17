/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.main.MainResults;

/**
 * @author peter
 *
 */
public class TestMainResultsDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	

	//EXAM RESULTS
	final String MAIN_RESULT_UUID = "204916f3-346d-4297-bdfa-82215820fff5";
	
				
	 
	 final String MAIN_RESULT_STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
		          MAIN_RESULT_STU_UUID_NEW="B38EE08C-6164-449E-8619-FF84676D5D46";
                 
	 
	 final double MAIN_RESULT_TOTALS = 877.0,
	              MAIN_RESULT_TOTALS_NEW = 877.1,
	              MAIN_RESULT_TOTALS_UPDATE = 877.2;
	 
	 final double MAIN_RESULT_POINTS = 56.0,
			      MAIN_RESULT_POINTS_NEW = 56.1,
			      MAIN_RESULT_POINTS_UPDATE = 56.2;
	 
	 final String MAIN_RESULT_GRADE = "C+",
		          MAIN_RESULT_GRADE_NEW = "B+",
		          MAIN_RESULT_GRADE_UPDATE = "UPDATEC+";
	 
	 final int MAIN_RESULT_POSITION = 5,
	              MAIN_RESULT_POSITION_NEW = 6,
	              MAIN_RESULT_PPOSITION_UPDATE = 7;
	
	 
	 final String MAIN_RESULT_REMARKS = "Good",
                  MAIN_RESULT_REMARKS_NEW = "NEWGood",
                  MAIN_RESULT_REMARKS_UPDATE = "UPDATEGood";
         
    
	 final Date   MAIN_RESULT_DATE = new Date(new Long("1417633242000"));
	 final Date   MAIN_RESULT_DATE_NEW = new Date(new Long("1417633242000"));
	 final Date   MAIN_RESULT_DATE_UPDATE = new Date(new Long("1417633242000"));
	
	
	
	private MainResultsDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#getMainResult(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetMainResult() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults m = new MainResults();
		m = store.getMainResult(MAIN_RESULT_STU_UUID); 
		assertEquals(m.getUuid(),MAIN_RESULT_UUID);
		assertEquals(m.getStudentUuid(),MAIN_RESULT_STU_UUID);
		assertEquals(m.getTotal(),MAIN_RESULT_TOTALS,0);
		assertEquals(m.getPoints(),MAIN_RESULT_POINTS,0);
		assertEquals(m.getGrade(),MAIN_RESULT_GRADE);
		assertEquals(m.getPosition(),MAIN_RESULT_POSITION);
		assertEquals(m.getRemarks(),MAIN_RESULT_REMARKS);
		//assertEquals(m.getSubmitdate(),MAIN_RESULTS_DATE);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#hasMainResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testHasMainResult() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mr = new MainResults();
		mr.setStudentUuid(MAIN_RESULT_STU_UUID);
		assertTrue(store.hasMainResult(mr, MAIN_RESULT_TOTALS, MAIN_RESULT_POINTS));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#addMainResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testAddMainResult() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mr = new MainResults();
		mr.setStudentUuid(MAIN_RESULT_STU_UUID); 
		mr.setGrade(MAIN_RESULT_GRADE_NEW);
		mr.setPosition(MAIN_RESULT_POSITION_NEW);
		mr.setRemarks(MAIN_RESULT_REMARKS_NEW);
		mr.setPoints(MAIN_RESULT_POINTS); 
		mr.setSubmitdate(MAIN_RESULT_DATE_NEW); 
		assertTrue(store.addMainResult(mr, MAIN_RESULT_TOTALS_NEW, MAIN_RESULT_POINTS_NEW));
	}
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#addMainResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testDeductMainResult() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mr = new MainResults();
		mr.setStudentUuid(MAIN_RESULT_STU_UUID); 
		mr.setGrade(MAIN_RESULT_GRADE_NEW);
		mr.setPosition(MAIN_RESULT_POSITION_NEW);
		mr.setRemarks(MAIN_RESULT_REMARKS_NEW);
		mr.setPoints(MAIN_RESULT_POINTS); 
		mr.setSubmitdate(MAIN_RESULT_DATE_NEW); 
		assertTrue(store.deductMainResult(mr, MAIN_RESULT_TOTALS_NEW, MAIN_RESULT_POINTS_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#deleteMainResult(com.yahoo.petermwenda83.contoller.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeleteMainResult() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainResults mr = new MainResults();
		mr.setStudentUuid(MAIN_RESULT_STU_UUID); 
		assertTrue(store.deleteMainResult(mr));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainResultsDAO#getAllMainResults()}.
	 */
	@Ignore
	@Test
	public void testGetAllMainResults() {
		store = new MainResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainResults> list = store.getAllMainResults();	
		for (MainResults l : list) {
			System.out.println(l);	
		}
	}

}
