$(document).ready(function(){
	var bool;
	
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "processing-document/check-role",
		contentType: "application/json",
		success: function(data){
			if(data.list == false){
				window.location.replace('/SERP/processing-document-viewall');
			}
		}
	});
	
	//hàm load dữ liệu ra bảng
	function LoadDataLimitInventory(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "processing-document/list_id_limit_inventory",
			contentType: "application/json",
			success: function(data){
				
				$.each(data.list,function(key,value){
					$('#limitInventorySelect').append($('<option>').text(value.limitInventoryId).attr('value', value.limitInventoryId));
				});

			},
			error: function(){
				alert("Cannot get data!");
			}
		});
	};
	
	function LoadDataProductionOrder(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "processing-document/list_id_production_order",
			contentType: "application/json",
			success: function(data){
				
				$.each(data.list,function(key,value){
					$('#productionOrderSelect').append($('<option>').text(value.poid).attr('value', value.poid));
				});
			},
			error: function(){
				alert("Cannot get data!");
			}
		});
	};
	
	function LoadDataProcesingTechnology(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "processing-document/list_id_processing_technology",
			contentType: "application/json",
			success: function(data){
				
				$.each(data.list,function(key,value){
					$('#processing_technology_note').append($('<option>').text(value.id).attr('value', value.id));
				});
			},
			error: function(){
				alert("Cannot get data!");
			}
		});
	};
	
	function action(){
		$("#send").click(function(){
			$("#Dialog").find("#messageContent").text('Are you sure create new processing document?');
			$("#Dialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "",
			height: 200,
			width: 500,
			modal: true,
			buttons:{
				/**
				 * Function when click add button on add processing document
				 */
				"Yes": function(){
							var id = 1233;
							var createDate = new Date();
							var production_order = $("#productionOrderSelect option:selected").val();
							var processing_technology_note = $("#processing_technology_note option:selected").val();
							var program_note = $("#program_note option:selected").val();
							var operation_trace_note = $("#operation_trace_note option:selected").val();
							var limit_inventory_note = $("#limitInventorySelect option:selected").val();
							
							if(production_order == "" || production_order == null || operation_trace_note=="" || operation_trace_note == null
									|| limit_inventory_note == "" || limit_inventory_note == null){
								//alert(limit_inventory_note);
								Dialog('Please fill in all required field');
							}
							else{
								 addNewProcessingDocument(createDate,production_order, operation_trace_note, limit_inventory_note, 
										processing_technology_note, program_note, name, id);			
							}
				},
				"No": function(){
					$("#Dialog").dialog("close");
				}
			},
			hide: {
				effect: "explode",
				duration: 500
			}
			});
		});
		
		$("#view_production_order").click(function(){
			var production_order = $("#productionOrderSelect option:selected").val();
			
			if(production_order == "" || production_order == null){
				Dialog('No file a available! check again');
			}
			else{
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: "processing-document/get_a_production_order/" + production_order,
					contentType: "application/json",
					success: function(data){
							$("#order").text(data.list.orders);
							$("#quantity").text(data.list.poQuantity);
							$("#unit").text(data.list.poUnit);
							$("#starttime").text(data.list.poStarttime);
							$("#finishtime").text(data.list.poFinishtime);
							$("#processtechnology").text(data.list.poProcessTechnology);
							$("#factoryManager").text(data.list.userByPoFactoryManager);
						}
					});
				$("#Dialog_detail").dialog({
					show:{
						effect: "blind",
						duration: 1000
					},
					height: 500,
					width: 400,
					modal: true,
					title : "Production detail",
					buttons :{
						"Close" : function(){
								$("#Dialog_detail").dialog("close");
							}
						}
				});
			}
		});
		
		$("#download_production_order").click(function(){
			var production_order = $("#productionOrderSelect option:selected").val();
			
			if(production_order == "" || production_order == null){
				Dialog('No file a available! check again');
			}
		});
		
		$("#view_processing_technology_note").click(function(){
			var processing_technology_note = $("#processing_technology_note option:selected").val();
			if(processing_technology_note == "" || processing_technology_note== null){
				Dialog('No file a available! check again');
			}
		});
		
		$("#download_processing_technology_note").click(function(){
			var processing_technology_note = $("#processing_technology_note option:selected").val();
			if(processing_technology_note == "" || processing_technology_note== null){
				Dialog('No file a available! check again');
			}
		});
			
		$("view_program_note").click(function(){
			var program_note = $("#program_note option:selected").val();
			if(program_note == "" || program_note == null){
				Dialog('No file a available! check again');
			}
		});
		$("download_program_note").click(function(){
			var program_note = $("#program_note option:selected").val();
			if(program_note == "" || program_note == null){
				Dialog('No file a available! check again');
			}
		});
		
		$("#view_operation_trace_note").click(function(){
			var operation_trace_note = $("#operation_trace_note option:selected").val();
			if(operation_trace_note == "" || operation_trace_note == null){
				Dialog('No file a available! check again');
			}
		});
		$("#download_operation_trace_note").click(function(){
			var operation_trace_note = $("#operation_trace_note option:selected").val();
			if(operation_trace_note == "" || operation_trace_note == null){
				Dialog('No file a available! check again');
			}
		});
		
		$("#view_limit_inventory_note")/click(function(){
			var limit_inventory_note = $("#limitInventorySelect option:selected").val();
			if(limit_inventory_note == "" || limit_inventory_note == null){
				Dialog('No file a available! check again');
			}
		});
		$("#download_limit_inventory_note")/click(function(){
			var limit_inventory_note = $("#limitInventorySelect option:selected").val();
			if(limit_inventory_note == "" || limit_inventory_note == null){
				Dialog('No file a available! check again');
			}
		});
	}
	
	
	function addNewProcessingDocument(createDate,production_order, operation_trace_note, limit_inventory_note, 
			processing_technology_note, program_note, name, id){
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					date: createDate,
					limitInventory: limit_inventory_note,
					orders : production_order,
					processingTechnology : processing_technology_note,
					programNote : program_note,
					operationTraceNote : operation_trace_note,
					name: name,
					pdid: id
				}),
			contentType: "application/json",
			url: "/SERP/processing-document/add",
			success: function(data){
				$("#Dialog").dialog("close");
				window.location.replace('/SERP/processing-document-viewall');
			},
			error: function(){
				$("#Dialog").dialog("close");
				alert('Exception can not add database -_-');
			}
		});
	}
	
	function Dialog(text){
		$("#Dialog").find("#messageContent").text(text);
		$("#Dialog").dialog({
			show:{
				effect: "blind",
				duration: 1000
			},
			height: 200,
			width: 400,
			modal: true,
			title: "",
			buttons: {
				"Close": function(){
					$("#Dialog").dialog("close");
				}
			}
		});
	}
	LoadDataLimitInventory();
	LoadDataProductionOrder();
	LoadDataProcesingTechnology();
	action();
	
	
});