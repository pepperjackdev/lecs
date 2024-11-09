package me.pepperjackdev.lecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("me.pepperjackdev.lecs")
@EntityScan("me.pepperjackdev.lecs")
@SpringBootApplication
public class LecsApplication {
	public static void main(String[] args) {
		SpringApplication.run(LecsApplication.class, args);
	}
}
