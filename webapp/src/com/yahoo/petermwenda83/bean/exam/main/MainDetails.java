/**
 * 
 */
package com.yahoo.petermwenda83.bean.exam.main;

import com.yahoo.petermwenda83.bean.exam.ExamType;


/**
 * @author peter
 *
 */
public class MainDetails extends ExamType implements Comparable<MainDetails>{
	

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
	public int compareTo(MainDetails o) {
		// TODO Auto-generated method stub  
		//return name.compareTo(((Contact) c).getName());
		return 0;
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
		builder.append(",getSubjectUuid() = ");
		builder.append(getSubjectUuid());
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
