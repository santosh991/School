/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;

/**
 * @author peter
 *
 */
public class TestPaper3DAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446",
		           STU_UUID_NEW="d8a28954-5f2e-4899-8e3f-37b48694778b";

    private String EXAM_DETAIL_UUID ="38EFA2D4-352D-4BC0-887F-9CA227950501",
		           EXAM_DETAIL_UUID_NEW="110E213B-1765-43F5-8435-E8C91F51F289";

  

    private String SYS_USER ="peter";

    final Date SUBMIT_DATE = new Date(new Long("1419410347000") );

    private double Paper3 = 35;

     private Paper3DAO storenew = new Paper3DAO(databaseName, Host, databaseUsername, databasePassword, databasePort);

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.Paper3DAO#hasPaper3(com.yahoo.petermwenda83.bean.exam.result.Perfomance)}.
	 */
    @Ignore
	@Test
	public void testHasPaper3() {
		Perfomance p = new Perfomance();
		p.setStudentUuid(STU_UUID);
		p.setExamDetailUuid(EXAM_DETAIL_UUID); 
		assertTrue(storenew.hasPaper3(p));  
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.Paper3DAO#putPaper3(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)}.
	 */
    
    //@Ignore
	@Test
	public void testPutPaper3() {
		Perfomance p = new Perfomance();
		p.setStudentUuid(STU_UUID_NEW);
		p.setExamDetailUuid(EXAM_DETAIL_UUID_NEW); 
		p.setSysUser(SYS_USER);
		p.setSubmitDate(SUBMIT_DATE);
		assertTrue(storenew.putPaper3(p, Paper3));
	}

}
