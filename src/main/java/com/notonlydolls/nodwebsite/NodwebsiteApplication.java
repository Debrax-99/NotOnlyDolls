package com.notonlydolls.nodwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.notonlydolls.repository")
public class NodwebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodwebsiteApplication.class, args);
	}

}
