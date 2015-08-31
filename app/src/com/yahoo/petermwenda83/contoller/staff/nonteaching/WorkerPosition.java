/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.contoller.staff.nonteaching;

import com.yahoo.petermwenda83.contoller.staff.Employees;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
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
