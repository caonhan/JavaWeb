package com.serp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.serp.dao.RoleDao;
import com.serp.entity.ProcessingDocumentEntity;
import com.serp.entity.UserEntity;
import com.serp.model.Material;
import com.serp.model.Role;
import com.serp.model.User;
import com.serp.service.UserService;


@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	MessageSource messageSource;
	@Autowired
	UserService userservice;
	@Autowired
	RoleDao roledao;
	
	
	String currentUser="";
	
	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public ModelAndView handleRequest1(HttpServletRequest request) {
		request.setAttribute("userList", userservice.getAllUser());
		currentUser = getPrincipal();
		request.setAttribute("userName", currentUser);
		request.setAttribute("user", userservice.findByUserID(currentUser));
		User user = userservice.findByUserID(currentUser);
		if((user.getRole().getRoleId()).compareTo("AD")==0) 
			return new ModelAndView("user/viewAdmin");
		return new ModelAndView("user/viewUser");
	}
	
	@RequestMapping(value = { "/user_add" }, method = RequestMethod.GET)
	public ModelAndView handleRequest2() {
		return new ModelAndView("user/addUser");
	}
	
	@RequestMapping(value = { "/user_profile" }, produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRoleDetail() {
		currentUser= getPrincipal();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("user", userservice.findUserEntityById(currentUser));
        return result;
    }
	
	@RequestMapping(value = { "/add_user" }, method = RequestMethod.POST)
    public String addUserRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("user_id");
            String password = request.getParameter("user_password");
            String name = request.getParameter("user_name");
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate = in.parse(request.getParameter("user_birthdate"));
            String phonenumber = request.getParameter("user_phonenumber");
            String email = request.getParameter("user_email");
            String address = request.getParameter("user_address");
            String department = request.getParameter("user_department");           
            String r = request.getParameter("user_role");
            Role role = roledao.findById(r);
            Integer status = Integer.parseInt(request.getParameter("user_status"));
            
            User user = new User(userId, password, name, birthdate, phonenumber, email, address, department, role, status);
            userservice.addUser(user);
            logger.debug("success");
            return "redirect:user";
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
		
    }
	
	@RequestMapping(value = { "/update_user" }, method = RequestMethod.GET)
    public ModelAndView updateUserRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Update user");
            String userId = request.getParameter("user_id");
            request.setAttribute("user", userservice.findByUserID(userId));
            return new ModelAndView("user/updateUser");
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }
	
	@RequestMapping(value = { "/save_user" }, method = RequestMethod.GET)
    public String saveUser(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String userId = request.getParameter("user_id");
            String password = request.getParameter("user_password");
            String name = request.getParameter("user_name");
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate = in.parse(request.getParameter("user_birthdate"));
            String phonenumber = request.getParameter("user_phonenumber");
            String email = request.getParameter("user_email");
            String address = request.getParameter("user_address");
            String department = request.getParameter("user_department");           
            String r = request.getParameter("user_role");
            Role role = roledao.findById(r);
            Integer status = Integer.parseInt(request.getParameter("user_status"));
           
            User user = new User(userId, password, name, birthdate, phonenumber, email, address, department, role, status);
            userservice.updateUser(user);
            
            logger.debug("success");
            return "redirect:user";
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
    }
	
	@RequestMapping(value = { "/delete_user" }, method = RequestMethod.GET)
    public String deleteUserRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("user_id");
            User user = userservice.findByUserID(userId);
            logger.debug("success");
            userservice.deleteUser(user);
            return "redirect:user";
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
    }
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        System.out.println(userName);
        return userName;
    }
}
