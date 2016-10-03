package com.serp.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.EstimateDAO;
import com.serp.dao.StatusDAO;
import com.serp.model.Estimate;
import com.serp.model.Function;
import com.serp.model.User;

@Service("estimateService")
public class EstimateServiceImpl implements EstimateService {
    @Autowired
    EstimateDAO estimateDAO;
    @Autowired
    StatusDAO statusDAO;
    @Autowired
    ElementService elementService;
    private static final Log log = LogFactory.getLog(EstimateServiceImpl.class);
    
    /**
     * add new estimate
     * 
     * @param estimate
     * @return true
     * @exception false
     */
    @Override
    public boolean addEstimate(Estimate estimate) {
	log.info(String.format("add estimate"));
	try {
	    estimate.setStatus(statusDAO.findById(1));
	    estimateDAO.persist(estimate);
	    log.debug("add new Estimate into database successfully");
	    return true;
	} catch (Exception e) {
	    log.error(String.format("add Estimate has error: %s", e.getMessage()));
	    return false;
	}
    }

    /**
     * update an estimate
     * 
     * @param estimate
     * 
     */

    @Override
    public void saveOrUpdate(Estimate estimate) {
	log.debug("in estimate service save or update");
	try {
	    estimateDAO.saveOrUpdate(estimate);
	    elementService.cleanTrash();
	} catch (Exception ex) {
	    log.fatal("save or update fatal " + ex.getMessage());
	    throw ex;
	}
    }

    /**
     * delete estimate function
     * 
     * @param estimate
     */

    @Override
    public void delete(Estimate estimate) {
	log.debug("in estimate service delete");
	try {
	    estimateDAO.delete(estimate);
	} catch (Exception ex) {
	    log.fatal("delete fatal " + ex.getMessage());
	    throw ex;
	}
    }

    /**
     * find an estimate by id
     * 
     * @param integer
     *            id
     * @return estimate
     * @exception null
     */

    @Override
    public Estimate findById(Integer id) {
	log.debug("in estimate service find by id");
	try {
	    return estimateDAO.findById(id);
	} catch (Exception ex) {
	    log.fatal("findbyid fatal " + ex.getMessage());
	}
	return null;
    }

    /**
     * list all estimate in database
     * 
     * @return list<estimate>
     * @exception null
     */

    @Override
    public List<Estimate> list() {
	log.debug("in estimate service list");
	try {
	    return estimateDAO.list();
	} catch (Exception ex) {
	    log.fatal("list fatal " + ex.getMessage());
	}
	return null;
    }

    /**
     * check if User has edit role for estimate
     * 
     * @param User
     *            u
     * @return true if user has role "FT3"
     */
    @Override
    public boolean hasEditRole(User u) {
	log.info("check user edit role");
	try {
	    Set<Function> functions = u.getRole().getFunctions();
	    Iterator<Function> iter = functions.iterator();
	    while (iter.hasNext()) {
		if ("FT3".equals(iter.next().getFunctionId())) {
		    log.debug("has edit role");
		    return true;
		}
	    }
	} catch (Exception ex) {
	    log.error("error, message: " + ex.getMessage());
	}
	return false;
    }

    /**
     * check if User has approve role for estimate
     * 
     * @param User
     *            u
     * @return true if user has role "FT4"
     */
    @Override
    public boolean hasApproveRole(User u) {
	log.info("check user approve role");
	try {
	    Set<Function> functions = u.getRole().getFunctions();
	    Iterator<Function> iter = functions.iterator();
	    while (iter.hasNext()) {
		if ("FT4".equals(iter.next().getFunctionId())) {
		    log.debug("has approve role");
		    return true;
		}
	    }
	} catch (Exception ex) {
	    log.error("error, message: " + ex.getMessage());
	}
	return false;
    }

}
