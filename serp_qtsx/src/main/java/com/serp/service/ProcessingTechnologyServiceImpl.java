package com.serp.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ProcessingTechnologyDAO;
import com.serp.model.Function;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;
import com.serp.model.User;

@Service("processingTechnologyService")
public class ProcessingTechnologyServiceImpl implements ProcessingTechnologyService {
	@Autowired
	ProcessingTechnologyDAO processingTechnologyDAO;
	private static final Log log = LogFactory.getLog(ProcessingTechnologyService.class);

	/**
	 * get list ProcessingTechnology leader_accept with status(new)
	 */
	@Override
	public List<ProcessingTechnology> listProcessingEditor_leader(String status) {
		log.debug("list Processing Editor_leader");
		try {
			return processingTechnologyDAO.listProcessingEditor_leader(status);
		} catch (Exception ex) {
			log.fatal("list processing Editor_leader fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * get list ProcessingTechnology manager_accept with status(Waiting Approval
	 * )
	 */
	@Override
	public List<ProcessingTechnology> listProcessingEditor_manager(String status) {
		log.debug("list Processing Editor_manager");
		try {
			return processingTechnologyDAO.listProcessingEditor_manager(status);
		} catch (Exception ex) {
			log.fatal("list processing Editor_manager fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * get list ProcessingTechnology with id
	 */
	@Override
	public List<ProcessingTechnologyDetail> listProcessingTechnologies(int id) {
		log.debug("list Processing TechnologyService");
		try {
			return processingTechnologyDAO.listProcessingTechnologies(id);

		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}

		return null;
	}

	/**
	 * leader approve/reject processingTechnology/program
	 */
	@Override
	public void checkProcessTechnology(ProcessingTechnology processingTechnology) {
		// TODO Auto-generated method stub
		log.debug("Add Processing TechnologyService");
		try {
			processingTechnologyDAO.checkProcessTechnology(processingTechnology);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}
	}

	/**
	 * manager approve/reject processingTechnology/program
	 */
	@Override
	public void approvalTechnology(ProcessingTechnology processingTechnology) {
		// TODO Auto-generated method stub
		log.debug("Add Processing TechnologyService");
		try {
			processingTechnologyDAO.approvalTechnology(processingTechnology);
		} catch (Exception ex) {
			log.fatal("list processing technologyService fatal " + ex.getMessage());
		}

	}

	/**
	 * find ProcessingTechnology by id
	 */
	@Override
	public ProcessingTechnology findById(Integer id) {
		log.debug("in ProcessingTechnology service find by id");
		try {
			return processingTechnologyDAO.findById(id);
		} catch (Exception ex) {
			log.fatal("findbyid fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * get role_function by user
	 */
	@Override
	public String hasCheckTechnologyRole(User u) {
		log.debug("in hasCheckTechnologyRole service find by user");
		try {
			Set<Function> functions = u.getRole().getFunctions();
			Iterator<Function> iter = functions.iterator();
			while (iter.hasNext()) {
				String tmp = iter.next().getFunctionId();
				if ("FT8".equals(tmp))
					return "KTCN";
				else {
					if ("FT9".equals(tmp))
						return "DCN";
				}
			}
		} catch (Exception ex) {
			log.fatal("hasCheckTechnologyRole fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * find processing program by element_id
	 */
	@Override
	public ProcessingProgram findProgram(String element_id) {
		log.debug("in ProcessingProgram service find by id");
		try {
			return processingTechnologyDAO.findProgram(element_id);
		} catch (Exception ex) {
			log.fatal("findbyid fatal " + ex.getMessage());
		}
		return null;
	}

	/**
	 * find list Program Detail by program_id
	 */
	@Override
	public List<ProcessingProgramDetail> findProgramDetail(Integer program_id) {
		log.debug("in ProcessingProgramDetail service find by id");
		try {
			return processingTechnologyDAO.findProgramDetail(program_id);
		} catch (Exception ex) {
			log.fatal("findbyid fatal " + ex.getMessage());
		}
		return null;
	}
}
