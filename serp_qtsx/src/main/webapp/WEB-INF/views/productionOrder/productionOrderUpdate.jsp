<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="#">Trang Chủ</a></li>
			<li><a href="#">Phiếu lệnh sản xuất</a></li>
			<li class="active">Cập nhật Phiếu lệnh sản xuất</li>
		</ul>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>Phiếu Lệnh sản xuất</h2>
		</div>
		<div class="panel panel-primary">
			<div class="panel-body">
				<form:form id="update" method="POST" commandName="poUpdate"
					action="${pageContext.request.contextPath}/productionOrder/saveDetails">

					<div class="col-md-6">
						<label><r:message code="productionorder.id" /></label>
						<form:input path="poId" type="text" disabled="disabled"/>
						<br /> <br /> <label><r:message
								code="productionorder.order" /></label> <input type="text"
							value="${poUpdate.orders.orderId}" name="orderId" /> <br /> <br /> <label><r:message
								code="productionorder.content" /></label>
						<form:input path="poContent" type="text"  />
						<br /> <br /> <label><r:message
								code="productionorder.quantity" /></label>
						<form:input path="poQuantity" type="text" />
						<br /> <br /> <label><r:message
								code="productionorder.unit" /></label>
						<form:input path="poUnit" type="text" />
						<br /> <br /> <label><r:message
								code="productionorder.processtechnology" /></label>
						<form:input path="poProcessTechnology" type="text" />
						<br /> <br />
					</div>
					<div class="col-md-6">
						<label><r:message code="productionorder.dateWanted" /></label>
						<form:input path="poTimelength" type="number" />
						<br /> <br /> <label><r:message
								code="productionorder.starttime" /></label> <input
							value="${poUpdate.poStarttime}" type="date"
							class="input-xlarge datepicker" name="startTime"
							required="required" /> <br /> <br /> <label><r:message
								code="productionorder.finishtime" /></label> <input
							value="${ poUpdate.poFinishtime}" type="date"
							class="input-xlarge datepicker" name="finishTime"
							required="required" /> <br /> <br /> <br /> <br />
					</div>
					<div class="col-md-12" style="text-align: center">
						<input type="submit" class="btn btn-success" name="btnAction2"
							value="<r:message code="productionorder.save" />" /> <input
							type="submit" class="btn btn-default" name="btnAction2"
							value="<r:message code="productionorder.exit" />" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>