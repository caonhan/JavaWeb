<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!--Find Factory Manager Status-->
	<c:if test="${li.factoryManagerStatus == 0}">
		<r:message code="limitInventory.approveAwaiting" var="fmStatus" />
	</c:if>
	<c:if test="${li.factoryManagerStatus == 1}">
		<r:message code="limitInventory.approved" var="fmStatus" />
	</c:if>
	<c:if test="${li.factoryManagerStatus == 2}">
		<r:message code="limitInventory.re-edit" var="fmStatus" />
	</c:if>
	<c:if test="${li.factoryManagerStatus == 3}">
		<r:message code="limitInventory.cancelled" var="fmStatus" />
	</c:if>

	<!-- Find Directorate Status -->
	<c:if test="${li.directorateStatus == 0}">
		<r:message code="limitInventory.approveAwaiting" var="dStatus" />
	</c:if>
	<c:if test="${li.directorateStatus == 1}">
		<r:message code="limitInventory.approved" var="dStatus" />
	</c:if>
	<c:if test="${li.directorateStatus == 2}">
		<r:message code="limitInventory.re-edit" var="dStatus" />
	</c:if>
	<c:if test="${li.directorateStatus == 3}">
		<r:message code="limitInventory.cancelled" var="dStatus" />
	</c:if>

	<div class="col-lg-10 col-sm-10">
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>"><r:message
							code="label.home" /></a></li>
				<li><a href="<%=request.getContextPath()%>/limitInventory"><r:message
							code="label.limitInventory" /></a></li>
				<li class="active"><r:message
						code="limitInventory.limitInventoryDetail" /></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-md-12">
				<h2>
					<r:message code="label.limitInventory" />
				</h2>				
			</div>
			<div class="col-md-6">
				<label> <r:message code="limitInventory.orderID" />:
				</label> <input type="text" value="${li.orders.orderId}" readonly="readonly" />
				<br /> <br /> <label> <r:message
						code="limitInventory.orderName" />:
				</label> <input type="text" value="${li.orders.projectName}"
					readonly="readonly" />
			</div>
			<div class="col-md-6">
				<label><r:message code="limitInventory.dateWanted" />:</label> <input
					type="text"
					value="<fmt:formatDate pattern="MM/dd/yyyy" value="${li.dateWanted}" />"
					readonly="readonly" /> <br /> <br /> <label><r:message
						code="limitInventory.timeModify" />:</label> <input type="text"
					value="${li.timeModify}" readonly="readonly" />
			</div>
			<br />
			<div class="col-md-12">
				<div class="box-content">
					<table
						class="table table-striped table-bordered datatable bootstrap-datatable responsive">
						<thead>
							<tr>
								<th rowspan="2"><r:message code="limitInventory.no" /></th>
								<th rowspan="2"><r:message code="limitInventory.stockName" /></th>
								<th colspan="4" align="center"><r:message
										code="limitInventory.specification" /> (mm)</th>
								<th rowspan="2"><r:message code="limitInventory.material" /></th>
								<th rowspan="2"><r:message code="limitInventory.quantity" /></th>
								<th rowspan="2"><r:message code="limitInventory.unit" /></th>
								<th rowspan="2"><r:message code="limitInventory.note" /></th>
							</tr>
							<tr>
								<th>&Phi;</th>
								<th>X</th>
								<th>Y</th>
								<th>Z</th>
							</tr>
						</thead>
						<tbody>
							<%
								int i = 1;
							%>
							<c:forEach var="detail" items="${li.limitInventoryDetails}">
								<tr>
									<td><%=i++%></td>
									<td>${detail.element.EName}</td>
									<td>${detail.o}</td>
									<td>${detail.x}</td>
									<td>${detail.y}</td>
									<td>${detail.z}</td>
									<td>${detail.material.materialName}</td>
									<td>${detail.quantity}</td>
									<td>${detail.element.EUnit}</td>
									<td>${detail.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-md-4">
				<label><r:message code="limitInventory.factoryManager" />:</label>
				<input type="text" value="${li.userByFactoryManagerId.name}"
					readonly="readonly" /> <br /> <br /> <label><r:message
						code="limitInventory.status" />:</label> <input type="text"
					readonly="readonly" value="${fmStatus}" /> <br /> <br />
			</div>

			<div class="col-md-4">
				<label><r:message code="limitInventory.directorate" />:</label> <input
					type="text" value="${li.userByDirectorateId.name}"
					readonly="readonly" /> <br /> <br /> <label><r:message
						code="limitInventory.status" />:</label> <input type="text"
					readonly="readonly" value="${dStatus}" /> <br /> <br />
			</div>

			<div class="col-md-4">
				<label><r:message code="limitInventory.creator" />:</label> <input
					type="text" value="${li.userByCreator.name}" readonly="readonly" />
				<br /> <br /> <label><r:message
						code="limitInventory.createdDate" />:</label> <input type="text"
					readonly="readonly" value="${li.createdDate}" /> <br /> <br />
			</div>

			<div class="col-md-12" style="text-align: center">
				<form method="get"
					action="<%=request.getContextPath()%>/limitInventory/detailAction">
					<input type="submit" class="btn btn-danger" name="btnAction"
						onclick="return confirmDeleting();"
						value="<r:message code="limitInventory.delete" />" /> <input
						type="submit" class="btn btn-success" name="btnAction"
						value="<r:message code="limitInventory.update" />" /> <input
						type="submit" class="btn btn-warning" name="btnAction"
						value="<r:message code="limitInventory.exit" />" />
				</form>
			</div>
		</div>
	</div>
	<script>
		function confirmDeleting() {
			var r = confirm("<r:message code="limitInventory.confirmDel" />");
			return r;
		}
	</script>
</body>
</html>