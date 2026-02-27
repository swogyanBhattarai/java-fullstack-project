package com.justdeepfried.learnApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LearnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApiApplication.class, args);
	}

}
