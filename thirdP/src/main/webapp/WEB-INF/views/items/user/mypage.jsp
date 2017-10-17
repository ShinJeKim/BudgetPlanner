<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="identifyFrm" name="identifyFrm" action="updateUser.do" method="post">
	<div>
		<div id="titleDiv">
			<img id="title" src="/apps/resources/files/images/title.png">
		</div>
		<div id="idinput">
			<input type="text" placeholder="아이디" required autofocus
				name="id" id="id" maxlength="15" value="${loginID}" readonly="readonly"/>
		</div>
		<div id="passinput">		
			<input type="password" placeholder="비밀번호" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div id="gobutton">
			<input type="button" id="identifyBtn" value="확인" />
		</div>
	</div>
</form>