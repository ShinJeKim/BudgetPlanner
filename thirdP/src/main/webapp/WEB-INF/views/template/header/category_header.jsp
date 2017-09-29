<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script src="/apps/resources/js/datepicker.js"></script>
<script type="text/javascript" src="/apps/resources/js/category_header.js"></script>
<link rel="stylesheet" type="text/css" href="/apps/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="/apps/resources/css/header.css">
<link rel="stylesheet" type="text/css" href="/apps/resources/css/datepicker.css">
</head>
<body>
	<div class="header">
		<div id="date">
		</div>
		<div id="balance">
			<label>잔고</label>
		</div>
		<div class="sub_menus">
			<ul>
				<li class="sub_menu">
					<input class="sub_selector" type="radio" name="menu" id="daily">
					<label class="sub_menu-label" for="daily"><a href="daily.jsp">일별</a></label>
				</li>
				<li class="sub_menu">
					<input class="sub_selector" type="radio" name="menu"  id="monthly">
					<label class="sub_menu-label" for="monthly"><a href="monthly.jsp">월별</a></label>
				</li>
				<li class="sub_menu">
					<input class="sub_selector" type="radio" name="menu" id="category">
					<label class="sub_menu-label" for="category"><a href="category.jsp">카테고리</a></label>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>