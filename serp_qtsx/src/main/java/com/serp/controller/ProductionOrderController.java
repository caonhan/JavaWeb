package com.serp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.serp.model.Orders;
import com.serp.model.ProductionOrder;
import com.serp.model.User;
import com.serp.service.OrderService;
import com.serp.service.ProductionOrderService;
import com.serp.service.UserLoginService;

@Controller
@RequestMapping("/")
public class ProductionOrderController {

	private static final Log log = LogFactory.getLog(QuotationController.class);

	private ProductionOrder temporary = new ProductionOrder();

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	OrderService orderService;

	@Autowired
	UserLoginService UserService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	ProductionOrderService poService;

	@RequestMapping(value = { "/productionOrder" }, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");
		List<ProductionOrder> poList = new ArrayList<ProductionOrder>();

		try {
			poList = poService.findAll();
			// System.out.println(poList.get(0).toString());
			request.setAttribute("poList", poList);
		} catch (NullPointerException ex) {
			log.error("NullPointerException, message: " + ex.getMessage());
		}

		return "productionOrder/productionOrderList";
	}

	@RequestMapping(value = { "/productionOrder/addnew" }, method = RequestMethod.GET)
	public String directToProductionOrderAddnew(HttpServletRequest request, HttpServletResponse response) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");

		try {
			int requisitionId = Integer.parseInt(request.getParameter("id"));

			// Clear temporary object
			temporary = new ProductionOrder();

		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
		} finally {
			request.setAttribute("podetail", temporary);
		}

