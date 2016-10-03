$(document).ready(function(){
	
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "processing-document/check-role-viewall",
		contentType: "application/json",
		success: function(data){
			if(data.list == false){
				$("#deleteDialog").find("#messageContent").text('You not have right to access this function! Please check again!');
				$("#deleteDialog").dialog({
					show:{
						effect: "blind",
						duration: 1000
					},
					height: 200,
					width: 400,
					modal: true,
					title : "Error",
					buttons :{
						"Close" : function(){
								$("#deleteDialog").dialog("close");
								window.location.replace('/SERP');
							}
						}
				});
				
			}
		}
	});
	
	//hàm load dữ liệu ra bảng
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "processing-document/list",
			contentType: "application/json",
			success: function(data){
				
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.pdid),
							$('<td>').text(value.pdname),
							$('<td>').text(value.date),
							$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.pdid+'" >View</button>'),
							$('<td>').html('<button class="btn btn-primary btnDelete" data-id="'+value.pdid+'">Delete</button>')
					).appendTo('#datatable');
				});
				$('#datatable').dataTable();
				console.log(data);
				action();			
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	
	function action(){
		//click button delete
		$(".btnDelete").click(function(){
			//set confirm message
			var pdid= $(this).data('id');
			$("#deleteDialog").find("#messageContent").text('Are you sure delete processing document "' + pdid+'"?');
			$("#deleteDialog").dialog({
				show:{
					effect: "blind",
					duration: 1000
				},
				height: 200,
				width: 400,
				modal: true,
				title: "Are you sure delete?",
				buttons: {
					"OK": function(){
						$.ajax({
                        	dataType: "json",
                            type: 'POST',
                            data:{},
                            contentType : "application/json",
                            url: "/SERP/processingdocument/delete/" +pdid,
                            success: function (data) {
                                //nếu nvbh có thể delete đc
                            	if(data.deleteStatus==true){
                            		$("#deleteDialog").dialog("close");
                                    $("#datatable").dataTable().fnDestroy();
									$('#datatable tbody').empty();
                                    loadData();
                            	}else{//nếu ko delete đc
                            		$("#deleteDialog").dialog("close");
                            		alert("Không thể xóa NVBH này!");
                            	}
                            },
                            error: function (data) {
                            	alert("Có lỗi xảy ra!");
                            }
                        });
					},
					"Cancel": function(){
						$("#deleteDialog").dialog("close");
					}
				},
				hide:{
					effect: "explode",
					duration: 1000
				}
			});
		});
		
		//click on button create
		$("#createProcessingDocument").click(function(){
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "processing-document/check-role",
				contentType: "application/json",
				success: function(data){
					
						if(data.list == true){
							window.location.replace('/SERP/processing-document-create');
						}
						else{
							$("#deleteDialog").find("#messageContent").text('You not have right to access this function');
							$("#deleteDialog").dialog({
								show:{
									effect: "blind",
									duration: 1000
								},
								height: 200,
								width: 400,
								modal: true,
								title: "Are you sure delete?",
								buttons: {
									"OK": function(){
										$("#deleteDialog").dialog("close");
									}
								}
							});
						}
				}
			});
		});
		
		//click view
		$(".btnView").click(function(){
			var pdid= $(this).data('id');
		
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "processing-document/get_a_processing_document/" + pdid,
				contentType: "application/json",
				success: function(data){
						$("#production_order").text(data.list.orders);
						$("#processing_technology_note").text(data.list.processingTechnology);
						$("#program_note").text(data.list.programNote);
						$("#operation_trace_note").text(data.list.operationTraceNote);
						$("#limit_inventory_note").text(data.list.limitInventory);
						$("#create_by").text(data.list.pdname);
						$("#create_date").text(data.list.date);
					}
				});
			
			$("#Dialog").dialog({
				show:{
					effect: "blind",
					duration: 1000
				},
				height: 500,
				width: 400,
				modal: true,
				title : "View all",
				buttons :{
					"Close" : function(){
							$("#Dialog").dialog("close");
						}
					}
			});
		});
	}
	
	loadData();
	
	
});