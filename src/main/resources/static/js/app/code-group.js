$(document).ready(function() {
	
	//코드그룹 목록 조회
	$("#codeGroupListBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/codegroups",
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log(data);
				
				alert(JSON.stringify(data));
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});

	//코드그룹 상세조회
	$("#codeGroupReadBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/codegroups/" + $("#groupCode").val(),
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log(data);
				
				alert(JSON.stringify(data));
				
				$("#groupName").val(data.groupName);
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});

	//코드그룹 등록 처리
	$("#codeGroupRegisterBtn").on("click", function() {
		var codeGroupObject = {
			groupCode : $("#groupCode").val(),
			groupName : $("#groupName").val()
		};
		
		alert(JSON.stringify(codeGroupObject));

		$.ajax({
			type : "POST",
			url : "/codegroups",
			data : JSON.stringify(codeGroupObject), //JSON.stringify(),
			contentType : "application/json; charset=UTF-8",
			success : function() {
				alert("Created");
			},
			error : function(xhr, textStatus, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});

	//코드그룹 삭제 처리
	$("#codeGroupDeleteBtn").on("click", function() {
		$.ajax({
			type : "DELETE",
			url : "/codegroups/" + $("#groupCode").val(),
			contentType : "application/json; charset=UTF-8",
			success : function() {
				alert("Deleted");
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});

	//코드그룹 수정 처리
	$("#codeGroupModifyBtn").on("click", function() {
		var groupCodeVal = $("#groupCode").val();
		
		var codeGroupObject = {
			groupCode : groupCodeVal,
			groupName : $("#groupName").val()
		};

		$.ajax({
			type : "PUT",
			url : "/codegroups/" + groupCodeVal,
			data : JSON.stringify(codeGroupObject),
			contentType : "application/json; charset=UTF-8",
			success : function() {
				alert("Modified");
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});
	
	//코드그룹 리셋
	$("#codeGroupResetBtn").on("click", function() {
		$("#groupCode").val("");
		$("#groupName").val("");
	});
	
	//CodeDetail
	//CodeDetail 목록조회 List
	$("#codeDetailListBtn").on("click",function() {
		alert("CodeDetail List~!!");
				$.ajax({
					type : "GET",
					url : "/codedetails",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						console.log(data);
						alert(JSON.stringify(data)); //url을 통해서 파라미터로 요청하고 받은 결과객체 List<CodeDetail>: 매퍼 참조
					},
					error : function(xhr, status, error) {
						alert("code:" + xhr.status + "\n" 
							+ "message:" + xhr.responseText + "\n" 
							+ "error:" + error);
					}
				});
			});

	//CodeDetail 상세조회 Read
	$("#codeDetailReadBtn").on("click",function(){
		alert("Reading~!!");
				$.ajax({
					type : "GET",
					url : "/codedetails/"+$("#codeGroupCode").val()+"/"+$("#codeValue").val(),
					contentType : "application/jason; charset=UTF-8",
					success : function(data){
						console.log(data);
						alert(JSON.stringify(data));
						$("#codeGroupCode").val(data.groupCode);
						$("#codeValue").val(data.codeValue);
						$("#codeName").val(data.codeName);
					},
					error : function(xhr, status, error){
						alert("code:" + xhr.status + "\n"
							+ "message:" + xhr.responseText + "\n"
							+ "error:" + error)
					}
				});
	});

	
	//CodeDetail 등록 Register
	$("#codeDetailRegisterBtn").on("click",function(){
			var codeDetailObject = {
					groupCode : $("#codeGroupCode").val(),
					codeValue : $("#codeValue").val(),
					codeName : $("#codeName").val(),
			};
			
			alert("CodeDetail Registering~!!");
			alert(JSON.stringify(codeDetailObject));
			
			$.ajax({
				type : "POST",
				url : "/codedetails",
				data : JSON.stringify(codeDetailObject),
				contentType : "application/json; charset=UTF-8",
				success : function(){
					alert("CodeDetail Created");
				},
				error : function(xhr, textStatus, error){
					alert("code:" + xhr.status + "\n"
							+ "message:" + xhr.responseText + "\n"
							+ "error:"+error);
				}
			});
	});
	

	//CodeDetail 삭제 Delete
	$("#codeDetailDeleteBtn").on("click", function() {
		alert("CodeDetail Deleting~!!");
		$.ajax({
			type : "DELETE",
			url : "/codedetails/" + $("#codeGroupCode").val()+"/"+$("#codeValue").val(),
			contentType : "application/json; charset=UTF-8",
			success : function() {
				alert("CodeDetail Deleted");
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});


	//CodeDetail 수정 Modify
	$("#codeDetailModifyBtn").on("click", function() {
		var codeGroupCodeVal = $("#codeGroupCode").val();
		var codeValueVal = $("#codeValue").val();
		
		var codeGroupCodeObject = {
			groupCode : codeGroupCodeVal,
			codeValue : codeValueVal,
			codeName : $("#codeName").val()
		};
		
		alert("Modifying~!!");
		alert("codeGroupCodeObject "+JSON.stringify(codeGroupCodeObject));
		$.ajax({
			type : "PUT",
			url : "/codedetails/" + codeGroupCodeVal +"/"+codeValueVal,
			data : JSON.stringify(codeGroupCodeObject),
			contentType : "application/json; charset=UTF-8",
			success : function() {
				alert("Modified");
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});
	
	//CodeDetail 입력값 리셋 Reset
	$("#codeDetailResetBtn").on("click", function() {
		alert("CodeDetail Resetting~!!");
		$("#codeGroupCode").val("");
		$("#codeValue").val("");
		$("#codeName").val("");
	});
});
