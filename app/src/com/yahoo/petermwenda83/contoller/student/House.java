/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;


/**
 * @author peter
 *
 */
public class House extends StudentSuper  {
	
	private String housename;
	/**
	 * 
	 */
	public House() {
		super();
		housename = "";
	}
	
	
	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return getStudentUuid();
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		setStudentUuid(studentUuid);
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
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(", housename =");
		builder.append("housename");
		builder.append("]");
		return builder.toString(); 
		}


}
