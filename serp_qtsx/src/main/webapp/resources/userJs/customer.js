$(document).ready(function(){	
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/customer/list",
			contentType: "application/json",
			success: function(data){
				//alert("OK");
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
					callMessageDialog("Message", "Bảng hiện tại chưa có dữ liệu")
				}
				$.each(data.list,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(value.customerId),
							$('<td>').text(value.companyName),
							$('<td>').text(value.assignee),
							$('<td>').text(value.address),						
							$('<td>').text(value.mobilePhone),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'
									+value.customerId
									+'"><option value="Options" disabled selected>More</option>'
									+ '<option value="Detail">Detail</option>'
									+ '<option value="Edit">Edit</option>'
									+ '<option value="Delete">Delete</option>')
					).appendTo('#listCustomer');
				});
				action();

				$('#listCustomer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};


	/**
	 * ---------------------------START: Add New Customer----------------------------------
	 */
	/**
	 * when click clear button
	 */
	$("#btnClearAddCustomer").click(function()
	{
		$("#txtCustomerID").val('');
		$("#txtCustomerID").val('');
		$("#txtCompanyName").val('');
		$("#txtAddress").val('');
		$("#txtAssignnee").val('');
		$("#txtTelephone").val('');
		$("#txtPhone").val('');
		$("#txtFax").val('');
		$("#txtEmail").val('');
		$("#txtWebsite").val('');
		$("#txtDescription").val('');
	});
	
	/**
	 * When click Save
	 */
	$("#addForm").find("#btnSave").click(function(e){
		if(!isValidationRequiredFieldAddCustomer()){
		//	alert("Please insert required form!");
			callMessageDialog("Massage", "Please insert required form!");
		}
		else if(!isValidationOverRangeWhenAddCustomer()){
			callMessageDialog("Massage", "Some feild is wrong");
		}
		
		else{
			var CustomerIDinput= $("#addForm").find("#txtCustomerID").val();
			
			$.ajax({
				dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/SERP/customer/isExist/"+CustomerIDinput,
				success: function(data){
					
					if(data.isExisted==false){
						var customerId= $("#addForm").find("#txtCustomerID").val();
						var companyName= $("#addForm").find("#txtCompanyName").val();
						var address= $("#addForm").find("#txtAddress").val();
						var assignee= $("#addForm").find("#txtAssignnee").val();
						var telePhone= $("#addForm").find("#txtTelephone").val();
						var mobilePhone= $("#addForm").find("#txtPhone").val();
						var fax= $("#addForm").find("#txtFax").val();
						var email= $("#addForm").find("#txtEmail").val();
						var website= $("#addForm").find("#txtWebsite").val();
						var createdDate = new Date();
						var description= $("#addForm").find("#txtDescription").val();
						$.ajax({
							dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									customerId: customerId,
									companyName: companyName,
									address: address,
									assignee: assignee,
									telePhone: telePhone,
									mobilePhone: mobilePhone,
									fax: fax,
									email: email,
									website: website,
									createdDate: createdDate,
									description: description,
								}),
							contentType: "application/json",
							url:  "/SERP/customer/add",
							success: function(data){
								callMessageDialog("Message", "Add new Customer successfully!");
								//window.location.href="/SERP/listcustomer";
							},
							error: function(){
								//alert("Failed to Add new Customer!");
								callMessageDialog("Message", "Failed to Add new Customer!");
							}
						});	
					}
					else{
							
							callMessageDialog("Message", "ID has been created!");
					}
					
					$("#listCustomer").dataTable().fnDestroy();
					$('#listCustomer tbody').empty();
					
					loadData();
				
				},
				error: function(){
				
				}
				
			});
		}
	});
	

	/**
	 * This function is write to handle on change of dropdown list on each record
	 */	
	
	function action(){
		
		
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var customerIdSeleted= $(this).data('id');//this is customerId of each record.
		   //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    if(valueSelected== "Detail"){
		    	//get detail of 1 Role by call ajax
		    	
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/customer/detail/"+customerIdSeleted,
					success: function(data){
						if(data.status== "ok"){
							$("#detailCustomerDialog").find("#txtCustomerID").val(data.customer.customerId).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtCompanyName").val(data.customer.companyName).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtAddress").val(data.customer.address).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtAssignnee").val(data.customer.assignee).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtTelephone").val(data.customer.telePhone).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtPhone").val(data.customer.mobilePhone).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtFax").val(data.customer.fax).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtEmail").val(data.customer.email).prop( "disabled", true );							
							$("#detailCustomerDialog").find("#txtWebsite").val(data.customer.website).prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtDescription").val(data.customer.description).show().prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtCreatedDate").val(data.customer.createdDate).show().prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtCreatedBy").val(data.customer.createdBy).show().prop( "disabled", true );
							$("#detailCustomerDialog").find("#txtmodifiedBy").val(data.customer.modified_by).show().prop( "disabled", true );
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get customer detail!');
					}
		    	});
		    	$.ajax({
		    		
		    	});
		    	
		    	//show detail Customer dialog.
		    	$("#detailCustomerDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Customer Detail",
					height: 600,
					width: 700,
					
					buttons:{
						"OK": function(){
							$("#detailCustomerDialog").dialog("close");
						}
					
					},
					hide:{
						effect:"explode",
						duration: 500
					}
					
		    	});

			
		    }
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	//set confirm message
		    	$("#deleteCustomerDialog").find("#messageContent").text('Are you sure you want to delete customer "' + customerIdSeleted+'"?');
		    	//show delete Role dialog.
		    	$("#deleteCustomerDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Customer Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							//do delete when user choose OK here, call ajax
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:{},
								contentType: "application/json",
								url: "/SERP/customer/delete/" + customerIdSeleted,
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											
											callMessageDialog("Message", 'Delete customer "'+ customerIdSeleted+ '" successfully!');
											//close dialog
											$("#deleteCustomerDialog").dialog("close");
											//reload table
											$("#listCustomer").dataTable().fnDestroy();
											$('#listCustomer tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t Delete Customer "'+ customerIdSeleted+ '"!');
											$("#deleteCustomerDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
									alert("unexpected error!");
								}
							});
						},
						"Cancle": function(){
							$("#deleteCustomerDialog").dialog("close");
						}
					},
					hide:{
						effect:"fold",
						duration: 500
					}
		    	});
		    }
		   
		    
		    
		    //end if user choose delete option
		    
		    //When user choose edit option
		    if(valueSelected=="Edit"){
		    	//get detail of customer which is choose
		    	
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/customer/detail/"+customerIdSeleted,
					
					success: function(data){
						if(data.status== "ok"){
						
							$("#editCustomerDialog").find("#txtCustomerID").val(data.customer.customerId).prop( "disabled", true );
							$("#editCustomerDialog").find("#txtCompanyName").val(data.customer.companyName).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtAddress").val(data.customer.address).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtAssignnee").val(data.customer.assignee).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtTelephone").val(data.customer.telePhone).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtPhone").val(data.customer.mobilePhone).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtFax").val(data.customer.fax).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtEmail").val(data.customer.email).prop( "disabled", false );							
							$("#editCustomerDialog").find("#txtWebsite").val(data.customer.website).prop( "disabled", false );
							$("#editCustomerDialog").find("#txtDescription").val(data.customer.description).prop( "disabled", false );
								
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('This alert should never show');
					}
		    	});
		    	
		    	//show edit Custmoer dialog.
		  
		    	$("#editCustomerDialog").dialog({
		    		
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Customer",
					height: 660,
					width: 700,
					buttons:{
						"Save": function(){
							if(!isValidationRequiredFieldAddCustomer()){
					    			callMessageDialog("Massage", "Please insert required form!");
					    		}
					    		else if(!isValidationOverRangeWhenAddCustomer()){
					    			callMessageDialog("Massage", "Some feild is wrong");
					    		}
					    		
					    		else{
					    			
							var customerId = $("#editCustomerDialog").find("#txtCustomerID").val();
							var companyName= $("#editCustomerDialog").find("#txtCompanyName").val();
							var address= $("#editCustomerDialog").find("#txtAddress").val();
							var assignee= $("#editCustomerDialog").find("#txtAssignnee").val();
							var telePhone= $("#editCustomerDialog").find("#txtTelephone").val();
							var mobilePhone= $("#editCustomerDialog").find("#txtPhone").val();
							var fax= $("#editCustomerDialog").find("#txtFax").val();
							var email= $("#editCustomerDialog").find("#txtEmail").val();
							var website= $("#editCustomerDialog").find("#txtWebsite").val();
							var description= $("#editCustomerDialog").find("#txtDescription").val();
						
								//convert object to JSON format
								$.ajax({
									dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											customerId :customerId,
											companyName: companyName,
											address: address,
											assignee:assignee,
											telePhone:telePhone,
											mobilePhone: mobilePhone,
											fax:fax,
											email:email,												
											website:website,
											description: description,
										}),
									contentType: "application/json",
									url: "/SERP/customer/edit",
									success: function(data){
											//if add into database successfully
											callMessageDialog("Message", "Edit Customer successfully!");
											
											$("#editCustomerDialog").dialog("close");
											
											//reload table
											
											$("#listCustomer").dataTable().fnDestroy();
											$('#listCustomer tbody').empty();
											$("#editCustomerDialog").dialog("close");
											loadData();
									},
									error: function(){
										alert('This alert maybe never show! (3)');
									}
								});
								//end edit
							//}
					    		}
						},
						"Cancel": function(){
							$("#editCustomerDialog").dialog("close");
						}
					},
					
					hide: {
						effect: "fold",
						duration: 500
					},
								
		    	});
								
							}
		});
	
		
		
	}
	
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "fold",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$("#messageDialog").dialog("close");
				}
			}
		});
	}
	/*This function to validate customerId
	 * 
	 */
	
	$("#addForm").find("#txtCustomerID").keyup(function(){
		var input= $(this).val();
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/SERP/customer/isExist/"+input,
			success: function(data){
				
				if(data.isExisted == false && input.length<=5 ){
					
					$("#addForm").find("#errorTxtCustomerID").text("valid Customer ID");
					$("#addForm").find("#errorTxtCustomerID").css("color", "green");
					$("#addForm").find("#txtCustomerID").css("border-color", "green");
			
					
				}
				
				else if(data.isExisted == true){
					$("#addForm").find("#errorTxtCustomerID").text("existed Customer ID!");
					$("#addForm").find("#errorTxtCustomerID").css("color", "red");
				}else if(input.length > 5 ){
					$("#addForm").find("#errorTxtCustomerID").text("Customer Id is over range");
					$("#addForm").find("#errorTxtCustomerID").css("color", "red");
					$("#addForm").find("#txtCustomerID").css("border-color", "green");
				}
			},
			error: function(){
				$("#addForm").find("#errorTxtCustomerID").text("Please enter Customer ID!");
				$("#addForm").find("#errorTxtCustomerID").css("color", "red");
				$("#addForm").find("#txtCustomerID").css("border-color", "red");
			}
		});
		
		
	});
		
		
		

	
	/**
	 * This function validate Company Name and show message below Role Name's textBox
	 */
	$("#addForm,#editCustomerDialog").find("#txtCompanyName").keyup(function(){
		var input= $(this).val();
		if(input.trim() === '' || input == null){
			$("#addForm,#editCustomerDialog").find("#errorTxtCompanyName").text("Please enter Customer Name!");
			$("#addForm,#editCustomerDialog").find("#errorTxtCompanyName").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtCompanyName").css("border-color", "red");
		}else if(input.length>100){
			$("#addForm,#editCustomerDialog").find("#errorTxtCompanyName").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtCompanyName").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtCompanyName").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtCompanyName").text("");
			$("#addForm,#editCustomerDialog").find("#txtCompanyName").css("border-color", "green");
		}
	});
	/**
	 * This function validate Company Address 
	 */
	$("#addForm,#editCustomerDialog").find("#txtAddress").keyup(function(){
		var input= $(this).val();
		if(input.trim() === '' || input == null){
			$("#addForm,#editCustomerDialog").find("#errorTxtAddress").text("Please enter Customer Name!");
			$("#addForm,#editCustomerDialog").find("#errorTxtAddress").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtAddress").css("border-color", "red");
		}else if(input.length>100){
			$("#addForm,#editCustomerDialog").find("#errorTxtAddress").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtAddress").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtAddress").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtAddress").text("");
			$("#addForm,#editCustomerDialog").find("#txtAddress").css("border-color", "green");
		}
	});
	/**
	 * This function validate Company Assignee 
	 */
	$("#addForm,#editCustomerDialog").find("#txtAssignnee").keyup(function(){
		var input= $(this).val();
		
		if(input.length>100){
			$("#addForm,#editCustomerDialog").find("#errorTxtAssignnee").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtAssignnee").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtAssignnee").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtAssignnee").text("");
			$("#addForm,#editCustomerDialog").find("#txtAssignnee").css("border-color", "green");
		}
	});
	
	/**
	 * This function is used to warn user when input telephone over range
	 */
	$("#addForm,#editCustomerDialog").find("#txtTelephone").keyup(function(){
		var input= $(this).val();
		if(input.length>20){
			$("#addForm,#editCustomerDialog").find("#errorTxtTelephone").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtTelephone").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtTelephone").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtTelephone").text("");
			$("#addForm,#editCustomerDialog").find("#txtTelephone").css("border-color", "green");
		}
	})
	/**
	 * This function is used to warn user when input mobilephone over range
	 */
	$("#addForm,#editCustomerDialog").find("#txtPhone").keyup(function(){
		var input= $(this).val();
		if(input.length>20){
			$("#addForm,#editCustomerDialog").find("#errorTxtPhone").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtPhone").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtPhone").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtPhone").text("");
			$("#addForm,#editCustomerDialog").find("#txtPhone").css("border-color", "green");
		}
	})
	/**
	 * This function is used to warn user when input mobilephone over range
	 */
	$("#addForm,#editCustomerDialog").find("#txtFax").keyup(function(){
		var input= $(this).val();
		if(input.length>20){
			$("#addForm,#editCustomerDialog").find("#errorTxtFax").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtFax").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtFax").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtFax").text("");
			$("#addForm,#editCustomerDialog").find("#txtFax").css("border-color", "green");
		}
	})
	
	
		/**
	 * This function is validate Email
	 */
	$("#addForm,#editCustomerDialog").find("#txtEmail").keyup(function(){
		var input= $(this).val();
		
		if(isValidationEmail(input)){
			$("#addForm,#editCustomerDialog").find("#errorTxtEmail").css("color", "green");
			$("#addForm,#editCustomerDialog").find("#txtEmail").css("border-color", "green");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtEmail").text("");
			$("#addForm,#editCustomerDialog").find("#txtEmail").css("border-color", "red");
		}
	})
	
	
		/**
	 * This function is validate website
	 */
	$("#addForm,#editCustomerDialog").find("#txtWebsite").keyup(function(){
		var input= $(this).val();
		
		if(isValidationWebsite(input)){
			$("#addForm,#editCustomerDialog").find("#errorTxtWebsite").css("color", "green");
			$("#addForm,#editCustomerDialog").find("#txtWebsite").css("border-color", "green");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtWebsite").text("");
			$("#addForm,#editCustomerDialog").find("#txtWebsite").css("border-color", "red");
		}
	})
	
	/**
	 * This function is used to warn user when input description over range
	 */
	$("#addForm,#editCustomerDialog").find("#txtDescription").keyup(function(){
		var input= $(this).val();
		if(input.length>800){
			$("#addForm,#editCustomerDialog").find("#errorTxtDescription").text("Your input is over range");
			$("#addForm,#editCustomerDialog").find("#errorTxtDescription").css("color", "red");
			$("#addForm,#editCustomerDialog").find("#txtDescription").css("border-color", "red");
		}else{
			$("#addForm,#editCustomerDialog").find("#errorTxtDescription").text("");
			$("#addForm,#editCustomerDialog").find("#txtDescription").css("border-color", "green");
		}
	})
	/**
	 * Allow input numeric only 
	 */
	$("#addForm,#editCustomerDialog").on('keydown', '#txtCustomerID,#txtTelephone,#txtPhone,#txtFax' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function isValidationRequiredFieldAddCustomer(){
		var customerId= $("#addForm,#editCustomerDialog").find("#txtCustomerID").val();
		if(customerId.trim() == '' || customerId == null)
			return false;
		
		var companyName= $("#addForm,#editCustomerDialog").find("#txtCompanyName").val();
		if(companyName.trim() == '' || companyName == null)
			return false;
		
		var companyAddress = $('#addForm,#editCustomerDialog').find('#txtAddress').val();
		if(companyAddress.trim()== ''|| companyAddress ==null ) 
			return false;
		
		return true;
	}
	/**
	 * This function validate user input if it's over range before add new role
	 */
	function isValidationOverRangeWhenAddCustomer(){
		if($("#addForm,#editCustomerDialog").find("#txtCustomerID").val().length>5 
				|| $("#addForm,#editCustomerDialog").find("#txtCompanyName").val().length>100
				|| $("#addForm,#editCustomerDialog").find("#txtAddress").val().length>100
				|| $("#addForm,#editCustomerDialog").find("#txtAssignnee").val().length >100
				|| $("#addForm,#editCustomerDialog").find("#txtTelephone").val().length > 20
				|| $("#addForm,#editCustomerDialog").find("#txtPhone").val().length>20
				|| $("#addForm,#editCustomerDialog").find("#txtFax").val().length>20
				|| isValidationWebsite($("#addForm,#editCustomerDialog").find("#txtEmail").val())== false
				|| isValidationWebsite($("#addForm,#editCustomerDialog").find("#txtWebsite").val())== false
				//|| $("#addForm").find("#txtEmail").val().
				//|| $("#addForm").find("#txtWebsite").val().length>800
				|| $("#addForm,#editCustomerDialog").find("#txtDescription").val().length>800
				
		)
			
			return false;
		return true;
	}
	
	/**
	 * this function validate email
	 */
	
	 function isValidationEmail($email) {
		  var emailReg = /^([\w-\.]+@([\w-])+([\w-]+\.)+[\w-]{2,4})?$/;
		  
		  return emailReg.test( $email );
		}
	
	 function isValidationWebsite($website){
		 var websiteReg = /^([\w-]+.([\w-])+([\w-]+\.)+[\w-]{2,4})?$/;
		  
		  return websiteReg.test( $website );
	 }
	 
	loadData();
});
