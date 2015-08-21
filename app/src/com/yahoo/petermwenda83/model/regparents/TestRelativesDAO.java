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

import com.yahoo.petermwenda83.contoller.guardian.StudentRelative;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestRelativesDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			STU_UUID_NEW = "",
			STU_UUID_UPDATE = "";
	final String UUID = "C3CFA249-F2A3-8253-1F44-B1C594C6A8D2",
			UUID_NEW ="",
			UUID_UPDATE ="";
	final String REL_NAME = "Dyanna Mwende",
			REL_NAME_NEW="",
			REL_NAME_UPDATE="";
	final String REL_PHONE = "7177667779",
			REL_PHONE_NEW = "",
			REL_PHONE_UPDATE = "";
	final String REL_ID = "377666662",
			REL_ID_NEW = "",
			REL_ID_UPDATE = "";
	
	
	private RelativesDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#getStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testGetStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentRelative r = new StudentRelative();
		r = store.getStudentRelative(STU_UUID);
		assertEquals(r.getStudentsUuid(),STU_UUID);
		assertEquals(r.getUuid(),UUID); 
		assertEquals(r.getRelativeName(),REL_NAME); 
		assertEquals(r.getRelativePhone(),REL_PHONE); 
		assertEquals(r.getNationalID(),REL_ID); 
	}
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#getStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testGetStudentRelativeByNationalID() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentRelative r = new StudentRelative();
		r = store.getStudentRelativeByNationalID(REL_ID); 
		assertEquals(r.getStudentsUuid(),STU_UUID);
		assertEquals(r.getUuid(),UUID); 
		assertEquals(r.getRelativeName(),REL_NAME); 
		assertEquals(r.getRelativePhone(),REL_PHONE); 
		assertEquals(r.getNationalID(),REL_ID); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#putStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	//@Ignore
	@Test
	public void testPutStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentRelative r = new StudentRelative();
		r.setStudentsUuid(STU_UUID_NEW);
		r.setRelativeName(REL_NAME_NEW);
		r.setRelativePhone(REL_PHONE_NEW);
		r.setNationalID(REL_ID_NEW);
		assertTrue(store.putStudentRelative(r));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#editStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testEditStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentRelative r = new StudentRelative();
		r.setStudentsUuid(STU_UUID_UPDATE);
		r.setRelativeName(REL_NAME_UPDATE);
		r.setRelativePhone(REL_PHONE_UPDATE);
		r.setNationalID(REL_ID_UPDATE); 
		assertTrue(store.editStudentRelative(r, STU_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#deleteStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentRelative r = new StudentRelative();
		r.setStudentsUuid(STU_UUID_NEW);
		assertTrue(store.deleteStudentRelative(r));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#getAllStudentRelative()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentRelative> list = store.getAllStudentRelative();	 
		for (StudentRelative l : list) {
			System.out.println(l);
			
		}
	}

}
