/**
 * 
 */
package com.yahoo.petermwenda83.contoller.user;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class User extends AllBean {
	
	    private String uuid;
		private String usertype;
		private String username;
		private String passowrd;

	/**
	 * 
	 */
	public User() {
		super();
		uuid = "";;
		usertype = "";
		username = "";
		passowrd = "";
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
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}


	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
		builder.append("Users [getUuid()=");
		builder.append(getUuid());
		builder.append(", uuid = ");
		builder.append(uuid);
		builder.append(", usertype =");
		builder.append(usertype);
		builder.append("username=");
		builder.append(username);
		builder.append("passowrd=");
		builder.append(passowrd);
		builder.append("]");
		return builder.toString(); 
		}

}
