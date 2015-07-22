/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.teaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class Teacher  extends Employees {
	
	private String teacherNumber;
	private String	teacherUuid;
	
	/**
	 * 
	 */
	public Teacher() {
		super();
		teacherNumber = "";
		teacherUuid = "";
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Teacher");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", teacherNumber =");
		builder.append(teacherNumber);
		builder.append(", teacherUuid =");
		builder.append(teacherUuid);
		builder.append(", getFirstName() =");
		builder.append(getFirstName());		
		builder.append(", getLastName() =");
		builder.append(getLastName());	
		builder.append(", getSurname() =");
		builder.append(getSurname());		
		builder.append(", getPhone() =");
		builder.append(getPhone());		
		builder.append(", getNationalID() =");
		builder.append(getNationalID());		
		builder.append(", getCounty() =");
		builder.append(getCounty());		
		builder.append(", getDOB() =");
		builder.append(getDOB());		
		builder.append(", getNhifno() =");
		builder.append(getNhifno());		
		builder.append(", getNssfno() =");
		builder.append(getNssfno());
		builder.append("]");
		return builder.toString(); 
		}

}
