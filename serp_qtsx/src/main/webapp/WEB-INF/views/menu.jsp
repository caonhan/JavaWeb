<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- left menu starts -->
<div class="col-sm-2 col-lg-2">
	<div class="sidebar-nav">
		<div class="nav-canvas">
			<div class="nav-sm nav nav-stacked"></div>
			<ul class="nav nav-pills nav-stacked main-menu">
				<li class="nav-header"><r:message code="label.main" /></li>
				<li><a class="ajax-link" href="${pageContext.request.contextPath}/home"><i
						class="glyphicon glyphicon-home"></i><span> <r:message
								code="label.home" /></span></a></li>


				<li class="accordion"><a href="customer"><i
						class="glyphicon glyphicon-user"></i><span> <r:message
								code="customer.customer" /></span></a>
					<ul class="nav nav-pills nav-stacked">
						
						<li><a class="ajax-link" href="listcustomer"><i
								class="glyphicon glyphicon-book"></i><span> <r:message
										code="customer.list" />
							</span></a></li>
						<li><a class="ajax-link" href="addcustomer"><i
								class="glyphicon glyphicon-book"></i><span> <r:message
										code="customer.add" />
							</span></a></li>


					</ul></li>


				<li class="accordion"><a href=""><i
						class="glyphicon glyphicon-list-alt"></i><span> <r:message
								code="order.order" /></span></a>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="addorder"><r:message code="order.addOrder" /></a></li>
						<li><a href="listorder"><r:message code="order.list" /></a></li>
					</ul></li>
				<li><a class="ajax-link" href="material"><i
						class="glyphicon glyphicon-book"></i><span> <r:message
								code="label.material" />
					</span></a></li>
				<li class="accordion"><a href="#"><i
						class="glyphicon glyphicon-briefcase"></i> <span><r:message
								code="label.product" /></span></a>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="#"><r:message code="label.home" /></a></li>
						<li><a href="#"><r:message code="label.home" /></a></li>
						<li><a href="#"><r:message code="label.home" /></a></li>
					</ul></li>
				<li><a class="ajax-link" href="estimate" id="listEs"><i
						class="glyphicon glyphicon-file"></i><span> <r:message
								code="label.estimate" /></span></a></li>

				
				<li class="accordion"><a href="#"><i
						class="glyphicon glyphicon-briefcase"></i> <r:message
								code="label.quotation" /></span></a>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="quotation"><r:message code="label.addQuotation" /></a></li>
						<li><a href="quotationList"><r:message code="label.approveQuotation" /></a></li>						
					</ul></li>								
				<li class="accordion"><a class="ajax-link" href="#"><i
						class="glyphicon glyphicon-compressed"></i><span> <r:message
								code="label.manufacturingDepartment" /></span></a>
					<ul class="nav nav-pills nav-stacked">
						<li><a href="<%=request.getContextPath()%>/productionOrder"><r:message
									code="label.productionOrder" /></a></li>
						<li><a href="<%=request.getContextPath()%>/limitInventory"><r:message
									code="label.limitInventory" /></a></li>
						<li><a href="<%=request.getContextPath()%>/stockRequisition"><r:message
									code="label.stockRequisition" /></a></li>
						<li><a href="<%=request.getContextPath()%>/listProgram"><r:message code="processingProgram.processingProgram" /></a></li>
						<li><a href="<%=request.getContextPath()%>/listAllTechnology"><r:message code="processingProgram.processingTechnology" /></a></li>
						<li><a href="<%=request.getContextPath() %>/processingEditor"><r:message code="checktechnology.checktechnology" /></a></li>
						<li><a href="<%=request.getContextPath() %>/processing-document-viewall"><r:message code="pd.pd"/></a></li>
					</ul></li>
				<li>
					<a class="ajax-link" href="listRole"><i class="glyphicon glyphicon-file"></i><span>Role</span></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- left menu ends -->