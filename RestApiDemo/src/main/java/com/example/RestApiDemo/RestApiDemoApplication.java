package com.example.RestApiDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// @ServletComponentScan >> FilterConfig 파일이 생기면서 해당 어노테이션은 제거
public class RestApiDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApiDemoApplication.class, args);
	}
}
