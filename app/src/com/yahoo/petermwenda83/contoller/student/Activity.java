/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;

import com.yahoo.petermwenda83.contoller.AllBean;

/**
 * @author peter
 *
 */
public class Activity extends AllBean  {
	private String studentUuid;
	private String  activity;
	/**
	 * 
	 */
	public Activity() {
		super();
		studentUuid = "";
		activity = "";
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
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}


	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}


	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Activity");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", studentUuid =");
		builder.append("studentUuid");
		builder.append(", activity =");
		builder.append("activity");
		builder.append("]");
		return builder.toString(); 
		}

}
