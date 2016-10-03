<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html class="no-js">
<head>
<title>SERP</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<meta charset="utf-8">
<!-- The styles -->
<link
	href="${pageContext.request.contextPath}/resources/themes/charisma-master/css/bootstrap-cerulean.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/themes/charisma-master/css/charisma-app.css"
	rel="stylesheet">
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/jquery.noty.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/noty_theme_default.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/elfinder.min.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/elfinder.theme.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/jquery.iphone.toggle.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/uploadify.css'
	rel='stylesheet'>
<link
	href='${pageContext.request.contextPath}/resources/themes/charisma-master/css/animate.min.css'
	rel='stylesheet'>

<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/jquery/jquery.min.js"></script>

<!-- The fav icon -->
<link
	rel="${pageContext.request.contextPath}/resources/themes/charisma-master/shortcut icon"
	href="img/favicon.ico">

</head>

<body>
	<!-- topbar starts -->
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="ch-container">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<jsp:include page="${partial}"></jsp:include>
		</div>
		<hr>
		<footer class="row" id="menu">
		<p class="col-md-8 col-sm-8 col-xs-12 copyright">
			&copy; <a href="https://www.facebook.com/groups/206558636366593/" target="_blank">Thanks for
				all.</a> 3/5/2016
		</p>
		<p class="col-md-4 col-sm-4 col-xs-12 powered-by">
			Powered by:<a href="https://www.facebook.com/groups/206558636366593/">C39_Java
				Team</a>
		</p>
		</footer>
	</div>

	<!--/.fluid-container-->
	<!-- external javascript -->

	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- library for cookie management -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/moment/min/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
	<!-- data table plugin -->
	<!-- 
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.dataTables.min.js"></script>
	 -->
	 <!-- update data table plugin -->
	 <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<!-- select or dropdown enhancer -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script
		src="${pageContext.request.contextPath}/resources/themes/charisma-master/js/charisma.js"></script>
		
		<script src="${pageContext.request.contextPath}/resources/userJs/checkLoggedIn.js"></script>
</body>
</html>