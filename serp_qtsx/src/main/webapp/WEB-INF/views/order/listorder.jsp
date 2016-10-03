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
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="resources/js/ListOrder.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<title><r:message code="order.list" /></title>
<!-- Latest compiled and minified CSS -->

</head>
<body>
<div id="content" class="col-lg-10 col-sm-10">
  <div class="row">
    <ul class="breadcrumb">
      <li><a href="#"><r:message code="order.home" /></a></li>
      <li class="active"><r:message code="order.list" /></li>
    </ul>
  </div>
  <div class="row">
    <div class="col-md-12">
      <header>
        <h2><r:message code="order.list" /></h2>
      </header>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="box-content">
<button id="btnAddOrder" class="btn btn-primary"><r:message code="order.addOrder" /></button>
	<hr>
	<!-- form to create estimate -->
	<form action="editEstimate" method="get" class="formEstimate">
						<input type="hidden" class="estimateId" name="estimateId" /> <input
							type="hidden" class="orderId" name="orderId" />
					</form>
	<table id="listOrder" class="table table-striped display">
		<thead>
			<tr>
				<th><r:message code="order.orderID" /></th>
				<th><r:message code="order.customer" /></th>
				<!-- <th>Status ID</th>-->
				<th><r:message code="order.status" /></th>
				<!-- 
				<th><r:message code="order.approver" /></th>
				<th><r:message code="order.userID" /></th>
				 -->
				<th><r:message code="order.projectName" /></th>
				<!-- <th><r:message code="order.createDate" /></th> -->
				<th><r:message code="order.orderContent" /></th>
				<th><r:message code="order.possibility" /></th>
				<!-- 
				<th><r:message code="order.judgingContent" /></th>
				<th><r:message code="order.approvalContent" /></th>
				-->
				<th><r:message code="order.productName" /></th>
				<th><r:message code="order.amount" /></th>
				<th><r:message code="order.dueDate" /></th>
				<th><r:message code="order.action" /></th>
			</tr>
		</thead>
	</table>
	
	</div>
	</div>
	</div>
	</div>
		
	<!-- This is dialog confirm delete -->
	<div id="deleteOrderDialog" style="display: none;"><span id="messageContent"></span></div>
	
	<!-- This is dialog to edit 1 Order -->
	<div id="editOrderDialog" style="display: none;" class="form-horizontal" name="addOrderForm">
		<div class="form-group">
	       <label for="txtOrderID" class="col-sm-3 control-label"><r:message code="order.orderID" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtOrderID" value="" name="txtOrderID" disabled>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtUserID" class="col-sm-3 control-label"><r:message code="order.userID" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtUserID" value="" name="txtUserId" disabled>
	       </div>
	   </div>			
	   <div class="form-group">
	       <label for="dtpCreateDate" class="col-sm-3 control-label"><r:message code="order.createDate" /></label>
	       <div class="col-sm-9">
	            <input type="text" class="form-control" id="dtpCreateDate" placeholder="<r:message code="order.createDate" />" value="" name="dtpCreateDate">
	            <span style="font-size: 11px;color: red" id="errorDtpCreateDate"></span>    
	        </div>
	    </div>
	    
			
	   <div class="form-group">
	       <label for="txtProjectName" class="col-sm-3 control-label"><r:message code="order.projectName" /><span style="color:red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtProjectName" placeholder="<r:message code="order.projectName" />" name="txtProjectName" required>
	           <span style="font-size: 11px;color: red" id="errorTxtProjectName"></span>
	        </div>
	    </div>
			
		<div class="form-group">
	       <label for="txtCustomerID" class="col-sm-3 control-label"><r:message code="order.customer" /><span style="color:red">*</span></label>
	       <div class="col-sm-9">
                <input type="text" class="form-control" id="txtCustomerID" placeholder="<r:message code="order.customer" />" value="" name="txtCustomerID" required>
            	<span style="font-size: 11px;color: red" id="errorTxtCustomerID"></span>
	        </div>
	    </div>
	    
		<div class="form-group">
		    <label for="txtProductName" class="col-sm-3 control-label"><r:message code="order.productName" /><span style="color:red">*</span></label>
		    <div class="col-sm-9">
                <input type="text" class="form-control" id="txtProductName" placeholder="<r:message code="order.productName" />" value="" name="txtProductName" required>
            	<span style="font-size: 11px;color: red" id="errorTxtProductName"></span>
			</div>
	    </div>    		
			
		<div class="form-group">
			<label for="dtpDueDate" class="col-sm-3 control-label"><r:message code="order.dueDate" /><span style="color:red">*</span></label>
			<div class="col-sm-9">
	           <input type="text" class="form-control" id="dtpDueDate" placeholder="<r:message code="order.dueDate" />" value="" name="dtpDueDate" required>
	           <span style="font-size: 11px;color: red" id="errorDtpDueDate"></span>
			</div>
	    </div>
	    
		<div class="form-group">
			<label for="txtAmount" class="col-sm-3 control-label"><r:message code="order.amount" /><span style="color:red">*</span></label>
			<div class="col-sm-9">
	           <input type="text" class="form-control" id="txtAmount" placeholder="<r:message code="order.amount" />" value="" name="txtAmount" required>
	           <span style="font-size: 11px;color: red" id="errorTxtAmount"></span>
			</div>
	    </div>
	    
	    <div class="form-group">
		    <label for="txtOrderContent" class="col-sm-3 control-label"><r:message code="order.orderContent" /><span style="color:red">*</span></label>
		    <div class="col-sm-9">
                <textarea type="text" class="form-control" id="txtOrderContent" placeholder="<r:message code="order.orderContent" />" value="" name="txtOrderContent" required width='100%' cols="3" rows="3"></textarea>
            	<span style="font-size: 11px;color: red" id="errorTxtOrderContent"></span>
			</div>
	    </div>
	    
		<div class="form-group">
			<label for="txtApprover" class="col-sm-3 control-label"><r:message code="order.approver" /><span style="color:red">*</span></label>
			<div class="col-sm-9">
	           <input type="text" class="form-control" id="txtApprover" name="txtApprover" placeholder="<r:message code="order.currentUser" />" disabled>
	           <span style="font-size: 11px;color: red" id="errorTxtApprover"></span>
			</div>
	    </div>
	    
	   	<div class="form-group">
		    <label for="txtJudgingContent" class="col-sm-3 control-label"><r:message code="order.judgingContent" /><span style="color:red">*</span></label>
		    <div class="col-sm-9">
                <textarea type="text" class="form-control" id="txtJudgingContent" placeholder="<r:message code="order.judgingContent" />" name="txtJudgingContent"></textarea>
            	<span style="font-size: 11px;color: red" id="errorTxtJudgingContent"></span>
			</div>
	    </div>
	    
	 	<div class="form-group">
		    <label for="txtApprovalContent" class="col-sm-3 control-label"><r:message code="order.approvalContent" /><span style="color:red">*</span></label>
		    <div class="col-sm-9">
                <textarea type="text" class="form-control" id="txtApprovalContent" placeholder="<r:message code="order.approvalContent" />" value="" name="txtApprovalContent"></textarea>
            	<span style="font-size: 11px;color: red" id="errorTxtApprovalContent"></span>
			</div>
	    </div>
  		
		<div class="form-group">
	      <label for="sltStatus" class="col-sm-3 control-label"><r:message code="order.status" /><span style="color:red">*</span></label>
	      	<div class="col-sm-9">
	      		<select class="form-control" id="sltStatus">      	
	      		</select>
     		</div>
		 </div>
		<div class="form-group">
	      <label for="stlPossibility" class="col-sm-3 control-label"><r:message code="order.possibility" /><span style="color:red">*</span></label>
	      	<div class="col-sm-9">
	      		<select class="form-control" id="sltPossibility">
	      			<option id="Okay"><r:message code="order.okay" /></option>
	      			<option id="No"><r:message code="order.no" /></option>
	      			<option id="Other"><r:message code="order.other" /></option>	      		
	      		</select>
     		</div>
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
	
	<!-- dialog show when edit order success-->
	<div id="dialogEditSuccess" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold"><r:message code="order.msgEditOrderSuccess" /></h4>
		</div>
	</div>
	<!-- cannot find order ID dialog-->
	<div id="dialogCannotFindCusID" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold"><r:message code="order.msgCannotFindCusID" /></h4>
		</div>
	</div>
	
   	
   	<script type="text/javascript">
	  $(function() {
		    $( "#dtpCreateDate" ).datepicker();
		    $( "#dtpCreateDate").datepicker( "option", "disabled", true );
		    $( "#dtpDueDate" ).datepicker();
		  });
   	</script>
</body>
</html>