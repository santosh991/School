/**
 * 
 */
package com.yahoo.petermwenda83.model.principal;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;

/**
 * @author peter
 *
 */
public class TestNonStaffRegistrationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
private String PUUID = "767b2084-570f-44c0-8416-fb5f25496a9f";
	
	private String TEACHER_UUID = "616361c1-e7c3-4336-ba25-5905ac0712a3";
			    
	
	private String TEACHER_FIRST_NAME = "Njoka";
			      
	
	private String TEACHER_LAST_NAME = "Mugera";
			     
	
	private String TEACHER_SURNAME = "Maina";
			      
	
	private String TEACHER_NHIF = "1234";
			     
	
	private String TEACHER_NSSF = "8765";
			      
	
	private String TEACHER_PHONE = "712346799";
			     
	
	private String TEACHER_DOB = "1986";
			      
	
	private String TEACHER_ID = "76347823";
			     
	
	private String TEACHER_NUMBER = "67856";
			       
	
	private String TEACHER_COUNTY = "meru";
			      
	
	private String TEACHER_LOCATION = "loc1";
			      
	private String TEACHER_SUB_LOCATION = "loc1";
			      
	
	private String TEACHER_POSITION = "Driver";
			      
	
	private String TEACHER_SALARY = "4000";
			      
	
	
	private NonStaffRegistrationDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.NonStaffRegistrationDAO#getNtStaff(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetNtStaff() {
		store = new NonStaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		NTstaff n = new NTstaff();
		n = (NTstaff) store.getNtStaff(TEACHER_UUID); 
		assertEquals(n.getUuid(),TEACHER_UUID);
		assertEquals(n.getFirstName(),TEACHER_FIRST_NAME);
		assertEquals(n.getLastName(),TEACHER_LAST_NAME);
		assertEquals(n.getSurname(),TEACHER_SURNAME);
		assertEquals(n.getNhifno(),TEACHER_NHIF);
		assertEquals(n.getNssfno(),TEACHER_NSSF);
		assertEquals(n.getPhone(),TEACHER_PHONE);
		assertEquals(n.getDOB(),TEACHER_DOB);
		assertEquals(n.getNationalID(),TEACHER_ID);
		assertEquals(n.getTeacherNumber(),TEACHER_NUMBER);
		assertEquals(n.getCounty(),TEACHER_COUNTY);
		assertEquals(n.getLocation(),TEACHER_LOCATION);
		assertEquals(n.getSublocation(),TEACHER_SUB_LOCATION);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.NonStaffRegistrationDAO#getNtstaffPos(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetNtstaffPos() {
		store = new NonStaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		NTPosition nt = new NTPosition();
		nt = (NTPosition) store.getNtstaffPos(TEACHER_UUID); 
		assertEquals(nt.getUuid(),PUUID);
		assertEquals(nt.getNTstaffUuid(),TEACHER_UUID);
		assertEquals(nt.getPosition(),TEACHER_POSITION);
		assertEquals(nt.getSalary(),TEACHER_SALARY);
	}

	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.NonStaffRegistrationDAO#getAllNTstaff()}.
	 */
	@Ignore
	@Test
	public void testGetAllNTstaff() {
		store = new NonStaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<NTstaff> list = store.getAllNTstaff();	  
		for (NTstaff l : list) {
			System.out.println(l);
		}
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.NonStaffRegistrationDAO#getAllNTstaff()}.
	 */
	@Ignore
	@Test
	public void testGetAllNTPosition() {
		store = new NonStaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<NTPosition> list = store.getAllNTPosition();	  
		for (NTPosition l : list) {
			System.out.println(l);
		}
	}

}
