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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.serp.entity.ElementEntity;
import com.serp.entity.MaterialEntity;
import com.serp.entity.StockRequisitionDetailsEntity;
import com.serp.model.LimitInventory;
import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;
import com.serp.model.User;
import com.serp.service.LimitInventoryService;
import com.serp.service.MaterialService;
import com.serp.service.StockRequisitionService;
import com.serp.service.UserLoginService;

/**
 * The Class StockRequisitionController.
 *
 * @author KhangNDD
 */
@Controller
@RequestMapping("/stockRequisition")
public class StockRequisitionController {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(StockRequisitionController.class.getSimpleName());

	/** The temporary. */
	private StockRequisition temporary = new StockRequisition();

	/** The list of deleted id. */
	private List<Integer> deleteIds = new ArrayList<Integer>();

	/** The formatter. */
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	/** The current user. */
	User currentUser = new User();

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The StockRequisition service. */
	@Autowired
	StockRequisitionService srService;

	/** The Material service. */
	@Autowired
	MaterialService mService;

	/** The LimitInventory service. */
	@Autowired
	LimitInventoryService liService;

	/** The UserLogin service. */
	@Autowired
	UserLoginService ulService;

	/**
	 * Handle request.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("StockRequisitionController - handleRequest starting");
		response.setContentType("text/html");

		try {
			List<StockRequisition> srList = new ArrayList<StockRequisition>();
			srList = srService.findAll();
			request.setAttribute("srList", srList);

			currentUser = ulService.findById(getPrincipal());
			request.setAttribute("currentUserFunctions", currentUser.getRole().getFunctions());
		} catch (NullPointerException ex) {
			log.error("NullPointerException, message: " + ex.getMessage());
			return "redirect:/errorPage";
		}

		return "stockRequisition/stockRequisitionList";
	}

	/**
	 * Gets the all materials.
	 *
	 * @return the all materials
	 */
	@RequestMapping(value = "/materialElementJsonList", produces = "application/json", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getAllMaterials() {
		log.info("StockRequisitionController - getAllMaterials starting");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			List<MaterialEntity> materials = srService.findAllMaterialEntity();
			List<ElementEntity> elements = srService.findAllElementEntity();

			result.put("materials", materials);
			result.put("elements", elements);
		} catch (Exception ex) {
			log.error("Error at StockRequisitionController - method: getAllMaterials");
		}

		return result;
	}

	/**
	 * Gets the all stock requisition details.
	 *
	 * @param locale
	 *            the locale
	 * @param session
	 *            the session
	 * @return the all stock requisition details
	 */
	@RequestMapping(value = "/srdJsonList", produces = "application/json", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getAllStockRequisitionDetails(Locale locale, HttpSession session) {
		log.info("StockRequisitionController - getAllStockRequisitionDetails starting");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// Convert All StockRequisitionDetails Model to Entity
			List<StockRequisitionDetailsEntity> srdes = new ArrayList<StockRequisitionDetailsEntity>();
			Iterator<StockRequisitionDetails> iterator = temporary.getStockRequisitionDetailses().iterator();
			while (iterator.hasNext()) {
				StockRequisitionDetails srd = iterator.next();
				StockRequisitionDetailsEntity srde = srService.convertModelToEntity(srd);

				srdes.add(srde);
			}

			result.put("srdes", srdes);

			// Send message code back to view
			result.put("deleteBtn", messageSource.getMessage("stockRequisition.delete", null, locale));
			result.put("actionBtn", messageSource.getMessage("stockRequisition.action", null, locale));
			result.put("updateBtn", messageSource.getMessage("stockRequisition.update", null, locale));
		} catch (Exception ex) {
			log.error("Error at StockRequisitionController - method: getAllStockRequisitionDetails, message: "
					+ ex.getMessage());
			System.err.println("Error at StockRequisitionController - method: getAllStockRequisitionDetails, message: "
					+ ex.getMessage());
		}

		return result;
	}

