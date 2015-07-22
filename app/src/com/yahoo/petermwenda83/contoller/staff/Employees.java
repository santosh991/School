/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class Employees extends AllBean{

	private String firstName;
	private String lastName;
	private String surname;
	private String phone;
	private int nationalID;
	private String	county; 
	private String nhifno;
	private String  nssfno;
	private String DOB;
	
/**
 * 
 */
	public Employees() {
		super();
		firstName = "";
		lastName = "";
		surname = "";
		phone = "";
		nationalID = 0;
		county = "";
		nhifno = "";
		nssfno  = "";
		DOB = "";
		
	}
/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}
/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
/**
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}
/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}
/**
 * @return the surname
 */
public String getSurname() {
	return surname;
}
/**
 * @param surname the surname to set
 */
public void setSurname(String surname) {
	this.surname = surname;
}
/**
 * @return the phone
 */
public String getPhone() {
	return phone;
}
/**
 * @param phone the phone to set
 */
public void setPhone(String phone) {
	this.phone = phone;
}
/**
 * @return the nationalID
 */
public int getNationalID() {
	return nationalID;
}
/**
 * @param nationalID the nationalID to set
 */
public void setNationalID(int nationalID) {
	this.nationalID = nationalID;
}
/**
 * @return the county
 */
public String getCounty() {
	return county;
}
/**
 * @param county the county to set
 */
public void setCounty(String county) {
	this.county = county;
}
/**
 * @return the dOB
 */
public String getDOB() {
	return DOB;
}
/**
 * @param dOB the dOB to set
 */
public void setDOB(String dOB) {
	DOB = dOB;
}
/**
 * @return the nhifno
 */
public String getNhifno() {
	return nhifno;
}
/**
 * @param nhifno the nhifno to set
 */
public void setNhifno(String nhifno) {
	this.nhifno = nhifno;
}
/**
 * @return the nssfno
 */
public String getNssfno() {
	return nssfno;
}
/**
 * @param nssfno the nssfno to set
 */
public void setNssfno(String nssfno) {
	this.nssfno = nssfno;
}


}
