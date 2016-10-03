<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="resources/userJs/Quotation.js"></script>
<title>Quotation List</title>
</head>
<body>	
	<div class="col-sm-10 col-lg-10">
		<div class="row">
			<ul class="breadcrumb">
	            <li> <a href="home"><r:message code="label.home" /></a> </li>
	            <li> <a href="quotationList"><r:message code="quo.listquotation" /></a> </li>
	        </ul>
		</div>
		<div class="box-inner">
			<div class="box-header well">
				<h2><i class="glyphicon glyphicon-file"></i><r:message code="quo.listquotation"/></h2>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
					<thead>
						<tr>
							<th><r:message code="quo.no"/></th>
							<th><r:message code="quo.company"/></th>
							<th><r:message code="quo.telephone"/></th>
							<th><r:message code="quo.createDate"/></th>							
							<th><r:message code="quo.deadline"/></th>
							<th><r:message code="quo.pay1"/></th>
							<th><r:message code="quo.pay2"/></th>
							<th><r:message code="quo.total"/></th>
							<th><r:message code="quo.status"/></th>
							<th><r:message code="quo.detail"/></th>
						</tr>
					</thead>					
					<tbody>
						<c:forEach items="${quotationList}" var="item">
						<tr>							
							<td class="quotationId">${item.quotationId}</td>
							<td class="center">${item.estimate.orders.customer.companyName}</td>
							<td class="center">${item.estimate.orders.customer.telephone}</td>
							<td class="center"><fmt:formatDate type="date" value="${item.publishDate}" /></td>
							<td class="center">${item.numOfDaysToComplete}</td>
							<td class="center">${item.paymentMethod1}</td>
							<td class="center">${item.paymentMethod2}</td>
							<td class="center">${item.totalAmount}</td>
							<c:if test="${item.status==1}">
								<td class="center approve"><span
								class="label-success label label-default"><r:message code="quo.status.approved"/></span></td>								
							</c:if>
							<c:if test="${item.status==2}">
								<td class="center approve"><span
								class="label-success label label-default"><r:message code="quo.status.awaiting"/></span></td>
							</c:if>
							<c:if test="${item.status==3}">
								<td class="center approve"><span
								class="label-success label label-default"><r:message code="quo.status.notapprove"/></span></td>								
							</c:if>
							<td class="center"><a class="btn btn-info showhideForm" id="${item.quotationId}" style="height: 24px;padding-top: 1px;" > <i
									class="glyphicon glyphicon-edit icon-white"	></i>   <r:message code="quo.detail"/></a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<br>
		<div class="box-inner detailQuotation">
			<div class="box-header well">
				<h2>
					<i class="glyphicon glyphicon-file"></i>
					<r:message code="quo.quotationdetail"/>
				</h2>
				<div class="box-icon">                    
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>                    
                </div>
			</div>
			<div class="box-content">				
				<div class="row">              	
              		<div class="col-md-12">
              		 	<table id="listQuoDetail" class="table table-striped table-bordered responsive">
                        <thead>
	                        <tr>
	                            <th><r:message code="quo.no"/></th>
	                            <th><r:message code="quo.productname"/></th>
	                            <th><r:message code="quo.quantity"/></th>
	                            <th><r:message code="quo.unit"/></th>
	                            <th><r:message code="quo.price"/></th>
	                            <th><r:message code="quo.amount"/></th>
	                            <th><r:message code="quo.note"/></th>	                            
	                        </tr>
                        </thead>
                        
                    	</table>
                    	<div class="row">
                    			<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message code="quo.amount"/></label>
								</div>
								<div class="col-md-3">
									<input disabled="disabled" type="text" class="form-control amount" id="inputSuccess4"
										style="width: 243px; height: 35px; margin-bottom: 4px;text-align: right;">
								</div>
						</div>
						<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message code="quo.vat"/></label>
								</div>
								<div class="col-md-3">
									<input disabled="disabled" type="text" class="form-control vat" id="inputSuccess4"
										style="width: 243px; height: 35px; margin-bottom: 4px;text-align: right;">
								</div>
						</div>  
						<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-1">
									<label class="control-label" for="inputSuccess4"><r:message code="quo.total"/></label>
								</div>
								<div class="col-md-3">
									<input disabled="disabled" type="text" class="form-control total" id="inputSuccess4"
										style="width: 243px; height: 35px; margin-bottom: 4px;text-align: right;">
								</div>
						</div>
						<div class="row">
							<div class="col-md-10"></div>
							<div class="col-md-2">
								<a class="btn btn-info btnApprove" id="approve" style="width: 151px;" > <i
								class="glyphicon glyphicon-edit icon-white"></i> <r:message code="quo.approve"/></a>
							</div>							
						</div>
                    </div>                                  	
            	</div>
			</div>
		</div>
	</div>
</body>
</html>