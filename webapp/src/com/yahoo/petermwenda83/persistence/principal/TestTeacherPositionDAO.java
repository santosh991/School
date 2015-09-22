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
package com.yahoo.petermwenda83.persistence.principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.staff.teaching.TeacherPosition;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestTeacherPositionDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="B0EB8B2D-C192-41DC-87E3-FA075ED3C865",
			       UUID_NEW ="DDEE067C-3CE1-462C-9C06-8C12C30A2E3B";
	private String TEACHER_UUID ="A68F5C77-493F-49B1-973D-1B950987D1FC",
			       TEACHER_UUID_NEW ="50f11966-b316-4ccf-9893-b557d7fc1987";
	private String TEACHER_POSITION ="CU Chair Person",
			       TEACHER_POSITION_NEW ="new CU",
			       TEACHER_POSITION_UPDATE ="update CU";
	private String TEACHER_SALARY ="9000",
			       TEACHER_SALARY_NEW ="new9000",
			       TEACHER_SALARY_UPDATE ="update9000";
	
	
	private TeacherPositionDAO store;
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherPositionDAO#getstaffPos(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetstaffPos() {
		store = new TeacherPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		TeacherPosition p = new TeacherPosition();
		p = store.getstaffPos(TEACHER_UUID);
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getTeacherUuid(),TEACHER_UUID);
		assertEquals(p.getPosition(),TEACHER_POSITION);
		assertEquals(p.getSalary(),TEACHER_SALARY);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherPositionDAO#putTeacherPosition(com.yahoo.petermwenda83.bean.staff.teaching.TeacherPosition)}.
	 */
	@Ignore
	@Test
	public void testPutTeacherPosition() {
		store = new TeacherPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		TeacherPosition p = new TeacherPosition();
		p.setUuid(UUID_NEW);
		p.setTeacherUuid(TEACHER_UUID_NEW);
		p.setPosition(TEACHER_POSITION_NEW);
		p.setSalary(TEACHER_SALARY_NEW);
		assertTrue(store.putTeacherPosition(p));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherPositionDAO#editTeacherPosition(com.yahoo.petermwenda83.bean.staff.teaching.TeacherPosition, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditTeacherPosition() {
		store = new TeacherPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		TeacherPosition p = new TeacherPosition();
		p.setTeacherUuid(TEACHER_UUID_NEW);
		p.setPosition(TEACHER_POSITION_UPDATE);
		p.setSalary(TEACHER_SALARY_UPDATE);
		assertTrue(store.editTeacherPosition(p, TEACHER_UUID_NEW));
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherPositionDAO#deleteTeacherPosition(com.yahoo.petermwenda83.bean.staff.teaching.TeacherPosition, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteTeacherPosition() {
		store = new TeacherPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		TeacherPosition p = new TeacherPosition();
		p.setTeacherUuid(TEACHER_UUID_NEW);
		assertTrue(store.deleteTeacherPosition(p, TEACHER_UUID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.principal.TeacherPositionDAO#getAllTeacherPosition()}.
	 */
	@Ignore
	@Test
	public void testGetAllTeacherPosition() {
		store = new TeacherPositionDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<TeacherPosition> list = store.getAllTeacherPosition();	
		for (TeacherPosition ss : list) {
			System.out.println(ss);
		}
	}

}
