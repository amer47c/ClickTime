package com.otv.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otv.model.User;
import com.otv.user.dao.DaoException;
import com.otv.user.dao.IUserDAO;

/**
 * 
 * User Service
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class UserService implements IUserService {

	// UserDAO is injected...
	private IUserDAO userDAO;
	
	
	@Transactional(readOnly = false)
	public void addUser(User user) throws DaoException {
		getUserDAO().makePersistent(user);
	}

		
	@Transactional(readOnly = false)	
	public void updateUser(User user) throws DaoException{
		getUserDAO().update(user);
	}
	
		
	public User getUserById(long id)  throws DaoException{
		return getUserDAO().findById(id,false);
	}


	public List<User> getUsers()  throws DaoException {	
		return getUserDAO().findAll();
	}

	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}




}
