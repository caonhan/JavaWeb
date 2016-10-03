<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.serp.model.ProcessingTechnology"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Technology</title>


<script type="text/javascript">
	function getConfirmation() {
		var retVal = confirm("Do you want to continue ?");
		if (retVal == true) {
			return true;
		} else {
			window.location = "redirect:processingEditor";
			return false;
		}
	}
</script>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>"><r:message
							code="label.home" /></a></li>
				<li class="active"><r:message
						code="checktechnology.checktechnology" /></li>
			</ul>
		</div>
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-ok"></i>
							<r:message code="checktechnology.checktechnology" />
						</h2>
					</div>
					<div class="page-header">
						<h5>
							<r:message code="checktechnology.technology" />
						</h5>
					</div>
					<div class="box-content">
						<form:form modelAttribute="proTechnology" action="checkTechnology"
							method="post">

							<!-- List Check_Technology -->
							<table
								class="datatable table table-striped table-bordered bootstrap-datatable responsive">
								<thead>
									<tr>
										<th><r:message code="checktechnology.stt" /></th>
										<th><r:message code="checktechnology.operation" /></th>
										<th><r:message code="checktechnology.operationcontent" /></th>
										<th><r:message code="checktechnology.operationdrawing" /></th>
										<th><r:message code="checktechnology.jig" /></th>
										<th><r:message code="checktechnology.accessories" /></th>
										<th><r:message code="checktechnology.note" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ct" items="${lstProTechnology}"
										varStatus="status">
										<tr>
											<th align="center">${status.count}</th>
											<td>${ct.operation}</td>
											<td class="center">${ct.operationContent}</td>
											<td class="center">${ct.operationDrawing}</td>
											<td class="center">${ct.jig}</td>
											<td class="center">${ct.accessories}</td>
											<td class="center">${ct.note }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>


							<!-- End list Check_Technology -->
							<!--  -->
							<!-- Table show list Processing Program -->
							<div class="page-header">
								<h5>
									<r:message code="checktechnology.program" />
								</h5>
							</div>
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

										</tr>
										<tr>
											<th><r:message code="processingProgram.jig" /></th>
											<th><r:message code="processingProgram.parameter1" /> <r:message
													code="processingProgram.parameter2" /> <r:message
													code="processingProgram.parameter3" /> <r:message
													code="processingProgram.parameter4" /></th>
											<th><r:message code="processingProgram.regime1" /> <r:message
													code="processingProgram.regime2" /> <r:message
													code="processingProgram.regime3" /></th>
											<th><r:message code="processingProgram.theory_time" /></th>
											<th><r:message code="processingProgram.real_time" /></th>
										</tr>
									</thead>

									<tbody>
										<%
											int i = 1;
										%>
										<c:forEach var="pr" items="${listProgram}">
											<tr>
												<td><%=i++%></td>
												<th>${pr.knife}</th>
												<td>${pr.parameter1}${pr.parameter2}${pr.parameter3}
													${pr.parameter4}</td>
												<td>${pr.regime1}${pr.regime2}${pr.regime3}</td>
												<td>${pr.programName}</td>
												<td>${pr.theoryTime}</td>
												<td>${pr.realTime}</td>
												<td>${pr.note}</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- End -->
								<!-- Table show list Processing Program -->

								<!-- textarea leader/manager note -->
								<div class="box-content">
									<div class="box-header well">
										<r:message code="checktechnology.note" />
									</div>
									<div class="box-content">
										<textarea rows="5" cols=100% name="ptNote">${ProTechnologyOld.getPtNote()}</textarea>
										<input type="hidden" name="ptId"
											value="${ProTechnologyOld.getPtId()}" /> <input
											type="hidden" name="ptStatus"
											value="${ProTechnologyOld.getPtStatus()}" /> <input
											type="hidden" name="element.EId"
											value="${ProTechnologyOld.getElement().getEId()}" />
									</div>
								</div>
								<div class="row">
									<%
										int tmp = 0;
									%>
									<c:forEach var="ct" items="${lstProTechnology}">
										<%
											if (tmp == 0) {
										%>
										<div class="box col-md-6">
											<!-- info -->
											<div class="col-md-4">
												<label class="control-label" for="inputSuccess4"><r:message
														code="checktechnology.productname" /></label>
											</div>
											<div class="col-md-8">
												<input type="text" class="form-control" disabled
													value="${ProTechnologyOld.element.EName }"
													style="width: 243px; height: 35px; margin-bottom: 4px;">
											</div>

											<div class="col-md-4">
												<label class="control-label" for="inputSuccess4"><r:message
														code="checktechnology.ordercode" /></label>
											</div>
											<div class="col-md-8">
												<input type="text" class="form-control" disabled value=""
													style="width: 243px; height: 35px; margin-bottom: 4px;">
											</div>

											<div class="col-md-4">
												<label class="control-label" for="inputSuccess4"><r:message
														code="checktechnology.drawingsymbols" /></label>
											</div>
											<div class="col-md-8">
												<input type="text" class="form-control" disabled
													value="${ProTechnologyOld.ptDrawingSymbols}"
													style="width: 243px; height: 35px; margin-bottom: 4px;">
											</div>
										</div>
										<%
											tmp++;
													} else {
													}
										%>
									</c:forEach>

									<!-- manager -->
									<div class="col-md-6">
										<div class="row">
											<div class="col-md-4">
												<input type="submit" class="btn btn-success btn-lg"
													onclick="getConfirmation()" name="action" value="<r:message
														code="checktechnology.approve" />" />
											</div>
											<div class="col-md-4">
												<input type="submit" class="btn btn-default btn-lg" name="action"
													value="<r:message
														code="checktechnology.print" />" />
											</div>
											<div class="col-md-4">
												<input type="submit" class="btn btn-danger btn-lg"
													onclick="getConfirmation()" name="action" value="<r:message
														code="checktechnology.reject" />" />
											</div>
										</div>
									</div>
								</div>

							</div>
						</form:form>
					</div>
				</div>
				<!--/span-->
			</div>
		</div>
		<!--/row-->
		<!-- content ends -->
	</div>
	<!--/#content.col-md-0-->
</html>