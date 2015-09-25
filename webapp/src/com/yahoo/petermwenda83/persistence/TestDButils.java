/**
 * 
 */
package com.yahoo.petermwenda83.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */

public class TestDButils {
	private DButils dButils;
	@Test
	public void getConnection() throws SQLException {
		System.out.println("connection test"); 
		
	dButils = new DButils("schooldb", "localhost", "school", "AllaManO1", 5432);
		
		Connection con; 
		con = dButils.getConnection();
		System.out.println("Connection is: " + con);
	}

}
