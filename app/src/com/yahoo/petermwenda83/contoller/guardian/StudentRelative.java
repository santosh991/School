/**
 * 
 */
package com.yahoo.petermwenda83.contoller.guardian;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class StudentRelative extends AllBean{

	private String relativeName;
	private int relativePhone;
	private int nationalID;
	private String studentUuid; 

	
	public StudentRelative() {
		super();
		relativeName = "";
		relativePhone = 0;
		nationalID = 0;
		studentUuid = "";
		
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
	public int getRelativePhone() {
		return relativePhone;
	}


	/**
	 * @param relativePhone the relativePhone to set
	 */
	public void setRelativePhone(int relativePhone) {
		this.relativePhone = relativePhone;
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
		builder.append("StudentRelative");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", relativeName = ");
		builder.append("relativeName");
		builder.append(", relativePhone =");
		builder.append("relativePhone");
		builder.append(", nationalID =");
		builder.append("nationalID");
		builder.append(", studentUuid =");
		builder.append("studentUuid");
		builder.append("]");
		return builder.toString(); 
		}
		
}
