$(document).ready(function(){	
	
	//load Status to select
	function loadStatusData(){
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: "/SERP/status/listStatusForOrder",
            success: function (data) {
            	$.each( data.list, function( key, value ) {
                    $('#editOrderDialog').find('#sltStatus').append($('<option>', {
                        value: value.statusId,
                        text: value.statusName
                    }));

                });
            },
            error: function(){
            	
            }
		});
	}
	
	/**
	 * --------------START: Load order list & set data to table ------------------
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/order/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					
					//convert possibility from number to text
					var possibilityName;
					if(value.possibility == 1) possibilityName = "Okay";
					else if (value.possibility == 2) possibilityName = "No";
					else possibilityName = "Other";
					var strCreateEstimate ="";
					if(value.statusId ==2)
					{
						strCreateEstimate='<option value="createEstimate">Create Estimate</option>';						
					}
					$('<tr>').append(
							$('<td>').text(value.orderId),
							$('<td>').text(value.customer),
							$('<td>').text(value.statusName),
							//$('<td>').text(value.status.statusName),
							//$('<td>').text(value.userByApprover),
							//$('<td>').text(value.userByUserId),
							$('<td>').text(value.projectName),
							//$('<td>').text(value.createDate),
							$('<td>').text(value.orderContent),
							$('<td>').text(possibilityName),
							//$('<td>').text(value.judgingContent),
							//$('<td>').text(value.approvalContent),
							$('<td>').text(value.productName),
							$('<td>').text(value.amountOfProduct),
							$('<td>').text(value.dueDate),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'
									+value.orderId
									+'"><option value="Options" disabled selected>Options</option>'
									+ '<option value="Edit">Edit</option>'
									+ '<option value="Delete">Delete</option>'
									+ strCreateEstimate)
					).appendTo('#listOrder');
				});
				action();

				$('#listOrder').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * ------------------END-------------------------
	 */
	
	/**
	 * -----------------------------START: EDIT + DELETE Order------------------------
	 */
	
	/**
	 * This function is write to handle on change of drop down list on each record
	 */
	function action(){
		$('.selectOption').on('change', function (e) {
			//action is choose by user
			var optionSelected = $(this).find("option:selected");
			var valueSelected  = optionSelected.val();
		    
		    //to handle current status for edit
		    var currentStatus;
		    
		    //orderID is selected on table
			var orderIdSeleted= $(this).data('id');
		    
		    $(".selectOption").val("Options");
		    if(valueSelected=="createEstimate"){
		    	var form = $('.formEstimate');
				$('.estimateId').val("0");
				$('.orderId').val(orderIdSeleted);
				form.submit();
		    }
		    
		    //When user choose edit option
		    if(valueSelected=="Edit"){
				$("#editOrderDialog").find("#txtUserID").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtProjectName").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtCustomerID").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtProductName").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#dtpDueDate").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtAmount").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtOrderContent").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtJudgingContent").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtApprovalContent").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#dtpCreateDate").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#txtOrderID").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#sltStatus").css("border-left", "gray 1px solid");
				$("#editOrderDialog").find("#sltPossibility").css("border-left", "gray 1px solid");
				
		    	//get detail of order which is choose
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/order/detail/"+orderIdSeleted,
					success: function(data){
						if(data.status== "ok"){
							$("#editOrderDialog").find("#txtOrderID").val(data.order.orderId);
							$("#editOrderDialog").find("#txtUserID").val(data.order.userByUserId);
							$("#editOrderDialog").find("#txtProjectName").val(data.order.projectName);
							$("#editOrderDialog").find("#dtpCreateDate").datepicker("setDate", new Date(data.order.createDate));
							$("#editOrderDialog").find("#txtCustomerID").val(data.order.customer);
							$("#editOrderDialog").find("#txtProductName").val(data.order.productName);
							$("#editOrderDialog").find("#txtAmount").val(data.order.amountOfProduct);
							$("#editOrderDialog").find("#txtOrderContent").val(data.order.orderContent);							
							$("#editOrderDialog").find("#dtpDueDate").datepicker("setDate", new Date(data.order.dueDate));
							$("#editOrderDialog").find("#txtApprover").val(data.order.userByApprover);
							$("#editOrderDialog").find("#txtJudgingContent").val(data.order.judgingContent);
							$("#editOrderDialog").find("#txtApprovalContent").val(data.order.approvalContent);							
							$("#editOrderDialog").find("#sltStatus").val(data.order.statusName);
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/SERP/isLoggedIn",
								contentType: "application/json",
								success: function(data){
									$("#editOrderDialog").find("#txtApprover").val(data.userName);
								},
								error: function(){
									alert("Failed to load current user!");
								}
							});
							
							//Set currentStatus to avoid null status when edit
							currentStatus = data.order.statusId;
							
							//convert possibility on select box to possibilityId
							if(data.order.possibility == 1)
								$("#editOrderDialog").find("#sltPossibility").val("Okay");
							else if(data.order.possibility == 2)
								$("#editOrderDialog").find("#sltPossibility").val("No");
							else if(data.order.possibility == 3)
								$("#editOrderDialog").find("#sltPossibility").val("Other");
