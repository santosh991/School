/**
 * 
 */
package com.yahoo.petermwenda83.model.regparents;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.guardian.StudentParent;

/**
 * @author peter
 *
 */
public class TestParentsDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	private ParentsDAO  store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testGetStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#putStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testPutStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#ediStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testEdiStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#deleteStudentParent(com.yahoo.petermwenda83.contoller.guardian.StudentParent)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.regparents.ParentsDAO#getAllStudentParent()}.
	 */
	//@Ignore
	@Test
	public void testGetAllStudentParent() {
		store = new ParentsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<StudentParent> list = store.getAllStudentParent();	 
			for (StudentParent l : list) {
				System.out.println(l);
				
			}
	}

}
