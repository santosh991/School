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
	/**
	 * 
	 */
	public Position() {
		super();

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

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nonteachingstaff position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(", getTeacherUuid() =");
		builder.append(getTeacherUuid());
		builder.append(", getPosition() =");
		builder.append(getPosition());
		builder.append(", getSalary() =");
		builder.append(getSalary());
		builder.append("]"); 
		return builder.toString();
	}
}
