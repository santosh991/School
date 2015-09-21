/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.cat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.cat.CatResults;

/**
 * @author peter
 *
 */
public class TestCatResultsDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	 //CAT RESULTS
	 final String CAT_RESULT_UUID = "a3c4dbbe-320c-464e-8b18-f6c90987e1d0";
		
	 
	 final String CAT_RESULT_STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
		          CAT_RESULT_STU_UUID_NEW="A7DB662A-F029-4E61-B9DA-8F8FBB6E9C2B";
                 
	 
	 
	 final double CAT_RESULT_TOTALS = 500.0,
	              CAT_RESULT_TOTALS_NEW = 900.1,
	              CAT_RESULT_TOTALS_UPDATE = 900.2;
	 
	 final double CAT_RESULT_POINTS = 60.0,
			      CAT_RESULT_POINTS_NEW = 90.1,
			      CAT_RESULT_POINTS_UPDATE = 80.2;
	 
	 final String CAT_RESULT_GRADE = "C-",
		          CAT_RESULT_GRADE_NEW = "B-",
		          CAT_RESULT_GRADE_UPDATE = "UPDATEC-";
	 
	 final int CAT_RESULT_POSITION = 10,
	              CAT_RESULT_POSITION_NEW =11,
	              CAT_RESULT_PPOSITION_UPDATE = 12;
	
	 
	 final String CAT_RESULT_REMARKS = "Poor",
                 CAT_RESULT_REMARKS_NEW = "NEWPoor",
                 CAT_RESULT_REMARKS_UPDATE = "UPDATEPoor";
        
   
	 final Date   CAT_RESULT_DATE = new Date(new Long("1417633242000"));
	 final Date   CAT_RESULT_DATE_NEW = new Date(new Long("1417633242000"));
	 final Date   CAT_RESULT_DATE_UPDATE = new Date(new Long("1417633242000"));
	 
	 private CatResultsDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#getCatResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatResults() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults catResults = new CatResults();
		catResults = store.getCatResults(CAT_RESULT_STU_UUID); 
		assertEquals(catResults.getUuid(),CAT_RESULT_UUID);
		assertEquals(catResults.getStudentUuid(),CAT_RESULT_STU_UUID);
		assertEquals(catResults.getTotal(),CAT_RESULT_TOTALS,0);
		assertEquals(catResults.getPoints(),CAT_RESULT_POINTS,0);
		assertEquals(catResults.getGrade(),CAT_RESULT_GRADE);
		assertEquals(catResults.getPosition(),CAT_RESULT_POSITION);
		assertEquals(catResults.getRemarks(),CAT_RESULT_REMARKS);
		//assertEquals(catResults.getSubmitdate(),CAT_RESULT_DATE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#hasCatResult(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testHasCatResult() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults c = new CatResults();
		c.setStudentUuid(CAT_RESULT_STU_UUID); 
		assertTrue(store.hasCatResult(c, CAT_RESULT_TOTALS, CAT_RESULT_POINTS)); 
		
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#addCatMark(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	//@Ignore
	@Test
	public void testAddCatMark() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults c = new CatResults();
		c.setStudentUuid(CAT_RESULT_STU_UUID_NEW); 
		c.setGrade(CAT_RESULT_GRADE_NEW);
		c.setPosition(CAT_RESULT_POSITION_NEW);
		c.setRemarks(CAT_RESULT_REMARKS_NEW);
		c.setSubmitdate(CAT_RESULT_DATE_NEW); 
		assertTrue(store.addCatMark(c, CAT_RESULT_TOTALS_NEW, CAT_RESULT_POINTS_NEW));
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#addCatMark(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testDeductCatMark() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults c = new CatResults();
		c.setStudentUuid(CAT_RESULT_STU_UUID_NEW); 
		c.setGrade(CAT_RESULT_GRADE_NEW);
		c.setPosition(CAT_RESULT_POSITION_NEW);
		c.setRemarks(CAT_RESULT_REMARKS_NEW);
		c.setSubmitdate(CAT_RESULT_DATE_NEW); 
		assertTrue(store.deductCatMark(c, CAT_RESULT_TOTALS_NEW, CAT_RESULT_POINTS_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#deleteCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeleteCatMark() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatResults c = new CatResults();
		c.setStudentUuid(CAT_RESULT_STU_UUID_NEW); 
		assertTrue(store.deleteCatMark(c));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.cat.SchoolCatResultsDAO#getAllCatResults()}.
	 */
	@Ignore
	@Test
	public void testGetAllCatResults() {
		store = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatResults> list = store.getAllCatResults();	
		for (CatResults l : list) {
			System.out.println(l); 
		}
	}

}
