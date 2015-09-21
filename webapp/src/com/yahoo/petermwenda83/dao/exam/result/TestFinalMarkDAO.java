/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.results.FinalMark;

/**
 * @author peter
 *
 */
public class TestFinalMarkDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String UUID = "4ABEC752-0015-4005-ADC7-00D66C8C6EC1";
	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y";
	final String SUB_UUID ="b9bbd718-b32f-4466-ab34-42f544ff900e";
	final double MARK = 74;
	final String GRADE = "A-";
	
	
	private FinalMarkDAO  store;
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO#get(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new FinalMarkDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalMark f = new FinalMark();
		f = store.get(STU_UUID,SUB_UUID);
		assertEquals(f.getUuid(),UUID);
		assertEquals(f.getStudentUuid(),STU_UUID);
		assertEquals(f.getMarks(),MARK,0);
		assertEquals(f.getGrade(),GRADE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO#hasMark(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testHasMark() {
		store = new FinalMarkDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalMark f = new FinalMark();
		f.setStudentUuid(STU_UUID);
		f.setSubjectUuid(SUB_UUID);
		assertTrue(store.hasMark(f, MARK));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO#addMark(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testAddMark() {
		store = new FinalMarkDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalMark f = new FinalMark();
		f.setStudentUuid(STU_UUID);
		f.setSubjectUuid(SUB_UUID); 
		f.setMarks(MARK);
		f.setGrade(GRADE);
		assertTrue(store.addMark(f, MARK));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO#deductMark(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeductMark() {
		store = new FinalMarkDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		FinalMark f = new FinalMark();
		f.setStudentUuid(STU_UUID);
		f.setSubjectUuid(SUB_UUID); 
		f.setMarks(MARK);
		f.setGrade(GRADE);
		assertTrue(store.deductMark(f, MARK));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.result.FinalMarkDAO#getAllFinalMark()}.
	 */
	@Ignore
	@Test
	public void testGetAllFinalMark() {
		store = new FinalMarkDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<FinalMark> list = store.getAllFinalMark();	 
		for (FinalMark l : list) {
			System.out.println(l);	
		}
	}

}
