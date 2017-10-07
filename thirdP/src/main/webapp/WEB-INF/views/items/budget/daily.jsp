<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = "id1";
	session.setAttribute("ID", id);
%>
<div id="dailyData">
	<div style="background-color: blue; width: 98%; margin-left:1%; height: 50px;">
		<form id="frm">		
			<input type="button" value="갔다와" id="subb" >	
			<input type="hidden" value="1" >
			<input type="text" name="reg_dt" id="reg_dt" value="2017.09.22" >
			<input type="text" name="mst_ct_nm" value="지출">
			<input type="text" name="dtl_ct_nm" value="식비">
			<input type="text" name="content" value="밥먹음">
			<input type="text" name="usage" value="20000">
		</form>
		
	</div>
</div>
	