package com.meteor.s3fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3fileuploadApplication {

	// TODO 공통 Exception 구축, AOP 설정
	public static void main(String[] args) {
		SpringApplication.run(S3fileuploadApplication.class, args);
	}
}
