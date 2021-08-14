$(document).ready(function() {
	//CodeDetail
	//코드 목록조회 List
	$("#codeDetailListBtn").on("click",function() {
		alert("CodeDetail List~!!");
				$.ajax({
					type : "GET",
					url : "/codedetails",
					contentType : "application/json; charset=UTF-8",
					//엑세스 토큰을 요청 헤더로 서버에 전달한다
					header: {
						"Authorization":"Bearer" + ACCESS_TOKEN
					},
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

	//코드 상세조회 Read
	$("#codeDetailReadBtn").on("click",function(){
		alert("Reading~!!");
				$.ajax({
					type : "GET",
					url : "/codedetails/"+$("#codeGroupCode").val()+"/"+$("#codeValue").val(),
					contentType : "application/jason; charset=UTF-8",
					//엑세스 토큰을 요청 헤더로 서버에 전달한다
					header: {
						"Authorization":"Bearer" + ACCESS_TOKEN
					},
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

	
	//코드 등록 insert
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
				//엑세스 토큰을 요청 헤더로 서버에 전달한다
				header: {
					"Authorization":"Bearer" + ACCESS_TOKEN
				},
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
	

	//코드 삭제 Delete
	$("#codeDetailDeleteBtn").on("click", function() {
		alert("CodeDetail Deleting~!!");
		$.ajax({
			type : "DELETE",
			url : "/codedetails/" + $("#codeGroupCode").val()+"/"+$("#codeValue").val(),
			contentType : "application/json; charset=UTF-8",
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
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


	//코드 수정 update
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
	
	//코드 입력값 리셋 Reset
	$("#codeDetailResetBtn").on("click", function() {
		alert("CodeDetail Resetting~!!");
		$("#codeGroupCode").val("");
		$("#codeValue").val("");
		$("#codeName").val("");
	});
	
	//화면 초기값 로딩
	$.getJSON("/codes/codeGroup",function(list){
		$(list).each(function(){
			var str = "<option value='" + this.value + "'>" + this.label + "</option>";
			$("#codeGroupCode").append(str);
		});
	});
});