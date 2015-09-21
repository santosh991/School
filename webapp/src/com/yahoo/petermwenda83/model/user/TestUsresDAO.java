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
package com.yahoo.petermwenda83.model.user;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.users.User;

/**
 *@author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestUsresDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;

	final String UUID ="740d418c-a50c-4d4c-ac8b-0d58714a8064";
	
	final String  USER_UUID = "730d418c-a50c-4d4c-ac8b-0d58714a8065",
			      USER_UUID_NEW = "678d418c-a50c-4d4c-ac8b-0d58714a8095";
	final String  USER_USERNAME = "karani",
			      USER_USERNAME_NEW = "kewuser";
	final String  USER_PASS = "karani",
			      USER_PASS_NEW = "newpas";
	final String  USER_PYPE  = "Principal";


	private UsresDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#getUser(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetUser() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
        User user = new User();
		
        user = store.getUser(USER_UUID);
		assertEquals(user.getUuid(),USER_UUID);
		assertEquals(user.getUsername(),USER_USERNAME);
		assertEquals(user.getPassword(),USER_PASS);
		assertEquals(user.getUserType(),USER_PYPE);
	}

	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#getUserName(com.yahoo.petermwenda83.contoller.users.User)}.
	 */
	@Ignore
	@Test
	public void testGetUserName() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
        User user = new User();
        user.setUsername(USER_USERNAME); 
        user.setPassword(USER_PASS); 
        user.setUserType(USER_PYPE); 
        
        user = store.getUserName(user);  
        assertEquals(user.getUuid(),USER_UUID);
        assertEquals(user.getUsername(),USER_USERNAME);
        assertEquals(user.getPassword(),USER_PASS);
        assertEquals(user.getUserType(),USER_PYPE);
	}

	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#editUser(com.yahoo.petermwenda83.contoller.users.User, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditUser() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		User user = new User();
		user.setUuid(USER_UUID);
		user.setPassword(USER_PASS);
		user.setUserType(USER_PYPE);
		user.setUsername(USER_USERNAME); 
		assertTrue(store.editUser(user, USER_UUID));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#putUser(com.yahoo.petermwenda83.contoller.users.User)}.
	 */
	@Ignore
	@Test
	public void testPutUser() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		User user = new User();
		user.setUuid(USER_UUID_NEW);
		user.setPassword(USER_PASS_NEW);
		user.setUserType(USER_PYPE);
		user.setUsername(USER_USERNAME_NEW); 
		assertTrue(store.putUser(user));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#deleteUser(com.yahoo.petermwenda83.contoller.users.User)}.
	 */
	@Ignore
	@Test
	public void testDeleteUser() {
		store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		User user = new User();
		user.setUuid(UUID); 
		assertTrue(store.deleteUser(user));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.user.UsresDAO#getAllUsers()}.
	 */
	//@Ignore
	@Test
	public void testGetAllUsers() {
		 store = new UsresDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<User> list = store.getAllUsers();	
			assertEquals(list.size(), 6);
			System.out.println(list);
			for (User l : list) {
				System.out.println(l);
				
			}
		 
	
	}

	
}