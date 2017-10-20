//do_searchCategory
function do_searchCategory(){
	if(mst_ct_id != null){
		var frm = document.frm;
	}else{
		return;
	}
} 

// 엑셀 다운로드
function do_excelDown(){
	var frm = document.excel_frm;
	frm.action = "do_excelDown.do";
	frm.submit();
}


	//jQuery Start
	$(document).ready(function(){
		
		var clickFlag = 0;
		
		$('#whole_div').css('width',$('.bodyCover').width()*0.98);
		$('#whole_div').css('margin-left',$('.bodyCover').width()*0.01);
		$('#whole_div').css('height',$('.bodyCover').height());
		$('#search_div').css('height',$('#whole_div')*0.1);
		$('#table').css('height',$('#whole_div')*0.9);
		$('#paging_div').css('height',$('#whole_div')*0.1);
	$(window).resize(function(){
		$('#whole_div').css('width',$('.bodyCover').width()*0.98);
		$('#whole_div').css('margin-left',$('.bodyCover').width()*0.01);
		$('#whole_div').css('height',$('.bodyCover').height());
		$('#search_div').css('height',$('#whole_div')*0.1);
		$('#table').css('height',$('#whole_div')*0.9);
		$('#paging_div').css('height',$('#whole_div')*0.1);
	});
		// datePicker
		 $('#date').datepicker({
			 type	: 'y',
			 lang	: 'ko'
		 });
		$('#category').attr('checked', true);
		$('#BudgetPlanner').attr('checked', true);
		$('#selectedDate').css('height',$('.header').height()*0.6);
		$('#balance').css('height',$('.header').height()*0.6);
		$('#currentDate').change(function(){
			var thisDate = $('#currentDate').val().toString().split(".");
			var selectedDate = thisDate[0];
		});
		
			// change_category
			$('#mst_ct_id').change(function(){
				var mst_ct_id = $('#mst_ct_id').val();

				$.ajax({
					type: "GET",
					url: "do_searchCategory.do",
					dataType: "JSON",
					data:{
						"mst_ct_id" : $('#mst_ct_id').val()
					},
					success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수

						do_searchCategory(mst_ct_id);
	
						var dtlList = "${dtlList}";

						var htmlval ="";
						
						for(var i=0;i<data.length;i++){

							htmlval += "<option value="+data[i]+">"+data[i]+"</option>"
						}
						$('#dtlList').html(htmlval);
						
						$('#e_dtl_ct_nm').val('전체');

					},
					complete: function(data){// 무조건 수행

					},
					error: function(xhr, status, error){

					}
				});// --ajax closed
			});// -- change category closed
			
			
			// do_searchList
			$("#do_searchList").on("click", function(){
				if($('#start_month').val() == '' | $('#end_month').val() == ''){
					if($('#start_month').val() == ''){
						alert("시작월을 선택해주세요");
					}
					if($('#end_month').val() == ''){
						alert("종료월을 선택해주세요");
					}
					
					return;
				}else{
					if(parseInt($('#start_month').val()) > parseInt($('#end_month').val())){
						alert("시작월은 종료월보다 이전이어야 합니다.");
						return;
					}
					var st_date;
					var ed_date;
					
					// month 쿼리를 위한 0 붙이기 조건
					if((parseInt($('#start_month').val())<10)){
						st_date = $('.currentDate').html().toString()+"-0"+$('#start_month').val()+"-01";
					}else{
						st_date = $('.currentDate').html().toString()+"-"+$('#start_month').val()+"-01";
					}
	
					if((parseInt($('#end_month').val())<9)){
						ed_date = $('.currentDate').html().toString()+"-0"+(parseInt($('#end_month').val())+1)+"-01";
					}else if(parseInt($('#end_month').val()) >= 9 && parseInt($('#end_month').val()) < 12){
						ed_date = $('.currentDate').html().toString()+"-"+(parseInt($('#end_month').val())+1)+"-01";
					}else if(parseInt($('#end_month').val()) == 12){
						ed_date = (parseInt($('.currentDate').html().toString())+1)+"-01-01";
					}
					
	
					$.ajax({
						type:"POST",
						url:"do_searchList.do",
						dataType:"JSON",
						data:{			
							"start_date": st_date.trim(),
							"end_date": ed_date.trim(),
							"mst_ct_id" : $('#mst_ct_id').val(),
							"dtl_ct_nm": $('#dtlList').val(),
							"page_size": "10",
							"page_num": "1"
						},
						success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수

							// 기존페이지네이션 있을시 없애고 재생성
							if($('.pagination').data("twbs-pagination")){
			                    $('.pagination').twbsPagination('destroy');
			                }
								
	
							if(data.length > 0){
								
								var totalCnt = data[0].totalNo;
								var page_size = 10;
								var max_page = Math.ceil(totalCnt/page_size);	
								
								var datahtml = "";
								
								datahtml += ""
								
								clickFlag = 1;	
									
								for(var i=0; i<data.length; i++){
									var content = data[i].content.replace(/(<([^>]+)>)/gi,'');
									
									datahtml += "<tr class='dataList'>"
									datahtml += "<td id='c_no' >"+data[i].No+"</td>"
									datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
									datahtml += "<td id='c_dtl_ct_nm' >"+data[i].dtl_ct_nm+"</td>"
									datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
									datahtml += "<td id='c_content' >"+content+"</td>"
									datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
									datahtml += "</tr >"

								}
								
									$('#tbody').html(datahtml);
	
									 $(document).find('.dataList').each(function(){
							   				if($(this).children('#c_usage').text().substring(1,0) == "-"){
							   					$(this).children('#c_usage').text($(this).children('#c_usage').text().replace('-',''));
							   					$(this).children('#c_usage').css('color','red');
							   				
							   					$(this).children('#c_usage').text(numberFormat($(this).children('#c_usage').text()));
							   				}else{
							   					$(this).children('#c_usage').css('color','blue');
							   					
							   					$(this).children('#c_usage').text(numberFormat($(this).children('#c_usage').text()));
							   				}		
							   			});
										
									$('#pagination').twbsPagination({
											        totalPages: max_page,
											        visiblePages: 5,
											        onPageClick: function(evt, page){
											        	$.ajax({
															type:"POST",
															url:"do_searchList.do",
															dataType:"JSON",
															data:{			
																"start_date": st_date.trim(),
																"end_date": ed_date.trim(),
																"mst_ct_id" : $('#mst_ct_id').val(),
																"dtl_ct_nm": $('#dtlList').val(),
																"page_size": "10",
																"page_num": page
															},
															success: function(data){// 통신이 성공적으로 이루어졌을때 받을 함수
																
																var totalCnt = data[0].totalNo;
																var page_size = 10;
																var max_page = Math.ceil(totalCnt/page_size);
															
	
																if(data.length > 0){
																	
																	var datahtml = "";
																	
																	datahtml += ""
																	
																	for(var i=0; i<data.length; i++){
																		
																		var content = data[i].content.replace(/(<([^>]+)>)/gi,'');
																		datahtml += "<tr class='dataList'>"
																		datahtml += "<td id='c_no' >"+data[i].No+"</td>"
																		datahtml += "<td id='c_mst_ct_id' >"+data[i].mst_ct_nm+"</td>"
																		datahtml += "<td id='c_dtl_ct_nm' >"+data[i].dtl_ct_nm+"</td>"
																		datahtml += "<td id='c_usage' >"+data[i].usage+"</td>"
																		datahtml += "<td id='c_content' >"+content+"</td>"
																		datahtml += "<td id='c_reg_dt' >"+data[i].reg_dt+"</td>"
																		datahtml += "</tr >"
																		
																		
																	}
																	
																		$('#tbody').html(datahtml);
	
																		$(document).find('.dataList').each(function(){
															   				if($(this).children('#c_usage').text().substring(1,0) == "-"){
															   					$(this).children('#c_usage').text($(this).children('#c_usage').text().replace('-',''));
															   					$(this).children('#c_usage').css('color','red');
															   				
															   					$(this).children('#c_usage').text(numberFormat($(this).children('#c_usage').text()));
															   				}else{
															   					$(this).children('#c_usage').css('color','blue');
															   					
															   					$(this).children('#c_usage').text(numberFormat($(this).children('#c_usage').text()));
															   				}		
															   			});
																	
																	// do_excelDown Btn event
																		$('#do_excelDown').click(function(){										
																			do_excelDown();
																		});
																}
															},
															complete: function(data){// 무조건 수행
															},
															error: function(xhr, status, error){

															}
														}) // --ajax closed
											        }
										 });
									
								// do_excelDown Btn event
									$('#do_excelDown').click(function(){
										do_excelDown();
									});		
							}else{
								$('#tbody').html("<tr><td></td></tr><tr><td id='result_empty' style='color: red;'>검색 결과가 없습니다.</td></tr>");
							}
						},
						complete: function(data){// 무조건 수행	
						},
						error: function(xhr, status, error){

						}
					}) // --ajax closed
				}
			});// --do_searchList closed
			
			
			$(document).on('change','#mst_ct_id',function(){
				$('#e_mst_ct_id').val($(this).val());
				
			});
			
			$(document).on('change','#dtlList',function(){
				$('#e_dtl_ct_nm').val($(this).val());
			});
			
			$(document).on('change','#start_month',function(){
				var month = "";
				if($(this).val().length == 1){
					month = '0'+$(this).val();
				}else{
					month = $(this).val();
				}
				var st_dt = $(document).find('.currentDate').html().toString()+"-"+month+"-01";
				$('#e_start_date').val(st_dt.trim());
			});
			
			$(document).on('change','#end_month',function(){
				var month = "";
				if($(this).val().length == 1){
					month = '0'+$(this).val();
				}else{
					month = $(this).val();
				}
				var ed_dt = $(document).find('.currentDate').html().toString()+"-"+month+"-01";
				$('#e_end_date').val(ed_dt.trim());
			});
	});//-- jQuery closed


