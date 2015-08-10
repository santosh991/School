/**
 * 
 */
package com.yahoo.petermwenda83.model.user;

import java.util.List;

import com.yahoo.petermwenda83.contoller.users.User;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public interface SystemUsersDAO {
	/**
	 * 
	 * @param Uuid, user uuid
	 * @return the Uuid
	 */
	public User getUser(String Uuid);
	 
	/**
	 * 
	 * @param user, the user
	 * @return the user
	 */
	public User getUserName(User user); 
	
	/**
	 * @param user, the user
	 * @param Uuid, user Uuid
	 * @return user
	 */
	public boolean editUser(User user,String Uuid);
	/**
	 * 
	 * @param user, the user
	 * @return user
	 */
	public boolean putUser(User user);
	  /**
	   * 
	   * @param  user, the user
	   * @return user
	   */
	public boolean deleteUser(User user);
	  /**
	   * 
	   * @return get AllUsers
	   */
	public List<User> getAllUsers();

}
