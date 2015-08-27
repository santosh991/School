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
	private String nationalID;
	private String	county; 
	private String nhifno;
	private String  nssfno;
	private String DOB;
	private String location;
	String sublocation ;
	private String teacherNumber;
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
		sublocation ="";
		teacherNumber = "";
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
 * @return the sublocation
 */
public String getSublocation() {
	return sublocation;
}
/**
 * @param sublocation the sublocation to set
 */
public void setSublocation(String sublocation) {
	this.sublocation = sublocation;
}
/**
 * @return the teacherNumber
 */
public String getTeacherNumber() {
	return teacherNumber;
}

/**
 * @param teacherNumber the teacherNumber to set
 */
public void setTeacherNumber(String teacherNumber) {
	this.teacherNumber = teacherNumber;
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
