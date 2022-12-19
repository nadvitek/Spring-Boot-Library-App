package com.example.cv01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Cv01Application {

	public static void main(String[] args) {
		SpringApplication.run(Cv01Application.class, args);
	}

}
