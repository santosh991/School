/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.nonteaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class NTPosition  extends Employees {
	
	/**
	 * 
	 */
	public NTPosition() {
		super();
		
	}
	
	
	/**
	 * @return the nTstaffUuid
	 */
	public String getNTstaffUuid() {
		return getEmployeeUuid();
	}




	/**
	 * @param nTstaffUuid the nTstaffUuid to set
	 */
	public void setNTstaffUuid(String nTstaffUuid) {
		setEmployeeUuid(nTstaffUuid);
	}




	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nonteachingstaff position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(", getNTstaffUuid() =");
		builder.append(getNTstaffUuid());
		builder.append(", getPosition() =");
		builder.append(getPosition());
		builder.append(", getSalary() =");
		builder.append(getSalary());
		builder.append("]"); 
		return builder.toString();
	}

}
