/**
 * 
 */
package com.yahoo.petermwenda83.model.user;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.user.User;

/**
 * @author peter
 *
 */
public class TestUsresDAO {


final String databaseName = "allamanodb";
final String Host = "localhost";
final String databaseUsername = "allamano";
final String databasePassword = "AllaManO1";
final int databasePort = 5432;

final String  USER_UUID = "730d418c-a50c-4d4c-ac8b-0d58714a8065";
final String  USER_USERNAME = "karani";
final String  USER_PASS = "karani";
final String  USER_PYPE  = "Principal";


private UsresDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#getUser(java.lang.String)}.
	 */
    //@Ignore
	@Test
	public void testGetUser() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
        User user = new User();
		
        user = store.getUser(USER_UUID);
		assertEquals(user.getUuid(),USER_UUID);
		assertEquals(user.getUsername(),USER_USERNAME);
		assertEquals(user.getPassowrd(),USER_PASS);
		assertEquals(user.getUsertype(),USER_PYPE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#editUser(com.yahoo.petermwenda83.contoller.user.User, java.lang.String)}.
	 */
    @Ignore
	@Test
	public void testEditUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#putUser(com.yahoo.petermwenda83.contoller.user.User)}.
	 */ 
    @Ignore
	@Test
	public void testPutUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#deleteUser(com.yahoo.petermwenda83.contoller.user.User)}.
	 */
    @Ignore
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#getAllUsers()}.
	 */
	 @Ignore
	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

}
