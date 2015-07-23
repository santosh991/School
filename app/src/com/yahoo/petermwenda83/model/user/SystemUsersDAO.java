/**
 * 
 */
package com.yahoo.petermwenda83.model.user;

import java.util.List;

import com.yahoo.petermwenda83.contoller.user.User;

/**
 * @author peter
 *
 */
public interface SystemUsersDAO {
	/**
	 * 
	 * @param Uuid
	 * @return
	 */
	public User getUser(String Uuid);
	
	/**
	 * @param user
	 * @param Uuid
	 * @return
	 */
	public boolean editUser(User user,String Uuid);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean putUser(User user);
	  /**
	   * 
	   * @param user
	   * @return
	   */
	public boolean deleteUser(User user);
	  /**
	   * 
	   * @return
	   */
	public List<User> getAllUsers();

}