	/**
	 * Handle add request.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String handleAddRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("StockRequisitionController - handleAddRequest");
		response.setContentType("text/html");

		try {
			int liId = Integer.parseInt(request.getParameter("liId"));
			LimitInventory limitInventory = liService.findById(liId);

			temporary = srService.getDataFromLimitInventory(limitInventory);

			request.setAttribute("sr", temporary);
			request.setAttribute("srde", new StockRequisitionDetailsEntity());
		} catch (Exception e) {
			log.error("Error at method: handleAddRequest, message: " + e.getMessage());
			return "redirect:/errorPage";
		}

		return "stockRequisition/addOrEditStockRequisition";
	}

	/**
	 * Direct to stock requisition details.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = { "/details" }, method = RequestMethod.GET)
	public String directToStockRequisitionDetails(HttpServletRequest request, HttpServletResponse response) {
		log.info("StockRequisitionController - directToStockRequisitionDetails");
		response.setContentType("text/html");

		try {
			int requisitionId = Integer.parseInt(request.getParameter("id"));

			// Clear and set new temporary object
			temporary = new StockRequisition();
			temporary = srService.findById(requisitionId);

			// initialize new List of deleted id
			deleteIds = new ArrayList<Integer>();
		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
			return "redirect:/errorPage";
		} catch (Exception ex) {
			log.fatal("Error at method: directToStockRequisitionDetails - StockRequisitionController, message: "
					+ ex.getMessage());
			return "redirect:/errorPage";
		} finally {
			request.setAttribute("sr", temporary);
		}

		return "stockRequisition/stockRequisitionDetails";
	}

	/**
	 * Handle details page actions.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param locale
	 *            the locale
	 * @return the string
	 */
	@RequestMapping(value = { "/detailsAction" }, method = RequestMethod.GET)
	public String handleDetailsPageActions(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		log.info("StockRequisitionController - handleDetailsPageActions");
		response.setContentType("text/html");

		try {
			String action = request.getParameter("btnAction");

			if (messageSource.getMessage("stockRequisition.exit", null, locale).equals(action)) {
				log.debug("Exit action");

				// Clear old object
				temporary = new StockRequisition();

				// initialize new List of deleted id
				deleteIds = new ArrayList<Integer>();

				return "redirect:/stockRequisition/list";
			} else if (messageSource.getMessage("stockRequisition.update", null, locale).equals(action)) {
				request.setAttribute("sr", temporary);
				// request.setAttribute("stockRequisition", new
				// StockRequisitionDetails());
				request.setAttribute("srde", new StockRequisitionDetailsEntity());

				return "stockRequisition/addOrEditStockRequisition";
			} else if (messageSource.getMessage("stockRequisition.delete", null, locale).equals(action)) {
				log.debug("Delete action");

				if (srService.delete(temporary.getRequisitionId())) {
					log.info("Delete successful");
				} else {
					log.error("Delete failed");
				}

				return "redirect:/stockRequisition/list";
			}
		} catch (Exception e) {
			log.error("Error at handleDetailsPageActions, message: " + e.getMessage());
			return "redirect:/errorPage";
		}

		return "redirect:/stockRequisition/details";
	}

