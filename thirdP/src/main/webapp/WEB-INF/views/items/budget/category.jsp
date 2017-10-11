<%@page import="com.apps.common.StringUtil"%>
<%
/*
<받아와야될 인자 List>
1. page_num
2. page_size
3. mst_ct_id
4. dtl_ct_id
5. start_date
6. end_date
7. id
*/
 	//상수 paging bottom count
	int bottomCount = 10;

	//for default element
	String page_num = "1";
	String page_size = "10";
	String totalCnt = "";
	String start_date = "2017-07-01";
	String end_date = "2017-09-28";
	String id = "id1";
	String mst_ct_id = "";
	String dtl_ct_id = "";

	//initializing default element
	page_num = StringUtil.nvl(request.getParameter("page_num"), "1");
	page_size = StringUtil.nvl(request.getParameter("page_size"), "10");
	//totalCnt = StringUtil.nvl(request.getAttribute("totalCnt").toString(), "");
	
	// 카테고리 param 받아오기
	mst_ct_id = StringUtil.nvl(request.getParameter("mst_ct_id"),""); 
	//String dtlList = StringUtil.nvl(request.getParameter("dtlList"), "");
	
	
%>
<%
	//contextPath
	String contextPath = request.getContextPath();
	contextPath = "http://localhost:8080/" + contextPath;
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>카테고리별 조회</title>

<script>

	$(document).ready(function(){
		
		 $('#date').datepicker({
			 type	: 'y',
			 lang	: 'ko'
		 });
		$('#category').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		$('#currentDate').change(function(){
			var thisDate = $('#currentDate').val().toString().split(".");
			var selectedDate = thisDate[0];
			console.log(selectedDate);
		});
		
		console.log("ready: ");
		
			$('#mst_ct_id').change(function(){
				var mst_ct_id = $('#mst_ct_id').val();
				console.log("1. mst_ct_id: "+mst_ct_id);
				$.ajax({
					type: "GET",
					url: "do_searchCategory.do",
					dataType: "JSON",
					data:{
						"mst_ct_id" : $('#mst_ct_id').val()
					},
					success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
						console.log("2. mst_ct_id: "+mst_ct_id);
						console.log("data: "+data);
						do_searchCategory(mst_ct_id);
	
						var dtlList = "${dtlList}";
						console.log("dtlList: "+"${dtlList}");
						var htmlval ="";
						for(var i=0;i<data.length;i++){
							console.log(data[i]);
							htmlval += "<option value="+i+">"+data[i]+"</option>"
						}
						$('#dtlList').html(htmlval);
					},
					complete: function(data){// 무조건 수행
						console.log($('#mst_ct_id').val());
					},
					error: function(xhr, status, error){
						console.log("error: "+error);
					}
				});// --ajax closed
			});
		

 		// do_searchCategory
		function do_searchCategory(){
			console.log("3. mst_ct_id: "+mst_ct_id.value);
			if(mst_ct_id != null){
				var frm = document.frm;
			}else{
				return;
			}
		} 

	});//-- jQuery closed
</script>

</head>
<body>
<div style="background-color: white; height: 100%; width: 100%;">
	<!-- div 1: 검색조건, 조회버튼, 엑셀다운로드 || div 2: 검색결과 list || div 3: Paging -->
		<!-- div 1 for search condition -->
		<div style="background-color: aliceblue; height: 20%;">
				<div id="layout">
					<div id="header" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 50%;">상위분류
							<select name="mst_ct_id" id="mst_ct_id">
								<option value="">전체</option>
								<option value="10" <%if(mst_ct_id.equals("10")) out.print("selected='selected'"); %>>지출</option>
								<option value="20" <%if(mst_ct_id.equals("20")) out.print("selected='selected'"); %>>수입</option>
							</select>하위분류
							<select name="dtlList" id="dtlList">
			 				<%-- <% 
								String dtlListSplit[] = dtlList.split(", ");
								int i = 0;				
								for(i=0; i<dtlListSplit.length;i++){		
								%>
								<option value="<%=i%>"><%dtlListSplit[i].toString();%></option>
								<%
								} 
								%> --%>
							</select>
						</div>
						<div style="float: right; width: 40%">
							<select name="start_date">
								<option value="">start_date</option>
							</select>
							<select name="end_date">
								<option value="">end_date</option>
							</select>
						</div>
					</div>
					<div id="content" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 60%">param 받아서 text에 넣기</div>
						<div style="float: right; width: 30%">
							<button id=do_searchList onclick="javascript:do_searchList();">조회</button>
						</div>
					</div>
					<div id="footer" style="padding: 10px; margin: 10px;">
						<div style="float: left; width: 60%">엑셀 다운로드</div>
					</div>
				</div>

		</div>
		<!--// div 1 closed-->
		
		<!-- div 2 for searchList-->
		<div style="background-color: Linen; height: 75%;">
		
		</div>
		<!--// div 2 closed-->
		
		<!-- div 3 -->
		<div style="background-color: LightSteelBlue; height: 5%;">
			for PAGING
		</div>
		<!--// div 3 closed-->
</div>
</body>
</html>


