$(document).ready(function() {
	
	//전체목록조회
	$("#itemListBtn").on("click", function() {
		$.get("/items", function(data) {
			console.log(data);

			alert(JSON.stringify(data));
		});
	});

	
	//상세목록조회
	$("#itemReadBtn").on("click", function() {
		$.ajax({
			type : "GET",
			url : "/items/" + $("#itemId").val(),
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log("data: " + data);
				
				alert(JSON.stringify(data));
				
				console.log("data: " + data.itemId);
				
				$("#itemName").val(data.itemName);
				$("#itemPrice").val(data.price);
				$("#itemDescription").val(data.description);
				
				$("#preview").empty();
				
				var str = "<img src='items/display?itemId=" + data.itemId + "&timestamp=" + new Date().getTime() + "' width='210' height='240'>";
				
				$("#preview").append(str);
				
				$("#preview2").empty();
				
				var str2 = "<img src='items/preview?itemId=" + data.itemId + "&timestamp=" + new Date().getTime() + "' width='210' height='240'>";
				
				$("#preview2").append(str2);
			},
			error : function(xhr, status, error) {
				alert("code:" + xhr.status + "\n"
					+ "message:" + xhr.responseText + "\n"
					+ "error:" + error);
			}
		});
	});

	//등록
	$("#itemRegisterBtn").on("click", function() {
		console.log("register");
		
		var itemObject = {
			itemName : $("#itemName").val(),
			price : $("#itemPrice").val(),
			description : $("#itemDescription").val()
		};
		
		var file = $("input[name=picture]")[0].files[0];
		var file2 = $("input[name=picture]")[1].files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		formData.append("file2", file2);
		formData.append("item",JSON.stringify(itemObject));
		
		$.ajax({
			url: "/items",
			data: formData,
			dataType:"text",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
			},
			processData: false,
			contentType: false,
			type: "POST",
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

	//삭제
	$("#itemDeleteBtn").on("click", function() {
		$.ajax({
			type : "DELETE",
			url : "/items/" + $("#itemId").val(),
			contentType : "application/json; charset=UTF-8",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
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

	//수정
	$("#itemModifyBtn").on("click", function() {
		console.log("modify");
		
		var itemIdVal = $("#itemId").val();
		
		var itemObject = {
			itemId : itemIdVal,
			itemName : $("#itemName").val(),
			price : $("#itemPrice").val(),
			description : $("#itemDescription").val()
		};
		
		var file = $("input[name=picture]")[0].files[0];
		var file2 = $("input[name=picture]")[1].files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		formData.append("file2", file2);
		formData.append("item",JSON.stringify(itemObject));
		
		$.ajax({
			url: "/items/" + itemIdVal,
			data: formData,
			dataType:"text",
			headers : {
				"Authorization" : "Bearer " + ACCESS_TOKEN
			},
			processData: false,
			contentType: false,
			type: "PUT",
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
	
	//리셋
	$("#itemResetBtn").on("click", function() {
		$("#itemId").val("");
		$("#itemName").val("");
		$("#itemPrice").val("");
		$("#itemDescription").val("");
		
		$("#preview").empty();
		$("#preview2").empty();
	});
	
	//아이템 다운로드
	$("#itemDownloadBtn").on("click", function() {
		var req = new XMLHttpRequest();
		
		req.open("GET", "/items/download/" + $("#itemId").val(), true);
		req.responseType = "blob";
		  
		req.setRequestHeader("Authorization", "Bearer " + ACCESS_TOKEN);
		  
		req.onload = function (event) {
			var responseHeader = req.getResponseHeader("Content-Disposition");
			var type = req.getResponseHeader("Content-Type");
				
			console.log("type: " + type);
				
			alert(responseHeader);
				
			var downloadFilename = responseHeader.substring(22, responseHeader.length - 1);
			
			var decodedDownloadFilename = decodeURIComponent(escape(downloadFilename));
				
			alert(decodedDownloadFilename); 
			
		    var blob = req.response;
		    console.log(blob.size);
		    var link=document.createElement('a');
		    link.href=window.URL.createObjectURL(blob);
		    link.download = decodedDownloadFilename;
		    link.click();
		  };

		  req.send();
	});
	
});
