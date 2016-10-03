<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="resources/userJs/customer.js">
</script>
	
<title>Insert title here</title>
</head>
<body>

	<div class="col-sm-10" id="addForm">
	
		<form class="form-horizontal">
		
		<div class="form-group">
				<label class="col-sm-2 control-label ">ID</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtCustomerID">
					 <span style="font-size: 11px;color: red" id="errorTxtCustomerID" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.companyName" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtCompanyName">
					 <span style="font-size: 11px;color: red" id="errorTxtCompanyName" class="help-block"></span>
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.address" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtAddress">
					 <span style="font-size: 11px;color: red" id="errorTxtAddress" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.assignee" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtAssignnee">
					<span style="font-size: 11px;color: red" id="errorTxtAssignnee" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.telePhone" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtTelephone">
					<span style="font-size: 11px;color: red" id="errorTxtTelephone" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.mobilePhone" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtPhone">
					<span style="font-size: 11px;color: red" id="errorTxtPhone" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">fax</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtFax">
					<span style="font-size: 11px;color: red" id="errorTxtFax" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">Email</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtEmail">
					<span style="font-size: 11px;color: red" id="errorTxtEmail" class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label ">Website</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtWebsite">
					<span style="font-size: 11px;color: red" id="errorTxtWebsite" class="help-block"></span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.createdDate" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtCreatedDate">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label "><r:message code="customer.description" /></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtDescription">
					<span style="font-size: 11px;color: red" id="errorTxtDescription" class="help-block"></span>
				</div>
			</div>
			<div class="form-group text-center ">
				<button type="button" class="btn btn-success glyphicon glyphicon-ok" id="btnSave"><r:message code="customer.save" /> </button>
				<!-- Standard button -->
				<button type="button" class="btn btn-warning glyphicon glyphicon-remove" 
				id="btnCancelAddCustomer" onclick="window.location='/SERP/listcustomer'"> <r:message code="customer.cancel" /></button>
				<button type="button" class="btn btn-warning glyphicon glyphicon-remove" 
				id="btnClearAddCustomer"><r:message code="customer.clear" /></button>
			</div>
		</form>

	</div>
	
	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	
	<script type="text/javascript">
	  $(function() {
		    $( "#txtCreatedDate" ).datepicker();
		    $("#txtCreatedDate").datepicker('setDate', new Date());
		    $( "#txtCreatedDate").datepicker( "option", "disabled", true );
		  });
	  
	  
   	</script>
</body>
</html>