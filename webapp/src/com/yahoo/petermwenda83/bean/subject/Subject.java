
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
package com.yahoo.petermwenda83.bean.subject;

import java.util.Date;

import com.yahoo.petermwenda83.bean.StorableBean;

/**
 * A subject in a SchoolAccount
 * 
 *  @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Subject extends StorableBean {
	
	  private static final long serialVersionUID = 1L;
	  private String SchoolAccountUuid;
	  private String SubjectCode;
	  private String SubjectName;
	  private String SubjectCategory;
	  private String SysUser;
	  private Date RegDate = new Date();

	/**
	 * 
	 */
	public Subject() {
		super();
		SchoolAccountUuid = "";
		SubjectCode = "";
		SubjectName = "";
		SubjectCategory = "";
		SysUser = "";
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
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return SubjectCode;
	}

	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		SubjectCode = subjectCode;
	}

	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return SubjectName;
	}

	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	/**
	 * @return the subjectCategory
	 */
	public String getSubjectCategory() {
		return SubjectCategory;
	}

	/**
	 * @param subjectCategory the subjectCategory to set
	 */
	public void setSubjectCategory(String subjectCategory) {
		SubjectCategory = subjectCategory;
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
	 * @return the regDate
	 */
	public Date getRegDate() {
		return RegDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("[ Subject, ");
		builder.append("getUuid()=");
		builder.append(getUuid()); 
		builder.append(",SchoolAccountUuid=");
		builder.append(SchoolAccountUuid);
		builder.append(",SubjectName=");
		builder.append(SubjectName);
		builder.append(",SubjectCode=");
		builder.append(SubjectCode);
		builder.append(", SubjectCategory=");
		builder.append(SubjectCategory);
		builder.append(", SysUser=");
		builder.append(SysUser);
		builder.append(",RegDate=");
		builder.append(RegDate);
		builder.append("]");
		return builder.toString(); 
		}

}
