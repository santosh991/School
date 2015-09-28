/**
 * 
 */
package com.yahoo.petermwenda83.bean.schoolaccount;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter
 *
 */
public class SchoolAccount extends AllBean {
	
	 private String SchoolName;
	 private String   Username;
	 private String   Password;
	 private String   Mobile; 
	 private String  Email;
	

	/**
	 * 
	 */
	public SchoolAccount() {
		
		 SchoolName = "";
		 Username = "";
		 Password = "";
		 Mobile = "";
		 Email = "";
		
	}
	
	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return SchoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return Mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("SchoolAccount ");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid()=");
		builder.append( getUuid());
		builder.append(", SchoolName =");
		builder.append(SchoolName);
		builder.append(", Username =");
		builder.append(Username);
		builder.append(", Password =");
		builder.append(Password);
		builder.append(", Mobile =");
		builder.append(Mobile);
		builder.append(", Email =");
		builder.append(Email);
		builder.append("]");
		return builder.toString(); 
		}
}
