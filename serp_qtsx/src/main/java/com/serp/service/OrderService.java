package com.serp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.CustomerHome;
import com.serp.dao.OrdersDAO;
import com.serp.dao.StatusDAO;
import com.serp.dao.UserLoginDao;
import com.serp.entity.OrderEntity;
import com.serp.model.Orders;

@Service("OrderService")
public class OrderService {
	
	private static final Log log = LogFactory.getLog(OrderService.class);
	
	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	UserLoginDao userDao; 
	
	@Autowired
	CustomerHome cusDao;	
	
	@Autowired
	StatusDAO sttDao;
	
	/**
	 * This function is used get all order
	 * @return list<order>
	 */
	public List<OrderEntity> getAllOrder(){
		log.info(String.format("getAllOrder in class: %s", getClass()));
		try{
			List<Orders> lstOrder = ordersDAO.getAllOrder();
			OrderEntity orderEn;
			List<OrderEntity> lstOrderEntity = new ArrayList<OrderEntity>();
			for (Orders or : lstOrder) {
				orderEn = new OrderEntity();
				orderEn.setOrderId(or.getOrderId());
				orderEn.setCustomer(or.getCustomer().getCustomerId());
				orderEn.setStatusId(or.getStatus()==null?orderEn.getStatusId():or.getStatus().getStatusId());
				orderEn.setStatusName(or.getStatus()==null?orderEn.getStatusName():or.getStatus().getStatusName());
				orderEn.setUserByApprover(or.getUserByApprover()==null ?"":or.getUserByApprover().getUserId());
				//orderEn.setUserByUserId(or.getUserByUserId().getUserId()==null?"":or.getUserByUserId().getUserId());
				orderEn.setUserByUserId(or.getUserByUserId().getUserId());
				orderEn.setProjectName(or.getProjectName());
				orderEn.setCreateDate(or.getCreateDate());
				orderEn.setOrderContent(or.getOrderContent()==null?"":or.getOrderContent());
				//orderEn.setPossibility(or.getPossibility()==null?1:or.getPossibility());
				orderEn.setPossibility(or.getPossibility());
				orderEn.setJudgingContent(or.getJudgingContent()==null?"":or.getJudgingContent());
				orderEn.setApprovalContent(or.getApprovalContent()==null?"":or.getApprovalContent());
				orderEn.setProductName(or.getProductName());
				orderEn.setAmountOfProduct(or.getAmountOfProduct());
				orderEn.setDueDate(or.getDueDate());
				
				lstOrderEntity.add(orderEn);
		}
		log.debug("getAllOrder successfully");	
		return lstOrderEntity;
		}catch(Exception e){
			log.error(String.format("getAllOrder in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to add new order into database
	 * @param orderEn
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addOrder(OrderEntity orderEn, String userId){
		log.info(String.format("addOrder in class: %s", getClass()));
		try {
			Orders or= new Orders();		
			or.setCustomer(cusDao.findById(orderEn.getCustomer()));
			or.setStatus(sttDao.findById(1));
			or.setPossibility(3);		
			//get current user
			or.setUserByUserId(userDao.findById(userId));
			or.setProjectName(orderEn.getProjectName());
			or.setOrderId(orderEn.getOrderId());
			or.setCreateDate(orderEn.getCreateDate());
			or.setOrderContent(orderEn.getOrderContent());
			or.setProductName(orderEn.getProductName());
			or.setAmountOfProduct(orderEn.getAmountOfProduct());
			or.setDueDate(orderEn.getDueDate());
			ordersDAO.persist(or);
			log.debug("add new Order into database successfully");
			return true;
		}catch(Exception e){
			log.debug("add new Order into database fail");
			System.err.println("add new Order into database fail, method addOrder(), class OrderService");
			return false;
		}
	}
	
	/**
	 * This function check if there is an order with orderId existed in database
	 * @param orderId
	 * @return false if not existed, true if existed
	 */
	public boolean isOrderExistedById(String orderId){
		if(null==ordersDAO.findById(orderId))
			return false;
		return true;
	}
	
	/**
	 * This function is used to delete an order in database.
	 * @param orderId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteOrder(String orderId){
		log.info(String.format("deleteOrder with param 'roleId' in class: %s", getClass()));	
		try{
			Orders or= ordersDAO.findById(orderId);
			ordersDAO.delete(or);
			return true;
		}catch(Exception e){
			return false;
		}
	}
/***
	 * @author vuong-bt
	 * @param id
	 * @return Orders or null
	 */

	public Orders findById(String id) {
		log.debug("in orders service find by id");
		try {
			return ordersDAO.findById(id);
		}
//		catch (NullPointerException ne) {
//			log.error("findbyid service orders " + id + " err: " + ne.getMessage());
//		}
//		catch (Exception ex) {
//			log.fatal("findbyid fatal " + ex.getMessage());
//		}
//		return null;
		catch(Exception e){
			log.error(String.format("findById with param 'id' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}	
		/**
	 * This function find a OrderEntity by id.
	 * @param OrderId
	 * @return a Order
	 */
	public OrderEntity findOrderEntityById(String orderId){
		log.info(String.format("findOrderEntityById with param 'orderId' in class: %s", getClass()));
		try{
			OrderEntity en= new OrderEntity();
			Orders order= ordersDAO.findById(orderId);
			
			//field for NV
			en.setCustomer(order.getCustomer().getCustomerId());
			en.setStatusId(order.getStatus().getStatusId());
			en.setPossibility(order.getPossibility());		
			en.setUserByUserId(order.getUserByUserId().getUserId());
			en.setProjectName(order.getProjectName());
			en.setOrderId(order.getOrderId());
			en.setCreateDate(order.getCreateDate());
			en.setOrderContent(order.getOrderContent());
			en.setProductName(order.getProductName());
			en.setAmountOfProduct(order.getAmountOfProduct());
			en.setDueDate(order.getDueDate());
			
			//field edit for TX
			en.setApprovalContent(order.getApprovalContent()==null?"":order.getApprovalContent());
			en.setJudgingContent(order.getJudgingContent()==null?"":order.getApprovalContent());
			en.setUserByApprover(order.getUserByApprover()==null?"":order.getUserByApprover().getUserId());
			
			
			log.debug("findOrderEntityById successfully");
			return en;
		}catch(Exception e){
			log.error(String.format("findOrderEntityById with param 'orderId' in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
	
	/**
	 * This function is used to edit Order into database
	 * @param OrderEn
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editOrder(OrderEntity orderEn, String userId){
		log.info(String.format("editOrder with param 'OrderEn', 'userId' in class: %s", getClass()));
		try{
			Orders order= ordersDAO.findById(orderEn.getOrderId());
			
			order.setCustomer(cusDao.findById(orderEn.getCustomer()));
			order.getStatus().setStatusId(orderEn.getStatusId());
			order.setPossibility(orderEn.getPossibility());		
			//unable to edit User Create
			//order.setUserByUserId(userDao.findById(userId));
			order.setProjectName(orderEn.getProjectName());
			order.setOrderId(orderEn.getOrderId());
			order.setCreateDate(orderEn.getCreateDate());
			order.setOrderContent(orderEn.getOrderContent());
			order.setProductName(orderEn.getProductName());
			order.setAmountOfProduct(orderEn.getAmountOfProduct());
			order.setDueDate(orderEn.getDueDate());
			
			order.setApprovalContent(orderEn.getApprovalContent());
			order.setJudgingContent(orderEn.getJudgingContent());
			order.setUserByApprover(userDao.findById(userId));
		
			ordersDAO.attachDirty(order);
			log.debug("editOrder successfully");
			return true;
		}catch(Exception e){
			log.error(String.format("editOrder with param 'orderEn', 'userId' in class: %s has error: %s", getClass(), e.getMessage()));
			System.err.println(String.format("editOrder with param 'orderEn', 'userId' in class: %s has error: %s", getClass(), e.getMessage()));
			return false;
		}
	}
}
