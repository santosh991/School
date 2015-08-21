/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.guardian.StudentSponsor;

/**
 * @author peter
 *
 */
public class TestSponsorsDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private SponsorsDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#getStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testGetStudentSponser() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#putStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testPutStudentSponser() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#editStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testEditStudentSponser() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#deleteStudentSponser(com.yahoo.petermwenda83.contoller.guardian.StudentSponsor)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentSponser() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.SponsorsDAO#getAllStudentSponser()}.
	 */
	//@Ignore
	@Test
	public void testGetAllStudentSponser() {
		store = new SponsorsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentSponsor> list = store.getAllStudentSponser();	 
		for (StudentSponsor l : list) {
			System.out.println(l);
	}
	}
}
