/**
 * 
 */
package com.yahoo.petermwenda83.persistence.schoolaccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;

/**
 * @author peter
 *
 */
public class TestAccountDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID = "B643876D-B90C-435C-B215-CC558D596626",
			       UUID_NEW ="51D90F63-39AD-409B-9645-7C6F4F7CC76F";
	private String SCHOOL_NAME ="Chuka Boys",
			       SCHOOL_NAME_NEW="New School",
			       SCHOOL_NAME_UPDATE="update School";
	
	private String USERNAME ="ChukaB",
			       USERNAME_NEW ="NewChukaB",
			       USERNAME_UPDATE ="UpdateChukaB";
	
	private String PASSWORD ="CB",
			       PASSWORD_NEW ="new",
			       PASSWORD_UPDATE ="update";
	
	private String MOBILE ="732425632",
			       MOBILE_NEW ="77777777",
			       MOBILE_UPDATE ="999999999";
	private String EMAIL ="chukab@yahoo.com",
			       EMAIL_NEW ="new@yahoo.com",
			       EMAIL_UPDATE ="update@yahoo.com";
	

	
	private AccountDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#getSchoolBySchoolName(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetSchoolBySchoolName() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		SchoolAccount s = new SchoolAccount();
		s = store.getSchoolBySchoolName(SCHOOL_NAME);
		assertEquals(s.getUuid(),UUID);
		assertEquals(s.getSchoolName(),SCHOOL_NAME);
		assertEquals(s.getUsername(),USERNAME);
		assertEquals(s.getPassword(),PASSWORD);
		assertEquals(s.getMobile(),MOBILE);
		assertEquals(s.getEmail(),EMAIL);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#getSchoolByUsername(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetSchoolByUsername() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		SchoolAccount s = new SchoolAccount();
		s = store.getSchoolBySchoolName(SCHOOL_NAME);
		assertEquals(s.getUuid(),UUID);
		assertEquals(s.getSchoolName(),SCHOOL_NAME);
		assertEquals(s.getUsername(),USERNAME);
		assertEquals(s.getPassword(),PASSWORD);
		assertEquals(s.getMobile(),MOBILE);
		assertEquals(s.getEmail(),EMAIL);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#put(com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		SchoolAccount s = new SchoolAccount();
		s.setUuid(UUID_NEW);
		s.setSchoolName(SCHOOL_NAME_NEW);
		s.setUsername(USERNAME_NEW);
		s.setPassword(PASSWORD_NEW);
		s.setMobile(MOBILE_NEW);
		s.setEmail(EMAIL_NEW);
		assertTrue(store.put(s));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#update(com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount)}.
	 */
	@Ignore
	@Test
	public void testUpdate() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		SchoolAccount s = new SchoolAccount();
		s.setUuid(UUID_NEW);
		s.setSchoolName(SCHOOL_NAME_UPDATE);
		s.setUsername(USERNAME_UPDATE);
		s.setPassword(PASSWORD_UPDATE);
		s.setMobile(MOBILE_UPDATE);
		s.setEmail(EMAIL_UPDATE);
		assertTrue(store.update(s)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#delete(com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		//SchoolAccount s = new SchoolAccount();
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.schoolaccount.AccountDAO#getAllSchools()}.
	 */
	@Ignore
	@Test
	public void testGetAllSchools() {
		store = new AccountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<SchoolAccount> list = store.getAllSchools();	
		for (SchoolAccount ss : list) {
			System.out.println(ss);
		}
		
		
		
	}

}
