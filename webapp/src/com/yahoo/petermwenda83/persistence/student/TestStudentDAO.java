package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;

public class TestStudentDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	
	final String CLASSROOM_UUID = "4DA86139-6A72-4089-8858-6A3A613FDFE6",
			     CLASSROOM_UUID_NEW = "4DA86139-6A72-4089-8858-6A3A613FDFE6",
			     CLASSROOM_UUID_UPDATE = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	
	final String UUID = "";
	
	final String STATUS_UUID = "",
			     STATUS_UUID_NEW = "";
	
	final String ADM_NO = "",
			     ADM_NO_NEW = "",
			     ADM_NO_UPDATE = "";
	
	final String FIRST_NAME = "",
			     FIRST_NAME_NEW = "",
			     FIRST_NAME_UPDATE = "";
	
	final String LAST_NAME = "",
			     LAST_NAME_NEW = "",
			     LAST_NAME_UPDATE = "";
	
	final String SURNAME = "",
			     SURNAME_NEW = "",
			     SURNAME_UPDATE = "";
	
	final String GENDER = "",
			     GENDER_NEW = "",
			     GENDER_UPDATE = "";
	
	final String DOB = "",
			     DOB_NEW = "",
			     DOB_UPDATE = "";
	
	final String B_CERT_NO = "",
			     B_CERT_NO_NEW = "",
			     B_CERT_NO_UPDATE = "";
	
	final String COUNTY = "",
			     COUNTY_NEW = "",
			     COUNTY_UPDATE = "";
	
	final String SYS_USER = "",
			     SYS_USER_NEW = "",
			     SYS_USER_UPDATE = "";
	
	final Date ADM_DATE =  new Date();
	
	private StudentDAO store;
    
	@Ignore
	@Test
	public void testGetStudent() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Student s = new Student();
		s = store.getStudent(SCHOOL_UUID,UUID);
		assertEquals(s.getUuid(),UUID);
	}
   
	@Ignore
	@Test
	public void testGetStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student s = new Student();
		 s = store.getStudents(SCHOOL_UUID,ADM_NO);
		 assertEquals(s.getUuid(),UUID);
	}
    
	@Ignore
	@Test
	public void testGetStudentByName() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 SchoolAccount schoolaccount = new SchoolAccount();
		 schoolaccount.setUuid(SCHOOL_UUID); 
		 List<Student> list = store.getStudentByName(schoolaccount, FIRST_NAME);
			for (Student l : list) {
				System.out.println(l);	
			}
	}
    
	@Ignore
	@Test
	public void testGetStudentByAdmNo() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 SchoolAccount schoolaccount = new SchoolAccount();
		 schoolaccount.setUuid(SCHOOL_UUID); 
		 List<Student> list = store.getStudentByAdmNo(SCHOOL_UUID, ADM_NO); 
			for (Student l : list) {
				System.out.println(l);	
			}
	}
	@Ignore
	@Test
	public void testPutStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student s = new Student();
		 s.setSchoolAccountUuid(SCHOOL_UUID);
		 s.setStatusUuid(STATUS_UUID);
		 
		 
		 assertTrue(store.putStudents(s));   

	}
	@Ignore
	@Test
	public void testUpdateStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student s = new Student();
		 s.setSchoolAccountUuid(SCHOOL_UUID);
		 s.setStatusUuid(STATUS_UUID);
		 
		 
		 assertTrue(store.updateStudents(s));  

	}
	 @Ignore
	@Test
	public void testDeleteStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student s = new Student();
		 s.setSchoolAccountUuid(SCHOOL_UUID);
		 s.setStatusUuid(STATUS_UUID);
		 assertTrue(store.deleteStudents(s)); 

	}
	@Ignore
	@Test
	public void testGetAllStudents() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Student> list = store.getAllStudents(SCHOOL_UUID,CLASSROOM_UUID);
		for (Student l : list) {
			System.out.println(l);	
		}
		
	}
	@Ignore
	@Test
	public void testGetStudentList() {
		store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 SchoolAccount schoolaccount = new SchoolAccount();
		 schoolaccount.setUuid(SCHOOL_UUID);
		List<Student> list = store.getStudentList(schoolaccount, 0, 15);
		for (Student l : list) {
			System.out.println(l);	
		}
	}
	 
	   
	    @Ignore
		@Test
		public void testGetAllStudentList() {
			store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
			List<Student> list = store.getAllStudentList(SCHOOL_UUID);
			for (Student l : list) {
				System.out.println(l);	
			}
		}

}
