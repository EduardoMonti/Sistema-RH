package com.AppRH.AppRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com/appRH/appRH/models")
public class AppRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRhApplication.class, args);
	}

}
