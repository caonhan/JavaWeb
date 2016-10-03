<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<div class="col-lg-10 col-sm-10">
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>"><r:message
							code="label.home" /></a></li>
				<li class="active"><r:message code="label.productionOrder" /></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>
					<r:message code="productionorder.productionOrderList" />
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<div>
				<form method="get"
					action="<%=request.getContextPath()%>/productionOrder/add">
					<button type="submit" class="btn btn-success">
						<i class="glyphicon glyphicon-plus"></i>
						<r:message code="productionorder.addnew" />
					</button>
				</form>
			</div>
			
				
				<div class="box-content">
					<table
						class="table table-striped table-bordered datatable bootstrap-datatable responsive">
						<thead>
							<tr>
								<th><r:message code="productionorder.id" /></th>
								<th><r:message code="productionorder.content" /></th>
								<th><r:message code="productionorder.quantity" /></th>
								<th><r:message code="productionorder.unit" /></th>
								<th><r:message code="productionorder.order" /></th>
								<th><r:message code="productionorder.processtechnology" /></th>
								<th><r:message code="productionorder.factoryManager" /></th>
								<th><r:message code="productionorder.approvedby" /></th>
								<th><r:message code="productionorder.status" /></th>
								<th><r:message code="productionorder.dateWanted" /></th>
								<th><r:message code="productionorder.starttime" /></th>
								<th><r:message code="productionorder.finishtime" /></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<%
							int i = 1;
						%>
						<c:forEach items="${poList}" var="po">
							<tr>
								<td><%=i++%></td>
								<td>${po.poContent}</td>
								<td>${po.poQuantity}</td>
								<td>${po.poUnit}</td>
								<td><a href="<%=request.getContextPath()%>/productionOrder/details?id=${po.poId}">${po.orders.orderId}</a></td>
								<td>${po.poProcessTechnology}</td>
								<td>${po.userByPoFactoryManager.userId}</td>
								<td>${po.userByPoApprovedBy.userId}</td>
								<td><c:if test="${po.poStatus == 0}">
										<span class="label-warning label"><r:message
												code="productionorder.unapproved" /> </span>
									</c:if> <c:if test="${po.poStatus == 1}">
										<span class="label-info label"><r:message
												code="productionorder.approveAwaiting" /> </span>
									</c:if> <c:if test="${po.poStatus == 2}">
										<span class="label-success label"><r:message
												code="productionorder.approved" /> </span>
									</c:if></td>
								<td>${po.poTimelength}</td>
								<td>${po.poStarttime}</td>
								<td>${po.poFinishtime}</td>
								<td><a
												href="<%=request.getContextPath()%>/productionOrder/detailCheck?id=${po.poId}">
													<i class="glyphicon glyphicon-check"></i> <r:message
														code="productionorder.check" />
								</a></td>
								<td><a href="#">Limit Inventory</a></td>
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