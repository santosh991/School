/**
 * 
 */
package com.yahoo.petermwenda83.model;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author peter
 *
 */

public class TestDButils {
	private DButils dButils;
	@Test
	public void getConnection() throws SQLException {
		System.out.println("connection test"); 	
	dButils = new DButils("allamanodb", "localhost", "allamano", "AllaManO1", 5432);
		
		Connection con; 
		con = dButils.getConnection();
		System.out.println("Connection is: " + con);
	}

}
