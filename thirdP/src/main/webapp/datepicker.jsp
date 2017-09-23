<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>

  $(function(){
	  $( "#datepicker" ).datepicker({
		  changeMonth: true,
	      changeYear: true,
	      showButtonPanel: true,
	      dateFormat: 'yyyy-mm-dd',
	      onClose: function(dateText, inst) { 
	          var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	          var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	          $(this).datepicker('setDate', new Date(year, month, 1));
	      }
	        	
	    });
	  $('#calendar').datepicker({
		    dateFormat: 'yy-m-d',
		    changeMonth: true,
		     changeYear: true,
		    inline: true,
		    onSelect: function(dateText, inst) { 
		        var date = $(this).datepicker('getDate'),
		            day  = date.getDate(),  
		            month = date.getMonth() + 1,              
		            year =  date.getFullYear();
		        alert(day + '-' + month + '-' + year);
		    }
		});
  });
  </script>
</head>
<body>
	
 <div id="calendar"></div>
 <input type="text" class="selector">
 
</body>
</html>