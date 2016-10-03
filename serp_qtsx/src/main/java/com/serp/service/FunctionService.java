package com.serp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.FunctionDao;
import com.serp.entity.FunctionEntity;
import com.serp.model.Function;

/**
 * 
 * @author PhiTT
 *
 */
@Service("functionService")
public class FunctionService {

	@Autowired
	FunctionDao dao;
	
	private static final Log log = LogFactory.getLog(FunctionService.class);
	
	/**
	 * This method is used to get a function by it's id.
	 * @param functionId
	 * @return a function
	 */
	public Function findById(String functionId){
		log.info(String.format("listAllFuncOfRoleById with param 'roleId' in class: %s", getClass()));
		try{
			return dao.findById(functionId);
		}catch(Exception e){
			log.error(String.format("findById with param 'id' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This method is used to get a list of all functions in database
	 * @return List<FunctionEntity>
	 */
	public List<FunctionEntity> getAllFunctionEntity(){
		log.info(String.format("getAllFunctionEntity in class: %s", getClass()));
		try{
			List<Function> lstFunction= dao.getAllFunction();
			
			FunctionEntity en;
			List<FunctionEntity> lst= new ArrayList<FunctionEntity>();
			for (Function function : lstFunction) {
				en= new FunctionEntity();
				en.setFunctionId(function.getFunctionId());
				en.setFunctionName(function.getFunctionName());
				en.setDescription(function.getDescription());
				
				lst.add(en);
			}
			log.debug("getAllFunctionEntity successfully");
			return lst;
		}catch(Exception e){
			log.error(String.format("getAllFunctionEntity in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
