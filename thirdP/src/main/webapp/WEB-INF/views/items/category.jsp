<%@page import="com.apps.common.StringUtil"%>
<%
	//상수 paging bottom count
	int bottomCount = 10;

	//for default element
	String page_num = "1";
	String page_size = "10";
	String search_word = "";
	String search_div = "";
	int totalCnt = 0;
	
	//initializing default element
	page_num = StringUtil.nvl(request.getParameter("page_num"), "1");
	page_size = StringUtil.nvl(request.getParameter("page_size"), "10");
	search_word = StringUtil.nvl(request.getParameter("search_word"), "");
	search_div = StringUtil.nvl(request.getParameter("search_div"), "");
	
	//parseInt for paging
	int oPage_size = Integer.parseInt(page_size);
	int oPage_num = Integer.parseInt(page_num);
			
	totalCnt = Integer.parseInt(StringUtil.nvl(request.getAttribute("totalCnt").toString(), "0"));
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
<!-- 부트스트랩 -->
<link href="<%=contextPath%>/resources/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=contextPath%>/resources/css/bootstrap-theme.min.css"
	rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jquery-3.2.1.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="<%=contextPath%>/resources/js/bootstrap.min.js"></script>

<title>카테고리별 조회</title>

<!-- Head Div -->
<div>
</div>
<!--// Head Div closed-->
</head>
<body>
	<!-- Body Div -->
	<div>
		<input data-provide="datepicker">
		<div class="input-group date" data-provide="datepicker">
			<input type="text" class="form-control">
			<div class="input-group-addon">
				<span class="glyphicon glyphicon-th"></span>
			</div>
		</div>
	</div>
	<!--// Body Div closed-->
</body>
<!-- Footer Div -->
<div>
</div>
<!--// Footer Div closed-->
</html>


















