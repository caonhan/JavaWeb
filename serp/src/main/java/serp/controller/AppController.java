package serp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import serp.model.Users;
import serp.service.UserService; 

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired   
    UserService service;
	
	 /*
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {
 
        List<Users> users = service.findAllUsers();
        model.addAttribute("users", users);
        return "login";
    }
}
