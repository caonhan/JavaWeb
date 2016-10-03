package com.gcs.serp_qtdh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.serp_qtdh.model.Users;
import com.gcs.serp_qtdh.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<Users> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
	}

}
