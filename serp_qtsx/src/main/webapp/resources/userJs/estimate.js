function loadData() {
	var modeApprove = $('#modeApprove').val();
	var modeEdit = $('#modeEdit').val();
	$('#detailForm :input').prop("disabled", true);
	$('.app').prop("readonly", true);
	$(':radio:not(:checked)').attr('disabled', true);
	$('#detailForm :input').prop("disabled", modeEdit != "true");
	$('.app').prop("readonly", modeApprove != "true");
	$(':radio:not(:checked)').attr('disabled', modeApprove != "true");
	$('#detailForm')[0].reset();
	$("#deleteDetail").prop("disabled", true);
	$("#editDetail").prop("disabled", true);
	$("#addDetail").prop("disabled", true);
	var idx = 1;
	$.ajax({
		dataType : "json",
		type : 'GET',
		data : {},
		contentType : "application/json",
		url : "getDetailList",
		success : function(data) {
			if (data.details.length == 0) {
				$('#listEstimateDetail tbody').empty();
				$("#btnSaveEstimate").prop("disabled", true);
			} else {
				if (modeApprove == "true" || modeEdit == "true")
					$("#btnSaveEstimate").prop("disabled", false);
				else
					$("#btnSaveEstimate").prop("disabled", true);
				$('#listEstimateDetail tbody').empty();
				$.each(data.details, function(key, value) {
					$('<tr>').append(
							$('<td>').text(idx++),
							$('<td>').text(value.element),
							$('<td>').text(value.edPhi),
							$('<td>').text(value.edX),
							$('<td>').text(value.edY),
							$('<td>').text(value.edZ),
							$('<td>').text(value.material),
							$('<td>').text(value.edQuantity),
							$('<td>').text(value.eUnit),
							$('<td>').text(value.edMaterialWeight),
							$('<td>').text(value.edMaterialCost),
							$('<td>').text(value.edLaborCost),
							$('<td>').text(value.edEquipmentCost),
							$('<td>').text(value.edToolCost),
							$('<td>').text(value.edExternalCost),
							$('<td>').text(value.edPrice),
							$('<td>').text(value.edTotalCost),
							$('<td>').html(
									'<input type="radio" name="sid" id="'
											+ value.element + '"/>')).appendTo(
							'#listEstimateDetail');
				});
			}
		},
		error : function() {
			callMessageDialog("Error", 'Cannot get details list!!');
		}
	});
}

