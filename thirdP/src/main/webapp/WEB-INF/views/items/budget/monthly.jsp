<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	//contextPath
	String contextPath = request.getContextPath();
    contextPath ="http://localhost:8080/"+contextPath;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::: Monthly Usage :::</title>
<!-- jQuery -->
<script type="text/javascript">
	$(document).ready(function(){
		 $('#date').datepicker({
			 type	: 'ym',
			 lang	: 'ko'
		 });
		$('#monthly').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		$('#currentDate').change(function(){
			var thisDate = $('#currentDate').val().toString().split(".");
			var selectedMonth = thisDate[0] + thisDate[1];
			console.log(selectedMonth);
		});
	});
</script>
</head>
<body>
	<div style="height:100%; width:100%;">
		<!-- 달력 헤더 -->
		<div style="height:5%; width:100%; ">
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">일
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">월
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">화
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">수
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">목
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">금
			</div>
			<div align="center" style="height:100%; width:14.1%; border:1px solid black; float:left">토
			</div>
		</div>
		
			<!-- 달력 컨텐츠 영역 -->
			<div style="height:18.5%; width:100%; ">
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 일 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						1
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<!-- 수입 -->
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<!-- 지출 -->
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<!-- 합계 -->
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 월 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						2
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 화 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						3
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 수 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						4
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 목 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						5
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 금 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						6
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
				<div style="height:100%; width:14.1%; border:1px solid black; float:left">
					<!-- 토 -->
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						7
					</div>
					<div style="height:100%; width:48.5%; border:1px solid green; float:left">
						<div style="height:32%; width:100%; border:1px solid gold">1
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">2
						</div>
						<div style="height:32%; width:100%; border:1px solid gold">3
						</div>
					</div>
				</div>
			</div>
	
	</div>
</body>
</html>