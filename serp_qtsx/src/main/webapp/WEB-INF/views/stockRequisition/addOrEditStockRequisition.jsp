<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="${pageContext.request.contextPath}/resources/userJs/stockRequisition.js"></script>

<!-- Include libs to use datepicker -->
<script
	src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/themes/charisma-master/css/datepicker.css">

<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>"><r:message
						code="label.home" /></a></li>
			<li><a href="<%=request.getContextPath()%>/stockRequisition"><r:message
						code="label.stockRequisition" /></a></li>
			<li class="active"><r:message
					code="stockRequisition.updateStockRequisition" /></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>
				<r:message code="label.stockRequisition" />
			</h2>
		</div>
		<form:form method="post"
			action="${pageContext.request.contextPath}/stockRequisition/saveDetails"
			commandName="sr">
			<input type="hidden" value="${sr.requisitionId}" name="srId" />
			<div class="panel panel-primary">
				<div class="panel-body">
					<div class="col-md-6">
						<label><r:message code="stockRequisition.department" />:</label>
						<input value="${sr.department}" maxlength="100" name="department"
							required="required" /> <br /> <br /> <label><r:message
								code="stockRequisition.limitInventoryId" />:</label> <input type="text"
							disabled="disabled" name="txtLimitInventory"
							value="${sr.limitInventory.limitInventoryId}" /> <br /> <br />
						<label><r:message code="stockRequisition.creator" />:</label> <input
							type="text" name="txtCreator" disabled="disabled"
							value="${sr.userByCreator.name}" /> <br /> <br />
					</div>
					<div class="col-md-6">
						<label><r:message code="stockRequisition.order" />:</label> <input
							type="text" disabled="disabled" name="txtOrder"
							value="${sr.orders.orderId}" /> <br /> <br /> <label><r:message
								code="stockRequisition.dateWanted" />:</label> <input type="text"
							value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sr.dateWanted}" />"
							name="dateWanted" maxlength="10" class="datepicker"
							required="required" /> <br /> <br /> <label><r:message
								code="stockRequisition.signDate" />:</label> <input type="text"
							disabled="disabled"
							value="<fmt:formatDate pattern="MM/dd/yyyy" value="${sr.createdDate}" />" />
						<br /> <br />
					</div>
					<div class="col-md-12" style="text-align: center">
						<input type="submit" name="btnAction"
							onclick="return confirmSaving();"
							value="<r:message code="stockRequisition.save" />"
							class="btn btn-success" /> <input type="submit"
							value="<r:message code="stockRequisition.exit" />"
							formnovalidate="formnovalidate" id="btnExit" name="btnAction"
							class="btn btn-warning" /> <br /> <br />
					</div>
				</div>
			</div>
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4>
						<r:message code="stockRequisition.purchaseOrder" />
					</h4>
				</div>
				<div class="panel-body">
					<div class="col-md-6">
						<r:message code="stockRequisition.recommendSupplier" />
						:
						<textarea cols="35%" maxlength="255" name="recommendSupplier">${sr.recommendSupplier}</textarea>
					</div>
					<div class="col-md-6">
						<r:message code="stockRequisition.reason" />
						:
						<textarea cols="42%" maxlength="255" name="reason">${sr.reason}</textarea>
					</div>
				</div>
			</div>
			<br />
			<br />
		</form:form>

		<div class="col-md-12">
			<div class="box-content">
				<!-- Button add new detail -->
				<c:if test="${not empty sr.requisitionId}">
					<div>
						<button type="button" id="addNew" class="btn btn-success"
							data-toggle="modal" data-target="#addStockRequisitionDetails"
							formnovalidate>
							<i class="glyphicon glyphicon-plus"></i>
							<r:message code="stockRequisition.addNew" />
						</button>
					</div>
				</c:if>
				<!-- End of button -->
				<br />
				<table id="detailsTable"
					class="table table-striped table-bordered datatable bootstrap-datatable responsive">
					<thead>
						<tr>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.no" /></th>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.name" /></th>
							<th colspan="4" class="center"><r:message
									code="stockRequisition.size" /> (mm)</th>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.material" /></th>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.quantity" /></th>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.unit" /></th>
							<th rowspan="2" class="center"><r:message
									code="stockRequisition.note" /></th>
							<c:if test="${not empty sr.requisitionId}">
								<th rowspan="2" class="center"><r:message
										code="stockRequisition.action" /></th>
							</c:if>
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
								<td><%=i%></td>
								<td>${detail.element.EName}</td>
								<td>${detail.phi}</td>
								<td>${detail.length}</td>
								<td>${detail.width}</td>
								<td>${detail.height}</td>
								<td>${detail.material.materialName}</td>
								<td>${detail.quantity}</td>
								<td>${detail.element.EUnit}</td>
								<td>${detail.note}</td>
								<c:if test="${not empty sr.requisitionId}">
									<td class="center"><div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												<r:message code="stockRequisition.action" />
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><button type="button" class="btn btn-info"
														data-toggle="modal" id="update" data-id="<%=i%>"
														data-target="#updateStockRequisitionDetails"
														formnovalidate>
														<r:message code="stockRequisition.update" />
													</button></li>
												<li><input type="button" class="btn btn-warning"
													id="delete" data-id="<%=i++%>" formnovalidate
													value="<r:message code="stockRequisition.delete" />" /></li>
											</ul>
										</div></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br />
	</div>
