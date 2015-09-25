/**
 * 
 */
package com.yahoo.petermwenda83.bean.employee;

import java.util.Date;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter
 *
 */
public class TeacherSubject extends AllBean {
	
	private String EmployeeUuid;
	private String SubjectUuid;
	private String ClassRoomUuid;
	private String SysUser;
	private Date AssignDate;
	/**
	 * 
	 */
	public TeacherSubject() {
		super();
		EmployeeUuid ="";
		SubjectUuid ="";
		ClassRoomUuid ="";
		SysUser ="";
		AssignDate = new Date(); 
	}
	
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
	 * @return the subjectUuid
	 */
	public String getSubjectUuid() {
		return SubjectUuid;
	}

	/**
	 * @param subjectUuid the subjectUuid to set
	 */
	public void setSubjectUuid(String subjectUuid) {
		SubjectUuid = subjectUuid;
	}

	/**
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return ClassRoomUuid;
	}

	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		ClassRoomUuid = classRoomUuid;
	}

	/**
	 * @return the sysUser
	 */
	public String getSysUser() {
		return SysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(String sysUser) {
		SysUser = sysUser;
	}

	/**
	 * @return the assignDate
	 */
	public Date getAssignDate() {
		return AssignDate;
	}

	/**
	 * @param assignDate the assignDate to set
	 */
	public void setAssignDate(Date assignDate) {
		AssignDate = assignDate;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Result");
		builder.append("[getUuid()=");
		builder.append(getUuid()); 
		builder.append(",EmployeeUuid=");
		builder.append(EmployeeUuid);
		builder.append(",SubjectUuid=");
		builder.append(SubjectUuid);
		builder.append(", ClassRoomUuid=");
		builder.append(ClassRoomUuid);
		builder.append(", SysUser=");
		builder.append(SysUser);
		builder.append(",AssignDate=");
		builder.append(AssignDate);
		builder.append("]");
		return builder.toString(); 
		}
}
