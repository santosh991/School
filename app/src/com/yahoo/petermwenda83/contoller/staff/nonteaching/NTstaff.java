/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.nonteaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class NTstaff extends Employees{
	
	private String NTstaffUuid;
	
	/**
	 * 
	 */
	public NTstaff() {
		super();
		
		NTstaffUuid = "";
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nonteaching staff");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", NTstaffUuid =");
		builder.append(NTstaffUuid);	
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
