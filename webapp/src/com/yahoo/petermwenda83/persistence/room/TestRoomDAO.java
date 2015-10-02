/**
 * 
 */
package com.yahoo.petermwenda83.persistence.room;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.room.ClassRoom;
 
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
	
	private String UUID = "4DA86139-6A72-4089-8858-6A3A613FDFE6",
			       UUID_NEW = "0B41224A-4BE2-4151-B6B1-6C8ED67E5C7E";
	
	private String SCHOOL_UUID = "B643876D-B90C-435C-B215-CC558D596626";
	
	private String ROOMNAME = "N",
			       ROOMNAME_NEW = "S",
			       ROOMNAME_UPDATE = "W";
	private String ROOM = "FORM 1 ",
		           ROOM_NEW = "FORM 1",
		           ROOM_UPDATE = "FORM 1";

	
	
	private RoomDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#getroom(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r = store.getroom(UUID);
		assertEquals(r.getUuid(),UUID);
		assertEquals(r.getRoom(),ROOM);
		assertEquals(r.getRoomName(),ROOMNAME);
		assertEquals(r.getSchoolAccountUuid(),SCHOOL_UUID);
		
		
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#getroom(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom(); 
		r = store.get(ROOM, ROOMNAME);
		assertEquals(r.getUuid(),UUID);
		assertEquals(r.getRoomName(),ROOMNAME);
		assertEquals(r.getRoom(),ROOM);
		assertEquals(r.getSchoolAccountUuid(),SCHOOL_UUID);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#putroom(com.yahoo.petermwenda83.bean.room.ClassRoom)}.
	 */
	@Ignore
	@Test
	public void testPutroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setUuid(UUID_NEW); 
		r.setRoomName(ROOMNAME_NEW); 
		r.setRoom(ROOM_NEW); 
		r.setSchoolAccountUuid(SCHOOL_UUID); 
		assertTrue(store.putroom(r));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#editroom(com.yahoo.petermwenda83.bean.room.ClassRoom, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setUuid(UUID_NEW);
		r.setRoomName(ROOMNAME_UPDATE);
		r.setRoom(ROOM_UPDATE);
		r.setSchoolAccountUuid(SCHOOL_UUID); 
		assertTrue(store.editroom(r));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#deleteroom(com.yahoo.petermwenda83.bean.room.ClassRoom, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setRoomName(ROOMNAME_UPDATE);
		r.setRoom(ROOM_UPDATE);
		assertTrue(store.deleteroom(r));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.room.RoomDAO#getAllRooms()}.
	 */
	//@Ignore
	@Test
	public void testGetAllRooms() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ClassRoom> list = store.getAllRooms();	
		for (ClassRoom ss : list) {
			System.out.println(ss);
		}
	}

}
