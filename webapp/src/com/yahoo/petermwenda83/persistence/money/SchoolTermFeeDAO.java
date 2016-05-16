package com.yahoo.petermwenda83.persistence.money;

import java.util.List;

import com.yahoo.petermwenda83.bean.money.TermFee;

public interface SchoolTermFeeDAO {
	
	/**
	 * 
	 * @param schoolAccountUuid
	 * @return
	 */
	public TermFee getTermFee(String schoolAccountUuid,String Term);
	/**
	 * 
	 * @param termFee
	 * @return
	 */
	public boolean putTermFee(TermFee termFee);
	/**
	 * 
	 * @param termFee
	 * @return
	 */
	public boolean updateTermFee(TermFee termFee); 
	/**
	 * 
	 * @param termFee
	 * @return
	 */
	public List<TermFee> getTermFeeList(String schoolAccountUuid); 

}
