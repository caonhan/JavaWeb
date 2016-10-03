<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page isELIgnored="false"%>
<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>"><r:message
						code="label.home" /></a></li>
			<li class="active"><r:message code="label.stockRequisition" /></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>
				<r:message code="stockRequisition.stockRequisitionList" />
			</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div>
				<form method="get"
					action="<%=request.getContextPath()%>/limitInventory">
					<button type="submit" class="btn btn-success">
						<i class="glyphicon glyphicon-plus"></i>
						<r:message code="stockRequisition.addNew" />
					</button>
				</form>
			</div>

			<div class="box-content">
				<table
					class="table table-striped table-bordered datatable bootstrap-datatable responsive">
					<thead>
						<tr>
							<th><r:message code="stockRequisition.no" /></th>
							<th><r:message code="stockRequisition.department" /></th>
							<th><r:message code="stockRequisition.limitInventoryId" /></th>
							<th><r:message code="stockRequisition.order" /></th>
							<th><r:message code="stockRequisition.dateWanted" /></th>
							<th><r:message code="stockRequisition.factoryManager" /></th>
							<th><r:message code="stockRequisition.status" /></th>
							<th><r:message code="stockRequisition.hfad" /></th>
							<th><r:message code="stockRequisition.status" /></th>
							<th><r:message code="stockRequisition.action" /></th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach items="${srList}" var="sr">
							<tr>
								<td><%=i++%></td>
								<td>${sr.department}</td>
								<td>${sr.limitInventory.limitInventoryId}</td>
								<td>${sr.orders.orderId}</td>
								<td>${sr.dateWanted}</td>
								<td>${sr.userByFactoryManager.name }</td>
								<td><c:if test="${sr.factoryManagerStatus == 0}">
										<span class="label-info label"><r:message
												code="stockRequisition.approveAwaiting" /></span>
									</c:if> <c:if test="${sr.factoryManagerStatus == 1}">
										<span class="label-success label"><r:message
												code="stockRequisition.approved" /></span>
									</c:if> <c:if test="${sr.factoryManagerStatus == 2}">
										<span class="label-warning label"><r:message
												code="stockRequisition.re-edit" /></span>
									</c:if> <c:if test="${sr.factoryManagerStatus == 3}">
										<span class="label-danger label"><r:message
												code="stockRequisition.cancelled" /></span>
									</c:if></td>
								<td>${sr.userByHfad.name }</td>
								<td><c:if test="${sr.hfadStatus == 0}">
										<span class="label-info label"><r:message
												code="stockRequisition.approveAwaiting" /></span>
									</c:if> <c:if test="${sr.hfadStatus == 1}">
										<span class="label-success label"><r:message
												code="stockRequisition.approved" /></span>
									</c:if> <c:if test="${sr.hfadStatus == 2}">
										<span class="label-warning label"><r:message
												code="stockRequisition.unapproved" /></span>
									</c:if></td>
								<td>
									<div class="dropdown">
										<button class="btn btn-warning dropdown-toggle" type="button"
											data-toggle="dropdown">
											<r:message code="stockRequisition.action" />
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a
												href="<%=request.getContextPath()%>/stockRequisition/examine?id=${sr.requisitionId}">
													<i class="glyphicon glyphicon-check"></i> <r:message
														code="stockRequisition.check" />
											</a></li>
											<li><a
												href="<%=request.getContextPath()%>/stockRequisition/details?id=${sr.requisitionId}"><i
													class="glyphicon glyphicon-tasks"></i> <r:message
														code="stockRequisition.details" /></a></li>
										</ul>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>