/**
 * 
 */
package com.yahoo.petermwenda83.persistence;

import java.sql.SQLException;

import com.yahoo.petermwenda83.server.servlet.util.DbPoolUtil;




/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class DBConnectDAO {

	
	 protected DButils dbutils;
	/**
	 * @throws SQLException 
	 * 
	 */
	public DBConnectDAO()  { 
	dbutils =  DbPoolUtil.getDBCredentials();
	}
	/**
	 * 
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public DBConnectDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		dbutils = new DButils(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	
	 
	public void closeConnections() {
		dbutils.closeConnections();
	}
	

}
