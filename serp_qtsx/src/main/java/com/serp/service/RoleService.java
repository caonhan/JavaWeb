package com.serp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.FunctionDao;
import com.serp.dao.RoleDao;
import com.serp.dao.UserLoginDao;
import com.serp.entity.FunctionEntity;
import com.serp.entity.RoleEntity;
import com.serp.model.Function;
import com.serp.model.Role;

/**
 * 
 * @author PhiTT
 *
 */
@Service("roleService")
public class RoleService {
	
	private static final Log log = LogFactory.getLog(RoleService.class);
	
	@Autowired
	RoleDao dao;
	
	@Autowired
	UserLoginDao userDao;
	
	@Autowired
	FunctionDao funcDao;
	
	/**
	 * This function get a role by id
	 * @param id
	 * @return a role
	 */
	public Role findById(String id){
		log.info(String.format("findById with param 'id' in class: %s", getClass()));
		try{
			return dao.findById(id);
		}catch(Exception e){
			log.error(String.format("findById with param 'id' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to get a list of all roles in database
	 * @return List<RoleEntity>
	 */
	public List<RoleEntity> getAllRoleEntiry(){
		log.info(String.format("getAllRoleEntiry in class: %s", getClass()));
		try{
			log.debug("get all Role in DB after that return a list RoleEntity");
			List<Role> lstRole= dao.getAllRole();
			
			RoleEntity en;
			List<RoleEntity> lst= new ArrayList<RoleEntity>();
			
			for (Role role : lstRole) {
				en= new RoleEntity();
				en.setRoleId(role.getRoleId());
				en.setRoleName(role.getRoleName());
				en.setDescription(role.getDescription());
				en.setCreatedDate(role.getCreatedDate());
				en.setModifiedDate(role.getModifiedDate());
				en.setCreatorId(role.getUserByCreator()==null?"":role.getUserByCreator().getUserId());
				en.setCreatedDate(role.getCreatedDate());
				en.setModifierId(role.getUserByModifier()==null?"":role.getUserByModifier().getUserId()); // this attribute from db maybe null (advoid NullPointerException)
				en.setModifiedDate(role.getModifiedDate());
				
				lst.add(en);
			}
			log.debug("getAllRoleEntiry successfully");
			return lst;
		}catch(Exception e){
			log.error(String.format("getAllRoleEntiry in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to add new Role into database
	 * @param roleEn
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addRole(RoleEntity roleEn, String userId){
		log.info(String.format("getAllRoleEntiry in class: %s", getClass()));
		try{
			Role role= new Role();
			role.setRoleId(roleEn.getRoleId());
			role.setRoleName(roleEn.getRoleName());
			role.setDescription(roleEn.getDescription());
			
			role.setUserByCreator(userDao.findById(userId));
			role.setCreatedDate(new Date());
		
			dao.persist(role);
			log.debug("add new Role into database successfully");
			return true;
		}catch(Exception e){
			log.error(String.format("addRole in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println(String.format("addRole in class: %s has error: %s", getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function find a RoleEntity by id.
	 * @param roleId
	 * @return a Role
	 */
	public RoleEntity findRoleEntityById(String roleId){
		log.info(String.format("findRoleEntityById with param 'roleId' in class: %s", getClass()));
		try{
			RoleEntity en= new RoleEntity();
			Role role= dao.findById(roleId);
			
			en.setRoleId(role.getRoleId());
			en.setRoleName(role.getRoleName());
			en.setDescription(role.getDescription());
			en.setCreatedDate(role.getCreatedDate());
			en.setModifiedDate(role.getModifiedDate());
			en.setCreatorId(role.getUserByCreator()==null?"":role.getUserByCreator().getUserId());
			en.setCreatedDate(role.getCreatedDate());
			en.setModifierId(role.getUserByModifier()==null?"":role.getUserByModifier().getUserId());
			en.setModifiedDate(role.getModifiedDate());
			
			log.debug("findRoleEntityById successfully");
			return en;
		}catch(Exception e){
			log.error(String.format("findRoleEntityById with param 'roleId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function check if there is a role with roleId existed in database
	 * @param roleId
	 * @return false if not existed, true if existed
	 */
	public boolean isRoleExistedById(String roleId){
		log.info(String.format("isRoleExistedById with param 'roleId' in class: %s", getClass()));
		try{
			if(null==dao.findById(roleId))
				return false;
			return true;
		}catch(Exception e){
			log.error(String.format("isRoleExistedById with param 'roleId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public boolean updateRole(Role role){
		log.info(String.format("updateRole with param 'role' in class: %s", getClass()));
		try{
			dao.attachDirty(role);
			log.debug("updateRole successfully");
			return true;
		}catch(Exception e){
			log.error(String.format("isRoleExistedById with param 'roleId' in class: %s has error: %s", getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to edit Role into database
	 * @param roleEn
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editRole(RoleEntity roleEn, String userId){
		log.info(String.format("editRole with param 'roleEn', 'userId' in class: %s", getClass()));
		try{
			Role role= dao.findById(roleEn.getRoleId());
			role.setRoleName(roleEn.getRoleName());
			role.setDescription(roleEn.getDescription());
			
			role.setUserByModifier(userDao.findById(userId));
			role.setModifiedDate(new Date());
		
			dao.attachDirty(role);
			log.debug("editRole successfully");
			return true;
		}catch(Exception e){
			log.error(String.format("editRole with param 'roleEn', 'userId' in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println(String.format("editRole with param 'roleEn', 'userId' in class: %s has error: %s", getClass(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * This function is used to delete a Role in database.
	 * @param roleId
	 * @return true if delete successfully, false if cant
	 */
	public boolean deleteRole(String roleId){
		log.info(String.format("deleteRole with param 'roleId' in class: %s", getClass()));
		try{
			Role role= dao.findById(roleId);
		
			dao.delete(role);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * This method return all function that belong to Role by RoleID
	 * @param roleId
	 * @return
	 */
	public List<FunctionEntity> listAllFuncOfRoleById(String roleId){
		log.info(String.format("listAllFuncOfRoleById with param 'roleId' in class: %s", getClass()));
		try{
			Role role= dao.findById(roleId);
			Set<Function> listFunc = role.getFunctions();
			
			List<FunctionEntity> lst= new ArrayList<FunctionEntity>();
			FunctionEntity en;
			
			for (Function function : listFunc) {
				en= new FunctionEntity();
				en.setFunctionId(function.getFunctionId());
				en.setFunctionName(function.getFunctionName());
				en.setDescription(function.getDescription());
				
				lst.add(en);
			}
			
			log.debug("listAllFuncOfRoleById successfully");
			return lst;
		}catch(Exception e){
			log.error(String.format("listAllFuncOfRoleById with param 'roleId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to assign function for 1 role
	 * @param roleId
	 * @param lst
	 * @return true if assign successfully, false is fail
	 */
	public boolean assignFunctionForRole(String roleId, ArrayList<FunctionEntity> lst){
		log.info(String.format("assignFunctionForRole with param 'roleId', 'lst' in class: %s", getClass()));
		try{
			Role role= dao.findById(roleId);
			
			if(lst.size()==0){
				
				if(role.getFunctions().size()==0){
					log.debug("assignFunctionForRole successfully");
					return true;
				}else{
					role.getFunctions().clear();
					try{
						dao.attachDirty(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					}catch(Exception e){
						return false;
					}
				}
			}else{
				
				if(role.getFunctions().size()==0){
					for (FunctionEntity functionEntity : lst) {
						role.getFunctions().add(funcDao.findById(functionEntity.getFunctionId()));
					}
					
					try{
						dao.attachDirty(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					}catch(Exception e){
						return false;
					}
				}else{
					List<Function> lstToBeRemove = new ArrayList<Function>();
					List<Function> lstToBeAdd = new ArrayList<Function>();
					Set<Function> oldLst = role.getFunctions();
					
					boolean flag= false;
					for (Function function : oldLst) {
						for (FunctionEntity functionEntity : lst) {
							if(function.getFunctionId().equals(functionEntity.getFunctionId())){
								flag= true;
							}
						}
						
						if(flag==false){
							lstToBeRemove.add(function);
						}
						
						if(flag==true){
							flag= false;
						}
					}
					
					for (FunctionEntity functionEntity : lst) {
						for (Function function : oldLst) {
							if(function.getFunctionId().equals(functionEntity.getFunctionId())){
								flag= true;
							}
						}
						
						if(flag==false){
							lstToBeAdd.add(funcDao.findById(functionEntity.getFunctionId()));
						}
						
						if(flag==true){
							flag= false;
						}
					}
					
					oldLst.addAll(lstToBeAdd);
					oldLst.removeAll(lstToBeRemove);
					
					try{
						dao.attachDirty(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					}catch(Exception e){
						return false;
					}
				}
			}
		}catch(Exception e){
			log.error(String.format("assignFunctionForRole with param 'roleId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
