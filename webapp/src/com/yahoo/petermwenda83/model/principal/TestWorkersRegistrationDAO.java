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
package com.yahoo.petermwenda83.model.principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.Workers;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestWorkersRegistrationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	   private String UUID = "616361c1-e7c3-4336-ba25-5905ac0712a3",
		              UUID_NEW = "64FE8C78-95BD-43ED-82E7-90E4640247AB";
		private String FIRSTNAME = "Njoka",
				       FIRSTNAME_NEW = "newNjoka",
				       FIRSTNAME_UPDATE = "updateNjoka";
		private String LASTNAME = "Mugera",
				       LASTNAME_NEW = "newMugera",
				       LASTNAME_UPDATE = "updateMugera";
		private String SURNAME = "Maina",
				       SURNAME_NEW = "newMaina",
				       SURNAME_UPDATE = "updateMaina";
		private String NHIF = "1234",
				       NHIF_NEW = "new1234",
				       NHIF_UPDATE = "update1234";
		private String NSSF = "8765",
				       NSSF_NEW = "new8765",
				       NSSF_UPDATE = "update8765";
		private String DOB = "1986",
				       DOB_NEW = "new1986",
				       DOB_UPDATE = "update1986";
		private String PHONE = "712346799",
			           PHONE_NEW = "new712346799",
			           PHONE_UPDATE = "update712346799";
		private String NATIONALID = "76347823",
				       NATIONALID_NEW = "new76347823",
				       NATIONALID_UPDATE = "update76347823";
		private String COUNTY = "meru",
				       COUNTY_NEW = "newmeru",
				       COUNTY_UPDATE = "updatemeru";
		private String LOCATION = "loc1",
				       LOCATION_NEW = "newloc1",
				       LOCATION_UPDATE = "updateloc1";
		
		private String SUBLOCATION = "loc1",
		               SUBLOCATION_NEW = "newloc1",
		               SUBLOCATION_UPDATE = "updateloc1";

	
	
	    private WorkersRegistrationDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.WorkersRegistrationDAO#getWorker(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetWorker() {
		store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Workers w = new Workers();
		w = store.getWorker(UUID);
		assertEquals(w.getUuid(),UUID);
		assertEquals(w.getFirstName(),FIRSTNAME);
		assertEquals(w.getLastName(),LASTNAME);
		assertEquals(w.getSurname(),SURNAME);
		assertEquals(w.getNhifno(),NHIF);
		assertEquals(w.getNssfno(),NSSF);
		assertEquals(w.getDOB(),DOB);
		assertEquals(w.getPhone(),PHONE);
		assertEquals(w.getNationalID(),NATIONALID);
		assertEquals(w.getCounty(),COUNTY);
		assertEquals(w.getLocation(),LOCATION);
		assertEquals(w.getSublocation(),SUBLOCATION);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.WorkersRegistrationDAO#putWorker(com.yahoo.petermwenda83.contoller.staff.nonteaching.Workers)}.
	 */
	@Ignore
	@Test
	public void testPutWorker() {
		store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Workers w = new Workers();
		w.setUuid(UUID_NEW);
		w.setFirstName(FIRSTNAME_NEW);
		w.setLastName(LASTNAME_NEW);
		w.setSurname(SURNAME_NEW);
		w.setNhifno(NHIF_NEW);
		w.setNssfno(NSSF_NEW);
		w.setDOB(DOB_NEW);
		w.setPhone(PHONE_NEW);
		w.setNationalID(NATIONALID_NEW);
		w.setCounty(COUNTY_NEW);
		w.setLocation(LOCATION_NEW);
		w.setSublocation(SUBLOCATION_NEW);
		assertTrue(store.putWorker(w));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.WorkersRegistrationDAO#editWorker(com.yahoo.petermwenda83.contoller.staff.nonteaching.Workers, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditWorker() {store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Workers w = new Workers();
		w.setUuid(UUID_NEW);
		w.setFirstName(FIRSTNAME_UPDATE);
		w.setLastName(LASTNAME_UPDATE);
		w.setSurname(SURNAME_UPDATE);
		w.setNhifno(NHIF_UPDATE);
		w.setNssfno(NSSF_UPDATE);
		w.setDOB(DOB_UPDATE);
		w.setPhone(PHONE_UPDATE);
		w.setNationalID(NATIONALID_UPDATE);
		w.setCounty(COUNTY_UPDATE);
		w.setLocation(LOCATION_UPDATE);
		w.setSublocation(SUBLOCATION_UPDATE);
		assertTrue(store.editWorker(w, UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.WorkersRegistrationDAO#deleteWorker(com.yahoo.petermwenda83.contoller.staff.nonteaching.Workers, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteWorker() {
		store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Workers w = new Workers();
		w.setUuid(UUID_NEW);
		assertTrue(store.deleteWorker(w, UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.WorkersRegistrationDAO#getAllWorker()}.
	 */
	@Ignore
	@Test
	public void testGetAllWorker() {
		store = new WorkersRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		  List<Workers> list = store.getAllWorker();	 
			for (Workers ss : list) {
				System.out.println(ss);
			}
	}

}
