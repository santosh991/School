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
	
	
	/**
	 * 
	 */
	public Teacher() {
		super();
		
		
	}
	
	

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Teacher");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(", getTeacherNumber() =");
		builder.append(getTeacherNumber());
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
		builder.append(", getNhifno() =");
		builder.append(getNhifno());		
		builder.append(", getNssfno() =");
		builder.append(getNssfno());
		builder.append(", getCounty() =");
		builder.append(getCounty());
		builder.append(", getLocation() =");
		builder.append(getLocation());
		builder.append(", getDOB() =");
		builder.append(getDOB());		
		builder.append("]");
		return builder.toString(); 
		}

}
