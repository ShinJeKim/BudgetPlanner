<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="whole_div">
	<form name="excel_frm" action="do_excelDown" method="post">
		<input type="hidden" id="e_start_date" name="start_date" >
		<input type="hidden" id="e_end_date" name="end_date">
		<input type="hidden" id="e_mst_ct_id" name="mst_ct_id">
		<input type="hidden" id="e_dtl_ct_nm" name="dtl_ct_nm">
	</form>
	<%-- div 1: 검색조건, 조회버튼, 엑셀다운로드 || div 2: 검색결과 list || div 3: Paging --%>
		<%-- div 1 for search condition --%>
				<div id="search_div">
					<div id="cat_select" >
						<div id="main_cate">
							<label>상위분류</label>
							<select name="mst_ct_id" id="mst_ct_id">
								<option value="">전체</option>
								<option value="10">지출</option>
								<option value="20">수입</option>
							</select>
						</div>
						<div id="sub_cate">
							<label>하위분류</label>
							<select name="dtlList" id="dtlList">
							</select>
						</div>
					</div>
					<div id="month_select">
							<select name="start_month" id="start_month" >
									<option id="s_month" value="">시작</option>
								<c:forEach begin="1" end="12" step="1" varStatus="str">
									<option id="s_month" value="${str.count}">${str.count}월</option>
								</c:forEach>
							</select>
							<select name="end_month" id="end_month">
									<option id="e_month" value="">종료</option>
								<c:forEach begin="1" end="12" step="1" varStatus="str">
									<option id="e_month" value="${str.count}">${str.count}월</option>
								</c:forEach>
							</select>
							<input type="button" id=do_searchList  value="조회">
							<img id="do_excelDown" src="../resources/files/images/excel.png" alt="엑셀파일 다운로드">
					</div>
				</div>
		<%--// div 1 closed--%>
		<%-- List table --%>
				<div id="table">
					<table id="listTable" >
					<thead id="thead" class="align-center">
						<tr>
							<th class="c_no">No.</th>
							<th class="c_mst_ct_id">구분</th>
							<th class="c_dtl_ct_nm">카테고리</th>
							<th class="c_usage">금액</th>
							<th class="c_content">상세설명</th>
							<th class="c_reg_dt">날짜</th>
						</tr>
					</thead>
					<tbody id="tbody">
					<c:choose>
						<c:when test="${list.size()>0}">
							<c:forEach var="CategoryVO" items="${list}">
								<form action="category.do" method="POST" name="frm" >
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
							<tr><td></td></tr><tr><td id="result_empty">검색 결과가 없습니다</td></tr>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</div>
		<%-- // List table closed--%>
			<div id="paging_div">
				<ul id="pagination" class="pagination_class"></ul>
			</div>
</div>


