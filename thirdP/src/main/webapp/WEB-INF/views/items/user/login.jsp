<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="loginFrm" name="loginFrm" action="do_login.do" method="post">
		<div id="logo">
			<label><img id src="/apps/resources/files/images/logo.png"></label>
		</div>
		<div id="idinput">
			<input type="text" placeholder="아이디" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div id="passinput">		
			<input type="password" placeholder="비밀번호" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div id="doLogin">
			<input type="button" id="loginBtn" value="로그인" />
			<c:if test="${message == 'passwordFailure'}">
				<div style="color: red;">
					아이디 및 비밀번호를 확인해주세요
				</div>
			</c:if>
			<c:if test="${message == 'deleteUser'}">
				<div style="color: red;">
					탈퇴한 회원입니다
				</div>
			</c:if>
		</div>
		<div id="anchorlink">
			<div>
				<a href="createUser.do">회원가입</a>&emsp;/&emsp;<a href="missing.do">ID/PW 찾기</a>
			</div>
		</div>
</form>