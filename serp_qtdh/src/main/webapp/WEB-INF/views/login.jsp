<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link href="resources/themes/charisma-master/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="resources/themes/charisma-master/css/charisma-app.css" rel="stylesheet">
    <link href='resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='resources/themes/charisma-master/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/jquery.noty.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/noty_theme_default.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/elfinder.min.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/elfinder.theme.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/uploadify.css' rel='stylesheet'>
    <link href='resources/themes/charisma-master/css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="resources/themes/charisma-master/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>
	<div class="row">
		
		<div class="row">
	        <div class="col-md-12 center login-header">
	            <h2>Welcome to SERP</h2>
	        </div>
	        <!--/span-->
    	</div><!--/row-->
		
		<div class="row">
			<div class="well col-md-5 center login-box">
				<c:url var="loginUrl" value="/login" />
		            <form action="${loginUrl}" method="post" class="form-horizontal">
		            	<c:if test="${param.error != null}">
	                        <div class="alert alert-danger">
	                            <p>Invalid username and password.</p>
	                        </div>
	                    </c:if>
	                    <c:if test="${param.logout != null}">
	                        <div class="alert alert-success">
	                            <p>You have been logged out successfully.</p>
	                        </div>
	                    </c:if>
	                    
		                <fieldset>
		                    <div class="input-group input-group-lg">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
		                        <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
		                    </div>
		                    <div class="clearfix"></div><br>
		
		                    <div class="input-group input-group-lg">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
		                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
		                    </div>
		                    <div class="clearfix"></div>
		                    
		                    <!-- 
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
							<div class="clearfix"></div>
							 -->
							
		                    <div class="input-prepend">
		                        <label class="remember" for="remember"><input type="checkbox" id="remember" name="remember-me"> Remember me</label>
		                    </div>
		                    <div class="clearfix"></div>
		
		                    <p class="center col-md-5">
		                        <button type="submit" class="btn btn-primary">Login</button>
		                    </p>
		                </fieldset>
		            </form>
		            
		        
		            
		            
		            <!-- 
		            <c:url var="loginUrl" value="/login" />
	                <form action="${loginUrl}" method="post" class="form-horizontal">
	                    <c:if test="${param.error != null}">
	                        <div class="alert alert-danger">
	                            <p>Invalid username and password.</p>
	                        </div>
	                    </c:if>
	                    <c:if test="${param.logout != null}">
	                        <div class="alert alert-success">
	                            <p>You have been logged out successfully.</p>
	                        </div>
	                    </c:if>
	                    <div class="input-group input-sm">
	                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
	                        <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
	                    </div>
	                    <div class="input-group input-sm">
	                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
	                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
	                    </div>
	                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	                         
	                    <div class="form-actions">
	                        <input type="submit"
	                            class="btn btn-block btn-primary btn-default" value="Log in">
	                    </div>
	                </form>
		             -->
			</div>
		</div>

		             
	</div>
	
	<!-- external javascript -->
	
	<script src="resources/themes/charisma-master/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<!-- library for cookie management -->
	<script src="resources/themes/charisma-master/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='resources/themes/charisma-master/bower_components/moment/min/moment.min.js'></script>
	<script src='resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='resources/themes/charisma-master/js/jquery.dataTables.min.js'></script>
	
	<!-- select or dropdown enhancer -->
	<script src="resources/themes/charisma-master/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="resources/themes/charisma-master/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="resources/themes/charisma-master/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="resources/themes/charisma-master/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script src="resources/themes/charisma-master/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="resources/themes/charisma-master/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="resources/themes/charisma-master/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="resources/themes/charisma-master/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="resources/themes/charisma-master/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="resources/themes/charisma-master/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="resources/themes/charisma-master/js/charisma.js"></script>
</body>
</html>