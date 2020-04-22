package com.techstack.mongo.config;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MigrationConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbConnectionUri;

    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee(mongoDbConnectionUri);
        runner.setMongoTemplate(this.mongoTemplate);
        runner.setChangeLogsScanPackage("com.techstack.mongo.model");
        return runner;
    }
}
