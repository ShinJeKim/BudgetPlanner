<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<title>:::: Login ::::</title>
<script type="text/javascript">
	$(document).ready(function(){
		
		///////////////////////////////////////////
		//ID focusing
		///////////////////////////////////////////		
		$("#resi_id").focus();
		
		///////////////////////////////////////////
		//LoginBtn
		///////////////////////////////////////////
		$("loginBtn").bind("click", function(e){
			
			console.log("loginBtn");
			
			//get input id
			var userID = $("id").val().trim();
			if(userID.length==0){
				alert("ID를 입력해 주세요");
				$("#id").val("");
				$("#id").focus();
				return false;
			} //end if
			
			//get input password
			var userPassword = $("password").val().trim();
			if(userPassword.length==0){
				alert("Password를 입력해 주세요");
				$("#password").val("");
				$("#password").focus();
				return false;
			} //end if
			
			$("#loginFrm").submit();
		});
		
	}); //End Document
</script>
</head>
<body>
	<form id="loginFrm" name="loginFrm" action="do_save.do">
		<input type="hidden" id="work_div" name="work_div" value="do_login" />
		<div>
			<div>
				ID <input type="text" placeholder="ID" required autofocus
					name="id" id="id" maxlength="15" />
				PASSWORD <input type="text" placeholder="ID" required autofocus
					name="password" id="password" maxlength="15"/>
			</div>
			<div>
				<input type="button" id="loginBtn" value="로그인" />
			</div>
		</div>
	</form>
</body>
</html>