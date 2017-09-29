<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/apps/resources/js/header.js" ></script>
<script src="/apps/resources/js/datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="/apps/resources/css/reset.css">
<style>
.main_menus{
	font-size: 0px;
	text-align: center;
}
.menu{
	width: 25%;
	display: inline-block;;
	height: 60px;	
	background-color: gray;
}
.menu-label{ 
	font-size: 1rem;
	
	width: 100%;
	background-color: gray;
	display: table;
	height: 100%;
}
.menu-label>a{
	color: white;
	display: table-cell;
	vertical-align: middle;
	text-decoration: none;
}
.main_selector:checked+.menu-label>a{
	color: yellow;
	box-shadow: inset 0 0 20px black; 
}
.main_selector{
	display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#daily').attr('checked', true);
});
</script>
</head>
<body>
	<div class="main_menus">
			<ul>
				<li class="menu">
					<input class="main_selector" type="radio" name="menu" id="daily">
					<label class="menu-label" for="daily"><a href="#">가계부</a></label>
				</li>
				<li class="menu">
					<input class="main_selector" type="radio" name="menu"  id="monthly">
					<label class="menu-label" for="monthly"><a href="#">통계</a></label>
				</li>
				<li class="menu">
					<input class="main_selector" type="radio" name="menu" id="category">
					<label class="menu-label" for="category"><a href="??">내정보</a></label>
				</li>
				<li class="menu">
					<input class="main_selector" type="radio" name="menu" id="category">
					<label class="menu-label" for="category"><a href="??">logout</a></label>
				</li>
			</ul>
		</div>
</body>
</html>