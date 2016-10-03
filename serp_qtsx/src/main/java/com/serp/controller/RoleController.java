package com.serp.controller;

import java.util.ArrayList;
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

import com.serp.entity.FunctionEntity;
import com.serp.entity.RoleEntity;
import com.serp.service.RoleService;

/**
 * 
 * @author PhiTT
 *
 */
@RestController
@RequestMapping(value="/")
public class RoleController {
	
	private static final Log log = LogFactory.getLog(RoleController.class);
	
	@Autowired
	RoleService ser;
	
	String currentUser="";
	
	/**
	 * This method is used to get all role in database and return a list role in json
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/role/list", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAllRole() {
        log.info(String.format("getAllRole in class %s", getClass()));
		try{
			log.debug("getting list of all Role and return json");
        	Map<String, Object> result = new HashMap<String, Object>();
            List<RoleEntity> ls = ser.getAllRoleEntiry();
            result.put("status", "ok");
            result.put("list", ls);
            log.debug("getAllRole successful");
            return result;
        }catch(Exception e){
        	log.error(String.format("getAllRole in class %s has error: %s", getClass(), e.getMessage()));
        	throw e;
        }
    }
	
	/**
	 * This method is used to return a view will contain list of roles from user request.
	 * @return ModelAndView()
	 */
	@RequestMapping(value = { "/listRole" }, method = RequestMethod.GET)
	public ModelAndView listRole(HttpServletResponse response) {
		log.info(String.format("listRole with param 'response' in class: %s", getClass()));
		try{
			log.debug("return listRole view for request");
			response.setContentType("text/html");
			//assign userId when access /listRole url
			currentUser= getPrincipal();
			log.debug("listRole successful");
			return new ModelAndView("role/roleList");
		}catch(Exception e){
			log.error(String.format("listRole with param 'response' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function find a Role by id then return it as json format
	 * @param roleId
	 * @return json format of a role
	 */
	@RequestMapping(value = "/role/detail/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRoleDetail(@PathVariable String roleId) {
		log.info(String.format("getRoleDetail with param 'roleId' in class: %s", getClass()));
		try{
			log.debug("getting role's detail by its roleId and return json");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("role", ser.findRoleEntityById(roleId));
	        log.debug("getRoleDetail successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("getRoleDetail with param 'roleId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function is used to add new role into database, after that return addStatus as json format
	 * @param role
	 * @return status, addStatus as json format 
	 */
	@ResponseBody
    @RequestMapping(value = "/role/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> addRole(@RequestBody RoleEntity role) {
		log.info(String.format("addRole with param 'role' in class: %s", getClass()));
		try{
			log.debug("adding 1 role into DB and return addStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        String userId= currentUser;
	        result.put("addStatus", ser.addRole(role, userId));
	        log.debug("addRole successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("addRole with param 'roleId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function check if a role with given roleId is existed on database.
	 * @param roleId
	 * @return json value
	 */
	@RequestMapping(value = "/role/isExist/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> isRoleExist(@PathVariable String roleId) {
		log.info(String.format("isRoleExist with param 'roleId' in class: %s", getClass()));
		try{
			log.debug("check if a role with roleId is existed in DB and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("isExisted", ser.isRoleExistedById(roleId));
	        log.debug("check isRoleExist successful");
	        return result;
		}catch(Exception e){
			log.error(String.format("isRoleExist with param 'roleId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function edit a Role and update into database
	 * @param role
	 * @return status, editStatus as json
	 */
	@ResponseBody
    @RequestMapping(value = "/role/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> editRole(@RequestBody RoleEntity role) {
		log.info(String.format("editRole with param 'role' in class: %s", getClass()));
		try{
			log.debug("edit 1 role and return edit status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        String userId= currentUser;
	        result.put("editStatus", ser.editRole(role, userId));
	        log.debug("editRole successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("editRole with param 'role' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This status is used to delete a role
	 * @param roleId
	 * @return status, deleteStatus as json
	 */
	@RequestMapping(value = "/role/delete/{roleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteRole(@PathVariable String roleId) {
		log.info(String.format("deleteRole with param 'roleId' in class: %s", getClass()));
		try{
			log.debug("delete 1 role and return delete status as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("deleteStatus", ser.deleteRole(roleId));
	        log.debug("deleteRole successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("deleteRole with param 'roleId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function return role info and its List of all function that belong
	 * @param roleId
	 * @return json format
	 */
	@RequestMapping(value = "/role/detailAndListFunc/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRoleDetailAndItsListFunction(@PathVariable String roleId) {
		log.info(String.format("getRoleDetailAndItsListFunction with param 'roleId' in class: %s", getClass()));
		try{
			log.debug("get 1 Role's detail, its List Function and return as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("role", ser.findRoleEntityById(roleId));
	        result.put("listFunction", ser.listAllFuncOfRoleById(roleId));
	        log.debug("getRoleDetailAndItsListFunction successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("getRoleDetailAndItsListFunction with param 'roleId' in class %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This method is used to assign Functions for Role
	 * @param roleId
	 * @param lst
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/role/AssignFuncForRole/{roleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> assignFunc4Role(@PathVariable String roleId, @RequestBody ArrayList<FunctionEntity> lst) {
		log.info(String.format("assignFunc4Role with params 'roleId', 'lst' in class: %s", getClass()));
		try{
			log.debug("assign function for role and return assignStatus as json format");
			Map<String, Object> result = new HashMap<String, Object>();
	        result.put("status", "ok");
	        result.put("assignStatus", ser.assignFunctionForRole(roleId, lst));
	        for (FunctionEntity functionEntity : lst) {
				System.err.println(functionEntity);
			}
	        System.err.println("List size: "+lst.size());
	        log.debug("assignFunc4Role successfully");
	        return result;
		}catch(Exception e){
			log.error(String.format("assignFunc4Role with params 'roleId', 'lst' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
	
	/**
	 * This function return userId of the current login user
	 * @return userid
	 */
	private String getPrincipal(){
		log.info(String.format("getPrincipal in class: %s", getClass()));
		try{
			log.debug("get current login user and return it's username");
			String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        System.out.println(userName);
	        log.debug("getPrincipal successfully");
	        return userName;
		}catch(Exception e){
			log.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
    }
}
