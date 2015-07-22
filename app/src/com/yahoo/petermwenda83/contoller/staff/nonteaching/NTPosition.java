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
	private String	ntUuid ;
	private String position ;
	private double salary ;
	/**
	 * 
	 */
	public NTPosition() {
		super();
		ntUuid = "";
		position = "";
		salary = 0.0;
	}
	
	
	/**
	 * @return the ntUuid
	 */
	public String getNtUuid() {
		return ntUuid;
	}


	/**
	 * @param ntUuid the ntUuid to set
	 */
	public void setNtUuid(String ntUuid) {
		this.ntUuid = ntUuid;
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
	public double getSalary() {
		return salary;
	}


	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nonteachingstaff position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", ntUuid =");
		builder.append(ntUuid);
		builder.append(", position =");
		builder.append(position);
		builder.append(", salary =");
		builder.append(salary);
		builder.append("]"); 
		return builder.toString();
	}

}
