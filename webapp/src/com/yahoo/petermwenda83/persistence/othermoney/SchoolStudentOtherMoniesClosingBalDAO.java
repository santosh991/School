package com.yahoo.petermwenda83.persistence.othermoney;

import java.util.List;

import com.yahoo.petermwenda83.bean.othermoney.StudentOtherMoniesClosingBal;

public interface SchoolStudentOtherMoniesClosingBalDAO {
	/**
	 * 
	 * @param studentUuid
	 * @return
	 */
	public StudentOtherMoniesClosingBal getStudentOtherMoniesClosingBal(String studentUuid,String OtherstypeUuid);
	/**
	 * 
	 * @param studentOtherMoniesClosingBal
	 * @return
	 */
	public boolean putStudentOtherMoniesClosingBal(StudentOtherMoniesClosingBal studentOtherMoniesClosingBal);
	/**
	 * 
	 * @param studentOtherMoniesClosingBal
	 * @return
	 */
	public boolean updateStudentOtherMoniesClosingBal(StudentOtherMoniesClosingBal studentOtherMoniesClosingBal);
	/**
	 * 
	 * @return
	 */
	public List<StudentOtherMoniesClosingBal> getStudentOtherMoniesClosingBalList();
	
	

}
