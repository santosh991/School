/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.cat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.cat.Cat;

/**
 * @author peter
 *
 */
public class TestCatDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="D50E6399-B913-42F2-A5B6-F0D4BAAF9571",
			       UUID_NEW="77D76D0C-CB86-4104-BC5F-A2BCDA77CC5B";
	private String CATNAME ="CAT 1",
			       CATNAME_NEW="new cat";
	
	
	private CatDAO store;
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatDAO#get(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new CatDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Cat c = new Cat();
		c = store.get(CATNAME);
		assertEquals(c.getUuid(),UUID);
		assertEquals(c.getCatname(),CATNAME);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatDAO#put(com.yahoo.petermwenda83.bean.exam.cat.Cat)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new CatDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Cat c = new Cat();
		c.setUuid(UUID_NEW);
		c.setCatname(CATNAME_NEW);
		assertTrue(store.put(c));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatDAO#delete(com.yahoo.petermwenda83.bean.exam.cat.Cat)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store = new CatDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Cat c = new Cat();
		c.setUuid(UUID_NEW);
		c.setCatname(CATNAME_NEW);
		assertTrue(store.delete(c)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatDAO#getAllCats()}.
	 */
	@Ignore
	@Test
	public void testGetAllCats() {
		store = new CatDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Cat> list = store.getAllCats();	
		for (Cat l : list) {
			System.out.println(l);	
		}
	}

}
