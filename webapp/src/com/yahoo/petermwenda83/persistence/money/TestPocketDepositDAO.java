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

import com.yahoo.petermwenda83.bean.money.pocket.Deposit;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestPocketDepositDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private String UUID ="e2a27b84-4352-4d8c-b495-3431a962c585";
	
    private String STUDENT_UUID ="DAF7EC32-EA25-7D32-8708-2CC132446A2Y";
		 
    private Double AMOUNT = 3000.0;
    
    private Double AMOUNT2 = 10.0;
		           
    private Date DEPOSITEDATE = new Date(new Long("1419410347000"));
                  

	
	private PocketDepositDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketDepositDAO#getPocketMoney(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetPocketMoney() {
		store = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Deposit p = new Deposit();
		p = store.getPocketMoney(STUDENT_UUID);
		assertEquals(p.getUuid(),UUID);
		assertEquals(p.getStudentUuid(),STUDENT_UUID);
		assertEquals(p.getAmount(),AMOUNT,0);
		//assertEquals(p.getDepositedate(),DEPOSITEDATE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketDepositDAO#hasBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testHasBalance() {
		store = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Deposit p = new Deposit();
		p.setStudentUuid(STUDENT_UUID);
		assertTrue(store.hasBalance(p, AMOUNT));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketDepositDAO#addBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testAddBalance() {
		store = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Deposit p = new Deposit();
		p.setStudentUuid(STUDENT_UUID);
		assertTrue(store.addBalance(p, AMOUNT2));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketDepositDAO#deductBalance(com.yahoo.petermwenda83.bean.money.pocket.Deposit, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testDeductBalance() {
		store = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		Deposit p = new Deposit();
		p.setStudentUuid(STUDENT_UUID);
		assertTrue(store.deductBalance(p, AMOUNT2));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.PocketDepositDAO#getAllBalanaces()}.
	 */
	@Ignore
	@Test
	public void testGetAllBalanaces() {
		store = new PocketDepositDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<Deposit> list = store.getAllBalanaces();	
		for (Deposit ss : list) {
			System.out.println(ss);
		}
	}

}
