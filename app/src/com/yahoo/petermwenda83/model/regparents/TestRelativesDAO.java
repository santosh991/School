/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.guardian.StudentRelative;

/**
 * @author peter
 *
 */
public class TestRelativesDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private RelativesDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#getStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testGetStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#putStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testPutStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#editStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testEditStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#deleteStudentRelative(com.yahoo.petermwenda83.contoller.guardian.StudentRelative)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.RelativesDAO#getAllStudentRelative()}.
	 */
	//@Ignore
	@Test
	public void testGetAllStudentRelative() {
		store = new RelativesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentRelative> list = store.getAllStudentRelative();	 
		for (StudentRelative l : list) {
			System.out.println(l);
			
		}
	}

}
