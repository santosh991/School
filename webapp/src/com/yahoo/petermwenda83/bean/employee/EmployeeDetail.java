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
package com.yahoo.petermwenda83.bean.employee;

import java.util.Date;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class EmployeeDetail extends Employee{

	private String FirstName;
	private String LastName;
	private String Surname;
	private String Gender;
	private String NhifNo;
	private String	NssfNo; 
	private String Phone;
	private String NationalID;
	private String  County;
	private String DOB;
	private String SysUser ;
	private Date RegDate;
	private String EmployeeNo ; 
	
/**
 * 
 */
	public EmployeeDetail() {
		super();
		FirstName ="";
		LastName ="";
		Surname ="";
		Gender ="";
		NhifNo ="";
		NssfNo ="";
		Phone ="";
		NationalID ="";
		County ="";
		DOB ="";
		SysUser ="";
		RegDate = new Date();
		EmployeeNo ="";
		
	}
	
/**
 * @return the firstName
 */
public String getFirstName() {
	return FirstName;
}

/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	FirstName = firstName;
}

/**
 * @return the lastName
 */
public String getLastName() {
	return LastName;
}

/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	LastName = lastName;
}

/**
 * @return the surname
 */
public String getSurname() {
	return Surname;
}

/**
 * @param surname the surname to set
 */
public void setSurname(String surname) {
	Surname = surname;
}

/**
 * @return the gender
 */
public String getGender() {
	return Gender;
}

/**
 * @param gender the gender to set
 */
public void setGender(String gender) {
	Gender = gender;
}

/**
 * @return the nhifNo
 */
public String getNhifNo() {
	return NhifNo;
}

/**
 * @param nhifNo the nhifNo to set
 */
public void setNhifNo(String nhifNo) {
	NhifNo = nhifNo;
}

/**
 * @return the nssfNo
 */
public String getNssfNo() {
	return NssfNo;
}

/**
 * @param nssfNo the nssfNo to set
 */
public void setNssfNo(String nssfNo) {
	NssfNo = nssfNo;
}

/**
 * @return the phone
 */
public String getPhone() {
	return Phone;
}

/**
 * @param phone the phone to set
 */
public void setPhone(String phone) {
	Phone = phone;
}

/**
 * @return the nationalID
 */
public String getNationalID() {
	return NationalID;
}

/**
 * @param nationalID the nationalID to set
 */
public void setNationalID(String nationalID) {
	NationalID = nationalID;
}

/**
 * @return the county
 */
public String getCounty() {
	return County;
}

/**
 * @param county the county to set
 */
public void setCounty(String county) {
	County = county;
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
 * @return the sysUser
 */
public String getSysUser() {
	return SysUser;
}

/**
 * @param sysUser the sysUser to set
 */
public void setSysUser(String sysUser) {
	SysUser = sysUser;
}

/**
 * @return the regDate
 */
public Date getRegDate() {
	return RegDate;
}

/**
 * @param regDate the regDate to set
 */
public void setRegDate(Date regDate) {
	RegDate = regDate;
}

/**
 * @return the employeeNo
 */
public String getEmployeeNo() {
	return EmployeeNo;
}

/**
 * @param employeeNo the employeeNo to set
 */
public void setEmployeeNo(String employeeNo) {
	EmployeeNo = employeeNo;
}

/**
 * @return the employeeUuid
 */
public String getEmployeeUuid() {
	return getEmployeeUuid();
}

/**
 * @param employeeUuid the employeeUuid to set
 */
public void setEmployeeUuid(String employeeUuid) {
	setEmployeeUuid(employeeUuid);
}

@Override
public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("Employee Detail [ getUuid() =");
	builder.append(getUuid());
	builder.append(", FirstName =");
	builder.append(FirstName);
	builder.append(", LastName =");
	builder.append(LastName);
	builder.append(", Surname =");
	builder.append(Surname);
	builder.append(", Gender =");
	builder.append(Gender);
	builder.append(", NhifNo =");
	builder.append(NhifNo);
	builder.append(", NssfNo =");
	builder.append(NssfNo);
	builder.append(", Phone =");
	builder.append(Phone);
	builder.append(", NationalID =");
	builder.append(NationalID);
	builder.append(", County =");
	builder.append(County);
	builder.append(", DOB =");
	builder.append(DOB);
	builder.append(", SysUser =");
	builder.append(SysUser);
	builder.append(", RegDate =");
	builder.append(RegDate);
	builder.append(", EmployeeNo =");
	builder.append(EmployeeNo);
	builder.append(",getEmployeeUuid() =");
	builder.append(getEmployeeUuid());
	builder.append("]");
	return builder.toString(); 
	}

}
