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
	$('.weeks>.dayOfWeeks').css('font-size',font_size*3);
	$('#monthlyVertical>.dateDiv>.dateData>.dates').css('font-size',font_size*2.5);
	$('#monthlyVertical>.dateDiv>.dateData>.price').css('font-size',font_size*1.5);
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
		for(var i=0; i<$("#monthlyListSize").val(); i++){
			(function(a){
				$("#area"+a).click(function(){

					var reg_dt = "";

					if($("#date"+a).text().trim().length == 1){
						reg_dt = $(".currentDate").html().toString() + "." + "0" + $("#date"+a).text().trim();

					} else if ($("#date"+a).text().trim().length == 2){
						reg_dt = $(".currentDate").html().toString() + "." + $("#date"+a).text().trim();

					} else if ($("#data"+a).text().trim() == ''){
						return false;
					}
					
					//일별 화면으로 이동
					$(location).attr('href', "daily.do?reg_dt="+reg_dt.trim());

				});
			})(i);
		}
		$(document).find('#totalUsage>label').each(function(){
			var totalUsage = $(this).html().toString()
			if(totalUsage.substring(1,0) == '-'){
				$(this).html(totalUsage.replace('-',''));
				$(this).css('border-top','1px solid red');
			}else if(totalUsage.substring(1,0) == '0'){
				$(this).html(totalUsage.replace('-',''));
				$(this).css('border-top','1px solid gray');
			}else if(totalUsage.substring(1,0) == ''){
				$(this).html(totalUsage);
				$(this).css('border-top','none');
			}else{
				$(this).html(totalUsage);
				$(this).css('border-top','1px solid blue');
			}
		});
		$(document).find('.dateData>div>label').each(function(){
			$(this).html(numberFormat($(this).html()));
		})
	});