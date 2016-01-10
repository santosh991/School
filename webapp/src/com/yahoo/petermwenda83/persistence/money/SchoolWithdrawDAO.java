/**
 * 
 */
package com.yahoo.petermwenda83.persistence.money;

    import java.util.List;

import com.yahoo.petermwenda83.bean.money.Withdraw;

/**
 * @author peter
 *
 */
   public interface SchoolWithdrawDAO {
	    /**
	     * 
	     * @param withdraw
	     * @param amount
	     * @return
	     */
	    public boolean hasBalance(Withdraw withdraw,Double amount);
		 /**
		  * 
		  * @param withdraw
		  * @param amount
		  * @return
		  */
		public boolean deductBalance(Withdraw withdraw,Double amount);
	     /**
	      * 
	      * @return
	      */
	    public List<Withdraw> getWithdrawList();

}
