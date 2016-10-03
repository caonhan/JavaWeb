package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ElementDAO;
import com.serp.dao.EstimateDetailDao;
import com.serp.model.Element;

@Service("elementService")
public class ElementServiceImpl implements ElementService {

	@Autowired
	protected ElementDAO elementDao;
	@Autowired
	EstimateDetailDao estimateDetailDao;

	public void setStockRequisitionDAO(ElementDAO elementDao) {
		this.elementDao = elementDao;
	}

	private static final Log log = LogFactory.getLog(ElementServiceImpl.class);

	@Override
	public List<Element> findAll() {
		log.info("findAll function");
		try {
			return elementDao.findAll();
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("NullPointerException - findAll failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("findAll failed");
		}
		return null;
	}

	@Override
	public Element findById(String id) {
		log.info("findById function");
		try {
			return elementDao.findById(id);
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ElementService#cleanTrash()
	 */
	@Override
	public void cleanTrash() {
		List<Element> list = elementDao.findAll();
		for (Element e : list) {
			try {
				elementDao.delete(e);
			} catch (Exception ex) {
			}
		}
	}

}
