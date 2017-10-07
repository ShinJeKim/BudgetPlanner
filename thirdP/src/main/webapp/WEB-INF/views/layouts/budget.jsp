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
	
	$('#dailyData').css('width',$('.bodyCover').width());
	$(window).resize(function(){
		$('#dailyData').css('width',$('.bodyCover').width());
	});
	//$('#txt').css("font-size",'4rem');
	//$('#txt').val("<p>이거 값나오는건가?<br>설마하니 그냥나오나?</p>");
	//$('#bt').click(function(){
	//var txt = $('#txt').val()
	//var ltxt = txt.replace(/\n/g,"<br>")
	//console.log(ltxt);
	//var oritxt = ltxt.replace(/<br>/,"\n").replace("<p>","").replace("</p>","");
	//$('#sub').html(ltxt);
	//$('#txt').val(oritxt);
	//});
	
	$('#subb').click(function(){
		$('#frm').attr("action","do_save.do");
		$('#frm').attr("method","post");
		$('#frm').submit();
	});
	$('#currentDate').change(function(){
		$('#reg_dt').val($('#currentDate').val()).trigger('change');
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