<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
function font_sizing(){
	var font_size = "";
	if(window.innerWidth > window.innerHeight){
		font_size = window.innerHeight*0.01;
	}else{
		font_size = window.innerWidth*0.01
	}
	$('.currentDate').css("font-size",font_size*6);
	$('#selectedDate').css('height',$('.header').height()*0.6);
	$('#balance').css('height',$('.header').height()*0.6);
}	
	$(document).ready(function(){
		 $('#date').datepicker({
			 type	: 'ym',
			 lang	: 'ko'
		 });
		$('#monthly').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		font_sizing();
		$('#currentDate').change(function(){
			
			//날짜 만들기
			var thisDate = $('#currentDate').val().toString().split(".");
			var selectedMonth = thisDate[0] + thisDate[1];
			console.log(selectedMonth);
			
			//날짜 변경시 화면 갱신해주기
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
		
		//div 영역 클릭 event 감지
		for(var i=0; i<${fn:length(monthlyList)}; i++){
			(function(a){
				$("#area"+a).click(function(){
					console.log("date.text : "+$("#date"+a).text().trim());
					var reg_dt = "";
					console.log("month" + $(".currentDate").html().toString());
					if($("#date"+a).text().trim().length == 1){
						reg_dt = $(".currentDate").html().toString() + "." + "0" + $("#date"+a).text().trim();
						console.log("selectedDate : "+reg_dt);
					} else if ($("#date"+a).text().trim().length == 2){
						reg_dt = $(".currentDate").html().toString() + "." + $("#date"+a).text().trim();
						console.log("selectedDate : "+reg_dt);
					}
					
					//일별 화면으로 이동
					$(location).attr('href', "daily.do?reg_dt="+reg_dt.trim());

				});
			})(i);
		}
		
	});
</script>
</head>
<body>
	<form id="monthlyData" method="post">
		<input type="hidden" id="month" name="month" value="${month}"/>
		<input type="hidden" id="reg_dt" name="reg_dt" value="${reg_dt}" />
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
				<div id="area${horizontal.index}" style="box-sizing:border-box; height:100%; width:14.28%; border:1px solid black; float:left">
					<!-- 일 -->
					<div id="date${horizontal.index}" style="height:100%; width:48.5%; float:left">
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