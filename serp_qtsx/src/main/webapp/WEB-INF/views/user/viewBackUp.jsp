<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<script type="text/javascript" src="resources/themes/charisma-master/js/ShowListUser.js"></script>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-user"></i>
							<r:message code="user.profile" />
						</h2>
					</div>
					<div class="box-content">		
						<div class="row">
						<!-- info -->
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.name" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_name" style="width: 270px; height: 40px; margin-bottom: 20px;">								
							</div>	
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>					
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.birthdate" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_birthdate" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>									
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.phonenumber"/></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_phonenumber" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>	
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.email" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_email" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>		
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>		
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.address" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_address" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>	
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>			
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.department" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_department" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>	
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>			
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.role" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_role" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>	
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>			
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.status" /></label>
							</div>
							<div class="col-md-3">
								<input type="text" class="form-control" id="user_status" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>	
							<div class="col-md-7">
								<a class="btn btn-success" href="#" style="width: 70px; height: 40px; margin-bottom: 20px;"> 
									<i class=" glyphicon glyphicon-pencil icon-white"></i><r:message code="user.edit" />
								</a>
							</div>		
						<!-- manager -->
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-4">
										<a class="btn btn-info" href="user_add"> 
											<i class=" glyphicon glyphicon-plus icon-white"></i><r:message code="user.add" />
										</a>
									</div>
									<div class="col-md-4">
										<a class="btn btn-danger" href="#"> 
											<i class="glyphicon glyphicon-remove icon-white"></i><r:message code="user.delete" />
										</a>
									</div>
									<div class="col-md-4">	
										<a class="btn btn-success" href="update_user?user_id=${userName}"> 
											<i class="glyphicon glyphicon-pencil icon-white"></i><r:message code="user.update"/>
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
</html>