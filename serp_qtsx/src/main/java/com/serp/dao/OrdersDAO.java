package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.Orders;

/**
 * Home object for domain model class Orders.
 * @see com.serp.dao.Orders
 * @author Hibernate Tools
 */
@Repository
@Transactional
public class OrdersDAO {

	private static final Log log = LogFactory.getLog(OrdersDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * This function use to list all orders
	 * Output: list<order>
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public List<Orders> getAllOrder() {
        log.debug("getting all order");
        try {
            List<Orders> results = (List<Orders>)sessionFactory.getCurrentSession().createQuery("from Orders").list();
            log.debug("getting all order successfull, result size: "
                    + results.size());
            
            return results;
        } catch (RuntimeException re) {
            log.error("get all order failed", re);
            throw re;
        }

    }
	
	/**
	 * This function use to add new order
	 * Output: an order
	 * Exception:throw RuntimeException if persist failed
	 * @param transientInstance
	 */
	public void persist(Orders transientInstance) {
		log.debug("persisting Orders instance");
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
	 * Input:
	 * Output:
	 * Exception:
	 * @param transientInstance
	 */
	public void attachDirty(Orders instance) {
		log.debug("attaching dirty Orders instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * This function use to delete an order
	 * Exception:throw RuntimeException if delete failed
	 * @param persistentInstance
	 */
	public void delete(Orders persistentInstance) {
		log.debug("deleting Orders instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * This function use to find an order by Id
	 * Input: id
	 * Output: an order
	 * Exception: throw RuntimeException if get failed
	 * @param transientInstance
	 */
	public Orders findById(java.lang.String id) {
		log.debug("getting Orders instance with id: " + id);
		try {
			Orders instance = (Orders) sessionFactory.getCurrentSession().get(
					"com.serp.model.Orders", id);
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
