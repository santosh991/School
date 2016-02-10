package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.StudentHouse;

public class TestHouseDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String UUID = "",
			     UUID_NEW = "";
	
	final String STUDENT_UUID = "";
	
	final String HOUSE_UUID = "",
			     HOUSE_UUID_NEW = "",
			     HOUSE_UUID_UPDATE = "";
	
	final String USER = "",
			     USER_NEW = "",
			     USER_UPDATE = "";
	
	final Date DATE_IN = new Date();
	
	private HouseDAO store;
	@Ignore
	@Test
	public void testGetHouse() {
		store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentHouse h = new StudentHouse();
		h = store.getHouse(STUDENT_UUID);
		assertEquals(h.getStudentUuid(),STUDENT_UUID);
		assertEquals(h.getUuid(),UUID);
		assertEquals(h.getHouseUuid(),HOUSE_UUID);
		assertEquals(h.getSysUser(),USER);
		//assertEquals(h.getDateIn(),DATE_IN);
		
	}
	@Ignore
	@Test
	public void testPutHouse() {
		store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentHouse h = new StudentHouse();
		h.setUuid(UUID_NEW);
		h.setStudentUuid(STUDENT_UUID);
		h.setHouseUuid(HOUSE_UUID_NEW);
		h.setSysUser(USER_NEW);
		h.setDateIn(DATE_IN);
		assertTrue(store.putHouse(h)); 
	}
	@Ignore
	@Test
	public void testUpdatetHouse() {
		store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentHouse h = new StudentHouse();
		h.setUuid(UUID_NEW);
		h.setStudentUuid(STUDENT_UUID);
		h.setHouseUuid(HOUSE_UUID_UPDATE);
		h.setSysUser(USER_UPDATE);
		h.setDateIn(DATE_IN);
		assertTrue(store.updatetHouse(h));  
	}
    @Ignore
	@Test
	public void testDeleteHouse() {
		store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentHouse h = new StudentHouse();
		h.setStudentUuid(STUDENT_UUID);
		assertTrue(store.deleteHouse(h));   
	}
	@Ignore
	@Test
	public void testGetHouseList() {
		store = new HouseDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentHouse> list = store.getHouseList();
		for (StudentHouse l : list) {
			System.out.println(l);	
		}
	}

}
