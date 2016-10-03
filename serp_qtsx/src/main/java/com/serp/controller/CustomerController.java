package com.serp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.serp.entity.CustomerEntity;
import com.serp.service.CustomerService;

@RestController
@RequestMapping(value="/")
public class CustomerController {
//private static final Logger logger = Logger.getLogger(QuotationController.class);
	
	//@Autowired
//	MessageSource messageSource;
	@Autowired
	CustomerService cusService;
	
	String currentUser="";
	
	
	
	
	
	
	@RequestMapping (value = {"/customer/list"},produces ="application/json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllCustomer(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<CustomerEntity> listCusEntity = cusService.getAllCustomer();
		result.put("status", "ok");
		result.put("list", listCusEntity);
		return result;
		
	}
//	@RequestMapping(value = { "/listcustomer" }, method = RequestMethod.GET)
//	public ModelAndView handleListRequest(){
//		return new ModelAndView("customer/list_customer");
//		
//	}
	
	@RequestMapping (value = "/viewcustomer", method = RequestMethod.GET)
	public ModelAndView handleViewRequest(HttpServletResponse response){
		response.setContentType("text/html");
		return new ModelAndView("customer/view_customer") ;
	}
	
	@RequestMapping(value = { "/listcustomer" }, method = RequestMethod.GET)
	public ModelAndView listCustomer(HttpServletResponse response) {
		response.setContentType("text/html");
		currentUser=getPrincipal();
		return new ModelAndView("customer/list_customer");
	}

	/**
	 * This function is used to delete an customer
	 * @param id
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteCustomer(@PathVariable int id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("deleteStatus", cusService.deleteCustomer(id));
        return result;
    }
	

	/**
	 * This function is used to add new Customer, return addStatus as JSON format
	 * @param customer
	 * @return status, addStatus as JSON format 
	 */
	@ResponseBody
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addCustomer(@RequestBody CustomerEntity cusEnt) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
		
        String userId= currentUser;
        result.put("addStatus", cusService.addCustomer(cusEnt, userId));
        return result;
    }
	
	/**
	 * This function is used set view to display customer list
	 */

	@RequestMapping(value = { "/addcustomer" }, method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletResponse response) {
		response.setContentType("text/html");
		currentUser=getPrincipal();
		return new ModelAndView("customer/add_customer");
	}
	
	/**
	 * This function check if a customer with given customerId is existed on database.
	 * @param customerId
	 * @return JSON value
	 */
	@RequestMapping(value = "/customer/isExist/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> isCustomerExist(@PathVariable int customerId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "ok");
        result.put("isExisted", cusService.isCustomerExistedById(customerId));
        return result;
    }
	
	/**
	 * This function return userId of the current login user
	 * @return userid
	 */
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
	
	/**
	 * This function edit a customer and update into database
	 * @param customer
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> editCustomer(@RequestBody CustomerEntity cusEnt) {
	//	log.info(String.format("editCustomer with param 'customer' in class: %s", getClass()));
		try{
		//	log.debug("edit 1 customer and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        String userId= currentUser;
	        result.put("editStatus", cusService.editCustomer(cusEnt, userId));
	     //   log.debug("editCustomer successfully");
	        return result;
		}catch(Exception e){
			//log.error(String.format("editCustomer with param 'customer' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function find a Customer by id then return it as json format
	 * @param customerId
	 * @return JSON format of a customer
	 */
	@RequestMapping(value = "/customer/detail/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCustomerDetail(@PathVariable int customerId) {
		//log.info(String.format("getCustomerDetail with param 'customerId' in class: %s", getClass()));
		try{
			//log.debug("getting customer's detail by its customerId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("customer", cusService.findCustomerEntityById(customerId));
	       // log.debug("getCustomerDetail successful");
	        return result;
		}catch(Exception e){
		//	log.error(String.format("getCustomerDetail with param 'customerId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
}