	/**
	 * Direct to examine page.
	 *
	 * @param response
	 *            the response
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = { "/examine" }, method = RequestMethod.GET)
	public String directToExaminePage(HttpServletResponse response, HttpServletRequest request) {
		log.info("StockRequisitionController - directToExaminePage");
		response.setContentType("text/html");

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			// Check to know if Factory Manager or Head of Finanacial Accounting
			// Department are logging in
			Integer loginRole = 0; // Default: Factory Manager are logging in

			// Head of Financial Accounting Department are logging in
			if ("TPTCKT".equals(ulService.findById(getPrincipal()).getRole().getRoleId())) {
				loginRole = 1;
			} else if ("TX".equals(ulService.findById(getPrincipal()).getRole().getRoleId())) {
				loginRole = 0; // Factory Manager role
			} else {
				loginRole = 2; // Admin role
			}
			request.setAttribute("loginRole", loginRole);

			// Clear temporary object
			temporary = new StockRequisition();
			temporary = srService.findById(id);

			// initialize new List of deleted id
			deleteIds = new ArrayList<Integer>();
		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
			return "redirect:/errorPage";
		} catch (Exception ex) {
			log.fatal("Error at method: directToExaminePage - StockRequisitionController, message: " + ex.getMessage());
			return "redirect:/errorPage";
		} finally {
			request.setAttribute("sr", temporary);
		}

		return "stockRequisition/examineStockRequisition";
	}

	/**
	 * Handle examine page actions.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param locale
	 *            the locale
	 * @return the string
	 */
	@RequestMapping(value = { "/examineAction" }, method = RequestMethod.POST)
	public String handleExaminePageActions(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		log.info("StockRequisitionController - handleExaminePageActions");
		response.setContentType("text/html");

		try {
			String action = request.getParameter("btnAction");

			if (messageSource.getMessage("stockRequisition.save", null, locale).equals(action)) {
				log.debug("Save action");
				currentUser = ulService.findById(getPrincipal());

				// If Factory Mananger are loging in
				if ("TX".equals(ulService.findById(getPrincipal()).getRole().getRoleId())) {
					String fmNote = request.getParameter("fm_note");
					Integer fmStatus = 0;
					if (null != request.getParameter("fm_status")) {
						fmStatus = Integer.parseInt(request.getParameter("fm_status"));
					}

					// If status has changed, then set today for sign date and
					// update status in Stock Requisition
					if (fmStatus != temporary.getFactoryManagerStatus()) {
						temporary.setFactoryManagerStatus(fmStatus);
						temporary.setFactoryManagerSignDate(new Date());
						temporary.setFactoryManagerComment(fmNote);

						// Set Factory Manager with current login user
						temporary.setUserByFactoryManager(currentUser);

						// Set current user as last modified user
						temporary.setUserByLastModifiedUser(currentUser);

						// save to datatabase
						srService.saveOrUpdate(temporary, deleteIds);
					}
				} else if ("TPTCKT".equals(ulService.findById(getPrincipal()).getRole().getRoleId())) {
					// If Head of Financial Accounting Department are loging in
					String hfadNote = request.getParameter("hfad_note");
					Integer hfadStatus = 0;
					if (null != request.getParameter("hfad_status")) {
						hfadStatus = Integer.parseInt(request.getParameter("hfad_status"));
					}

					// If status has changed, then set today for sign date and
					// update status in Stock Requisition
					if (hfadStatus != temporary.getHfadStatus()) {
						temporary.setHfadStatus(hfadStatus);
						temporary.setHfadSignDate(new Date());
						temporary.setHfadComment(hfadNote);

						// Set Head of Financial Accounting Department with
						// current
						// login user
						temporary.setUserByHfad(currentUser);

						// Set current user as last modified user
						temporary.setUserByLastModifiedUser(currentUser);

						// save to datatabase
						srService.saveOrUpdate(temporary, deleteIds);
					}
				}

				log.debug("Update status successful");

				// Check to know if Factory Manager or Head of Finanacial
				// Accounting
				// Department are logging in
				int loginRole = 0; // Factory Manager are logging in

				// Head of Financial Accounting Department are logging in
				if ("TPTCKT".equals(ulService.findById(getPrincipal()).getRole().getRoleId())) {
					loginRole = 1;
				}
				request.setAttribute("loginRole", loginRole);

				// Send feedback to view
				request.setAttribute("saveStatus", true);
				request.setAttribute("sr", temporary);
				return "stockRequisition/examineStockRequisition";
			} else if (messageSource.getMessage("stockRequisition.exit", null, locale).equals(action)) {
				// Clear old object
				temporary = new StockRequisition();

				// initialize new List of deleted id
				deleteIds = new ArrayList<Integer>();
			}
		} catch (Exception e) {
			log.error("Error at method: handleExaminePageActions - message: " + e.getMessage());
			System.err.println("Error at method: handleExaminePageActions - message: " + e.getMessage());
			request.setAttribute("saveStatus", false);
			return "redirect:/errorPage";
		}

		return "redirect:/stockRequisition/list";
	}

