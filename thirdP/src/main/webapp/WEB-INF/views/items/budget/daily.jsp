<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = "id1";
	session.setAttribute("ID", id);
%>
<div id="dailyData">
	<div id="dailyTitle">
		<form id="search">
			<input type="hidden" id="reg_dt" name="reg_dt">
		</form>
		<div id="sum">
			<ul id = "total">
				<li id="nowDate"><label>18</label></li>
				<li id="total_in">수입&nbsp;<label>10000원</label></li>
				<li id="total_out">지출&nbsp;<label>10000원</label></li>
				<li id="total_sum">총계&nbsp;<label>10000원</label></li>
			</ul>
		</div>
	</div>
	<div id="dailyList">
	<c:choose>
            <c:when test="${list.size()>0}" >
				<c:forEach var="dailyVO" items="${list}">
					<form class="dailyDatas">	
						<div class="dailyitem">
						<ul class="itemList">
							<li class="item_cate"><label>${dailyVO.dtl_ct_nm}</label></li>
							<li class="item_content"><label></label>${dailyVO.content}</li>
							<li class="add"><label>▼</label></li>
							<li class="item_price"><label>${dailyVO.usage}</label>원</li>
						</ul>
						</div>
					</form>
				</c:forEach>
       		</c:when>
	        <c:otherwise>
	        </c:otherwise>
        </c:choose>
		<div id="blank"></div>
	</div>
	<div id="plus"><label id="icon">+</label></div>
</div>
	