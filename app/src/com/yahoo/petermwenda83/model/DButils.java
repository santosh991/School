package com.yahoo.petermwenda83.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;



public class DButils {
	private Logger logger = Logger.getLogger(this.getClass());
    protected String databaseName;
    protected String Host;
    protected String databaseUsername;
    protected String databasePassword;
    protected int databasePort;
    private String url = ""; 
    
	public DButils() {
		databaseName="allamanodb";
		Host="localhost";
		databaseUsername="allamano";
		databasePassword="AllaManO1";
		databasePort=5432;
	}
	
	public DButils(String databaseName,String Host,String databaseUsername ,
			String databasePassword,int databasePort){
		this.databaseName = databaseName;
		this.Host = Host;
		this.databaseUsername = databaseUsername;
		this.databasePassword = databasePassword;
		this.databasePort = databasePort;
	}


	public Connection getConnection() throws SQLException{
		Connection con = null;
		try{
	     url = "jdbc:postgresql://"+Host+":"+databasePort+"/"+databaseName;
	     con = DriverManager.getConnection(url, databaseUsername, databasePassword);
		}catch(SQLException ex){
		 logger.error(ExceptionUtils.getStackTrace(ex));
		}
		return con;
	}

	public void closeConnections() {
		// TODO Auto-generated method stub
		
	}

	

}