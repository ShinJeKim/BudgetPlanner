<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="loginFrm" name="loginFrm" action="do_login.do" method="post">
	<div>
		<div id="logo">
			<label>LOGO</label>
		</div>
		<div>
			<input type="text" placeholder="아이디" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div>		
			<input type="password" placeholder="비밀번호" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div>
			<input type="button" id="loginBtn" value="로그인" />
			<c:if test="${message == 'passwordFailure'}">
				<div style="color: red;">
					아이디 및 비밀번호를 확인해주세요
				</div>
			</c:if>
		</div>
		<div>
			<a href="createUser.do">회원가입</a>
			/ <a href="missing.do">ID/PW 찾기</a>
		</div>
	</div>
</form>