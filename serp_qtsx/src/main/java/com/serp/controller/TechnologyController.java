package com.serp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class TechnologyController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = { "/technologyService" }, method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		return new ModelAndView("processingTechnology/createTechnology");
	}
}