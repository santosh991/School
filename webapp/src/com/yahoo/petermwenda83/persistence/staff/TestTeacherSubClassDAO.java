/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.TeacherSubClass;

/**
 * @author peter
 *
 */
public class TestTeacherSubClassDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private TeacherSubClassDAO store;
	
	final String TEACHER_UUID = "F49DB775-4952-4915-B978-9D9F3E36D6E9";
	final String SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#getSubjectClass(java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGetSubjectClass() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		TeacherSubClass t = new TeacherSubClass();
		t = store.getSubjectClass(TEACHER_UUID); 
		assertEquals(t.getSubjectUuid(),SUB_UUID);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#putSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)}.
	 */
	@Ignore
	@Test
	public void testPutSubjectClass() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#updateSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)}.
	 */
	@Ignore
	@Test
	public void testUpdateSubjectClass() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#deleteSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)}.
	 */
	@Ignore
	@Test
	public void testDeleteSubjectClass() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#getSubjectClassList()}.
	 */
	@Ignore
	@Test
	public void testGetSubjectClassList() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<TeacherSubClass> list = store.getSubjectClassList();	
		for (TeacherSubClass l : list) {
			System.out.println(l);	
		}
		
	}

}
