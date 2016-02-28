/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.money.StudentAmount;

/**
 * @author peter
 *
 */
public class TestStudentAmountDAO {
	

	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private StudentAmountDAO store;
	
	final String UUID = "6E261DBD-5AA1-4664-87F4-C72A941F61E0",
			     UUID_NEW ="80FEAEBA-829E-4BD8-A6BD-3DF8924D39D1";
	
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
			   
	final String STUDENT_UUID = "4F218688-6DE5-4E69-8690-66FBA2F0DC9F",
			     STUDENTUUID_NEW ="A195BAF6-D6E7-43A5-B7C9-D6C627A42815";

	
	final double AMOUNT_PAID = 30000,
			     AMOUNT_PAID_NEW =80000,
			     AMOUNT_PAID_UPDATE =1000;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.StudentAmountDAO#getStudentAmount(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public final void testGetStudentAmount() {
		store = new StudentAmountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentAmount studentAmount = new StudentAmount();
		studentAmount = store.getStudentAmount(SCHOOL_UUID, STUDENT_UUID);
		assertEquals(studentAmount.getUuid(),UUID);
		assertEquals(studentAmount.getSchoolAccountUuid(),SCHOOL_UUID);
		assertEquals(studentAmount.getStudentUuid(),STUDENT_UUID);
		assertEquals(studentAmount.getAmount(),AMOUNT_PAID,0);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.StudentAmountDAO#hasAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)}.
	 */
	@Ignore
	@Test
	public final void testHasAmount() {
		store = new StudentAmountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentAmount studentAmount = new StudentAmount();
		studentAmount.setSchoolAccountUuid(SCHOOL_UUID);
		studentAmount.setStudentUuid(STUDENT_UUID);
		assertTrue(store.hasAmount(SCHOOL_UUID,STUDENT_UUID, AMOUNT_PAID_NEW));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.StudentAmountDAO#addAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)}.
	 */
	//@Ignore
	@Test
	public final void testAddAmount() {
		store = new StudentAmountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentAmount studentAmount = new StudentAmount();
		studentAmount.setSchoolAccountUuid(SCHOOL_UUID);
		studentAmount.setStudentUuid(STUDENTUUID_NEW);
		assertTrue(store.addAmount(SCHOOL_UUID,"4F218688-6DE5-4E69-8690-66FBA2F0DC9F", 50000));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.StudentAmountDAO#deductAmount(com.yahoo.petermwenda83.bean.money.StudentAmount, double)}.
	 */
	@Ignore
	@Test
	public final void testDeductAmount() {
		store = new StudentAmountDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		StudentAmount studentAmount = new StudentAmount();
		studentAmount.setSchoolAccountUuid(SCHOOL_UUID);
		studentAmount.setStudentUuid(STUDENTUUID_NEW);
		assertTrue(store.deductAmount(SCHOOL_UUID,"4F218688-6DE5-4E69-8690-66FBA2F0DC9F", 10000));
	}

}
