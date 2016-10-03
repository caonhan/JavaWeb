package com.serp.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ProcessingProgramDetailDAO;
import com.serp.model.ProcessingProgramDetail;

/**
 * The interface ProcessingProgramServiceImpl.
 *
 * @author ThoNP
 */
@Service("processingProgramDetailService")
public class ProcessingProgramDetailServiceImpl implements
		ProcessingProgramDetailService {

	/** The processing program detail DAO. */
	@Autowired
	ProcessingProgramDetailDAO processingProgramDetailDao;

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(ProcessingProgramDetailServiceImpl.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramServiceImpl#listProcessingProgram()
	 */
	@Override
	public List<ProcessingProgramDetail> listProcessingProgramDetail(Integer id) {
		log.debug("list Processing TechnologyService");
		try {
			return processingProgramDetailDao.listProcessingProgramDetail(id);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}

		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramServiceImpl#add(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean add(ProcessingProgramDetail processingProgramDetail) {
		// TODO Auto-generated method stub
		log.debug("Add Processing TechnologyService");
		try {
			return processingProgramDetailDao.add(processingProgramDetail);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramServiceImpl#findProcessingProgramById(Integer)
	 */
	@Override
	public ProcessingProgramDetail searchProcessingProgramDetail(Integer id) {
		log.debug("list Processing TechnologyService");
		try {
			return processingProgramDetailDao.searchProcessingProgramDetail(id);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.ProcessingProgramServiceImpl#delete(com.serp.model.ProcessingProgram)
	 */
	@Override
	public boolean delete(ProcessingProgramDetail processingProgramDetail) {
		log.debug("Add Processing TechnologyService");
		try {
			return processingProgramDetailDao.delete(processingProgramDetail);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal "
					+ ex.getMessage());
		}
		return false;
	}

}
