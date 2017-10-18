package com.apps.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.daily.domain.DailyVO;


public class ExcelDownUtil {
	private static Logger log=LoggerFactory.getLogger(ExcelUtil.class);
	private HSSFWorkbook workbook;
	private String filePath;
	private String excelFileName;
	private String changFileName;
	private static short firstRow = 5;
	private static short firstCol = 1;
	
	
	/**
	 * 엑셀 파일을 생성하는 메소드
	 * @param filePath
	 * @param excelFileName
	 * @param header
	 * @param align
	 * @param data
	 * @throws IOException
	 */   
	public String writeExcel(String filePath,String excelFileName,List<DailyVO> data)throws IOException{
		this.filePath = filePath;
		this.excelFileName =excelFileName;
		FileOutputStream out = setFile(this.filePath,this.excelFileName);
	    
		// create a new workbook
		HSSFWorkbook  wb =  createExcel(data);
		try{
			wb.write(out);
		}finally{
			out.close();
			wb.close();
		}
		
		return changFileName;
	}
	


	
	/**
	 * 엑셀 시트 생성 메소드
	 * @param data
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createExcel(List<?> data){
	   workbook = new HSSFWorkbook();
	   HSSFSheet sheet = workbook.createSheet("BalanceSheet");
       
       // ## Font Setting
       // @HSSFFont : 폰트 설정
       //  - FONT_ARIAL : 기본
       HSSFFont font = workbook.createFont();
       font.setFontName("맑은 고딕");
       
       // 머리말행 폰트 설정
       HSSFFont titleFont = workbook.createFont();
       titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       titleFont.setFontHeightInPoints((short)13);
       titleFont.setFontName("맑은 고딕");
       
        
       // ## Title Style Setting
       // @HSSFColor : 셀 배경색
       //  - GREY_$_PERCENT : 회색 $ 퍼센트
       // @HSSFCellStyle
       //  - ALIGN_$ : $ 쪽 정렬
       HSSFCellStyle titleStyle = workbook.createCellStyle();
       titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
       titleStyle.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
       titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
       titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
       titleStyle.setFont(titleFont);
       
        
       // ## Row Create
       // ? 가로열 생성
       HSSFRow row = sheet.createRow((short)firstRow);
        
       // ## Title Cell Create
       // @row.createCell((short)n) : n번째 셀 설정
       // @setCellValue(String) : n 번째 셀의 내용
       // @setCellStyle(style) : n 번째 셀의 스타일
       HSSFCell cell_0 = row.createCell((short)0+firstCol);
       cell_0.setCellValue("번호");
       cell_0.setCellStyle(titleStyle);
        
       HSSFCell cell_1 = row.createCell((short)1+firstCol);
       cell_1.setCellValue("수입/지출");
       cell_1.setCellStyle(titleStyle);
        
       HSSFCell cell_2 = row.createCell((short)2+firstCol);
       cell_2.setCellValue("카테고리");
       cell_2.setCellStyle(titleStyle);
        
       HSSFCell cell_3 = row.createCell((short)3+firstCol);
       cell_3.setCellValue("금액");
       cell_3.setCellStyle(titleStyle);

       HSSFCell cell_4 = row.createCell((short)4+firstCol);
       cell_4.setCellValue("상세설명");
       cell_4.setCellStyle(titleStyle);
       
       HSSFCell cell_5 = row.createCell((short)5+firstCol);
       cell_5.setCellValue("날짜");
       cell_5.setCellStyle(titleStyle);
       
       
       // ## Content Style Setting
       HSSFCellStyle contentStyle = workbook.createCellStyle();
       contentStyle.setFont(font);
        
       //  Content align : center
       HSSFCellStyle styleCenter = workbook.createCellStyle();
       styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       styleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       styleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
       styleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
       styleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       styleCenter.setFont(font);
        
       //  Content align : left
       HSSFCellStyle styleLeft = workbook.createCellStyle();
       styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
       styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       styleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       styleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
       styleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
       styleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       styleLeft.setFont(font);
       
       //  Content align : left
       HSSFCellStyle styleRight = workbook.createCellStyle();
       styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
       styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       styleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       styleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
       styleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
       styleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       styleRight.setFont(font);
       
       // Content vertical align : center
       HSSFCellStyle verticalCenter = workbook.createCellStyle();
       verticalCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       verticalCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       verticalCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
       verticalCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
       verticalCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       verticalCenter.setFont(font);
       
       HSSFCellStyle conStyle = workbook.createCellStyle();
       conStyle.setWrapText(true);
       conStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
       conStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       conStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       conStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
       conStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
       conStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       conStyle.setFont(font);
       
       //  ObjectList 가 비어있으면 제목만 출력 후 종료
       if(data == null) return workbook;
        
       //  ObjectList 엑셀에 출력
       for(int i = 0; i < data.size(); i++){
    	   
           // 1번째 행은 제목이니 건너 뜀
           row = sheet.createRow((short)this.firstRow+(i+1));
           DailyVO dailyVO = (DailyVO)data.get(i);
           
           // 번호
           cell_0 = row.createCell((short)0+firstCol);
           cell_0.setCellValue(dailyVO.getNo());
           cell_0.setCellStyle(styleCenter);      
           
           // 수입/지출
           cell_1 = row.createCell((short)1+firstCol);
           cell_1.setCellValue(dailyVO.getMst_ct_nm());
           cell_1.setCellStyle(styleCenter);
           
           // 카테고리 
           cell_2 = row.createCell((short)2+firstCol);
           cell_2.setCellValue(dailyVO.getDtl_ct_nm());
           cell_2.setCellStyle(styleLeft);
           
           // 금액 
           cell_3 = row.createCell((short)3+firstCol);
           cell_3.setCellValue(dailyVO.getUsage());
           cell_3.setCellStyle(styleRight);
           
           // 내용
           cell_4 = row.createCell((short)4+firstCol);
           cell_4.setCellValue(dailyVO.getContent());
           cell_4.setCellStyle(conStyle);
           //sheet.setColumnWidth(cell_4.getColumnIndex(), 8400);
           
           // 날짜
           cell_5 = row.createCell((short)5+firstCol);
           cell_5.setCellValue(dailyVO.getReg_dt());
           cell_5.setCellStyle(styleCenter);          
       }
        
       //컬럼사이즈
       for(int i=0; i<7; i++){
    	   if(i==0){
    		   sheet.setColumnWidth(0,700);
    	   }else{
    		   sheet.autoSizeColumn((short)i);
    		   sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );  // 윗줄만으로는 컬럼의 width 가 부족하여 더 늘려야 함.
    	   }
       }
       
       sheet.setColumnWidth(cell_4.getColumnIndex(), 12000);
       
       
       return workbook;
   }

	/**
	 * 파일을 저장시키는 메소드
	 * @param filePath
	 * @param excelFileName
	 * @return FileOutputStream
	 * @throws FileNotFoundException
	 */
    private FileOutputStream setFile(String filePath, String excelFileName)throws FileNotFoundException{
        File dir = new File(filePath); 
        if(!dir.exists()) dir.mkdirs(); 
        //File존재하면
        String changeFileName = createFile(filePath,excelFileName);
        
        FileOutputStream fout = new FileOutputStream(filePath+"/"+changeFileName); 
        return fout;
    }
    
    /**
     * 파일 rename
     * @param filePath
     * @param excelFileName
     * @return
     */
    public String createFile(String filePath, String excelFileName){
        File file = new File(filePath, excelFileName);
        String changeFileName = excelFileName;
        if(file.isFile()){
            changeFileName=System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-", "")+"_"+excelFileName;
        }
        changFileName = changeFileName;
        return changeFileName;
     }

	
    
}
