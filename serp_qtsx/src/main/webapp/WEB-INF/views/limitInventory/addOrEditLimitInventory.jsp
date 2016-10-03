<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="${pageContext.request.contextPath}/resources/userJs/limitInventory.js"></script>
<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath()%>"><r:message
						code="label.home" /></a></li>
			<li><a href="<%=request.getContextPath()%>/limitInventory"><r:message
						code="label.limitInventory" /></a></li>
			<li class="active"><r:message
					code="limitInventory.updateLimitInventory" /></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>
				<r:message code="label.limitInventory" />
			</h2>
		</div>

		<form:form method="post"
			action="${pageContext.request.contextPath}/limitInventory/saveDetail"
			commandName="li">
			<input type="hidden" value="${li.limitInventoryId}" name="liId" />
			<div class="panel panel-primary">
				<div class="panel-body">
					<div class="col-md-6">
						<label><r:message code="limitInventory.orderID" />:</label> <input
							type="text" disabled="disabled" value="${li.orders.orderId}"
							name="txtOrderId"><br /> <br /> <label><r:message
								code="limitInventory.orderName" />:</label> <input type="text"
							disabled="disabled" value="${li.orders.projectName}"
							name="txtOrderName"> <br /> <br /> <label><r:message
								code="limitInventory.creator" />:</label> <input type="text"
							disabled="disabled" value="${li.userByCreator.name}"
							name="txtCreator" /> <br />
					</div>

					<div class="col-md-6">
						<label><r:message code="limitInventory.dateWanted" />:</label> <input
							type="date"
							value="<fmt:formatDate pattern="yyyy-MM-dd" value="${li.dateWanted}" />"
							required="required" name="dateWanted" /> <br /> <br /> <label><r:message
								code="limitInventory.timeModify" />:</label> <input type="text"
							value="${li.timeModify}" required="required" name="timeModify" />
						<br /> <br /> <label><r:message
								code="limitInventory.signDate" />:</label> <input type="text"
							disabled="disabled" value="${li.createdDate}"
							name="txtCreateDate" /> <br /> <br />
					</div>

					<div class="col-md-12" style="text-align: center">
						<input type="submit" name="btnAction"
							onclick="return confirmSaving();"
							value="<r:message code="limitInventory.save" />"
							class="btn btn-success" /> <input type="submit"
							value="<r:message code="limitInventory.exit" />"
							formnovalidate="formnovalidate" id="btnExit" name="btnAction"
							class="btn btn-warning" /> <br /> <br />
					</div>
				</div>
			</div>
		</form:form>

		<div class="col-md-12">
			<div class="box-content">
				<!-- If requisition id is null then table of details will be hidden -->
				<c:if test="${li.limitInventoryId !=null }">
					<div>
						<button type="button" id="addNew" class="btn btn-success"
							data-toggle="modal" data-target="#addLimitInventoryDetail"
							formnovalidate>
							<i class="glyphicon glyphicon-plus"></i>
							<r:message code="limitInventory.addNew" />
						</button>
					</div>
					<br />
					<table id="detailTable"
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
								<th rowspan="2"><r:message code="limitInventory.action" /></th>
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
									<td><%=i%></td>
									<td>${detail.element.EName}</td>
									<td>${detail.o}</td>
									<td>${detail.x}</td>
									<td>${detail.y}</td>
									<td>${detail.z}</td>
									<td>${detail.material.materialName}</td>
									<td>${detail.quantity}</td>
									<td>${detail.element.EUnit}</td>
									<td>${detail.note}</td>
									<td class="center"><div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												<r:message code="limitInventory.action" />
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><button type="button" class="btn btn-info"
														data-toggle="modal" id="update" data-id="<%=i%>"
														data-target="#updateLimitInventoryDetail" formnovalidate>
														<r:message code="limitInventory.update" />
													</button></li>
												<li><input type="button" class="btn btn-warning"
													id="delete" data-id="<%=i++%>" formnovalidate
													value="<r:message code="limitInventory.delete" />" /></li>
											</ul>
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!-- Add New Limit Inventory Detail Dialog box -->
<div class="modal fade" id="addLimitInventoryDetail" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" id="close" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span> <span class="li-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<r:message code="limitInventory.limitInventoryDetail"></r:message>
				</h4>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">
				<form:form class="form-horizontal"
					action="${pageContext.request.contextPath}/limitInventory/addDetail"
					id="addNewForm" role="form" method="post" commandName="lide">
					<input type="hidden" name="limitInventoryId" id="liId"
						value="${li.limitInventoryId }" />
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.stockName"></r:message></label>
						<div class="col-sm-10">
							<form:select id="elementSelectBox1" path="elementId">
								<option value="#">Element ID - Element Name</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Phi</label>
						<div class="col-sm-10">
							<input type="number" min="0.01" step="0.01" required
								class="form-control" id="txtPhi" name="phi" value="${lide.phi}"
								placeholder="Phi" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.length"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" step="0.01" required
								class="form-control" value="${lide.length}" name="length"
								id="txtLength"
								placeholder="<r:message
								code="limitInventory.length"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.width"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" step="0.01" required
								class="form-control" value="${lide.width}" name="width"
								id="txtWidth"
								placeholder="<r:message
								code="limitInventory.width"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.height"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="0.01" step="0.01" required
								class="form-control" value="${lide.height}" name="height"
								id="txtHeight"
								placeholder="<r:message
								code="limitInventory.height"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.material"></r:message></label>
						<div class="col-sm-10">
							<form:select id="materialSelectBox1" path="materialId">
								<option value="#">Material ID - Material Name -
									Material Price</option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.quantity"></r:message></label>
						<div class="col-sm-10">
							<input type="number" min="1" required class="form-control"
								id="txtQuantity" value="${lide.quantity}" name="quantity"
								placeholder="<r:message
								code="limitInventory.quantity"></r:message>" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><r:message
								code="limitInventory.note"></r:message></label>
						<div class="col-sm-10">
							<textarea id="txtNote" name="note" class="form-control"
								placeholder="<r:message
								code="limitInventory.note"></r:message>"
								rows="3" cols="80%">${lide.note}</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">
								<r:message code="limitInventory.save"></r:message>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- End Add New -->
<!-- Update Limit Inventory Detail Dialog box -->

<!-- End Dialog update -->
<script>
	$('.datepicker').datepicker();

	function confirmSaving() {
		var r = confirm("<r:message code="limitInventory.confirmSave" />");
		return r;
	}
</script>