<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-lg-10 col-sm-10">
	<div class="row">
		<ul class="breadcrumb">
			<li><a href="#">Trang Chủ</a></li>
			<li><a href="#">Phiếu lệnh sản xuất</a></li>
			<li class="active">Chi tiết phiếu lệnh sản xuất</li>
		</ul>
	</div>
	<div class="row">
		
					<div>
						<label>Đơn hàng: ${podetail.orders.orderId } </label>  
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
								<th><r:message code="productionorder.approvedby" /></th>
								<th><r:message code="productionorder.status" /></th>
								<th><r:message code="productionorder.dateWanted" /></th>
								<th><r:message code="productionorder.starttime" /></th>
								<th><r:message code="productionorder.finishtime" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${podetail.poId}</td>
								<td>${podetail.poContent}</td>
								<td>${podetail.poQuantity}</td>
								<td>${podetail.poUnit}</td>
								<td>${podetail.poProcessTechnology}</td>
								<td>${podetail.userByPoFactoryManager.userId}</td>
								<td>${podetail.userByPoApprovedBy.userId}</td>
								<td><c:if test="${podetail.poStatus == 0}">
										<span class="label-warning label"><r:message
												code="productionorder.unapproved" /> </span>
									</c:if> <c:if test="${podetail.poStatus == 1}">
										<span class="label-info label"><r:message
												code="productionorder.approveAwaiting" /> </span>
									</c:if> <c:if test="${podetail.poStatus == 2}">
										<span class="label-success label"><r:message
												code="productionorder.approved" /> </span>
									</c:if></td>
								<td>${podetail.poTimelength}</td>
								<td>${podetail.poStarttime}</td>
								<td>${podetail.poFinishtime}</td>
								<td><a href="#">Limit Inventory</a></td>
							</tr>
							
						</tbody>
					</table>
					
			<br />
			
			<div class="col-md-6">
						<label>Trưởng xưởng:</label> <input type="text" disabled="disabled" /> <br /> <br />
						<label>Ngày ký:</label> <input type="text" disabled="disabled" />
						<br /> <br />
					</div>
			
			<div class="col-md-12" style="text-align: center">					
						<form method="get"
						action="<%=request.getContextPath()%>/productionOrder/detailsAction">
						
						<input type="submit" class="btn btn-warning" name="btnAction"
						onclick="return confirmDeleting();"
						value="<r:message code="productionorder.delete" />" />
						
						 <input type="submit" class="btn btn-success" name="btnAction"
						value="<r:message code="productionorder.update" />" />
						
						 <input type="submit" class="btn btn-info" name="btnAction"
						value="<r:message code="productionorder.download" />" />
						
						 <input type="submit" class="btn btn-default" name="btnAction"
						value="<r:message code="productionorder.exit" />" />
				</form>
			</div>
			
	</div>
</div>