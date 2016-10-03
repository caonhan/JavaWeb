package com.serp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.serp.entity.UserEntity;
import com.serp.model.Element;
import com.serp.model.ProcessingProgram;
import com.serp.model.ProcessingProgramDetail;
import com.serp.model.User;
import com.serp.service.ElementService;
import com.serp.service.UserService;
import com.serp.service.ProcessingProgramDetailService;
import com.serp.service.ProcessingProgramService;

/**
 * The Class ProcessingProgramController.
 *
 * @author ThoNP
 */
@Controller
@RequestMapping("/")
public class ProcessingProgramController {

	/**
	 * Initialization id for current ProcessingProgram and current
	 * ProcessingProgramDetail
	 */
	String current_idPP = "";
	String current_idPPDetail = "";

	/** The message source */
	@Autowired
	MessageSource messageSource;

	/** The element service */
	@Autowired
	ElementService elementService;

	/** The user service */
	@Autowired
	UserService UserService;

	/** The processing program detail service */
	@Autowired
	ProcessingProgramDetailService processingProgramDetailService;

	/** The processing program service */
	@Autowired
	ProcessingProgramService processingProgramService;

	/** For ProcessingProgram */

	/**
	 * Gets the processing program.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the processing programs
	 */
	@RequestMapping(value = { "/listProgram" }, method = RequestMethod.GET)
	public ModelAndView listProcessingProgram(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html");

		/** Initialization resource */
		// Get list UserEntity to set attribute for file
		// processingProgram/listProgram.jsp
		
		//List<UserEntity> listUser = UserService.getAllUser();
		List<User> listUser = UserService.getAllUser();
		request.setAttribute("listUser", listUser);

		// Get list Element to set attribute for file
		// processingProgram/listProgram.jsp
		List<Element> listElement = elementService.findAll();
		request.setAttribute("listElement", listElement);

		// Set ProcessingProgram to set attribute for file
		// processingProgram/listProgram.jsp
		ProcessingProgram ppd = new ProcessingProgram();
		request.setAttribute("processingPro", ppd);

		// Set list ProcessingProgram to set attribute for file
		// processingProgram/listProgram.jsp
		List<ProcessingProgram> lPP = processingProgramService
				.listProcessingProgram();
		if (lPP == null) {
			System.out.println("List Processing Program is NULL");
		} else {
			System.out
					.println("Number of List Processing Program" + lPP.size());
		}
		request.setAttribute("listProcessingProgram", lPP);
		return new ModelAndView("processingProgram/listProgram");
	}

	/**
	 * Add the processing program.
	 *
	 * @ModelAttribute("processingPro")
	 *
	 * @param ProcessingProgram
	 *            the ProcessingProgram
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return "redirect:/listProgram";
	 */
	@RequestMapping(value = { "/addProcessingProgram" }, method = RequestMethod.POST)
	public String addProcessingProgram(
			@ModelAttribute("processingPro") ProcessingProgram processingProgram,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		/** Initialization data bind */
		User creator = new User();
		User recieve = new User();
		Element element = new Element();
		Date dateCreate = new Date();
		Date dateRecieve = new Date();

		/** Get data form @ModelAttribute("processingPro") */
		creator = UserService.findByUserID(processingProgram
				.getUserByCreatorId().getUserId());
		recieve = UserService.findByUserID(processingProgram.getImage2());
		element = elementService.findById(processingProgram.getImage3());

		/** Set data for ProcessingProgram */
		processingProgram.setUserByCreatorId(creator);
		processingProgram.setUserByRecieverId(recieve);
		processingProgram.setElement(element);
		processingProgram.setCreatorDate(dateCreate);
		processingProgram.setRecieverDate(dateRecieve);

		/** Add ProcessingProgram to data base */
		processingProgramService.add(processingProgram);
		return "redirect:/listProgram";
	}

	/**
	 * Delete the processing program.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return "redirect:/listProgram";
	 */
	@RequestMapping(value = { "/deleteProcessingProgram" }, method = RequestMethod.GET)
	public String deleteProcessingProgram(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html");

		/** Get id ProcessingProgram current */
		current_idPP = request.getParameter("idPP");
		ProcessingProgram processingProgram = processingProgramService
				.findProcessingProgramById(Integer.valueOf(current_idPP));

		/** Delete ProcessingProgram form id ProcessingProgram current */
		processingProgramService.delete(processingProgram);

		/** Clear id ProcessingProgram detail current */
		current_idPPDetail = "";
		return "redirect:/listProgram";
	}

	/** For ProcessingProgramDetail */

	/**
	 * Gets the processing program detail.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the processing program details.
	 */
	@RequestMapping(value = { "/program" }, method = RequestMethod.GET)
	public ModelAndView listProcessingProgramDetail(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html");

		/** Get id processing program current */
		String id = request.getParameter("id");
		if (id != null) {
			current_idPP = id;
			ProcessingProgram pp = new ProcessingProgram();
			pp = processingProgramService.findProcessingProgramById(Integer
					.valueOf(current_idPP));
			request.setAttribute("processingPro", pp);
		}

		/** Get id processing program detail current */
		current_idPPDetail = request.getParameter("idPPDetail");

		ProcessingProgramDetail pr = new ProcessingProgramDetail();
		if (current_idPPDetail != null) {
			pr = processingProgramDetailService
					.searchProcessingProgramDetail(Integer
							.valueOf(current_idPPDetail));
		}

		/** Set attribute to show for processing program detail current */
		request.setAttribute("processingProDe", pr);

		List<ProcessingProgramDetail> l = processingProgramDetailService
				.listProcessingProgramDetail(Integer.valueOf(current_idPP));
		if (l == null)
			System.out.println("gia tri NULL");
		else {
			System.out.println("asd " + l.size());
		}
		request.setAttribute("listProgramDetail", l);

		/** Show view file processingProgram/createProgramDetail.jsp */
		return new ModelAndView("processingProgram/createProgramDetail");
	}

	/**
	 * Add the processing program Detail.
	 *
	 * @ModelAttribute("processingProDe")
	 *
	 * @param ProcessingProgramDetail
	 *            the ProcessingProgramDetail
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return "redirect:/program";
	 */
	@RequestMapping(value = { "/addProcessingProgramDetail" }, method = RequestMethod.POST)
	public String addProcessingProgramDetail(
			@ModelAttribute("processingProDe") ProcessingProgramDetail processingProgramDetail,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		/** Get id processing program current */
		int id = Integer.valueOf(current_idPP);
		processingProgramDetail.setProcessingProgram(processingProgramService
				.findProcessingProgramById(Integer.valueOf(id)));

		processingProgramDetailService.add(processingProgramDetail);
		return "redirect:/program?id=" + id;
	}

	/**
	 * Delete the processing program detail.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return "redirect:/program";
	 */
	@RequestMapping(value = { "/deleteProcessingProgramDetail" }, method = RequestMethod.GET)
	public String deleteProcessingProgramDetail(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html");

		/** Get id processing program detail current */
		current_idPPDetail = request.getParameter("idPPDetail");
		ProcessingProgramDetail processingProgramDetail = processingProgramDetailService
				.searchProcessingProgramDetail(Integer
						.valueOf(current_idPPDetail));

		/** Keep id processing program current */
		int idPP = Integer.valueOf(current_idPP);
		processingProgramDetailService.delete(processingProgramDetail);
		current_idPPDetail = "";
		return "redirect:/program?id=" + idPP;
	}

}
