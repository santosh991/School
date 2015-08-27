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
package com.yahoo.petermwenda83.model.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.student.StudentSubject;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestStudentSubjectDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	final String SUB_UUID ="D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			     SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e",//AGR UUID
			     SUB_UUID_UPDATE ="";
	
	final String CLASS ="FORM 1 W",
			     CLASS_NEW ="NEWClass",
			     CLASS_UPDATE ="UPdateClass";
	
	final String STU_UUID ="DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			     STU_UUID_NEW ="838c6508-b971-4590-b7f1-4021867a197e";
	
	private StudentSubjectDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.StudentSubjectDAO#getStudentSubject(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudentSubject() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject s = new StudentSubject();
		s = store.getStudentSubject(STU_UUID);
		assertEquals(s.getStudentUuid(),STU_UUID);
		assertEquals(s.getSubjectUuid(),SUB_UUID);
		assertEquals(s.getClasz(),CLASS);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.StudentSubjectDAO#putStudentSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testPutStudentSubject() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject s = new StudentSubject();
		s.setStudentUuid(STU_UUID_NEW);
		s.setSubjectUuid(SUB_UUID_NEW);
		s.setClasz(CLASS_NEW);
		assertTrue(store.putStudentSubject(s));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.StudentSubjectDAO#editStudentSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditStudentSubject() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject s = new StudentSubject();
		s.setSubjectUuid(SUB_UUID_NEW);
		s.setStudentUuid(STU_UUID_NEW);
		s.setClasz(CLASS_UPDATE);
		assertTrue(store.editStudentSubject(s));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.StudentSubjectDAO#deleteStudentSubject(com.yahoo.petermwenda83.contoller.student.StudentSubject)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentSubject() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSubject s = new StudentSubject();
		s.setStudentUuid(STU_UUID_NEW);
		s.setSubjectUuid(SUB_UUID_NEW); 
		assertTrue(store.deleteStudentSubject(s));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.StudentSubjectDAO#getAllStudentSubject()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudentSubject() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentSubject> list = store.getAllStudentSubject();	
		for (StudentSubject ss : list) {
			System.out.println(ss);
		}
	}

}
