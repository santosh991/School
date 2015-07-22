/**
 * 
 */
package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class StudentParent extends AllBean {
	private String studentUuid;
	private String fathername;
	private int fatherphone;
	private String fatheroccupation;
	private int fatherID;
	
	private String motherrname;
	private int motherphone;
	private String motheroccupation;
	private int motherID;
	
	public StudentParent() {
		super();
		 studentUuid = "";
		 fathername = "";
		 fatherID = 0;
		 fatherphone =0;
		 fatheroccupation ="";
		 motherrname ="";
		 motherID = 0;
		 motherphone = 0;
		 motheroccupation = "";
	}
	
	
	
	public String getFathername() {
		return fathername;
	}



	public void setFathername(String fathername) {
		this.fathername = fathername;
	}



	public int getFatherID() {
		return fatherID;
	}



	public void setFatherID(int fatherID) {
		this.fatherID = fatherID;
	}



	public int getFatherphone() {
		return fatherphone;
	}



	public void setFatherphone(int fatherphone) {
		this.fatherphone = fatherphone;
	}



	public String getFatheroccupation() {
		return fatheroccupation;
	}



	public void setFatheroccupation(String fatheroccupation) {
		this.fatheroccupation = fatheroccupation;
	}
	
	public String getMotherrname() {
		return motherrname;
	}



	public void setMotherrname(String motherrname) {
		this.motherrname = motherrname;
	}



	public int getMotherID() {
		return motherID;
	}



	public void setMotherID(int motherID) {
		this.motherID = motherID;
	}



	public int getMotherphone() {
		return motherphone;
	}



	public void setMotherphone(int motherphone) {
		this.motherphone = motherphone;
	}



	public String getMotheroccupation() {
		return motheroccupation;
	}



	public void setMotheroccupation(String motheroccupation) {
		this.motheroccupation = motheroccupation;
	}

	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return studentUuid;
	}



	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
	}



	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", studentUuid = ");
		builder.append("studentUuid");
		builder.append(", fathername = ");
		builder.append("fathername");
		builder.append(", fatherphone =");
		builder.append("fatherphone");
		builder.append(", fatheroccupation =");
		builder.append("fatheroccupation");
		builder.append(", fatherID =");
		builder.append("fatherID");
		builder.append(", motherrname = ");
		builder.append("motherrname");
		builder.append(", motherphone =");
		builder.append("motherphone");
		builder.append(", motheroccupation =");
		builder.append("motheroccupation");
		builder.append(", motherID =");
		builder.append("motherID");
		builder.append("]");
		return builder.toString(); 
		}
}
