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
package com.yahoo.petermwenda83.dao.principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.nonteaching.WorkerPosition;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestWorkersPositionDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="767b2084-570f-44c0-8416-fb5f25496a9f",
		           UUID_NEW ="13A31EFA-388A-4E29-B7F7-25ACF18CE617";
    private String WORKER_UUID ="616361c1-e7c3-4336-ba25-5905ac0712a3",
    		       WORKER_UUID_NEW ="7b9623b6-86be-4fbf-b64e-adead65fc304";
    private String WORKER_POSITION ="Driver",
    		       WORKER_POSITION_NEW ="newDriver",
    		       WORKER_POSITION_UPDATE ="updateDriver";
    private String WORKER_SALARY ="4000",
    		       WORKER_SALARY_NEW ="new4000",
    		       WORKER_SALARY_UPDATE ="update4000";
	
	
	private WorkersPositionDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.principal.WorkersPositionDAO#getWorkerPosition(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetWorkerPosition() {
		store = new WorkersPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		WorkerPosition p = new WorkerPosition();
		p = store.getWorkerPosition(UUID);
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getWorkerUuid(),WORKER_UUID);
		assertEquals(p.getPosition(),WORKER_POSITION);
		assertEquals(p.getSalary(),WORKER_SALARY);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.principal.WorkersPositionDAO#put(com.yahoo.petermwenda83.bean.staff.nonteaching.WorkerPosition)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store = new WorkersPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		WorkerPosition p = new WorkerPosition();
		p.setUuid(UUID_NEW);
		p.setWorkerUuid(WORKER_UUID_NEW);
		p.setPosition(WORKER_POSITION_NEW);
		p.setSalary(WORKER_SALARY_NEW);
		assertTrue(store.put(p));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.principal.WorkersPositionDAO#edit(com.yahoo.petermwenda83.bean.staff.nonteaching.WorkerPosition, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEdit() {
		store = new WorkersPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		WorkerPosition p = new WorkerPosition();
		p.setWorkerUuid(WORKER_UUID_NEW);
		p.setPosition(WORKER_POSITION_UPDATE);
		p.setSalary(WORKER_SALARY_UPDATE);
		assertTrue(store.edit(p, UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.principal.WorkersPositionDAO#delete(com.yahoo.petermwenda83.bean.staff.nonteaching.WorkerPosition, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store = new WorkersPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		WorkerPosition p = new WorkerPosition();
		p.setWorkerUuid(WORKER_UUID_NEW);
		assertTrue(store.delete(p, UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.principal.WorkersPositionDAO#getAllWorkerPosition()}.
	 */
	@Ignore
	@Test
	public void testGetAllWorkerPosition() {
		store = new WorkersPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		  List<WorkerPosition> list = store.getAllWorkerPosition();	
			for (WorkerPosition ss : list) {
				System.out.println(ss);
			}
		    
	}

}
