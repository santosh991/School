/**
 * 
 */
package com.yahoo.petermwenda83.server.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author peter
 *
 */
 
public class SessionStatistics implements Serializable {
	
	private int allIncomingCount;
	
	//They are used to keep count against Month
    private final Map<String, Integer> accountCount;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6516924608718437954L;
   
	 public SessionStatistics() {
		 allIncomingCount =0;
		 accountCount = new HashMap<>();
	 }

	/**
	 * @return the allIncomingCount
	 */
	public int getAllIncomingCount() {
		return allIncomingCount;
	}

	/**
	 * @param allIncomingCount the allIncomingCount to set
	 */
	public void setAllIncomingCount(int allIncomingCount) {
		this.allIncomingCount = allIncomingCount;
	}

	/**
	 * @return the accountCount
	 */
	public Map<String, Integer> getAccountCount() {
		return accountCount;
	}
   
    

}

 