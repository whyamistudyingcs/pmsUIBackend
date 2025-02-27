package com.fdm.pmsuibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PmsuibackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsuibackendApplication.class, args);
	}

}
