<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Find Factory Manager Status -->
<c:if test="${sr.factoryManagerStatus == 0}">
	<r:message code="stockRequisition.approveAwaiting" var="fmStatus" />
</c:if>
<c:if test="${sr.factoryManagerStatus == 1}">
	<r:message code="stockRequisition.approved" var="fmStatus" />
</c:if>
<c:if test="${sr.factoryManagerStatus == 2}">
	<r:message code="stockRequisition.re-edit" var="fmStatus" />
</c:if>
<c:if test="${sr.factoryManagerStatus == 3}">
	<r:message code="stockRequisition.cancelled" var="fmStatus" />
</c:if>

<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>"><r:message
						code="label.home" /></a></li>
			<li><a href="<%=request.getContextPath()%>/stockRequisition"><r:message
						code="label.stockRequisition" /></a></li>
			<li class="active"><r:message
					code="stockRequisition.stockRequisitionDetails" /></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-12">
			<!-- Alert box for save status -->
			<c:if test="${saveStatus == false}">
				<div class="alert alert-danger fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong><r:message
							code="stockRequisition.saveFailedMessage" /></strong>
				</div>
			</c:if>
			<c:if test="${saveStatus == true}">
				<div class="alert alert-success fade in">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong><r:message
							code="stockRequisition.saveSuccessMessage" /></strong>
				</div>
			</c:if>
			<!-- End of Alert box -->
			<header>
				<h2>
					<r:message code="label.stockRequisition" />
				</h2>
			</header>
		</div>
		<div class="col-md-6">
			<label> <r:message code="stockRequisition.department" />:
			</label> <input type="text" value="${sr.department}" readonly="readonly" />
			<br /> <br /> <label><r:message
					code="stockRequisition.limitInventoryId" />:</label> <input type="text"
				readonly="readonly" value="${sr.limitInventory.limitInventoryId}" />
		</div>
		<div class="col-md-6">
			<label><r:message code="stockRequisition.dateWanted" />:</label> <input
				type="text"
				value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sr.dateWanted}" />"
				readonly="readonly" /> <br /> <br /> <label><r:message
					code="stockRequisition.order" />:</label> <input type="text"
				value="${sr.orders.orderId}" readonly="readonly" />
		</div>

		<div class="col-md-12">
			<div class="box-content">
				<br />
				<table
					class="table table-striped table-bordered datatable bootstrap-datatable responsive">
					<thead>
						<tr>
							<th rowspan="2"><r:message code="stockRequisition.no" /></th>
							<th rowspan="2"><r:message code="stockRequisition.name" /></th>
							<th rowspan="2"><r:message code="stockRequisition.material" /></th>
							<th rowspan="2"><r:message code="stockRequisition.quantity" /></th>
							<th rowspan="2"><r:message code="stockRequisition.unit" /></th>
							<th colspan="4" align="center"><r:message
									code="stockRequisition.size" /> (mm)</th>
							<th rowspan="2"><r:message code="stockRequisition.note" /></th>
						</tr>
						<tr>
							<th>&Phi;</th>
							<th>L</th>
							<th>W</th>
							<th>H</th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
						%>
						<c:forEach var="detail" items="${sr.stockRequisitionDetailses}">
							<tr>
								<td><%=i++%></td>
								<td>${detail.element.EName}</td>
								<td>${detail.material.materialName}</td>
								<td>${detail.quantity}</td>
								<td>${detail.element.EUnit}</td>
								<td>${detail.phi}</td>
								<td>${detail.length}</td>
								<td>${detail.width}</td>
								<td>${detail.height}</td>
								<td>${detail.note}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<br />

			<!-- Purchase Order panel -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<r:message code="stockRequisition.purchaseOrder" />
				</div>
				<div class="panel-body">
					<div class="col-md-6">
						<label><r:message
								code="stockRequisition.recommendSupplier" />:</label> <br />
						<textarea cols="60%" readonly="readonly" rows="5%">${sr.recommendSupplier}</textarea>
						<br /> <br /> <label><r:message
								code="stockRequisition.hfad" />:</label> <input type="text"
							readonly="readonly" value="${sr.userByHfad.name}" /> <br /> <br />
						<label><r:message code="stockRequisition.note" />:</label>
						<textarea cols="35%" readonly>${sr.hfadComment}</textarea>
					</div>
					<div class="col-md-6">
						<label><r:message code="stockRequisition.reason" />:</label> <br />
						<textarea cols="60%" readonly="readonly" rows="5%">${sr.reason}</textarea>
						<br /> <br /> <label><r:message
								code="stockRequisition.signDate" />: </label> <input type="text"
							readonly="readonly"
							value="<fmt:formatDate pattern='MM/dd/yyyy' value="${sr.hfadSignDate}" />" />
						<br /> <br /> <input type="radio"
							<c:if test="${sr.hfadStatus == 1}">checked</c:if> disabled
							name="hfad_status" value="accept" />
						<r:message code="stockRequisition.approved" />
						<br /> <input type="radio"
							<c:if test="${sr.hfadStatus == 2}">checked</c:if> disabled
							name="hfad_status" value="deny" />
						<r:message code="stockRequisition.unapproved" />
					</div>
				</div>
			</div>
			<br /> <br />
			<!-- End of Purchase Order panel -->

			<div class="col-md-6">
				<label> <r:message code="stockRequisition.creator" />:
				</label> <input type="text" value="${sr.userByCreator.name}"
					readonly="readonly" /> <br /> <br /> <label><r:message
						code="stockRequisition.signDate" />:</label> <input type="text"
					value="<fmt:formatDate pattern='MM/dd/yyyy' value="${sr.createdDate}" />"
					readonly="readonly" /><br />
			</div>
			<div class="col-md-6">
				<label><r:message code="stockRequisition.factoryManager" />:</label>
				<input type="text" value="${sr.userByFactoryManager.name}"
					readonly="readonly" /> <br /> <br /> <label><r:message
						code="stockRequisition.status" />:</label> <input type="text"
					readonly="readonly" value="${fmStatus}" /> <br /> <br /> <label><r:message
						code="stockRequisition.signDate" />:</label> <input type="text"
					readonly="readonly"
					value="<fmt:formatDate pattern='MM/dd/yyyy' value="${sr.factoryManagerSignDate}" />" />
				<br /> <br /> <label><r:message
						code="stockRequisition.note" />:</label>
				<textarea readonly="readonly" cols="60%" rows="5%">${sr.factoryManagerComment}</textarea>
			</div>

			<!-- Button delete, update, download, exit -->
			<div class="col-md-12" style="text-align: center">
				<br />
				<form method="get"
					action="<%=request.getContextPath()%>/stockRequisition/detailsAction">
					<input type="submit" class="btn btn-danger" name="btnAction"
						onclick="return confirmDeleting();"
						value="<r:message code="stockRequisition.delete" />" /> <input
						type="submit" class="btn btn-primary" name="btnAction"
						<c:if test="${sr.factoryManagerStatus == 1 && sr.hfadStatus == 1}">disabled</c:if>
						value="<r:message code="stockRequisition.update" />" /> <input
						type="submit" class="btn btn-warning" name="btnAction"
						value="<r:message code="stockRequisition.exit" />" />
				</form>
			</div>
			<div class="col-md-12" style="text-align: right">
				<br /> <i>Last updated on ${sr.lastModifiedDate} by
					${sr.userByLastModifiedUser.name}</i>
			</div>
		</div>
	</div>
</div>

<script>
	function confirmDeleting() {
		var r = confirm("<r:message code="stockRequisition.confirmDel" />");
		return r;
	}
</script>