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
package com.yahoo.petermwenda83.contoller.users;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class User extends AllBean {
	 private String uuid;
	 private String userType;
	 private String username;
	 private String password;
	/**
	 *  
	 */
	public User() {
		super();
	    uuid = "";
	    userType  = "";
	    username  = "";
	    password  = "";
	}
	
	/**
	 * @return the uuid
	 */
	@Override
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("User [ getUuid() =");
		builder.append(getUuid());
		builder.append(", userType =");
		builder.append(userType);
		builder.append(", username =");
		builder.append(username);
		builder.append(", password =");
		builder.append(password);
		builder.append("]");
		return builder.toString(); 
		}
}
