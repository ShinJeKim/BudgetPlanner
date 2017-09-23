<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/apps/resources/js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
		
	
	$(".years").click(function(){
		var year =  $(this).val();
		$("#year").val(year);
		$("#yearly").val(year);
	});

		var status = "<input id='status' type='text'>";
		$("#dpicker").append(status);	
		
		var calm = "";
			calm += "<table style='border: 2px solid;'>                                                                                                     ";
			calm += "		<thead style='text-align: center;'>                                                                                             ";
			calm += "			<tr>                                                                                                                        ";
			calm += "				<th colspan='4'>                                                                                                        ";
			calm += "				<input id=cprefyear type='button' value='<'><input id='cyearly' type='button' value='2017'><input type='button' value='>'>";
			calm += "				</th>                                                                                                                   ";
			calm += "			</tr>                                                                                                                       ";
			calm += "		</thead>                                                                                                                        ";
			calm += "		<tbody>                                                                                                                         ";
			calm += "			<tr>                                                                                                                        ";
			calm += "				<td><input class='cmonth' type='button' value='1월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='2월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='3월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='4월'><td>                                                                 ";
			calm += "			</tr>                                                                                                                       ";
			calm += "			<tr>                                                                                                                        ";
			calm += "				<td><input class='cmonth' type='button' value='4월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='5월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='6월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='7월'><td>                                                                 ";
			calm += "			</tr>                                                                                                                       ";
			calm += "			<tr>                                                                                                                        ";
			calm += "				<td><input class='cmonth' type='button' value='8월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='9월'><td>                                                                 ";
			calm += "				<td><input class='cmonth' type='button' value='10월'><td>                                                                ";
			calm += "				<td><input class='cmonth' type='button' value='12월'><td>                                                                ";
			calm += "			</tr>	                                                                                                                    ";
			calm += "		</tbody>                                                                                                                        ";
			calm += "	</table>                                                                                                                            ";
		
		
		
		
		
		var cal = "";
					cal += "<table id='yearpicker' style='border: 2px solid;'>                                                                                  "  ;
					cal += "		<thead style='text-align: center;'>                                                                        "   ;
					cal += "		<tr>                                                                                                       "   ;
					cal += "			<th colspan='4'>                                                                                       "   ;
					cal += "			<input type='button' value='<'><input type='button' value='2009 ~ 2017'><input type='button' value='>'>"   ;
					cal += "			</th>                                                                                                  "   ;
					cal += "		</tr>                                                                                                      "   ;
					cal += "	</thead>                                                                                                       "   ;
					cal += "	<tbody>                                                                                                        "   ;
					cal += "		<tr>                                                                                                       "   ;
					cal += "			<td><input class='cyears' type='button' value='2009'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2010'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2011'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2012'><td>                                               "   ;
					cal += "		</tr>                                                                                                      "   ;
					cal += "		<tr>                                                                                                       "   ;
					cal += "			<td><input class='cyears' type='button' value='2013'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2014'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2015'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2016'><td>                                               "   ;
					cal += "		</tr>                                                                                                      "   ;
					cal += "		<tr>                                                                                                       "   ;
					cal += "			<td><input class='cyears' type='button' value='2007'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2018'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2019'><td>                                               "   ;
					cal += "			<td><input class='cyears' type='button' value='2020'><td>                                               "   ;
					cal += "		</tr>	                                                                                                   "   ;
					cal += "	</tbody>                                                                                                       "   ;
					cal += "</table>                                                                                                        "      ;
		$("#picker").append(cal);			
	
		
		$(".cyears").click(function(){
			$("#status").val($(this).val());
			$("#yearpicker").remove();
			$("#picker").append(calm);
			$("#cyearly").val($("#status").val());
			
			$(".cmonth").click(function(){
				var month =  $(this).val().replace("월","");
				console.log(month)
				var year = $("#cyearly").val();
				$("#status").val(year+"."+month);
			});
			
			$("#cprefyear").click(function(){
				var prefyear = parseInt($("#cyearly").val());
				$("#cyearly").val(prefyear-1);
			});
		});

		
	});
</script>
</head>
<body>
	<div>
		<table style="border: 2px solid;">
			<thead style="text-align: center;">
				<tr>
					<th colspan="4">
					<input type="button" value="<"><input type="button" value="2009 ~ 2017"><input type="button" value=">">
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input class="years" type="button" value="2009"><td>
					<td><input class="years" type="button" value="2010"><td>
					<td><input class="years" type="button" value="2011"><td>
					<td><input class="years" type="button" value="2012"><td>
				</tr>
				<tr>
					<td><input class="years" type="button" value="2013"><td>
					<td><input class="years" type="button" value="2014"><td>
					<td><input class="years" type="button" value="2015"><td>
					<td><input class="years" type="button" value="2016"><td>
				</tr>
				<tr>
					<td><input class="years" type="button" value="2007"><td>
					<td><input class="years" type="button" value="2018"><td>
					<td><input class="years" type="button" value="2019"><td>
					<td><input class="years" type="button" value="2020"><td>
				</tr>	
			</tbody>
		</table>
		<input type="text" id="year" value="1111">
	</div>
	
	<div>
		<table style="border: 2px solid;">
			<thead style="text-align: center;">
				<tr>
					<th colspan="4">
					<input id=prefyear type="button" value="<"><input id="yearly" type="button" value="2017"><input type="button" value=">">
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input class="month" type="button" value="1월"><td>
					<td><input class="month" type="button" value="2월"><td>
					<td><input class="month" type="button" value="3월"><td>
					<td><input class="month" type="button" value="4월"><td>
				</tr>
				<tr>
					<td><input class="month" type="button" value="4월"><td>
					<td><input class="month" type="button" value="5월"><td>
					<td><input class="month" type="button" value="6월"><td>
					<td><input class="month" type="button" value="7월"><td>
				</tr>
				<tr>
					<td><input class="month" type="button" value="8월"><td>
					<td><input class="month" type="button" value="9월"><td>
					<td><input class="month" type="button" value="10월"><td>
					<td><input class="month" type="button" value="12월"><td>
				</tr>	
			</tbody>
		</table>
		<input type="text" id="month">
	</div>
	<div id="dpicker">
	<div id="picker">
	</div>
	</div>
</body>
</html>