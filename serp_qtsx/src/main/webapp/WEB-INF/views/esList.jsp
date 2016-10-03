<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="resources/userJs/Quotation.js"></script>
<title>Quotation</title>
</head>
<body>	
	<div class="col-sm-10 col-lg-10">
		<div class="row">
			<ul class="breadcrumb">
	            <li> <a href="home"><r:message code="label.home" /></a> </li>
	            <li> <a href="quotation"><r:message code="label.estimateList" /></a> </li>
	        </ul>
		</div>
		<div class="box-inner">
			<div class="box-header well">
				<h2><i class="glyphicon glyphicon-file"></i><r:message code="estimate.list" /></h2>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
					<thead>
						<tr>
							<th><r:message code="estimate.id" /></th>
							<th><r:message code="estimate.project_name" /></th>
							<th><r:message code="estimate.published_date" /></th>
							<th><r:message code="estimate.creator" /></th>
							<th><r:message code="estimate.status" /></th>
							<th><r:message code="estimate.approveContent" /></th>
							<th><r:message code="estimate.actions" /></th>
						</tr>
					</thead>
					<% int i=1; %>
					<tbody>
						<c:forEach items="${estimateList}" var="item">
							<c:if test="${item.status.statusName == 'approved'}">
								<tr>
									<th><%= i++ %>.</th>
									<td>${item.orders.projectName}</td>
									<td class="center"><fmt:formatDate type="date" value="${item.esPublishedDate}" /></td>
									<td class="center">${item.userByEsCreatorId.name}</td>
									<td class="center"><span
										class="label-success label label-default">${item.status.statusName}</span></td>
									<td class="center"><span
										class="label-success label label-default">${item.esApproveContent}</span></td>
									<c:if test="${item.esApproveContent != 'Yes'}">
										<td class="center"><a class="btn btn-info" style="height: 24px;padding-top: 1px;" href="createQuotation?estimateId=${item.esId}&approveContent=${item.esApproveContent}"> <i
											class="glyphicon glyphicon-edit icon-white"></i>   <r:message code="label.addQuotation" /></a></td>
									</c:if>
									<c:if test="${item.esApproveContent == 'Yes'}">
										<td class="center"><a class="btn btn-info" style="height: 24px;padding-top: 1px;" href="createQuotation?estimateId=${item.esId}&approveContent=${item.esApproveContent}"> <i
											class="glyphicon glyphicon-edit icon-white"></i>   <r:message code="label.editQuotation" /></a></td>
									</c:if>
								</tr>
							</c:if>						
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>