package com.serp.controller;

import java.beans.PropertyEditorSupport;
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
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.serp.entity.ElementEntity;
import com.serp.entity.EstimateDetailEntity;
import com.serp.model.Element;
import com.serp.model.EstimateDetailValidator;
import com.serp.model.Estimate;
import com.serp.model.EstimateDetail;
import com.serp.model.Material;
import com.serp.model.User;
import com.serp.service.ElementService;
import com.serp.service.EstimateDetailService;
import com.serp.service.EstimateService;
import com.serp.service.MaterialService;
import com.serp.service.OrderService;
import com.serp.service.StatusService;
import com.serp.service.UserLoginService;

/***
 * 
 * @author vuong-bt
 *
 */
@Controller
@RequestMapping("/")
public class EstimateController {
    private static final Logger logger = Logger.getLogger(EstimateController.class);
    private static final String elementIdSession = "detailSelectedSession";
    private static final String detailListSession = "detailListSession";
    private static final String isApproveMode = "isApproveMode";
    private static final String isEditMode = "isEditMode";
    private String currentUser = "";
    @Autowired
    EstimateDetailValidator estimateDetailValidator;
    @Autowired
    MessageSource messageSource;
    @Autowired
    StatusService statusService;
    @Autowired
    EstimateService estimateService;
    @Autowired
    EstimateDetailService estimateDetailService;
    @Autowired
    MaterialService materialService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserLoginService userService;
    @Autowired
    ElementService elementService;

    /**
     * list all estimate
     * 
     * @param request
     * @param response
     * @return estimate view
     */

    @RequestMapping(value = { "/estimate" }, method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
	logger.info("in list estimate controller");
	response.setContentType("text/html");
	request.setAttribute("listEstimate", estimateService.list());
	currentUser = getPrincipal();
	User user = userService.findById(currentUser);
	request.setAttribute(isApproveMode, estimateService.hasApproveRole(user));
	request.setAttribute(isEditMode, estimateService.hasEditRole(user));
	return new ModelAndView("estimate/estimate");
    }

    /***
     * Handle get request to add new / update estimate
     * 
     * @param request,
     *            form
     * @param response
     * @return edit estimate view
     */
    @RequestMapping(value = { "/editEstimate" }, method = RequestMethod.GET)
    public String addEstimateRequest(HttpServletRequest request, HttpServletResponse response) {
	logger.info("get edit estimate view");
	response.setContentType("text/html");
	currentUser = getPrincipal();// get current logged in userName
	User user = userService.findById(currentUser);
	request.setAttribute(isApproveMode, estimateService.hasApproveRole(user));// check
										  // and
										  // get
										  // user's
										  // role
	request.setAttribute(isEditMode, estimateService.hasEditRole(user));
	String viewMode = request.getParameter("viewMode");// check if the mode
							   // is view
	if (estimateService.hasEditRole(user) || estimateService.hasApproveRole(user)) {
	    String eId = request.getParameter("estimateId");
	    int estimateId = 0;
	    try {
		estimateId = Integer.parseInt(eId);
	    } catch (Exception ne) {
	    }
	    Estimate estimate = new Estimate();
	    Set<EstimateDetail> set = new HashSet<EstimateDetail>();
	    try {
		if (estimateId == 0) {// add new estimate mode
		    logger.debug("Add new estimate");
		    String orderId = request.getParameter("orderId");
		    estimate.setOrders(orderService.findById(orderId));
		    estimate.setUserByEsCreatorId(user);
		    estimate.setStatus(statusService.findById(1));
		} else {// update an estimate
		    logger.debug("Update estimate, id = " + estimateId);
		    estimate = estimateService.findById(estimateId);
		    set = estimate.getEstimateDetail();
		    if ("viewMode".equals(viewMode) || estimate.getStatus().getStatusId() == 2) {
			request.setAttribute(isApproveMode, false);
			request.setAttribute(isEditMode, false);
		    } else {
			request.setAttribute(isApproveMode, estimateService.hasApproveRole(user));
			request.setAttribute(isEditMode, estimateService.hasEditRole(user));
		    }
		}
		// Prepare empty estimate detail object for edit form
		EstimateDetail ed = new EstimateDetail();
		ed.setElement(new Element());
		ed.setMaterial(new Material());
		ed.setEstimate(estimate);
		request.setAttribute("detail", ed);
		// get status list
		request.setAttribute("statusList", statusService.list());
		// get material list
		request.setAttribute("materialList", materialService.getAllMaterial());
		// send the estimate with filled info
		request.setAttribute("estimate", estimate);
		// send the list of detail
		request.getSession().setAttribute(detailListSession, set);
		request.getSession().removeAttribute(elementIdSession);
	    } catch (Exception ex) {
		logger.error("get add estimate view error, message: " + ex);
		throw ex;
	    }
	    return "estimate/editEstimate";
	} else
	    return "redirect:/errorPage";
    }

