$(document).ready(function() {
	//CodeGroup
	//코드그룹 목록 조회
	$("#codeGroupListBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/codegroups",
			contentType : "application/json; charset=UTF-8",
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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
});