		return "productionOrder/productionOrderAdd";
	}

	@RequestMapping(value = { "/productionOrder/add" }, method = RequestMethod.GET)
	public String directToProductionOrderAdd(HttpServletRequest request, HttpServletResponse response) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");

		try {

			// Create temporary object
			temporary = new ProductionOrder();
			System.out.println(temporary.toString());

		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
		} finally {
			request.setAttribute("poUpdate", temporary);
		}

		return "productionOrder/productionOrderAdd";
	}

	@RequestMapping(value = { "/productionOrder/details" }, method = RequestMethod.GET)
	public String directToProductionOrderDetails(HttpServletRequest request, HttpServletResponse response) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");

		try {
			int requisitionId = Integer.parseInt(request.getParameter("id"));

			// Clear temporary object
			temporary = new ProductionOrder();
			temporary = poService.findById(requisitionId);
			System.out.println(temporary.toString());

		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
		} finally {
			request.setAttribute("podetail", temporary);
		}

		return "productionOrder/productionOrderDetails";
	}

	@RequestMapping(value = { "/productionOrder/detailsAction" }, method = RequestMethod.GET)
	public String handleDetailsPageActions(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");
		String action = request.getParameter("btnAction");

		if (messageSource.getMessage("productionorder.exit", null, locale).equals(action)) {
			log.debug("Exit action");

			// Clear old object
			temporary = new ProductionOrder();
			return "redirect:/productionOrder";
		} else if (messageSource.getMessage("productionorder.update", null, locale).equals(action)) {

			request.setAttribute("poUpdate", temporary);

			return "productionOrder/productionOrderUpdate";
		} else if (messageSource.getMessage("productionorder.delete", null, locale).equals(action)) {
			log.debug("Delete action");

			if (poService.delete(temporary.getPoId())) {
				log.info("Delete successful");
			} else {
				log.error("Delete failed");
			}

			return "redirect:/productionOrder/list";
		} else if (messageSource.getMessage("productionorder.download", null, locale).equals(action)) {
			log.debug("Downloading Production Order with id: " + temporary.getPoId());
			System.out.println("Downloading!!!");
		}

		return "redirect:/productionOrder/details";
	}

	@RequestMapping(value = { "/productionOrder/detailCheck" }, method = RequestMethod.GET)
	public String directToProductionOrderDetailCheck(HttpServletRequest request, HttpServletResponse response) {
		log.debug("ProductionOrderController - handleRequest");
		response.setContentType("text/html");

		try {
			int requisitionId = Integer.parseInt(request.getParameter("id"));

			// Clear temporary object
			temporary = new ProductionOrder();
			temporary = poService.findById(requisitionId);
			System.out.println(temporary.toString());

		} catch (NumberFormatException nfe) {
			log.error("NumberFormatException - No id found: " + nfe.getMessage());
		} finally {
			request.setAttribute("poUpdate", temporary);
		}

		return "productionOrder/productionOrderCheck";
	}

	@RequestMapping(value = { "/productionOrder/saveDetails" }, method = RequestMethod.POST)
	public String handleSaveBtnInAddOrEditView(@ModelAttribute("poUpdate") ProductionOrder productionOrderUpdate,
			HttpServletResponse response, HttpServletRequest request, Locale locale) {
		response.setContentType("text/html");
		log.info("ProductionOrderController - handleSaveBtnInAddOrEditView");
		try {
			String action = request.getParameter("btnAction2");

			if (messageSource.getMessage("productionorder.save", null, locale).equals(action)) {
				log.debug("Save action");
				int requisitionId = request.getParameter("poId").isEmpty() ? -1
						: Integer.parseInt(request.getParameter("poId"));

				String orderId = request.getParameter("orderId");
				Orders order = orderService.findById(orderId);
				productionOrderUpdate.setOrders(order);

				Date startTime = formatter.parse(request.getParameter("startTime"));
				Date finishTime = formatter.parse(request.getParameter("finishTime"));
				productionOrderUpdate.setPoStarttime(startTime);
				productionOrderUpdate.setPoFinishtime(finishTime);

				if (requisitionId == -1) {
					log.debug("User add new Production Order");

				} else {
					log.debug("User update old Production Order");
				}
				boolean saveStatus = poService.saveOrUpdate(productionOrderUpdate);

				if (saveStatus) {
					log.debug("Save or Update successful");
					System.out.println("Model: " + temporary.toString() + ", save or update successful");
				} else {
					throw new Exception();
				}

				request.setAttribute("saveStatus", saveStatus);
			} else if (messageSource.getMessage("productionorder.check", null, locale).equals(action)) {
				log.debug("Check action");

				User userByPoApprovedBy = UserService.findById(getPrincipal());
				productionOrderUpdate.setUserByPoApprovedBy(userByPoApprovedBy);
				
				User factoryManager = UserService.findById(request.getParameter("factoryManager"));
				productionOrderUpdate.setUserByPoFactoryManager(factoryManager);

				String orderId = request.getParameter("orderId");
				Orders order = orderService.findById(orderId);
				productionOrderUpdate.setOrders(order);

				/*
				 * String userByPoApprovedBy =
				 * request.getParameter("userByPoApprovedBy"); // int poStatus
				 * =Integer.valueOf(request.getParameter("poStatus"));
				 * productionOrderUpdate.setUserByPoApprovedBy(getPrincipal());
				 * // productionOrderUpdate.setPoStatus(poStatus);
				 */

				Date startTime = formatter.parse(request.getParameter("startTime"));
				Date finishTime = formatter.parse(request.getParameter("finishTime"));
				productionOrderUpdate.setPoStarttime(startTime);
				productionOrderUpdate.setPoFinishtime(finishTime);
				boolean checkStatus = poService.saveOrUpdate(productionOrderUpdate);

				if (checkStatus) {
					log.debug("Check successful");
					System.out.println("Model: " + productionOrderUpdate.toString() + ", check successful");
				} else {
					throw new Exception();
				}

				request.setAttribute("checkStatus", checkStatus);
			} else if (messageSource.getMessage("productionorder.exit", null, locale).equals(action)) {
				log.debug("Exit action");
				return "redirect:/productionOrder";
			}
		} catch (Exception ex) {
			log.error("Failed to save or update Production Order details, message: " + ex.getMessage());
			System.err.println("Failed to save or update production order details, message: " + ex.getMessage());
		}

		request.setAttribute("poUpdate", productionOrderUpdate);
		return "redirect:/productionOrder";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		System.out.println(userName);
		return userName;
	}

}
