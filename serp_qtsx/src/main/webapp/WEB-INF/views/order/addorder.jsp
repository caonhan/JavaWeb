<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script src="resources/userJs/addOrder.js"></script>
<title><r:message code="order.addOrder" /></title>
<!-- Latest compiled and minified CSS -->

</head>
<body>
<div class="col-lg-10 col-sm-10">	
  <div class="row">
    <ul class="breadcrumb">
      <li><a href="#"><r:message code="order.home" /></a></li>
      <li class="active"><r:message code="order.addOrder" /></li>
    </ul>
  </div>
  <div id="addForm" >
		<div class="form-group">
			<div class="form-group" >
		    <label for="txtOrderID" class="col-sm-2 control-label"><r:message code="order.orderID" /><span style="color:red">*</span></label>
		    <div class="col-sm-4">
                <input type="text" class="form-control" id="txtOrderID" placeholder="<r:message code="order.orderID" />" value="" name="txtOrderID" required>
            	<span style="font-size: 11px;color: red" id="errorTxtOrderID"></span>
			</div>
			</div>
			<div class="form-group" >
			<label for="dtpCreateDate" class="col-sm-2 control-label"><r:message code="order.createDate" /><span style="color:red">*</span></label>
			<div class="col-sm-4">
	            <input type="text" class="form-control" id="dtpCreateDate" placeholder="<r:message code="order.createDate" />" value="" name="dtpCreateDate" required>
	            <span style="font-size: 11px;color: red" id="errorDtpCreateDate"></span>    
			</div>
			</div>
		</div>
		<div class="form-group">
		<div class="form-group" >
		    <label for="txtUserID" class="col-sm-2 control-label"><r:message code="order.userID" /><span style="color:red">*</span></label>
		    <div class="col-sm-4">
                <input type="text" class="form-control" id="txtUserID" placeholder="<r:message code="order.currentUser" />" value="" name="txtUserID" disabled>
            	<span style="font-size: 11px;color: red" id="errorTxtUserID"></span>
			</div>
			</div>
			<div class="form-group" >
		    <label for="txtCustomerID" class="col-sm-2 control-label"><r:message code="order.customer" /><span style="color:red">*</span></label>
		    <div class="col-sm-4">
                <input type="text" class="form-control" id="txtCustomerID" placeholder="<r:message code="order.customer" />" value="" name="txtCustomerID" required>
            	<span style="font-size: 11px;color: red" id="errorTxtCustomerID"></span>
			</div>
			</div>
		</div>
		<div class="form-group">
			<div class="form-group" >
			<label for="txtProjectName" class="col-sm-2 control-label"><r:message code="order.projectName" /><span style="color:red">*</span></label>
			<div class="col-sm-4">
	           <input type="text" class="form-control" id="txtProjectName" placeholder="<r:message code="order.projectName" />" value="" name="txtProjectName" required>
	           <span style="font-size: 11px;color: red" id="errorTxtProjectName"></span>
			</div>
			</div>
			<div class="form-group" >
		    <label for="txtProductName" class="col-sm-2 control-label"><r:message code="order.productName" /><span style="color:red">*</span></label>
		    <div class="col-sm-4">
                <input type="text" class="form-control" id="txtProductName" placeholder="<r:message code="order.productName" />" value="" name="txtProductName" required>
            	<span style="font-size: 11px;color: red" id="errorTxtProductName"></span>
			</div>
			</div>
		</div>	
		<div class="form-group">
			<div class="form-group" >
			<label for="dtpDueDate" class="col-sm-2 control-label"><r:message code="order.dueDate" /><span style="color:red">*</span></label>
			<div class="col-sm-4">
	           <input type="text" class="form-control" id="dtpDueDate" placeholder="<r:message code="order.dueDate" />" value="" name="dtpDueDate" required>
	           <span style="font-size: 11px;color: red" id="errorDtpDueDate"></span>
			</div>
			</div>
			<div class="form-group" >
			<label for="txtAmount" class="col-sm-2 control-label"><r:message code="order.amount" /><span style="color:red">*</span></label>
			<div class="col-sm-4">
	           <input type="text" class="form-control" id="txtAmount" placeholder="<r:message code="order.amount" />" value="" name="txtAmount" required>
	           <span style="font-size: 11px;color: red" id="errorTxtAmount"></span>
			</div>
			</div>
		</div>
		<div class="form-group">
		    <label for="txtOrderContent" class="col-sm-2 control-label"><r:message code="order.orderContent" /><span style="color:red">*</span></label>
		    <div class="col-sm-10">
                <textarea type="text" class="form-control" id="txtOrderContent" placeholder="<r:message code="order.orderContent" />" value="" name="txtOrderContent" required width='100%' cols="2" rows="2"></textarea>
            	<span style="font-size: 11px;color: red" id="errorTxtOrderContent"></span>
			</div>
		</div>
		<div class="form-group" style="margin-left:500px">
				<button id="btnSave" class="btn btn-primary btn-success"><r:message code="order.save" /></button>
				<button id="btnClear" class="btn btn-primary btn-info "><r:message code="order.clear" /></button>
		</div>
	</div>
	
        
	<div id="listCustomerDialog" style="display: none;" class="form-horizontal">
	<table id="listCustomer" class=" table table-striped table-bordered table-hover table-responsive">
		<thead>
			<tr>
				<th class="col-sm-1"><r:message code="customer.id" /></th>
				<th class="col-sm-2"><r:message code="customer.assignee" /></th>
				<th class="col-sm-2"><r:message code="customer.company" /></th>
				<th class="col-sm-3"><r:message code="customer.address" /></th>
				<th class="col-sm-2"><r:message code="customer.phone" /></th>
				<th class="col-sm-2"><r:message code="customer.option" /></th>
			</tr>
		</thead>
	</table>
	</div> 
	
	<!-- dialog show when add new order success-->
	<div id="dialogInsertSuccess" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold"><r:message code="order.msgAddNewOrderSuccess" /></h4>
		</div>
	</div>
	
	<!-- validate ad new order dialog-->
	<div id="dialogValidateAddOrder" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold"><r:message code="order.msgValidateAddOrder" /></h4>
		</div>
	</div>
	
	<!-- cannot find order ID dialog-->
	<div id="dialogCannotFindCusID" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold"><r:message code="order.msgCannotFindCusID" /></h4>
		</div>
	</div>
<!--  
		<div class="col-md-12">
	 		<div class="box-content">
	     		<table id="listOrder" class="table table-striped display">
					<thead>
						<tr>
							<th>Order ID</th>
							<th>Customer ID</th>
							<th>User ID</th>
							<th>Project Name</th>
							<th>Create Date</th>
							<th>Order Content</th>
							<th>Product Name</th>
							<th>Amount Of Product</th>
							<th>Due Date</th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
-->
	</div>
	
	<script type="text/javascript">
	  $(function() {
		    $( "#dtpCreateDate" ).datepicker();
		    $("#dtpCreateDate").datepicker('setDate', new Date());
		    $( "#dtpCreateDate").datepicker( "option", "disabled", true );
		  });
	  $(function() {
		    $( "#dtpDueDate" ).datepicker();
		  });
	  
   	</script>
</body>
</html>