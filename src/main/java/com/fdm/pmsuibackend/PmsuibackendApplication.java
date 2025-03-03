package com.fdm.pmsuibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fdm.pmsuibackend", "com.fdm.pmscommon"})
@EntityScan(basePackages = {"com.fdm.pmscommon.entities"})
@EnableJpaRepositories(basePackages = {"com.fdm.pmscommon.repositories"})
public class PmsuibackendApplication {
	public static void main(String[] args) {
		try {
            Class.forName("com.fdm.pmscommon.entities.Account");
            System.out.println("Account class found");
        } catch (ClassNotFoundException e) {
            System.out.println("Account class not found: " + e.getMessage());
        }
        SpringApplication.run(PmsuibackendApplication.class, args);
	}

}
