/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.main.Main;

/**
 * @author peter
 *
 */
public class TestMainDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID = "AE24F15B-5038-4A15-8607-1DB2A7A0B7DE",
			       UUID_NEW="339A9998-B549-4EF7-A71E-A3EFE3C1E99F";
	private String MAINNAME ="PAPER 1",
			       MAINNAME_NEW="new paper";
	
	
	private MainDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDAO#get(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new MainDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Main m = new Main();
		m = store.get(MAINNAME);
		assertEquals(m.getUuid(),UUID);
		assertEquals(m.getMainname(),MAINNAME);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDAO#put(com.yahoo.petermwenda83.contoller.exam.main.Main)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new MainDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Main m = new Main();
		m.setUuid(UUID_NEW);
		m.setMainname(MAINNAME_NEW);
		assertTrue(store.put(m));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDAO#delete(com.yahoo.petermwenda83.contoller.exam.main.Main)}.
	 */
	//@Ignore
	@Test
	public void testDelete() {
		store = new MainDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Main m = new Main();
		m.setUuid(UUID_NEW);
		m.setMainname(MAINNAME_NEW);
		assertTrue(store.delete(m)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDAO#getAllMain()}.
	 */
	@Ignore
	@Test
	public void testGetAllMain() {
		store = new MainDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Main> list = store.getAllMain();
		for (Main l : list) {
			System.out.println(l);	
		}
	}

}
