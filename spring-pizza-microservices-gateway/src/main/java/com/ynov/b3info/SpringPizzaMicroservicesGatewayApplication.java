package com.ynov.b3info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringPizzaMicroservicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPizzaMicroservicesGatewayApplication.class, args);
	}

}
