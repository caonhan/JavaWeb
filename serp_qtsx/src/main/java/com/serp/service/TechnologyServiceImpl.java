package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;
import com.serp.dao.TechnologyDao;

@Service("technologyService")
public class TechnologyServiceImpl implements TechnologyService{
	@Autowired
	TechnologyDao technologyDAO;
	private static final Log log = LogFactory.getLog(TechnologyServiceImpl.class);


	@Override
	public List<ProcessingTechnologyDetail> list() {
		log.debug("list technologyService");
		try{
			return technologyDAO.list();
		} catch(Exception ex){
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}
		
		return null;
	}


	@Override
	public void add(ProcessingTechnologyDetail technology) {
		log.debug("Add Processing TechnologyService");
		try{
			technologyDAO.add(technology);
		} catch(Exception ex){
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}
	
	}


	/**
	 * This function update processing technology
	 * @param
	 * @return
	 */
	@Override
	public void update(ProcessingTechnologyDetail technology) {
		log.debug("Update Processing TechnologyService");
		try{
			technologyDAO.upadate(technology);
			log.debug("Update processing technology detail success!");
		} catch(Exception ex){
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}
	}


	@Override
	public ProcessingTechnologyDetail findById(Integer id) {
		log.info("findById function");
		try {
			return technologyDAO.findById(id);
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}


	@Override
	public void save(ProcessingTechnologyDetail technologyDetail) {
		log.debug("Add technologyService");
		try{
			technologyDAO.save(technologyDetail);
		} catch(Exception ex){
			log.fatal("list technologyService fatal " + ex.getMessage());
		}
	}


	@Override
	public List<ProcessingTechnology> listProcessingTechnology() {
		log.debug("list technologyService");
		try{
			return technologyDAO.listProcessingTechnology();
		} catch(Exception ex){
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}
		
		return null;
	}
	
	
	


}
