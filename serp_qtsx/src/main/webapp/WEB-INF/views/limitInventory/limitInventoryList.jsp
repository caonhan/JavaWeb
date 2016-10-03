<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Limit Inventory List</title>
</head>
<body>
	<div class="col-lg-10 col-sm-10">
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>"><r:message
							code="label.home" /></a></li>
				<li class="active"><r:message code="label.limitInventory" /></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>
					<r:message code="limitInventory.limitInventoryList" />
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div>
					<button type="submit" class="btn btn-primary">
						<i class="glyphicon glyphicon-plus"></i>
						<r:message code="limitInventory.add" />
					</button>
				</div>
				<div class="box-content">
					<table
						class="table table-striped table-bordered datatable bootstrap-datatable responsive">
						<thead>
							<tr>
								<th><r:message code="limitInventory.no" /></th>
								<th><r:message code="limitInventory.orderID" /></th>
								<th><r:message code="limitInventory.creator" /></th>
								<th><r:message code="limitInventory.createdDate" /></th>
								<th><r:message code="limitInventory.factoryManager" /></th>
								<th><r:message code="limitInventory.status" /></th>
								<th><r:message code="limitInventory.directorate" /></th>
								<th><r:message code="limitInventory.status" /></th>
								<th><r:message code="limitInventory.viewDetail" /></th>
							</tr>
						</thead>
						<tbody>
							<%
								int i = 1;
							%>
							<c:forEach items="${liList}" var="li">
								<tr>
									<td><%=i++%></td>
									<td>${li.orders.orderId}</td>
									<td>${li.userByCreator.name }</td>
									<td>${li.createdDate}</td>
									<td>${li.userByFactoryManagerId.name}</td>
									<td><c:if test="${li.factoryManagerStatus == 0}">
											<span class="label-info label"><r:message
													code="limitInventory.approveAwaiting" /></span>
										</c:if> <c:if test="${li.factoryManagerStatus == 1}">
											<span class="label-success label"><r:message
													code="limitInventory.approved" /></span>
										</c:if> <c:if test="${li.factoryManagerStatus == 2}">
											<span class="label-warning label"><r:message
													code="limitInventory.re-edit" /></span>
										</c:if> <c:if test="${li.factoryManagerStatus == 3}">
											<span class="label-danger label"><r:message
													code="limitInventory.cancelled" /></span>
										</c:if></td>
									<td>${li.userByDirectorateId.name}</td>
									<td><c:if test="${li.directorateStatus == 0}">
											<span class="label-info label"><r:message
													code="limitInventory.approveAwaiting" /></span>
										</c:if> <c:if test="${li.directorateStatus == 1}">
											<span class="label-success label"><r:message
													code="limitInventory.approved" /></span>
										</c:if> <c:if test="${li.directorateStatus == 2}">
											<span class="label-warning label"><r:message
													code="limitInventory.re-edit" /></span>
										</c:if> <c:if test="${li.directorateStatus == 3}">
											<span class="label-danger label"><r:message
													code="limitInventory.cancelled" /></span>
										</c:if></td>
									<td><div class="dropdown">
											<button class="btn btn-warning dropdown-toggle" type="button"
												data-toggle="dropdown">
												<r:message code="limitInventory.action" />
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="#"> <i
														class="glyphicon glyphicon-check"></i> <r:message
															code="limitInventory.check" />
												</a></li>
												<li><a
													href="<%=request.getContextPath()%>/limitInventory/detail?id=${li.limitInventoryId}"><i
														class="glyphicon glyphicon-tasks"></i> <r:message
															code="limitInventory.viewDetail" /></a></li>
												<!-- Link to Stock Requisition create page  -->
												<c:if
													test="${li.factoryManagerStatus == 1 && li.directorateStatus == 1 }">
													<li><a
														href="<%=request.getContextPath()%>/stockRequisition/add?liId=${li.limitInventoryId}">
															<i class="glyphicon glyphicon-plus"></i> <r:message
																code="limitInventory.stockRequisition" />
													</a></li>
												</c:if>
												<!-- End of link -->
											</ul>
										</div></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>