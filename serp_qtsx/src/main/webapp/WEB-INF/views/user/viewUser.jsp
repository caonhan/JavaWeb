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
												<a class="btn btn-success" href="update_user?user_id=${user.userId}"> 
													<i class="glyphicon glyphicon-pencil icon-white"></i><r:message code="user.update" />
												</a>
											</div>
										</div>
									</div>
								</div>	
							</div>		
						</div>
					</div>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>