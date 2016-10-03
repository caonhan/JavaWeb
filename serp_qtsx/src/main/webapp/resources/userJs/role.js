$(document).ready(function(){
	
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/role/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.roleId),
							$('<td>').text(value.roleName),
							$('<td>').text(value.creatorId),
							$('<td>').text(value.createdDate),
							$('<td>').text(value.modifierId),
							$('<td>').text(value.modifiedDate),
							//$('<td>').html('<button class="btn btn-primary btnEditNvbh" data-id="'+value.roleId+'">Edit</button>'),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.roleId+'">'
									+'<option value="Options" disabled selected>Options</option><option value="Detail">Detail</option>'
									+'<option value="Edit">Edit</option><option value="Delete">Delete</option>'
									+'<option value="AssignFunction">Assign Function</option></select>')
					).appendTo('#listRole');
				});
				console.log(data);
				action();

				$('#listRole').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * This function is write to handle on change of dropdown list on each record
	 */
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var roleIdSeleted= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    //if user choose Detail option
		    if(valueSelected== "Detail"){
		    	//get detail of 1 Role by call ajax
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/role/detail/"+roleIdSeleted,
					success: function(data){
						if(data.status== "ok"){
							$("#detailRoleDialog").find("#txtRoleID").val(data.role.roleId);
							$("#detailRoleDialog").find("#txtRoleName").val(data.role.roleName);
							$("#detailRoleDialog").find("#txtCreator").val(data.role.creatorId);
							$("#detailRoleDialog").find("#txtCreatedDate").val(data.role.createdDate);
							$("#detailRoleDialog").find("#txtModifier").val(data.role.modifierId);
							$("#detailRoleDialog").find("#txtModifiedDate").val(data.role.modifiedDate);
							$("#detailRoleDialog").find("#txtDescription").val(data.role.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
		    	
		    	
		    	//show detail Role dialog.
		    	$("#detailRoleDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Role's Detail",
					height: 600,
					width: 700,
					buttons:{
						"OK": function(){
							$("#detailRoleDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	//get detail of 1 Role by call ajax
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/role/detail/"+roleIdSeleted,
					success: function(data){
						if(data.status== "ok"){
							$("#editRoleDialog").find("#txtRoleID").val(data.role.roleId);
							$("#editRoleDialog").find("#txtRoleName").val(data.role.roleName);
							$("#editRoleDialog").find("#txtDescription").val(data.role.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
		    	
		    	//show edit Role dialog.
		    	$("#editRoleDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Role",
					height: 500,
					width: 700,
					buttons:{
						"Edit": function(){
							//same with add
							//check if there no required fields left.
							if(!validateRequiredFieldForEdit()){
								//when still left required field, set the text of message dialog, after that show it
								callMessageDialog("Warning message", "Please enter required fields!");
								//do css in each textBox
								$("#editRoleDialog").find("#errorTxtRoleName").text("Please enter Role Name!");
								$("#editRoleDialog").find("#errorTxtRoleName").css("color", "red");
								$("#editRoleDialog").find("#txtRoleName").css("background-color", "red");
							}else{
								//when all required fields is ok
								if(!validateOverRangeWhenAddRole()){
									//if user input is over range
									callMessageDialog("Warning message", "Your input is over range!");
								}else{
									//do edit here
									//get values from input tags, do edit role ($.ajax)
									var roleId= $("#editRoleDialog").find("#txtRoleID").val();
									var roleName= $("#editRoleDialog").find("#txtRoleName").val();
									var description= $("#editRoleDialog").find("#txtDescription").val();
									//alert(roleId+roleName+description);
									$.ajax({
										dataType: "json",
										type: 'POST',
										data:
											JSON.stringify({
												roleId: roleId,
												roleName: roleName,
												description: description
											}),
										contentType: "application/json",
										url: "/SERP/role/edit",//đang fix đến đây
										success: function(data){
											if(data.editStatus == true){
												//if add into db successfully
												callMessageDialog("Message", "Edit Role successfully!");
												//close dialog and clear input
												$("#editRoleDialog").find("#txtRoleID").val('');
												$("#editRoleDialog").find("#txtRoleName").val('');
												$("#editRoleDialog").find("#txtDescription").val('');
												$("#editRoleDialog").find("#errorTxtRoleID").text("");
												$("#editRoleDialog").find("#errorTxtRoleName").text("");
												$("#editRoleDialog").find("#errorTxtDescription").text("");
												$("#editRoleDialog").find("#txtRoleID").css("background-color", "white");
												$("#editRoleDialog").find("#txtRoleName").css("background-color", "white");
												$("#editRoleDialog").dialog("close");
												
												//after add reload table
												$("#listRole").dataTable().fnDestroy();
												$('#listRole tbody').empty();
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
						},
						"Cancel": function(){
							$("#editRoleDialog").dialog("close");
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
		    	$("#deleteRoleDialog").find("#messageContent").text('Are you sure you want to delete Role "' + roleIdSeleted+'"?');
		    	//show delete Role dialog.
		    	$("#deleteRoleDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Role Confirm",
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
								url: "/SERP/role/delete/" + roleIdSeleted,
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete Role "'+ roleIdSeleted+ '" successfully!');
											//close dialog
											$("#deleteRoleDialog").dialog("close");
											//reload table
											$("#listRole").dataTable().fnDestroy();
											$('#listRole tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t Delete Role "'+ roleIdSeleted+ '"!');
											$("#deleteRoleDialog").dialog("close");
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
							$("#deleteRoleDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    //end if user choose delete option
		    
		    //if use choose AssignFunction
		    if(valueSelected=="AssignFunction"){
		    	//gọi ajax lấy dữ liệu trc
		    	$.ajax({
					dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/role/detailAndListFunc/" + roleIdSeleted,
					success: function(data){
						if(data.status=="ok"){
							//gán dữ liệu vô các textBox
							$("#assignFunctionDialog").find("#txtRoleID").val(data.role.roleId);
							$("#assignFunctionDialog").find("#txtRoleName").val(data.role.roleName);
							
							//VERY IMPORTANT
//							$('input[name="functions"]').each(function () {
//						           //if (this.checked) {
//						               console.log($(this).val());
//						               $(this).prop('checked', true);
//						           //}
//							});
							
							//lặp qua listFunction của 1 Role, nếu trùng value vs checkBox nào thì check cái cbox đó.
							$.each(data.listFunction,function(key,value){
								//lặp qua danh sách các checkBox trong group
								$('input[name="functions"]').each(function () {
									//this.val() là value của mỗi checkBox trong checkBox group
									if(value.functionId == $(this).val()){
										$(this).prop('checked', true);
									}
								});
							});
							
						}else{
							alert('Lỗi trong code? (Xử lý Assign Functions)');
						}
					},
					error: function(){
						alert('Lỗi 404? (Xử lý Assign Functions)');
					}
		    	});
		    	
		    	//show assign Function Dialog.
		    	$("#assignFunctionDialog").dialog({
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Assign Function",
					height: 600,
					width: 700,
					close: function(){
						//Khi nhấn [x] thì reset hết textBox về chưa check
						$('input[name="functions"]').each(function () {
					           //console.log($(this).val());
				               $(this).prop('checked', false);
						});
					},
					buttons:{
						"Update": function(){
							//Xử lý khi nhấn nút [update], tạo ra 1 array? để lưu các function đc check
							var listFunction =[];
							//duyệt qua r đẩy vô hết trong array
							$('input[name="functions"]:checked').each(function () {
								listFunction.push({ 'functionId': $(this).val() });
							});
							//console.log(JSON.stringify(listFunction));
							//lấy giá trị của roleId
							var chosenRoleId = $("#assignFunctionDialog").find("#txtRoleID").val();
							
							//sau khi có array r thì gọi ajax về controller
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify(listFunction),
								contentType: "application/json",
								url: "/SERP/role/AssignFuncForRole/" + chosenRoleId,
								success: function(data){
									if(data.status=="ok"){
										if(data.assignStatus==true){
											callMessageDialog("Message", "Assign Function successfully!");
										}else if(data.assignStatus==false){
											callMessageDialog("Warning Message", "Assign Function failed!");
										}else{
											alert("never show!");
										}
									}else{
										alert("vấn đề code, 356");
									}
								},
								error: function(){
									alert("failed on /role/AssignFuncForRole/");
								}
							});
							//kết thúc
						},
						"Cancel": function(){
							//Khi nhấn cancel thì reset hết textBox về chưa check r đóng dialog
							$('input[name="functions"]').each(function () {
						           //console.log($(this).val());
					               $(this).prop('checked', false);
							});
							$("#assignFunctionDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    //end if use choose AssignFunction
		});
	}
	
	/**
	 * Function when click AddRole button
	 */
	$("#btnAddRole").click(function(){
		$("#addRoleDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add new Role",
			height: 500,
			width: 700,
			buttons:{
				/**
				 * Function when click add button on addRoleDialog
				 */
				"Add": function(){
					//check if there no required fields left.
					if(!validateRequiredFieldForAdd()){
						//when still left required field, set the text of message dialog, after that show it
						callMessageDialog("Warning message", "Please enter required fields!");
						//do css in each textBox
//						$("#addRoleDialog").find("#errorTxtRoleID").text("Please enter Role ID!");
//						$("#addRoleDialog").find("#errorTxtRoleID").css("color", "red");
//						$("#addRoleDialog").find("#txtRoleID").css("background-color", "red");
						
//						$("#addRoleDialog").find("#errorTxtRoleName").text("Please enter Role Name!");
//						$("#addRoleDialog").find("#errorTxtRoleName").css("color", "red");
//						$("#addRoleDialog").find("#txtRoleName").css("background-color", "red");
					}else{
						//after required fields are fill ok, check if input is over range 
						if(!validateOverRangeWhenAddRole()){
							//if user input is over range
							callMessageDialog("Warning message", "Your input is over range!");
						}else{
							//when user input is ok, check if role id isnt existed.
							var input= $("#addRoleDialog").find("#txtRoleID").val();
							//use ajax to check if roleId is existed
							$.ajax({
								dataType: "json",
								type: 'GET',
								data:{},
								contentType: "application/json",
								url: "/SERP/role/isExist/"+input,
								success: function(data){
									//if isExisted == false (no existed)
									if(data.isExisted == false){
										//get values from input tags, do add new role ($.ajax)
										var roleId= $("#addRoleDialog").find("#txtRoleID").val();
										var roleName= $("#addRoleDialog").find("#txtRoleName").val();
										var description= $("#addRoleDialog").find("#txtDescription").val();
										//alert(roleId+roleName+description);
										$.ajax({
											dataType: "json",
											type: 'POST',
											data:
												JSON.stringify({
													roleId: roleId,
													roleName: roleName,
													description: description
												}),
											contentType: "application/json",
											url: "/SERP/role/add",
											success: function(data){
												if(data.addStatus == true){
													//if add into db successfully
													callMessageDialog("Message", "Add new Role successfully!");
													//close dialog and clear input
													$("#addRoleDialog").find("#txtRoleID").val('');
													$("#addRoleDialog").find("#txtRoleName").val('');
													$("#addRoleDialog").find("#txtDescription").val('');
													$("#addRoleDialog").find("#errorTxtRoleID").text("");
													$("#addRoleDialog").find("#errorTxtRoleName").text("");
													$("#addRoleDialog").find("#errorTxtDescription").text("");
													$("#addRoleDialog").find("#txtRoleID").css("background-color", "white");
													$("#addRoleDialog").find("#txtRoleName").css("background-color", "white");
													$("#addRoleDialog").dialog("close");
													
													//after add reload table
													$("#listRole").dataTable().fnDestroy();
													$('#listRole tbody').empty();
													loadData();
												}else if(data.addStatus == false){
													alert('This alert maybe never show! (4)');
												}else{
													alert('This alert maybe never show! (5)');
												}
											},
											error: function(){
												alert('This alert maybe never show! (3)');
											}
										});
									}else if(data.isExisted == true){
										//this is when roleid is existed
										callMessageDialog("Warning Message", "Role ID is existed, please choose different Role ID!");
									}else{
										alert('This alert never show! (2)');
									}
								},
								error: function(){
									alert('This alert maybe show when disconnect DB! (1)');
								}
							});
						}
					}
				},
				"Cancle": function(){
					$("#addRoleDialog").dialog("close");
					//clear all input, error
					$("#addRoleDialog").find("#txtRoleID").val('');
					$("#addRoleDialog").find("#txtRoleName").val('');
					$("#addRoleDialog").find("#txtDescription").val('');
					$("#addRoleDialog").find("#errorTxtRoleID").text("");
					$("#addRoleDialog").find("#errorTxtRoleName").text("");
					$("#addRoleDialog").find("#errorTxtDescription").text("");
					$("#addRoleDialog").find("#txtRoleID").css("background-color", "white");
					$("#addRoleDialog").find("#txtRoleName").css("background-color", "white");
				}
			},
			hide: {
				effect: "explode",
				duration: 500
			}
		});
	});
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var roleId= $("#addRoleDialog").find("#txtRoleID").val();
		if(roleId.trim() === '' || roleId == null)
			return false;
		
		var roleName= $("#addRoleDialog").find("#txtRoleName").val();
		if(roleName.trim() === '' || roleName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new role
	 */
	function validateOverRangeWhenAddRole(){
		if($("#addRoleDialog").find("#txtRoleID").val().length>50 || $("#addRoleDialog").find("#txtRoleName").val().length>100 
				|| $("#addRoleDialog").find("#txtDescription").val().length>500)//test, need fix
			return false;
		return true;
	}
	
	/**
	 * This function validate RoleID and show message below RoleId's textBox
	 */
	$("#addRoleDialog").find("#txtRoleID").keyup(function(){
		var input= $(this).val();
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/SERP/role/isExist/"+input,
			success: function(data){
				if(data.isExisted == false && input.length<=50){
					$("#addRoleDialog").find("#errorTxtRoleID").text("valid Role ID");
					$("#addRoleDialog").find("#errorTxtRoleID").css("color", "green");
					$("#addRoleDialog").find("#txtRoleID").css("background-color", "white");
				}else if(data.isExisted == true){
					$("#addRoleDialog").find("#errorTxtRoleID").text("existed Role ID!");
					$("#addRoleDialog").find("#errorTxtRoleID").css("color", "red");
					//$("#addRoleDialog").find("#txtRoleID").css("background-color", "red");
				}
			},
			error: function(){
				$("#addRoleDialog").find("#errorTxtRoleID").text("Please enter Role ID!");
				$("#addRoleDialog").find("#errorTxtRoleID").css("color", "red");
				$("#addRoleDialog").find("#txtRoleID").css("background-color", "red");
			}
		});
		
		if(input.length>50){
			$("#addRoleDialog").find("#errorTxtRoleID").text("Your input is over range");
			$("#addRoleDialog").find("#errorTxtRoleID").css("color", "red");
			//$("#addRoleDialog").find("#txtRoleID").css("background-color", "red");
		}
	});
	
	/**
	 * This function validate Role Name and show message below Role Name's textBox
	 */
	$("#addRoleDialog").find("#txtRoleName").keyup(function(){
		var input= $(this).val();
		if(input.trim() === '' || input == null){
			$("#addRoleDialog").find("#errorTxtRoleName").text("Please enter Role Name!");
			$("#addRoleDialog").find("#errorTxtRoleName").css("color", "red");
			$("#addRoleDialog").find("#txtRoleName").css("background-color", "red");
		}else if(input.length>100){
			$("#addRoleDialog").find("#errorTxtRoleName").text("Your input is over range");
			$("#addRoleDialog").find("#errorTxtRoleName").css("color", "red");
			$("#addRoleDialog").find("#txtRoleName").css("background-color", "red");
		}else{
			$("#addRoleDialog").find("#errorTxtRoleName").text("");
			$("#addRoleDialog").find("#txtRoleName").css("background-color", "white");
		}
		
	});
	
	/**
	 * This function is used to warn user when input description over range
	 */
	$("#addRoleDialog").find("#txtDescription").keyup(function(){
		var input= $(this).val();
		if(input.length>500){
			$("#addRoleDialog").find("#errorTxtDescription").text("Your input is over range");
			$("#addRoleDialog").find("#errorTxtDescription").css("color", "red");
			$("#addRoleDialog").find("#txtDescription").css("background-color", "red");
		}else{
			$("#addRoleDialog").find("#errorTxtDescription").text("");
			$("#addRoleDialog").find("#txtDescription").css("background-color", "white");
		}
	})
	
	/**
	 * ---------------------------------End of function for adding------------------------------------
	 */
	
	/**
	 * This function is used to validate required fields before editing
	 */
	function validateRequiredFieldForEdit(){
		var roleId= $("#editRoleDialog").find("#txtRoleID").val();
		if(roleId.trim() === '' || roleId == null)
			return false;
		
		var roleName= $("#editRoleDialog").find("#txtRoleName").val();
		if(roleName.trim() === '' || roleName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit new role
	 */
	function validateOverRangeWhenEditRole(){
		if($("#editRoleDialog").find("#txtRoleID").val().length>50 || $("#editRoleDialog").find("#txtRoleName").val().length>100 
				|| $("#editRoleDialog").find("#txtDescription").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * This function validate Role Name and show message below Role Name's textBox (edit case)
	 */
	$("#editRoleDialog").find("#txtRoleName").keyup(function(){
		var input= $(this).val();
		if(input.trim() === '' || input == null){
			$("#editRoleDialog").find("#errorTxtRoleName").text("Please enter Role Name!");
			$("#editRoleDialog").find("#errorTxtRoleName").css("color", "red");
			$("#editRoleDialog").find("#txtRoleName").css("background-color", "red");
		}else if(input.length>100){
			$("#editRoleDialog").find("#errorTxtRoleName").text("Your input is over range");
			$("#editRoleDialog").find("#errorTxtRoleName").css("color", "red");
			$("#editRoleDialog").find("#txtRoleName").css("background-color", "red");
		}else{
			$("#editRoleDialog").find("#errorTxtRoleName").text("");
			$("#editRoleDialog").find("#txtRoleName").css("background-color", "white");
		}
		
	});
	
	/**
	 * This function is used to warn user when input description over range (edit case)
	 */
	$("#editRoleDialog").find("#txtDescription").keyup(function(){
		var input= $(this).val();
		if(input.length>500){
			$("#editRoleDialog").find("#errorTxtDescription").text("Your input is over range");
			$("#editRoleDialog").find("#errorTxtDescription").css("color", "red");
			$("#editRoleDialog").find("#txtDescription").css("background-color", "red");
		}else{
			$("#editRoleDialog").find("#errorTxtDescription").text("");
			$("#editRoleDialog").find("#txtDescription").css("background-color", "white");
		}
	})
	
	/**
	 * ---------------------------------End of function for editing------------------------------------
	 */
	
	function loadCheckBoxFunction(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/function/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					$("#assignFunctionDialog").find("#listOfFunctions").append('<input type="checkbox" name="functions" value="'+value.functionId+'" />'+ value.functionName+'<br/>');
				});
				
				//VERY IMPORTANT
//				$('input[name="functions"]').each(function () {
//			           //if (this.checked) {
//			               console.log($(this).val());
//			               $(this).prop('checked', true);
//			           //}
//				});
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	}
	
	/**
	 * This function is used to call and set params for message dialog.
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
				effect: "explode",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$("#messageDialog").dialog("close");
				}
			}
		});
	}
	
	loadData();
	loadCheckBoxFunction();
})