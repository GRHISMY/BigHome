package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	//获取配置的上传文件的系统临时文件夹路径
//	@Value("${filePath}")
//	private String tempfilePath;
//
//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//允许上传的文件最大值
//		factory.setMaxFileSize(DataSize.parse("100MB"));
//		/// 设置总上传数据总大小
//		factory.setMaxRequestSize(DataSize.parse("1024MB"));
//		//设置临时文件路径，以防长时间不操作后删除临时文件导致报错
//		File f = new File(tempfilePath);
//		if (!f.exists())
//			f.mkdirs();
//		factory.setLocation(tempfilePath);
//		return factory.createMultipartConfig();
//
//	}


}
