package com.apps.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.user.domain.UserVO;

/**
 * 오류로 인한 주석처리부분은 TODO로 표시해놓음
 * 주석처리외에는 수업때 했던 내용 그대로
 */
public class ExcelUtil {
	private static Logger log=LoggerFactory.getLogger(ExcelUtil.class);
	private HSSFWorkbook workbook;
	private String filePath;
	private String excelFileName;
	private String changFileName;
	private static short firstRow = 5;
	private static short firstCol = 1;
	
	
	/**
	 * 
	 * @param filePath
	 * @param excelFileName
	 * @param header
	 * @param align
	 * @param data
	 * @throws IOException
	 */   
	public String writeExcel(String filePath,String excelFileName,List<UserVO> data)throws IOException{
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
	 * 
	 * @param data
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createExcel(List<?> data){
	   workbook = new HSSFWorkbook();
	   HSSFSheet sheet = workbook.createSheet("UserList");
       
       // ## Font Setting
       // @HSSFFont : 폰트 설정
       //  - FONT_ARIAL : 기본
       HSSFFont font = workbook.createFont();
       font.setFontName(HSSFFont.FONT_ARIAL);
        
       // ## Title Style Setting
       // @HSSFColor : 셀 배경색
       //  - GREY_$_PERCENT : 회색 $ 퍼센트
       // @HSSFCellStyle
       //  - ALIGN_$ : $ 쪽 정렬
       HSSFCellStyle titleStyle = workbook.createCellStyle();
       titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
       titleStyle.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
       titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       titleStyle.setFont(font);
        
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
       cell_1.setCellValue("아이디");
       cell_1.setCellStyle(titleStyle);
        
       HSSFCell cell_2 = row.createCell((short)2+firstCol);
       cell_2.setCellValue("이름");
       cell_2.setCellStyle(titleStyle);
        
       HSSFCell cell_3 = row.createCell((short)3+firstCol);
       cell_3.setCellValue("Level");
       cell_3.setCellStyle(titleStyle);

        
       HSSFCell cell_4 = row.createCell((short)4+firstCol);
       cell_4.setCellValue("로그인");
       cell_4.setCellStyle(titleStyle);
       
       HSSFCell cell_5 = row.createCell((short)5+firstCol);
       cell_5.setCellValue("이메일");
       cell_5.setCellStyle(titleStyle);
       
       
       // ## Content Style Setting
       HSSFCellStyle contentStyle = workbook.createCellStyle();
       contentStyle.setFont(font);
        
       //  Content align : center
       HSSFCellStyle styleCenter = workbook.createCellStyle();
       styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       styleCenter.setFont(font);
        
       //  Content align : left
       HSSFCellStyle styleLeft = workbook.createCellStyle();
       styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
       styleLeft.setFont(font);
       
       //  Content align : left
       HSSFCellStyle styleRight = workbook.createCellStyle();
       styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
       styleRight.setFont(font);
       
       
       
       //  ObjectList 가 비어있으면 제목만 출력 후 종료
       if(data == null) return workbook;
       //TODO...
      /*  
       //  ObjectList 엑셀에 출력
       for(int i = 0; i < data.size(); i++){
           // 1번째 행은 제목이니 건너 뜀
           row = sheet.createRow((short)this.firstRow+(i+1));
           UserVO user = (UserVO)data.get(i);
           //번호
           cell_0 = row.createCell((short)0+firstCol);
           cell_0.setCellValue(user.getNo());
           cell_0.setCellStyle(styleLeft);           
           //id
           cell_1 = row.createCell((short)1+firstCol);
           cell_1.setCellValue(user.getId());
           cell_1.setCellStyle(styleLeft);
           //이름 
           cell_2 = row.createCell((short)2+firstCol);
           cell_2.setCellValue(user.getName());
           cell_2.setCellStyle(styleLeft);
           //Level 
           cell_3 = row.createCell((short)3+firstCol);
           cell_3.setCellValue(user.getU_level());
           cell_3.setCellStyle(styleRight);
           //로그인
           cell_4 = row.createCell((short)4+firstCol);
           cell_4.setCellValue(user.getLogin());
           cell_4.setCellStyle(styleRight);
           //이메일
           cell_5 = row.createCell((short)5+firstCol);
           cell_5.setCellValue(user.getMail());
           cell_5.setCellStyle(styleLeft);            
       }
        */
       //컬럼사이즈
       for(int i=0; i<7; i++){
    	   if(i==0){
    		   sheet.setColumnWidth(0,700);
    	   }else{
    		   sheet.autoSizeColumn((short)i);
    		   sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );  // 윗줄만으로는 컬럼의 width 가 부족하여 더 늘려야 함.
    	   }
       }
       
       
       return workbook;
   }

	
    	
	

