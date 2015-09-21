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
package com.yahoo.petermwenda83.bean.student;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Location extends StudentSuper {

	private String county;
	private String location;
	private String sublocation;
	
	public Location() {
		super();
		county = "";
		location = "";
		sublocation = "";
	}
	
	

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}


	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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
	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("Location");
	builder.append("[ id =");
	builder.append(getId()); 
	builder.append(", getStudentUuid() =");
	builder.append(getStudentUuid());
	builder.append(", county =");
	builder.append(county);
	builder.append(", location =");
	builder.append(location);
	builder.append(", sublocation =");
	builder.append(sublocation);
	builder.append("]");
	return builder.toString(); 
	}
}
