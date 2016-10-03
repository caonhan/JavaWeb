<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script src="resources/userJs/customer.js"></script>

<title ></title>
<!-- Latest compiled and minified CSS -->

</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<div class="row">
			<ul class="breadcrumb">
				<li><a href="#"><r:message code="customer.home" /></a></li>
				<li class="active"><r:message code="customer.customer" /></li>
			</ul>
		</div>
<button id="btnAddCustomer" class="btn btn-primary" type="button"
						onclick="window.location='/SERP/addcustomer'"><r:message code="customer.add"/></button>
		<div class="row">
			<div class="col-md-12">
				<header>
				<h2><r:message code="customer.list"/></h2>
				</header>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="box-content">
					
					<hr>
					<table class="table table-border display responsive" id="listCustomer">
						<thead>
							<tr>
								<th class="col-sm-1">ID</th>
								<th class="col-sm-2"><r:message code="customer.companyName"/></th>
								<th class="col-sm-2"><r:message code="customer.assignee"/></th>
								<th class="col-sm-3"><r:message code="customer.address"/></th>
								<th class="col-sm-2"><r:message code="customer.mobilePhone"/></th>
								<th></th>


							</tr>
						</thead>
					</table>


				</div>
			</div>
		</div>
	</div>
	<!-- This is dialog confirm delete -->
	<div id="deleteCustomerDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	
	<!-- This is dialog detail -->

	
	<div id="detailCustomerDialog" style="display: none;" class="form-horizontal" >
	<div class="form-group">
				<label class="col-sm-3 control-label ">ID</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCustomerID">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.companyName"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCompanyName">
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.address"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtAddress">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.assignee"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtAssignnee">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.telePhone"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTelephone">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.mobilePhone"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtPhone">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">fax</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFax">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">Email</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtEmail">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">Website</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtWebsite">
				</div>
			</div>
	
		

		<div class="form-group" id="createdBy">
				<label class="col-sm-3 control-label "><r:message code="customer.createdBy"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCreatedBy">
				</div>
		</div>
		<div class="form-group" id="createdDate">
				<label class="col-sm-3 control-label "><r:message code="customer.createdDate"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCreatedDate">
				</div>
		</div>
		<div class="form-group" id="modifiedBy">
				<label class="col-sm-3 control-label " ><r:message code="customer.modifiedBy"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtmodifiedBy">
				</div>
		</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.description"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtDescription">
				</div>
			</div>
			
		
  	</div>
  	<!--  edit dialig -->
  	
  	<div id="editCustomerDialog" style="display: none;" class="form-horizontal" >
	<div class="form-group">
				<label class="col-sm-3 control-label ">ID</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCustomerID">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.companyName"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCompanyName">
					<span style="font-size: 11px;color: red" id="errorTxtCompanyName" class="help-block"></span>
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.address"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtAddress">
					<span style="font-size: 11px;color: red" id="errorTxtAddress" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.assignee"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtAssignnee">
					<span style="font-size: 11px;color: red" id="errorTxtAssignnee" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.telePhone"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTelephone">
					<span style="font-size: 11px;color: red" id="errorTxtTelephone" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.mobilePhone"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtPhone">
					<span style="font-size: 11px;color: red" id="errorTxtPhone" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">fax</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFax">
					<span style="font-size: 11px;color: red" id="errorTxtFax" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">Email</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtEmail">
					<span style="font-size: 11px;color: red" id="errorTxtEmail" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label ">Website</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtWebsite">
					<span style="font-size: 11px;color: red" id="errorTxtWebsite" class="help-block"></span>
				</div>
			</div>
	
			<div class="form-group">
				<label class="col-sm-3 control-label "><r:message code="customer.description"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtDescription">
					<span style="font-size: 11px;color: red" id="errorTxtDescription" class="help-block"></span>
				</div>
			</div>
			
		
  	</div>
</body>
</html>