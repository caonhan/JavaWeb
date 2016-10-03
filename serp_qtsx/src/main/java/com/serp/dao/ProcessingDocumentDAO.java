package com.serp.dao;

// Generated Apr 13, 2016 8:58:21 AM by Hibernate Tools 4.3.1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.serp.model.ProcessingDocument;

/**
 * Home object for domain model class ProcessingDocument.
 * @see com.serp.dao.ProcessingDocument
 * @author Hibernate Tools
 */

@Repository
@Transactional
public class ProcessingDocumentDAO {

	private static final Log log = LogFactory
			.getLog(ProcessingDocumentDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
     * This function add new ProcessingDocument
     * Output: an processing document
     * Exception:throw RuntimeException if persist failed
     * @param transientInstance
     */
	public void persist(ProcessingDocument transientInstance) {
		log.debug("persisting ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


    /**
     * This function use to delete an processing document
     * Input: processing document
     * Output: delete this processing document
     * Exception:throw RuntimeException if delete failed
     * @param transientInstance
     */
	public void delete(ProcessingDocument persistentInstance) {
		log.debug("deleting ProcessingDocument instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	/**
     * This function use to find an processingdocument by Id
     * Input: id
     * Output: an processingdocument
     * Exception: throw RuntimeException if get failed
     * @param transientInstance
     */
	public ProcessingDocument findById(Integer id) {
		log.debug("getting ProcessingDocument instance with id: " + id);
		try {
			ProcessingDocument instance = (ProcessingDocument) sessionFactory
					.getCurrentSession().get("com.serp.model.ProcessingDocument",
							id);
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
     * This function use to list all processing document
     * Output: list processing document
     * @return
     */ 
    @SuppressWarnings("unchecked")
    public List<ProcessingDocument> getAllProcessingdocument() {
        log.debug("getting all processingdocument");
        try {
            List<ProcessingDocument> results = (List<ProcessingDocument>)sessionFactory.getCurrentSession().createQuery("from ProcessingDocument").list();
            log.debug("getting all processingdocument successfull, result size: "
                    + results.size());
            
            return results;
        } catch (Exception re) {
            log.error("get all order failed", re);
            throw re;
        }

    }

	

}
