package com.ps.exercise.eip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start spring boot application
 */
@SpringBootApplication(scanBasePackages = "com.ps.exercise.eip")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
