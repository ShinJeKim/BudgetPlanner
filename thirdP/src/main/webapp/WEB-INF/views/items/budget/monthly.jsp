<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>
<form id="monthlyData" method="post">
	<input type="hidden" id="month" name="month" value="${month}"/>
	<input type="hidden" id="reg_dt" name="reg_dt" value="${reg_dt}" />
	<input type="hidden" id="monthlyListSize" name="monthlyListSize" value="${monthlyListSize}"/>
	<div class="calendar">
		<!-- 달력 헤더 -->
		<div class="weeks" >
			<div class="dayOfWeeks">
				<label>일</label>
			</div>
			<div class="dayOfWeeks">
				<label>월</label>
			</div>
			<div class="dayOfWeeks">
				<label>화</label>
			</div>
			<div class="dayOfWeeks">
				<label>수</label>
			</div>
			<div class="dayOfWeeks">
				<label>목</label>
			</div>
			<div class="dayOfWeeks">
				<label>금</label>
			</div>
			<div class="dayOfWeeks">
				<label>토</label>
			</div>
		</div>
		
		<!-- 달력 컨텐츠 영역 -->
		<!-- 세로 : 6, 가로 : 7 -->
		<c:forEach begin="0" end="5" step="1" varStatus="vertical">
		<div id="monthlyVertical">
			<c:forEach begin="${vertical.index*7}" end="${vertical.index*7+6}" step="1" varStatus="horizontal" var="List" items="${monthlyList}">
			<div class="dateDiv" id="area${horizontal.index}" >
				<div class="dateData">
				<!-- 일 -->
				<div class="dates" id="date${horizontal.index}">
					<label>${List.date}</label>
				</div>
				<!-- 수입 -->
				<div class="price" id="income">
					<label>${List.income}</label>
				</div>
				<!-- 지출 -->
				<div class="price" id="expense">
					<label>${List.expense.replace("-","")}</label>
				</div>
				<!-- 합계 -->
				<div class="price" id="totalUsage">
					<label>${List.totalUsage}</label>
				</div>
				</div>
			</div>
			</c:forEach>
		</div>
		</c:forEach>	
	</div>
</form>