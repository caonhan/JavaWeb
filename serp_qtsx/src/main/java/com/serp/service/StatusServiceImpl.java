package com.serp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.StatusDAO;
import com.serp.entity.OrderEntity;
import com.serp.entity.StatusEntity;
import com.serp.model.Orders;
import com.serp.model.Status;

@Service("statusService")
public class StatusServiceImpl implements StatusService {
	@Autowired
	StatusDAO statusDAO;
	private static final Log log = LogFactory.getLog(EstimateServiceImpl.class);

	@Override
	public List<Status> list() {
		log.debug("in estimate service list");
		try {
			return statusDAO.list();
		} catch (NullPointerException ne) {
			log.error("list service estimate err: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("list fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * This function is used get all status
	 * @return list<status>
	 */
	public List<StatusEntity> getAllStatus(){
		List<Status> lstOrder = statusDAO.list();
		StatusEntity statusEn;
		List<StatusEntity> lstStatusEntity = new ArrayList<StatusEntity>();
		for (Status stt : lstOrder) {
			statusEn = new StatusEntity();
			statusEn.setStatusId(stt.getStatusId());
			statusEn.setStatusName(stt.getStatusName());
			
			lstStatusEntity.add(statusEn);
		}
		return lstStatusEntity;
	}
	@Override
	public Status findById(int id) {
		log.debug("in status service find by id");
		try {
			return statusDAO.findById(id);
		} catch (NullPointerException ne) {
			log.error("findbyid service status " + id + " err: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("findbyid fatal " + ex.getMessage());
			throw ex;
		}
		return null;
	}

	@Override
	public Status findByName(String name) {
		log.debug("in status service find by name");
		try {
			for(Status s : statusDAO.list()){
				if(s.getStatusName().equals(name)){
					return s;
				}
			}
		} catch (NullPointerException ne) {
			log.error("findByName service status " + name + " err: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("status findByName fatal " + ex.getMessage());
			throw ex;
		}
		return null;
	}

}
