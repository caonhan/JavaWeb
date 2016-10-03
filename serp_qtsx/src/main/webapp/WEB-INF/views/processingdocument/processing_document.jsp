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
	<title><r:message code="pd.pd" /></title>
	<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">	
	<link rel="stylesheet" type="text/css" href="resources/themes/charisma-master/css/style_hosogiacong.css">
	<script src="resources/userJs/ProcessingDocument.js"></script>
  </head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
      <div class="row">
        <ul class="breadcrumb">
          <li><a href="/SERP/processing-document-viewall"><r:message code="pd.back" /></a></li>
        </ul>
      </div>
      <div class="row">	
        <div class="col-md-10">
           <h2><r:message code="pd.create_processing_document_in" /></h2>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10">
          <div class="box-content">
      	    <p id="intro"><r:message code="pd.introduction" /> </p> 
      	    <p id="remind">(*) <r:message code="pd.is_required" /> </p> 
            <table id="datatable" class="datatable" cellspacing="0" width="100%">
               <tr>
					<td><r:message code="pd.production_order" />*</td>
					<td>
						<select id="productionOrderSelect">
							<option></option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="view_production_order"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="download_production_order"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.processing_technology_note" /></td>
					<td>
						<select id="processing_technology_note">
							<option></option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="view_processing_technology_note"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="download_processing_technology_note"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.program_note" /></td>
					<td>
						<select id="program_note">
							<option></option>
							<option value="PN001">PN001</option>
							<option value="PN002">PN002</option>
							<option value="PN003">PN003</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="view_program_note"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="download_program_note"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.operation_trace_note" />*</td>
					<td>
						<select id = operation_trace_note>
							<option></option>
							<option value="OTN001">OTN001</option>
							<option value="OTN002">OTN002</option>
							<option value="OTN003">OTN003</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="view_operation_trace_note"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="download_operation_trace_note"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.limit_inventory_note" />*</td>
					<td>
						<select id="limitInventorySelect">
							<option></option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="view_limit_inventory_note"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-primary" id="download_limit_inventory_note"><r:message code="pd.download" /></button>
					</td>
				</tr>				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><button type="button" class="btn btn-primary" id="send"><r:message code="pd.send" /></button></td>
				</tr>
            </table>
          <div id="Dialog" style="display: none;"><span id="messageContent"></span></div>
          <div id="Dialog_detail" style="display: none;">
				<table>
					<tbody>
						<tr>
							<td>+ <r:message code="productionorder.order" /></td>
							<td id="order"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.quantity" /></td>
							<td id="quantity"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.unit" /></td>
							<td id="unit"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.starttime" /></td>
							<td id="starttime"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.finishtime" /></td>
							<td id="finishtime"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.processtechnology" /></td>
							<td id="processtechnology"></td>
						</tr>
						<tr>
							<td>+ <r:message code="productionorder.factoryManager" /></td>
							<td id="factoryManager"></td>
						</tr>
					</tbody>
				</table>
			</div>
        </div>
      </div>
    </div>
  </div>	
</body>
</html>