package com.zerodha.kite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ApplicationWeb {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationWeb.class, args);
	}
}
