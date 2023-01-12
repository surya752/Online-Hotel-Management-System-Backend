package com.capgemini.descoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DescoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DescoveryServerApplication.class, args);
	}

}
