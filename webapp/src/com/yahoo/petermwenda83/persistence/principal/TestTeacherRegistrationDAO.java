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
package com.yahoo.petermwenda83.persistence.principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.teaching.Teachers;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestTeacherRegistrationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID = "9A5D5377-06C5-4EA1-9A5F-ECE6BBDD7F09",
			       UUID_NEW = "CF8339EF-38B8-4C7A-A1FC-83FEBFB585E0";
	private String FIRSTNAME = "munene",
			       FIRSTNAME_NEW = "newmunene",
			       FIRSTNAME_UPDATE = "updatemunene";
	private String LASTNAME = "nyaga",
			       LASTNAME_NEW = "newnyaga",
			       LASTNAME_UPDATE = "updatenyaga";
	private String SURNAME = "nesh",
			       SURNAME_NEW = "newnesh",
			       SURNAME_UPDATE = "updatenesh";
	private String NHIF = "8888",
			       NHIF_NEW = "new8888",
			       NHIF_UPDATE = "update8888";
	private String NSSF = "333",
			       NSSF_NEW = "new333",
			       NSSF_UPDATE = "update333";
	private String PHONE = "76588754",
			       PHONE_NEW = "new76588754",
			       PHONE_UPDATE = "update76588754";
	private String DOB = "1986",
			       DOB_NEW = "new1986",
			       DOB_UPDATE = "update1986";
	private String NATIONALID = "87757678",
			       NATIONALID_NEW = "new87757678",
			       NATIONALID_UPDATE = "update87757678";
	private String TEACHERNO = "67856",
			       TEACHERNO_NEW = "new67856", 
			       TEACHERNO_UPDATE = "update67856";
	private String COUNTY = "meru",
			       COUNTY_NEW = "newmeru",
			       COUNTY_UPDATE = "updatemeru";
	private String LOCATION = "naari",
			       LOCATION_NEW = "newnaari",
			       LOCATION_UPDATE = "updatenaari";
	
	
	
	private TeacherRegistrationDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherRegistrationDAO#getTeacher(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetTeacher() {
		store = new TeacherRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teachers t = new Teachers();
		t = store.getTeacher(TEACHERNO);
		assertEquals(t.getUuid(),UUID);
		assertEquals(t.getFirstName(),FIRSTNAME);
		assertEquals(t.getLastName(),LASTNAME);
		assertEquals(t.getSurname(),SURNAME);
		assertEquals(t.getNhifno(),NHIF);
		assertEquals(t.getNssfno(),NSSF);
		assertEquals(t.getPhone(),PHONE);
		assertEquals(t.getDOB(),DOB);
		assertEquals(t.getNationalID(),NATIONALID);
		assertEquals(t.getTeacherNumber(),TEACHERNO);
		assertEquals(t.getCounty(),COUNTY);
		assertEquals(t.getLocation(),LOCATION);
		

	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherRegistrationDAO#putTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers)}.
	 */
	@Ignore
	@Test
	public void testPutTeacher() {
		store = new TeacherRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teachers t = new Teachers();
		t.setFirstName(FIRSTNAME_NEW);
		t.setLastName(LASTNAME_NEW);
		t.setSurname(SURNAME_NEW);
		t.setNhifno(NHIF_NEW);
		t.setNssfno(NSSF_NEW);
		t.setPhone(PHONE_NEW);
		t.setDOB(DOB_NEW);
		t.setNationalID(NATIONALID_NEW);
		t.setTeacherNumber(TEACHERNO_NEW);
		t.setCounty(COUNTY_NEW);
		t.setLocation(LOCATION_NEW); 
		//t.setUuid(UUID_NEW); 
		assertTrue(store.putTeacher(t));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherRegistrationDAO#editTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditTeacher() {
		store = new TeacherRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teachers t = new Teachers();
		t.setFirstName(FIRSTNAME_UPDATE);
		t.setLastName(LASTNAME_UPDATE);
		t.setSurname(SURNAME_UPDATE);
		t.setNhifno(NHIF_UPDATE);
		t.setNssfno(NSSF_UPDATE);
		t.setPhone(PHONE_UPDATE);
		t.setDOB(DOB_UPDATE);
		t.setNationalID(NATIONALID_UPDATE);
		t.setTeacherNumber(TEACHERNO_UPDATE);
		t.setCounty(COUNTY_UPDATE);
		t.setLocation(LOCATION_UPDATE); 
		t.setUuid(UUID_NEW);
		assertTrue(store.editTeacher(t, UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherRegistrationDAO#deleteTeacher(com.yahoo.petermwenda83.bean.staff.teaching.Teachers, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteTeacher() {
		store = new TeacherRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teachers t = new Teachers();
		t.setUuid(UUID_NEW);
		assertTrue(store.deleteTeacher(t, UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherRegistrationDAO#getAllTeachers()}.
	 */
	@Ignore
	@Test
	public void testGetAllTeachers() {
		store = new TeacherRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	     List<Teachers> list = store.getAllTeachers();	
		for (Teachers ss : list) {
			System.out.println(ss);
		}
	    
	}

}
