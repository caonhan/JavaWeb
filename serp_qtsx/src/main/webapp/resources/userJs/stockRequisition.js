/**
 * @author: KhangNDD
 */

$(document).ready(function() {
	loadMaterialsAndElements("elementSelectBox2", "materialSelectBox2");
	deleteAction();
	updateAction();
	
	function reloadTable(){
		console.log("Reload table");
		$('#detailsTable tbody').empty();
		
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: {},
			url: "/SERP/stockRequisition/srdJsonList",
			contentType: "application/json",
			success: function(data){
				if(data.srdes.length == 0){
					alert("No data in table.");
				} else {
					var rowCount = 1;
					
					$.each(data.srdes, function(key, value){
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
						        		   'data-target="#updateStockRequisitionDetails" id="update" ' + 
						        		   'data-id="' + (rowCount) + '" formnovalidate>' + data.updateBtn + 
						        		   '</button></li><li><input type="button" class="btn btn-warning" ' +
						        		   'id="delete" data-id="' + (rowCount++) + '" ' + 
						        		   'formnovalidate value="' + data.deleteBtn + '" /></li></ul></div>')
						        		   ).appendTo('#detailsTable');
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
			url: "/SERP/stockRequisition/materialElementJsonList",
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
				var detailsId = $(this).data('id');
				
				// Call ajax function
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data: {},
					contentType: "application/json",
					url: "/SERP/stockRequisition/deleteDetails/" + detailsId,
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
		var srde = JSON.stringify(form.serializeFormJSON());
		
		$.ajax({
			dataType: "json",
			type: form.attr('method'),
			data: srde,
			contentType: "application/json",
			url: form.attr('action'),
			success: function(data){
				if(data.addStatus == true){
					// close dialog and clear input
					console.log('Clear input and close dialog');
					$("#addStockRequisitionDetails").find("#close").trigger("click");
					$("#addStockRequisitionDetails").find("#txtPhi").val('');
					$("#addStockRequisitionDetails").find("#txtLength").val('');
					$("#addStockRequisitionDetails").find("#txtWidth").val('');
					$("#addStockRequisitionDetails").find("#txtHeight").val('');
					$("#addStockRequisitionDetails").find("#txtQuantity").val('');
					$("#addStockRequisitionDetails").find("#txtNote").val('');
					
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
			var detailsId = $(this).data('id');
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: {},
				contentType: "application/json",
				url: "/SERP/stockRequisition/details/" + detailsId,
				success: function(data){
					if(data.status == true){
						console.log(data.detail.stockRequisitionDetailsId);
						
						// Fill input with model
						$("#updateStockRequisitionDetails").find("#detailId").val(data.detail.stockRequisitionDetailsId);
						$("#updateStockRequisitionDetails").find("#detailIndex").val(detailsId - 1);
						$("#updateStockRequisitionDetails").find("#elementSelectBox2").val(data.detail.elementId);
						console.log($("#updateStockRequisitionDetails").find("#elementSelectBox2").val());
						$("#updateStockRequisitionDetails").find("#materialSelectBox2").val(data.detail.materialId);
						console.log($("#updateStockRequisitionDetails").find("#materialSelectBox2").val());
						$("#updateStockRequisitionDetails").find("#txtPhi").val(data.detail.phi);
						$("#updateStockRequisitionDetails").find("#txtLength").val(data.detail.length);
						$("#updateStockRequisitionDetails").find("#txtWidth").val(data.detail.width);
						$("#updateStockRequisitionDetails").find("#txtHeight").val(data.detail.height);
						$("#updateStockRequisitionDetails").find("#txtQuantity").val(data.detail.quantity);
						$("#updateStockRequisitionDetails").find("#txtNote").val(data.detail.note);
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
		
		var index = $("#updateStockRequisitionDetails").find("#detailIndex").val();
		var form = $(this);
		var srde = JSON.stringify(form.serializeFormJSON());
		
		$.ajax({
			dataType: "json",
			type: form.attr('method'),
			data: srde,
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