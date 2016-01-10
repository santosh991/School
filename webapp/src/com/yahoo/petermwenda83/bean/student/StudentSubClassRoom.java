
/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/
package com.yahoo.petermwenda83.bean.student;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * Student's Subject-Class Allocation 
 * 
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 * 
 */
public class StudentSubClassRoom extends StorableBean {
	
	private static final long serialVersionUID = 1L;
	private String studentUuid;
	private String subjectUuid;
	private String classRoomUuid;
	private String sysUser;
	private String allocationDate;

	public StudentSubClassRoom(){
		   super();
		   studentUuid = "";
		   subjectUuid = "";
		   classRoomUuid = "";
		   sysUser = "";
		   allocationDate = "";
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
	 * @return the subjectUuid
	 */
	public String getSubjectUuid() {
		return subjectUuid;
	}

	/**
	 * @param subjectUuid the subjectUuid to set
	 */
	public void setSubjectUuid(String subjectUuid) {
		this.subjectUuid = subjectUuid;
	}

	/**
	 * @return the classRoomUuid
	 */
	public String getClassRoomUuid() {
		return classRoomUuid;
	}

	/**
	 * @param classRoomUuid the classRoomUuid to set
	 */
	public void setClassRoomUuid(String classRoomUuid) {
		this.classRoomUuid = classRoomUuid;
	}

	/**
	 * @return the sysUser
	 */
	public String getSysUser() {
		return sysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	/**
	 * @return the allocationDate
	 */
	public String getAllocationDate() {
		return allocationDate;
	}

	/**
	 * @param allocationDate the allocationDate to set
	 */
	public void setAllocationDate(String allocationDate) {
		this.allocationDate = allocationDate;
	}

	@Override
	public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("Student's Subject-ClassRoom [ getUuid() =");
	builder.append(getUuid());
	builder.append(", studentUuid =");
	builder.append(studentUuid);
	builder.append(", subjectUuid =");
	builder.append(subjectUuid);
	builder.append(", classRoomUuid =");
	builder.append(classRoomUuid);
	builder.append(", sysUser =");
	builder.append(sysUser);
	builder.append(", allocationDate =");
	builder.append(allocationDate);
	builder.append("]");
	return builder.toString(); 
	}
}
