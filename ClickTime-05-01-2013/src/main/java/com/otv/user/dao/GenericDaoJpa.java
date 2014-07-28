/**
 * 
 */
package com.otv.user.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import com.otv.user.dao.GenericDao;



public class GenericDaoJpa<T, ID extends Serializable> implements GenericDao <T, ID> {

	private Logger LOG = Logger.getLogger(this.getClass().getName());
	
    private Class<T> entityBeanType;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoJpa() {
        this.entityBeanType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericDaoJpa(Class<T> entity) {
    	entityBeanType = entity;
    }

    
    // If this DAO is wired in as a Seam component, Seam injects the right persistence context
    // if a method on this DAO is called. If the caller is a conversational stateful component,
    // the persistence context will be scoped to the conversation, not to the method call.
    // You can call this method and set the EntityManager manually, in an integration test.
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        if (em == null)
            throw new IllegalStateException("EntityManager has not been set on DAO before usage");
        return em;
    }

    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    public T findById(ID id, boolean lock)  throws DaoException {
    	T entity = null;
    	try {
            if (lock) {
                entity = getEntityManager().find(getEntityBeanType(), id);
                em.lock(entity, LockModeType.WRITE);
            } else {
                entity = getEntityManager().find(getEntityBeanType(), id);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll()  throws DaoException {
    	try {
        return getEntityManager().createQuery("from " + getEntityBeanType().getName()).getResultList();        
            
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }


    @SuppressWarnings("unchecked")
    public List<T> findLimited()  throws DaoException {
    	try {
        Query query =  getEntityManager().createQuery("from " + getEntityBeanType().getName() + " as e  order by  e.id desc ");  //       
        query.setMaxResults(50);
       
        return query.getResultList();
        
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }
    
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) throws DaoException {
    	try {
            // Using Hibernate, more difficult with EntityManager and EJB-QL
            org.hibernate.Criteria crit = ((org.hibernate.ejb.HibernateEntityManager)getEntityManager()).getSession().createCriteria(getEntityBeanType());
            org.hibernate.criterion.Example example =  org.hibernate.criterion.Example.create(exampleInstance);
            example.enableLike(MatchMode.START);
            //example.ignoreCase();
            //example.excludeZeroes();
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            crit.add(example);
            return crit.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }

    public T makePersistent(T entity) throws DaoException {
    	try {
    		getEntityManager().persist(entity);
    		return entity;
		} catch (Exception e) {
			
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }
    
    public void update(T entity) throws DaoException {
    	try {
    		getEntityManager().merge(entity);
		} catch (Exception e) {
	
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }

    public void makeTransient(T entity) throws DaoException {
    	try {
            getEntityManager().remove(entity);			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
    }

    public void flush() {
    	try {
            getEntityManager().flush();			
		} catch (Exception e) {
	
			LOG.error(e);
		}
    }

    public void clear() {
    	 try {
    		 getEntityManager().clear();			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(org.hibernate.criterion.Criterion... criterion) throws DaoException {    	
    	try {
            // Using Hibernate, more difficult with EntityManager and EJB-QL
            org.hibernate.Session session =
                ((org.hibernate.ejb.HibernateEntityManager)getEntityManager()).getSession();
        org.hibernate.Criteria crit
                = session.createCriteria(getEntityBeanType());
        for (org.hibernate.criterion.Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
		return new ArrayList<T>();
   }

    @SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) throws DaoException {
		try {
			String queryString = "from " + getEntityBeanType().getName() + " e where e." + propertyName + "  = :propertyValue   order by  e.id desc ";
			
			return getEntityManager().createQuery(queryString).setParameter("propertyValue", value).setMaxResults(200).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new DaoException(e);
		}
	}

    
}
