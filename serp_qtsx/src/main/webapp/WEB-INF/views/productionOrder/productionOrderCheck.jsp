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
			<li class="active">Chi tiết phiếu lệnh sản xuất</li>
		</ul>
	</div>
	<form:form id="update" method="POST" commandName="poUpdate"
		action="${pageContext.request.contextPath}/productionOrder/saveDetails">
		<div class="row">
			<form:hidden path="poId" />
			<form:hidden path="poContent" />
			<form:hidden path="poQuantity" />
			<form:hidden path="poUnit" />
			<form:hidden path="poProcessTechnology" />
			<input type="hidden" name="factoryManager" value="${poUpdate.userByPoFactoryManager.userId}" />
			<form:hidden path="poTimelength" />
			<input value="${poUpdate.poStarttime}" type="hidden"
				class="input-xlarge datepicker" name="startTime" /> <input
				value="${poUpdate.poFinishtime}" type="hidden"
				class="input-xlarge datepicker" name="finishTime" /> <input
				type="hidden" value="${poUpdate.orders.orderId}" name="orderId" />

			<div>
				<label>${poUpdate.orders.orderId} </label>
			</div>
			<br />

			<table
				class="table table-striped table-bordered bootstrap-datatable responsive">
				<thead>
					<tr>
						<th>STT</th>
						<th><r:message code="productionorder.content" /></th>
						<th><r:message code="productionorder.quantity" /></th>
						<th><r:message code="productionorder.unit" /></th>
						<th><r:message code="productionorder.processtechnology" /></th>
						<th><r:message code="productionorder.factoryManager" /></th>
						<%-- <th><r:message code="productionorder.approvedby" /></th>
								<th><r:message code="productionorder.status" /></th> --%>
						<th><r:message code="productionorder.dateWanted" /></th>
						<th><r:message code="productionorder.starttime" /></th>
						<th><r:message code="productionorder.finishtime" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${poUpdate.poId}</td>
						<td>${poUpdate.poContent}</td>
						<td>${poUpdate.poQuantity}</td>
						<td>${poUpdate.poUnit}</td>
						<td>${poUpdate.poProcessTechnology}</td>
						<td>${poUpdate.userByPoFactoryManager.userId}</td>
						<%-- <td>${poUpdate.userByPoApprovedBy.userId}</td>
								<td><c:if test="${poUpdate.poStatus == 0}">
										<span class="label-warning label"><r:message
												code="productionorder.unapproved" /> </span>
									</c:if> <c:if test="${poUpdate.poStatus == 1}">
										<span class="label-info label"><r:message
												code="productionorder.approveAwaiting" /> </span>
									</c:if> <c:if test="${poUpdate.poStatus == 2}">
										<span class="label-success label"><r:message
												code="productionorder.approved" /> </span>
									</c:if></td> --%>
						<td>${poUpdate.poTimelength}</td>
						<td>${poUpdate.poStarttime}</td>
						<td>${poUpdate.poFinishtime}</td>
					</tr>

				</tbody>
			</table>

			<br />

			<div class="col-md-6">
				<%-- <label><r:message code="productionorder.approvedby" /></label>
				<form:input path="userByPoApprovedBy" name="userByPoApprovedBy"
					/> --%>
				<br /> <br /> <label><r:message
						code="productionorder.status" /></label>
				<form:select path="poStatus" type="number">
					<option value="0"><r:message
							code="stockRequisition.unapproved" /></option>
					<option value="1"><r:message
							code="stockRequisition.approveAwaiting" /></option>
					<option value="2"><r:message
							code="stockRequisition.approved" /></option>
				</form:select>
			</div>

			<br /> <br />
		</div>

		<div class="col-md-12" style="text-align: center">
			<input type="submit" class="btn btn-success" name="btnAction2"
				value="<r:message code="productionorder.check" />" /> <input
				type="submit" class="btn btn-default" name="btnAction2"
				value="<r:message code="productionorder.exit" />" />
		</div>
	</form:form>
</div>
