/**
 * 
 */
package com.yahoo.petermwenda83.model.room;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.room.ClassRoom;
 
/**
 * @author peter
 *
 */
public class TestRoomDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID = "4DA86139-6A72-4089-8858-6A3A613FDFE6",
			       UUID_NEW = "0B41224A-4BE2-4151-B6B1-6C8ED67E5C7E";
	private String ROOMNAME = "FORM 1 N",
			       ROOMNAME_NEW = "new",
			       ROOMNAME_UPDATE = "update";
	
	
	
	private RoomDAO store;
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#getroom(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r = store.getroom(UUID);
		assertEquals(r.getUuid(),UUID);
		assertEquals(r.getRoomname(),ROOMNAME);
		
		
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#getroom(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom(); 
		r = store.get(ROOMNAME);
		assertEquals(r.getUuid(),UUID);
		assertEquals(r.getRoomname(),ROOMNAME);
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#putroom(com.yahoo.petermwenda83.contoller.room.ClassRoom)}.
	 */
	@Ignore
	@Test
	public void testPutroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setUuid(UUID_NEW); 
		r.setRoomname(ROOMNAME_NEW); 
		assertTrue(store.putroom(r));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#editroom(com.yahoo.petermwenda83.contoller.room.ClassRoom, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setUuid(UUID_NEW);
		r.setRoomname(ROOMNAME_UPDATE);
		assertTrue(store.editroom(r, ROOMNAME_UPDATE));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#deleteroom(com.yahoo.petermwenda83.contoller.room.ClassRoom, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteroom() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassRoom r = new ClassRoom();
		r.setRoomname(ROOMNAME_UPDATE);
		assertTrue(store.deleteroom(r, ROOMNAME_UPDATE));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.room.RoomDAO#getAllRooms()}.
	 */
	@Ignore
	@Test
	public void testGetAllRooms() {
		store = new RoomDAO (databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ClassRoom> list = store.getAllRooms();	
		for (ClassRoom ss : list) {
			System.out.println(ss);
		}
	}

}
