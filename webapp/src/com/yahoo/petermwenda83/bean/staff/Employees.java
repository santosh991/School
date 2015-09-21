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
package com.yahoo.petermwenda83.bean.staff;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Employees extends AllBean{

	private String firstName;
	private String lastName;
	private String surname;
	
	private String phone;
	private String nationalID;
	
	private String	county; 
	private String location;
	
	
	private String nhifno;
	private String  nssfno;
	private String DOB;
	
	
	
	private String position ;
	private String salary ;
	
	private String	EmployeeUuid ;
	
/**
 * 
 */
	public Employees() {
		super();
		firstName = "";
		lastName = "";
		surname = "";
		phone = "";
		nationalID = "";
		county = "";
		nhifno = "";
		nssfno  = "";
		DOB = "";
		location ="";
		position = "";
		salary = "";
		
		
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
public String getNationalID() {
	return nationalID;
}
/**
 * @param nationalID the nationalID to set
 */
public void setNationalID(String nationalID) {
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
/**
 * @return the employeeUuid
 */
public String getEmployeeUuid() {
	return EmployeeUuid;
}
/**
 * @param employeeUuid the employeeUuid to set
 */
public void setEmployeeUuid(String employeeUuid) {
	EmployeeUuid = employeeUuid;
}
/**
 * @return the location
 */
public String getLocation() {
	return location;
}
/**
 * @param location the location to set
 */
public void setLocation(String location) {
	this.location = location;
}

/**
 * @return the position
 */
public String getPosition() {
	return position;
}
/**
 * @param position the position to set
 */
public void setPosition(String position) {
	this.position = position;
}
/**
 * @return the salary
 */
public String getSalary() {
	return salary;
}
/**
 * @param salary the salary to set
 */
public void setSalary(String salary) {
	this.salary = salary;
}






}
