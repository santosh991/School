/**
 * 
 */
package com.yahoo.petermwenda83.persistence.guardian;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author peter
 *
 */
public class TestParentsDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private ParentsDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.ParentsDAO#getParent(java.lang.String)}.
	 */
	@Test
	public void testGetParent() {
		store =new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.ParentsDAO#putParent(com.yahoo.petermwenda83.bean.student.guardian.StudentParent)}.
	 */
	@Test
	public void testPutParent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.ParentsDAO#updateParent(com.yahoo.petermwenda83.bean.student.guardian.StudentParent)}.
	 */
	@Test
	public void testUpdateParent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.ParentsDAO#deleteParent(com.yahoo.petermwenda83.bean.student.guardian.StudentParent)}.
	 */
	@Test
	public void testDeleteParent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.ParentsDAO#getParentList()}.
	 */
	@Test
	public void testGetParentList() {
		fail("Not yet implemented");
	}

}
