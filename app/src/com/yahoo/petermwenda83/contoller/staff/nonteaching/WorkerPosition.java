/**
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.nonteaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;

/**
 * @author peter
 *
 */
public class WorkerPosition  extends Employees {
	
	/**
	 * 
	 */
	public WorkerPosition() {
		super();
		
	}
	
	
	/**
	 * @return the nTstaffUuid
	 */
	public String getWorkerUuid() {
		return getEmployeeUuid();
	}




	/**
	 * @param nTstaffUuid the nTstaffUuid to set
	 */
	public void setWorkerUuid(String workerUuid) {
		setEmployeeUuid(workerUuid); 
	}




	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Worker Position");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());
		builder.append(", getWorkerUuid() =");
		builder.append(getWorkerUuid());
		builder.append(", getPosition() =");
		builder.append(getPosition());
		builder.append(", getSalary() =");
		builder.append(getSalary());
		builder.append("]"); 
		return builder.toString();
	}

}
