function loadPage(){
	var quotationId=$('#idquotation').val();				
	$.ajax({
		type : 'GET',
		contentType : "application/json; charset=utf-8",
		dataType : 'json',				
		url : '/SERP/quotationDetail/'+quotationId,
		success : function(data) {
			$('#listQuoDetail tbody tr').remove();
			if (data.list.length == 0) {
				alert("Bảng hiện tại chưa có dữ liệu.");
			}
			var amount=0;
			$.each(data.list,function(key,value){
				if(data.quotationId == value.quotationId){						
					$('<tr class="quotation">').append(
							$('<td>').text(value.quotationDetailsId),
							$('<td>').text(value.nameOfDetail),
							$('<td>').text(value.quantity),
							$('<td>').text(value.unit),
							$('<td>').text(value.price),
							$('<td>').text(value.amount),
							$('<td>').text(value.note),
							$('<td><a class="btn btn-info a" id="editQuoDetail" onclick=editQuoDetail('+value.quotationDetailsId+')><i class="glyphicon glyphicon-edit icon-white"></i> Edit </a>'),
							$('<td><a class="btn btn-danger" id="deleteQuoDetail" onclick=deleteQuoDetail("'+value.quotationDetailsId+'")><i class="glyphicon glyphicon-trash icon-white"></i> Delete </a>')
					).appendTo('#listQuoDetail');
					amount=amount+value.amount;
				}						
				
			});
			var vat=amount*10/100;
			$('#amount').val(amount);
			$('#vat').val(vat);
			$('#totalAmount').val(vat+amount);
		},
		error : function() {
			alert("Không lấy đc dữ liệu!");
		}
	});
}
$(document).ready(function(){
	$(".detailQuotation").hide();
	//-------------------Show QuotationDetails-------------------------
	$('.showhideForm').click(function(){
		var approved="Approved";
		var notapproved="NotApproved";
		var quotationId=$(this).closest("tr").find(".quotationId").text();
		var status=$(this).closest("tr").find(".approve").text();			
		$('.btnApprove').attr('href',"approveQuotation?quotationId="+quotationId);
		$.ajax({
			type : 'GET',
			contentType : "application/json; charset=utf-8",
			dataType : 'json',				
			url : '/SERP/quotationDetail/'+quotationId,
			success : function(data) {
				$('#listQuoDetail tbody tr').remove();
				if (data.list.length == 0) {
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				var amount=0;
				var no=1;
				$.each(data.list,function(key,value){
					if(data.quotationId == value.quotationId){						
						$('<tr>').append(
								$('<td>').text(no++),
								$('<td>').text(value.nameOfDetail),
								$('<td>').text(value.quantity),
								$('<td>').text(value.unit),
								$('<td>').text(value.price),
								$('<td>').text(value.amount),
								$('<td>').text(value.note)
						).appendTo('#listQuoDetail');	
						amount= amount + value.amount;
					}
				});							
				$(".detailQuotation").show();
				$('.amount').val(amount);
				var vat=amount*10/100;
				$('.vat').val(vat);
				$('.total').val(amount+vat);
				$('body,html').animate({scrollTop:400},400);
				if(approved==status){						
					$('.btnApprove').attr('disabled','disabled');
				}
				else{						
					$('.btnApprove').removeAttr('disabled');
				}
			},
			error : function() {
				alert("Không lấy đc dữ liệu!");
			}
		});			
	});
	//-------------------Update Quotation---------------------------
	$('#updateQuotation').click(function(){
		var quotationId=$('#idquotation').val();
		var estimate= quotationId;
		var userId= 1;
		var numOfValidityDays= $("#QuotationValue").val();
		var nonumOfDaysToCompletete= $("#Deadline").val();
		var paymentMethod1= $("#PayMethod1").find('option:selected').text();	
		var paymentMethod2= $("#PayMethod2").find('option:selected').text();		
		var status=3;
		var publishDate= new Date();
		var amount=$('#amount').val();
		var vat=$('#vat').val();
		var totalAmount	=$('#totalAmount').val();
		
		if(validateEditQuotation()){
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						quotationId: quotationId,
						estimate: estimate,
						userId: userId,
						numOfValidityDays: numOfValidityDays,
						numOfDaysToComplete: nonumOfDaysToCompletete,
						paymentMethod1:paymentMethod1,
						paymentMethod2:paymentMethod2,
						status:status,
						publishDate:publishDate,
						amount:amount,
						vat:vat,
						totalAmount:totalAmount
					}),
				contentType: "application/json",					
				url : '/SERP/updateQuotation',
				success : function(data) {													
					if(data.quotationEntity == true){					
						bootbox.alert("Update Quotation success !");
					}
				},
				error : function() {
					alert("Không lấy đc dữ liệu!");
				}
			});
		}
	});	
});
function validateEditQuotation(){
	var QuotationValue=$("#QuotationValue").val();
	if(QuotationValue.trim() === '' || QuotationValue == null || QuotationValue < 1){		
		$(".errorValidityDay").css("display", "block");		
		return false;
	}
	else{
		$(".errorValidityDay").css("display", "none");		
	}
	
	var Deadline=$("#Deadline").val();
	if(Deadline.trim() === '' || Deadline == null || Deadline<1){
		$(".errorCompleteDay").css("display", "block");
		return false;
	}
	else{
		$(".errorCompleteDay").css("display", "none");		
	}
	
	return true;
}