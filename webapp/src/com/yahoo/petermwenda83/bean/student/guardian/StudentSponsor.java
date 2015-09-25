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
package com.yahoo.petermwenda83.bean.student.guardian;

import com.yahoo.petermwenda83.bean.student.StudentSuper;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class StudentSponsor extends StudentSuper {
	
	private String name;
	private String phone;
	private String NationalID;
	
	private String occupation;
	private String county;
	private String country;
	public StudentSponsor(){
		super();
		 name = "";
		 NationalID = "";
		 phone = "";
		 occupation = "";
		 county = "";
		 country = "";
	}
	
	


	/**
	 * @return the studentUuid
	 */
	public String getStudentsUuid() {
		return getStudentUuid();
	}



	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentsUuid(String studentUuid) {
		setStudentUuid(studentUuid);
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
		return NationalID;
	}




	/**
	 * @param nationalID the nationalID to set
	 */
	public void setNationalID(String nationalID) {
		NationalID = nationalID;
	}




	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}




	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}




	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}




	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentSponsor");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getStudentsUuid() =");
		builder.append(getStudentUuid());
		builder.append(",name = ");
		builder.append(name);
		builder.append(", NationalID =");
		builder.append(NationalID);
		builder.append(",phone =");
		builder.append(phone);
		builder.append(", occupation =");
		builder.append(occupation);
		builder.append(", county =");
		builder.append(county);
		builder.append(", country =");
		builder.append(country);
		builder.append("]");
		return builder.toString(); 
		} 
		

	
}
