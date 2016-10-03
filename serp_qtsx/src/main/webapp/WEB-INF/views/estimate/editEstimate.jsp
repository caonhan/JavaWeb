<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/userJs/estimate.js"></script>
<title>Insert title here</title>
</head>
<body onload="loadData()">
	<input type="hidden" name="modeApprove" id="modeApprove"
		value="${isApproveMode}" />
	<input type="hidden" name="modeEdit" id="modeEdit"
		value="${isEditMode}" />
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-file"></i>
							<r:message code="estimate.estimate" />
						</h2>
					</div>
					<div class="box-content">
						<div class="row">
							<div class="box col-md-12">
								<div class="box-inner">
									<div class="box-header well">
										<h2>
											<r:message code="estimate.order_info" />
										</h2>
									</div>
									<div class="box-content">
										<div class="row">
											<div class="col-md-6">
												<h6>
													<r:message code="estimate.project_name" />
													: <b>${estimate.orders.projectName }</b>
												</h6>
											</div>
											<div class="col-md-6">
												<h6>
													<r:message code="estimate.published_date" />
													: <b>${estimate.orders.createDate }</b>
												</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<form:form modelAttribute="detail" method="POST"
							action="addDetail" id="detailForm">
							<form:hidden path="edId" id="edId" />
							<!-- element info and material info -->
							<div class="row">
								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well">
											<h2>
												<r:message code="estimate.element_info" />
											</h2>
										</div>
										<div class="box-content">
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.id" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="element.EId" id="elementid"
															required="required" maxlength="50" />
														<span id="validateMessagePass" hidden="hidden"><font
															color="green"><i class="glyphicon glyphicon-ok"></i>
														</font></span> <span id="validateMessageFail" hidden="hidden"><font
															color="red"><i class="glyphicon glyphicon-remove"></i>
														</font></span>
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="element.EId" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.name" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="element.EName" required="required"
															id="elementname" maxlength="50" />
														<span id="validateMessagePassN" hidden="hidden"><font
															color="green"><i class="glyphicon glyphicon-ok"></i>
														</font></span> <span id="validateMessageFailN" hidden="hidden"><font
															color="red"><i class="glyphicon glyphicon-remove"></i>
														</font></span>
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="element.EName" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.unit" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="element.EUnit" required="required"
															id="elementunit" maxlength="50" />
														<span id="validateMessagePassU" hidden="hidden"><font
															color="green"><i class="glyphicon glyphicon-ok"></i>
														</font></span> <span id="validateMessageFailU" hidden="hidden"><font
															color="red"><i class="glyphicon glyphicon-remove"></i>
														</font></span>
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="element.EUnit" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.quantity" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edQuantity" min="1" type="number"
															required="required" id="quantity" max="999999"  value="1"/>
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edQuantity" />
													</h6>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--/span-->

								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well">
											<h2>
												<r:message code="estimate.material_info" />
											</h2>
										</div>
										<div class="box-content">
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.material" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:select path="material" required="required"
															id="material">
															<form:option value="">----- <r:message
																	code="estimate.select" /> -----</form:option>
															<form:options items="${materialList}"
																itemLabel="materialName" itemValue="materialId" />
														</form:select>
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="material" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-1">
													<h6>
														<r:message code="estimate.size" />
													</h6>
												</div>
												<div class="col-md-1">
													<h6>Ø</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edPhi" min="0" type="number"
															required="required" id="phi" max="999999" step="0.01" />
														<r:message code="estimate.mm" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edPhi" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-1">
													<h6></h6>
												</div>
												<div class="col-md-1">
													<h6>X</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edX" min="0" type="number"
															required="required" id="x" max="999999" step="0.01" />
														<r:message code="estimate.mm" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edPhi" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-1">
													<h6></h6>
												</div>
												<div class="col-md-1">
													<h6>Y</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edY" min="0" type="number"
															required="required" id="y" max="999999" step="0.01" />
														<r:message code="estimate.mm" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edY" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-1">
													<h6></h6>
												</div>
												<div class="col-md-1">
													<h6>Z</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edZ" min="0" type="number"
															required="required" id="z" max="999999" step="0.01" />
														<r:message code="estimate.mm" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edZ" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.weight" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edMaterialWeight" min="0" type="number"
															id="weight" required="required" max="999999" step="0.01" />
														<r:message code="estimate.kg" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edMaterialWeight" />
													</h6>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>



							<!--  cost info and price info -->

							<div class="row">
								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well">
											<h2>
												<r:message code="estimate.cost_info" />
											</h2>
										</div>
										<div class="box-content">
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.material" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edMaterialCost" min="0" type="number"
															id="materialcost" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edMaterialCost" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.labor" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edLaborCost" min="0" type="number"
															id="laborcost" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edLaborCost" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.equipment" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edEquipmentCost" min="0" type="number"
															id="equipmentcost" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edEquipmentCost" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.tool" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edToolCost" min="0" type="number"
															id="toolcost" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edToolCost" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.external" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edExternalCost" min="0" type="number"
															id="externalcost" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edExternalCost" />
													</h6>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--/span-->

								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well">
											<h2>
												<r:message code="estimate.price" />
											</h2>
										</div>
										<div class="box-content">
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.price_unit" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edPrice" min="0" type="number"
															id="price" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edPrice" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.total_price" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<form:input path="edTotalCost" min="0" type="number"
															id="totalprice" required="required" />
														<r:message code="estimate.vnd" />
													</h6>
												</div>
												<div class="col-md-4">
													<h6>
														<form:errors path="edTotalCost" />
													</h6>
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">
													<h6>
														<r:message code="estimate.in_words" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6></h6>
												</div>
												<div class="col-md-4">
													<h6></h6>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- task buttons -->
								<div class="box col-md-6">
									<div class="row">
										<div class="col-md-12">
											<form:button class="button btn btn-success" name="addDetail">
												<i class="glyphicon glyphicon-plus icon-white"></i>
												<r:message code="estimate.add" />
											</form:button>
											<form:button class="button btn btn-info" name="editDetail">
												<i class="glyphicon glyphicon-edit icon-white"></i>
												<r:message code="estimate.edit" />
											</form:button>
											<form:button class="button btn btn-danger"
												disabled="disabled" name="deleteDetail">
												<i class="glyphicon glyphicon-trash icon-white"></i>
												<r:message code="estimate.delete" />
											</form:button>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>

						</form:form>
						<form:form modelAttribute="estimate" method="POST"
							id="estimateForm">
							<form:hidden path="esId" />
							<input type="hidden" name="publishedDate"
								value="${estimate.esPublishedDate}" />
							<input type="hidden" name="creatorId"
								value="${estimate.userByEsCreatorId.userId}" />
							<input type="hidden" name="orderId"
								value="${estimate.orders.orderId}" />
							<!-- status info -->
							<div class="row">
								<div class="box col-md-12">
									<div class="box-inner">
										<div class="box-header well">
											<h2>
												<r:message code="estimate.approval_info" />
											</h2>
										</div>
										<div class="box-content">
											<div class="row">
												<div class="col-md-1">
													<h6>
														<r:message code="status.status" />
													</h6>
												</div>
												<c:forEach var="stt" items="${statusList}">
													<div class="col-md-2">
														<c:set var="name">
															<r:message code="status.${stt.statusName}" />
														</c:set>
														<form:radiobutton path="status" value="${stt}"
															label="${name}" class="app" />
													</div>
												</c:forEach>
											</div>
											<div class="row">
												<div class="col-md-1">
													<h6>
														<r:message code="estimate.comment" />
													</h6>
												</div>
												<div class="col-md-6">
													<h6>
														<textarea class="col-md-12 app" name="comment"
															maxlength="200">${estimate.esApproveContent}</textarea>
													</h6>
													<h6>
														<form:errors path="esApproveContent" />
													</h6>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="messageDialog" style="display: none;">
								<span id="messageContent"></span>
							</div>
							<div id="dialog" style="display: none;">
								<span id="messageContent"></span>
							</div>
							<!--  table -->
							<table id="listEstimateDetail"
								class="table table-striped table-bordered bootstrap-datatable responsive table-hover">
								<thead>
									<tr>
										<th align="center" rowspan="2"><r:message
												code="estimate.no" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.element_id" /></th>
										<th align="center" colspan="4"><r:message
												code="estimate.embryos" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.material" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.quantity" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.unit" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.weight" /><br />(<r:message
												code="estimate.kg" />)</th>
										<th align="center" rowspan="2"><r:message
												code="estimate.cost_material" /><br />
										<r:message code="estimate.vnd" /></th>
										<th align="center" colspan="4"><r:message
												code="estimate.cost" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.price_unit" /><br />
										<r:message code="estimate.vnd" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.total_price" /><br />
										<r:message code="estimate.vnd" /></th>
										<th align="center" rowspan="2"><r:message
												code="estimate.select" /></th>
									</tr>
									<tr>
										<th align="center">Ø</th>
										<th align="center">X</th>
										<th align="center">Y</th>
										<th align="center">Z</th>
										<th align="center"><r:message code="estimate.labor" /></th>
										<th align="center"><r:message code="estimate.equipment" /></th>
										<th align="center"><r:message code="estimate.tool" /></th>
										<th align="center"><r:message code="estimate.external" /></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>

							<!-- buttons -->
							<div class="row">
								<div class="box col-md-6"></div>
								<div class="box col-md-6">
									<div class="row">
										<div class="col-md-12">
											<form:button class="button btn btn-success"
												name="saveEstimate" id="btnSaveEstimate">
												<i class="glyphicon glyphicon-floppy-save icon-white"></i>
												<r:message code="estimate.save" />
											</form:button>
											<form:button class="button btn btn-danger"
												name="cancelEditEstimate">
												<i class="glyphicon glyphicon-remove icon-white"></i>
												<r:message code="estimate.cancel" />
											</form:button>
										</div>
									</div>
								</div>
							</div>
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
</body>
</html>