package com.sample.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SampleTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleTestApplication.class, args);
	}

}
