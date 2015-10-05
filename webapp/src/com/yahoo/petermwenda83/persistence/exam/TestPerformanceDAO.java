/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

/**
 * @author peter
 *
 */
public class TestPerformanceDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	 private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446";
     private String EXAM_DETAIL_UUID ="38EFA2D4-352D-4BC0-887F-9CA227950501";
     
     double CAT1 = 20;
     double CAT2 = 19;
     double PAPER1 = 60;
     double PAPER2 = 56;
     double PAPER3 = 12;
     
     private String USER ="Karani";
     final Date SUBMIT_DATE = new Date(new Long("1419410347000") );
	               


	
	private PerformanceDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.PerformanceDAO#getByStudentUuid(java.lang.String)}.
	 */
	@Ignore
	@Test 
	public void testGet() {
		store = new PerformanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Perfomance p = new Perfomance();
		p = store.get(STU_UUID, EXAM_DETAIL_UUID);
		assertEquals(p.getStudentUuid(),STU_UUID);
		assertEquals(p.getExamDetailUuid(),EXAM_DETAIL_UUID);
		assertEquals(p.getCat1(),CAT1,0);
		assertEquals(p.getCat2(),CAT2,0);
		assertEquals(p.getPaper1(),PAPER1,0);
		assertEquals(p.getPaper2(),PAPER2,0);
		assertEquals(p.getPaper3(),PAPER3,0);
		assertEquals(p.getSysUser(),USER);
		//assertEquals(p.getSubmitDate(),SUBMIT_DATE);
		
	}


	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.PerformanceDAO#delete(com.yahoo.petermwenda83.bean.exam.result.Perfomance)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store = new PerformanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Perfomance p = new Perfomance();
		p.setStudentUuid(STU_UUID);
		p.setExamDetailUuid(EXAM_DETAIL_UUID); 
		assertTrue(store.delete(p));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.PerformanceDAO#getAllPerfomance()}.
	 */
	//@Ignore
	@Test
	public void testGetAllPerfomance() {
		store = new PerformanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Perfomance> list = store.getAllPerfomance();	
		for (Perfomance p : list) {
			System.out.println(p);
		}
	}

}
