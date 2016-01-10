/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

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

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO#getSubjectClass(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetSubjectClass() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
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
	//@Ignore
	@Test
	public void testGetSubjectClassList() {
		store = new TeacherSubClassDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<TeacherSubClass> list = store.getSubjectClassList();	
		for (TeacherSubClass l : list) {
			System.out.println(l);	
		}
		
	}

}
