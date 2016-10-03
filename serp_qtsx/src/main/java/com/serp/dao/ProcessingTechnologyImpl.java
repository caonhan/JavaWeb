package com.serp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;

@Repository("processingTechnologyDao")
@Transactional
public class ProcessingTechnologyImpl implements ProcessingTechnologyDAO {

	private static final Log log = LogFactory.getLog(EstimateDetailDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * get list ProcessingTechnology with id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingTechnologyDetail> listProcessingTechnologies(int id) {
		try {

			List<ProcessingTechnologyDetail> list = new ArrayList<ProcessingTechnologyDetail>();
			list = (List<ProcessingTechnologyDetail>) sessionFactory.getCurrentSession()
					.createQuery("from ProcessingTechnologyDetail where processingTechnology.ptId = '" + id + "'")
					.list();
			return list;
		} catch (Exception e) {
			log.error("get listProcessingTechnologies failed! ");
			throw e;
		}

	}

	/**
	 * get list ProcessingTechnology leader_accept with status(new)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingTechnology> listProcessingEditor_leader(String status) {
		try {
			List<ProcessingTechnology> list = (List<ProcessingTechnology>) sessionFactory.getCurrentSession()
					.createQuery("from ProcessingTechnology where ptStatus='" + status + "'").list();
			return list;
		} catch (Exception e) {
			log.error("get listProcessingTechnologies failed! ");
			throw e;
		}
	}

	/**
	 * get list ProcessingTechnology leader_accept with status(waiting approve)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingTechnology> listProcessingEditor_manager(String status) {
		try {
			List<ProcessingTechnology> list = (List<ProcessingTechnology>) sessionFactory.getCurrentSession()
					.createQuery("from ProcessingTechnology where ptStatus='" + status + "'").list();
			return list;
		} catch (Exception e) {
			log.error("get listProcessingTechnologies failed! ");
			throw e;
		}
	}

	/**
	 * leader approve/reject processingTechnology/program
	 */
	@Override
	public void checkProcessTechnology(ProcessingTechnology processingTechnology) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hqlUpdate = "update ProcessingTechnology u set u.ptNote = :newNote, u.ptStatus= :newStatus, u.ptCheckDay= :newDay, u.userByPtLeaderAccept = :newLeader where u.ptId = :oldId";
			session.createQuery(hqlUpdate).setString("newNote", processingTechnology.getPtNote())
					.setInteger("oldId", processingTechnology.getPtId())
					.setString("newStatus", processingTechnology.getPtStatus())
					.setDate("newDay", processingTechnology.getPtCheckDay())
					.setString("newLeader", processingTechnology.getUserByPtLeaderAccept().getUserId()).executeUpdate();

			String hqlUpdateProgram = "update ProcessingProgram u set u.note = :newNote, u.status= :newStatus, u.checkedDate= :newDay, u.userByCheckedId = :newLeader where u.element.EId = :oldId";
			session.createQuery(hqlUpdateProgram).setString("newNote", processingTechnology.getPtNote())
					.setString("oldId", processingTechnology.getElement().getEId())
					.setString("newStatus", processingTechnology.getPtStatus())
					.setDate("newDay", processingTechnology.getPtCheckDay())
					.setString("newLeader", processingTechnology.getUserByPtLeaderAccept().getUserId()).executeUpdate();

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.flush();
			session.close();
		}

	}

	/**
	 * manager approve/reject processingTechnology/program
	 */
	@Override
	public void approvalTechnology(ProcessingTechnology processingTechnology) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hqlUpdate = "update ProcessingTechnology u set u.ptNote = :newNote, u.ptStatus= :newStatus, u.ptApproveDay= :newDay, u.userByPtManagerAccept = :newManager where u.ptId = :oldId";
			session.createQuery(hqlUpdate).setString("newNote", processingTechnology.getPtNote())
					.setInteger("oldId", processingTechnology.getPtId())
					.setString("newStatus", processingTechnology.getPtStatus())
					.setDate("newDay", processingTechnology.getPtApproveDay())
					.setString("newManager", processingTechnology.getUserByPtManagerAccept().getUserId())
					.executeUpdate();

			String hqlUpdateProgram = "update ProcessingProgram u set u.note = :newNote, u.status= :newStatus where u.element.EId = :oldId";
			session.createQuery(hqlUpdateProgram).setString("newNote", processingTechnology.getPtNote())
					.setString("oldId", processingTechnology.getElement().getEId())
					.setString("newStatus", processingTechnology.getPtStatus()).executeUpdate();

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.flush();
			session.close();
		}
	}

	/**
	 * find ProcessingTechnology by id
	 */
	@Override
	public ProcessingTechnology findById(Integer id) {
		log.debug("getting ProcessingTechnology instance with id: " + id);
		try {
			ProcessingTechnology instance = (ProcessingTechnology) sessionFactory.getCurrentSession()
					.get("com.serp.model.ProcessingTechnology", id);

			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception ex) {
			log.fatal("find ProcessingTechnology error, message: " + ex);
			throw ex;
		}
	}

	/**
	 * find processing program by element_id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ProcessingProgram findProgram(String element_id) {
		log.debug("getting ProcessingProgram instance with element_id: " + element_id);
		try {
			List<ProcessingProgram> instance = (List<ProcessingProgram>) sessionFactory.getCurrentSession()
					.createQuery("from ProcessingProgram where element.EId='" + element_id + "'").list();

			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance.get(0);
		} catch (Exception ex) {
			log.fatal("find ProcessingProgram error, message: " + ex);
			throw ex;
		}
	}

	/**
	 * find list Program Detail by program_id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessingProgramDetail> findProgramDetail(Integer program_id) {
		log.debug("getting ProcessingProgramDetail instance with element_id: " + program_id);
		try {

			List<ProcessingProgramDetail> list = new ArrayList<ProcessingProgramDetail>();
			list = (List<ProcessingProgramDetail>) sessionFactory.getCurrentSession()
					.createQuery("from ProcessingProgramDetail where processingProgram.id = '" + program_id + "'")
					.list();
			return list;
		} catch (Exception e) {
			log.error("get listProcessingTechnologies failed! ");
			throw e;
		}
	}
}
