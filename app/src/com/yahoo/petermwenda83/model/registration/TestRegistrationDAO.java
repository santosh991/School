/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.student.House;
import com.yahoo.petermwenda83.contoller.student.Location;
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
	
	
	
	
	private RegistrationDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsString() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(com.yahoo.petermwenda83.contoller.student.Student)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsStudent() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testPutStudentActivity() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testPutStudentHouse() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testPutStudentLocation() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentSubject() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testEditStudentActivity() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testEditStudentHouse() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testEditStudentLocation() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testEditStudentStudentSubject() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentActivity() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentHouse() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentLocation() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentStudentSubject() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllStudents()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudents() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Student> list = store.getAllStudents();	
			//assertEquals(list.size(), 6); 
			//System.out.println(list);
			for (Student l : list) {
				System.out.println(l);
				
			}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllHouse()}.
	 */
	@Ignore
	@Test
	public void testGetAllHouse() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<House> list = store.getAllHouse();	 
			//assertEquals(list.size(), 6);
			//System.out.println(list);
			for (House l : list) {
				System.out.println(l);
				
			}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getAllLocation()}.
	 */
	//@Ignore
	@Test
	public void testGetAllLocation() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Location> list = store.getAllLocation();	 
			//assertEquals(list.size(), 6);
			//System.out.println(list);
			for (Location l : list) {
				System.out.println(l);
				
			}
	}

}
