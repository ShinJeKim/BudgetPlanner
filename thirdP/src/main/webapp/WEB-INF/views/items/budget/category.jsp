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
<div id="body_div">
	<!-- div 1: 검색조건, 조회버튼, 엑셀다운로드 || div 2: 검색결과 list || div 3: Paging -->
		<!-- div 1 for search condition -->
		<div id="search_div">
				<div id="layout">
					<div id="search_header">
						<div id="cat_select" >상위분류
							<select name="mst_ct_id" id="mst_ct_id">
								<option value="">전체</option>
								<option value="10" <%if(mst_ct_id.equals("10")) out.print("selected='selected'"); %>>지출</option>
								<option value="20" <%if(mst_ct_id.equals("20")) out.print("selected='selected'"); %>>수입</option>
							</select>하위분류
							<select name="dtlList" id="dtlList">
							</select>
						</div>
						<div id="month_select">
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
					<div id="search_content">
						<div id="result_text"><!-- param 받아서 text에 넣기 --></div>
					</div>
					
						<div id="search_footer">
							<img id="do_excelDown" src="../resources/files/images/excel.png" width="40" height="40" title="엑셀파일 다운로드">
							<!-- <input type="button" id=do_excelDown  value="엑셀 다운로드"></input> -->
						</div>
				</div>
		</div>
		<!--// div 1 closed-->
		
		<!-- div 2 for searchList-->
		<div id="searchList">
		
			<!-- List table -->
		
				<table id="listTable" class="table table-bordered table-hover table-striped">
				<thead id="thead" class="align-center">
					<tr>
						<th>No.&nbsp;</th>
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
							<form action="category.do" method="POST" name="frm">
								<tr>
									<td id="c_no" class="text-center"><c:out value="${CategoryVO.No }"></c:out></td>
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
		<div id="paging_div">
			<ul id="pagination" class="pagination_class" align="center"></ul>
		<!-- // Paging closed  --> 
		</div>
		<!--// div 3 closed-->
</div>
</body>
</html>


