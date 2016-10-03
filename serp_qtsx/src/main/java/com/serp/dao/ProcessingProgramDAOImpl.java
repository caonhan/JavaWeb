package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.ProcessingProgram;

/**
 * The Class ProcessingProgramDAOImpl.
 *
 * @author ThoNP
 */
@Repository("processingProgramDAO")
@Transactional
public class ProcessingProgramDAOImpl implements ProcessingProgramDAO {

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(EstimateDetailDaoImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDAO#listProcessingProgram()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingProgram> listProcessingProgram() {
		log.debug("in list ProcessingProgram");
		try {
			List<ProcessingProgram> results = (List<ProcessingProgram>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.serp.model.ProcessingProgram").list();
			// Check result list ProcessingProgram.
			log.debug("list successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDAO#add(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean add(ProcessingProgram processingProgram) {
		log.debug("in list ProcessingProgram");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(processingProgram);
			// Check result add ProcessingProgram.
			log.debug("Add: " + processingProgram + "Success!");
			return true;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			return false;
			
		}
		
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDAO#findProcessingProgramById(Integer)
	 */
	@Override
	public ProcessingProgram findProcessingProgramById(Integer id) {
		log.debug("getting EstimateDetail instance with id: " + id);
		try {
			ProcessingProgram instance = (ProcessingProgram) sessionFactory
					.getCurrentSession().get(
							"com.serp.model.ProcessingProgram", id);
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDAO#delete(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean delete(ProcessingProgram processingProgram) {
		log.debug("in list ProcessingProgram");
		try {
			sessionFactory.getCurrentSession().delete(processingProgram);
			// Check result delete ProcessingProgram.
			log.debug("Delete: " + processingProgram + "Success!");
			return true;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			return false;
		}
	}

}
