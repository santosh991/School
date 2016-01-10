/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.ClassTeacher;

/**
 * @author peter
 *
 */
public class TestClassTeacherDAO {
	

	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String TEACHER_UUID = "708D2D79-BD0A-4338-BC75-62A5659A4F56";
	final String CLASS_ROOM_UUID = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	final String UUID = "93521ABA-E702-4F6F-BBA2-79107B2CF327";
	
	private ClassTeacherDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO#getClassTeacher(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetClassTeacher() {
		store = new ClassTeacherDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ClassTeacher c = new ClassTeacher();
		c = store.getClassTeacher(TEACHER_UUID);
		assertEquals(c.getClassRoomUuid(),CLASS_ROOM_UUID);
		assertEquals(c.getUuid(),UUID);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO#putClassTeacher(com.yahoo.petermwenda83.bean.staff.ClassTeacher)}.
	 */
	@Ignore
	@Test
	public void testPutClassTeacher() {
		store = new ClassTeacherDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO#updateClassTeacher(com.yahoo.petermwenda83.bean.staff.ClassTeacher)}.
	 */
	@Ignore
	@Test
	public void testUpdateClassTeacher() {
		store = new ClassTeacherDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO#deleteClassTeacher(com.yahoo.petermwenda83.bean.staff.ClassTeacher)}.
	 */
	@Ignore
	@Test
	public void testDeleteClassTeacher() {
		store = new ClassTeacherDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO#getClassTeacherList()}.
	 */
	@Ignore
	@Test
	public void testGetClassTeacherList() {
		store = new ClassTeacherDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ClassTeacher> list = store.getClassTeacherList();
		for (ClassTeacher ss : list) {
			System.out.println(ss);
		}
	}

}
