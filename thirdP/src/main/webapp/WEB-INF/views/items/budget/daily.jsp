<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<label>시작</label>
	<c:forEach begin="0" end="20" step="1">
		<div style="background-color: blue; height: 90px; width: 100%;"></div>
	</c:forEach>
	<label>끝</label>
	