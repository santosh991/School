/**
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.contoller.exam.MainMarks;

/**
 * @author peter
 *
 */
public class TestExamDAO {

	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	

    final String TYPE_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2F" ;
    final String EXAM_TYPE = "CAT" ;
    final String EXAM_TERM = "one" ;
    final String EXAM_CLASS = "Form 1 A" ;
    final String EXAM_YEAR = "2015" ;
    final String EXAM_DESCRI = " Cat 1" ;
    
    final String EXAM_MARKS_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
	final String STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd";//type uuid DAF7EC32-EA25-7D32-8708-2CC132446A2F
	final String SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E";
	final double MARKS = 78; 
	final Date EXAM_DATE = new Date(new Long("1402599642000"));
	
	
	
	
	private ExamDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getExamType(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamType() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.getExamType(TYPE_UUID);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getClasz(),EXAM_CLASS);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getMainMarks(java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGetMainMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks mainMarks = new MainMarks();
		mainMarks = store.getMainMarks(EXAM_MARKS_UUID);
		assertEquals(mainMarks.getUuid(),EXAM_MARKS_UUID);
		assertEquals(mainMarks.getStudentUuid(),STU_UUID);
		assertEquals(mainMarks.getExamTypeUuid(),TYPE_UUID);
		assertEquals(mainMarks.getSubjectUuid(),SUB_UUID);
		assertEquals(mainMarks.getMarks(),MARKS,0);
		assertEquals(mainMarks.getSubmitdate(),EXAM_DATE);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#getCatResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamType(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamType() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#putExamResults(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#editExamType(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamType() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#editExamMarks(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#editExamResults(com.yahoo.petermwenda83.contoller.exam.Exam, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamType(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamType() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamMarks(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamMarks() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamDAO#deleteExamResults(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamResults() {
		store = new ExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

}
