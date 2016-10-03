package com.serp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/", "/index", "/dashboard", "/home" }, method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletResponse response, HttpSession request) {
		response.setContentType("text/html");
		return new ModelAndView("home");
	}

	@RequestMapping(value = { "/errorPage" }, method = RequestMethod.GET)
	public ModelAndView directToErrorPage(HttpServletResponse response, HttpSession request) {
		response.setContentType("text/html");
		return new ModelAndView("errorPage");
	}

}
