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
public class TestSponsorsDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private SponsorsDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.SponsorsDAO#getSponsor(java.lang.String)}.
	 */
	@Test
	public void testGetSponsor() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.SponsorsDAO#putSponsor(com.yahoo.petermwenda83.bean.student.guardian.StudentSponsor)}.
	 */
	@Test
	public void testPutSponsor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.SponsorsDAO#updateSponsor(com.yahoo.petermwenda83.bean.student.guardian.StudentSponsor)}.
	 */
	@Test
	public void testUpdateSponsor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.SponsorsDAO#deleteSponsor(com.yahoo.petermwenda83.bean.student.guardian.StudentSponsor)}.
	 */
	@Test
	public void testDeleteSponsor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.guardian.SponsorsDAO#getStudentSponsorList()}.
	 */
	@Test
	public void testGetStudentSponsorList() {
		fail("Not yet implemented");
	}

}
