/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class House extends AllBean  {
	
	private String studentUuid;
	private String housename;
	/**
	 * 
	 */
	public House() {
		super();
		studentUuid = "";
		housename = "";
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
	 * @return the housename
	 */
	public String getHousename() {
		return housename;
	}


	/**
	 * @param housename the housename to set
	 */
	public void setHousename(String housename) {
		this.housename = housename;
	}


	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("House");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", studentUuid =");
		builder.append("studentUuid");
		builder.append(", housename =");
		builder.append("housename");
		builder.append("]");
		return builder.toString(); 
		}


}