	/**
	 * Handle add stock requisition details.
	 *
	 * @param sDetails
	 *            the s details
	 * @param locale
	 *            the locale
	 * @return the map
	 */
	@RequestMapping(value = {
			"/addDetails" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> handleAddStockRequisitionDetails(
			@RequestBody StockRequisitionDetailsEntity sDetails, Locale locale) {
		log.info("StockRequisitionController - handleAddStockRequisitionDetails");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			StockRequisitionDetails srd = srService.convertEntityToModel(sDetails);

			// Add detail to temporary StockRequisition Object
			temporary.getStockRequisitionDetailses().add(srd);
			result.put("addStatus", true);

			log.debug("Add detail to temporary object successful");
			System.out.println("Add detail to temporary object successful");
		} catch (Exception ex) {
			log.error("Failed to add details to temporary list, message: " + ex.getMessage());
			System.err.println("Failed to add details to temporary list");
			result.put("addStatus", false);
		}

		return result;
	}

	/**
	 * Handle save btn in add or edit view.
	 *
	 * @param response
	 *            the response
	 * @param request
	 *            the request
	 * @param locale
	 *            the locale
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
	@RequestMapping(value = { "/saveDetails" }, method = RequestMethod.POST)
	public String handleSaveBtnInAddOrEditView(HttpServletResponse response, HttpServletRequest request, Locale locale)
			throws ParseException {
		log.info("StockRequisitionController - handleSaveBtnInAddOrEditView");
		response.setContentType("text/html");

		try {
			// Get parameters from view
			String action = request.getParameter("btnAction");

			if (messageSource.getMessage("stockRequisition.save", null, locale).equals(action)) {
				log.debug("Save action");

				int requisitionId = request.getParameter("srId").isEmpty() ? -1
						: Integer.parseInt(request.getParameter("srId"));
				Date dateWanted = formatter.parse(request.getParameter("dateWanted"));
				String department = request.getParameter("department");
				String reason = request.getParameter("reason");
				String rs = request.getParameter("recommendSupplier");

				// Finding if user add new or update
				int flag = 0; // flag = 0 is update, = 1 is add new
				if (temporary != null) {
					if (requisitionId == -1) {
						flag = 1;
					}
				} else {
					flag = 1;
				}

				// Handle add new
				if (flag == 1) {
					log.debug("User add new Stock Requisition");
					temporary.setCreatedDate(new Date());

					// Set current user to creator
					currentUser = ulService.findById(getPrincipal());
					temporary.setUserByCreator(currentUser);
				} else if (flag == 0) { // Handle update
					log.debug("User update old Stock Requisition");

					// reset status of hfad and factory manager back to Approve
					// Awaiting
					temporary.setHfadStatus(0);
					temporary.setHfadComment("");
					temporary.setHfadSignDate(null);
					temporary.setUserByHfad(null);

					temporary.setFactoryManagerStatus(0);
					temporary.setFactoryManagerSignDate(null);
					temporary.setFactoryManagerComment("");
					temporary.setUserByFactoryManager(null);
				}

				temporary.setDateWanted(dateWanted);
				temporary.setDepartment(department);
				temporary.setReason(reason);
				temporary.setRecommendSupplier(rs);

				// Set current user as last modified user
				temporary.setUserByLastModifiedUser(ulService.findById(getPrincipal()));

				// Save to database
				boolean saveStatus = srService.saveOrUpdate(temporary, deleteIds);

				if (saveStatus) {
					log.debug("Save or Update successful");
				} else {
					throw new Exception();
				}

				request.setAttribute("saveStatus", saveStatus);
			} else if (messageSource.getMessage("stockRequisition.exit", null, locale).equals(action)) {
				log.debug("Exit action");
				return "redirect:/stockRequisition";
			}
		} catch (NullPointerException ne) {
			log.error("NullPointerException - Failed to save or update stock requisition details, message: "
					+ ne.getMessage());
			return "redirect:/errorPage";
		} catch (Exception ex) {
			log.error("Failed to save or update stock requisition details, message: " + ex.getMessage());
			System.err.println("Failed to save or update stock requisition details, message: " + ex.getMessage());
			return "redirect:/errorPage";
		}

		request.setAttribute("sr", temporary);
		return "stockRequisition/stockRequisitionDetails";
	}

	/**
	 * Delete details.
	 *
	 * @param detailsId
	 *            the details id
	 * @return the map
	 */
	@RequestMapping(value = "/deleteDetails/{detailsId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> deleteDetails(@PathVariable Integer detailsId) {
		log.info("StockRequisitionController - deleteDetails");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			List<StockRequisitionDetails> srdList = new ArrayList<StockRequisitionDetails>(
					temporary.getStockRequisitionDetailses());
			// Get the item will be removed
			StockRequisitionDetails removedItem = srdList.get(detailsId - 1);

			// Add id of deleted item to list
			// If id of detail not exist then add -1 to list
			if (null == removedItem.getStockRequisitionDetailsId() || removedItem.getStockRequisitionDetailsId() < 0) {
				deleteIds.add(-1);
			} else {
				deleteIds.add(removedItem.getStockRequisitionDetailsId());
			}

			// Remove detail in list by object
			srdList.remove(removedItem);

			Set<StockRequisitionDetails> srds = new HashSet<StockRequisitionDetails>(srdList);
			temporary.setStockRequisitionDetailses(srds);

			// Send delete status back to view
			result.put("deleteStatus", true);
			log.debug("deleteDetails successfully");
		} catch (Exception e) {
			log.error("StockRequisitionController - deleteDetails failed, message: " + e.getMessage());
			System.err.println("StockRequisitionController - deleteDetails failed, message: " + e.getMessage());
			result.put("deleteStatus", false);
		}

		return result;
	}

