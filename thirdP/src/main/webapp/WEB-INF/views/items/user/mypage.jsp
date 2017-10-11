<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="identifyFrm" name="identifyFrm" action="do_identify.do" method="post">
	<div>
		<div>
			<input type="text" placeholder="아이디" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div>		
			<input type="text" placeholder="비밀번호" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div>
			<input type="button" id="identifyBtn" value="확인" />
		</div>
	</div>
</form>