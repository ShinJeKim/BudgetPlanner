<%@page import="com.apps.common.StringUtil"%>
<%
	// 상위 카테고리 param 받아오기
	String mst_ct_id = StringUtil.nvl(request.getParameter("mst_ct_id"),""); 
%>
<%
	//contextPath
	String contextPath = request.getContextPath();
	contextPath = "http://localhost:8080/" + contextPath;
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>카테고리별 조회</title>

</head>
<body>
<div style="background-color: white; height: 100%; width: 100%;">
	<!-- div 1: 검색조건, 조회버튼, 엑셀다운로드 || div 2: 검색결과 list || div 3: Paging -->
		<!-- div 1 for search condition -->
		<div style="background-color: aliceblue; height: 20%;">
				<div id="layout">
					<div id="header" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 50%;">상위분류
							<select name="mst_ct_id" id="mst_ct_id">
								<option value="">전체</option>
								<option value="10" <%if(mst_ct_id.equals("10")) out.print("selected='selected'"); %>>지출</option>
								<option value="20" <%if(mst_ct_id.equals("20")) out.print("selected='selected'"); %>>수입</option>
							</select>하위분류
							<select name="dtlList" id="dtlList">
							</select>
						</div>
						<div style="float: right; width: 40%">
							<select name="start_month" id="start_month" >
							<% 
							int start_month=1;
								for(start_month=1; start_month<=12; start_month++){
							%>
								<option id="s_month" value="<%=start_month%>"><%=start_month%>월</option>
							<%
								}
							%>
							</select>
							<select name="end_month" id="end_month">
							<% 
							int end_month=1;
								for(end_month=1; end_month<=12; end_month++){
							%>
								<option id="e_month" value="<%=end_month%>"><%=end_month%>월</option>
							<%
								}
							%>
							</select>
							<input type="button" id=do_searchList  value="조회">
						</div>
					</div>
					<div id="content" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 60%"><!-- param 받아서 text에 넣기 --></div>
					</div>
					
						<div style="float: left; width: 60%">
							<input type="button" id=do_excelDown  value="엑셀 다운로드">
						</div>
					
				</div>

		</div>
		<!--// div 1 closed-->
		
		<!-- div 2 for searchList-->
		<div style="background-color: Linen; height: 75%;">
		
		<!-- List table -->
		<table id="listTable" class="table table-bordered table-hover table-striped"  border="1" cellpadding="1" cellspacing="0">
		<thead id="thead">
			<tr>
				<th>수입/지출&nbsp;</th>
				<th>카테고리&nbsp;</th>
				<th>금액&nbsp;</th>
				<th>상세설명&nbsp;</th>
				<th>날짜&nbsp;</th>
			</tr>
		</thead>
		<tbody id="tbody">
		<c:choose>
			<c:when test="${list.size()>0 }">
				<c:forEach var="CategoryVO" items="${list }">
					<form action="category.do" method="POST" name="${CategoryVO.id }">
						<tr>
							<td id="c_mst_ct_id" class="text-left"><c:out value="${CategoryVO.mst_ct_id }"></c:out></td>
							<td id="c_dtl_ct_nm" class="text-left"><c:out value="${CategoryVO.dtl_ct_nm }"></c:out></td>
							<td id="c_usage" class="text-left"><c:out value="${CategoryVO.usage }"></c:out></td>
							<td id="c_content" class="text-left"><c:out value="${CategoryVO.content }"></c:out></td>
							<td id="c_reg_dt" class="text-center"><c:out value="${CategoryVO.reg_dt }"></c:out></td>
						</tr>
					</form>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="99">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<!-- // List table closed-->
		
		</div>
		<!--// div 2 closed-->
		
		<!-- div 3 -->
		<div style="background-color: LightSteelBlue;" align="center">
		<!-- Paging -->
	 	<!-- 	<ul class="pagination" align="center">
				<li class="disabled"><a><<</a></li>
				<li class="disabled"><a><</a></li>
				<li class="disabled active"><a>1</a></li>
				<li class="goPage" data-page="2"><a>2</a></li>
				<li class="goPage" data-page="3"><a>3</a></li>
				<li class="disabled"><a>></a></li>
				<li class="goLastPage"><a>>></a></li>
			</ul>  -->
			<!-- <ul id="pagination" class="pagination_class" align="center"></ul> -->
<!--  -->
		<!-- // Paging closed  --> 
		</div>
		<!--// div 3 closed-->
</div>
</body>
</html>


