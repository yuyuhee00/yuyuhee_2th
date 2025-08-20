package com.example.yuyuhee_2th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication (scanBasePackageClasses = {BasicDataSourceConfig.class, SecurityConfig.class})
public class Yuyuhee2thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Yuyuhee2thApplication.class, args);
	}

}
