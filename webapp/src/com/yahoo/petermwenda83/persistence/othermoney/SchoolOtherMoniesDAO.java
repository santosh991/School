package com.yahoo.petermwenda83.persistence.othermoney;

import java.util.List;

import com.yahoo.petermwenda83.bean.othermoney.OtherMonies;

public interface SchoolOtherMoniesDAO {
	/**
	 * 
	 * @param studentUuid
	 * @return
	 */
	public OtherMonies getOtherMonies(String studentUuid);
	/**
	 * 
	 * @param otherMonies
	 * @return
	 */
	public boolean hasAmount(String studentUuid,double amount);
	
	/**
	 * 
	 * @param otherMonies
	 * @return
	 */
	public boolean AddOtherMonies(OtherMonies otherMonies,double amount);
	/**
	 * 
	 * @param otherMonies
	 * @return
	 */
	public boolean deductOtherMonies(OtherMonies otherMonies,double amount);
	
	/**
	 *  
	 * @param otherMonies
	 * @return
	 */
	public boolean changeOtherMoniesStatus(OtherMonies otherMonies);
	
	/**
	 * 
	 * @return
	 */
	public List<OtherMonies> getOtherMoniesList();

}
