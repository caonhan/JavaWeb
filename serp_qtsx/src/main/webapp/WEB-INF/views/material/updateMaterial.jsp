<%@page import="com.serp.model.Material"%>
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
<title>Insert title here</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<div class="box col-md-5">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<r:message code="label.materialUpdate" />
					</h2>
				</div>
				<form action="save" method="get">
					<div class="box-content">
						<div class="row">
							<div class="col-md-4">
								<h6>
									<r:message code="label.materialId" />
								</h6>
							</div>
							<div class="col-md-6">
								<input type="text" readonly="readonly" name="materialId"
									value="${material.materialId}" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<h6>
									<r:message code="label.materialName" />
								</h6>
							</div>
							<div class="col-md-6">
								<input type="text" name="materialName"
									value="${material.materialName}" required />
							</div>

						</div>
						<div class="row">
							<div class="col-md-4">
								<h6>
									<r:message code="label.materialPrice" />
								</h6>
							</div>
							<div class="col-md-6">
								<input type="number" min="1000" max="999999999"
									name="materialPrice" value="${material.materialPrice}" required/>
							</div>
						</div>
						<!--Button-->
						<div class="row">
							<div class="box col-md-4"></div>
							<div class="box col-md-8">
								<div class="row">
									<div class="col-md-12">
										<input class="btn btn-success" type="submit"
											value=<r:message code="label.materialSave" />> <a
											href="material">
											<button class="btn btn-danger">
												<r:message code="label.materialCancel" />
											</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!--/Button-->
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>