/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.detail;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.ExamDetail;

/**
 * @author peter
 *
 */
public class TestExamDetailDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID = "38EFA2D4-352D-4BC0-887F-9CA227950501";
	
	private String SCHOOL_UUID ="B643876D-B90C-435C-B215-CC558D596626",
			       SCHOOL_UUID_NEW ="0DCE250C-CA1B-46DB-AB53-8B076352B876";
	
	private String EXAM_UUID ="AE24F15B-5038-4A15-8607-1DB2A7A0B7DE";
	private String CLASSROOM_UUID ="13C66627-450B-4808-A6A9-E26256BF2E68";
	private String SUBJECT_UUID ="44f23b3c-e066-4b45-931c-0e8073d3a93a";
	
	
	private String TERM = "ONE",
			       TERM_NEW = "TWO",
			       TERM_UPDATE = "THREE";
	
	private String YEAR ="2015",
			       YEAR_NEW ="2016",
			       YEAR_UPDATE ="2020";
	
	private int OUT_OF = 70,
			    OUT_OF_NEW = 80,
			    OUT_OF_UPDATE = 90;
	
	
	private ExamDetailDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#get(java.lang.String)}.
	 */
    @Ignore
	@Test
	public void testGetString() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamDetail e = new ExamDetail();
		e = store.get(SCHOOL_UUID,EXAM_UUID);
		assertEquals(e.getUuid(),UUID);
		assertEquals(e.getSchoolAccountUuid(),SCHOOL_UUID);
		assertEquals(e.getExamUuid(),EXAM_UUID);
		assertEquals(e.getClassRoomUuid(),CLASSROOM_UUID);
		assertEquals(e.getSubjectUuid(),SUBJECT_UUID);
		assertEquals(e.getTerm(),TERM);
		assertEquals(e.getYear(),YEAR);
		assertEquals(e.getOutOf(),OUT_OF);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#get(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStringStringString() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamDetail e = new ExamDetail();
		e = store.get(SCHOOL_UUID,EXAM_UUID, TERM, YEAR);
		assertEquals(e.getUuid(),UUID);
		assertEquals(e.getSchoolAccountUuid(),SCHOOL_UUID);
		assertEquals(e.getExamUuid(),EXAM_UUID);
		assertEquals(e.getClassRoomUuid(),CLASSROOM_UUID);
		assertEquals(e.getSubjectUuid(),SUBJECT_UUID);
		assertEquals(e.getTerm(),TERM);
		assertEquals(e.getYear(),YEAR);
		assertEquals(e.getOutOf(),OUT_OF);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#put(com.yahoo.petermwenda83.bean.exam.ExamDetail)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamDetail e = new ExamDetail();
		e.setSchoolAccountUuid(SCHOOL_UUID_NEW);
		e.setExamUuid(EXAM_UUID);
		e.setClassRoomUuid(CLASSROOM_UUID);
		e.setSubjectUuid(SUBJECT_UUID);
		e.setTerm(TERM_NEW);
		e.setYear(YEAR_NEW);
		e.setOutOf(OUT_OF_NEW);
		assertTrue(store.put(e)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#update(com.yahoo.petermwenda83.bean.exam.ExamDetail)}.
	 */
	@Ignore
	@Test
	public void testUpdate() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamDetail e = new ExamDetail();
		e.setSchoolAccountUuid(SCHOOL_UUID_NEW);
		e.setExamUuid(EXAM_UUID);
		e.setClassRoomUuid(CLASSROOM_UUID);
		e.setSubjectUuid(SUBJECT_UUID);
		e.setTerm(TERM_UPDATE);
		e.setYear(YEAR_UPDATE);
		e.setOutOf(OUT_OF_UPDATE);
		assertTrue(store.update(e));  
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#delete(com.yahoo.petermwenda83.bean.exam.ExamDetail)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamDetail e = new ExamDetail();
		e.setSchoolAccountUuid(SCHOOL_UUID_NEW);
		e.setExamUuid(EXAM_UUID);
		e.setClassRoomUuid(CLASSROOM_UUID);
		e.setSubjectUuid(SUBJECT_UUID);
		assertTrue(store.delete(e)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.detail.ExamDetailDAO#getAllExamdetails(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetAllExamdetails() {
		store =new  ExamDetailDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ExamDetail> list = store.getAllExamdetails(SCHOOL_UUID, TERM, YEAR);	
		for (ExamDetail p : list) {
			System.out.println(p);
		}
		
		
	}

}
