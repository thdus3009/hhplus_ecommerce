package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class EcommerceServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceServiceApplication.class, args);
	}

}
