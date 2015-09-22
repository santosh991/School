/**
 * 
 */
package com.yahoo.petermwenda83.persistence.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Primary;

/**
 * @author peter
 *
 */
public class TestPrimaryDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID =  "085bda44-b7a2-4015-80b0-c778bb2043ba",
			       UUID_NEW =  "05477FB9-DFBC-491F-815B-752467DE2398";
	private String STUDENT_UUID =  "9771e08e-4e87-4fe4-9608-a31952ec10cd",
			       STUDENT_UUID_NEW =  "75d63eed-2e4e-43ed-915f-552443d628cd";
	private String SCHOOLNAME =  "Kambandi Day",
			       SCHOOLNAME_NEW =  "new Kambandi",
			       SCHOOLNAME_UPDATE =  "upadte Kambandi";
	private String INDEX =  "1930876001",
			       INDEX_NEW =  "new index",
			       INDEX_UPDATE =  "update index";
	private String KCPEYEAR =  "2006",
			       KCPEYEAR_NEW =  "new 2006",
			       KCPEYEAR_UPDATE =  "update 2006";
	private String KCPEMARKS =  "376",
			       KCPEMARKS_NEW =  "new 367",
			       KCPEMARKS_UPDATE =  "update443";
	
	private PrimaryDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.registration.PrimaryDAO#get(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new PrimaryDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Primary p = new Primary();
		p = store.get(STUDENT_UUID);
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getStudentUuid(),STUDENT_UUID);
		assertEquals(p.getSchoolname(),SCHOOLNAME);
		assertEquals(p.getIndex(),INDEX);
		assertEquals(p.getKcpeyear(),KCPEYEAR);
		assertEquals(p.getKcpemarks(),KCPEMARKS);
		
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.registration.PrimaryDAO#put(com.yahoo.petermwenda83.bean.student.Primary)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new PrimaryDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Primary p = new Primary();
		p.setUuid(UUID_NEW);
		p.setStudentUuid(STUDENT_UUID_NEW);
		p.setSchoolname(SCHOOLNAME_NEW);
		p.setIndex(INDEX_NEW);
		p.setKcpeyear(KCPEYEAR_NEW);
		p.setKcpemarks(KCPEMARKS_NEW);
		assertTrue(store.put(p));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.registration.PrimaryDAO#edit(com.yahoo.petermwenda83.bean.student.Primary, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEdit() {
		store = new PrimaryDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Primary p = new Primary();
		p.setStudentUuid(STUDENT_UUID_NEW);
		p.setSchoolname(SCHOOLNAME_UPDATE);
		p.setIndex(INDEX_UPDATE);
		p.setKcpeyear(KCPEYEAR_UPDATE);
		p.setKcpemarks(KCPEMARKS_UPDATE);
		assertTrue(store.edit(p, STUDENT_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.registration.PrimaryDAO#delete(com.yahoo.petermwenda83.bean.student.Primary, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store = new PrimaryDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Primary p = new Primary();
		p.setStudentUuid(STUDENT_UUID_NEW);
		assertTrue(store.delete(p, STUDENT_UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.registration.PrimaryDAO#getAllPrimary()}.
	 */
	@Ignore
	@Test
	public void testGetAllPrimary() {
		store = new PrimaryDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Primary> list = store.getAllPrimary();	  
			for (Primary l : list) {
				System.out.println(l);
			}
	}

}
