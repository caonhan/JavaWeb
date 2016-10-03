package com.serp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.serp.entity.ElementEntity;
import com.serp.entity.LimitInventoryDetailEntity;
import com.serp.entity.MaterialEntity;
import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;
import com.serp.service.LimitInventoryService;

@Controller
@RequestMapping("/limitInventory")
public class LimitInventoryController {
	private static final Log log = LogFactory.getLog(LimitInventoryController.class);

	private LimitInventory temp = new LimitInventory();
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	MessageSource messageSource;

	@Autowired
	LimitInventoryService liService;

	@RequestMapping(value = { "", "/listLimitInventory" }, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		log.debug("LimitInventoryController - handleRequest");
		response.setContentType("text/html");

		List<LimitInventory> liList = new ArrayList<LimitInventory>();

		try {
			liList = liService.findAll();
			request.setAttribute("liList", liList);
		} catch (NullPointerException ex) {
			log.error("NullPointerException, message: " + ex.getMessage());
		}
		return "limitInventory/limitInventoryList";
	}

	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET)
	public ModelAndView directToLimitInventoryDetail(HttpServletRequest request, HttpServletResponse response) {
		log.debug("LimitInvntoryController - directToLimitInventoryDetail");
		response.setContentType("text/html");

		try {
			int limitInventoryId = Integer.parseInt(request.getParameter("id"));
			temp = liService.findById(limitInventoryId);
		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
		} catch (Exception e) {
			log.fatal("Error at method: directToStockRequisitionDetails - StockRequisitionController, message: "
					+ e.getMessage());
		} finally {
			request.setAttribute("li", temp);
		}
		return new ModelAndView("limitInventory/limitInventoryDetail");

	}

	@RequestMapping(value = { "/detailAction" })
	public String handleDetailPageActions(HttpServletRequest request, HttpServletResponse response, Locale locale) { // Locale??
		log.debug("StockRequisitionController - handleDetailsPageActions");
		response.setContentType("text/html");
		String action = request.getParameter("btnAction");

		if (messageSource.getMessage("limitInventory.exit", null, locale).equals(action)) {
			log.debug("Exit action");
			temp = new LimitInventory();

			return "redirect:/limitInventory/listLimitInventory";
		}

		else if (messageSource.getMessage("limitInventory.update", null, locale).equals(action)) {
			request.setAttribute("li", temp);
			request.setAttribute("lide", new LimitInventoryDetailEntity());

			return "limitInventory/addOrEditLimitInventory";
		} else if (messageSource.getMessage("limitInventory.delete", null, locale).equals(action)) {
			log.debug("Delete action");

			if (liService.delete(temp.getLimitInventoryId())) {
				log.info("Delete successful");
			} else {
				log.info("Delete failed");
			}

			return "redirect:/limitInventory/listLimitInventory";
		}

		else if (messageSource.getMessage("limitInventory.download", null, locale).equals(action)) {
			log.debug("Downloading Limit Inventory with id: " + temp.getLimitInventoryId());
			System.out.println("Downloading!!!");
		}

		return "redirect:/limitInventory/detail";
	}

	@RequestMapping(value = "/materialElementJSonList", produces = "application/json", method = RequestMethod.POST) // why
																													// POST?
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getAllMaterials() {
		log.info("LimitInventoryController - getAllMaterials starting");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			List<MaterialEntity> materials = liService.findAllMaterialEntity();
			List<ElementEntity> elements = liService.findAllElementEntity();

			result.put("materials", materials);
			result.put("elements", elements);
		} catch (Exception e) {
			log.error("Error at LimitInventoryController - method: getAllMaterials");
		}

		return result;
	}

	@RequestMapping(value = "/lidJSonList", produces = "application/json", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getAllLimitInventoryDetail(Locale locale) {
		log.info("LimitInventoryController - getAllLimitInventoryDetail starting");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// convert all limitInventoryDetail model to entity
			Set<LimitInventoryDetailEntity> lides = new HashSet<LimitInventoryDetailEntity>();
			Iterator<LimitInventoryDetail> iterator = temp.getLimitInventoryDetails().iterator();

			while (iterator.hasNext()) {
				LimitInventoryDetail lid = iterator.next();
				LimitInventoryDetailEntity lide = liService.convertModelToEntity(lid);

				lides.add(lide);
			}
			result.put("lides", lides);

			// send massege code back view
			result.put("deleteBtn", messageSource.getMessage("limitInventory.delete", null, locale));
			result.put("actionBtn", messageSource.getMessage("limitInventory.action", null, locale));
			result.put("updateBtn", messageSource.getMessage("limitInventory.update", null, locale));

		} catch (Exception e) {
			log.error("Error at LimitInventoryController- method: getAllLimitInventoryDetail, message: "
					+ e.getMessage());
			System.err.println("Error at LimitInventoryController- method: getAllLimitInventoryDetail, message: "
					+ e.getMessage());
		}
		return result;

	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String handleAddRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		request.setAttribute("limitInventory", new LimitInventory());

		return "limitInventory/addOrEditLimitInventory";

	}

	@RequestMapping(value = {
			"/addDetail" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> handleAddLimitInventoryDetail(
			@RequestBody LimitInventoryDetailEntity liDetail, Locale locale) {
		log.info("LimitInventoryController - handleAddLimitInventoryDetail");
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println("Entity From AJAX: " + liDetail.toString());

		try {
			LimitInventoryDetail lid = liService.convertEntityToModel(liDetail);

			// Add detail to temp LimitInventory Object
			temp.getLimitInventoryDetails().add(lid);
			result.put("addStatus", true);

			// Send full detail json entity back to view
			result.put("lid", liService.convertModelToEntity(lid));

			// Send message code back to view
			result.put("deleteBtn", messageSource.getMessage("limitInventory.delete", null, locale));
			result.put("actionBtn", messageSource.getMessage("limitInventory.action", null, locale));
			result.put("updateBtn", messageSource.getMessage("limitInventory.update", null, locale));

			log.debug("Add detail temp object successful");
			System.out.println("Add detail temp object successful");

		} catch (Exception ex) {
			log.error("Failed to add detail to temp list, message: " + ex.getMessage());
			System.err.println("Failed to add detail to temp list");
			result.put("addStatus", false);
		}

		return result;
	}

	@RequestMapping(value = { "/saveDetail" }, method = RequestMethod.POST)
	public String handleSaveBtnAddOrEditView(HttpServletResponse response, HttpServletRequest request, Locale locale)
			throws ParseException {
		response.setContentType("text/html");
		log.info("LimitInventoryController - handleSaveBtnAddOrEditView");

		try {
			// Get parameters from view
			String action = request.getParameter("btnAction");

			int limitInventoryId = request.getParameter("liId").isEmpty() ? -1
					: Integer.parseInt(request.getParameter("liId"));
			Date dateWanted = formatter.parse(request.getParameter("dateWanted"));
			int timeModify = Integer.parseInt(request.getParameter("timeModify"));
			
			
			if(messageSource.getMessage("limitInventory.save", null, locale).equals(action))
			{
				log.debug("Save action");
				// Finding if user add new or update
				int flag = 0; // flag = 0 is update, = 1 is add new
				if(temp != null){
					if(limitInventoryId == -1){
						flag = 1;
					}
				}else{
					flag = 1;
				}
				
				// Handle add new
				if(flag == 1){
					log.debug("User add new Limit Inventory");
					temp.setCreatedDate(new Date());
					//temp.setLastModifiedDate(new Date());
				}
				// Handle update
				else if(flag == 0){
					log.debug("User update old Limit Inventory");
					//temp.setLastModifiedDate(new Date());
					
					// reset status of factoryManager and directorate back to Approve Awaiting
					temp.setFactoryManagerStatus(0);
					temp.setUserByFactoryManagerId(null);
					temp.setFactoryManagerSignDate(null);
					temp.setFactoryManagerComment(null);
					
					temp.setDirectorateStatus(0);
					temp.setUserByDirectorateId(null);
					temp.setDirectorateSignDate(null);
					temp.setDirectorateComment(null);
				}
				
				temp.setDateWanted(dateWanted);
				temp.setTimeModify(timeModify);
				
				boolean saveStatus = liService.saveOrUpdate(temp);
				
				if(saveStatus){
					log.debug("Save or Update successfull");
					System.out.println("Model: " + temp.toString() + ", save or update successful");
				}else{
					throw new Exception();
				}
				
				request.setAttribute("saveStatus", saveStatus);
			}else if(messageSource.getMessage("limitInventory.exit", null, locale).equals(action)){
				log.debug("Exit action");
				return "redirect:/limitInventory";
			}
			
		} catch (Exception e) {
			log.error("Failed to save or update limit inventory detail, message: " + e.getMessage());
			System.err.println("Failed to save or update limit inventory detail, message: " + e.getMessage());
		}
		
		request.setAttribute("li", temp);
		return "limitInventory/limitInventoryDetail";
	}
	
	@RequestMapping(value = "/deleteDetail/{detailId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> deleteDetail(@PathVariable Integer detailId){
		log.info("LimitInventoryController - deleteDetail");
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			List<LimitInventoryDetail> lidList = new ArrayList<LimitInventoryDetail>(temp.getLimitInventoryDetails());
			//lidList.addAll(temp.getLimitInventoryDetails());
			lidList.remove(detailId - 1);
			
			Set<LimitInventoryDetail> lids = new HashSet<LimitInventoryDetail>(lidList);
			temp.setLimitInventoryDetails(lids);
			
			result.put("deleteStatus", true);
			log.debug("deleteDetail successful");
			System.out.println("deleteDetail successful " + detailId);
		}catch (Exception e) {
			log.error("LimitInventoryController - deleteDetail failed, message: " + e.getMessage());
			System.err.println("LimitInventoryController - deleteDetail failed, message: " + e.getMessage());
			result.put("deleteStatus", false);
		}
		return result;
		
	}
}
