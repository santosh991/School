/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.main;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.main.MainMarks;
import com.yahoo.petermwenda83.bean.exam.results.FinalMark;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;

/**
 * @author peter
 *
 */
public class TestMainSubMarkDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	//FOR MAIN SUBJECT MARKS
   	final String MAIN_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
   	
   	final String MAIN_STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			     MAIN_STU_UUID_NEW="B38EE08C-6164-449E-8619-FF84676D5D46";
			    
		         
   	
	final String MAIN_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			     MAIN_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
			     
   	
    
    final double MAIN_MARK =78.0,
		         MAIN_MARK_NEW = 78.1,
		         MAIN_MARK_UPDATE = 78.2;
    
    final double MAIN_SUB_MARK =78.0,
	         MAIN_SUB_MARK_NEW = 78.1,
	         MAIN_SUB_MARK_UPDATE = 78.2;
    
    final double MAIN_PERCENT = 86.0,
	             MAIN_PERCENT_NEW = 86.1,
	             MAIN_PERCENT_UPDATE = 86.2;
    
    final double MAIN_POINTS = 11.0,
		         MAIN_POINTS_NEW = 10.0,
		         MAIN_POINTS_UPDATE = 8.0;
    
    final String MAIN_GRADE = "A-",
    		     MAIN_GRADE_NEW = "NEWA-",
    		     MAIN_GRADE_UPDATE = "UPDATEA-";
    
    final Date MAIN_DATE = new Date(new Long("1417633242000"));
    final Date MAIN_DATE_NEW = new Date(new Long("1417633242000"));
    final Date MAIN_DATE_UPDATE = new Date(new Long("1417633242000"));
    
    
  
   
	
	
	private MainSubMarkDAO  store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#getMainMark(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetMainMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks m = new MainMarks();
		m = store.getMainMark(MAIN_STU_UUID,MAIN_SUB_UUID);
		assertEquals(m.getUuid(),MAIN_UUID);
		assertEquals(m.getStudentUuid(),MAIN_STU_UUID);
		assertEquals(m.getSubjectUuid(),MAIN_SUB_UUID);
		assertEquals(m.getMarks(),MAIN_MARK,0);
		assertEquals(m.getPercent(),MAIN_PERCENT,0);
		assertEquals(m.getPoints(),MAIN_POINTS,0);
		assertEquals(m.getGrade(),MAIN_GRADE);
		//assertEquals(m.getSubmitdate(),MAIN_DATE);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#hasMainMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testHasMainMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		//MainMarks m = new MainMarks();
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#addMainMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	//@Ignore
	@Test
	public void testAddMainMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks m = new MainMarks();
		m.setStudentUuid(MAIN_STU_UUID);
		m.setSubjectUuid(MAIN_SUB_UUID);
		m.setMarks(MAIN_MARK);
		m.setSubmark(MAIN_SUB_MARK);
		m.setPercent(MAIN_PERCENT);
		m.setGrade(MAIN_GRADE); 
		m.setRemarks("remarks");
		m.setPoints(MAIN_POINTS);
		m.setSubmitdate(MAIN_DATE);
		assertTrue(store.addMainMark(m, MAIN_MARK, MAIN_POINTS));
		
		 FinalResult fr = new FinalResult();
		 fr.setStudentUuid(MAIN_STU_UUID);
		 fr.setSubjectUuid(MAIN_SUB_UUID);
		 fr.setGrade("A+");
		 fr.setRemarks("Spledid"); 
		 assertTrue(store.addMainMark(fr, MAIN_MARK, MAIN_POINTS));
		 
		 FinalMark fm = new FinalMark();
		 fm.setStudentUuid(MAIN_STU_UUID);
		 fm.setSubjectUuid(MAIN_SUB_UUID);
		 fm.setSubmark(45); 
		 fm.setGrade("A+"); 
		 assertTrue(store.addMainMark(fm, MAIN_MARK, MAIN_POINTS));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#editMainMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testEditMainMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks m = new MainMarks();
		m.setStudentUuid(MAIN_STU_UUID);
		m.setSubjectUuid(MAIN_SUB_UUID);
		m.setMarks(MAIN_MARK_NEW);
		m.setPercent(MAIN_PERCENT_NEW);
		m.setGrade(MAIN_GRADE_NEW); 
		m.setPoints(MAIN_POINTS_NEW);
		m.setSubmitdate(MAIN_DATE_NEW);
		assertTrue(store.editMainMark(m, MAIN_MARK_NEW, MAIN_POINTS_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#deleteMainMark(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeleteMainMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainMarks m = new MainMarks();
		m.setStudentUuid(MAIN_STU_UUID_NEW);
		m.setSubjectUuid(MAIN_SUB_UUID_NEW);
		assertTrue(store.deleteMainMark(m));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.main.MainSubMarkDAO#getAllMainSubMark()}.
	 */
	@Ignore
	@Test
	public void testGetAllMainSubMark() {
		store = new MainSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainMarks> list = store.getAllMainSubMark();	
		for (MainMarks l : list) {
			System.out.println(l);
		}
		
	}

}