    /**
     * handle save or update estimate request
     * 
     * @param estimate
     * @param request
     * @return add status
     */

    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/saveEstimate" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> handleSaveEstimate(@ModelAttribute("estimate") Estimate estimate,
	    HttpServletRequest request, Locale locale) {
	logger.info("EstimateController - save estimate");
	currentUser = getPrincipal();
	User user = userService.findById(currentUser);
	Map<String, Object> result = new HashMap<String, Object>();
	String creatorId = request.getParameter("creatorId");
	if (!estimateService.hasApproveRole(user) && !creatorId.equals(user.getUserId())) {
	    // check user's role, if user has only edit role and current
	    // estimate was created by another, return error
	    result.put("addStatus", messageSource.getMessage("estimate.edit.permission", null, locale));
	    return result;
	}
	try {
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {
		EstimateDetail ed = iter.next();
		ed.setEstimate(estimate);
	    }
	    if (estimate.getEsId() == null || estimate.getEsId() == 0) {// add
									// new
									// mode
		estimate.setEsPublishedDate(new Date(new java.util.Date().getTime()));
	    } else {// update mode
		String date = request.getParameter("publishedDate");
		estimate.setEsPublishedDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	    }
	    String orderId = request.getParameter("orderId");
	    estimate.setOrders(orderService.findById(orderId));
	    estimate.setUserByEsCreatorId(userService.findById(creatorId));
	    estimate.setEstimateDetail(set);
	    String statusName = estimate.getStatus().getStatusName();
	    estimate.setStatus(statusService.findByName(statusName));
	    if (estimateService.hasApproveRole(user)) {
		estimate.setUserByEsApproverId(user);
		estimate.setEsApproveContent(request.getParameter("comment"));
	    } else {
		estimate.setStatus(statusService.findById(1));
		estimate.setUserByEsApproverId(null);
		estimate.setEsApproveContent("");
	    }
	    estimateService.saveOrUpdate(estimate);
	    logger.debug("save or Update estimate o.k.");
	    result.put("addStatus", messageSource.getMessage("estimate.save.ok", null, locale));
	} catch (Exception ex) {
	    logger.error("Error to save estimate Message: " + ex);
	    result.put("addStatus", messageSource.getMessage("estimate.save.error", null, locale));
	}
	return result;
    }

    /**
     * cancel editing an estimate
     * 
     * @param request
     */

    @RequestMapping(value = { "/cancelEditEstimate" }, method = RequestMethod.POST)
    public ModelAndView handleSaveEstimate(HttpServletRequest request) {
	logger.info("EstimateController - cancel save estimate");
	try {
	    request.getSession().removeAttribute(detailListSession);// remove
								    // temp data
								    // in
								    // session
	    logger.debug("back o.k.");
	} catch (Exception ex) {
	    logger.error("Error to cancel edit: " + ex);
	}
	return new ModelAndView("redirect:/estimate");
    }

    /**
     * delete an estimate
     * 
     * @param estimateId
     * @param request
     * @param locale
     * @return delete status
     */
    @RequestMapping(value = "/deleteEstimate/{estimateId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Map<String, Object> deleteEstimate(@PathVariable int estimateId, HttpServletRequest request,
	    Locale locale) {
	logger.info("in delete estimate estimateId = " + estimateId);
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    Estimate es = estimateService.findById(estimateId);
	    currentUser = getPrincipal();
	    User user = userService.findById(currentUser);
	    // check user's role and owner, can only delete estimate with status
	    // awaiting
	    if (!user.getUserId().equals(es.getUserByEsCreatorId().getUserId()) || es.getStatus().getStatusId() != 1) {
		result.put("deleteStatus", messageSource.getMessage("estimate.delete.wrong", null, locale));
		return result;
	    }
	    estimateService.delete(es);
	    result.put("deleteStatus", messageSource.getMessage("estimate.delete.ok", null, locale));
	} catch (Exception ex) {
	    logger.error("error to delete estimate with id = " + estimateId + " , message = " + ex);
	    result.put("deleteStatus", messageSource.getMessage("estimate.delete.fail", null, locale));
	}
	return result;
    }

    /*
     * Detail processing area
     */

    /**
     * This function is to check if inputValue already exist in db or not
     * 
     * @param inputValue
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkElementId/{inputValue}", produces = "application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Map<String, Object> checkElementId(@PathVariable String inputValue,
	    HttpServletRequest request) {
	logger.info("in checking input new elementId value = " + inputValue);
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    String elementId = (String) request.getSession().getAttribute(elementIdSession);
	    logger.info("elementIdSession = "+elementId);
	    Element element = elementService.findById(inputValue);
	    result.put("checkStatus", (element == null || (elementId != null && element.getEId().equals(elementId)))
		    ? "check.pass" : "check.fail");
	} catch (Exception ex) {
	    logger.error("error to check element id, message = " + ex);
	    result.put("checkStatus", "check.fail");
	}
	return result;
    }

    /**
     * get detail list of current estimate
     * 
     * @param request
     * @return list<detail>
     */

    @RequestMapping(value = "/getDetailList", produces = "application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Map<String, Object> getAllDetail(HttpServletRequest request) {
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    List<EstimateDetailEntity> details = new ArrayList<EstimateDetailEntity>();
	    @SuppressWarnings("unchecked")
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {
		EstimateDetail ed = iter.next();
		EstimateDetailEntity ede = new EstimateDetailEntity(ed);
		details.add(ede);
	    }
	    result.put("details", details);
	    logger.info("get detail list ok");
	} catch (Exception ex) {
	    logger.error("error to get detail List , message: " + ex);
	}

	return result;
    }

    /***
     * This function is to handle post request to add a detail to estimate
     * 
     * @param detail
     * @param request
     * @return status
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/addDetail" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> handleAddDetails(@Valid @ModelAttribute("detail") EstimateDetail detail,
	    HttpServletRequest request, Locale locale, BindingResult bindingResult) {
	logger.info("EstimateController - add detail");
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    request.getSession().removeAttribute(elementIdSession);
	    estimateDetailValidator.validate(detail, bindingResult);
	    if (bindingResult.hasErrors()) {
		result.put("addStatus", messageSource.getMessage("estimate.add.fail", null, locale));
		return result;
	    }
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {// check duplicate
		EstimateDetail ed = iter.next();
		Element e = ed.getElement();
		if (e.getEId().equals(detail.getElement().getEId())) {
		    result.put("addStatus", messageSource.getMessage("estimate.add.duplicate", null, locale));
		    logger.debug("add detail duplicate - " + detail);
		    return result;
		}
	    }
	    set.add(detail);
	    request.getSession().setAttribute(detailListSession, set);
	    result.put("addStatus", messageSource.getMessage("estimate.add.ok", null, locale));

	} catch (Exception ex) {
	    logger.error("Error to add detail Message: " + ex);
	    result.put("addStatus", messageSource.getMessage("estimate.add.fail", null, locale));
	}
	return result;
    }

    /**
     * get info of selected detail base on element Id
     * 
     * @param elementId
     * @param request
     * @return detail
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getDetail/{elementId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Map<String, Object> getDetail(@PathVariable String elementId, HttpServletRequest request) {
	logger.info("in getting detail for editing detail JSON elementId = " + elementId);
	Map<String, Object> result = new HashMap<String, Object>();
	EstimateDetailEntity detail = null;
	ElementEntity element = null;
	try {
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    String material = "";
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {
		EstimateDetail ed = iter.next();
		Element e = ed.getElement();
		if (e.getEId().equals(elementId)) {
		    detail = new EstimateDetailEntity(ed);
		    element = new ElementEntity(e.getEId(), e.getEName(), e.getEUnit());
		    material = ed.getMaterial().getmaterialId();
		    request.getSession().setAttribute(elementIdSession, e.getEId());
		}
	    }
	    result.put("detail", detail);
	    result.put("element", element);
	    result.put("material", material);
	} catch (Exception ex) {
	    logger.error("error to get detail by element id, message = " + ex);
	}
	return result;
    }

    /**
     * this function is to handle edit detail request
     * 
     * @param detail
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/editDetail" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> handleEditDetails(@Valid @ModelAttribute("detail") EstimateDetail detail,
	    HttpServletRequest request, Locale locale, BindingResult bindingResult) {
	logger.info("EstimateController - edit detail");
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    String elementId = (String) request.getSession().getAttribute(elementIdSession);
	    estimateDetailValidator.validate(detail, bindingResult);
	    if (bindingResult.hasErrors()) {
		result.put("addStatus", messageSource.getMessage("estimate.update.fail", null, locale));
		return result;
	    }
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {
		EstimateDetail ed = iter.next();
		Element e = ed.getElement();
		if (e.getEId().equals(elementId)) {
		    set.remove(ed);
		    break;
		}
	    }
	    set.add(detail);
	    request.getSession().setAttribute(detailListSession, set);
	    result.put("addStatus", messageSource.getMessage("estimate.update.ok", null, locale));
	} catch (Exception ex) {
	    logger.error("Error to add detail Message: " + ex.getMessage());
	    result.put("addStatus", messageSource.getMessage("estimate.update.fail", null, locale));
	    request.getSession().removeAttribute(elementIdSession);
	}
	return result;
    }

    /**
     * This function is to handle delete request
     * 
     * @param detail
     * @param request
     * @return status string
     */

    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/deleteDetail" }, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> handleDeleteDetails(@ModelAttribute("detail") EstimateDetail detail,
	    HttpServletRequest request, Locale locale) {
	logger.info("EstimateController - delete detail");
	Map<String, Object> result = new HashMap<String, Object>();
	try {
	    Set<EstimateDetail> set = (Set<EstimateDetail>) request.getSession().getAttribute(detailListSession);
	    String elementId = (String) request.getSession().getAttribute(elementIdSession);
	    Iterator<EstimateDetail> iter = set.iterator();
	    while (iter.hasNext()) {
		EstimateDetail ed = iter.next();
		Element e = ed.getElement();
		if (e.getEId().equals(elementId)) {
		    set.remove(ed);
		    request.getSession().setAttribute(detailListSession, set);
		    result.put("addStatus", messageSource.getMessage("estimate.delete.ok", null, locale));
		    return result;
		}
	    }
	    result.put("addStatus", messageSource.getMessage("estimate.delete.notfound", null, locale));
	} catch (Exception ex) {
	    logger.error("Error to add detail Message: " + ex);
	    result.put("addStatus", messageSource.getMessage("delete.fail", null, locale));
	}
	return result;
    }

    /***
     * Override setAsText to bind material object to view
     * 
     * @param binder
     */

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(Material.class, "material", new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
		Material material = materialService.getMaterial(text);
		setValue(material);
	    }
	});
    }

    /**
     * This function return userId of the current login user
     * 
     * @return userName
     */
    private String getPrincipal() {
	logger.info(String.format("getPrincipal in class: %s", getClass()));
	try {
	    logger.debug("get current login user and return it's username");
	    String userName = null;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails)
		userName = ((UserDetails) principal).getUsername();
	    logger.debug("getPrincipal successfully, userName = " + userName);
	    return userName;
	} catch (Exception e) {
	    logger.error(String.format("getPrincipal in class: %s has error: %s", getClass(), e.getMessage()));
	    throw e;
	}
    }

}
