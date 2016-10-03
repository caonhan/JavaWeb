$(document).ready(function(){
	function checkLoggedIn(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/SERP/isLoggedIn",
			contentType: "application/json",
			success: function(data){
//				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
//				}
//				$.each(data.list,function(key,value){
//					$('<tr>').append(
//							$('<td>').text(value.roleId),
//							$('<td>').text(value.roleName),
//							$('<td>').text(value.creatorId),
//							$('<td>').text(value.createdDate),
//							$('<td>').text(value.modifierId),
//							$('<td>').text(value.modifiedDate),
//							//$('<td>').html('<button class="btn btn-primary btnEditNvbh" data-id="'+value.roleId+'">Edit</button>'),
//							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.roleId+'"><option value="Options" disabled selected>Options</option><option value="Detail">Detail</option><option value="Edit">Edit</option><option value="Delete">Delete</option>'
//									+'<option value="AssignFunction">Assign Function</option></select>')
//					).appendTo('#listRole');
//				});
//				console.log(data);
//				action();
//
//				$('#listRole').DataTable( {
//					"pagingType": "full_numbers"
//			    } );
				if(data.isLoggedIn==true){
					//alert("Đã logged in");
					$('#LogInLogOut').append('<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">'
							+'<i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> '+data.userName+' </span>'
							+'<span class="caret"></span>'
							+'</button>'
							+'<ul class="dropdown-menu">'
							+'<li>'
							+'<a class="ajax-link" href="/SERP/user">'
							+'<i class="glyphicon glyphicon-user"></i>'
							+'<span> Profile</span>'
							+'</a>'
							+'</li>'
							+'<li class="divider"></li>'
							+'<li> <a href="/SERP/logout">Logout</a></li>'
							+'</ul>');
				}else{
					//alert("Chưa logged in");
					$('#LogInLogOut').append('<a href="/SERP/login" style="color:white;" >Log In</a>');
				}
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	checkLoggedIn();
})