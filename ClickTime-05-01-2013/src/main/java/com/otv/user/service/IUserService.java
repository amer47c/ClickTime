package com.otv.user.service;

import java.util.List;

import com.otv.model.User;
import com.otv.user.dao.DaoException;

/**
 * 
 * User Service Interface
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IUserService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUser(User user)  throws DaoException;
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateUser(User user)  throws DaoException;

	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserById(long id)  throws DaoException;
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<User> getUsers()  throws DaoException;
}
