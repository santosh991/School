/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.classroom.Classes;

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
	
	final String UUID = "",
			     UUID_NEW = "";
	
	final String CLASS_NAME = "FORM 1",
			     CLASS_NAME_NEW = "FORM 1",
			     CLASS_NAME_UPDATE = "FORM 1";

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.ClassesDAO#getClass(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetClassString() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Classes c = new Classes();
		c = store.getClass(CLASS_NAME);
		assertEquals(c.getClassName(),CLASS_NAME);
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.ClassesDAO#getClass(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testPutClass() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Classes c = new Classes();
		c.setUuid(UUID);
		c.setClassName(CLASS_NAME_NEW); 
		assertTrue(store.putClass(c)); 
		
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.classroom.ClassesDAO#getClass(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testUpdateClass() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Classes c = new Classes();
		c.setUuid(UUID_NEW);
		c.setClassName(CLASS_NAME_UPDATE); 
		assertTrue(store.updateClass(c));  

	}
	
	@Ignore
	@Test
	public void testGetClassList() {
		store = new ClassesDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Classes> list = store.getClassList();
		for (Classes c : list) {
			System.out.println(c);
		}
	}

}
