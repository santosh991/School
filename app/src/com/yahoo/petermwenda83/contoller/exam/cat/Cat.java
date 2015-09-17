package com.yahoo.petermwenda83.contoller.exam.cat;

import com.yahoo.petermwenda83.contoller.exam.ExamType;

public class Cat extends ExamType {
	
	
	public Cat() {
		super();
		
	}

	/**
	 * @return the catname
	 */
	public String getCatname() {
		return getExamname();
	}

	/**
	 * @param catname the catname to set
	 */
	public void setCatname(String catname) {
		setExamname(catname);
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentParent");
		builder.append("[ id =");
		builder.append(getId()); 
		builder.append(", getUuid() = ");
		builder.append(getUuid());
		builder.append(",getCatname() = ");
		builder.append(getCatname());
		builder.append("]");
		return builder.toString(); 
		}
}
