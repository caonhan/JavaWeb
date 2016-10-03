<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="resources/userJs/estimate.js"></script>
<title>Estimate</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<div id="dialog" style="display: none;">
			<span id="messageContent"></span>
		</div>
		<div id="messageDialog" style="display: none;">
			<span id="messageContent"></span>
		</div>
		<!-- content starts -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-file"></i>
							<r:message code="estimate.list" />
						</h2>
					</div>
					<div class="box-content">
						<c:if test="${isEditMode}">
							<a class="btn-sm btn-success" href="listorder"><i
								class="glyphicon glyphicon-plus"></i><span> <r:message
										code="estimate.add" /></span></a>
						</c:if>
						<table
							class="table table-striped table-bordered datatable bootstrap-datatable responsive">
							<thead>
								<tr>
									<th><r:message code="estimate.id" /></th>
									<th><r:message code="estimate.project_name" /></th>
									<th><r:message code="estimate.published_date" /></th>
									<th><r:message code="estimate.creator" /></th>
									<th><r:message code="estimate.status" /></th>
									<th><r:message code="estimate.actions" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="es" items="${listEstimate}">
									<tr>
										<th>${es.esId}</th>
										<td>${es.orders.projectName}</td>
										<td>${es.esPublishedDate}</td>
										<td>${es.userByEsCreatorId.name}</td>
										<td><c:if test="${es.status.statusId == 3}">
												<span class="label-warning label label-default"> <r:message
														code="status.${es.status.statusName}" />
												</span>
											</c:if> <c:if test="${es.status.statusId == 2}">
												<span class="label-success label label-default"> <r:message
														code="status.${es.status.statusName}" />
												</span>
											</c:if> <c:if test="${es.status.statusId == 1}">
												<span class="label-default label"> <r:message
														code="status.${es.status.statusName}" />
												</span>
											</c:if> <c:if test="${es.status.statusId == 4}">
												<span class="label-danger label label-danger"> <r:message
														code="status.${es.status.statusName}" />
												</span>
											</c:if></td>
										<td><div class="control-group">
												<form action="editEstmate" method="get" class="formEstimate">
													<input type="hidden" class="estimateId" name="estimateId" />
													<input type="hidden" class="viewMode" name="viewMode" />
												</form>
												<div class="controls">
													<select class="selectAction">
														<option value=""><r:message
																code="estimate.select" /></option>
														<c:if test="${es.status.statusId == 1}">
															<c:if test="${isEditMode}">
																<option value="deleteEstimate=${es.esId}"><r:message
																		code="estimate.delete" /></option>
																<option value="editEstimate=${es.esId}"><r:message
																		code="estimate.edit" /></option>
															</c:if>
															<c:if test="${isApproveMode}">
																<option value="editEstimate=${es.esId}"><r:message
																		code="estimate.approve" /></option>
															</c:if>
														</c:if>
														<c:if
															test="${es.status.statusId == 2 || es.status.statusId == 4}">
															<option value="viewEstimate=${es.esId}"><r:message
																	code="estimate.view" /></option>
														</c:if>
														<c:if test="${es.status.statusId ==3}">
															<c:if test="${isEditMode}">
																<option value="editEstimate=${es.esId}"><r:message
																		code="estimate.reestimate" /></option>
															</c:if>
															<c:if test="${isApproveMode}">
																<option value="editEstimate=${es.esId}"><r:message
																		code="estimate.approve" /></option>
															</c:if>
														</c:if>
													</select>
												</div>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--/span-->

		</div>
		<!--/row-->



		<!-- content ends -->
	</div>
	<!--/#content.col-md-0-->
</body>
</html>