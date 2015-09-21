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
package com.yahoo.petermwenda83.dao.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Activity;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestActivityDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String STU_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			       STU_UUID_NEW = "75d63eed-2e4e-43ed-915f-552443d628cd";
	private String STU_ACTIVITY = "Socker",
			       STU_ACTIVITY_NEW = "newctivity",
			       STU_ACTIVITY_UPDATE = "Updatectivity";
	
	private String UUID = "C3CFA249-F2A3-8253-1F44-B1C594C6A&G2";
	
	private ActivityDAO store; 

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.ActivityDAO#getStudent(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetStudent() {
		 store = new ActivityDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Activity a = new Activity();
		 a = store.getStudent(STU_UUID);
		 assertEquals(a.getUuid(),UUID);
		 assertEquals(a.getStudentUuid(),STU_UUID);
		 assertEquals(a.getActivity(),STU_ACTIVITY);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.ActivityDAO#putStudent(com.yahoo.petermwenda83.bean.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testPutStudent() {
		 store = new ActivityDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Activity activity = new Activity();
		 activity.setStudentUuid(STU_UUID_NEW);
		 activity.setActivity(STU_ACTIVITY_NEW);
		 assertTrue(store.putStudent(activity));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.ActivityDAO#editStudent(com.yahoo.petermwenda83.bean.student.Activity, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditStudent() {
		 store = new ActivityDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Activity activity = new Activity();
		 activity.setStudentUuid(STU_UUID_NEW);
		 activity.setActivity(STU_ACTIVITY_UPDATE);
		 assertTrue(store.editStudent(activity, STU_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.ActivityDAO#deleteStudent(com.yahoo.petermwenda83.bean.student.Activity)}.
	 */
	@Ignore
	@Test
	public void testDeleteStudent() {
		 store = new ActivityDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 Activity activity = new Activity();
		 activity.setStudentUuid(STU_UUID_NEW);
		 assertTrue(store.deleteStudent(activity));  
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.registration.ActivityDAO#getAllActivity()}.
	 */
	@Ignore
	@Test
	public void testGetAllActivity() {
		 store = new ActivityDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		 List<Activity> list = store.getAllActivity();	  
			for (Activity l : list) {
				System.out.println(l);
			}
	}

}
