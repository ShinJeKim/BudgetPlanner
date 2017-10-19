<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>예외 발생</title>
</head>
<body>

	요청 처리 과정에서 예외가 발생하였습니다.
	<br> 빠른 시간 내에 문제를 해결하도록 하겠습니다.
	<p>
		에러 타입:
		<%=exception.getClass().getName()%>
		<br> 에러 메시지: <b><%=exception.getMessage()%></b> <img
			src="/apps/resources/files/images/error.PNG">
</body>
</html>
<!--
17.만약 에러 페이지의 길이가 513 바이트보다 작다면,
18.인터넷 익스플로러는 이 페이지가 출력하는 에러 페이지를 출력하지 않고
19.자체적으로 제공하는 'HTTP 오류 메시지' 화면을 출력할 것이다.
20.만약 에러 페이지의 길이가 513 바이트보다 작은데
21.에러 페이지의 내용이 인터넷 익스플로러에서도 올바르게 출력되길 원한다면,
22.응답 결과에 이 주석과 같은 내용을 포함시켜서
23.에러 페이지의 길이가 513 바이트 이상이 되도록 해 주어야 한다.
24.참고로 이 주석은 456바이트이다.
25.-->
