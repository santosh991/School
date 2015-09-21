/**
 * 
 */
package com.yahoo.petermwenda83.model.exam.main;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.main.MainDetails;

/**
 * @author peter
 *
 */
public class TestMainDetailsDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	
	private String UUID = "38EFA2D4-352D-4BC0-887F-9CA227950501",
			       UUID_NEW ="605A11C8-7408-41E5-96AA-6D4E05C6D10C";
	
	private String MAIN_UUID ="AE24F15B-5038-4A15-8607-1DB2A7A0B7DE",
			       MAIN_UUID_NEW="4531A31D-1F8A-40D7-BFE6-D3CB3D91951A";
	
	private String ROOM_NAME_UUID ="13C66627-450B-4808-A6A9-E26256BF2E68",
			       ROOM_NAME_UUID_NEW="4927E344-0A7C-40A1-AD9B-4A0E4AC305B8";
	
	private String SUBJECT_UUID = "66027e51-b1ad-4b10-8250-63af64d23323",
			       SUBJECT_UUID_NEW="b9bbd718-b32f-4466-ab34-42f544ff900e";
	
	private String YEAR = "2015",
			       YEAR_NEW="2016",
			       YEAR_UPDATE="2017";
	
	private String TERM = "ONE",
			       TERM_NEW="TWO",
			       TERM_UPDATE="THREE";
	
	private int OUTOF = 70,
			    OUTOF_NEW=80,
			    OUTOF_UPDATE=90;
	
	
	private MainDetailsDAO store;
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#get(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainDetails m = new MainDetails();
		m = store.get(MAIN_UUID, ROOM_NAME_UUID, SUBJECT_UUID);
		assertEquals(m.getUuid(),UUID);
		assertEquals(m.getMainuuid(),MAIN_UUID);
	    assertEquals(m.getRoomnameuuid(),ROOM_NAME_UUID);
	    assertEquals(m.getSubjectUuid(),SUBJECT_UUID);
	    assertEquals(m.getTerm(),TERM);
	    assertEquals(m.getYear(),YEAR);
	    assertEquals(m.getOutof(),OUTOF);
	    
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#put(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainDetails m = new MainDetails();
		m.setUuid(UUID_NEW);
		m.setMainuuid(MAIN_UUID_NEW);
		m.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		m.setSubjectUuid(SUBJECT_UUID_NEW);
		m.setTerm(TERM_NEW);
		m.setYear(YEAR_NEW);
		m.setOutof(OUTOF_NEW);
		assertTrue(store.put(m));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#edit(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)}.
	 */
	@Ignore
	@Test
	public void testEdit() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainDetails m = new MainDetails();
		m.setMainuuid(MAIN_UUID_NEW);
		m.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		m.setSubjectUuid(SUBJECT_UUID_NEW);
		m.setTerm(TERM_UPDATE);
		m.setYear(YEAR_UPDATE);
		m.setOutof(OUTOF_UPDATE);
		assertTrue(store.edit(m)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#delete(com.yahoo.petermwenda83.contoller.exam.main.MainDetails)}.
	 */
	//@Ignore
	@Test
	public void testDelete() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		MainDetails m = new MainDetails();
		m.setMainuuid(MAIN_UUID_NEW);
		m.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		m.setSubjectUuid(SUBJECT_UUID_NEW);
		assertTrue(store.delete(m) ); 
	
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#getAllMainDetails()}.
	 */
	@Ignore
	@Test
	public void testGetAllMainDetails() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainDetails> list = store.getAllMainDetails();	 
		for (MainDetails l : list) {
			System.out.println(l);	
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.main.MainDetailsDAO#getAllMainDetails(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetAllMainDetailsString() {
		store = new MainDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<MainDetails> list = store.getAllMainDetails(TERM);
		for (MainDetails l : list) {
			System.out.println(l);	
		}
	}

}
