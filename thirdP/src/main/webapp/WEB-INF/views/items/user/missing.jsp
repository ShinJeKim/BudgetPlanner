<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="button" id=goBack >
<form id="missingIDFrm" name="missingIDFrm" action="do_findID.do" method="post">
	<div>
		<div class="title">
			<label>아이디찾기</label>
		</div>
		<div>
			<input type="text" placeholder="이름" required autofocus
				name="name" id="name"  maxlength="30" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id="email" maxlength="50"/>
		</div>
		<div>
			<input type="submit" id="btn_ID" value="확인" />
		</div>
		<div id="IDlabel">
			<c:if test="${message == 'idOK'}">
				<label id="IDresult" style="color: #77f799">아이디는  '${id}' 입니다</label>
			</c:if>
			<c:if test="${message == 'idNo'}">
				<label id="IDresult"  style="color: #f56360">이름과 이메일을 확인해 주세요</label>
			</c:if>
		</div>
	</div>
</form>
<form id="missingPWFrm" name="missingPWFrm" action="do_findPW.do" method="post">
	<div>
		<div class="title">
			<label>비밀번호찾기</label>
		</div>
		<div>
			<input type="text" placeholder="ID" required autofocus
				name="id" id="id" maxlength="30" />
		</div>
		<div>		
			<input type="text" placeholder="이메일" required autofocus
				name="email" id="pw_email" maxlength="50"/>
		</div>
		<div>
			<input type="submit" id="btn_PW" value="확인" />
		</div>
	<div id="PWlabel">
		<c:if test="${message == 'pwOK'}">
			<label id="PWresult" style="color: #77f799">비밀번호는  ${email} 으로 전송되었습니다.</label>
		</c:if>
		<c:if test="${message == 'pwNo'}">
			<label id="PWresult" style="color: #f56360">ID와 이메일을 확인해 주세요</label>
		</c:if>
	</div>
</form>