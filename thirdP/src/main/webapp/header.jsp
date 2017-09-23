<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/apps/resources/js/header.js" ></script>
<link rel="stylesheet" type="text/css" href="/apps/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="/apps/resources/css/header.css">
</head>
<body>
	<div class="block">
		<input type="hidden" class="url" value="">
		<ul>
			<li class="menu">
				<input class="selector" type="radio" name="menu" id="daily">
				<label class="menu-label" for="daily"><a href="/apps/header.jsp">일별</a></label>
				
			</li>
			<li class="menu">
				<input class="selector" type="radio" name="menu"  id="monthly">
				<label class="menu-label" for="monthly"><a href="monthly.jsp">월별</a></label>
			</li>
			<li class="menu">
				<input class="selector" type="radio" name="menu" id="category">
				<label class="menu-label" for="category"><a href="??">카테고리</a></label>
			</li>
		</ul>
	</div>
</body>
</html>