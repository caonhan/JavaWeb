<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<script src="resources/userJs/ShowList_Processing_document.js"></script>
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<title>List hồ sơ gia công</title>
		<!-- Latest compiled and minified CSS -->
	</head>
	<body>
		<div id="content" class="col-lg-10 col-sm-10">
		  <div class="row">
		    <ul class="breadcrumb">
		      <li><a href="/SERP"><r:message code="pd.home" /></a></li>
		      <li class="active"><r:message code="pd.pd" /></li>
		    </ul>
		  </div>
		  <div class="row">
		    <div class="col-md-12">
		        <h2><r:message code="pd.list_of_processing_document" /></h2>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-md-12">
		    	<button id="createProcessingDocument" class="btn btn-primary"><r:message code="pd.create" /></button>
		      <div class="box-content">
				<table id="datatable" class="table table-striped">
					<thead>
						<tr>
							<th><r:message code="pd.id" /></th>
							<th><r:message code="pd.create_by" /></th>
							<th><r:message code="pd.create_date" /></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
				</table>
			   </div>
			  </div>
			</div>
		</div>
			<!-- This is dialog confirm delete -->
			<div id="deleteDialog" style="display: none;"><span id="messageContent"></span></div>
			<div id="Dialog" style="display: none;">
				<table>
					<tbody>
						<tr>
							<td><r:message code="pd.production_order" /></td>
							<td id="production_order"></td>
						</tr>
						<tr>
							<td><r:message code="pd.processing_technology_note" /></td>
							<td id="processing_technology_note"></td>
						</tr>
						<tr>
							<td><r:message code="pd.program_note" /></td>
							<td id="program_note"></td>
						</tr>
						<tr>
							<td><r:message code="pd.operation_trace_note" /></td>
							<td id="operation_trace_note"></td>
						</tr>
						<tr>
							<td><r:message code="pd.limit_inventory_note" /></td>
							<td id="limit_inventory_note"></td>
						</tr>
						<tr>
							<td>+ <r:message code="pd.create_by" /></td>
							<td id="create_by"></td>
						</tr>
						<tr>
							<td>+ <r:message code="pd.create_date" /></td>
							<td id="create_date"></td>
						</tr>
					</tbody>
				</table>
			</div>
	</body>
</html>