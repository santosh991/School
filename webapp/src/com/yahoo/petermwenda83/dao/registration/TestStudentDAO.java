/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.dao.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Student;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestStudentDAO {

	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			      STU_UUID_NEW = "541b324e-cd83-4d21-b687-aa63fffa5dc0";
	
	private String  STU_FNAME = "Mati",
			        STU_FNAME_NEW = "NewMati",
			        STU_FNAME_UPDATE = "UpdatedMati";
	
	private String STU_LASTNAME = "Morris",
			       STU_LASTNAME_NEW = "NewMorris",
			       STU_LASTNAME_UPDATE = "UpdatedMorris";
	
	private String STU_SURNAME = "Ngugi",
			       STU_SURNAME_NEW = "NewNgugi",
			       STU_SURNAME_UPDATE = "UpdatedNgugi";
	
	private String STU_ADMNO = "A1213",
			       STU_ADMNO_NEW = "NewA1213";
			     //  STU_ADMNO_UPDATE = "UpdatedA1213";
	
	private String STU_YEAR ="2015",
			       STU_YEAR_NEW ="New2015",
			       STU_YEAR_UPDATE ="UPdated2015";
	
	private String STU_DOB ="1992",
			       STU_DOB_NEW ="New1992",
			       STU_DOB_UPDATE ="Updated1992";
	
	private String STU_BCERTNO = "A66655",
			       STU_BCERTNO_NEW = "NEWCertNo",
			       STU_BCERTNO_UPDATE = "UpdateCertNO";
	
	
    final Date STU_ADMDATE = new Date(new Long("1419410347000") );
    private Date STU_ADMDATE_NEW = new Date(new Long("1419410677000") );
    private Date STU_ADMDATE_UPDATE = new Date(new Long("141941056700") );
	
	
	private StudentDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student student = new Student();
			
		 student = store.getStudent(STU_UUID);
		 assertEquals(student.getUuid(),STU_UUID);
		 assertEquals(student.getFirstname(),STU_FNAME );
		 assertEquals(student.getLastname(),STU_LASTNAME);
		 assertEquals(student.getSurname(),STU_SURNAME);
		 assertEquals(student.getAdmno(),STU_ADMNO);
		 assertEquals(student.getYear(),STU_YEAR);
		 assertEquals(student.getDOB(),STU_DOB);
		 assertEquals(student.getBcertno(),STU_BCERTNO);
		 //assertEquals(student.getAdmissiondate(),STU_ADMDATE);
			
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#getStudents(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsString() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student student = new Student();
			
		 student = store.getStudents(STU_ADMNO);
		 assertEquals(student.getUuid(),STU_UUID);
		 assertEquals(student.getFirstname(),STU_FNAME );
		 assertEquals(student.getLastname(),STU_LASTNAME);
		 assertEquals(student.getSurname(),STU_SURNAME);
		 assertEquals(student.getAdmno(),STU_ADMNO);
		 assertEquals(student.getYear(),STU_YEAR);
		 assertEquals(student.getDOB(),STU_DOB);
		 assertEquals(student.getBcertno(),STU_BCERTNO);
		 //assertEquals(student.getAdmissiondate(),STU_ADMDATE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#getStudents(com.yahoo.petermwenda83.bean.student.Student)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsStudent() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#putStudent(com.yahoo.petermwenda83.bean.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student stu = new Student();
		 stu.setFirstname(STU_FNAME_NEW);
		 stu.setLastname(STU_LASTNAME_NEW);
		 stu.setSurname(STU_SURNAME_NEW);
		 stu.setAdmno(STU_ADMNO_NEW);
		 stu.setYear(STU_YEAR_NEW);
		 stu.setDOB(STU_DOB_NEW);
		 stu.setBcertno(STU_BCERTNO_NEW);
		 stu.setAdmissiondate(STU_ADMDATE_NEW);
		 
		
		 assertTrue(store.putStudents(stu));   
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#editStudent(com.yahoo.petermwenda83.bean.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testEditStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student stu = new Student();
		 stu.setFirstname(STU_FNAME_UPDATE);
		 stu.setLastname(STU_LASTNAME_UPDATE);
		 stu.setSurname(STU_SURNAME_UPDATE);
		 stu.setYear(STU_YEAR_UPDATE);
		 stu.setDOB(STU_DOB_UPDATE);
		 stu.setBcertno(STU_BCERTNO_UPDATE);
		 stu.setAdmissiondate(STU_ADMDATE_UPDATE);
		 
		 stu.setAdmno(STU_ADMNO_NEW);
		 assertTrue(store.editStudents(stu));
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#editStudent(com.yahoo.petermwenda83.bean.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student student = new Student();
		 student.setUuid(STU_UUID_NEW);
		 assertTrue(store.deleteStudents(student));
	}

	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.StudentDAO#getAllStudents()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudents() {
		 store = new StudentDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Student> list = store.getAllStudents();	
			for (Student l : list) {
				System.out.println(l);
			}
	}

	
	

}
