<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-lg-10 col-sm-10">
	<form method="post"
		action="<%=request.getContextPath()%>/stockRequisition/examineAction">
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>"><r:message
							code="label.home" /></a></li>
				<li><a href="<%=request.getContextPath()%>/stockRequisition"><r:message
							code="label.stockRequisition" /></a></li>
				<li class="active"><r:message
						code="stockRequisition.examineStockRequisition" /></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-12">
				<!-- Alert box to display save status -->
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
				<!-- End of Alert Box -->

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
					value="${sr.orders.orderId}" readonly="readonly" /> <br /> <br />
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
				<br /> <br />
				<div class="panel panel-info">
					<div class="panel-heading">
						<r:message code="stockRequisition.purchaseOrder" />
					</div>

					<div class="panel-body">
						<div class="col-md-6">
							<label><r:message
									code="stockRequisition.recommendSupplier" />:</label> <br />
							<textarea cols="60%" disabled rows="5%">${sr.recommendSupplier}</textarea>
							<br /> <br /> <label><r:message
									code="stockRequisition.hfad" />:</label> <input type="text" disabled
								value="${sr.userByHfad.name}" /> <br /> <br /> <label><r:message
									code="stockRequisition.note" />:</label>
							<textarea id="hfad_note" name="hfad_note"
								<c:if test="${loginRole == 0}">disabled</c:if>
								<c:if test="${sr.hfadStatus != 2}">disabled</c:if> cols="45%">${sr.hfadComment}</textarea>
						</div>
						<div class="col-md-6">
							<label><r:message code="stockRequisition.reason" />:</label> <br />
							<textarea disabled cols="60%" rows="5%">${sr.reason}</textarea>
							<br /> <br /> <label><r:message
									code="stockRequisition.signDate" />: </label> <input type="text"
								disabled
								value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sr.hfadSignDate}" />" />
							<br /> <br /> <input type="radio"
								<c:if test="${sr.hfadStatus == 1}">checked</c:if>
								name="hfad_status"
								<c:if test="${loginRole == 0}">disabled</c:if>
								onchange="disableHfadNote(this);" id="hfad_accept" value="1" />
							<r:message code="stockRequisition.approved" />
							<br /> <input type="radio" name="hfad_status"
								<c:if test="${loginRole == 0}">disabled</c:if>
								<c:if test="${sr.hfadStatus == 2}">checked</c:if>
								onchange="enableHfadNote(this);" id="hfad_deny" value="2" />
							<r:message code="stockRequisition.unapproved" />
						</div>
					</div>
				</div>
				<br />
				<div class="col-md-6">
					<label> <r:message code="stockRequisition.creator" />:
					</label> <input type="text" value="${sr.userByCreator.name}"
						disabled="disabled" /> <br /> <br /> <label><r:message
							code="stockRequisition.signDate" />:</label> <input type="text"
						value="<fmt:formatDate pattern='MM/dd/yyyy' value="${sr.createdDate}" />"
						disabled="disabled" /> <br />
				</div>
				<div class="col-md-6">
					<label><r:message code="stockRequisition.factoryManager" />:</label>
					<input type="text" value="${sr.userByFactoryManager.name}"
						disabled="disabled" /> <br /> <br /> <label><r:message
							code="stockRequisition.status" />:</label> <br /> <input type="radio"
						name="fm_status" value="1"
						<c:if test="${loginRole == 1}">disabled</c:if>
						<c:if test="${sr.factoryManagerStatus == 1}">checked</c:if>
						onchange="disableFmNote(this);" />
					<r:message code="stockRequisition.approved" />
					<br /> <input type="radio" name="fm_status" value="2"
						onchange="enableFmNote(this);"
						<c:if test="${loginRole == 1}">disabled</c:if>
						<c:if test="${sr.factoryManagerStatus == 2}">checked</c:if> />
					<r:message code="stockRequisition.re-edit" />
					<br /> <input type="radio" name="fm_status" value="3"
						onchange="enableFmNote(this);"
						<c:if test="${loginRole == 1}">disabled</c:if>
						<c:if test="${sr.factoryManagerStatus == 3}">checked</c:if> />
					<r:message code="stockRequisition.cancelled" />
					<br /> <br /> <label><r:message
							code="stockRequisition.signDate" />:</label> <input type="text"
						disabled="disabled"
						value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sr.factoryManagerSignDate}" />" />
					<br /> <br /> <label><r:message
							code="stockRequisition.note" />:</label>
					<textarea name="fm_note" id="fm_note" cols="60%" rows="5%"
						<c:if test="${loginRole == 1}">disabled</c:if>
						<c:if test="${sr.factoryManagerStatus != 2 && sr.factoryManagerStatus != 3}">disabled</c:if>>
					${sr.factoryManagerComment}
				</textarea>
				</div>

				<div class="col-md-12" style="text-align: center">
					<br /> <input type="submit" class="btn btn-primary"
						name="btnAction"
						value="<r:message code="stockRequisition.save" />"
						onclick="return confirmSaving();" /> <input type="submit"
						class="btn btn-warning" name="btnAction"
						value="<r:message code="stockRequisition.exit" />"
						formnovalidate="formnovalidate" />
				</div>
				<div class="col-md-12" style="text-align: right">
					<br /> <i>Last updated on ${sr.lastModifiedDate} by
						${sr.userByLastModifiedUser.name}</i>
				</div>
			</div>
		</div>
	</form>
</div>

<script>
	function confirmSaving() {
		var r = confirm("<r:message code="stockRequisition.confirmSave" />");
		return r;
	}

	var hfad_note = document.getElementById("hfad_note"), fm_note = document
			.getElementById("fm_note");

	function disableHfadNote(radioBtn) {
		hfad_note.disabled = radioBtn.checked;
		hfad_note.value = '';
	}

	function enableHfadNote(radioBtn) {
		hfad_note.disabled = !radioBtn.checked;
	}

	function disableFmNote(radioBtn) {
		fm_note.disabled = radioBtn.checked;
		fm_note.value = '';
	}

	function enableFmNote(radioBtn) {
		fm_note.disabled = !radioBtn.checked;
	}
</script>