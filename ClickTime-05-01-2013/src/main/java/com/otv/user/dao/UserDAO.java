package com.otv.user.dao;

import org.apache.log4j.Logger;

import com.otv.model.User;

/**
 * 
 * User DAO
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

public class UserDAO extends GenericDaoJpa<User, Long> implements IUserDAO {
	
	private Logger LOG = Logger.getLogger(this.getClass().getName());

	public UserDAO() {
		super();

	}

	public UserDAO(Class<User> entity) {
		super(entity);
	}

}