	/**
	 * Gets the details.
	 *
	 * @param detailsId
	 *            the details id
	 * @return the details
	 */
	@RequestMapping(value = "/details/{detailsId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getDetails(@PathVariable Integer detailsId) {
		log.info("StockRequisitionController - getDetails");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			List<StockRequisitionDetails> srdList = new ArrayList<StockRequisitionDetails>(
					temporary.getStockRequisitionDetailses());

			// Get detail in list by index
			StockRequisitionDetailsEntity srde = srService.convertModelToEntity(srdList.get(detailsId - 1));

			result.put("detail", srde);
			result.put("status", true);
			log.debug("getDetails successfully");
		} catch (Exception e) {
			log.error("StockRequisitionController - getDetails failed, message: " + e.getMessage());
			System.err.println("StockRequisitionController - getDetails failed, message: " + e.getMessage());
			result.put("status", false);
		}

		return result;
	}

	/**
	 * Handle update stock requisition details.
	 *
	 * @param sDetails
	 *            the s details
	 * @param locale
	 *            the locale
	 * @param index
	 *            the index
	 * @return the map
	 */
	@RequestMapping(value = {
			"/editDetails/{index}" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> handleUpdateStockRequisitionDetails(
			@RequestBody StockRequisitionDetailsEntity sDetails, Locale locale, @PathVariable Integer index) {
		log.info("StockRequisitionController - handleUpdateStockRequisitionDetails");
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			StockRequisitionDetails srd = srService.convertEntityToModel(sDetails);

			List<StockRequisitionDetails> srdList = new ArrayList<StockRequisitionDetails>(
					temporary.getStockRequisitionDetailses());
			
			// Set current id to new object
			srd.setStockRequisitionDetailsId(srdList.get(index).getStockRequisitionDetailsId());
			
			// Replace old detail with the new one in list by index
			srdList.set(index, srd);

			Set<StockRequisitionDetails> srds = new HashSet<StockRequisitionDetails>(srdList);
			temporary.setStockRequisitionDetailses(srds);
			result.put("editStatus", true);

			log.debug("Update detail to temporary object successful");
			System.out.println("Update detail to temporary object successful");
		} catch (Exception ex) {
			log.error("Failed to update details to temporary list, message: " + ex.getMessage());
			System.err.println("Failed to update details to temporary list");
			result.put("editStatus", false);
		}

		return result;
	}

	/**
	 * This function return userId of the current login user.
	 *
	 * @return userid
	 */
	private String getPrincipal() {
		log.info(String.format("getPrincipal in class: %s", getClass()));
		try {
			log.debug("get current login user and return it's username");
			String userName = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}

			System.out.println(userName);
			log.debug("getPrincipal successfully");
			return userName;
		} catch (Exception e) {
			log.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
			throw e;
		}
	}
}
