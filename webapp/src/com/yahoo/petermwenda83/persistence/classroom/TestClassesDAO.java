/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.classroom.Classes;
import com.yahoo.petermwenda83.bean.staff.Duties;

/**
 * @author peter
 *
 */
public class TestClassesDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private ClassesDAO store;
	
	final String CLASS_UUID = "C143978A-E021-4015-BC67-5A00D6C910D1";
	final String CLASS_NAME = "FORM 1";

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.ClassesDAO#getClass(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetClassString() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Classes c = new Classes();
		c = store.getClass(CLASS_UUID);
		assertEquals(c.getClassName(),CLASS_NAME);
	}
	//@Ignore
	@Test
	public void testGetClassList() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Classes> list = store.getClassList();
		for (Classes c : list) {
			System.out.println(c);
		}
	}

}