$(document)
		.ready(
				function() {
					$('#elementname').on('change', function() {
						changeButton();
					});
					$('#elementunit').on('change', function() {
						changeButton();
					});
					$('#elementid').on('change', function() {
						changeButton();
					});
					$('.selectAction')
							.change(
									function() {
										var selectOption = $(this).find(
												"option:selected").val();
										if (selectOption != "") {
											var form = $('.formEstimate');
											var param = selectOption.split("=");
											var estimateId = param[1];
											var action = param[0];
											$('.estimateId').val(estimateId)
											form.attr("action", action);
											if (action == "viewEstimate") {
												$('.viewMode').val("viewMode");
												form.attr("action",
														'editEstimate');
												form.submit();
											} else if (action == "editEstimate") {
												form.submit();
											} else {
												$("#dialog")
														.find("#messageContent")
														.text(
																'Are you sure you want to delete Estimate: "'
																		+ estimateId
																		+ '"?');
												// show delete estimate dialog.
												$("#dialog")
														.dialog(
																{
																	show : {
																		effect : "blind",
																		duration : 500
																	},
																	title : "Delete Estimate Confirm",
																	height : 200,
																	width : 400,
																	buttons : {
																		"OK" : function() {
																			$
																					.ajax(
																							{
																								type : form
																										.attr('method'),
																								url : form
																										.attr('action')
																										+ "/"
																										+ estimateId,
																								data : {}
																							})
																					.done(
																							function(
																									data) {
																								callMessageDialog(
																										"Delete",
																										data.deleteStatus);
																								location
																										.reload();
																							})
																					.fail(
																							function(
																									data) {
																							});
																		},
																		"Cancel" : function() {
																			$(
																					"#dialog")
																					.dialog(
																							"close");
																		}
																	}
																})
											}
										}
									});
					var clickedButton;
					$(".button").click(function() {
						clickedButton = $(this).attr("name");
					});
					$('form#detailForm').submit(function(event) {
						event.preventDefault(); // Prevent the form from
						// submitting via the
						// browser
						var form = $(this);
						$.ajax({
							type : form.attr('method'),
							url : clickedButton,
							data : form.serialize()
						}).done(function(data) {
							callMessageDialog("Add", data.addStatus);
							loadData();
							$('#validateMessagePass').prop("hidden", true);
							$('#validateMessageFail').prop("hidden", true);
							$('#validateMessagePassN').prop("hidden", true);
							$('#validateMessageFailN').prop("hidden", true);
							$('#validateMessagePassU').prop("hidden", true);
							$('#validateMessageFailU').prop("hidden", true);
						}).fail(function(data) {
							callMessageDialog("Add", data.addStatus);
						});
					});

					$('form#estimateForm')
							.submit(
									function(event) {
										event.preventDefault(); // Prevent the
										// form from
										// submitting
										// via the
										// browser
										var form = $(this);
										if (clickedButton != "cancelEditEstimate") {
											$
													.ajax(
															{
																type : form
																		.attr('method'),
																url : clickedButton,
																data : form
																		.serialize()
															})
													.done(
															function(data) {
																callMessageDialog(
																		"Add",
																		data.addStatus);
																window.location.href = "estimate";
															})
													.fail(
															function(data) {
																callMessageDialog(
																		"Add",
																		"Submit fail");
															});
										} else {
											$("#dialog")
													.find("#messageContent")
													.text(
															'Are you sure you want to cancel?');
											// show delete Role dialog.
											$("#dialog")
													.dialog(
															{
																show : {
																	effect : "blind",
																	duration : 200
																},
																title : "Cancel Confirm",
																height : 200,
																width : 400,
																buttons : {
																	"OK" : function() {
																		$
																				.ajax(
																						{
																							type : form
																									.attr('method'),
																							url : "cancelEditEstimate",
																							data : {}
																						})
																				.done(
																						function(
																								data) {
																							window.location.href = "estimate";
																						});
																	},
																	"Cancel" : function() {
																		$(
																				"#dialog")
																				.dialog(
																						"close");
																	}
																}
															})
										}

									});

					$("#listEstimateDetail tbody")
							.on(
									'click',
									'tr',
									function() {
										var mode = $('#modeEdit').val();
										var elementId = $(this)
												.find('td:eq(1)').text();
										$("#" + elementId)
												.prop("checked", true);
										if (mode == "true") {
											$("#deleteDetail").prop("disabled",
													false);
											$("#editDetail").prop("disabled",
													false);
										}
										$
												.ajax({
													dataType : "json",
													type : 'GET',
													data : {},
													contentType : "application/json",
													url : "getDetail/"
															+ elementId,
													success : function(data) {
														if (data.detail == null
																|| data.element == null) {
															callMessageDialog(
																	"Error",
																	'No data to get');
														} else {
															var element = data.element;
															$("#elementid")
																	.val(
																			element.eid);
															$("#elementname")
																	.val(
																			element.ename);
															$("#elementunit")
																	.val(
																			element.eunit);
															var detail = data.detail;
															$("#quantity")
																	.val(
																			detail.edQuantity);
															$("select#material")
																	.val(
																			data.material);
															$("#phi")
																	.val(
																			detail.edPhi);
															$("#x").val(
																	detail.edX);
															$("#y").val(
																	detail.edY);
															$("#z").val(
																	detail.edZ);
															$("#materialcost")
																	.val(
																			detail.edMaterialCost);
															$("#laborcost")
																	.val(
																			detail.edLaborCost);
															$("#equipmentcost")
																	.val(
																			detail.edEquipmentCost);
															$("#toolcost")
																	.val(
																			detail.edToolCost);
															$("#externalcost")
																	.val(
																			detail.edExternalCost);
															$("#price")
																	.val(
																			detail.edPrice);
															$("#totalprice")
																	.val(
																			detail.edTotalCost);
															$("#edId")
																	.val(
																			detail.edId);
														}
													},
													error : function() {
														callMessageDialog(
																"Error",
																'cannot get element or detail');
													}
												});
									});
				});
function changeButton() {
	var inputValue = $('#elementid').val();
	var name = checkEStringPattern($('#elementname').val());
	var unit = checkEStringPattern($('#elementunit').val());
	var id = false;
	if (checkEIdPattern(inputValue)) {
		var promise = checkEID(inputValue);
		promise.success(function(data) {
			if (data.checkStatus == "check.pass") {
				id = true;
			} else
				id = false;
			if (name && unit && id) {
				$("#addDetail").prop("disabled", false);
				$("#editDetail").prop("disabled", false);
			} else {
				$("#addDetail").prop("disabled", true);
				$("#editDetail").prop("disabled", true);
			}
			$('#validateMessagePass').prop("hidden", !id);
			$('#validateMessageFail').prop("hidden", id);
		});
	}

	$('#validateMessagePassN').prop("hidden", !name);
	$('#validateMessageFailN').prop("hidden", name);
	$('#validateMessagePassU').prop("hidden", !unit);
	$('#validateMessageFailU').prop("hidden", unit);
}
function checkEID(inputValue) {
	return $.ajax({
		dataType : "json",
		type : 'GET',
		data : {},
		contentType : "application/json",
		url : "checkElementId/" + inputValue
	});

}
function checkEIdPattern(id) {
	var regexp = /^[a-zA-Z0-9-_]+$/;
	if (id.search(regexp) == -1) {
		return false;
	} else {
		return true;
	}
}
function checkEStringPattern(string) {
	if (string.trim().length == 0) {
		return false;
	} else {
		return true;
	}
}

function callMessageDialog(title, messageContent) {
	$("#messageDialog").find("#messageContent").text(messageContent);
	$("#messageDialog").dialog({
		show : {
			effect : "blind",
			duration : 200
		},
		title : title,
		height : 150,
		width : 300,
		hide : {
			effect : "explode",
			duration : 200
		},
		buttons : {
			"OK" : function() {
				$("#messageDialog").dialog("close");
			}
		}
	});
}
