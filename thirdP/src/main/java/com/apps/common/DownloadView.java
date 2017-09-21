package com.apps.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
//AbstractView : 스프링 MVC 사용시 DispatcherServlet 기능
//             : requestURI에 따라 컨트롤러로 분기하고 로직 처리 후 Resolver를 사용하여
//             : 해당 jsp 파일을 찾아 응답하게 되는데 그 사이 시점을 잡아 처리하는 부분의 기능

public class DownloadView extends AbstractView {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	/**
	 * 다운로드 파일명 설정
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void setDownloadFileName(String fileName, 
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent");

        boolean isIe = userAgent.indexOf("MSIE") != -1;

        if(isIe){
            fileName = URLEncoder.encode(fileName, "utf-8");
        } else {
        	//한글파일명으로 파일 변환 Download
        	String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            fileName = new String(docName.getBytes("utf-8"));
        }

        response.setHeader("Content-Disposition"
        		, "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding"
        		, "binary");
          
        log.debug("=setDownloadFileName==============================");
        log.debug("fileName: " + fileName);
        log.debug("=setDownloadFileName==============================");
        
    }

	/**
	 * 다운로드 파일
	 * @param downloadFile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void downloadFile(File downloadFile, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(downloadFile);

        try {
            log.debug("=88downloadFile==============================");
            log.debug("downloadFile: " + downloadFile.getPath()+""+downloadFile.getName());
            log.debug("=88downloadFile==============================");
            
            FileCopyUtils.copy(in, out);
            out.flush();
        } catch (Exception e) {
        	log.debug("downloadFile Exception: " + e.getMessage());
            throw e;
        } finally {
            try { 
            	if (in != null) in.close(); 
            } catch (IOException ioe) {
            	throw ioe;
            }
            try { 
            	if (out != null) out.close(); 
            } catch (IOException ioe) {
            	throw ioe;
            }
        }
    }

	
	/**
	 * 
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
            setResponseContentType(request, response);
            File downloadFile = (File) model.get("downloadFile");

            log.debug("=renderMergedOutputModel==============================");
            log.debug("downloadFile: " + downloadFile);
            log.debug("=renderMergedOutputModel==============================");
                
            setDownloadFileName(downloadFile.getName(), 
            		               request, response);

            response.setContentLength((int) downloadFile.length());
            downloadFile(downloadFile, request, response);
        } catch (Exception e) {
            throw e;
        }



	}

}
