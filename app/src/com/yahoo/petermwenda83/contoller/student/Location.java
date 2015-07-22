/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import com.yahoo.petermwenda83.contoller.AllBean;


/**
 * @author peter
 *
 */
public class Location extends AllBean {
	
	private String studentUuid;
	private String county;
	private String location;
	private String sublocation;
	
	public Location() {
		super();
		studentUuid = "";
		county = "";
		location = "";
		sublocation = "";
	}
	
	
	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return studentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
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


	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("Location");
	builder.append("[ id =");
	builder.append(getId()); 
	builder.append(", studentUuid =");
	builder.append("studentUuid");
	builder.append(", county =");
	builder.append("county");
	builder.append(", location =");
	builder.append("location");
	builder.append(", sublocation =");
	builder.append("sublocation");
	builder.append("]");
	return builder.toString(); 
	}
}
