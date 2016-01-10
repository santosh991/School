package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentSubClassRoom;

public class TestStudentDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	
	private StudentDAO store;
    
	 @Ignore
	@Test
	public void testGetStudent() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
   
	 @Ignore
	@Test
	public void testGetStudents() {
		fail("Not yet implemented");
	}
    
	 @Ignore
	@Test
	public void testGetStudentByName() {
		fail("Not yet implemented");
	}
    
	 @Ignore
	@Test
	public void testGetStudentByAdmNo() {
		fail("Not yet implemented");
	}
	 @Ignore
	@Test
	public void testPutStudents() {
		fail("Not yet implemented");
	}
	 @Ignore
	@Test
	public void testUpdateStudents() {
		fail("Not yet implemented");
	}
	 @Ignore
	@Test
	public void testDeleteStudents() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	 //@Ignore
	@Test
	public void testGetAllStudents() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Student> list = store.getAllStudents(SCHOOL_UUID,FORM_ONE_N);
		for (Student l : list) {
			System.out.println(l);	
		}
		
	}
	 @Ignore
	@Test
	public void testGetStudentList() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

}
