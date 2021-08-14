$(document).ready(function(){
	
	//member
	//전체목록조회 list
	$("#memberListBtn").on("click",function(){
		
		$.ajax({
			type: "GET",
			url: "/users",
			contentType: "application/json; charset=UTF-8",
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
			success: function(data){
				
				alert("member listing~~~success !!!")
				
				console.log(data);
				
				alert(JSON.stringify(data));
			},
			error: function(xhr, status, error){
				alert("code: "+xhr.status+"\n"
						+ "message: "+xhr.responseText+"\n"
						+ "error: "+error);
			}
			
		});
	});
	
	//상세목록조회 Read
	$("#memberReadBtn").on("click",function(){

		$.ajax({
			type: "GET",
			url: "/users/"+$("#userNo").val(),
			contentType: "application/json; charset=UTF-8",
			success: function(data){
				alert("member Read~~success !!!");
				console.log(data);
				alert(JSON.stringify(data));
				$("#memberId").val(data.userId);
				$("#memberPw").val(data.userPassword);
				$("#memberName").val(data.userName);
				$("#job").val(data.job);
				if(data.authList[0]){
					$("#memberAuth0").val(data.authList[0].auth);
				}
				
				if(data.memberAuthList[1]){
					$("#memberAuth1").val(data.authList[1].auth);
				}
				
				if(data.memberAuthList[2]){
					$("#memberAuth2").val(data.authList[2].auth);
				}
			},
			error: function(xhr, status, error){
				alert("code: "+xhr.status+"\n"
						+"message: "+xhr.reponseText+"\n"
						+"error: "+error);
			}
		});
	});
	
	// 상세내역 저장 Insert all
	$("#memberRegisterBtn").on("click", function() {
		var memberObject = {
				userId: $("#memberId").val(),
				userPw: $("#memberPw").val(),
				userName:  $("#memberName").val(),
				job: $("#job").val()
			};
		
		alert("JSON.stringify(memberObject): "+JSON.stringify(memberObject));

		$.ajax({
			type: "POST",
			url: "/users",
			data: JSON.stringify(memberObject),
			contentType: "application/json; charset=UTF-8",
			success: function(data){
					console.log(data);
					alert("memberObject after success: "+JSON.stringify(data));
					alert("Created~~success!!");
			},
			error: function(xhr, status, error){
				alert("code: "+xhr.status+"\n"
						+"message: "+xhr.reponseText+"\n"
						+"error: "+error);
			}
		});
	});
	
	
	//delete by userNo
	$("#memberDeleteBtn").on("click",function(){
		$.ajax({
			type: "DELETE",
			url: "/users/"+$("#userNo").val(),
			contentType: "application/json; charset=UTF-8",
			//엑세스 토큰을 요청 헤더로 서버에 전달한다
			header: {
				"Authorization":"Bearer" + ACCESS_TOKEN
			},
			success: function(data){
				console.log(data);
				alert("data: "+JSON.stringify(data));
				alert("Deleted~!!!")
			},
			error: function(xhr, status, error){
				alert("code: "+xhr.status+"\n"
						+"message: "+xhr.reponseText+"\n"
						+"error: "+error);
			}
			
		});
		
	});
	
	
	//update by userNo
	$("#memberModifyBtn").on("click",function(){
		var userNoVal = $("#userNo").val();
		
		var memberObject = {
				userId: $("#memberId").val(),
				userPw: $("#memberPw").val(),
				userName: $("memberName").val(),
				job: $("#job").val(),
				authList:[
					{
						userNo: userNoVal,
						auth: $("#memberAuth0").val()
					},
					{
						userNo: userNoVal,
						auth: $("#memberAuth1").val()
					},
					{
						userNo: userNoVal,
						auth: $("#memberAuth2").val()
					}
				]
		};
		
		alert("JSON.stringify(memberObject) :"+JSON.stringify(memberObject));
		
		$.ajax({
			type: "PUT",
			url: "/users/"+userNoVal,
			data: JSON.stringify(memberObject),
			contentType: "application/json; charset=UTF-8",
			success: function(data){
				alert("after success JSON.stringify(data): "+JSON.stringify(data));
				alert("member Modified~!!!");
			},
			error: function(xhr, status, error){
				alert("code: "+xhr.status+"\n"
						+"message: "+xhr.reponseText+"\n"
						+"error: "+error);
			}
		});
		
	});
	
	
	//reset
	$("#memberResetBtn").on("click",function(){
		$("#userNo").val("");
		$("#memberId").val("");
		$("#memberPw").val("");
		$("#memberName").val("");
		$("#job").val("");
		$("#memberAuth0").val("");
		$("#memberAuth1").val("");
		$("#memberAuth2").val("");
	});
	
	
	//화면 초기값 로딩 codeServiceImpl 로 부터 List 값을 가져다가 세팅함
	$.getJSON("/codes/job", function(list){
		$(list).each(function(){
			//CodeLabelValue 객체로 부터 직업코드 값과 직업명을 가져오기 위한 코드
			var str = "<option value= '"+ this.value + "'>" + this.label + "</option>";
			$("#job").append(str);
		});
			
	});
	
});
