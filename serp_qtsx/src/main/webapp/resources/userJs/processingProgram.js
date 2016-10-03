$(document).ready(function(){	
	function loadData() {
		/** For page create processing program detail*/
		document.getElementById('checkKnife').style.display = 'none'; //Hidden id checkKnife
		document.getElementById('checkProgramName').style.display = 'none'; //Hidden id checkProgramName
		$("#knife").mouseout(function()
		{
			if($("#knife").val() == ""){
				document.getElementById('checkKnife').style.display = 'run-in'; //Show id checkKnife 
			}else
			{
				document.getElementById('checkKnife').style.display = 'none'; 
			}
		})
		$("#programName").mouseout(function()
		{
			if($("#programName").val() == ""){
				document.getElementById('checkProgramName').style.display = 'run-in'; 
			}else
			{
				document.getElementById('checkProgramName').style.display = 'none'; 
			}
		})
		$("#btnAddPPD").click(function()
		{
			if($("#programName").val() == ""){
				alert("Program Name is null!"); //Alert input is null
			}
			
			if($("#parameter1").val() == 0 || $("#parameter2").val() == 0 || $("#parameter3").val() == 0 || $("#parameter4").val() == 0){
				var parameter = confirm("Are you sure about the parameter:\n" +
						"φd = " + $("#parameter1").val() +
						", φDxAp "+ $("#parameter2").val() +
						", H = " + $("#parameter3").val() +
						", R = " + $("#parameter4").val() + "!"); //Confirm for user
				if(parameter == false){
					$("#parameter1").val('');
					$("#parameter2").val('');
					$("#parameter3").val('');
					$("#parameter4").val('');
				}
			}
			if($("#regime1").val() == 0 || $("#regime2").val() == 0 || $("#regime3").val() == 0){
				var regime = confirm("Are you sure about the parameter:\n" +
						"S = " + $("#regime1").val() +
						", F = " + $("#regime1").val() +
						", t = " + $("#regime1").val() + "!");
				if(regime == false){
					$("#regime1").val('');
					$("#regime2").val('');
					$("#regime3").val('');
				}				
			}
			if($("#parameter1").val() && $("#parameter2").val() 
					&& $("#parameter3").val() && $("#parameter4").val() 
					&& $("#regime1").val() && $("#regime2").val() 
					&& $("#regime3").val() && $("#knife").val() 
					&& $("#theoryTime").val()){
				alert("Save success!");
			}
		})
		
		/** For page create processing program*/
		$("#btnAddPP").click(function()
		{
			alert("Save success!");
		})
		$("#test").click(function()
		{
			alert("Save success!");
		})
	}
	loadData();
});
