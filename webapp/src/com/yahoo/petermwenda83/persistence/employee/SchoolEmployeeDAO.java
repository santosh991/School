/**
 * 
 */
package com.yahoo.petermwenda83.persistence.employee;

import java.util.List;

import com.yahoo.petermwenda83.bean.employee.Employee;

/**
 * @author peter
 *
 */
public interface SchoolEmployeeDAO {
	/**
	 * 
	 * @param SchoolAccountUuid
	 * @return Employee for a school
	 */
	public Employee get(String SchoolAccountUuid);
	/**
	 * 
	 * @param employee
	 * @return Whether Employee was put successfully 
	 */
	public boolean put(Employee employee);
	/**
	 * 
	 * @param employee
	 * @return Whether Employee was updated successfully 
	 */
	public boolean update(Employee employee);
	/**
	 * 
	 * @param employee
	 * @return Whether Employee was deleted successfully 
	 */
	public boolean delete(Employee employee);
	/**
	 * 
	 * @return List of Employees for a school
	 */
	public List<Employee> getAllEmployees(String SchoolAccountUuid);

}
