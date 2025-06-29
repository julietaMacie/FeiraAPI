package com.MacieNhangumele.FeiraAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class FeiraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeiraApiApplication.class, args);
	}

}
