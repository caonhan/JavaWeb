
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.serp.model.ProcessingTechnology"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Processing Technology</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
	<!-- Table show list Processing Technology -->
			<div class="box-content">
				<table
					class="datatable table table-striped table-bordered bootstrap-datatable responsive">
					<thead>
						<tr>
							<th><r:message code="processingProgram.no" /></th>
							<th><r:message code="processingProgram.element" /></th>
							<th><r:message code="processingProgram.operationdrawing" /></th>
							<th><r:message code="processingProgram.creator" /></th>
							<th><r:message code="processingProgram.leader" /></th>
							<th><r:message code="processingProgram.manager" /></th>
							<th><r:message code="processingProgram.createdDay" /></th>
							<th><r:message code="processingProgram.check_date" /></th>
							<th><r:message code="processingProgram.approveDay" /></th>
							<th><r:message code="processingProgram.status" /></th>
							<th><r:message code="processingProgram.note" /></th>
							<th><r:message code="processingProgram.edit" /></th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach var="tech" items="${listProcessingTechnology}">
							<tr>
								<td><%=i%></td>
								<td></td>

								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>${tech.ptCreatedDay}</td>
								<td>${tech.ptCheckDay}</td>
								<td>${tech.ptApproveDay}</td>
								<td>${tech.ptStatus}</td>
								<td>${tech.ptNote}</td>
								<td><a href="processing?l=${tech.ptId}"
									class="btn btn-success btnUpdate"> <r:message
											code="processingProgram.technologyDetail" />
								</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

</body>
</html>