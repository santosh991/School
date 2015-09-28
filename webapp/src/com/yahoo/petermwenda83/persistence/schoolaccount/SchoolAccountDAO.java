/**
 * 
 */
package com.yahoo.petermwenda83.persistence.schoolaccount;

import java.util.List;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;

/**
 * @author peter
 *
 */
public interface SchoolAccountDAO {
	/**
	 * 
	 * @param SchoolName
	 * @return the SchoolName
	 */
	public SchoolAccount getSchoolBySchoolName(String SchoolName);
	/**
	 * 
	 * @param Username
	 * @return the SchoolName
	 */
	public SchoolAccount getSchoolByUsername(String Username);
	/**
	 * 
	 * @param school
	 * @return whether School has been added successfully
	 */
    public boolean put(SchoolAccount school);
    /**
     * 
     * @param school
     * @return whether School has been updated successfully
     */
    public boolean update(SchoolAccount school);
    /**
     * 
     * @param school
     * @return whether School has been deleted successfully
     */
    public boolean delete(SchoolAccount school);
    /**
     * 
     * @return List of all schools 
     */
    public List<SchoolAccount> getAllSchools();
    
    

}
