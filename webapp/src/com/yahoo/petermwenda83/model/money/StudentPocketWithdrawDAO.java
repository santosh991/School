/**
 * 
 */
package com.yahoo.petermwenda83.model.money;

    import java.util.List;

import com.yahoo.petermwenda83.contoller.money.pocket.Withdraw;

/**
 * @author peter
 *
 */
   public interface StudentPocketWithdrawDAO {
	/**
	 * 
	 * @param StudentUuid
	 * @return
	 */
	public Withdraw get(String StudentUuid);
	   /**
	    * 
	    * @param pMoney
	    * @return
	    */
	public boolean put(Withdraw withdraw); 
	   /**
	    * 
	    * @return
	    */
	public List<Withdraw> getWithdraw(Withdraw withdraw);

}
