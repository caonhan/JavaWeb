$(document).ready(function(){
	
		    	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/SERP/user_profile",
					success: function(data){
						if(data.status== "ok"){
							$("#user_name").val(data.user.name);
							$("#user_birthdate").val(data.user.birthDate);
							$("#user_phonenumber").val(data.user.phoneNumber);
							$("#user_email").val(data.user.email);
							$("#user_address").val(data.user.address);
							$("#user_department").val(data.user.department);
							$("#user_role").val(data.user.role_id);
							$("#user_status").val(data.user.status);
							
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
		    	
	loadData();
})