/**
 * 
 */
package com.yahoo.petermwenda83.dao.curriculum;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.StudentSubject;

/**
 * @author peter
 *
 */
public class TestStudSubjectDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	final String  UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E";

	final String  SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			       SUB_UUID_2 = "H0F7EC32-EA25-9D32-8708-2CC132446A2F",
			       SUB_UUID_3 ="4dcd3ab3-fffa-4083-89d5-c2734e0706a8";
	               
	final String SUB_CODE = "ENG",
			     SUB_CODE_2 = "MATH",
			     SUB_CODE_3 = "KISW",
			     SUB_CODE_4 = "CHEMx";
	final String SUB_NAME = "English",
			     SUB_NAME_2 = "Mathematics",
			     SUB_NAME_3 ="Kiswahili", 
			     SUB_NAME_4 ="Chemistry";
	final String SUB_CAT = "Compulsary",
	             SUB_CAT_2 ="Humanity",
	             SUB_CAT_3 ="Techinical",
	             SUB_CAT_4 = "Science";
			
	final String ROOM_UUID ="4DA86139-6A72-4089-8858-6A3A613FDFE6",
			     ROOM_UUID_NEW ="58444390-EFCA-4C4B-9010-577C31499A63",
			     ROOM_UUID_UPDATE ="46398A47-93F2-4591-B36F-1C28B03CC2F3";

	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y", 
	             STU_UUID_2 ="DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
	             STU_UUID_3 ="66027e51-b1ad-4b10-8250-63af64d23323";
	final String STUSUB_UUID = "650195B6-9357-C147-C24E-7FBDAEEC74ED", 
	             STUSUB_UUID_2 ="e35a03bb-8bdb-4b9a-b9bd-ce24b16b0e27";

	final String studentUuid = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y";
	final String uuid = "4a053537-b2cb-429a-a95b-fb529f692a2e";


	  //final String SUB3UUID = "5fd784ea-3316-4d12-8e77-7862f0c5e084";

	private StudSubjectDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#getStudentSubject(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentSubject() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject stusub = new StudentSubject();
    	stusub = store.getStudentSubject(STUSUB_UUID);
    	assertEquals(stusub.getUuid(),STUSUB_UUID);
		assertEquals(stusub.getStudentUuid(),STU_UUID);
		assertEquals(stusub.getSubjectUuid(),SUB_UUID);
		assertEquals(stusub.getRoomnameUuid(),ROOM_UUID);
	}
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#getStudentSubject(java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGetStudentSubjects() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject stusub = new StudentSubject();
		 stusub.setRoomnameUuid(ROOM_UUID);
		 List<StudentSubject> list = store.getStudentSubject(stusub, ROOM_UUID);	   
			//assertEquals(list.size(), 2);
			System.out.println(list);
			for (StudentSubject l : list) {
				System.out.println(l);
				
			}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#putStudentSubject(com.yahoo.petermwenda83.bean.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudentSubject() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject stusub = new StudentSubject();
    	stusub.setUuid(UUID);
    	stusub.setStudentUuid(STU_UUID);
    	stusub.setSubjectUuid(STU_UUID_3);
    	stusub.setRoomnameUuid(ROOM_UUID_NEW);;
    	
    	assertTrue(store.putStudentSubject(stusub));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#editSubject(com.yahoo.petermwenda83.bean.student.StudentSubject, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditSubject() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject stusub = new StudentSubject();
    	stusub.setStudentUuid(STU_UUID);
    	stusub.setSubjectUuid(STU_UUID_3);
    	stusub.setRoomnameUuid(ROOM_UUID_NEW);;
    	
    	assertTrue(store.editSubject(stusub, UUID)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#deleteSubject(com.yahoo.petermwenda83.bean.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testDeleteSubject() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject stusubject = new StudentSubject();
		stusubject.setStudentUuid(STU_UUID); 
		assertTrue(store.deleteSubject(stusubject)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.curriculum.StudSubjectDAO#getAllStudentSubject()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudentSubject() {
		store = new StudSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<StudentSubject> list = store.getAllStudentSubject();	
			assertEquals(list.size(), 2);
			System.out.println(list);
			for (StudentSubject l : list) {
				System.out.println(l);
				
			}
	}

}
