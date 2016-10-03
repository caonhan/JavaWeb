$(document).ready(function(){	
	
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
					alert("Table do not have any data");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.orderId),
							$('<td>').text(value.customer),
							$('<td>').text(value.userByUserId),
							$('<td>').text(value.projectName),
							$('<td>').text(value.createDate),
							$('<td>').text(value.orderContent),
							$('<td>').text(value.productName),
							$('<td>').text(value.amountOfProduct),
							$('<td>').text(value.dueDate)
					).appendTo('#listOrder');
				});

				$('#listOrder').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can't load data!");
			}
		});
	};
	
	/**
	 * ------------------END-------------------------
	 */
	
	
	/**
	 * ---------------------------START: Add New Order----------------------------------
	 */
	function loadUser(){
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "/SERP/isLoggedIn",
		contentType: "application/json",
		success: function(data){
			$("#addForm").find("#txtUserID").val(data.userName);
		},
		error: function(){
			alert("Failed to load current user!");
		}
	});
	}

	/**
	 * when click clear button
	 */
	$("#btnClear").click(function()
	{
		$("#addForm").find("#txtOrderID").val('');
		$("#addForm").find("#dtpCreateDate").val('');
		$("#addForm").find("#txtUserID").val('');
		$("#addForm").find("#txtProjectName").val('');
		$("#addForm").find("#txtCustomerID").val('');
		$("#addForm").find("#txtProductName").val('');
		$("#addForm").find("#dtpDueDate").val('');
		$("#addForm").find("#txtAmount").val('');
		$("#addForm").find("#txtOrderContent").val('');
		
	});
	
	/**
	 * When click Save
	 */
	$("#btnSave").click(function(e){
		if(validateWhenAddOrder())
		{
			//check customer is existed
			var customerInput= $("#txtCustomerID").val();
			$.ajax({
				dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/SERP/customer/isExist/"+customerInput,
				success: function(data){
					if(data.isExisted == false){
						$("#addForm").find("#txtCustomerID").css("border-left", "red 5px solid");
						$("#addForm").find("#txtCustomerID").val("");
						ShowDialog_CannotFindCusID();
					}
					else if(data.isExisted == true){
						$("#addForm").find("#txtCustomerID").css("border-left", "blue 5px solid");
						//handle orderId value
						var OrderIDinput= $("#addForm").find("#txtOrderID").val();
						//check if orderId existed
						$.ajax({
							dataType: "json",
							type: 'GET',
							data:{},
							contentType: "application/json",
							url: "/SERP/order/isExist/"+OrderIDinput,
							success: function(data){
								if(data.isExisted==false){
									var orderId= $("#addForm").find("#txtOrderID").val();
									var customer= $("#addForm").find("#txtCustomerID").val();
									var projectName= $("#addForm").find("#txtProjectName").val();
									var createDate= $("#dtpCreateDate").datepicker('getDate');
									var orderContent= $("#addForm").find("#txtOrderContent").val();
									var productName= $("#addForm").find("#txtProductName").val();
									var amountOfProduct= $("#addForm").find("#txtAmount").val();
									var dueDate = $('#dtpDueDate').datepicker('getDate')
									$.ajax({
										dataType: "json",
										type: 'POST',
										data:
											JSON.stringify({
												orderId: orderId,
												customer: customer,
												projectName: projectName,
												createDate: $.datepicker.formatDate('yy-mm-dd', createDate),
												productName: productName,
												amountOfProduct:amountOfProduct,
												orderContent: orderContent,
												dueDate: $.datepicker.formatDate('yy-mm-dd', dueDate) 
											}),
										contentType: "application/json",
										url:  "/SERP/order/add",
										success: function(data){
											ShowDialog_InsertSuccess();
											$("#listOrder").dataTable().fnDestroy();
											$('#listOrder tbody').empty();
											loadData();
										},
										error: function(){
											alert("Failed to add new Order!");
										}
									});	
								}
								//clear all textbox
								$("#addForm").find("#txtOrderID").val('');
								$("#addForm").find("#txtUserID").val('');
								$("#addForm").find("#txtProjectName").val('');
								$("#addForm").find("#txtCustomerID").val('');
								$("#addForm").find("#txtProductName").val('');
								$("#addForm").find("#dtpDueDate").val('');
								$("#addForm").find("#txtAmount").val('');
								$("#addForm").find("#txtOrderContent").val('');
								$("#addForm").find("#txtOrderID").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtUserID").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtProjectName").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtCustomerID").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtProductName").css("border-left", "grey 1px solid");
								$("#addForm").find("#dtpDueDate").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtAmount").css("border-left", "grey 1px solid");
								$("#addForm").find("#txtOrderContent").css("border-left", "grey 1px solid");
								loadData();
							},
							error: function(){
								alert("Failed to add new Order!");
							}
						});
						
					}
				},
				error: function(){
					ShowDialog_CannotFindCusID();
				}
			});
			}
		else {
			ShowDialog_Validate();
		}
	});
	
	
	/**
	 * This function validate user input if it's over range before add new order
	 */
	function validateWhenAddOrder(){
		if($("#addForm").find("#txtOrderID").val().length>50 
				|| $("#addForm").find("#txtOrderID").val().length<1
				|| $("#addForm").find("#txtProjectName").val().length>50 
				|| $("#addForm").find("#txtProjectName").val().length<1
				|| $("#addForm").find("#txtProductName").val().length>50
				|| $("#addForm").find("#txtProductName").val().length<1
				|| $("#addForm").find("#txtAmount").val().length>10
				|| $("#addForm").find("#txtAmount").val().length<1
				|| $("#addForm").find("#txtOrderContent").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * This function validate orderID 
	 */
	$("#addForm").find("#txtOrderID").keyup(function(){
		var input= $(this).val();
		if(input.length<=50 && input.length>0){
			$.ajax({
				dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/SERP/order/isExist/"+input,
				success: function(data){
					if(data.isExisted == false){
						$("#addForm").find("#txtOrderID").css("border-left", "blue 5px solid");
						//$("#addForm").find("#errorTxtOrderID").text("valid Order ID");
						//$("#addForm").find("#errorTxtOrderID").css("color", "green");
						//$("#addForm").find("#txtOrderID").css("background-color", "white");
					}else if(data.isExisted == true){
						//$("#addForm").find("#errorTxtOrderID").text("existed Order ID!");
						//$("#addForm").find("#errorTxtOrderID").css("color", "red");
						alert("existed Order ID!");
						$("#addForm").find("#txtOrderID").css("border-left", "red 5px solid");
						$("#addForm").find("#txtOrderID").val("");
					}
				},
				error: function(){
					//$("#addForm").find("#errorTxtOrderID").text("Please enter Order ID!");
					//$("#addForm").find("#errorTxtOrderID").css("color", "red");
					//$("#addForm").find("#txtOrderID").css("border-left", "red 10px solid");
				}
			});
		}
		else {
			$("#addForm").find("#txtOrderID").css("border-left", "red 5px solid");
		}
	});
	
	/*


	  This function validate customerID and show message below customerId's textBox
	 
	$("#addForm").find("#txtCustomerID").keyup(function(){
		var input= $(this).val();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/SERP/c/isExist/"+input,
			success: function(data){
				if(data.isExisted == false && input.length<=50){
					$("#addForm").find("#errorTxtCustomerID").text("valid Customer ID");
					$("#addForm").find("#errorTxtCustomerID").css("color", "green");
					$("#addForm").find("#txtCustomerID").css("background-color", "white");
				}else if(data.isExisted == true){
					$("#addForm").find("#errorTxtCustomerID").text("existed Customer ID!");
					$("#addForm").find("#errorTxtCustomerID").css("color", "red");
				}
			},
			error: function(){
				$("#addForm").find("#errorTxtCustomerID").text("Please enter Customer ID!");
				$("#addForm").find("#errorTxtCustomerID").css("color", "red");
				$("#addForm").find("#txtCustomerID").css("background-color", "red");
			}
		});
		
		if(input.length>50){
			$("#addForm").find("#errorTxtCustomerID").text("Your input is over range");
			$("#addForm").find("#errorTxtCustomerID").css("color", "red");
		}
	});
	*/
	
	/**
	 * Allow input numeric only for txtAmount 
	 */
	$("#addForm").on('keydown', '#txtAmount', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	/**
	 * This function validate Project Name
	 */
	$("#addForm").find("#txtProjectName").keyup(function(){
		var input= $("#txtProjectName").val();
		if(input.length>50 || input.length<1){
			$("#addForm").find("#txtProjectName").css("border-left", "red 5px solid");
			$("#addForm").find("#txtProjectName").val("");
			}
		else {
			$("#addForm").find("#txtProjectName").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function validate Product Name
	 */
	$("#addForm").find("#txtProductName").keyup(function(){
		var input= $("#txtProductName").val();
		if(input.length>50 || input.length<1){
			$("#addForm").find("#txtProductName").css("border-left", "red 5px solid");
			$("#addForm").find("#txtProductName").val("");
			}
		else {
			$("#addForm").find("#txtProductName").css("border-left", "blue 5px solid");
		}
	});
	
	
	/**
	 * This function validate Amount
	 */
	$("#addForm").find("#txtAmount").keyup(function(){
		var input= $("#txtAmount").val();
		if(input.length>10 || input.length<1){
			$("#addForm").find("#txtAmount").css("border-left", "red 5px solid");
			$("#addForm").find("#txtAmount").val("");
			}
		else {
			$("#addForm").find("#txtAmount").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * This function is used to validate order content
	 */
	$("#addForm").find("#txtOrderContent").keyup(function(){
		var input= $("#txtOrderContent").val();
		if(input.length>500){
			$("#addForm").find("#txtOrderContent").css("border-left", "red 5px solid");
			$("#addForm").find("#txtOrderContent").val("");
			}
		else {
			$("#addForm").find("#txtOrderContent").css("border-left", "blue 5px solid");
		}
	});
	
	
	/**
	 * ---------------------------------END: Add New Order------------------------------------
	 */
	// This function is used to call add new order success dialog.
	function ShowDialog_InsertSuccess() {
        $( "#dialogInsertSuccess" ).dialog({
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
                    $( "#dialogInsertSuccess" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
	
	// This function is used to call validate add order dialog.
	function ShowDialog_Validate() {
        $( "#dialogValidateAddOrder" ).dialog({
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
                    $( "#dialogValidateAddOrder" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
    
	// This function is used to call validate add order dialog.
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
	function action(){
		$("#listCustomerDialog").find(".btnChooseCustomer").click(function(){

			var cusID= $(this).data('id');
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/SERP/customer/detail/"+ cusID,
				contentType: "application/json",
				success: function(data){
					$("#addForm").find("#txtCustomerID").val(data.customer.customerId);
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
				action();

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
	loadUser();
	loadData();
});