<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form id="save" name="save" action="up_save.do" method="post">
		<div id="input_division">
			<input type="hidden" id="workDiv" name="workDiv" value="${workDiv}">
			<input type="hidden" id="loadWork" name="loadWork" value="reload">
			<input type="hidden" id="daily_code" name="daily_code" value="${daily_code}">
			<input type="hidden" id="main_cate" name="mst_ct_nm" value="${updateData.mst_ct_nm}">
			<input type="hidden" id="sub_cate" name="dtl_ct_nm" value="${updateData.dtl_ct_nm}">
			<input type="hidden" id="content" name="content" value="${updateData.content}">
			<div id="date_division"" >
				<div class="input_title" id="tdate">
					<label>날짜</label>
				</div> 
				<div class="input_content" id="cdate">
					<input type="text" id="reg_dt" name="reg_dt" placeholder="날짜" value="${updateData.reg_dt}${reg_dt}">
					<div id="datepick"></div>
				</div>
			</div>
			<div id="category_division">	
				<div class="input_title" id="tcate">
					<label>카테고리</label> 
				</div> 
				<div class="input_content" id="ccate">
					
				</div>
			</div>
			<div id="usage_division">
				<div class="input_title" id="tusage">
					<label>금액 </label>
				</div> 
				<div class="input_content" id="cusage">
					<input type="text" id="usage" maxlength="8" name="usage" numberonly="true" value="${updateData.usage}"><label>&nbsp;원</label>
				</div>
			</div>
			<div id="content_division">
				<div class="input_title" id="tcontent">
					<label>내용</label>
				</div> 
				<div class="input_content" id="ccontent">
					<textarea id="detail" style="width: 100%; height: 100%;"></textarea>
				</div>
			</div>
		</div>
		<div id="button_division">	
			<div style="display: table-cell; vertical-align: middle; text-align: center;">
				<input type="button" id="up_save" value="저장">
				<input type="button" id="cancle" value="취소">
			</div>
		</div>
	</form>
</div>