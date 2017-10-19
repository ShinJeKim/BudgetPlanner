<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>에러 발생</title>
<style type="text/css">
#errorDiv{
	width:80%;
	height:80%;
	position: absolute;
	top: 10%;
	left: 10%;
	background-image: url("/apps/resources/files/images/error.PNG");
	background-repeat: round;
}
#errorDiv>input{
    position: relative;
    top: 70%;
    left: 44%;
    width: 10%;
    outline: 0px;
    border: 2px solid lightgray;
    border-radius: 0px 5px 0px 5px;
    background-color: white;
    font-size: 12px;
    line-height: 150%;
    font-weight: bold;
    color: #c17660;
    font-family: monospace;
    text-align: center;
}
#errorDiv>input:hover{
	cursor: pointer;
}    
</style>
<script type="text/javascript" src="/apps/resources/js/common/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#goMain').click(function(){
		location.replace('/apps/main.do');
	});
	$('#goBack').click(function(){
		window.history.go(-1);
	});
});	
</script>
</head>
<body>
	<div id="errorDiv">
		<input type="button" id="goMain" value=">초기화면">
		<input type="button" id="goBack" value=">돌아가기">
	</div>
</body>
</html>
