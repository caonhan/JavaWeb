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
<title>Material</title>
</head>
<body>
	<div id="content" class="col-lg-10 col-sm-10">
		<!-- content starts -->
		<div class="row">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-file"></i>
						<r:message code="label.material" />
					</h2>
				</div>
				<div class="row">
					<!-- Add material -->
					<form method="post" action="addmaterial">
						<div class="box col-md-5">
							<div class="box-inner">
								<div class="box-header well">
									<h2>
										<r:message code="label.materialAdd" />
									</h2>
								</div>
								<div class="box-content">
									<div class="row">
										<div class="col-md-4">
											<h6>
												<r:message code="label.materialId" />
											</h6>
										</div>
										<div class="col-md-6">
											<input type="text" name="materialId" required />
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<h6>
												<r:message code="label.materialName" />
											</h6>
										</div>
										<div class="col-md-6">
											<input type="text" name="materialName" required />
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
												name="materialPrice" required/>
										</div>
									</div>
									<!--Button-->
									<div class="row">
										<div class="box col-md-4"></div>
										<div class="box col-md-8">
											<div class="row">
												<div class="col-md-12">
													<input class="btn btn-success btn-sm" type="submit"
														value="<r:message code="label.materialAdd"/>" />
												</div>
											</div>
										</div>
									</div>
									<!--/Button-->
								</div>
							</div>
						</div>
					</form>
					<!-- /Add material -->
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well">
								<h2>
									<r:message code="label.materialList" />
								</h2>
							</div>
							<div class="box-content">
								<table id="listMaterial"
									class="table table-striped table-bordered bootstrap-datatable datatable responsive">
									<thead>
										<tr>
											<th><r:message code="label.materialSTT" /></th>
											<th><r:message code="label.materialId" /></th>
											<th><r:message code="label.materialName" /></th>
											<th><r:message code="label.materialPrice" /></th>
											<th><r:message code="estimate.actions" /></th>
										</tr>
									</thead>
									<tbody>
										<%
										    int i = 1;
										%>
										<c:forEach var="m" items="${materialList}">
											<tr>
												<td><%=i++%></td>
												<td>${m.materialId}</td>
												<td>${m.materialName}</td>
												<td>${m.materialPrice}</td>
												<td><div class="control-group">
														<div class="controls">
															<a href="updatematerial?materialId=${m.materialId}">
																<button
																	class="glyphicon glyphicon-wrench btn btn-primary">
																	<r:message code="label.materialUpdate" />
																</button>
															</a> <a href="deletematerial?materialId=${m.materialId}">
																<button
																	class="glyphicon glyphicon-remove btn btn-danger">
																	<r:message code="label.materialDelete" />
																</button>
															</a>
														</div>
													</div></td>
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
		<!--/row-->
		<!-- content ends -->
	</div>
</body>
</html>