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
package com.yahoo.petermwenda83.persistence.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.money.Withdraw;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestPocketWithdrawDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="618a8bc0-1851-4026-bad3-1266d163fb83",
			       UUID_NEW ="CA291774-9C84-49B6-8913-42E854170113";
	
	private String STUDENT_UUID ="DAF7EC32-EA25-7D32-8708-2CC132446A2Y",
			       STUDENT_UUID_NEW ="75d63eed-2e4e-43ed-915f-552443d628cd";
	
	private Double AMOUNT = 1000.0,
			       AMOUNT_NEW = 50.0;
			       
	private Date WITHDRAWDATE = new Date(new Long("1419410347000")),
			     WITHDRAWDATE_NEW = new Date(new Long("1419410347000"));
			   
	
	private PocketWithdrawDAO store;


	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketWithdrawDAO#get(java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGet() {
		store = new PocketWithdrawDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Withdraw w = new Withdraw();
		w = store.get(STUDENT_UUID);
		assertEquals(w.getUuid(),UUID);
		assertEquals(w.getStudentUuid(),STUDENT_UUID);
		assertEquals(w.getAmount(),AMOUNT,0);
		//assertEquals(w.getWithdrawdate(),WITHDRAWDATE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketWithdrawDAO#put(com.yahoo.petermwenda83.bean.money.Deposit)}.
	 */
	//@Ignore
	@Test
	public void testPut() {
		store = new PocketWithdrawDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Withdraw w = new Withdraw();
		w.setStudentUuid(STUDENT_UUID);
		w.setAmount(AMOUNT_NEW); 
		w.setWithdrawdate(WITHDRAWDATE_NEW);
		assertTrue(store.put(w));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketWithdrawDAO#getWithdraw(com.yahoo.petermwenda83.bean.money.Deposit)}.
	 */
	@Ignore
	@Test
	public void testGetWithdraw() {
		store = new PocketWithdrawDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Withdraw w = new Withdraw();
		w.setStudentUuid(STUDENT_UUID);
		List<Withdraw> list = store.getWithdraw(w);	 
		for (Withdraw ss : list) {
			System.out.println(ss);
		}
		
	}

}
