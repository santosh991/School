
/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/

package com.yahoo.petermwenda83.bean.schoolaccount;

import java.util.Date;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * A school
 * 
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 */
public class SchoolAccount extends StorableBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6003538799757391780L;
	private String statusUuid;
	private String schoolName;
	private String username;
	private String password;
	private String  mobile; 
	private String  email ;
	private Date creationDate;

	public SchoolAccount() {
		statusUuid ="";
		schoolName ="";
		username ="";
		password ="";
		mobile ="";
		email ="";
		creationDate = new Date(); 
	}
	

	/**
	 * @return the statusUuid
	 */
	public String getStatusUuid() {
		return statusUuid;
	}


	/**
	 * @param statusUuid the statusUuid to set
	 */
	public void setStatusUuid(String statusUuid) {
		this.statusUuid = statusUuid;
	}


	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}


	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}


	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("SchoolAccount");
		builder.append("[getUuid()=");
		builder.append(getUuid()); 
		builder.append(",statusUuid=");
		builder.append(statusUuid);
		builder.append(",schoolName=");
		builder.append(schoolName);
		builder.append(", username=");
		builder.append(username);
		builder.append(",password=");
		builder.append(password);
		builder.append(",mobile=");
		builder.append(mobile);
		builder.append(",email=");
		builder.append(email);
		builder.append(",creationDate=");
		builder.append(creationDate);
		builder.append("]");
		return builder.toString(); 
		}

}
