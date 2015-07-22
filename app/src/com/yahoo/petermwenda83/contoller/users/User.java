/**
 * 
 */
package com.yahoo.petermwenda83.contoller.users;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class User extends Employees {
	 private String uuid;
	 private String userType;
	 private String username;
	 private String passowrd;
	/**
	 *  
	 */
	public User() {
		super();
		uuid = "";
	    userType  = "";
	    username  = "";
	    passowrd  = "";
	}
	
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
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
	 * @return the passowrd
	 */
	public String getPassowrd() {
		return passowrd;
	}

	/**
	 * @param passowrd the passowrd to set
	 */
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Users");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", uuid =");
		builder.append("uuid");
		builder.append(", userType =");
		builder.append("userType");
		builder.append(", username =");
		builder.append("username");
		builder.append(", passowrd =");
		builder.append("passowrd");
		builder.append("]");
		return builder.toString(); 
		}
}
