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
package com.yahoo.petermwenda83.model.regparents;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.guardian.StudentSponsor;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestSponsorsDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String UUID = "C3CFA249-F2A3-8253-1F44-B1C594C6AG8";
	
	final String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			STU_UUID_NEW ="75d63eed-2e4e-43ed-915f-552443d628cd",
			STU_UUID_UPDATE ="";
	final String SPO_NAME = "Obed Muthomi",
			SPO_NAME_NEW ="Mukuru Mbwani",
			SPO_NAME_UPDATE="Update";
	final String SPO_PHONE = "720286230",
			SPO_PHONE_NEW ="777777777n",
			SPO_PHONE_UPDATE="8888888u";
	final String SPO_ID = "284167655",
			SPO_ID_NEW="5555555n",
			SPO_ID_UPDATE="4444444u";
	final String SPO_OCCUP = "Coder",
			SPO_OCCUP_NEW="Nurse",
			SPO_OCCUP_UPDATE="Engineer";
	final String SPO_COUNTY = "Meru",
			SPO_COUNTY_NEW="Embu",
			SPO_COUNTY_UPDATE="Muranga";
	final String SPO_COUNTRY = "Kenya",
			SPO_COUNTRY_NEW="Uganda",
			SPO_COUNTRY_UPDATE="Tanzania";
	
	private SponsorsDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#getStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testGetStudentSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSponsor s = new StudentSponsor();
		s= store.getStudentSponsor(STU_UUID);
		assertEquals(s.getUuid(),UUID);
		assertEquals(s.getStudentsUuid(),STU_UUID);
		assertEquals(s.getName(),SPO_NAME);
		assertEquals(s.getPhone(),SPO_PHONE);
		assertEquals(s.getNationalID(),SPO_ID);
		assertEquals(s.getOccupation(),SPO_OCCUP);
		assertEquals(s.getCountry(),SPO_COUNTRY);
		assertEquals(s.getCounty(),SPO_COUNTY);
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#getStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testGetStudentSponsorById() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSponsor s = new StudentSponsor();
		s= store.getStudentSponsorById(SPO_ID); 
		assertEquals(s.getUuid(),UUID);
		assertEquals(s.getStudentsUuid(),STU_UUID);
		assertEquals(s.getName(),SPO_NAME);
		assertEquals(s.getPhone(),SPO_PHONE);
		assertEquals(s.getNationalID(),SPO_ID);
		assertEquals(s.getOccupation(),SPO_OCCUP);
		assertEquals(s.getCountry(),SPO_COUNTRY);
		assertEquals(s.getCounty(),SPO_COUNTY);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#putStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testPutStudentSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSponsor s = new StudentSponsor();
		s.setName(SPO_NAME_NEW);
		s.setStudentsUuid(STU_UUID_NEW);
		s.setPhone(SPO_PHONE_NEW);
		s.setNationalID(SPO_ID_NEW);
		s.setOccupation(SPO_OCCUP_NEW);
		s.setCountry(SPO_COUNTRY_NEW);
		s.setCounty(SPO_COUNTY_NEW);
		assertTrue(store.putStudentSponsor(s));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#editStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	//@Ignore
	@Test
	public void testEditStudentSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSponsor ss = new StudentSponsor();
		ss.setName(SPO_NAME_UPDATE);
		ss.setPhone(SPO_PHONE_UPDATE);
		ss.setNationalID(SPO_ID_UPDATE);
		ss.setOccupation(SPO_OCCUP_UPDATE);
		ss.setCounty(SPO_COUNTY_UPDATE);
		ss.setCountry(SPO_COUNTRY_UPDATE);
		assertTrue(store.editStudentSponsor(ss, STU_UUID_NEW)) ;
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#deleteStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentSponsor ss = new StudentSponsor();
		ss.setStudentsUuid(STU_UUID_UPDATE); 
		assertTrue(store.deleteStudentSponsor(ss));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#getAllStudentSponser()}.
	 */
	@Ignore
	@Test
	public void testGetAllStudentSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentSponsor> list = store.getAllStudentSponsor();	 
		for (StudentSponsor l : list) {
			System.out.println(l);
	}
	}
}
