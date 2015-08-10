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
	
	private String position ;
	private String salary ;

	
	/**
	 * 
	 */
	public NTPosition() {
		super();
		position = "";
		salary = "";
		
	}
	
	
	

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}


	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
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
		builder.append(", getNTstaffUuid() =");
		builder.append(getNTstaffUuid());
		builder.append(", position =");
		builder.append(position);
		builder.append(", salary =");
		builder.append(salary);
		builder.append("]"); 
		return builder.toString();
	}

}
