package com.otv.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import com.otv.model.User;
import com.otv.user.dao.DaoException;
import com.otv.user.service.IUserService;

/**
 * 
 * User Managed Bean
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	
	List<User> userList;	
	private User user;
	
	public UserManagedBean(){
		user = new User();
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 * @throws DaoException 
	 */
	public String addUser() throws DaoException {
		try {			
			getUserService().addUser(user);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.user = new User();
	}
	
	public String success() {
		return SUCCESS;
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 * @throws DaoException 
	 */
	public List<User> getUserList() throws DaoException {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
	
	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param IUserService - User Service
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
		
}