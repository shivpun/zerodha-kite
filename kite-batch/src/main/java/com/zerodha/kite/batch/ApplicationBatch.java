package com.zerodha.kite.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.zerodha.kite" })
@EnableBatchProcessing
@EntityScan(basePackages = { "com.zerodha.kite.domain" })
@EnableJpaRepositories(basePackages = { "com.zerodha.kite.repository" })
public class ApplicationBatch {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationBatch.class, args);
	}
}
