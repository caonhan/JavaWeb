$(document).ready(function() {
	loadMaterialsAndElements("elementSelectBox2", "materialSelectBox2");
	deleteAction();
	updateAction();
	
	function reloadTable(){
		console.log("Reload table");
		$('#detailTable tbody').empty();
		
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: {},
			url: "/SERP/limitInventory/lidJSonList",
			contentType: "application/json",
			success: function(data){
				if(data.lides.length == 0){
					alert("No data in table.");
				} else {
					var rowCount = 1;
					
					$.each(data.lides, function(key, value){
						$('<tr>').append(
								$('<td>').text(rowCount),
								$('<td>').text(value.elementName),
								$('<td>').text(value.phi),
								$('<td>').text(value.length),
								$('<td>').text(value.width),
								$('<td>').text(value.height),
								$('<td>').text(value.materialName),
								$('<td>').text(value.quantity),
								$('<td>').text(value.elementUnit),
								$('<td>').text(value.note),
								$('<td>').html('<div class="dropdown">' + 
						        		   '<button class="btn btn-primary dropdown-toggle" type="button"' +
						        		   ' data-toggle="dropdown">' + data.actionBtn +
						        		   '<span class="caret"></span></button><ul class="dropdown-menu">' +
						        		   '<li><button type="button" class="btn btn-info" data-toggle="modal" ' +
						        		   'data-target="#updateLimitInventoryDetail" id="update" ' + 
						        		   'data-id="' + (rowCount) + '" formnovalidate>' + data.updateBtn + 
						        		   '</button></li><li><input type="button" class="btn btn-warning" ' +
						        		   'id="delete" data-id="' + (rowCount++) + '" ' + 
						        		   'formnovalidate value="' + data.deleteBtn + '" /></li></ul></div>')
						        		   ).appendTo('#detailTable');
					});
					
					console.log(data);
					deleteAction();	// Recall event onclick button Delete
					updateAction(); // Recall all events of button Update
				}
			},
			error: function(){
				alert("Cannot get data from database!");
			}
		});
	};
	
	// Load data to Material and Element dropdown box
	function loadMaterialsAndElements(elementBoxId, materialBoxId) {
		$.ajax({
    		dataType: "json",
			type: 'POST',
			data: {},
			contentType: "application/json",
			url: "/SERP/limitInventory/materialElementJSonList",
			success: function(data){
				if(data.materials.length == 0){
					alert('No material data to get');
				} else {
					var options = '';
					$.each(data.materials, function(key, value){
		                options += '<option value="' + value.mid + '">' + value.mid 
		                + ' - ' + value.mname + ' - ' + value.mprice + '</option>';
					});
		            
					$("select#" + materialBoxId).html(options);
				}
				
				if(data.elements.length == 0){
					alert('No elements data to get');
				} else {
					var options = '';
					$.each(data.elements, function(key, value){
		                options += '<option value="' + value.eid + '">' + value.eid 
		                + ' - ' + value.ename + ' - ' + value.eunit + '</option>';
					});
		            
					$("select#" + elementBoxId).html(options);
				}
			},
			error: function(){
				alert('Cannot get material and element list!!');
			}
    	});
	}

	function deleteAction() {
		$('input#delete').on('click', function() {
			var r = confirm("Do you really want to delete this row?");
			
			if (r == true) {
				// Get index of this row
				var detailId = $(this).data('id');
				
				// Call ajax function
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data: {},
					contentType: "application/json",
					url: "/SERP/limitInventory/deleteDetail/" + detailId,
					success: function(data){
						console.log(data.deleteStatus);
						if(data.deleteStatus == true){
							reloadTable();
						} else if (!data.deleteStatus) {
							alert("Delete failed! Please try again later.");
						}
					},
					error: function(){
						alert('Cannot delete this details!!');
					}
		    	});
			}
		});
	}
	
	// Convert Form data to JSON Object
	(function ($) {
	    $.fn.serializeFormJSON = function () {

	        var o = {};
	        var a = this.serializeArray();
	        $.each(a, function () {
	            if (o[this.name]) {
	                if (!o[this.name].push) {
	                    o[this.name] = [o[this.name]];
	                }
	                o[this.name].push(this.value || '');
	            } else {
	                o[this.name] = this.value || '';
	            }
	        });
	        return o;
	    };
	})(jQuery);
	
	$('#addNew').click(function() {
		loadMaterialsAndElements("elementSelectBox1", "materialSelectBox1");
	});
		
	$("#addNewForm").on('submit', function (e) {
		// Stop form submission
		e.preventDefault();
		
		var form = $(this);
		var lide = JSON.stringify(form.serializeFormJSON());
		
		$.ajax({
			dataType: "json",
			type: form.attr('method'),
			data: lide,
			contentType: "application/json",
			url: form.attr('action'),
			success: function(data){
				if(data.addStatus == true){
					// close dialog and clear input
					console.log('Clear input and close dialog');
					$("#addLimitInventoryDetail").find("#close").trigger("click");
					$("#addLimitInventoryDetail").find("#txtPhi").val('');
					$("#addLimitInventoryDetail").find("#txtLength").val('');
					$("#addLimitInventoryDetail").find("#txtWidth").val('');
					$("#addLimitInventoryDetail").find("#txtHeight").val('');
					$("#addLimitInventoryDetail").find("#txtQuantity").val('');
					$("#addLimitInventoryDetail").find("#txtNote").val('');
					
					// after that, reload DataTable
					reloadTable();
				} else if(data.addStatus == false){
					alert('Add failed! Please try again later.');
				}
			},
			error: function(){
				alert('Add failed! Please try again later.');
			}
		});
	});
	
	function updateAction() {
		$('button#update').click(function() {
			var detailId = $(this).data('id');
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: {},
				contentType: "application/json",
				url: "/SERP/limitInventory/detail/" + detailId,
				success: function(data){
					if(data.status == true){
						console.log(data.detail.elementId);
						console.log(data.detail.materialId);
						
						// Fill input with model
						$("#updateLimitInventoryDetail").find("#detailIndex").val(detailsId - 1);
						$("#updateLimitInventoryDetail").find("#elementSelectBox2").val(data.detail.elementId);
						console.log($("#updateLimitInventoryDetail").find("#elementSelectBox2").val());
						$("#updateLimitInventoryDetail").find("#materialSelectBox2").val(data.detail.materialId);
						console.log($("#updateLimitInventoryDetail").find("#materialSelectBox2").val());
						$("#updateLimitInventoryDetail").find("#txtPhi").val(data.detail.phi);
						$("#updateLimitInventoryDetail").find("#txtLength").val(data.detail.length);
						$("#updateLimitInventoryDetail").find("#txtWidth").val(data.detail.width);
						$("#updateLimitInventoryDetail").find("#txtHeight").val(data.detail.height);
						$("#updateLimitInventoryDetail").find("#txtQuantity").val(data.detail.quantity);
						$("#updateLimitInventoryDetail").find("#txtNote").val(data.detail.note);
					} else if(data.status == false){
						alert('Get detail failed! Please try again later.');
					}
				},
				error: function(){
					alert('Error when getting detail! Please try again later.');
				}
			});
		});
	}
	
	$("#editForm").on('submit', function (e) {
		// Stop form submission
		e.preventDefault();
		
		var index = $("#updateLimitInventoryDetail").find("#detailIndex").val();
		var form = $(this);
		var lide = JSON.stringify(form.serializeFormJSON());
		
		$.ajax({
			dataType: "json",
			type: form.attr('method'),
			data: lide,
			contentType: "application/json",
			url: form.attr('action') + "/" + index,
			success: function(data){
				if(data.editStatus == true){
					// close dialog
					$("#close").trigger("click");
					
					// after that, reload DataTable
					reloadTable();
				} else if(data.editStatus == false){
					alert('Update failed! Please try again later.');
				}
			},
			error: function(){
				alert('Update failed! Please try again later.');
			}
		});
	});
});