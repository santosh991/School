/**
 * 
 */
package com.yahoo.petermwenda83.contoller.exam.cat;

import com.yahoo.petermwenda83.contoller.exam.ExamType;

/**
 * @author peter
 *
 */
public class CatDetails extends ExamType {

	/**
	 * 
	 */
	public CatDetails() {
		super();
	}

	/**
	 * @return the catuuid
	 */
	public String getCatuuid() {
		return getExamuuid();
	}

	/**
	 * @param catuuid the catuuid to set
	 */
	public void setCatuuid(String catuuid) {
		setExamuuid(catuuid);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid() = ");
		builder.append(getUuid());
		builder.append(",getCatuuid() = ");
		builder.append(getCatuuid());
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
