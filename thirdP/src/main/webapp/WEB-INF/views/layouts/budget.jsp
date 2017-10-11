<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="script" />
<tiles:insertAttribute name="style" />
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$('#up_save').click(function(){
			$('#save').submit();	
		});
		$('#cancle').click(function(){
			history.go(-1);
		});	
	});
</script>
<body>
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="bodyCover">
		<div class="body">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>