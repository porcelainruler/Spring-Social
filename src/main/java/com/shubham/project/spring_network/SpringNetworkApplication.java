package com.shubham.project.spring_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNetworkApplication.class, args);
	}

}
