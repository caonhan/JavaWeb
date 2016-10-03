function loadPage(){
	var quotationId=$('#idquotation').val();				
	$.ajax({
		type : 'GET',
		contentType : "application/json; charset=utf-8",
		dataType : 'json',				
		url : '/SERP/quotationDetail/'+quotationId,
		success : function(data) {
			$('#listQuoDetail tbody tr').remove();			
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
	loadPage();
	//--------------------Add QuotationDetail----------------------
	$('.btnAddQuoDetail').click(function(){
		if(validateRequiredFieldForAdd()){	
			var productName= $("#productName").val();
			var quantity= $("#quantity").val();
			var unit= $("#unit").val();
			var price= $("#price").val();
			var note= $("#note").val();
			var amount= quantity*price;
			var quotationId=$('#idquotation').val();							
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						nameOfDetail: productName,
						quantity: quantity,
						unit: unit,
						price: price,
						note: note,
						amount:amount,
						quotationId:quotationId
					}),
				contentType: "application/json",					
				url : '/SERP/createQuotationDetail',
				success : function(data) {													
					if(data.list == true){	
						loadPage();
						$('#productName').val("");
						$('#quantity').val("");
						$('#unit').val("");
						$('#price').val("");
						$('#note').val("");
					}								
				},
				error : function() {
					alert("Không lấy đc dữ liệu!");
				}
			});	
		}				
	});
});

//--------------------Delete QuotationDetail----------------------
function deleteQuoDetail(quotationDetailsId){
	bootbox.confirm("Are you want delete QuotationDetail by ID = "+quotationDetailsId+" ?",function(result){
		if(result==true){
			$.ajax({
				type : 'GET',
				contentType : "application/json; charset=utf-8",
				dataType : 'json',				
				url : '/SERP/deleteQuoDetail/'+quotationDetailsId,
				success : function(data) {					
					if(data.flag == true){							
						bootbox.alert("Delete success !");
						loadPage();
					}
				},
				error : function() {
					alert("Không lấy đc dữ liệu!");
				}
			});	
		}
	});	
}
//--------------------Update QuotationDetail----------------------
function editQuoDetail(quotationDetailsId){
	var quotationId="";
	$.ajax({
		type : 'GET',
		contentType : "application/json; charset=utf-8",
		dataType : 'json',				
		url : '/SERP/getQuoDetail/'+quotationDetailsId,
		success : function(data) {					
			$("#editQuoDetailDialog").find("#txtID").val(data.quoD.quotationDetailsId);
			$("#editQuoDetailDialog").find("#txtProductName").val(data.quoD.nameOfDetail);
			$("#editQuoDetailDialog").find("#txtQuantity").val(data.quoD.quantity);
			$("#editQuoDetailDialog").find("#txtPrice").val(data.quoD.price);
			$("#editQuoDetailDialog").find("#txtUnit").val(data.quoD.unit);
			$("#editQuoDetailDialog").find("#txtNote").val(data.quoD.note);
			quoId=data.quoD.quotationId;
		},
		error : function() {
			alert("Không lấy đc dữ liệu!");
		}
	});	
	
	$('#editQuoDetailDialog').dialog({
		
		show:{
			effect:"fold",
			duration: 1000
		},
		title: "Edit Quotation Detail",
		height: 500,
		width: 550,
		buttons:{
			"Edit": function(){
				if(validateRequiredFieldForEdit()){
					var id=$("#editQuoDetailDialog").find("#txtID").val();
					var name=$("#editQuoDetailDialog").find("#txtProductName").val();
					var quantity=$("#editQuoDetailDialog").find("#txtQuantity").val();
					var price=$("#editQuoDetailDialog").find("#txtPrice").val();
					var unit=$("#editQuoDetailDialog").find("#txtUnit").val();
					var note=$("#editQuoDetailDialog").find("#txtNote").val();
					var amount=price*quantity;				
					$.ajax({
						dataType: "json",
						type: 'POST',
						data:
							JSON.stringify({
								quotationDetailsId:id,
								nameOfDetail: name,
								quantity: quantity,
								unit: unit,
								price: price,
								note: note,
								amount:amount,
								quotationId:quoId
							}),
						contentType: "application/json",					
						url : '/SERP/updateQuotationDetail',
						success : function(data) {													
							if(data.list == true){
								$('#editQuoDetailDialog').dialog("close");
								bootbox.alert("Update success !");
								loadPage();							
							}								
						},
						error : function() {
							alert("Không lấy đc dữ liệu!");
						},						
					});
				}		
			},
			"Cancel": function(){
				$("#editQuoDetailDialog").dialog("close");
			}
		},
		hide:{
			effect:"fold",
			duration: 1000
		}
	});
}
function validateRequiredFieldForAdd(){
	var name=$("#productName").val();
	if(name.trim() === '' || name == null){		
		$(".errorProductName").css("display", "block");		
		return false;
	}
	else{
		$(".errorProductName").css("display", "none");		
	}
	
	var quantity=$("#quantity").val();
	if(quantity.trim() === '' || quantity == null){
		$(".errorQuantity").css("display", "block");
		return false;
	}
	else{
		$(".errorQuantity").css("display", "none");		
	}
	
	var unit=$("#unit").val();
	if(unit.trim() === '' || unit == null){
		$(".errorUnit").css("display", "block");
		return false;
	}
	else{
		$(".errorUnit").css("display", "none");		
	}
	
	var price=$("#price").val();
	if(price.trim() === '' || price == null){
		$(".errorPrice").css("display", "block");
		return false;
	}
	else{
		$(".errorPrice").css("display", "none");		
	}
	
	var price=$("#note").val();
	if(price.length >= 200){
		bootbox.alert("Note field over 200 characters !!!")
		return false;
	}	
	
	return true;
}
function validateRequiredFieldForEdit(){
	var name=$("#editQuoDetailDialog").find("#txtProductName").val();
	var quantity=$("#editQuoDetailDialog").find("#txtQuantity").val();
	var price=$("#editQuoDetailDialog").find("#txtPrice").val();
	var unit=$("#editQuoDetailDialog").find("#txtUnit").val();
	
	if(name.trim() === '' || name == null){		
		$(".erEditProductName").css("display", "block");		
		return false;
	}
	else{
		$(".erEditProductName").css("display", "none");		
	}
		
	if(quantity.trim() === '' || quantity == null){
		$(".erEditQuantity").css("display", "block");
		return false;
	}
	else{
		$(".erEditQuantity").css("display", "none");		
	}
	
	if(price.trim() === '' || price == null){
		$(".erEditPrice").css("display", "block");
		return false;
	}
	else{
		$(".erEditPrice").css("display", "none");		
	}
	
	if(unit.trim() === '' || unit == null){
		$(".erEditUnit").css("display", "block");
		return false;
	}
	else{
		$(".erEditUnit").css("display", "none");		
	}		
				
	return true;
}