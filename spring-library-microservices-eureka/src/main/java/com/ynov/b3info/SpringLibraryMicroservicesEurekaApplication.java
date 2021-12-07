package com.ynov.b3info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringLibraryMicroservicesEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLibraryMicroservicesEurekaApplication.class, args);
	}

}
