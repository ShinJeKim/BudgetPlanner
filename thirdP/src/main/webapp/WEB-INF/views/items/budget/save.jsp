<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form id="save" name="save" action="up_save.do" method="post">
		<input type="hidden" id="workDiv" name="workDiv" value="${workDiv}">
		<input type="hidden" id="daily_code" name="daily_code" value="${daily_code}">
		<input type="text" id="reg_dt" name="reg_dt" placeholder="날짜" value="${updateData.reg_dt}">
		<input type="text" id="category" name="mst_ct_nm" placeholder="상위 카테고리" value="${updateData.mst_ct_nm}">
		<input type="text" id="category" name="dtl_ct_nm" placeholder="카테고리" value="${updateData.dtl_ct_nm}">
		<input type="text" id="usage" name="usage" placeholder="금액" value="${updateData.usage}">
		<input type="text" id="content" name="content" placeholder="내용" value="${updateData.content}">
		<input type="button" id="up_save" value="저장">
		<input type="button" id="cancle" value="취소">
	</form>
	<hr/><br/>
	<h2>ui세팅중</h2>
	<div>
		날짜	<input type="text" placeholder="yyyy.mm.dd"><input type="button" value="데이트피커">
	</div>
	<div>	
		카테고리 
		<input type="radio" value="카테1" name="cate">식비
		<input type="radio" value="카테2" name="cate">기타
		<input type="radio" value="카테3" name="cate">용돈
	</div>
	<div>
		금액 <label>0000원</label>
	</div>
	<div>
		내용
		<textarea style="width: 500px; height: 80px;"></textarea>
	</div>	
</div>