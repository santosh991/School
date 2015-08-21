/**
 * 
 */
package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.student.StudentSuper;

/**
 * @author peter
 *
 */
public class StudentParent extends StudentSuper {
	private String relationship;
	private String fathername;
	private String fatherphone;
	private String fatheroccupation;
	private String fatherID;
	private String fatherEmail;
	
	private String mothername;
	private String motherphone;
	private String motheroccupation;
	private String motherID;
	private String motherEmail;
	
	public StudentParent() {
		super();
		relationship = "";
		 fathername = "";
		 fatherID = "";
		 fatherphone ="";
		 fatheroccupation ="";
		 fatherEmail ="";
		 mothername ="";
		 motherID = "";
		 motherphone = "";
		 motheroccupation = "";
		 motherEmail = "";
	}
	
	
	
	public String getFathername() {
		return fathername;
	}



	public void setFathername(String fathername) {
		this.fathername = fathername;
	}



	public String getFatherID() {
		return fatherID;
	}



	public void setFatherID(String fatherID) {
		this.fatherID = fatherID;
	}



	public String getFatherphone() {
		return fatherphone;
	}



	public void setFatherphone(String fatherphone) {
		this.fatherphone = fatherphone;
	}



	public String getFatheroccupation() {
		return fatheroccupation;
	}



	public void setFatheroccupation(String fatheroccupation) {
		this.fatheroccupation = fatheroccupation;
	}
	
	public String getMothername() {
		return mothername;
	}



	public void setMothername(String mothername) {
		this.mothername = mothername;
	}



	public String getMotherID() {
		return motherID;
	}



	public void setMotherID(String motherID) {
		this.motherID = motherID;
	}



	public String getMotherphone() {
		return motherphone;
	}



	public void setMotherphone(String motherphone) {
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
	 * @return the fatherEmail
	 */
	public String getFatherEmail() {
		return fatherEmail;
	}



	/**
	 * @param fatherEmail the fatherEmail to set
	 */
	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}



	/**
	 * @return the motherEmail
	 */
	public String getMotherEmail() {
		return motherEmail;
	}



	/**
	 * @param motherEmail the motherEmail to set
	 */
	public void setMotherEmail(String motherEmail) {
		this.motherEmail = motherEmail;
	}



	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}



	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}



	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getStudentsUuid() = ");
		builder.append(getStudentUuid());
		builder.append(", fathername = ");
		builder.append(fathername);
		builder.append(", fatherphone =");
		builder.append(fatherphone);
		builder.append(", fatheroccupation =");
		builder.append(fatheroccupation);
		builder.append(", fatherEmail =");
		builder.append(fatherEmail);
		builder.append(", fatherID =");
		builder.append(fatherID);
		builder.append(", mothername = ");
		builder.append(mothername);
		builder.append(", motherphone =");
		builder.append(motherphone);
		builder.append(", motheroccupation =");
		builder.append(motheroccupation);
		builder.append(", motherEmail =");
		builder.append(motherEmail);
		builder.append(", motherID =");
		builder.append(motherID);
		builder.append(", relationship =");
		builder.append(relationship);
		builder.append("]");
		return builder.toString(); 
		}
}
