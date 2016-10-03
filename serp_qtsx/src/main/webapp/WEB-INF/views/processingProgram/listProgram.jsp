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
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/processingProgram.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<title>List Processing Program</title>
</head>
<body>
	<!-- Start content -->
	<div id="content" class="col-lg-10 col-sm-10">
		<div class="row">		
			<div class="box-content">
				<div class="box col-md-12">
				
					<!-- Form ProcessingProgram to fill out -->
					<form:form modelAttribute="processingPro" method="POST"
						action="addProcessingProgram">
					<div class="box-inner">
						<div class="box-header well">
							<label class="control-label" for="inputSuccess4"><r:message
									code="processingProgram.productInformation" /></label>
						</div>
						<div class="col-md-12">								
							<div class="col-md-6">
								<div class="col-md-6">	
									<r:message code="processingProgram.creator" />					
								</div>
								<div class="col-md-6">
									<form:input path="id" type="hidden" />
									<form:select path="userByCreatorId" required="required"
										id="userByCreatorId">
										<form:options items="${listUser}"
											itemLabel="name" itemValue="userID" />
									</form:select>				
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-6">
									<r:message code="processingProgram.reciever" />	
								</div>				
								<div class="col-md-6">	
									<form:select path="image2" required="required"
										id="userByCreatorId">
										<form:options items="${listUser}"
											itemLabel="name" itemValue="userID" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="col-md-6">
									<r:message code="processingProgram.product_name" />	
								</div>	
								<div class="col-md-6">	
									<form:select path="image3" required="required"
										id="userByCreatorId">
										<form:options items="${listElement}"
											itemLabel="EName" itemValue="EId" />
									</form:select>		
									
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-6">
									<r:message code="processingProgram.equipment" />	
								</div>			
								<div class="col-md-6">
									<form:input path="equipment" id="equipment"/>
								</div>
							</div>
						</div>		
							<div class="col-md-12">
							<div class="col-md-6">
								<div class="col-md-6">
									<r:message code="processingProgram.status" />	
								</div>	
								<div class="col-md-6">	
									<form:input path="status" id="status"/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-6">
									<r:message code="processingProgram.note" />	
								</div>			
								<div class="col-md-6">
									<form:input path="note" id="note"/>
								</div>
							</div>
						</div>	
						<div class="col-md-12" align="center">
							<button class="btn btn-success" id="btnAddPP">
								<i class="glyphicon glyphicon-plus icon-white"></i>
								<r:message code="processingProgram.addPP" />
							</button>
						</div>
					</div>
					</form:form>
					<!-- End form ProcessingProgram to fill out -->
				</div>
				
				<!-- Table show list ProcessingProgram -->
				<table
					class="datatable table table-striped table-bordered bootstrap-datatable responsive">
					<thead>
						<tr>
							<th><r:message code="processingProgram.no" /></th>
							<th><r:message code="processingProgram.product_name" /></th>
							<th><r:message code="processingProgram.creator" /></th>
							<th><r:message code="processingProgram.create_date" /></th>
							<th><r:message code="processingProgram.reciever" /></th>
							<th><r:message code="processingProgram.recieve_date" /></th>
							<th><r:message code="processingProgram.equipment" /></th>
							<th><r:message code="processingProgram.status" /></th>
							<th><r:message code="processingProgram.note" /></th>
							<th><r:message code="processingProgram.detail" /></th>
							<th><r:message code="processingProgram.delete" /></th>
						</tr>
					</thead>

					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach var="pr" items="${listProcessingProgram}">
							<tr>
								<td><%=i++%></td>
								<td>${pr.element.EName}</td>
								<td>${pr.userByCreatorId.name}</td>
								<td>${pr.creatorDate}</td>
								<td>${pr.userByRecieverId.name}</td>
								<td>${pr.recieverDate}</td>
								<td>${pr.equipment}</td>
								<td>${pr.status}</td>
								<td>${pr.note}</td>
								<td class="center"><a
									href="<%=request.getContextPath()%>/program?id=${pr.id}">
										<button class="btn btn-info btn-sm" id="addProcessingProgram">
											<i class="glyphicon glyphicon-ok-circle"></i>

										</button>
								</a></td>
								<td>
									<a href="<%=request.getContextPath()%>/deleteProcessingProgram?idPP=${pr.id}">
											<button class="btn btn-info btn-sm"
												id= "deleteProcessingProgram">
												<i class="glyphicon glyphicon-remove"></i>
											</button>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- End table show list ProcessingProgram -->
			</div>
		</div>
	</div>
	<!-- End content -->
</body>
</html>