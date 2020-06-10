package com.sy.s1.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class FilePathGenerator {
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ServletContext servletContext;
	
	
	//  /static/upload/notice
	//  /static/upload/qna
	public File getUserResourceLoader(String path) throws Exception{
		// ResourceLoader
		// Classes경로룰 받아오기 위해 사용
		// 생성하는 디렉토리가 없으면 에러 발생
		// Default로 만들어진 static을 이용해서 File 객체를 생성
		// 하위 디렉토리를 Child로 사용해 디렉토리 완성
		String defaultPath = "classpath:/static/";
		File file = resourceLoader.getResource(defaultPath).getFile();
		file = new File(file,path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("경로: "+file.getAbsolutePath());			//사진 저장 경로	
		return file;
	}
	
	
	public File getUseClassPathResource(String path) throws Exception{
		// ClassPathResource
		// Classes 경로를 받아오기 위해 사용
		// ResourceLoader와 같지만
		// 시작 경로에 classpath를 쓰지 않음
		// 개발자가 직접 객체 생성
		String defaultPath = "static";
		ClassPathResource classPathResource = new ClassPathResource(defaultPath);
		File file = classPathResource.getFile();
		file = new File(file,path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("경로: "+file.getAbsolutePath());
		return file;
	}
	
	
	// webapp에 하위 폴더를 생성, static 폴더 X
	// 이건 사용 X
	public File getUseServletContext(String path) throws Exception{
		// Servlet Context
		// ClassPath가 아닌 webapp 하위에 만들어짐
		// 생성할 디렉토리가 static이라면 
		// webapp 하위에 static폴더가 하나 더 만들어짐
		path = servletContext.getRealPath(path);
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();			
		}
		System.out.println(path);
		return file;	
	}
	
	
}
