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
<title>Add User</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well">
						<h2>
							<i class="glyphicon glyphicon-plus"></i>
							<r:message code="user.add" />
						</h2>
					</div>
					<div class="box-content">		
						<div class="row">
						<form method="post" action="add_user">
						<!-- info -->
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.id" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_id" style="width: 270px; height: 40px; margin-bottom: 20px;">								
							</div>	
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.password" /></label>
							</div>
							<div class="col-md-10">
								<input type="password" class="form-control" name="user_password" style="width: 270px; height: 40px; margin-bottom: 20px;">								
							</div>	
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.name" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_name" style="width: 270px; height: 40px; margin-bottom: 20px;">								
							</div>												
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.birthdate" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_birthdate" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>															
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.phonenumber"/></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_phonenumber" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>								
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.email" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_email" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>											
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.address" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_address" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>										
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.department" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_department" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>										
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.role" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_role" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>										
							<div class="col-md-2">
								<label class="control-label" for="inputSuccess4" style="margin: 10px;"><r:message code="user.status" /></label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" name="user_status" style="width: 270px; height: 40px; margin-bottom: 20px;">
							</div>										
						<!-- manager -->
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-4"></div>
									<div class="col-md-4">
										<input class="btn btn-success btn-success" type="submit"
														value="<r:message code="user.save"/>" />										
									</div>
									<div class="col-md-4">
										<a class="btn btn-danger" href="#"> 
											<r:message code="user.cancel" />
										</a>
									</div>									
								</div>
							</div>
						</form>
						</div>	
					</div>
						
						
				</div>
			</div>
			</div>
			<!--/span-->

		</div>
		<!--/row-->



		<!-- content ends -->
	</div>
	<!--/#content.col-md-0-->
</html>