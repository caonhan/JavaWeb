package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.Estimate;
import com.serp.model.Material;
import com.serp.model.ProcessingDocument;
import com.serp.model.Role;
import com.serp.model.User;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class User.
 * @see com.serp.dao.User
 * @author Hibernate Tools
 */
@Repository
@Transactional
public class UserDAO {

	private static final Log log = LogFactory.getLog(UserDAO.class);

/*	
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
*/
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void addUser(User user) {
        log.debug("adding user instance ");
        try {
        	sessionFactory.getCurrentSession().persist(user);
            log.debug("adding successful");
        } catch (RuntimeException re) {
            log.error("adding failed", re);
            throw re;
        }
    }
		
	public void deleteUser(User user) {
        log.debug("deleting user instance ");
        try {
        	sessionFactory.getCurrentSession().delete(user);
            log.debug("deleting successful");
        } catch (RuntimeException re) {
            log.error("deleting failed", re);
            throw re;
        }
    }
	
	public void updateUser(User user) {
        log.debug("updating user instance ");
        try {
        	sessionFactory.getCurrentSession().update(user);
            log.debug("updating successful");
        } catch (RuntimeException re) {
            log.error("updating failed", re);
            throw re;
        }
    }
	
	@SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        log.debug("getting all user");
        try {
            List<User> list = (List<User>)sessionFactory.getCurrentSession().createQuery("from User").list();
            log.debug("getting all successful");
            return list;
        } catch (RuntimeException re) {
            log.error("getting all failed", re);
            throw re;
        }

    }
		
	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"com.serp.model.User", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) sessionFactory
					.getCurrentSession().createCriteria("com.serp.model.User")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
