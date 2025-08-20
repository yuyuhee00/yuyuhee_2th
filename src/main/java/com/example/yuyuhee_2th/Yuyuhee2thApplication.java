package com.example.yuyuhee_2th;

import com.example.config.BasicDataSourceConfig;
import com.example.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

@SpringBootApplication (scanBasePackageClasses = {BasicDataSourceConfig.class, SecurityConfig.class})

// For Spring Boot Testing
@ComponentScan(excludeFilters={@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value={EmbeddedDatabase.class})})

public class Yuyuhee2thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Yuyuhee2thApplication.class, args);
	}

}
