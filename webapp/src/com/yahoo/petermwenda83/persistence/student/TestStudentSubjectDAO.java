/**
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.StudentSubClassRoom;

/**
 * @author peter
 *
 */
public class TestStudentSubjectDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	 private StudentSubjectDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO#getSubRoom(java.lang.String)}.
	 */
	 @Ignore
	@Test
	public void testGetSubRoom() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO#putSubRoom(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)}.
	 */
	 @Ignore
	@Test
	public void testPutSubRoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO#updateSubRoom(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)}.
	 */
	 @Ignore
	@Test
	public void testUpdateSubRoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO#deleteSubRoom(com.yahoo.petermwenda83.bean.student.StudentSubClassRoom)}.
	 */
	 @Ignore
	@Test
	public void testDeleteSubRoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentSubjectDAO#getSubRoomList()}.
	 */
	// @Ignore
	@Test
	public void testGetSubRoomList() {
		store = new StudentSubjectDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentSubClassRoom> list = store.getSubRoomList();	
		for (StudentSubClassRoom l : list) {
			System.out.println(l);	
		}
	}

}
