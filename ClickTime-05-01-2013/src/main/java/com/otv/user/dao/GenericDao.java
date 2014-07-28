/**
 * 
 */
package com.otv.user.dao;

import java.io.Serializable;
import java.util.List;

/**
 * An interface shared by all business data access objects. * All CRUD (create,
 * read, update, delete) basic data access operations are isolated in this
 * interface and shared accross all DAO implementations. The current design is
 * for a state-management oriented persistence layer (for example, there is no
 * UDPATE statement function) that provides automatic transactional dirty
 * checking of business objects in persistent state.
 * 
 */

public interface GenericDao<T, ID extends Serializable> {

	public T findById(ID id, boolean lock)  throws DaoException;

	public List<T> findAll()  throws DaoException;
	
	public List<T> findLimited()  throws DaoException;

	public List<T> findByExample(T exampleInstance, String... excludeProperty) throws DaoException;

	public T makePersistent(T entity) throws DaoException;

	public void update(T entity) throws DaoException;

	public void makeTransient(T entity) throws DaoException;
	
	public List<T> findByProperty(String propertyName, Object value) throws DaoException;
	
	
	
}