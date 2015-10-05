/**
 * 
 */
package com.yahoo.petermwenda83.persistence.employee;

import java.util.List;

import com.yahoo.petermwenda83.bean.employee.EmployeeDetail;

/**
 * @author peter
 *
 */
public interface SchoolEmployeeDetail {
	/**
	 * 
	 * @param EmployeeUuid
	 * @return an Employee
	 */
	public EmployeeDetail get(String EmployeeUuid);
	/**
	 * 
	 * @param employeeDetail
	 * @return Whether Employee was put successfully 
	 */
	public boolean put(EmployeeDetail employeeDetail);
	/**
	 * 
	 * @param employeeDetail
	 * @return Whether Employee was updated successfully 
	 */
	public boolean update(EmployeeDetail employeeDetail);
	/**
	 * 
	 * @param employeeDetail
	 * @return Whether Employee was deleted successfully 
	 */
	public boolean delete(EmployeeDetail employeeDetail);
	/**
	 * 
	 * @return List of all Employees Details
	 */
	public List<EmployeeDetail> getAllEmployeeDetail();

}
