/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.StudentExam;

/**
 * @author peter
 *
 */
public class TestStudentExamDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	final String STUDENT_UUID = "4F218688-6DE5-4E69-8690-66FBA2F0DC9F",
			     STUDENT_UUID_NEW ="7BF6983F-CF8D-4897-8122-7CCD0F778CAF";
	
	
    private StudentExamDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.StudentExamDAO#putStudentExam(java.lang.String)}.
	 */
    @Ignore
	@Test
	public void testgetStudentExam() {
		store = new StudentExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentExam se = new StudentExam();
		se = store.getStudentExam(SCHOOL_UUID,STUDENT_UUID);
		assertEquals(se.getClassRoomUuid(),FORM_ONE_N);
	}
    
    /**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.StudentExamDAO#putStudentExam(java.lang.String)}.
	 */
    @Ignore
	@Test
	public void testPutStudentExam() {
		store = new StudentExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentExam se = new StudentExam();
		se.setSchoolAccountUuid(SCHOOL_UUID);
		se.setClassRoomUuid(FORM_ONE_N);
		se.setStudentUuid(STUDENT_UUID);
		assertTrue(store.putStudentExam(se)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.StudentExamDAO#getStudentExamList(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentExamList() {
		store = new StudentExamDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentExam> list = store.getStudentExamList(SCHOOL_UUID,FORM_ONE_N);
		for(StudentExam p : list){
			System.out.println(p);
		}
		
		
		
		
	}

}
