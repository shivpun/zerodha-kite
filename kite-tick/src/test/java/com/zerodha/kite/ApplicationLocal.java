package com.zerodha.kite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.zerodha"})
public class ApplicationLocal {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationLocal.class, args);
	}
}
