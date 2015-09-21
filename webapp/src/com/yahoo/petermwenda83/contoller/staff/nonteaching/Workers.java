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
public class Workers extends Employees{
	private String sublocation ;
	
	/**
	 * 
	 */
	public Workers() {
		super();
		sublocation ="";
		
	}

/**
 * @return the sublocation
 */
public String getSublocation() {
	return sublocation;
}
/**
 * @param sublocation the sublocation to set
 */
public void setSublocation(String sublocation) {
	this.sublocation = sublocation;
}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workers");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(",getUuid() =");
		builder.append(getUuid());	
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
		builder.append(", getLocation() =");
		builder.append(getLocation());
		builder.append(", sublocation =");
		builder.append(sublocation);
		builder.append("]"); 
		return builder.toString();
	}


}
