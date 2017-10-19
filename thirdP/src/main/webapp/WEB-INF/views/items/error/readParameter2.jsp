<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>파라미터 출력</title>
</head>
<body>

	name 파라미터 값:
	<%=request.getParameter("name").toUpperCase()%>
	<img src="/apps/resources/files/images/error.PNG">
</body>
</html>
