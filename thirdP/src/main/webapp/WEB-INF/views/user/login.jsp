<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</script>
</head>
<body>
	<form id="loginFrm" name="loginFrm" action="do_login.do">
		<div>
			<div>
				ID <input type="text" placeholder="ID" required autofocus
					name="id" id="id" maxlength="15" />
				PASSWORD <input type="text" placeholder="PASSWORD" required autofocus
					name="password" id="password" maxlength="15"/>
			</div>
			<div>
				<input type="button" id="loginBtn" value="로그인" />
				<c:if test="${message == 'passwordFailure'}">
					<div>
						비밀번호를 확인해주세요
					</div>
				</c:if>
			</div>
			<div>
				<a href="createUser.do">회원가입</a>
			</div>
		</div>
	</form>
</body>
</html>