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
	private double salary ;
	private String NTstaffUuid ;
	
	/**
	 * 
	 */
	public NTPosition() {
		super();
		position = "";
		salary = 0.0;
		NTstaffUuid = "";
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
	


	/**
	 * @return the nTstaffUuid
	 */
	public String getNTstaffUuid() {
		return NTstaffUuid;
	}




	/**
	 * @param nTstaffUuid the nTstaffUuid to set
	 */
	public void setNTstaffUuid(String nTstaffUuid) {
		NTstaffUuid = nTstaffUuid;
	}




	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nonteachingstaff position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", NTstaffUuid =");
		builder.append(NTstaffUuid);
		builder.append(", position =");
		builder.append(position);
		builder.append(", salary =");
		builder.append(salary);
		builder.append("]"); 
		return builder.toString();
	}

}
