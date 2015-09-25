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
package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Location;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestLocationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="C3CFA249-F2A3-8253-1F44-B1C594C6A8D2";
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			       STU_UUID_NEW = "d8a28954-5f2e-4899-8e3f-37b48694778b";
	
	private String STU_COUNTY = "Muranga",
			       STU_COUNTY_NEW = "newaCounty",
			       STU_COUNTY_UPDATE = "UpdateCounty";
	
	private String STU_LOCATION = "Muranga1",
			       STU_LOCATION_NEW = "newLocation",
			       STU_LOCATION_UPDATE = "UpdatLocation";
	
	private String STU_SUBLOCAT = "Muranga2",
			       STU_SUBLOCAT_NEW = "newSublocation",
			       STU_SUBLOCAT_UPDATE = "UpdatSublocation";
	
	private LocationDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.LocationDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new LocationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Location l = new Location();
		 l = store.getStudent(STU_UUID);
		 assertEquals(l.getUuid(),UUID);
		 assertEquals(l.getStudentUuid(),STU_UUID);
		 assertEquals(l.getLocation(),STU_LOCATION);
		 assertEquals(l.getSublocation(),STU_SUBLOCAT);
		 assertEquals(l.getCounty(),STU_COUNTY);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.LocationDAO#putStudent(com.yahoo.petermwenda83.bean.student.Location)}.
	 */
	@Ignore
	@Test
	public void testPutStudent() {
		 store = new LocationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Location location = new Location();
		 location.setStudentUuid(STU_UUID_NEW);
		 location.setCounty(STU_COUNTY_NEW);
		 location.setLocation(STU_LOCATION_NEW);
		 location.setSublocation(STU_SUBLOCAT_NEW);	 
		 assertTrue(store.putStudent(location)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.LocationDAO#editStudent(com.yahoo.petermwenda83.bean.student.Location, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditStudent() {
		 store = new LocationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Location location = new Location();
		 location.setCounty(STU_COUNTY_UPDATE);
		 location.setLocation(STU_LOCATION_UPDATE);
		 location.setSublocation(STU_SUBLOCAT_UPDATE);	 
		 assertTrue(store.editStudent(location, STU_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.LocationDAO#deleteStudent(com.yahoo.petermwenda83.bean.student.Location)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudent() {
		 store = new LocationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Location location = new Location();
		 location.setStudentUuid(STU_UUID_NEW);
		 assertTrue(store.deleteStudent(location));
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.LocationDAO#getAllLocation()}.
	 */
	@Ignore
	@Test
	public void testGetAllLocation() {
		 store = new LocationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Location> list = store.getAllLocation();	 
			for (Location l : list) {
				System.out.println(l);
				
			}
	}

}
