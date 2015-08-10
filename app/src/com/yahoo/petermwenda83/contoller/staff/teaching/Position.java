/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.teaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class Position  extends Employees {
	private String position ;
	private double salary ;
	/**
	 * 
	 */
	public Position() {
		super();
		position = "";
		salary = 0.0;
	}

	

	/**
	 * @return the teacherUuid
	 */
	public String getTeacherUuid() {
		return getEmployeeUuid();
	}



	/**
	 * @param teacherUuid the teacherUuid to set
	 */
	public void setTeacherUuid(String teacherUuid) {
		setEmployeeUuid(teacherUuid);
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
		builder.append(", getTeacherUuid() =");
		builder.append(getTeacherUuid());
		builder.append(", position =");
		builder.append(position);
		builder.append(", salary =");
		builder.append(salary);
		builder.append("]"); 
		return builder.toString();
	}
}
