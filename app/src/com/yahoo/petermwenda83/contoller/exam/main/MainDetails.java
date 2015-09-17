/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam.main;

import com.yahoo.petermwenda83.contoller.exam.ExamType;

/**
 * @author peter
 *
 */
public class MainDetails extends ExamType{
	

	/**
	 * 
	 */
	public MainDetails() {
		super();
	}

	/**
	 * @return the mainuuid
	 */
	public String getMainuuid() {
		return getExamuuid();
	}

	/**
	 * @param mainuuid the mainuuid to set
	 */
	public void setMainuuid(String mainuuid) {
		setExamuuid(mainuuid);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid() = ");
		builder.append(getUuid());
		builder.append(",getMainuuid() = ");
		builder.append(getMainuuid());
		builder.append(",getRoomnameuuid() = ");
		builder.append(getRoomnameuuid());
		builder.append(",getSubjectuuid() = ");
		builder.append(getSubjectuuid());
		builder.append(",getTerm() = ");
		builder.append(getTerm());
		builder.append(",getYear() = ");
		builder.append(getYear());
		builder.append(",getOutof() = ");
		builder.append(getOutof());
		builder.append("]");
		return builder.toString(); 
		}

}
