/**
 * 
 */
package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.student.StudentSuper;

/**
 * @author peter
 *
 */
public class StudentRelative extends StudentSuper{

	private String relativeName;
	private String relativePhone;
	private String nationalID;
	//private String studentUuid; 

	
	public StudentRelative() {
		super();
		relativeName = "";
		relativePhone = "";
		nationalID = "";
		//studentUuid = "";
		
	}

	
	/**
	 * @return the relativeName
	 */
	public String getRelativeName() {
		return relativeName;
	}


	/**
	 * @param relativeName the relativeName to set
	 */
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}


	/**
	 * @return the relativePhone
	 */
	public String getRelativePhone() {
		return relativePhone;
	}


	/**
	 * @param relativePhone the relativePhone to set
	 */
	public void setRelativePhone(String relativePhone) {
		this.relativePhone = relativePhone;
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



	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentRelative");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", relativeName = ");
		builder.append(relativeName);
		builder.append(", relativePhone =");
		builder.append(relativePhone);
		builder.append(", nationalID =");
		builder.append(nationalID);
		builder.append(",getStudentsUuid() =");
		builder.append(getStudentUuid());
		builder.append("]");
		return builder.toString(); 
		}
		
}
