/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.dao.user;

import java.util.List;

import com.yahoo.petermwenda83.bean.users.User;


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
