<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="missingIDFrm" name="missingIDFrm" action="missingID.do" method="post">
	<div>
		<label>아이디찾기</label>
		<div>
			<input type="text" placeholder="이름" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div>
			<input type="button" id="identifyBtn" value="확인" />
		</div>
		<div>
			<label id="IDresult"></label>
		</div>
	</div>
</form>
<form id="missingPWFrm" name="missingPWFrm" action="missingPW.do" method="post">
	<div>
		<label>비밀번호찾기</label>
		<div>
			<input type="text" placeholder="ID" required autofocus
				name="id" id="id" maxlength="15" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="password" id="password" maxlength="15"/>
		</div>
		<div>
			<input type="button" id="identifyBtn" value="확인" />
		</div>
	</div>
	<div>
		<label id="PWresult"></label>
	</div>
</form>