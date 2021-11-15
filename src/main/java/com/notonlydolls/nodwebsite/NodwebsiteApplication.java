package com.notonlydolls.nodwebsite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot application class
 * 
 * @author Ana Blanco Escudero
 * @since 06-04-21
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.notonlydolls.nodwebsite.repository")
@EnableMongoAuditing
public class NodwebsiteApplication implements CommandLineRunner {

	@Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(NodwebsiteApplication.class, args);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	@Override
	public void run(String... args) throws Exception {

	}

}
