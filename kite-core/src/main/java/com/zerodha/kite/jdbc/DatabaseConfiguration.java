package com.zerodha.kite.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfiguration {

	@Bean
	public DataSource dataSource(@Value(value="${spring.datasource.url}") String url, @Value(value="${spring.datasource.username}") String username, @Value(value="${spring.datasource.password}") String password, @Value(value="${spring.datasource.driver-class-name}") String driverClassName) {
		DataSource dataSource = DataSourceBuilder.create()
												 .url(url)
												 .username(username)
												 .password(password)
												 .driverClassName(driverClassName).build();
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DefaultKiteTickDataBase kiteDataConfig(JdbcTemplate jdbcTemplate) {
		DefaultKiteTickDataBase kiteTickDataBase = new DefaultKiteTickDataBase(jdbcTemplate);
		return kiteTickDataBase;
	}
}
