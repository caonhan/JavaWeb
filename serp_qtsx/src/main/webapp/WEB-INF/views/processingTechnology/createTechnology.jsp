<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Processing Technology</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">

			<div class="box col-md-12">
				<!-- Product Information -->
				<div class="box-inner">
					<div class="box-header well">
						<label class="control-label" for="inputSuccess4"><r:message
								code="processingProgram.productInformation" /></label>
					</div>
				</div>
				<div class="box-content">
					<div class="col-md-4">
						<label class="control-label" for="inputSuccess4"><r:message
								code="processingProgram.productName" /></label>
					</div>
					<div class="col-md-8">
						<%-- <form:input path="id"/> --%>
					</div>


					<div class="col-md-4">
						<label class="control-label" for="inputSuccess4"><r:message
								code="processingProgram.ordercode" /></label>
					</div>
					<div class="col-md-8">
						<%-- <form:input path="ordercode"/> --%>
					</div>

					<div class="col-md-4">
						<label class="control-label" for="inputSuccess4"><r:message
								code="processingProgram.drawingsymbols" /></label>
					</div>
					<div class="col-md-8">
						<%-- <form:input path="drawingsymbols"/> --%>
					</div>
				</div>
				<!-- End -->
				<!-- Product Information -->
			</div>
			<!-- Processing Program Deatail -->
			<form:form modelAttribute="technologyDetail" method="POST"
				action="updateTechnology">
				<div class="box col-md-12">
					<!-- Processing Deatail -->
					<div class="box-inner">
						<div class="box-header well">
							<label class="control-label" for="inputSuccess4"><r:message
									code="processingProgram.processingDetail" /></label>
						</div>
					</div>
					<div class="box-content">
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.jig" /></label>
								</div>
								<div class="col-md-6">
									<form:input path="id" type="hidden"/>
									<form:input path="jig"/>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.operationdrawing" /></label>
								</div>
								<div class="col-md-6">
									<form:input path="operationDrawing" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.note" /></label>
								</div>
								<div class="col-md-6">
									<form:input path="note" />
								</div>
							</div>
						</div>
						<div class="col-md-6">

							<div class="col-md-6">
								<label class="control-label" for="inputSuccess4"><r:message
										code="processingProgram.operation" /></label>
							</div>
							<div class="col-md-6">
								<form:input path="operation" />
							</div>
							<div class="col-md-6">
								<label class="control-label" for="inputSuccess4"><r:message
										code="processingProgram.accessories" /></label>
							</div>
							<div class="col-md-6">
								<form:input path="accessories" />
							</div>
							<div class="col-md-6">
								<label class="control-label" for="inputSuccess4"><r:message
										code="processingProgram.operationcontent" /></label>
							</div>
							<div class="col-md-6">
								<form:input path="operationContent" />
							</div>
						</div>
					</div>
					<!-- End -->
					<!-- Processing Program Deatail -->



					<!-- Button Save -->
					<div class="col-md-12" align="center">
						<form:button class="btn btn-success">
							<r:message code="processingProgram.save" />
						</form:button>
					</div>
					<!-- End -->
					<!-- Button Save -->
				</div>
			</form:form>

			<!-- Table show list Processing Technology -->
			<div class="box-content">
				<%-- <form:form modelAttribute="technologyDetail" method="GET"
				action="updateTechnology" > --%> 
				<table
					class="datatable table table-striped table-bordered bootstrap-datatable responsive">
					<thead>
						<tr>
							<th><r:message code="processingProgram.no" /></th>
							<th><r:message code="processingProgram.operation" /></th>
							<th><r:message code="processingProgram.operationcontent" /></th>
							<th><r:message code="processingProgram.operationdrawing" /></th>
							<th><r:message code="processingProgram.jig" /></th>
							<th><r:message code="processingProgram.note" /></th>
							<th><r:message code="processingProgram.edit" /></th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach var="tech" items="${listtechnology}">
							<tr>
								<td><%=i++%></td>

								<td>${tech.operation}</td>
								<td>${tech.operationContent}</td>
								<td>${tech.operationDrawing}</td>
								<td>${tech.jig}</td>
								<td>${tech.note}</td>
								<td class="center"><a class="btn btn-info btn-sm"
									href="<%=request.getContextPath()%>/processing?idPTDetail=${tech.id}">
										<!-- <button class="btn btn-info btn-sm"
												id= "addProcessingProgramDetail"> --> <i
										class="glyphicon glyphicon-ok-circle"></i> <!-- </button> -->
								</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<%-- </form:form> --%>
				<!-- Option for user -->
				<div class="row" align="right">
					<div class="col-md-12">
						<a class="btn btn-info" href="#"> <i
							class=" glyphicon glyphicon-ok icon-white"></i> <r:message
								code="processingProgram.completeProcessingTechnology" />
						</a> <a class="btn btn-info" href="#"> <i
							class="glyphicon glyphicon-download-alt icon-white"></i> <r:message
								code="processingProgram.print" />
						</a> <a class="btn btn-danger" href="#"> <i
							class="glyphicon glyphicon-remove icon-white"></i> <r:message
								code="processingProgram.cancel" />
						</a>
					</div>
				</div>
			</div>
			<!-- content ends -->
		</div>
		<!--/#content.col-md-0-->
	</div>
</html>