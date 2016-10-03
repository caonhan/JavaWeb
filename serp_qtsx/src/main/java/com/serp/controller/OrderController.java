package com.serp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.serp.entity.OrderEntity;
import com.serp.entity.StatusEntity;
import com.serp.service.OrderService;
import com.serp.service.StatusServiceImpl;

@RestController
@RequestMapping(value="/")
public class OrderController {
	
	private static final Log log = LogFactory.getLog(OrderController.class);
	
	@Autowired
	OrderService service;
	
	@Autowired
	StatusServiceImpl serviceStatus;
	
	String currentUser="";
	
	/**
	 * This function is used to return order list as JSON format
	 * @return orderlist as JSON 
	 */
	@RequestMapping(value = "/order/list", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAllOrder() {
		log.info(String.format("getAllROrder in class %s", getClass()));
		try {
			log.debug("getting list of all order and return json");
	        Map<String, Object> result = new HashMap<String, Object>();
	        List<OrderEntity> ls = service.getAllOrder();
	        result.put("status", "ok");
	        result.put("list", ls);
	        log.debug("getAllOrder successful");
	        return result;
		}catch(Exception e){
        	log.error(String.format("getAllOrder in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }

        
    }
	
	/**
	 * This function is used set view to display order list
	 */
	@RequestMapping(value="/listorder",method = RequestMethod.GET)
	public ModelAndView listOrder(HttpServletResponse response) {
		log.info(String.format("listOrder with param 'response' in class: %s", getClass()));
		try {
			log.debug("return listOrder view for request");
			response.setContentType("text/html");
			currentUser=getPrincipal();
			log.debug("listOrder successful");
			return new ModelAndView("order/listorder");
		}catch(Exception e){
			log.error(String.format("listOrder with param 'response' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

	}
	
	/**
	 * This function is used to add new order, return addStatus as JSON format
	 * @param order
	 * @return status, addStatus as JSON format 
	 */
	@ResponseBody
    @RequestMapping(value = "/order/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addOrder(@RequestBody OrderEntity orderEn) {
		log.info(String.format("addOrder with param 'order' in class: %s", getClass()));
		try{
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
			
	        String userId= currentUser;
	        result.put("addStatus", service.addOrder(orderEn, userId));
	        log.debug("addOrder successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("addOrder with param 'orderId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

    }
	
	/**
	 * This function is used set view to display order list
	 */
	@RequestMapping(value="/addorder",method = RequestMethod.GET)
	public ModelAndView addOrder(HttpServletResponse response) {
		log.info(String.format("addOrder with param 'orderId' in class: %s", getClass()));
		try {
			response.setContentType("text/html");
			currentUser=getPrincipal();
			log.debug("addOrder successful");
			return new ModelAndView("order/addorder");
		}catch(Exception e){
			log.error(String.format("addOrder with param 'orderId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
		

	}
	
	/**
	 * This function check if a order with given orderId is existed on database.
	 * @param orderId
	 * @return JSON value
	 */
	@RequestMapping(value = "/order/isExist/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> isOrderExist(@PathVariable String orderId) {
		log.info(String.format("isOrderExist with param 'orderId' in class: %s", getClass()));
		try{
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("isExisted", service.isOrderExistedById(orderId));
	        log.debug("check isOrderExist successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("isOrderExist with param 'orderId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

    }
	
	/**
	 * This function return userId of the current login user
	 * @return userId
	 */
	private String getPrincipal(){
		log.info(String.format("getPrincipal in class: %s", getClass()));
		try{
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        System.out.println(userName);
	        return userName;
		}catch(Exception e){
			log.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}


    }
	
	/**
	 * This function is used to delete an order
	 * @param orderId
	 * @return status, deleteStatus as JSON
	 */
	@RequestMapping(value = "/order/delete/{orderId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteOrder(@PathVariable String orderId) {
		log.info(String.format("deleteOrder with param 'orderId' in class: %s", getClass()));
		try{
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("deleteStatus", service.deleteOrder(orderId));
	        log.debug("deleteOrder successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("deleteOrder with param 'orderId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

    }
	
	/**
	 * This function edit a order and update into database
	 * @param order
	 * @return status, editStatus as JSON
	 */
	@ResponseBody
    @RequestMapping(value = "/order/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> editOrder(@RequestBody OrderEntity orderEn) {
		log.info(String.format("editOrder with param 'order' in class: %s", getClass()));
		try{
			log.debug("edit 1 order and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        String userId= currentUser;
	        result.put("editStatus", service.editOrder(orderEn, userId));
	        log.debug("editOrder successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("editOrder with param 'order' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function find a Order by id then return it as JSON format
	 * @param orderId
	 * @return JSON format of a order
	 */
	@RequestMapping(value = "/order/detail/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrderDetail(@PathVariable String orderId) {
		log.info(String.format("getOrderDetail with param 'orderId' in class: %s", getClass()));
		try{
			log.debug("getting order's detail by its orderId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("order", service.findOrderEntityById(orderId));
	        log.debug("getOrderDetail successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("getOrderDetail with param 'orderId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function is used to return status list as JSON format
	 * @return status as JSON 
	 */
	@RequestMapping(value = "/status/listStatusForOrder", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAllStatus() {
		log.info(String.format("getAllStatus in class: %s", getClass()));
		try{
	        Map<String, Object> result = new HashMap<String, Object>();
	        List<StatusEntity> ls = serviceStatus.getAllStatus();
	        result.put("status", "ok");
	        result.put("list", ls);
	        log.debug("getAllStatus successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("getAllStatus in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}

    }
}