	/**
	 * 
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

	
    /**
     * ExcelUpload
     * @param excelFile
     * @return
     * @throws Exception
     */
	public List<UserVO> parseTableExcel(String excelFile) throws Exception {
		List<UserVO>   list =new ArrayList<UserVO>();
		try{
			Workbook wb = WorkbookFactory.create(new FileInputStream(excelFile));
			Sheet sheet = (Sheet) wb.getSheetAt(0);
			int lastCellNum = 0;
			
			log.debug("sheet.getFirstRowNum(): "+sheet.getFirstRowNum());
			log.debug("sheet.getLastRowNum(): "+sheet.getLastRowNum());
			
			
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				log.debug("i: "+i);
				Row row = sheet.getRow(i);
				if (i == 0) {
                    lastCellNum = row.getLastCellNum();
                    log.debug("lastCellNum: "+lastCellNum);
                    if (lastCellNum != 5) { throw new Exception("양식이 잘못되었습니다."); }
 
                    Cell cell = row.getCell(0);
                    if (!cell.getRichStringCellValue().getString().trim().equals("아이디")) { throw new Exception("[아이디] 헤더가 일치 하지 않습니다."
                            + cell.getRichStringCellValue().equals("아이디")); }
 
                    cell = row.getCell(1);
                    if (!cell.getRichStringCellValue().getString().trim().equals("이름")) { throw new Exception("[이름] 헤더가 일치 하지 않습니다."
                            + cell.getRichStringCellValue().equals("이름")); }
 
                    cell = row.getCell(2);
                    if (!cell.getRichStringCellValue().getString().trim().equals("Level")) { throw new Exception("[Level] 헤더가 일치 하지 않습니다."
                            + cell.getRichStringCellValue().equals("Level")); }
                    cell = row.getCell(3);
                    if (!cell.getRichStringCellValue().getString().trim().equals("로그인횟수")) { throw new Exception("[로그인횟수] 헤더가 일치 하지 않습니다."
                            + cell.getRichStringCellValue().equals("로그인횟수")); }                    
                    cell = row.getCell(4);
                    if (!cell.getRichStringCellValue().getString().trim().equals("이메일")) { throw new Exception("[이메일] 헤더가 일치 하지 않습니다."
                            + cell.getRichStringCellValue().equals("이메일")); }
                    
 
                }else{
                	 // 필수항목 체크
                    if (!cellValue(row.getCell(0)).equals("")){
                    	UserVO dto =new UserVO();
                    	//TODO....
                  /*  	
                    	dto.setId(cellValue(row.getCell(0)));
                    	dto.setName(cellValue(row.getCell(1)));
                    	dto.setU_level(Integer.parseInt(cellValue(row.getCell(2))));
                    	dto.setLogin(Integer.parseInt(cellValue(row.getCell(3))));
                    	dto.setMail(cellValue(row.getCell(4)));
                   */ 	
                    	list.add(dto);
                    }

                }  

			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
            throw new Exception("오류가 있는 데이터가 있습니다." + e.getMessage());
        }  


		return list;
	}
	
    @SuppressWarnings("deprecation")
	public String cellValue(Cell cell) {
    	 
        String value = null;
        if (cell == null) value = "";
        else {
            switch (cell.getCellType()) { //cell 타입에 따른 데이타 저장
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
                    log.debug("date: "+value);
                } else {
                    value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = "" + cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "" + cell.getErrorCellValue();
                break;
            default:
            }
        }
 
        return value.trim();
    }

    
}
