<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/apps/resources/js/header.js" ></script>
<script src="/apps/resources/js/datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="/apps/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="/apps/resources/css/header.css">
<jsp:include page="YMD.jsp"></jsp:include>
<script type="text/javascript">
 $(document).ready(function(){
	 $('#date').datepciker({
		 type: 'y'
	 });
 });
</script>
</head>
<body>
	<div id="date">
		<label>데이트피커</label>
	</div>
	<div class="block">
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