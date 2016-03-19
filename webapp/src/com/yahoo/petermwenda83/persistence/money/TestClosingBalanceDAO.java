/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author peter
 *
 */
public class TestClosingBalanceDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
    final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	
	final String STUDENT_UUID = "4F218688-6DE5-4E69-8690-66FBA2F0DC9F",
		         STUDENTUUID_NEW ="A195BAF6-D6E7-43A5-B7C9-D6C627A42815";
	
	final String TERM = "1";
	final String YEAR = "2014";
	
	
	private ClosingBalanceDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO#getClosingBalanceByStudentId(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public final void testGetClosingBalanceByStudentId() {
		store = new ClosingBalanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO#getClosingBalanceList(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public final void testGetClosingBalanceList() {
		store = new ClosingBalanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.money.ClosingBalanceDAO#putClosingBalance(com.yahoo.petermwenda83.bean.money.ClosingBalance)}.
	 */
	@Ignore
	@Test
	public final void testPutClosingBalance() {
		store = new ClosingBalanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
	}

}
