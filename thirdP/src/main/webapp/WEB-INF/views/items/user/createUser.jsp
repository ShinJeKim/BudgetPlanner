<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="createFrm" method="post" action="do_save.do">
	<div>
		<!-- 아이디 -->
		<div id="div_id">
			<label for="id">아이디</label>
			<div>
				<input type="text" class="onlyAlphabetAndNumber" id="id" name="id" data-rule-required="true"
					placeholder="12자 이내의 알파벳, 숫자만 입력 가능합니다." maxlength="12"/>
				<input type="button" id="checkUserId" name="checkUserId" value="중복확인"/>	
			</div>
		</div>
		<!-- 비밀번호 -->
		<div id="div_password">
			<label for="password">비밀번호</label>
			<div>
				<input type="password" id="password" name="password" data-rule-required="true"
				placeholder="비밀번호" maxlength="12">
			</div>
		</div>
		<!-- 비밀번호확인 -->
		<div id="div_password_check">
			<label for="password_check">비밀번호확인</label>
			<div>
				<input type="password" id="password_check" name="password_check" data-rule-required="true"
				placeholder="비밀번호확인" maxlength="12">
			</div>
		</div>
		<!-- 이름 -->
		<div id="div_name">
			<label for="name">이름</label>
			<div>
				<input type="text" id="name" name="name" data-rule-required="true" 
				placeholder="이름" maxlength="30">
			</div>
		</div>
		<!-- 이메일 -->
		<div id="div_email">
			<label for="email">이메일</label>
			<div>
				<input type="text" id="email" name="email" data-rule-required="true" 
				placeholder="이메일" maxlength="50">
			</div>
		</div>
		<!-- 고정수입 -->
		<div class="onlyNumber" id="div_fixed_income">
			<label for="fixed_income">고정수입</label>
			<div>
				<input type="text" id="fixed_income" name="fixed_income" data-rule-required="true" 
				placeholder="고정수입" maxlength="10">
			</div>
		</div>
		<!-- 현재잔고 -->
		<div class="onlyNumber" id="div_balance">
			<label for="balance">현재잔고</label>
			<div>
				<input type="text" id="balance" name="balance" data-rule-required="true" 
				placeholder="현재잔고" maxlength="12">
			</div>
		</div>
		<!-- 버튼 -->
		<div>
			<input type="button" id="cancle" value="취소" onclick="history.go(-1)"/>
			<input type="button" id="doSave" value="가입" />
		</div>
	</div>
</form>