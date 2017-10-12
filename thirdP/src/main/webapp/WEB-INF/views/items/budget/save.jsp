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
</div>