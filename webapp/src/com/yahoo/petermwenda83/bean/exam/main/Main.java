package com.yahoo.petermwenda83.bean.exam.main;

import com.yahoo.petermwenda83.bean.exam.ExamType;

public class Main extends ExamType {
  
	
	public Main() {
		super();
		
	}
	
	/**
	 * @return the mainname
	 */
	public String getMainname() {
		return getExamname();
	}

	/**
	 * @param mainname the mainname to set
	 */
	public void setMainname(String mainname) {
		setExamname(mainname);
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid() = ");
		builder.append(getUuid());
		builder.append(",getMainname()= ");
		builder.append(getMainname());
		builder.append("]");
		return builder.toString(); 
		}
}
