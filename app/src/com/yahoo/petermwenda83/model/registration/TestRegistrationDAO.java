/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.student.Student;

/**
 * @author peter
 *
 */
public class TestRegistrationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	
	private  RegistrationDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
	store = new RegistrationDAO	(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(com.yahoo.petermwenda83.contoller.student.Student)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsStudent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentActivity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentHouse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentStudentSubject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testEditStudentStudentActivity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testEditStudentStudentHouse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testEditStudentStudentLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testEditStudentStudentStudentSubject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentStudentActivity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentStudentHouse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentStudentLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Student, com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentStudentStudentSubject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllStudents()}.
	 */
	//@Ignore
	@Test
	public void testGetAllStudents() {
		store = new RegistrationDAO	(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Student> list = store.getAllStudents();
		//assertEquals(list.size(), 6);
		System.out.println(list);
		for(Student s : list){
			System.out.println(s);
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllHouse()}.
	 */
	@Ignore
	@Test
	public void testGetAllHouse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllLocation()}.
	 */
	@Ignore
	@Test
	public void testGetAllLocation() {
		fail("Not yet implemented");
	}

}
