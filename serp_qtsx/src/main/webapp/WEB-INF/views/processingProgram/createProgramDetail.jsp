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
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/processingProgram.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<title>Processing Program</title>
</head>
<body>
	<!-- content starts -->
	<div id="content" class="col-lg-10 col-sm-10">
		<div class="row">
			<!-- Form ProcessingProgram Information -->
			<form:form modelAttribute="processingPro" method="POST"
				action="addProcessingProgram">
				<!-- ProcessingProgram Information -->
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well">
							<label class="control-label" for="inputSuccess4"><r:message
									code="processingProgram.productInformation" /></label>
						</div>
					</div>
					<div class="box-content">
						<div class="col-md-6">
							<div class="col-md-12">

								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.issuers" /></label>
								</div>
								<div class="col-md-8">
									<input type="text"
										value="${processingPro.userByCreatorId.name}" disabled />
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.productName" /></label>
								</div>
								<div class="col-md-8">
									<input type="text" value="${processingPro.element.EName}"
										disabled />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.reciever" /></label>
								</div>
								<div class="col-md-8">
									<input type="text"
										value="${processingPro.userByRecieverId.name}" disabled />
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.machinery" /></label>
								</div>
								<div class="col-md-8">
									<input type="text" value="${processingPro.equipment}" disabled />
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End ProcessingProgram Information -->
			</form:form>
			<!-- End Form ProcessingProgram Information -->
			
			
			<!-- Form ProcessingProgramDetail -->
			<form:form modelAttribute="processingProDe" method="POST"
				action="addProcessingProgramDetail" id="processingPD">
				<!-- Processing Program Deatail -->
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well">
							<label class="control-label" for="inputSuccess4"><r:message
									code="processingProgram.programDetail" /></label>
						</div>
					</div>
					<div class="box-content">
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.knife" /></label>
								</div>
								<div class="col-md-6">
									<form:input path="id" type="hidden" />
									<form:input path="knife" value="${processingProDe.knife}"
										id="knife" required="required" />
									<label id="checkKnife" style="color: red;"><r:message
											code="processingProgram.erroKnife" /></label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.program_name" /></label>
								</div>
								<div class="col-md-6">
									<form:input path="programName"
										value="${processingProDe.programName}" />
									<label id="checkProgramName" style="color: red;"><r:message
											code="processingProgram.erroProgramName" /></label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.theory_time" /></label>
								</div>
								<div class="col-md-6">
									<form:input type="number" min="0" id="theoryTime"
										value="${processingProDe.theoryTime}" required="required"
										path="theoryTime" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.real_time" /></label>
								</div>
								<div class="col-md-6">
									<form:input type="number" min="0" id="realTime"
										value="${processingProDe.realTime}" path="realTime" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.parameter" /></label>
								</div>
								<div class="col-md-2" align="center">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.parameter1" /></label>
									<form:input type="number" min="0" id="parameter1"
										value="${processingProDe.parameter1}" required="required"
										path="parameter1" maxlength="50" cssStyle="width: 60px" />
								</div>
								<div class="col-md-2" align="center">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.parameter2" /></label>
									<form:input type="number" min="0" id="parameter2"
										value="${processingProDe.parameter2}" required="required"
										path="parameter2" maxlength="50" cssStyle="width: 60px" />
								</div>
								<div class="col-md-2" align="center">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.parameter3" /></label>
									<form:input type="number" min="0" id="parameter3"
										value="${processingProDe.parameter3}" required="required"
										path="parameter3" maxlength="50" cssStyle="width: 60px" />
								</div>
								<div class="col-md-2" align="center">
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.parameter4" /></label>
									<form:input type="number" min="0" id="parameter4"
										value="${processingProDe.parameter4}" required="required"
										path="parameter4" maxlength="50" cssStyle="width: 60px" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="col-md-12">
									<div class="col-md-6">
										<label class="control-label" for="inputSuccess4"><r:message
												code="processingProgram.regime" /></label>
									</div>
									<div class="col-md-2" align="center">
										<label class="control-label" for="inputSuccess4"><r:message
												code="processingProgram.regime1" /></label>

										<form:input type="number" min="0" id="regime1"
											value="${processingProDe.regime1}" required="required"
											path="regime1" maxlength="50" cssStyle="width: 60px" />
									</div>
									<div class="col-md-2" align="center">
										<label class="control-label" for="inputSuccess4"><r:message
												code="processingProgram.regime2" /></label>
										<form:input type="number" min="0" id="regime2"
											value="${processingProDe.regime2}" required="required"
											path="regime2" maxlength="50" cssStyle="width: 60px" />
									</div>
									<div class="col-md-2" align="center">
										<label class="control-label" for="inputSuccess4"><r:message
												code="processingProgram.regime3" /></label>
										<form:input type="number" min="0" id="regime3"
											value="${processingProDe.regime3}" required="required"
											path="regime3" maxlength="50" cssStyle="width: 60px" />
									</div>
								</div>
								<div class="col-md-12">
									<hr>
									<label class="control-label" for="inputSuccess4"><r:message
											code="processingProgram.note" /></label>
									<form:input path="note" maxlength="250" cssStyle="width: 250px"
										value="${processingProDe.note}" />
								</div>
							</div>
						</div>

					</div>
					<!-- End -->
					<!-- Processing Program Deatail -->


					<!-- Button Save -->

					<div class="col-md-12" align="center">
						<form:button class="btn btn-success" id="btnAddPPD">
							<i class="glyphicon glyphicon-plus icon-white"></i>
							<r:message code="processingProgram.add" />
						</form:button>
					</div>
					<!-- End -->
					<!-- Button Save -->
				</div>
			</form:form>
			<!-- End Form ProcessingProgramDetail -->

			<!-- Table show list Processing Program -->
			<div class="box-content">
				<table
					class="datatable table table-striped table-bordered bootstrap-datatable responsive">
					<thead>
						<tr>
							<th rowspan="2"><r:message code="processingProgram.no" /></th>
							<th colspan="2"><r:message
									code="processingProgram.parameter" /></th>
							<th><r:message code="processingProgram.regime" /></th>
							<th rowspan="2"><r:message
									code="processingProgram.program_name" /></th>
							<th colspan="2"><r:message code="processingProgram.time" /></th>
							<th rowspan="2"><r:message code="processingProgram.note" /></th>
							<th rowspan="2"><r:message code="processingProgram.edit" /></th>
							<th rowspan="2"><r:message code="processingProgram.delete" /></th>
						</tr>
						<tr>
							<th><r:message code="processingProgram.jig" /></th>
							<th><r:message code="processingProgram.parameter1" />, <r:message
									code="processingProgram.parameter2" />, <r:message
									code="processingProgram.parameter3" />, <r:message
									code="processingProgram.parameter4" /></th>
							<th><r:message code="processingProgram.regime1" />, <r:message
									code="processingProgram.regime2" />, <r:message
									code="processingProgram.regime3" /></th>
							<th><r:message code="processingProgram.theory_time" /></th>
							<th><r:message code="processingProgram.real_time" /></th>
						</tr>
					</thead>

					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach var="pr" items="${listProgramDetail}">
							<tr>
								<td><%=i++%></td>
								<td>${pr.knife}</td>
								<td>${pr.parameter1},${pr.parameter2},${pr.parameter3},
									${pr.parameter4}</td>
								<td>${pr.regime1},${pr.regime2},${pr.regime3}</td>
								<td>${pr.programName}</td>
								<td>${pr.theoryTime}</td>
								<td>${pr.realTime}</td>
								<td>${pr.note}</td>
								<td class="center"><a
									href="<%=request.getContextPath()%>/program?idPPDetail=${pr.id}">
										<button class="btn btn-info btn-sm"
											id="addProcessingProgramDetail">
											<i class="glyphicon glyphicon-ok-circle"></i>
										</button>
								</a></td>
								<td><a
									href="<%=request.getContextPath()%>/deleteProcessingProgramDetail?idPPDetail=${pr.id}">
										<button class="btn btn-info btn-sm"
											id="deleteProcessingProgramDetail">
											<i class="glyphicon glyphicon-remove"></i>
										</button>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>	
					<!-- Upload image -->
					<%-- <tr>
								<td colspan="2"><r:message code="processingProgram.image" />1</td>
								<td colspan="3"><r:message code="processingProgram.image" />2</td>
								<td colspan="3"><r:message code="processingProgram.image" />3</td>
								<td colspan="2"><r:message code="processingProgram.image" />4</td>
							</tr>
							<tr>
								<td colspan="2"><img alt="" src="../image/1.png"></td>
								<td colspan="3"><img alt="" src="../image/2.png"></td>
								<td colspan="3"><img alt="" src="../image/3.png"></td>
								<td colspan="2"><img alt="" src="../image/4.png"></td>					
							</tr> 		 --%>
					<!-- End upload image -->
				</table>
				<!-- End table show list Processing Program -->


				<!-- Option for user -->
				<div class="row" align="right">
					<div class="col-md-12">
						<a class="btn btn-info"
							href="<%=request.getContextPath()%>/listProgram"> <i
							class=" glyphicon glyphicon-ok icon-white"></i> <r:message
								code="processingProgram.completeProcessingTechnology" />
						</a> 
					</div>
				</div>
				<!-- End option for user -->

			</div>
		</div>
	</div>
	<!-- End content -->
</html>