package com.zerodha.kite.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.zerodha.kite"})
@EnableBatchProcessing
public class ApplicationBatch {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationBatch.class, args);
	}
}
