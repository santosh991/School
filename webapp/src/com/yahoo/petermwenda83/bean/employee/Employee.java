/**
 * 
 */
package com.yahoo.petermwenda83.bean.employee;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter
 *
 */
public class Employee extends AllBean {
	
	private String SchoolAccountUuid;
	private String Category; 
	private String Position; 
	private String EmployeeUuid;

	/**
	 * @return the employeeUuid
	 */
	public String getEmployeeUuid() {
		return EmployeeUuid;
	}

	/**
	 * @param employeeUuid the employeeUuid to set
	 */
	public void setEmployeeUuid(String employeeUuid) {
		EmployeeUuid = employeeUuid;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
		SchoolAccountUuid ="";
		Category="";
		Position="";
	}
	
	/**
	 * @return the schoolAccountUuid
	 */
	public String getSchoolAccountUuid() {
		return SchoolAccountUuid;
	}

	/**
	 * @param schoolAccountUuid the schoolAccountUuid to set
	 */
	public void setSchoolAccountUuid(String schoolAccountUuid) {
		SchoolAccountUuid = schoolAccountUuid;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return Category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		Category = category;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return Position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		Position = position;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [ getUuid() =");
		builder.append(getUuid());
		builder.append(", Category =");
		builder.append(Category);
		builder.append(", Position =");
		builder.append(Position);
		builder.append(", SchoolAccountUuid =");
		builder.append(SchoolAccountUuid);
		builder.append("]");
		return builder.toString(); 
		}

}
