/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;

/**
 * @author peter
 *
 */
public class TestRoomDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	
	final String ROOM_NAME = "FORM 1  N";
	
	
	private RoomDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.RoomDAO#getroom(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetroom() {
		store = new RoomDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.RoomDAO#putroom(com.yahoo.petermwenda83.bean.classroom.ClassRoom)}.
	 */
	//@Ignore
	@Test
	public void testPutroom() {
		store = new RoomDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom c = new ClassRoom();
		c = store.getroomByRoomName(SCHOOL_UUID, ROOM_NAME);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.RoomDAO#updateroom(com.yahoo.petermwenda83.bean.classroom.ClassRoom)}.
	 */
	@Ignore
	@Test
	public void testUpdateroom() {
		store = new RoomDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.RoomDAO#deleteroom(com.yahoo.petermwenda83.bean.classroom.ClassRoom)}.
	 */
	@Ignore
	@Test
	public void testDeleteroom() {
		store = new RoomDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.RoomDAO#getAllRooms(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetAllRooms() {
		store = new RoomDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ClassRoom> list = store.getAllRooms(SCHOOL_UUID);
		for (ClassRoom cl : list) {
			System.out.println(cl); 
		}
	}

}
