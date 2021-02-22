package com.example.demospringapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemospringapplicationApplication {
	public static void main(String[] args) {

		System.out.println("Hello World!");
		SpringApplication.run(DemospringapplicationApplication.class, args);
	}
}
