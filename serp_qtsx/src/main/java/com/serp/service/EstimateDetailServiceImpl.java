/**
 * 
 */
package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.EstimateDetailDao;
import com.serp.model.EstimateDetail;

/**
 * @author vuong-bt
 *
 */
@Service("estimateDetailService")
public class EstimateDetailServiceImpl implements EstimateDetailService {
	@Autowired
	EstimateDetailDao estimateDetailDao;
	private static final Log log = LogFactory.getLog(EstimateServiceImpl.class);

	/**
	 * This function is to find an EstimateDetail by id
	 * @param int id
	 * @return EstimateDetail
	 * @exception null
	 */
	@Override
	public EstimateDetail findById(int id) {
		log.debug("in estimate detail service find by id");
		try {
			EstimateDetail ed = estimateDetailDao.findById(id);
			return ed;
		} catch (NullPointerException ne) {
			log.error("findbyid service estimate detail" + id + " err: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("findbyid fatal " + ex.getMessage());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.serp.service.EstimateDetailService#list()
	 */
	@Override
	public List<EstimateDetail> list() {
		log.debug("in estimateDetail service list");
		try {
			return estimateDetailDao.list();
		} catch (NullPointerException ne) {
			log.error("list service estimate err: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("list fatal " + ex.getMessage());
		}
		return null;
	}

}
