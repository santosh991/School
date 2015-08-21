/**
 * 
 */
package com.yahoo.petermwenda83.model.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.student.Activity;
import com.yahoo.petermwenda83.contoller.student.House;
import com.yahoo.petermwenda83.contoller.student.Location;
import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.contoller.student.StudentSubject;

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
	
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y";
	private String STU_FNAME = "Mati";
	private String STU_LASTNAME = "Morris";
	private String STU_SURNAME = "Ngugi";
	private String STU_ADMNO = "A1213";
	private String STU_YEAR ="2015";
	private String STU_DOB ="1992";
	private String STU_BCERTNO = "A66655";
	//private String STU_ADMDATE = "";
	
	private String STU_ACTIVITY = "newactivity";
	private String STU_HOUSE = "newahouse";
	
	private String STU_COUNTY = "newaCounty";
	private String STU_LOCATION = "newLocation";
	private String STU_SUBLOCAT = "newSublocation";
	
	private String STU_SUB_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
	private String STU_SUB_CODE = "CHEM";
	private String STU_CLASS = "FORM 1 A";
	
	private String STU_F_NAME = "Rael ";
	private String STU_L_NAME = "Kathomi";
	private String STU_S_NAME = "Njeru";
    final Date STU_ADMDATE = new Date(new Long("1419410347000") );
	
	
	private RegistrationDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
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
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsString() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
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
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#getStudents(com.yahoo.petermwenda83.contoller.student.Student)}.
	 */
	@Ignore
	@Test
	public void testGetStudentsStudent() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testPutStudentActivity() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Activity activity = new Activity();
		 activity.setStudentUuid(STU_UUID);
		 activity.setActivity(STU_ACTIVITY);
		 assertTrue(store.putStudent(activity));
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testPutStudentHouse() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 House house = new House();
		 house.setStudentUuid(STU_UUID);
		 house.setHousename(STU_HOUSE);
		 assertTrue(store.putStudent(house));
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.Location)}.
	 */
	@Ignore
	@Test
	public void testPutStudentLocation() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Location location = new Location();
		 location.setStudentUuid(STU_UUID);
		 location.setCounty(STU_COUNTY);
		 location.setLocation(STU_LOCATION);
		 location.setSublocation(STU_SUBLOCAT);
		 
		 assertTrue(store.putStudent(location)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudentStudentSubject() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 StudentSubject stusubject = new StudentSubject();
		 stusubject.setStudentUuid(STU_UUID);
		 stusubject.setSubjectUuid(STU_SUB_UUID);
		 stusubject.setSubjectcode(STU_SUB_CODE);
		 stusubject.setClasz(STU_CLASS); 
		 
		 assertTrue(store.putStudent(stusubject));  
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#putStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	//@Ignore
	@Test
	public void testPutStudents() {
		 store = new RegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Student stu = new Student();
		 stu.setFirstname(STU_F_NAME);
		 stu.setLastname(STU_L_NAME);
		 stu.setSurname(STU_S_NAME);
		 stu.setAdmno(STU_ADMNO);
		 stu.setYear(STU_YEAR);
		 stu.setDOB(STU_DOB);
		 stu.setBcertno(STU_BCERTNO);
		 stu.setAdmissiondate(STU_ADMDATE);
		 
		
		 assertTrue(store.putStudents(stu));   
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.Activity)}.
	 */
	@Ignore//
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
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.RegistrationDAO#editStudent(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testEditStudents() {
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
	@Ignore
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
