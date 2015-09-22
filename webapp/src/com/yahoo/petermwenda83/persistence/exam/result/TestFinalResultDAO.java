/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.results.FinalResult;

/**
 * @author peter
 *
 */
public class TestFinalResultDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	final String UUID = "CC2AE569-9C2C-4CFD-AB53-34EC275FE4C6";
	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y";
	final double POINTS = 80;
	final String GRADE = "A";
	final int POSITION = 1;
	final String REMARKS = "Excellent"; 
	
	
	
	private FinalResultDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO#get(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalResult f = new FinalResult();
		f = store.get(STU_UUID);
		assertEquals(f.getUuid(),UUID);
		assertEquals(f.getStudentUuid(),STU_UUID);
		assertEquals(f.getPoints(),POINTS,0);
		assertEquals(f.getGrade(),GRADE);
		assertEquals(f.getRemarks(),REMARKS);
		assertEquals(f.getPosition(),POSITION);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO#hasPoints(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testHasPoints() {
		store = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalResult f = new FinalResult();
		f = store.get(STU_UUID);
		assertTrue(store.hasPoints(f, POINTS));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO#addPoints(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testAddPoints() {
		store = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalResult f = new FinalResult();
		f.setStudentUuid(STU_UUID);
		f.setPoints(POINTS);
		f.setGrade(GRADE);
		f.setRemarks(REMARKS);
		f.setPosition(POSITION);
		assertTrue(store.addPoints(f, POINTS));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO#deductPoints(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeductPoints() {
		store = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalResult f = new FinalResult();
		f.setStudentUuid(STU_UUID);
		f.setPoints(POINTS);
		f.setGrade(GRADE);
		f.setRemarks(REMARKS);
		f.setPosition(POSITION);
		assertTrue(store.deductPoints(f, POINTS));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO#getAllFinalResult()}.
	 */
	@Ignore
	@Test
	public void testGetAllFinalResult() {
		store = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<FinalResult> list = store.getAllFinalResult();	  
		for (FinalResult l : list) {
			System.out.println(l);	
		}
	}

}
