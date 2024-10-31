package com.example.clev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClevApplication.class, args);
	}
}