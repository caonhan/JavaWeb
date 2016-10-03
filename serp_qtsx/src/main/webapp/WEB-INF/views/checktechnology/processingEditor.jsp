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
<title>Check Technology</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-ok"></i>
							<r:message code="checktechnology.checktechnology" />
						</h2>
					</div>
					<div class="box-content">
						<form:form action="listTechnology" method="POST">
							<table
								class="datatable table table-striped table-bordered bootstrap-datatable responsive">
								<thead>
									<tr>
										<th><r:message code="checktechnology.stt" /></th>
										<th><r:message code="checktechnology.productname" /></th>
										<th><r:message code="checktechnology.sender" /></th>
										<th><r:message code="checktechnology.sentdate" /></th>
										<th><r:message code="checktechnology.view" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="ct" items="${lstProEditor}" varStatus="status">
										<tr>
											<th>${status.count}</th>
											<td>${ct.element.EName}</td>
											<td class="center">${ct.userByPtCreatorId.name}</td>
											<td class="center">${ct.ptCreatedDay}</td>
											<td class="center"><input type="submit"
												class="btn btn-default" name="action"
												value="<r:message	code="checktechnology.view" />" /> <input
												type="hidden" name="proName" id="proName" value="${ct.ptId}" />
												<input type="hidden" name="note" id="note"
												value="${ct.ptNote}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->
		<!-- content ends -->
	</div>
	<!--/#content.col-md-0-->
</html>