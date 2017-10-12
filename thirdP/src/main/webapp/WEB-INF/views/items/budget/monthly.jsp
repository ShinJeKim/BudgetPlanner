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
	
	var selectedMonth = "";
	
	$(document).ready(function(){
		 $('#date').datepicker({
			 type	: 'ym',
			 lang	: 'ko'
		 });
		$('#monthly').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		$('#currentDate').change(function(){
			
			var thisDate = $('#currentDate').val().toString().split(".");
			selectedMonth = thisDate[0] + thisDate[1];
			console.log(selectedMonth);
			
			$.ajax({
				type:"POST",
				url:"get_monthly_usage.do",
				dataType:"HTML", //option default : html
				async: false,
				data: {
					"month" : selectedMonth
				},
				success: function(data){	//통신이 성공적으로 이루어 졌을 때 받을 함수
					console.log(data);
					$("#month").val(selectedMonth);
					$("#monthlyData").submit();
				},
				complete: function(data){	//실패 성공 상관없이 무조건 수행
					
				}, 
				error: function(xhr, status, error){
					
				}
			}); //ajax
			
		});
		
	});
</script>
</head>
<body>
	<form id="monthlyData" method="post">
		<input type="hidden" id="month" name="month" value="${month}"/>
		<div style="height:100%; width:100%; ">
			<!-- 달력 헤더 -->
			<div style="height:5%; width:100%; ">
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">일
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">월
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">화
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">수
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">목
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">금
				</div>
				<div align="center" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">토
				</div>
			</div>
			
			<!-- 달력 컨텐츠 영역 -->
			<!-- 세로 : 6, 가로 : 7 -->
			<c:forEach begin="0" end="5" step="1" varStatus="vertical">
			<div id="monthlyVertical" style="height:15.5%; width:100%; ">
				<c:forEach begin="${vertical.index*7}" end="${vertical.index*7+6}" step="1" varStatus="horizontal" var="List" items="${monthlyList}">
				<div id="monthlyHorizontal" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">
					<!-- 일 -->
					<div style="height:100%; width:48.5%; float:left">
						${List.date}
					</div>
					<div style="height:100%; width:48.5%; float:left">
						<!-- 수입 -->
						<div style="box-sizing:border-box; height:32%; width:100%; border:1px solid gold">
							${List.income}
						</div>
						<!-- 지출 -->
						<div style="box-sizing:border-box; height:32%; width:100%; border:1px solid gold">
							${List.expense}
						</div>
						<!-- 합계 -->
						<div style="box-sizing:border-box; height:32%; width:100%; border:1px solid gold">
							${List.totalUsage}
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			</c:forEach>	
		</div>
	</form>
</body>
</html>