package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.ProcessingProgramDetail;

/**
 * The Class ProcessingProgramDetailDAOImpl.
 *
 * @author ThoNP
 */
@Repository("processingProgramDetailDAO")
@Transactional
public class ProcessingProgramDetailDAOImpl implements
		ProcessingProgramDetailDAO {

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(ProcessingProgramDetailDAOImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDetailDAO#listProcessingProgramDetail()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingProgramDetail> listProcessingProgramDetail(Integer id) {
		log.debug("in list ProcessingProgram");
		try {
			List<ProcessingProgramDetail> results = (List<ProcessingProgramDetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from ProcessingProgramDetail where processing_program_id="
									+ id).list();
			// Check result list ProcessingProgramDetail.
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
	 * @see com.serp.dao.ProcessingProgramDetailDAO#add(com.serp.model.ProcessingProgramDetail)
	 */
	@Override
	public boolean add(ProcessingProgramDetail processingProgramDetail) {
		log.debug("in list ProcessingProgram");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(
					processingProgramDetail);
			// Check result add ProcessingProgramDetail.
			log.debug("Add: " + processingProgramDetail + "Success!");
			return true;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDAO#findProcessingProgramDetail(java.lang.Integer)
	 */
	@Override
	public ProcessingProgramDetail searchProcessingProgramDetail(
			java.lang.Integer id) {
		log.debug("getting Element instance with id: " + id);
		try {
			ProcessingProgramDetail ppd = (ProcessingProgramDetail) sessionFactory
					.getCurrentSession().get(ProcessingProgramDetail.class, id);
			return ppd;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.dao.ProcessingProgramDetailDAO#delete(com.serp.model.ProcessingProgramDetail)
	 */
	@Override
	public boolean delete(ProcessingProgramDetail processingProgramDetail) {
		log.debug("in list ProcessingProgram");
		try {
			sessionFactory.getCurrentSession().delete(processingProgramDetail);
			// Check result delete ProcessingProgramDetail.
			log.debug("Delete: " + processingProgramDetail + "Success!");
			return true;
		} catch (RuntimeException re) {
			log.error("list failed", re);
			throw re;
		}

	}

}