</div>

<!-- Update Stock Requisition Details Dialog box -->
<div class="modal fade" id="updateStockRequisitionDetails" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" id="close" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<r:message code="stockRequisition.stockRequisitionDetails"></r:message>
				</h4>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">
				<form:form class="form-horizontal"
					action="${pageContext.request.contextPath}/stockRequisition/editDetails"
					id="editForm" role="form" method="post" commandName="srde">
					<input type="hidden" name="stockRequisitionId" id="srId"
						value="${sr.requisitionId}" />
					<input type="hidden" name="stockRequisitionDetailsId" id="detailId" />
					<input type="hidden" name="detailIndex" id="detailIndex" />
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.name"></r:message></label>
						<div class="col-sm-10">
							<select id="elementSelectBox2" name="elementId">
								<option value="#">Element ID - Element Name</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Phi</label>
						<div class="col-sm-10">
							<input type="number" min="0" max="999999" step="0.01"
								class="form-control" id="txtPhi" name="phi" value="${srde.phi}"
								placeholder="Phi" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.length"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.length}" name="length"
								id="txtLength"
								placeholder="<r:message
								code="stockRequisition.length"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.width"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.width}" name="width"
								id="txtWidth"
								placeholder="<r:message
								code="stockRequisition.width"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.height"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.height}" name="height"
								id="txtHeight"
								placeholder="<r:message
								code="stockRequisition.height"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.material"></r:message></label>
						<div class="col-sm-10">
							<form:select id="materialSelectBox2" path="materialId">
								<option value="#">Material ID - Material Name -
									Material Price</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.quantity"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="1" max="9999" required
								class="form-control" id="txtQuantity" value="${srde.quantity}"
								name="quantity"
								placeholder="<r:message
								code="stockRequisition.quantity"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.note"></r:message></label>
						<div class="col-sm-10">
							<textarea id="txtNote" name="note" maxlength="255"
								class="form-control"
								placeholder="<r:message
								code="stockRequisition.note"></r:message>"
								rows="3" cols="80%">${srde.note}</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">
								<r:message code="stockRequisition.save"></r:message>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- End of dialog box -->

<!-- Add new Stock Requisition Details Dialog box -->
<div class="modal fade" id="addStockRequisitionDetails" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" id="close" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<r:message code="stockRequisition.stockRequisitionDetails"></r:message>
				</h4>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">
				<form:form class="form-horizontal"
					action="${pageContext.request.contextPath}/stockRequisition/addDetails"
					id="addNewForm" role="form" method="post" commandName="srde">
					<input type="hidden" name="stockRequisitionId" id="srId"
						value="${sr.requisitionId}" />
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.name"></r:message></label>
						<div class="col-sm-10">
							<form:select id="elementSelectBox1" path="elementId">
								<option value="#">Element ID - Element Name</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Phi</label>
						<div class="col-sm-10">
							<input type="number" min="0" max="999999" step="0.01"
								class="form-control" id="txtPhi" name="phi" value="${srde.phi}"
								placeholder="Phi" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.length"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.length}" name="length"
								id="txtLength"
								placeholder="<r:message
								code="stockRequisition.length"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.width"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.width}" name="width"
								id="txtWidth"
								placeholder="<r:message
								code="stockRequisition.width"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.height"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" max="999999" step="0.01" required
								class="form-control" value="${srde.height}" name="height"
								id="txtHeight"
								placeholder="<r:message
								code="stockRequisition.height"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.material"></r:message></label>
						<div class="col-sm-10">
							<form:select id="materialSelectBox1" path="materialId">
								<option value="#">Material ID - Material Name -
									Material Price</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.quantity"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="1" max="9999" required
								class="form-control" id="txtQuantity" value="${srde.quantity}"
								name="quantity"
								placeholder="<r:message
								code="stockRequisition.quantity"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="stockRequisition.note"></r:message></label>
						<div class="col-sm-10">
							<textarea id="txtNote" name="note" maxlength="255"
								class="form-control"
								placeholder="<r:message
								code="stockRequisition.note"></r:message>"
								rows="3" cols="80%">${srde.note}</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">
								<r:message code="stockRequisition.save"></r:message>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- End of dialog box -->

<script>
	$('.datepicker').datepicker();

	function confirmSaving() {
		var r = confirm("<r:message code="stockRequisition.confirmSave" />");
		return r;
	}
</script>