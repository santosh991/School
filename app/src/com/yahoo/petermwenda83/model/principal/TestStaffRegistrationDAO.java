/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;
import com.yahoo.petermwenda83.contoller.staff.teaching.Position;
import com.yahoo.petermwenda83.contoller.staff.teaching.Teacher;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestStaffRegistrationDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String PUUID = "93D73F25-857C-45E8-81D6-8A05C6A02D6D";
	
	private String TEACHER_UUID = "9A5D5377-06C5-4EA1-9A5F-ECE6BBDD7F09",
			       TEACHER_UUID_2 = "616361c1-e7c3-4336-ba25-5905ac0712a3";
	
	private String TEACHER_FIRST_NAME = "munene",
			       TEACHER_FIRST_NAME_NEW ="Newmunene" ,
			       TEACHER_FIRST_NAME_UPDATE = "Updatemunene";
	
	private String TEACHER_LAST_NAME = "nyaga",
			       TEACHER_LAST_NAME_NEW = "Newnyaga",
			       TEACHER_LAST_NAME_UPDATE = "Updatenyaga";
	
	private String TEACHER_SURNAME = "nesh",
			       TEACHER_SURNAME_NEW = "Newnesh",
			       TEACHER_SURNAME_UPDATE = "Updatenesh";
	
	private String TEACHER_NHIF = "8888",
			       TEACHER_NHIF_NEW = "New8888",
			       TEACHER_NHIF_UPDATE = "Update8888";
	
	private String TEACHER_NSSF = "333",
			       TEACHER_NSSF_NEW = "New333",
			       TEACHER_NSSF_UPDATE = "Update333";
	
	private String TEACHER_PHONE = "76588754",
			       TEACHER_PHONE_NEW = "New76588754",
			       TEACHER_PHONE_UPDATE = "Update76588754";
	
	private String TEACHER_DOB = "1986",
			       TEACHER_DOB_NEW = "1New986",
			       TEACHER_DOB_UPDATE = "Update1986";
	
	private String TEACHER_ID = "87757678",
			       TEACHER_ID_NEW = "New87757678",
			       TEACHER_ID_UPDATE = "Update87757678";
	
	private String TEACHER_NUMBER = "67856",
			       TEACHER_NUMBER_NEW = "New67856",
			       TEACHER_NUMBER_UPDATE = "Update67856";
	
	private String TEACHER_COUNTY = "meru",
			       TEACHER_COUNTY_NEW = "meru",
			       TEACHER_COUNTY_UPDATE = "Updatemeru";
	
	private String TEACHER_LOCATION = "naari",
			       TEACHER_LOCATION_NEW = "Newnaari",
			       NT_LOCATION_NEW = "loc1",
			       TEACHER_LOCATION_UPDATE = "Updatenaari";
	
	private String TEACHER_SUB_LOCATION_NEW = "Newsublocat",
			       TEACHER_SUB_LOCATION_UPDATE = "Updatesublocat";
	
	private String TEACHER_POSITION = "CM",
			       TEACHER_POSITION_NEW = "NewCM",
			       TEACHER_POSITION_UPDATE = "UpdateCM";
	
	private String TEACHER_SALARY = "7000",
			       TEACHER_SALARY_NEW = "8700",
			       TEACHER_SALARY_UPDATE = "";
	
	
	
	private StaffRegistrationDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getStaff(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStaff() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teacher t = new Teacher();
		t = store.getStaff(TEACHER_UUID);
		assertEquals(t.getUuid(),TEACHER_UUID);
		assertEquals(t.getFirstName(),TEACHER_FIRST_NAME);
		assertEquals(t.getLastName(),TEACHER_LAST_NAME);
		assertEquals(t.getSurname(),TEACHER_SURNAME);
		assertEquals(t.getNhifno(),TEACHER_NHIF);
		assertEquals(t.getNssfno(),TEACHER_NSSF);
		assertEquals(t.getPhone(),TEACHER_PHONE);
		assertEquals(t.getDOB(),TEACHER_DOB);
		assertEquals(t.getNationalID(),TEACHER_ID);
		assertEquals(t.getTeacherNumber(),TEACHER_NUMBER);
		assertEquals(t.getCounty(),TEACHER_COUNTY);
		assertEquals(t.getLocation(),TEACHER_LOCATION);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getstaffPos(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetstaffPos() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Position p = new Position();
		p = store.getstaffPos(TEACHER_UUID);
		assertEquals(p.getUuid(),PUUID);
		assertEquals(p.getTeacherUuid(),TEACHER_UUID);
		assertEquals(p.getPosition(),TEACHER_POSITION);
		assertEquals(p.getSalary(),TEACHER_SALARY);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putStaff(com.yahoo.petermwenda83.contoller.staff.Employees)}.
	 */
	@Ignore
	@Test
	public void testPutStaff() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teacher t = new Teacher();
		t.setFirstName(TEACHER_FIRST_NAME_NEW);
		t.setLastName(TEACHER_LAST_NAME_NEW);
		t.setSurname(TEACHER_SURNAME_NEW); 
		t.setNssfno(TEACHER_NSSF_NEW);
		t.setNhifno(TEACHER_NHIF_NEW);
		t.setPhone(TEACHER_PHONE_NEW); 
		t.setDOB(TEACHER_DOB_NEW);
		t.setNationalID(TEACHER_ID_NEW);
		t.setTeacherNumber(TEACHER_NUMBER_NEW);
		t.setCounty(TEACHER_COUNTY_NEW);
		t.setLocation(TEACHER_LOCATION_NEW);  
		assertTrue(store.putStaff(t));
		
		
		NTstaff n =new NTstaff();
		n.setFirstName(TEACHER_FIRST_NAME_NEW);
		n.setLastName(TEACHER_LAST_NAME_NEW);
		n.setSurname(TEACHER_SURNAME_NEW); 
		n.setNssfno(TEACHER_NSSF_NEW);
		n.setNhifno(TEACHER_NHIF_NEW);
		n.setPhone(TEACHER_PHONE_NEW); 
		n.setDOB(TEACHER_DOB_NEW);
		n.setNationalID(TEACHER_ID_NEW);
		n.setCounty(TEACHER_COUNTY_NEW);
		n.setLocation(NT_LOCATION_NEW);
		n.setSublocation(TEACHER_SUB_LOCATION_NEW);
	
		assertTrue(store.putStaff(n)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putstaffPOss(com.yahoo.petermwenda83.contoller.staff.Employees)}.
	 */
	@Ignore
	@Test
	public void testPutstaffPOss() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Position p = new Position();
		p.setTeacherUuid(TEACHER_UUID);
		p.setPosition(TEACHER_POSITION_NEW);
		p.setSalary(TEACHER_SALARY_NEW);
		assertTrue(store.putstaffPOss(p)); 
		
		NTPosition nt = new NTPosition();
		nt.setNTstaffUuid(TEACHER_UUID_2); 
		nt.setPosition(TEACHER_POSITION_NEW);
		nt.setSalary(TEACHER_SALARY_NEW);
		assertTrue(store.putstaffPOss(nt)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editStaff(com.yahoo.petermwenda83.contoller.staff.Employees)}.
	 */
	@Ignore
	@Test
	public void testEditStaff() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teacher t = new Teacher();
		t.setUuid(TEACHER_UUID);
		t.setFirstName(TEACHER_FIRST_NAME_UPDATE);
		t.setLastName(TEACHER_LAST_NAME_UPDATE);
		t.setSurname(TEACHER_SURNAME_UPDATE);
		t.setNssfno(TEACHER_NSSF_UPDATE);
		t.setNhifno(TEACHER_NHIF_UPDATE);
		t.setPhone(TEACHER_PHONE_UPDATE); 
		t.setDOB(TEACHER_DOB_UPDATE);
		t.setNationalID(TEACHER_ID_UPDATE);
		t.setTeacherNumber(TEACHER_NUMBER_UPDATE);
		t.setCounty(TEACHER_COUNTY_UPDATE);
		t.setLocation(TEACHER_LOCATION_UPDATE);  
		assertTrue(store.editStaff(t));
		
		NTstaff n =new NTstaff();
		n.setUuid(TEACHER_UUID_2);
		n.setFirstName(TEACHER_FIRST_NAME);
		n.setLastName(TEACHER_LAST_NAME_UPDATE);
		n.setSurname(TEACHER_SURNAME_UPDATE); 
		n.setNssfno(TEACHER_NSSF_UPDATE);
		n.setNhifno(TEACHER_NHIF_UPDATE);
		n.setPhone(TEACHER_PHONE_UPDATE); 
		n.setDOB(TEACHER_DOB_UPDATE);
		n.setNationalID(TEACHER_ID_UPDATE);
		n.setCounty(TEACHER_COUNTY_UPDATE);
		n.setLocation(TEACHER_LOCATION_UPDATE);
		n.setSublocation(TEACHER_SUB_LOCATION_UPDATE);
		
		assertTrue(store.editStaff(n));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editStaffPos(com.yahoo.petermwenda83.contoller.staff.Employees)}.
	 */
	//@Ignore
	@Test
	public void testEditStaffPos() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Position p = new Position();
		p.setTeacherUuid(TEACHER_ID_UPDATE);
		p.setPosition(TEACHER_POSITION_UPDATE);
		p.setSalary(TEACHER_SALARY_UPDATE);
		assertTrue(store.editStaffPos(p));  
		
		NTPosition nt = new NTPosition();
		nt.setNTstaffUuid(TEACHER_UUID); 
		nt.setPosition(TEACHER_POSITION_NEW);
		nt.setSalary(TEACHER_SALARY_NEW);
		assertTrue(store.editStaffPos(nt));  
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#deleteStaf(com.yahoo.petermwenda83.contoller.staff.Employees, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteStaf() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Teacher t = new Teacher();
		t.setUuid(TEACHER_UUID);
		NTstaff n =new NTstaff();
		n.setUuid(TEACHER_UUID_2); 
	    assertTrue(store.deleteStaf(t, TEACHER_UUID));
	    assertTrue(store.deleteStaf(n, TEACHER_UUID_2));
	    
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#deleteStaffPOs(com.yahoo.petermwenda83.contoller.staff.Employees, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteStaffPOs() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Position p = new Position();
		p.setTeacherUuid(TEACHER_UUID); 
		NTPosition nt = new NTPosition();
		nt.setNTstaffUuid(TEACHER_UUID_2); 
		assertTrue(store.deleteStaffPOs(p, TEACHER_UUID));
		assertTrue(store.deleteStaffPOs(nt, TEACHER_UUID_2));
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getAllTeacher()}.
	 */
	@Ignore
	@Test  
	public void testGetAllTeacher() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Teacher> list = store.getAllTeacher();	  
			for (Teacher l : list) {
				System.out.println(l);
			}
	}
	
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getAllTeacher()}.
	 */
	@Ignore
	@Test  
	public void testGetAllStaffPos() {
		store = new StaffRegistrationDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Position> list = store.getAllStaffPos();	  
			for (Position l : list) {
				System.out.println(l);
			}
	}

}
