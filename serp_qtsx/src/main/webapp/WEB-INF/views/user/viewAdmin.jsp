<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-user"></i>
						<r:message code="user.view" />
					</h2>
				</div>
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<r:message code="user.profile" />
								</h2>
							</div>
							<div class="box-content">		
								<div class="row">
								<!-- info -->
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.name" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_name" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.name}">								
									</div>											
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.birthdate" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_birthdate" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.birthdate}">
									</div>																	
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.phonenumber"/></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_phonenumber" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.phonenumber}">
									</div>										
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.email" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_email" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.email}">
									</div>												
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.address" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_address" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.address}">
									</div>											
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.department" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_department" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.department}">
									</div>											
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.role" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_role" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.role.roleId}">
									</div>	
									<div class="col-md-2">
										<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.status" /></label>
									</div>
									<div class="col-md-10">
										<input type="text" readonly="readonly" class="form-control" id="user_status" style="width: 270px; height: 40px; margin-bottom: 20px;" value="${user.status}">
									</div>											
									<!-- manager -->
									<div class="col-md-6">
										<div class="row">
											<div class="col-md-4">
												<a class="btn btn-info" href="user_add"> 
													<i class=" glyphicon glyphicon-plus icon-white"></i><r:message code="user.add" />
												</a>
											</div>
										</div>
									</div>
								</div>	
							</div>		
						</div>
					</div>					
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<r:message code="user.list" />
								</h2>
							</div>
							<div class="box-content">
								<table id="listUser"
									class="table table-striped table-bordered bootstrap-datatable datatable responsive">
									<thead>
										<tr>
											<th>#</th>
											<th><r:message code="user.id" /></th>
											<th><r:message code="user.password" /></th>
											<th><r:message code="user.name" /></th>
											<th><r:message code="user.birthdate" /></th>
											<th><r:message code="user.phonenumber" /></th>
											<th><r:message code="user.email" /></th>
											<th><r:message code="user.address" /></th>
											<th><r:message code="user.department" /></th>
											<th><r:message code="user.role" /></th>
											<th><r:message code="user.status" /></th>	
											<th>Action</th>										
										</tr>
									</thead>
									<tbody>
										<%
										    int i = 1;
										%>
										<c:forEach var="u" items="${userList}">
											<tr>
												<td><%=i++%></td>
												<td>${u.userId}</td>
												<td>${u.password}</td>
												<td>${u.name}</td>
												<td>${u.birthdate}</td>
												<td>${u.phonenumber}</td>
												<td>${u.email}</td>
												<td>${u.address}</td>
												<td>${u.department}</td>
												<td>${u.role.roleId}</td>
												<td>${u.status}</td>
												<td>
													<div class="control-group">
														<div class="controls">
															<a class="btn btn-success" href="update_user?user_id=${u.userId}">
																<i class="glyphicon glyphicon-pencil icon-white"></i><r:message code="user.update"/>
															</a>
															<a class="btn btn-danger" href="delete_user?user_id=${u.userId}"> 
																<i class="glyphicon glyphicon-remove icon-white"></i><r:message code="user.delete" />
															</a> 															
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>