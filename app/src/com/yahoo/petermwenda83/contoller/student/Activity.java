/**
 * 
 */
package com.yahoo.petermwenda83.contoller.student;


/**
 * @author peter
 *
 */
public class Activity extends Student {
	private String  activity;
	/**
	 * 
	 */
	public Activity() {
		super();
		activity = "";
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
		builder.append(", getStudentUuid() =");
		builder.append(getStudentUuid());
		builder.append(", activity =");
		builder.append("activity");
		builder.append("]");
		return builder.toString(); 
		}

}
