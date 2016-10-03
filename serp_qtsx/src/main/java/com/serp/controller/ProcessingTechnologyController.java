package com.serp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.serp.model.ProcessingTechnology;
import com.serp.model.ProcessingTechnologyDetail;
import com.serp.service.ElementService;
import com.serp.service.TechnologyService;
import com.serp.service.UserLoginService;

@Controller
@RequestMapping("/")
public class ProcessingTechnologyController {
	private static final Logger logger = Logger.getLogger(ProcessingTechnologyController.class);
	String current_idPTD = "";
	String current_idPT = "";
	@Autowired
	MessageSource messageSource;
	@Autowired
	TechnologyService technologyService;
	@Autowired
	ElementService elementService;
	@Autowired
	UserLoginService userService;
	@RequestMapping(value = {"/listAllTechnology"}, method  = RequestMethod.GET)
	public ModelAndView listProcessingTechnology(HttpServletRequest request,HttpServletResponse reponse)
	{
		reponse.setContentType("text/html");
		
		//Show list Processing Technology
		List<ProcessingTechnology> lPT = technologyService.listProcessingTechnology();
		if(lPT == null)
		{
			System.out.println("List Processing Technology is NULL");
		}
		else
		{
			System.out.println("Number of List Processing Technology" + lPT.size());
		}
		request.setAttribute("listProcessingTechnology", lPT);
		return new ModelAndView("processingTechnology/listTechnology");
	}
	
	@RequestMapping(value = { "/processing" }, method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		current_idPT =  (String) request.getAttribute("l");
		
		ProcessingTechnologyDetail ptd = new ProcessingTechnologyDetail();
		current_idPTD =  request.getParameter("idPTDetail");
		if(current_idPTD != null)
		{
			ptd = technologyService.findById(Integer
					.valueOf(current_idPTD));

		}
		
		request.setAttribute("technologyDetail", ptd);
		//Show list Processing Technology
		List<ProcessingTechnologyDetail> lPT = technologyService.list();
		if(lPT == null)
		{
			System.out.println("List Processing Technology is NULL");
		}
		else
		{
			System.out.println("Number of List Processing Technology" + lPT.size());
		}
		request.setAttribute("listtechnology", lPT);

		return new ModelAndView("processingTechnology/createTechnology");
	}
	@RequestMapping(value = { "/updateTechnology" }, method = RequestMethod.POST)
	public String updateTechnology(@ModelAttribute("technologyDetail") ProcessingTechnologyDetail technology,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		current_idPTD = request.getParameter("idPTDetail");
		response.setContentType("text/html");
		current_idPTD = (String) request.getAttribute("idPTDetail");
		
		response.setContentType("text/html");
		technologyService.add(technology);
		return "redirect:/processing?idPTDetail=" + current_idPTD;
	}
	
	@RequestMapping(value = { "/addTechnology" }, method = RequestMethod.POST)
	public String addProcessingTechnology(@ModelAttribute("technologyDetail") ProcessingTechnologyDetail technology,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		System.out.println(technology);
		ProcessingTechnology pg = (ProcessingTechnology) request.getSession().getAttribute("technology");
		String lId = (String) request.getSession().getAttribute("lId");
		technology.setProcessingTechnology(pg);
		if (lId != null) {
			try {
				int iId = Integer.parseInt(lId);
				Object[] arr = pg.getProcessingTechnologyDetails().toArray();
				arr[iId - 1] = technology;
				Set<ProcessingTechnologyDetail> s = new HashSet<ProcessingTechnologyDetail>();
				for (Object ptd : arr) {
					s.add((ProcessingTechnologyDetail) ptd);
				}
				pg.setProcessingTechnologyDetails(s);
			} catch (Exception e) {
			}
			request.getSession().setAttribute("lId", null);
		} else
			pg.getProcessingTechnologyDetails().add(technology);
		request.getSession().setAttribute("technology", pg);
		return "redirect:/processing";
	}

	
	
	
	@RequestMapping(value = { "/completeEdit" }, method = RequestMethod.POST)
	public String addPT(@ModelAttribute("technologyDetail") ProcessingTechnologyDetail technology,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		ProcessingTechnologyDetail pg = (ProcessingTechnologyDetail) request.getSession().getAttribute("technology");
//		technologyService.save(pg);
//		currentUser = getPrincipal();
//		pg.setUserByPtCreatorId(userService.findById(currentUser));
		technologyService.save(pg);
		return "redirect:/processing";
	}
	
	private String getPrincipal() {
		logger.info(String.format("getPrincipal in class: %s", getClass()));
		try {
			logger.debug("get current login user and return it's username");
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails)
				userName = ((UserDetails) principal).getUsername();
			logger.debug("getPrincipal successfully, userName = " + userName);
			return userName;
		} catch (Exception e) {
			logger.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}

}
