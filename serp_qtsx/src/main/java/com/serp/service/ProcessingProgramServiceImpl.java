package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ProcessingProgramDAO;
import com.serp.model.ProcessingProgram;

/**
 * The Class ProcessingProgramServiceImpl.
 *
 * @author ThoNP
 */
@Service("processingProgramService")
public class ProcessingProgramServiceImpl implements ProcessingProgramService {

	/** The processing program DAO. */
	@Autowired
	ProcessingProgramDAO processingProgramDao;

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(ProcessingProgramServiceImpl.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramService#listProcessingProgram()
	 */
	@Override
	public List<ProcessingProgram> listProcessingProgram() {
		log.debug("list Processing TechnologyService");
		try {
			return processingProgramDao.listProcessingProgram();
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}

		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramService#add(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean add(ProcessingProgram processingProgram) {
		// TODO Auto-generated method stub
		log.debug("Add Processing TechnologyService");
		try {
			return processingProgramDao.add(processingProgram);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramService#findProcessingProgramById(Integer)
	 */
	@Override
	public ProcessingProgram findProcessingProgramById(Integer id) {
		log.debug("list Processing TechnologyService");
		try {
			return processingProgramDao.findProcessingProgramById(id);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramService#delete(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean delete(ProcessingProgram processingProgram) {
		// TODO Auto-generated method stub
		log.debug("Add Processing TechnologyService");
		try {
			return processingProgramDao.delete(processingProgram);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return false;
	}
}
