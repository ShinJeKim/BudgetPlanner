	$(document).ready(function(){
		
		///////////////////////////////////////////
		//ID focusing
		///////////////////////////////////////////		
		$("#id").focus();
		
		///////////////////////////////////////////
		//LoginBtn
		///////////////////////////////////////////
		$("#loginBtn").bind("click", function(){
			
			console.log("loginBtn");
			
			//get input id
			var userID = $("#id").val().trim();
			if(userID.length==0){
				alert("ID를 입력해 주세요");
				$("#id").val("");
				$("#id").focus();
				return false;
			} //end if
			
			//get input password
			var userPassword = $("#password").val().trim();
			if(userPassword.length==0){
				alert("Password를 입력해 주세요");
				$("#password").val("");
				$("#password").focus();
				return false;
			} //end if
			
			$("#loginFrm").submit();
		});
		
	}); //End Document