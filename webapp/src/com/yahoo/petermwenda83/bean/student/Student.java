
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
package com.yahoo.petermwenda83.bean.student;

import java.util.Date;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * Has Student;s Basic details 
 * 
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 * 
 */
public class Student extends StorableBean implements Comparable<Student> {
	

		private static final long serialVersionUID = -7544635242162355411L;
		
		private String schoolAccountUuid;
		private String statusUuid;
		private String classRoomUuid;
		private String admno;
		private String firstname; // baptism name
		private String lastname;
		private String surname;  // family name 
		private String gender;		
		private String dOB;
		private String bcertno;
		private String county;
		private String sysUser;
		private Date admissionDate;
  
	
    public Student() {
		super();
		schoolAccountUuid = "";
		statusUuid = "";
		classRoomUuid = "";
		admno = "";
		firstname = "";
		lastname = "";
		surname = "";
		gender ="";
		dOB = "";
		bcertno = "";
		county ="";
		sysUser = "";
		admissionDate = new Date();
	
	}
	
	

	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return schoolAccountUuid;
	}



	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		this.schoolAccountUuid = schoolAccountUuid;
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
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return classRoomUuid;
	}



	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		this.classRoomUuid = classRoomUuid;
	}



	/**
	 * @return the admno
	 */
	public String getAdmno() {
		return admno;
	}



	/**
	 * @param admno the admno to set
	 */
	public void setAdmno(String admno) {
		this.admno = admno;
	}



	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}



	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}



	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}



	/**
	 * @return the dOB
	 */
	public String getdOB() {
		return dOB;
	}



	/**
	 * @param dOB the dOB to set
	 */
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}



	/**
	 * @return the bcertno
	 */
	public String getBcertno() {
		return bcertno;
	}



	/**
	 * @param bcertno the bcertno to set
	 */
	public void setBcertno(String bcertno) {
		this.bcertno = bcertno;
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
	 * @return the sysUser
	 */
	public String getSysUser() {
		return sysUser;
	}



	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}



	/**
	 * @return the admissionDate
	 */
	public Date getAdmissionDate() {
		return admissionDate;
	}



	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = new Date(admissionDate.getTime());
	}



	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Student [getUuid() =");
		builder.append(getUuid());
		builder.append(",schoolAccountUuid =");
		builder.append(schoolAccountUuid);
		builder.append(",statusUuid =");
		builder.append(statusUuid);		
		builder.append(",classRoomUuid =");
		builder.append(classRoomUuid);		
		builder.append(",admno =");
		builder.append(admno);	
		builder.append(",firstname =");
		builder.append(firstname);	
		builder.append(",lastname =");
		builder.append(lastname);
		builder.append(",surname =");
		builder.append(surname);
		builder.append(",gender =");
		builder.append(gender);
		builder.append(",dOB =");
		builder.append(dOB);
		builder.append(",bcertno =");
		builder.append(bcertno);
		builder.append(",county =");
		builder.append(county);
		builder.append(",sysUser =");
		builder.append(sysUser);
		builder.append(",admissionDate =");
		builder.append(admissionDate);
		builder.append("]");
		return builder.toString(); 
		}


	
	@Override
	public int compareTo(Student ss) {
		return getAdmno().compareTo(((Student) ss).getAdmno());
	}


}
