package com.notonlydolls.nodwebsite;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Configuration class for MongoDB connection
 * 
 * @author Ana Blanco Escudero
 * @since 06-04-21
 */
@Configuration
@PropertySource("classpath:nodwebsite.properties")
public class MongoConfig extends AbstractMongoClientConfiguration {
 
	// Database port
	@Value("${db_port}")
	private String port;
	
	// Database name
	@Value("${db_name}")
	private String name;
		
    @Override
    protected String getDatabaseName() {
        return name;
    }
 
    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:" + port + "/" + name);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.notonlydolls");
    }
    
    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
