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
			data : JSON.stringify(codeGroupObject),
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
	
	$("#codeGroupResetBtn").on("click", function() {
		$("#groupCode").val("");
		$("#groupName").val("");
	});
	
});
