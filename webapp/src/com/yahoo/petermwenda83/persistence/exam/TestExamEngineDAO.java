/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.CatOne;
import com.yahoo.petermwenda83.bean.exam.CatTwo;
import com.yahoo.petermwenda83.bean.exam.EndTerm;

/**
 * @author peter
 *
 */
public class TestExamEngineDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD",
			     SCHOOL_UUID_NEW = "3A387B8B-A0D8-4F27-B9D2-E329619DF055";
	
	
	final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	
	final String STUDENT_UUID = "4F218688-6DE5-4E69-8690-66FBA2F0DC9F",
			     STUDENT_UUID_NEW="6912F4C6-9370-440A-BA87-11A8426BA92C";
	
	final String SUBJECT_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446",
			     SUBJECT_UUID_NEW="c9caf109-c27d-4062-9b9f-ac4268629e27";
	
	final String TAECHER_UUID = "F49DB775-4952-4915-B978-9D9F3E36D6E9",
		         TEACHER_UUID_NEW="F49DB775-4952-4915-B978-9D9F3E36D6E9";
	
	final String TERM = "1",
	             TERM_NEW="2";
	
	final String YEAR = "2015",
	             YEAR_NEW="2016";
	
	final Date SUBMIT_DATE = new Date(new Long("141941056800") );

	
	final Double SCORE = 26.5;
	
	private ExamEgineDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.ExamEgineDAO#getCatOne(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatOne() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.ExamEgineDAO#hasCatOne(com.yahoo.petermwenda83.bean.exam.Perfomance, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testChecker() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		/*Common p = new Common();
		p.setSchoolAccountUuid(SCHOOL_UUID);
		p.setClassRoomUuid(FORM_ONE_N);
		p.setStudentUuid(STUDENT_UUID);
		p.setSubjectUuid(SUBJECT_UUID); */
		assertTrue(store.Checker(SCHOOL_UUID,FORM_ONE_N,STUDENT_UUID,SUBJECT_UUID)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.ExamEgineDAO#putCatOne(com.yahoo.petermwenda83.bean.exam.Perfomance, java.lang.Double)}.
	 */
	//@Ignore
	@Test
	public void testPutCatOne() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		
		CatOne p = new CatOne();
		p.setSchoolAccountUuid(SCHOOL_UUID);
		p.setClassRoomUuid(FORM_ONE_N);
		p.setStudentUuid(STUDENT_UUID_NEW);
		p.setSubjectUuid(SUBJECT_UUID); 
		
		p.setTeacherUuid(TEACHER_UUID_NEW);
		p.setTerm(TERM_NEW);
		p.setYear(YEAR_NEW);
		p.setCatOne(SCORE);
		//assertTrue(store.putScore(p));
		
		CatTwo p2 = new CatTwo();
		p2.setSchoolAccountUuid(SCHOOL_UUID);
		p2.setClassRoomUuid(FORM_ONE_N);
		p2.setStudentUuid(STUDENT_UUID_NEW);
		p2.setSubjectUuid(SUBJECT_UUID); 
		
		p2.setTeacherUuid(TEACHER_UUID_NEW);
		p2.setTerm(TERM_NEW);
		p2.setYear(YEAR_NEW);
		p2.setCatTwo(SCORE); 
		//assertTrue(store.putScore(p2));
		
		EndTerm p3 = new EndTerm();
		
		p3.setSchoolAccountUuid(SCHOOL_UUID);
		p3.setClassRoomUuid(FORM_ONE_N);
		p3.setStudentUuid(STUDENT_UUID_NEW);
		p3.setSubjectUuid(SUBJECT_UUID); 
		
		p3.setTeacherUuid(TEACHER_UUID_NEW);
		p3.setTerm(TERM_NEW);
		p3.setYear(YEAR_NEW);
		p3.setEndTerm(SCORE);
		assertTrue(store.putScore(p3)); 
	}  
	
	
	@Ignore
	@Test
	public void testGetcatoneList() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatOne> list = store.getcatoneList(SCHOOL_UUID,FORM_ONE_N);
		for(CatOne p : list){
			System.out.println(p);
		}
	
	}
	
	@Ignore
	@Test
	public void testGetcatwoList() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatTwo> list = store.getcatwoList(SCHOOL_UUID,FORM_ONE_N);
		for(CatTwo p : list){
			System.out.println(p);
		}
	
	}
	
	@Ignore
	@Test
	public void testGetendtermList() {
		store = new ExamEgineDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<EndTerm> list = store.getendtermList(SCHOOL_UUID,FORM_ONE_N);
		for(EndTerm p : list){
			System.out.println(p);
		}
	
	}

}