//						            //Loop add the items to the list: 
//						            $.each(data.list, function(key, value) {
//						                $("#stlStatus").get(0).options[$("#stlStatus").get(0).options.length] = new Option(value.statusName, value.statusId);
//						            });		
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get order detail!');
					}
		    	});
		    	
		    	//show edit Order dialog.
		    	$("#editOrderDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Order",
					height: 700,
					width: 700,
					buttons:{
						"Save": function(){
							//to validate form component
							if(!validateOverRangeWhenEditOrderOverRange()){
								//if user input is over range
								callMessageDialog("Warning message", "Your input is over range!");
							}else{
								
								//check customer is existed
								var customerInput= $("#editOrderDialog").find("#txtCustomerID").val();
								$.ajax({
									dataType: "json",
									type: 'GET',
									data:{},
									contentType: "application/json",
									url: "/SERP/customer/isExist/"+customerInput,
									success: function(data){
										if(data.isExisted == false){
											$("#editOrderDialog").find("#txtCustomerID").css("border-left", "red 5px solid");
											$("#editOrderDialog").find("#txtCustomerID").val("");
											ShowDialog_CannotFindCusID();
										}
										else if(data.isExisted == true){
											//get value form input tag
											var orderId = $("#editOrderDialog").find("#txtOrderID").val();
											var projectName = $("#editOrderDialog").find("#txtProjectName").val();
											var customer= $("#editOrderDialog").find("#txtCustomerID").val();									
											var orderContent= $("#editOrderDialog").find("#txtOrderContent").val();
											var judgingContent= $("#editOrderDialog").find("#txtJudgingContent").val();
											var approvalContent= $("#editOrderDialog").find("#txtApprovalContent").val();
											var productName= $("#editOrderDialog").find("#txtProductName").val();
											var amountOfProduct= $("#editOrderDialog").find("#txtAmount").val();
											var dueDate = $('#dtpDueDate').datepicker('getDate');
											var createDate= $("#dtpCreateDate").datepicker('getDate');
											var tmpPossibility = $("#editOrderDialog").find("#sltPossibility").val();
											var possibility;
											
											//handle possibility select box
											if(tmpPossibility=="Okay")
												possibility = 1;
											else if (tmpPossibility=="No")
												possibility = 2;
											else possibility = 3;
											
											//handle status select box
											var statusId = $("#editOrderDialog").find("#sltStatus").val();
											
											//avoid null status. Set it to its current status
											if(statusId==null) statusId=currentStatus;
											
											//convert object to JSON format
											$.ajax({
												dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														orderId: orderId,
														projectName: projectName,
														customer:customer,
														orderContent:orderContent,
														productName:productName,
														judgingContent:judgingContent,
														approvalContent:approvalContent,												
														amountOfProduct:amountOfProduct,
														createDate: $.datepicker.formatDate('yy-mm-dd', createDate),
														dueDate: $.datepicker.formatDate('yy-mm-dd', dueDate),
														possibility:possibility,
														statusId:statusId
													}),
												contentType: "application/json",
												url: "/SERP/order/edit",
												success: function(data){
													if(data.editStatus == true){
														//if add into database successfully
														callMessageDialog("Message", "Edit Order successfully!");
														
														//close dialog and clear input
														$("#editOrderDialog").find("#txtProjectName").val('');
														$("#editOrderDialog").find("#txtProjectName").css("background-color", "white");
														$("#editOrderDialog").dialog("close");
														
														//reload table
														$("#listOrder").dataTable().fnDestroy();
														$('#listOrder tbody').empty();
														loadData();
													}else if(data.editStatus == false){
														alert('This alert maybe never show! (4)');
													}else{
														alert('This alert maybe never show! (5)');
													}
												},
												error: function(){
													alert('This alert maybe never show! (3)');
												}
											});
											//end edit
										}
									}
									});		
								ShowDialog_EditOrderSuccess();
							}
							
						},
						"Cancel": function(){
							$("#editOrderDialog").dialog("close");
						}
					},
					hide:{
						effect:"fold",
						duration: 500
					}
		    	});
		    }
		    
		    //when user choose delete option
		    if(valueSelected=="Delete"){
		    	//set confirm message
		    	$("#deleteOrderDialog").find("#messageContent").text('Are you sure you want to delete order "' + orderIdSeleted+'"?');
		    	//show delete Order dialog.
		    	$("#deleteOrderDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Order Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							//do delete when user choose OK 
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:{},
								contentType: "application/json",
								url: "/SERP/order/delete/" + orderIdSeleted,
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete Order "'+ orderIdSeleted+ '" successfully!');
											//close dialog
											$("#deleteOrderDialog").dialog("close");
											//reload table
											$("#listOrder").dataTable().fnDestroy();
											$('#listOrder tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t Delete Order "'+ orderIdSeleted+ '"!');
											$("#deleteOrderDialog").dialog("close");
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
						"Cancel": function(){
							$("#deleteOrderDialog").dialog("close");
						}
					},
					hide:{
						effect:"fold",
						duration: 500
					}
		    	});
		    }
		    //end if user choose delete option
		});
	}
	/**
	 * ---------------------------------END: handle dropdownlist request---------------------
	 */
	
	//Go to addOrder page
	$("#btnAddOrder").click(function(){
		window.location.href = '/SERP/addorder';
	});
	
	/**
	 * This function is used to call and set parameter for message dialog.
	 */
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
	
	/**
	 * This function validate user input if it's over range before add new order
	 */
	function validateOverRangeWhenEditOrderOverRange(){
		if($("#editOrderDialog").find("#txtJudgingContent").val().length>500 
				|| $("#editOrderDialog").find("#txtApprovalContent").val().length>500
				|| $("#editOrderDialog").find("#txtProjectName").val().length>50 
				|| $("#editOrderDialog").find("#txtProjectName").val().length<1
				|| $("#editOrderDialog").find("#txtProductName").val().length>50
				|| $("#editOrderDialog").find("#txtProductName").val().length<1
				|| $("#editOrderDialog").find("#txtAmount").val().length>10
				|| $("#editOrderDialog").find("#txtAmount").val().length<1
				|| $("#editOrderDialog").find("#txtOrderContent").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * Allow input numeric only for txtAmount 
	 */
	$("#editOrderDialog").on('keydown', '#txtAmount', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	/**
	 * This function validate Project Name
	 */
	$("#editOrderDialog").find("#txtProjectName").keyup(function(){
		var input= $("#txtProjectName").val();
		if(input.length>50 || input.length<1){
			$("#editOrderDialog").find("#txtProjectName").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtProjectName").val("");
			}
		else {
			$("#editOrderDialog").find("#txtProjectName").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function validate Product Name
	 */
	$("#editOrderDialog").find("#txtProductName").keyup(function(){
		var input= $("#txtProductName").val();
		if(input.length>50 || input.length<1){
			$("#editOrderDialog").find("#txtProductName").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtProductName").val("");
			}
		else {
			$("#editOrderDialog").find("#txtProductName").css("border-left", "blue 5px solid");
		}
	});
	
	
	/**
	 * This function validate Amount
	 */
	$("#editOrderDialog").find("#txtAmount").keyup(function(){
		var input= $("#txtAmount").val();
		if(input.length>10 || input.length<1){
			$("#editOrderDialog").find("#txtAmount").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtAmount").val("");
			}
		else {
			$("#editOrderDialog").find("#txtAmount").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function is used to validate order content
	 */
	$("#editOrderDialog").find("#txtOrderContent").keyup(function(){
		var input= $("#txtOrderContent").val();
		if(input.length>500){
			$("#editOrderDialog").find("#txtOrderContent").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtOrderContent").val("");
			}
		else {
			$("#editOrderDialog").find("#txtOrderContent").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function is used to validate approval content
	 */
	$("#editOrderDialog").find("#txtApprovalContent").keyup(function(){
		var input= $("#txtApprovalContent").val();
		if(input.length>500){
			$("#editOrderDialog").find("#txtApprovalContent").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtApprovalContent").val("");
			}
		else {
			$("#editOrderDialog").find("#txtApprovalContent").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function is used to validate judging content
	 */
	$("#editOrderDialog").find("#txtJudgingContent").keyup(function(){
		var input= $("#txtJudgingContent").val();
		if(input.length>500){
			$("#editOrderDialog").find("#txtJudgingContent").css("border-left", "red 5px solid");
			$("#editOrderDialog").find("#txtJudgingContent").val("");
			}
		else {
			$("#editOrderDialog").find("#txtJudgingContent").css("border-left", "blue 5px solid");
		}
	});
	
	// This function is used to call validate cusID when add order dialog.
	function ShowDialog_CannotFindCusID() {
        $( "#dialogCannotFindCusID" ).dialog({
        	title: "Message",
            show: {
                effect: "slide",
                duration: 300
            },
            height: 200,
            width: 320,
            modal: false,
            buttons: {
                "OK": function() {
                    $( "#dialogCannotFindCusID" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
	
	// This function is used to call editOrderSuccess dialog.
	function ShowDialog_EditOrderSuccess() {
        $( "#dialogEditSuccess" ).dialog({
        	title: "Message",
            show: {
                effect: "slide",
                duration: 300
            },
            height: 200,
            width: 320,
            modal: false,
            buttons: {
                "OK": function() {
                    $( "#dialogEditSuccess" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
    
	//When Click F2
	$(document).keydown(function(e) {
	    if(e.which == 113) {
	    	$("#listCustomerDialog").dialog({
	    		show:{
					effect: "slide",
					duration: 1000
				},
				height: 500,
				width: 700,
				modal: true,
	            buttons: {
	            	"Exit": function() {
	                    $( "#listCustomerDialog" ).dialog( "close" );
	                }
	            },
				hide:{
					effect: "slide",
					duration: 1000
				}
			});
    	}
	});
	
	//When click select
	function chooseCustomer(){
		$("#listCustomerDialog").find(".btnChooseCustomer").click(function(){

			var cusID= $(this).data('id');
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/SERP/customer/detail/"+ cusID,
				contentType: "application/json",
				success: function(data){
					$("#editOrderDialog").find("#txtCustomerID").val(data.customer.customerId);
					$("#listCustomerDialog").dialog("close");
				},
				error: function(){
					alert("Failed when click select")
				}
			});
		});
	}
	
	//Load Data for Customer Table
	function loadCustomerData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/customer/list",
			contentType: "application/json",
			title: "Customer List",
			success: function(data){
				//alert("OK");
				if(data.list.length==0){
					alert("Table do not have any data");
				}
				$.each(data.list,function(key,value){	
					$('<tr>').append(
							$('<td>').text(value.customerId),
							$('<td>').text(value.companyName),
							$('<td>').text(value.assignee),
							$('<td>').text(value.address),						
							$('<td>').text(value.mobilePhone),
							$('<td>').html('<button class="btn btn-primary btnChooseCustomer" data-id="'+value.customerId+'">Select</button>')
							).appendTo('#listCustomer');
				});
				chooseCustomer();

				$('#listCustomer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can't load data!");
			}
		});
	};
	loadCustomerData();
	loadData();
	loadStatusData();
});