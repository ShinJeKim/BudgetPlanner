<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form id="save" name="save" action="up_save.do" method="post">
		<div style="height: 80%; background-color:antiquewhite;">
			<input type="hidden" id="workDiv" name="workDiv" value="${workDiv}">
			<input type="hidden" id="daily_code" name="daily_code" value="${daily_code}">
			<input type="hidden" id="main_cate" name="mst_ct_nm" value="${updateData.mst_ct_nm}">
			<input type="hidden" id="sub_cate" name="dtl_ct_nm" value="${updateData.dtl_ct_nm}">
			<div style="height: 15%; border: 1px solid; box-sizing: border-box;" >
				<div class="input_title" id="tdate">
					<label>날짜</label>
				</div> 
				<div class="input_content" id="cdate">
					<input type="text" id="reg_dt" name="reg_dt" placeholder="날짜" value="${updateData.reg_dt}">
					<div id="datepick"></div>
				</div>
			</div>
			<div style="height: 20%; border: 1px solid; box-sizing: border-box;">	
				<div class="input_title" id="tcate">
					<label>카테고리</label> 
				</div> 
				<div class="input_content" id="ccate">
					<input type="radio" value="카테1" name="cate">식비
					<input type="radio" value="카테2" name="cate">기타
					<input type="radio" value="카테3" name="cate">용돈
				</div>
			</div>
			<div style="height: 15%; border: 1px solid; box-sizing: border-box;">
				<div class="input_title" id="tusage">
					<label>금액 </label>
				</div> 
				<div class="input_content" id="cusage">
					<input type="text" id="usage" name="usage" value="${updateData.usage}"><label>원</label>
				</div>
			</div>
			<div style="height: 50%; border: 1px solid; box-sizing: border-box;">
				<div class="input_title" id="tcontent">
					<label>내용</label>
				</div> 
				<div class="input_content" id="ccontent">
					<textarea style="width: 80%; height: 80%;">${updateData.content}</textarea>
				</div>
			</div>
		</div>
		<div style="height: 20%; display: table; width: 100%;">	
			<div style="display: table-cell; vertical-align: middle; text-align: center;">
				<input type="button" id="up_save" value="저장">
				<input type="button" id="cancle" value="취소">
			</div>
		</div>
	</form>
</div>