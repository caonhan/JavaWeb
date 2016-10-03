package com.serp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;

@Repository("limitInventoryDao")
@Transactional
public class LimitInventoryDAOImpl implements LimitInventoryDAO {
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	private static final Log log = LogFactory.getLog(LimitInventoryDAOImpl.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateLimitInventory(LimitInventory li) {
		log.debug("Saving or Updating LimitInventory instance with id: " + li.getLimitInventoryId());

		try {
			getSession().saveOrUpdate(li);
			
			Iterator<LimitInventoryDetail> iterator = li.getLimitInventoryDetails().iterator();
			while(iterator.hasNext()){
				LimitInventoryDetail lid = iterator.next();
				lid.setLimitInventory(li);
				// Save or update detail to database
				saveOrUpdateLimitInventoryDetail(lid);
				
			}
			log.debug("Save or update successful");
		} catch (RuntimeException re) {
			log.error("Save or Update failed", re);
			throw re;
		}

	}

	@Override
	public LimitInventory getLimitInventoryById(Integer id) {
		log.debug("getting LimitInventory instance with id: " + id);

		try {
			LimitInventory instance = (LimitInventory) getSession().get(LimitInventory.class, id);
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

	@Override
	public void deleteLimitInventory(Integer id) {
		log.debug("deleting LimitInventory instance");

		try {
			LimitInventory limitInventory = (LimitInventory) getSession().load(LimitInventory.class, id);
			getSession().delete(limitInventory);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LimitInventory> getAllLimitInventories() {
		log.debug("fiding all LimitInventory instance");

		try {
			List<LimitInventory> liList = new ArrayList<LimitInventory>();

			liList = getSession().createQuery("FROM " + LimitInventory.class.getName())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

			log.debug("find all successful, result size: " + liList.size());
			return liList;
		} catch (RuntimeException re) {
			log.error("find all failed - Message:" + re);
			throw re;
		}
	}

	@Override
	public void saveOrUpdateLimitInventoryDetail(LimitInventoryDetail lid) {
		log.debug("Saving or Updating LimitInventoryDeatail instance with id: " + lid.getLimitInventoryDetailId());

		try {
			getSession().saveOrUpdate(lid);
			log.debug("Save or update successful");
		} catch (RuntimeException re) {
			log.error("Save or Update failed", re);
			throw re;
		}
	}

	@Override
	public void deleteLimitInventoryDetail(Integer id) {
		log.debug("Deleting LimitInventoryDeatail instance with id: " + id);

		try {
			LimitInventoryDetail detail = (LimitInventoryDetail) getSession().load(LimitInventoryDetail.class, id);
			getSession().delete(detail);
			log.debug("Delete successful");
		} catch (RuntimeException re) {
			log.error("Delete failed", re);
			throw re;

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LimitInventoryDetail> getAllDetailByLimitInventoryId(Integer id) {
		log.debug("Finding all LimitInventoryDetail by LimitInventory Id: " + id);

		try {
			List<LimitInventoryDetail> lidList = new ArrayList<LimitInventoryDetail>();
			lidList = getSession().createQuery("FROM " + LimitInventoryDetail.class.getName())
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			log.debug("find all detail successful, result size: " + lidList.size());
		} catch (RuntimeException re) {
			log.error("find all detail failed", re);
			throw re;
		}
		return null;
	}
	
	/**
	 * @author VIETDAT
     * This function use to list all id of limit inventory 
     * Output: list id of limit inventory
     * @return
     */ 
    @SuppressWarnings("unchecked")
    @Override
    public List<LimitInventory> getAllIdLimitInventories() {
        log.debug("getting all limit inventory");
        try {
            List<LimitInventory> results = (List<LimitInventory>)sessionFactory.getCurrentSession().createQuery("from LimitInventory").list();
            log.debug("getting all limit inventory successfull, result size: "
                    + results.size());
            
            return results;
        } catch (RuntimeException re) {
            log.error("get all limit inventory failed", re);
            throw re;
        }
    }

}
