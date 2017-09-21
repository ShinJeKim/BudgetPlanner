package com.apps.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



/**
 * String Util
 * @author SIST
 *
 */
public class StringUtil {

	/**
	 * yyyy-MM-dd형태 날짜 처리
	 * @param type
	 * @return String 2017-09-11
	 */
	public static String currDate(String type) {
		Date date=new Date();
		SimpleDateFormat  sdf=new SimpleDateFormat(type);
		return sdf.format(date);
	}
	
	/**
	 * 32bit random함수
	 * @return String
	 */
	//uuid생성
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
	/**
	 * debug 출력
	 * @param flag
	 */
	public static void debugPrint(boolean flag,String msg){
		if(flag){
			System.out.println(msg);
		}
	}
	
	/**
	 * SQL Inject방지 
	 * @param str
	 * @return "'" 제거  
	 */
	public static String sqlInjectPro(String str){
		String retStr = "";
		retStr = str.replaceAll("'", "");//' -->""
		return retStr;
	}
	
	/**
	 * NVL 
	 * @param str
	 * @return retStr
	 */
	public static String nvl(String str,String defVal){
		String retStr = "";
		if(str == null || str.equals("") ){
			retStr = defVal;
		}else{
			retStr = str.trim();  
		}
		
		return retStr;
	}
	
//	/**
//	 * <select name="page">
//	 *      <option value="">전체</option>
//	 * 		<option value="10">10</option>
//	 * </select>
//	 * @param list
//	 * @param selectNm
//	 * @param allYN
//	 * @return
//	 */
//	public static String mkSelect(List<CodesDTO> list, String page_size,
//			String selectNm,
//			boolean allYN){
//		/*
//		 * <select name="page">
//		 *      <option value="">전체</option>
//		 * 		<option value="10">10</option>
//		 * </select>
//		 */
//		
//		StringBuilder sb=new StringBuilder();
//		
//		sb.append("<select class='orm-control input-sm' name='"+selectNm+"' > \n");
//		//전체 처리: allYN=true
//		if(allYN == true) 	sb.append("<option value=''>=전체=</option>\n");
//		/*
//		 * <option value="<%=dto.getDtl_cd_id() %>"  
//				    				<%if(page_size.equals(dto.getDtl_cd_id()))out.print("selected='selected'"); %>>
//				    			<%=dto.getDtl_cd_nm() %></option>
//		 */
//		for(CodesDTO dto:list){
//			sb.append("<option value='"+dto.getDtl_cd_id()+"' ");
//			if(page_size.equals(dto.getDtl_cd_id())){
//				sb.append("selected='selected'");	
//			}
//			sb.append(">");
//			sb.append(dto.getDtl_cd_nm());
//			sb.append("</option>\n");
//		}
//		sb.append("</select>  \n");
//		System.out.println(sb.toString());
//		return sb.toString();
//	}
	
	/**
	   * Paging처리 
	   * @param maxNum_i
	   * @param currPageNoIn_i
	   * @param rowsPerPage_i
	   * @param bottomCount_i
	   * @param url_i
	   * @param scriptName_i
	   * @return
	   */
	public static String renderPaging(int maxNum_i, int currPageNoIn_i, int rowsPerPage_i, int bottomCount_i,
			String url_i, String scriptName_i) {
			int maxNum = 0; // 총 갯수
			int currPageNo = 1; // 현재 페이지 번호 : page_num
			int rowPerPage = 10; // 한페이지에 보여질 행수 : page_size
			int bottomCount = 10; // 바닥에 보여질 페이지 수: 10

			maxNum = maxNum_i;
			currPageNo = currPageNoIn_i;
			rowPerPage = rowsPerPage_i;
			bottomCount = bottomCount_i;

			String url = url_i; // 호출 URL
			String scriptName = scriptName_i; // 호출 자바스크립트

			int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;
			int startPageNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;//
			int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;
			int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;
			int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;

			int inx = 0;
			StringBuilder html = new StringBuilder();
			if (currPageNo > maxPageNo) {
				return "";
			}

			html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
			html.append("<tr> 																						\n");
			html.append("<td class=\"list_num\">                                                                    \n");
			html.append("<ul class=\"pagination pagination-sm\"> 	                                                \n");
			// <<
			if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
				html.append("<li><a href=\"javascript:" + scriptName + "( '" + url+ "', 1 );\">  \n");
				html.append("&laquo;   \n");
				html.append("</a></li>      \n");
			}

			// <
			if (startPageNo > bottomCount) {
				html.append("<li><a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - 1)+ ");\"> \n");
				html.append("<        \n");
				html.append("</a></li>     \n");
			}



			// 1 2 3 ... 10	(숫자보여주기)
			for (inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
				
				if (inx == currPageNo) {
					html.append("<li class='active'><a href='#'>" + inx	+ "</a></li>");
				} else {
					html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "'," + inx+ ");\" class=\"num_text\">" + inx + "</a></li> \n");
				}
			}
			
			// >
			if (maxPageNo >= inx) {
				html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "',"+ ((nowBlockNo * bottomCount) + 1) + ");\"> \n");
				html.append(">                       \n");
				html.append("</a></li>              \n");
			}

			// >>
			if (maxPageNo >= inx) {
				html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo+ ");\">      \n");
				html.append("&raquo;     \n");
				html.append("</a></li>    \n");
			}
			html.append("</ul>		\n");
			html.append("</td>  	\n");
			html.append("</tr>  	\n");
			html.append("</table>   \n");

			return html.toString();
		}
	
}
