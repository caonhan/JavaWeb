package com.serp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;
import com.serp.model.User;
import com.serp.service.ProcessingTechnologyService;
import com.serp.service.UserLoginService;

@Controller
@RequestMapping("/")
public class CheckTechnology {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	private String currentUser = "";

	@Autowired
	MessageSource messageSource;
	@Autowired
	ProcessingTechnologyService processingTechnologyService;
	@Autowired
	UserLoginService userService;

	/**
	 * 
	 * @param reques
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/processingEditor" }, method = RequestMethod.GET)
	public ModelAndView listEditor(HttpServletRequest reques,
			HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			List<ProcessingTechnology> lstProEditor = new ArrayList<ProcessingTechnology>();
			currentUser = getPrincipal();
			User user = userService.findById(currentUser);

			if ("KTCN".equals(processingTechnologyService
					.hasCheckTechnologyRole(user))) {
				lstProEditor = processingTechnologyService
						.listProcessingEditor_leader("new");
			} else if ("DCN".equals(processingTechnologyService
					.hasCheckTechnologyRole(user))) {
				lstProEditor = processingTechnologyService
						.listProcessingEditor_manager("waiting approval");

			}
			System.out.println("role : "
					+ processingTechnologyService.hasCheckTechnologyRole(user));
			if (lstProEditor == null)
				System.out.println("null ...");
			reques.setAttribute("lstProEditor", lstProEditor);
			return new ModelAndView("checktechnology/processingEditor");
		} catch (Exception e) {
			logger.error(String.format("listEditor in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * 
	 * @param reques
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/listTechnology" }, method = RequestMethod.POST)
	public ModelAndView listTechnology(HttpServletRequest reques,
			HttpServletResponse response) {

		try {
			response.setContentType("text/html");
			String idproName = reques.getParameter("proName");
			String note = reques.getParameter("note");
			List<ProcessingTechnologyDetail> lstProTechnology = new ArrayList<ProcessingTechnologyDetail>();

			ProcessingTechnology proTechnology = new ProcessingTechnology();
			proTechnology = processingTechnologyService.findById(Integer
					.valueOf(idproName));

			ProcessingProgram program = new ProcessingProgram();
			List<ProcessingProgramDetail> lstProgramDetail = new ArrayList<ProcessingProgramDetail>();
			try {
				program = processingTechnologyService.findProgram(proTechnology
						.getElement().getEId());

				lstProgramDetail = processingTechnologyService
						.findProgramDetail(program.getId());
			} catch (NullPointerException e) {
				logger.error(String.format(
						"proTechnology in class: %s has error: %s", getClass(),
						e.getMessage()));
				throw e;
			}
			if (idproName != null) {
				lstProTechnology = processingTechnologyService
						.listProcessingTechnologies(Integer.valueOf(idproName));

				if (lstProTechnology == null)
					System.out.println("null ...");
			}

			reques.setAttribute("lstProTechnology", lstProTechnology);

			User user = userService.findById(currentUser);

			if ("KTCN".equals(processingTechnologyService
					.hasCheckTechnologyRole(user))) {
				proTechnology.setPtStatus("waiting approval");
			} else if ("DCN".equals(processingTechnologyService
					.hasCheckTechnologyRole(user))) {
				proTechnology.setPtStatus("approval");
			}

			reques.setAttribute("ProTechnologyOld", proTechnology);
			reques.setAttribute("listProgram", lstProgramDetail);
			logger.debug("listTechnology successfully");
			return new ModelAndView("checktechnology/checktechnology",
					"proTechnology", proTechnology);
		} catch (Exception e) {
			logger.error(String.format(
					"listTechnology in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * 
	 * @param checkTechnology
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = { "/checkTechnology" }, method = RequestMethod.POST, params = "action=Approve")
	public ModelAndView approve(
			@ModelAttribute("proTechnology") ProcessingTechnology proTechnology,
			HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			// get Current date
			Date date = new Date();
			proTechnology.setPtCheckDay(date);
			proTechnology.setPtApproveDay(date);

			// get Current users
			User user = userService.findById(currentUser);

			proTechnology.setUserByPtLeaderAccept(user);
			proTechnology.setUserByPtManagerAccept(user);

			System.out.println("pro "
					+ proTechnology.getUserByPtLeaderAccept().getUserId());
			if (null != proTechnology) {
				if ("KTCN".equals(processingTechnologyService
						.hasCheckTechnologyRole(user))) {
					processingTechnologyService
							.checkProcessTechnology(proTechnology);
				} else if ("DCN".equals(processingTechnologyService
						.hasCheckTechnologyRole(user))) {
					processingTechnologyService
							.approvalTechnology(proTechnology);
				}
				logger.debug("approve successfully");
			} else {
				System.out.println("fail");
			}
			
			return new ModelAndView("redirect:processingEditor");
		} catch (Exception e) {
			logger.error(String.format("approve in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = { "/checkTechnology" }, method = RequestMethod.POST, params = "action=Reject")
	public ModelAndView reject(
			@ModelAttribute("proTechnology") ProcessingTechnology proTechnology,
			HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			// get Current date
			Date date = new Date();
			proTechnology.setPtCheckDay(date);
			proTechnology.setPtApproveDay(date);

			// get Current users
			User user = userService.findById(currentUser);

			proTechnology.setUserByPtLeaderAccept(user);
			proTechnology.setUserByPtManagerAccept(user);
			proTechnology.setPtStatus("Reject");

			System.out.println("pro "
					+ proTechnology.getUserByPtLeaderAccept().getUserId());
			if (null != proTechnology) {
				if ("KTCN".equals(processingTechnologyService
						.hasCheckTechnologyRole(user))) {
					processingTechnologyService
							.checkProcessTechnology(proTechnology);
				} else if ("DCN".equals(processingTechnologyService
						.hasCheckTechnologyRole(user))) {
					processingTechnologyService
							.approvalTechnology(proTechnology);
				}

				logger.debug("reject successfully");
			} else {
				System.out.println("fail");
			}
			
			return new ModelAndView("redirect:processingEditor");
		} catch (Exception e) {
			logger.error(String.format("reject in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * printProcessingTechnology This function return userId of the current
	 * login user
	 * 
	 * @return userName
	 */
	private String getPrincipal() {
		logger.info(String.format("getPrincipal in class: %s", getClass()));
		try {
			logger.debug("get current login user and return it's username");
			String userName = null;
			Object principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			if (principal instanceof UserDetails)
				userName = ((UserDetails) principal).getUsername();
			logger.debug("getPrincipal successfully, userName = " + userName);
			return userName;
		} catch (Exception e) {
			logger.error(String.format(
					"getPrincipal in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

}
