/**##########################################################
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

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.student.House;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestHouseDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			       STU_UUID_NEW = "75d63eed-2e4e-43ed-915f-552443d628cd";
	private String STU_HOUSE = "Kirima Mbogo",
			       STU_HOUSE_NEW = "newhouse",
			       STU_HOUSE_UPDATE = "Updatedhouse";
	
	private String UUID = "650195B6-9357-C147-C24E-7FBDAEEC74EK";
	
	
	private HouseDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.HouseDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 House house = new House();
		 house = store.getStudent(STU_UUID);
		 assertEquals(house.getUuid(),UUID);
		 assertEquals(house.getStudentUuid(),STU_UUID);
		 assertEquals(house.getHousename(),STU_HOUSE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.HouseDAO#putStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	@Ignore
	@Test
	public void testPutStudent() {
		 store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 House house = new House();
		 house.setStudentUuid(STU_UUID_NEW);
		 house.setHousename(STU_HOUSE_NEW);
		 assertTrue(store.putStudent(house));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.HouseDAO#editStudent(com.yahoo.petermwenda83.contoller.student.House, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditStudent() {
		 store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 House house = new House();
		 house.setStudentUuid(STU_UUID_NEW);
		 house.setHousename(STU_HOUSE_UPDATE);
		 assertTrue(store.editStudent(house, STU_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.HouseDAO#deleteStudent(com.yahoo.petermwenda83.contoller.student.House)}.
	 */
	//@Ignore
	@Test
	public void testDeleteStudent() {
		 store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 House house = new House();
		 house.setStudentUuid(STU_UUID_NEW);
		 assertTrue(store.deleteStudent(house));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.registration.HouseDAO#getAllHouse()}.
	 */
	@Ignore
	@Test
	public void testGetAllHouse() {
		 store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<House> list = store.getAllHouse();	 
			for (House l : list) {
				System.out.println(l);
				
			}
	}

}
