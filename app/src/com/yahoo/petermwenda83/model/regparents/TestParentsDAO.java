/**##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without approval of from
 * ###### owner.#############################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.guardian.StudentParent;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestParentsDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String STUDENT_UUID ="9771e08e-4e87-4fe4-9608-a31952ec10cd";
	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			     STU_UUID_NEW = "d8a28954-5f2e-4899-8e3f-37b48694778b";
	final String UUID = "C6D5BA33-C95E-BCC3-D951-2A099E5F9230";
	final String FATHER_NAME = "Peter mwenda",FATHER_NAME_NEW ="Njeru Mwenda Peter";
	final String FATHER_PHONE = "718953974",FATHER_PHONE_NEW ="765456787654";
	final String FATHER_OCCUP = "Programmer",FATHER_OCCUP_NEW="newOccupation";
	final String FATHER_ID = "28314165",FATHER_ID_NEW="111111",FATHER_ID_UPDATE ="11112";
	final String FATHER_EMAIL = "peter@gmail.com",FATHER_EMAIL_NEW ="xxxxx@gmail.com",
			FATHER_EMAIL_UPDATE ="petermwenda72@gmail.com";
	
	final String MOTHER_NAME = "Mary Muthoni",MOTHER_NAME_NEW ="Merit Karimi";
	final String MOTHER_PHONE = "29314165",MOTHER_PHONE_NEW ="8765456787";
	final String MOTHER_OCCUP = "Teacher",MOTHER_OCCUP_NEW ="Business woman";
	final String MOTHER_ID = "65567788",MOTHER_ID_NEW= "7654567",MOTHER_ID_UPDATE="87678";
	final String MOTHER_EMAIL = "muthoni@gmail.com",MOTHER_EMAIL_NEW="karimimerit@yahoo.com",
			MOTHER_EMAIL_UPDATE ="meritkarimi@yahoo.com";
	
	final String RELATIONSHIP = "Blood Parent",RELATIONSHIP_NEW ="Adoption",RELATIONSHIP_UPDATE="Update";
	
	
	
	private ParentsDAO  store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testGetStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		StudentParent p = new StudentParent();
		p = store.getStudentParent(STU_UUID);
		
	
		assertEquals(p.getStudentsUuid(),STU_UUID); 
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getFathername(),FATHER_NAME);
		assertEquals(p.getFatheroccupation(),FATHER_OCCUP);
		assertEquals(p.getFatherphone(),FATHER_PHONE);
		assertEquals(p.getFatherEmail(),FATHER_EMAIL);
		assertEquals(p.getFatherID(),FATHER_ID);
		
		assertEquals(p.getMothername(),MOTHER_NAME);
		assertEquals(p.getMotheroccupation(),MOTHER_OCCUP);
		assertEquals(p.getMotherphone(),MOTHER_PHONE);
		assertEquals(p.getMotherEmail(),MOTHER_EMAIL);
		assertEquals(p.getMotherID(),MOTHER_ID);
		
		assertEquals(p.getRelationship(),RELATIONSHIP);
		
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testGetParentByFatherId() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentParent p = new StudentParent();
		p = store.getParentByFatherId(FATHER_ID);
		
	
		assertEquals(p.getStudentsUuid(),STU_UUID); 
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getFathername(),FATHER_NAME);
		assertEquals(p.getFatheroccupation(),FATHER_OCCUP);
		assertEquals(p.getFatherphone(),FATHER_PHONE);
		assertEquals(p.getFatherEmail(),FATHER_EMAIL);
		assertEquals(p.getFatherID(),FATHER_ID);
		
		assertEquals(p.getMothername(),MOTHER_NAME);
		assertEquals(p.getMotheroccupation(),MOTHER_OCCUP);
		assertEquals(p.getMotherphone(),MOTHER_PHONE);
		assertEquals(p.getMotherEmail(),MOTHER_EMAIL);
		assertEquals(p.getMotherID(),MOTHER_ID);
		
		assertEquals(p.getRelationship(),RELATIONSHIP);
	}
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testGetParentByMotherId() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentParent p = new StudentParent();
		p = store.getParentByMotherId(MOTHER_ID) ;
		
	
		assertEquals(p.getStudentsUuid(),STU_UUID); 
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getFathername(),FATHER_NAME);
		assertEquals(p.getFatheroccupation(),FATHER_OCCUP);
		assertEquals(p.getFatherphone(),FATHER_PHONE);
		assertEquals(p.getFatherEmail(),FATHER_EMAIL);
		assertEquals(p.getFatherID(),FATHER_ID);
		
		assertEquals(p.getMothername(),MOTHER_NAME);
		assertEquals(p.getMotheroccupation(),MOTHER_OCCUP);
		assertEquals(p.getMotherphone(),MOTHER_PHONE);
		assertEquals(p.getMotherEmail(),MOTHER_EMAIL);
		assertEquals(p.getMotherID(),MOTHER_ID);
		
		assertEquals(p.getRelationship(),RELATIONSHIP);
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#putStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testPutStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentParent p = new StudentParent();
		p.setStudentsUuid(STU_UUID_NEW);
		
		p.setFathername(FATHER_NAME_NEW);
		p.setFatheroccupation(FATHER_OCCUP_NEW);
		p.setFatherEmail(FATHER_EMAIL_NEW);
		p.setFatherID(FATHER_ID_NEW);
		p.setFatherphone(FATHER_PHONE_NEW);
		
		p.setMothername(MOTHER_NAME_NEW);
		p.setMotheroccupation(MOTHER_OCCUP_NEW);
		p.setMotherEmail(MOTHER_EMAIL_NEW);
		p.setMotherID(MOTHER_ID_NEW);
		p.setMotherphone(MOTHER_PHONE_NEW);
		
		p.setRelationship(RELATIONSHIP_NEW);
		
	    assertTrue(store.putStudentParent(p));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#ediStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testEdiStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentParent pp = new StudentParent();
		
		pp.setFathername(FATHER_NAME_NEW);
		pp.setFatheroccupation(FATHER_OCCUP_NEW);
		pp.setFatherEmail(FATHER_EMAIL_UPDATE);
		pp.setFatherID(FATHER_ID_UPDATE);
		pp.setFatherphone(FATHER_PHONE_NEW);
		
		pp.setMothername(MOTHER_NAME_NEW);
		pp.setMotheroccupation(MOTHER_OCCUP_NEW);
		pp.setMotherEmail(MOTHER_EMAIL_UPDATE);
		pp.setMotherID(MOTHER_ID_UPDATE);
		pp.setMotherphone(MOTHER_PHONE_NEW);
		
		pp.setRelationship(RELATIONSHIP_UPDATE);
		
	    assertTrue(store.ediStudentParent(pp,STU_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#deleteStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	//@Ignore
	@Test
	public void testDeleteStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentParent pp = new StudentParent();
		pp.setStudentsUuid(STUDENT_UUID);
		assertTrue(store.deleteStudentParent(pp));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getAllStudentParent()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<StudentParent> list = store.getAllStudentParent();	 
			for (StudentParent l : list) {
				System.out.println(l);
				
			}
	}

}
