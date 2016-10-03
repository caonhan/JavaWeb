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
<script src="resources/userJs/role.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<title><r:message code="role.listRole" /></title>
<!-- Latest compiled and minified CSS -->

</head>
<body>
<div id="content" class="col-lg-10 col-sm-10">
  <div class="row">
    <ul class="breadcrumb">
      <li><a href="#"><r:message code="role.homePage" /></a></li>
      <li class="active"><r:message code="role.listRole" /></li>
    </ul>
  </div>
  <div class="row">
    <div class="col-md-12">
      <header>
        <h2><r:message code="role.listRole" /></h2>
      </header>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="box-content">
<button id="btnAddRole" class="btn btn-primary"><r:message code="role.addRole" /></button>
	<hr>
	<table id="listRole" class="table table-striped display responsive">
		<thead>
			<tr>
				<th><r:message code="role.No" /></th>
				<th><r:message code="role.roleID" /></th>
				<th><r:message code="role.roleName" /></th>
				<th><r:message code="role.creator" /></th>
				<th><r:message code="role.createdDate" /></th>
				<th><r:message code="role.modifier" /></th>
				<th><r:message code="role.modifiedDate" /></th>
				<th></th>
			</tr>
		</thead>
	</table>
	
	</div>
	</div>
	</div>
	</div>

	
	 <!-- This is dialog to add Role -->
	<div id="addRoleDialog" style="display: none;" class="form-horizontal" name="addRoleForm">
		<div class="form-group">
	       <label for="txtRoleID" class="col-sm-3 control-label"><r:message code="role.roleID" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleID" placeholder='<r:message code="role.roleID" />' value="" name="txtRoleID" required>
	           <span style="font-size: 11px;color: red" id="errorTxtRoleID"></span>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.roleName" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleName" placeholder='<r:message code="role.roleName" />' value="" name="txtRoleName" required>
	           <span style="font-size: 11px;color: red" id="errorTxtRoleName"></span>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><r:message code="role.description" /></label>
	       <br/>
	       <div class="col-sm-9">
	           <textarea class="form-control" id="txtDescription" placeholder='<r:message code="role.description" />'></textarea>
	           <span style="font-size: 11px;color: red" id="errorTxtDescription"></span>
	        </div>
	    </div>
	</div>
	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	
	<!-- This is dialog to show detail of 1 Role -->
	<div id="detailRoleDialog" style="display: none;" class="form-horizontal" name="addRoleForm">
		<div class="form-group">
			<label for="txtRoleID" class="col-sm-3 control-label"><r:message code="role.roleID" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtRoleID" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.roleName" /></label>
	       <div class="col-sm-9">
				<input type="text" class="form-control" id="txtRoleName" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.creator" /></label>
	       <div class="col-sm-9">
				<input type="text" class="form-control" id="txtCreator" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.createdDate" /></label>
	       <div class="col-sm-9">
				<input type="text" class="form-control" id="txtCreatedDate" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.modifier" /></label>
	       <div class="col-sm-9">
				<input type="text" class="form-control" id="txtModifier" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.modifiedDate" /></label>
	       <div class="col-sm-9">
				<input type="text" class="form-control" id="txtModifiedDate" disabled>
			</div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><r:message code="role.description" /></label>
	       <br/>
	       <div class="col-sm-9">
	           <textarea class="form-control" id="txtDescription" placeholder="Role's Description" disabled></textarea>
	        </div>
	    </div>
	</div>
	
	<!-- This is dialog to edit 1 Role -->
	<div id="editRoleDialog" style="display: none;" class="form-horizontal" name="addRoleForm">
		<div class="form-group">
	       <label for="txtRoleID" class="col-sm-3 control-label"><r:message code="role.roleID" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleID" placeholder='<r:message code="role.roleID" />' value="" name="txtRoleID" disabled>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.roleName" /><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleName" placeholder='<r:message code="role.roleName" />' value="" name="txtRoleName" required>
	           <span style="font-size: 11px;color: red" id="errorTxtRoleName"></span>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><r:message code="role.description" /></label>
	       <br/>
	       <div class="col-sm-9">
	           <textarea class="form-control" id="txtDescription" placeholder='<r:message code="role.description" />'></textarea>
	           <span style="font-size: 11px;color: red" id="errorTxtDescription"></span>
	        </div>
	    </div>
	</div>
	
	<!-- This is dialog confirm delete -->
	<div id="deleteRoleDialog" style="display: none;"><span id="messageContent"></span></div>
	
	<!-- This is dialog to Assign function for 1 Role -->
	<div id="assignFunctionDialog" style="display: none;" class="form-horizontal" name="addRoleForm">
		<div class="form-group">
	       <label for="txtRoleID" class="col-sm-3 control-label"><r:message code="role.roleID" /></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleID" placeholder="Role ID" value="" name="txtRoleID" disabled>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtRoleName" class="col-sm-3 control-label"><r:message code="role.roleName" /></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtRoleName" placeholder="Role Name" value="" name="txtRoleName" disabled>
	           <span style="font-size: 11px;color: red" id="errorTxtRoleName"></span>
	       </div>
	   </div>
	   
	   <div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><r:message code="role.allFunctions" /></label>
	       <br/>
	       <div class="col-sm-9" id="listOfFunctions">
	        </div>
	    </div>
	</div>
</body>
</html>