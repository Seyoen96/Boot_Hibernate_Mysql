package com.sy.s1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;



@Component
public class FileDown extends AbstractView{
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		// 1. 저장 경로 확인 (어디에 업로드 할건지)
		// path는 변수로 계속 바꿔줄 것
		String path = "classpath:/static/";
		path = (String)path+model.get("path");
		BoardFileVO boardFileVO = (BoardFileVO)model.get("fileVO");
		
		path = path + boardFileVO.getFileName();	
		File file = resourceLoader.getResource(path).getFile();
		
		// 한글 처리
		// 파일명 한글인 경우 처리
		response.setCharacterEncoding("UTF-8");
		// 총 파일의 크기
		response.setContentLengthLong(file.length());
		// 다운로드 시 파일의 이름을 인코딩
		String fileName = URLEncoder.encode(boardFileVO.getOriName(),"UTF-8");
		
		// header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// 전송
		// HDD에서 파일을 읽고 
		FileInputStream fi = new FileInputStream(file);
		// client로 전송 준비
		OutputStream os = response.getOutputStream();
		// 전송
		FileCopyUtils.copy(fi, os);
		os.close();
		fi.close();
		*/
	}

}
