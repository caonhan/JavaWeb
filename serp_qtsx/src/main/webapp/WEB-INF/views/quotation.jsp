<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="resources/themes/charisma-master/js/bootbox.min.js"></script>
<script type="text/javascript" src="resources/userJs/Quotation.js"></script>
<script type="text/javascript" src="resources/userJs/quotationPage.js"></script>
<link rel="stylesheet" href="resources/css/quotation.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<title>Quotation</title>
</head>
<body>
	<div class="col-sm-10 col-lg-10">
		<div class="row">
			<ul class="breadcrumb">
	            <li> <a href="home"><r:message code="label.home" /></a> </li>
	            <li> <a href="quotation"><r:message code="label.estimateList" /></a> </li>
	            <li> <a href=""><r:message code="label.addQuotation" /></a> </li>
	        </ul>
		</div>		
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-folder-open"></i>
					<r:message code="quo.quotation" />
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-minimize btn-round btn-default"><i
						class="glyphicon glyphicon-chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<h4>
					<r:message code="quo.customer" />
				</h4>
				<div class="row">
					<c:forEach items="${estimate}" var="item">
						<div class="col-sm-6 col-lg-6">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user red"></i></span> <input type="text"
									class="form-control" id="donvi"
									value="${item.orders.customer.companyName}">
							</div>
						</div>
						<div class="col-sm-3 col-lg-3">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-bullhorn red"></i></span> <input
									type="text" class="form-control" id="dienthoai"
									value="${item.orders.customer.mobilePhone}">
							</div>
						</div>
						<div class="col-sm-3 col-lg-3">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-print red"></i></span> <input type="text"
									class="form-control" id="fax"
									value="${item.orders.customer.fax}">
							</div>
						</div>
					</c:forEach>
				</div>
				<hr>
				<c:forEach items="${quotation}" var="quo">
					<h4><r:message code="quo.quotationinformation" /> </h4>
					<div class="row">
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.no" /></label>
								</div>
								<div class="col-md-6">
									<input type="text" class="form-control" id="idquotation"
										style="width: 243px; height: 35px; margin-bottom: 4px;"
										name="idQuo" value="${quo.quotationId }" disabled="disabled">									
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.quotationvalue" /></label>
								</div>
								<div class="col-md-6">
									<input type="number" min="1" max="20" class="form-control"
										id="QuotationValue"
										style="width: 243px; height: 35px; margin-bottom: 4px;"
										name="valueQuo" value="${quo.numOfValidityDays}">
									<div class="alert alert-danger error errorValidityDay">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.valueDay" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.deadline" /></label>
								</div>
								<div class="col-md-6">
									<input type="number" min="1" max="30" class="form-control"
										id="Deadline"
										style="width: 243px; height: 35px; margin-bottom: 4px;"
										name="deadlineQuo" value="${quo.numOfDaysToComplete}">
									<div class="alert alert-danger error errorCompleteDay">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.valueDay" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<label class="control-label" for="inputSuccess4"><r:message
									code="quo.paymethod" /></label> <br> <label class="control-label"
								for="inputSuccess4" style="width: 75px;"><r:message
									code="quo.first" /></label> <select name="selectorPay1"
								id="PayMethod1" data-rel="chosen">
								<OPTION ${quo.paymentMethod1 == "Cash" ? 'selected' : ''}>Cash</OPTION>
								<OPTION
									${quo.paymentMethod1 == "BankTransfer" ? 'selected' : ''}>BankTransfer</OPTION>
								<OPTION>_____________</OPTION>
							</select> <br> <label class="control-label" for="inputSuccess4"
								style="width: 75px;"><r:message code="quo.second" /></label> <select
								name="selectorPay2" id="PayMethod2" data-rel="chosen">
								<OPTION ${quo.paymentMethod2 == "Cash" ? 'selected' : ''}>Cash</OPTION>
								<OPTION
									${quo.paymentMethod2 == "BankTransfer" ? 'selected' : ''}>BankTransfer</OPTION>
								<OPTION>_____________</OPTION>
							</select>
						</div>
					</div>
					<hr>
					<h4>
						<r:message code="quo.quotationdetail" />
					</h4>
					<div class="row">
						<div class="col-md-5">
							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.productname" /></label>
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" id="productName"
										style="width: 243px; height: 35px; margin-bottom: 4px;">
									<div class="alert alert-danger error errorProductName">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.productNameField" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.quantity" /></label>
								</div>
								<div class="col-md-8">
									<input type="number" min="0" class="form-control" id="quantity"
										style="width: 243px; height: 35px; margin-bottom: 4px;">
									<div class="alert alert-danger error errorQuantity">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.quantityField" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.unit" /></label>
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" id="unit"
										style="width: 243px; height: 35px; margin-bottom: 4px;">
									<div class="alert alert-danger error errorUnit">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.unitField" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-3">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.price" /></label>
								</div>
								<div class="col-md-9">
									<input type="number" min="0" class="form-control" id="price"
										style="width: 243px; height: 35px; margin-bottom: 4px;">
									<div class="alert alert-danger error errorPrice">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<r:message code="error.priceField" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.note" /></label>
								</div>
								<div class="col-md-9">
									<textarea class="autogrow" style="width: 243px; height: 75px;"
										id="note"></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-2">
							<div class="row">
								<div class="col-md-6">
									<button class="btn btn-primary noty btnAddQuoDetail">
										<i class="glyphicon glyphicon-check icon-white"></i>
										<r:message code="quo.add" />
									</button>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-12">
							<table class="table table-striped table-bordered responsive"
								id="listQuoDetail">
								<thead>
									<tr>
										<th><r:message code="quo.no" /></th>
										<th><r:message code="quo.productname" /></th>
										<th><r:message code="quo.quantity" /></th>
										<th><r:message code="quo.unit" /></th>
										<th><r:message code="quo.price" /></th>
										<th><r:message code="quo.amount" /></th>
										<th><r:message code="quo.note" /></th>
										<th><r:message code="quo.edit" /></th>
										<th><r:message code="quo.delete" /></th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message code="quo.amount" /></label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" id="amount"
										style="width: 243px; height: 35px; margin-bottom: 4px; text-align: right;"
										name="amount" disabled="disabled">
								</div>
							</div>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message
											code="quo.vat" /></label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" id="vat"
										style="width: 243px; height: 35px; margin-bottom: 4px; text-align: right;"
										name="vat" disabled="disabled">
								</div>
							</div>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message code="quo.total" /></label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" id="totalAmount"
										style="width: 243px; height: 35px; margin-bottom: 4px; text-align: right;"
										name="totalAmout" disabled="disabled">
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<hr>
				<div class="row">
					<div class="col-md-12">
						<button class="btn btn-primary noty" id="updateQuotation">
							<i class="glyphicon glyphicon-ok icon-white"></i>
							<r:message code="quo.updatequotation" />
						</button>
						<button class="btn btn-primary noty">
							<i class="glyphicon glyphicon-print icon-white"></i>
							<r:message code="quo.print" />
						</button>
					</div>
				</div>
			</div>
		</div>
		<div id="editQuoDetailDialog" style="display: none;" class="form-horizontal">
			<div class="form-group">
				<label for="txtRoleID" class="col-sm-4 control-label"><r:message code="quo.no" /><span style="color: red;">*</span></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtID" name="txtRoleID" disabled="disabled">
				</div>
			</div>
			<div class="form-group">
				<label for="txtRoleID" class="col-sm-4 control-label"><r:message code="quo.productname" /><span style="color: red;">*</span></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtProductName">
					<div class="error erEditProductName"><r:message code="error.productNameField" /></div>
				</div>
			</div>

			<div class="form-group">
				<label for="txtRoleName" class="col-sm-4 control-label"><r:message code="quo.quantity" /><span style="color: red;">*</span></label>
				<div class="col-sm-8">
					<input type="number" class="form-control" id="txtQuantity">
					<div class="error erEditQuantity"><r:message code="error.quantityField" /></div>
				</div>
			</div>
			<div class="form-group">
				<label for="txtRoleName" class="col-sm-4 control-label"><r:message code="quo.price" /><span style="color: red;">*</span></label>
				<div class="col-sm-8">
					<input type="number" class="form-control" id="txtPrice">
					<div class="error erEditPrice"><r:message code="error.priceField" /></div>
				</div>
			</div>
			<div class="form-group">
				<label for="txtRoleName" class="col-sm-4 control-label"><r:message code="quo.unit" /><span style="color: red;">*</span></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtUnit">
					<div class="error erEditUnit"><r:message code="error.unitField" /></div>
				</div>
			</div>
			<div class="form-group">
				<label for="txtDescription" class="col-sm-4 control-label"><r:message code="quo.note" /></label> <br />
				<div class="col-sm-8">
					<textarea class="form-control" id="txtNote"></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtDescription"></span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>