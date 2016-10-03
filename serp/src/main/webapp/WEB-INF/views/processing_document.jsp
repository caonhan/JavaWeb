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
	<title>Hồ sơ gia công</title>
	
	<link rel="stylesheet" type="text/css" href="resources/themes/charisma-master/css/style_hosogiacong.css">
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
	
  <div class="row">
    <ul class="breadcrumb">
      <li><a href="#"><r:message code="label.home" /></a></li>
      <li class="active"><r:message code="pd.pd" /></li>
    </ul>
  </div>
  <div class="row">
    <div class="col-md-10">
      <header>
        <h2><r:message code="pd.title" /></h2>
      </header>
    </div>
  </div>
  <div class="row">
    <div class="col-md-10">
      <div class="box-content">
        <table id="datatable" class="datatable" cellspacing="0" width="100%">
          <tr>
					<td><r:message code="pd.production_order" /></td>
					<td>
						<select>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.processing_technology_note" /></td>
					<td>
						<select>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.program_note" /></td>
					<td>
						<select>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.operation_trace_note" /></td>
					<td>
						<select>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.download" /></button>
					</td>
				</tr>
				<tr>
					<td><r:message code="pd.limit_inventory_note" /></td>
					<td>
						<select>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.view" /></button>
					</td>
					<td>
						<button type="button" class="btn btn-default"><r:message code="pd.download" /></button>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td><button type="button" class="btn btn-default"><r:message code="pd.send" /></button></td>
					<td><button type="button" class="btn btn-default"><r:message code="pd.close" /></button></td>
				</tr>
        </table>
      </div>
    </div>
  </div>
</div>
		
</body>
</html>