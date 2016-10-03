package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Customer;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Customer.
 * @see com.serp.dao.Customer
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class CustomerHome {

	private static final Log log = LogFactory.getLog(CustomerHome.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/** This function used to list all customer
	 *  input: no @para
	 *  output: List<Customer>
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomer() {
        log.debug("getting all customer");
        try {
            List<Customer> results = (List<Customer>)sessionFactory.getCurrentSession().createQuery("from Customer").list();
            log.debug("getting all Customer successfull, result size: "
                    + results.size());
            
            return results;
        } catch (RuntimeException re) {
            log.error("get all Customer failed", re);
            throw re;
        }

    }
	/**
	 * This function use to add new Customer
	 * 
	 * Exception:throw RuntimeException if persist failed
	 * @param transientInstance
	 */
	
	public void persist(Customer transientInstance) {
		log.debug("persisting Customer instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * This function use to 
	 * @param customer
	 * 
	 * s
	 * @param transientInstance
	 */
	public void attachDirty(Customer instance) {
		log.debug("attaching dirty Customer instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * This function use to delete a customer
	 * Exception:throw RuntimeException if delete failed
	 * @param persistentInstance
	 */
	
	public void delete(Customer persistentInstance) {
		log.debug("deleting Customer instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * This function use to find an customer by Id
	 * Input: id
	 * Output: an Customer
	 * Exception: throw RuntimeException if get failed
	 * @param transientInstance
	 */
	public Customer findById(int id) {
		log.debug("getting Customer instance with id: " + id);
		try {
			Customer instance = (Customer) sessionFactory.getCurrentSession()
					.get("com.serp.model.Customer", id);//
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
}
