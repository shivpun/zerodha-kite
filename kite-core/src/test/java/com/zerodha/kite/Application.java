package com.zerodha.kite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zerodha.kite.properties.KiteProperties;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		KiteProperties kiteProperties = context.getBean(KiteProperties.class);
		System.out.println("K");
	}
}
