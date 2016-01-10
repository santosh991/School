/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.Staff;

/**
 * @author peter
 *
 */
public class TestStaffDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD",
		         SCHOOL_UUID_NEW ="3A387B8B-A0D8-4F27-B9D2-E329619DF055";
	
	final String UUID = "708D2D79-BD0A-4338-BC75-62A5659A4F56";
	
	final String CATEGORY = "Teaching";
	
	final String POSITION_UUID = "C3915245-00EE-4EF4-9898-ACE59683DD60";
	
	final String  USERNAME = "demo";
	
	final String PASSWORD = "demo";
	
	private StaffDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#getStaff(java.lang.String, java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGetStaff() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Staff s = new Staff();
		s = store.getStaffByUsername(SCHOOL_UUID, USERNAME);
		assertEquals(s.getCategory(),CATEGORY);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#getStaffByUsername(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStaffByUsername() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#putStaff(com.yahoo.petermwenda83.bean.staff.Staff)}.
	 */
	@Ignore
	@Test
	public void testPutStaff() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#updateStaff(com.yahoo.petermwenda83.bean.staff.Staff)}.
	 */
	@Ignore
	@Test
	public void testUpdateStaff() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#deleteStaff(com.yahoo.petermwenda83.bean.staff.Staff)}.
	 */
	@Ignore
	@Test
	public void testDeleteStaff() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.StaffDAO#getStaffList(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStaffList() {
		store = new StaffDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Staff> list = store.getStaffList(SCHOOL_UUID);
		for (Staff ss : list) {
			System.out.println(ss);
		}
	}

}
