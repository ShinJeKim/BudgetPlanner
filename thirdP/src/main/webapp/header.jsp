<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/apps/resources/css/reset.css">
<style type="text/css">
.block{
	width: 100%;
	font-size: 0px;
	text-align: center;
}
.select{
	width: 33.3%;
	background-color: aqua;
	display: inline-block;;
	height: 60px;	
}
.linkbox{ 
	font-size: 14px;
	width: 100%;
	background-color: yellow;
	display: table;
	height: 100%;
}
.linkbox>a{
	display: table-cell;
	vertical-align: middle;
}
.radio:checked+ .linkbox{
	background-color: aqua;
	box-shadow: inset 0 0 10px blue;
}
.radio:checked~ a{
	color: red;
}
.radio{
	display: none;
}
</style>
</head>
<body>
	<div class="block">
		<ul>
			<li class="select">
				
				<input class="radio" type="radio" name="1" id="1" checked="checked">
				<label class="linkbox" for="1"><a href="#">1</a></label>
				
			</li>
			<li class="select">
				<label class="linkbox">
				<input class="radio" type="radio" name="1">
				<a href="#">2</a>
				</label>
			</li>
			<li class="select">
				<label class="linkbox">
				<input class="radio" type="radio" name="1">
				<a href="#">3</a>
				</label>
			</li>
		</ul>
	</div>
</body>
</html>