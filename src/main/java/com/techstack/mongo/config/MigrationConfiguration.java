package com.techstack.mongo.config;

import com.github.cloudyrock.mongock.SpringMongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MigrationConfiguration {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Value("${spring.data.mongodb.uri}")
//    private String mongoDbConnectionUri;

//    @Bean
//    public Mongobee mongobee() {
//        Mongobee runner = new Mongobee(mongoDbConnectionUri);
//        runner.setMongoTemplate(this.mongoTemplate);
//        runner.setChangeLogsScanPackage("com.techstack.mongo.model");
//        return runner;
//    }

    @Bean
    public SpringMongock springMongock(MongoTemplate mongoTemplate, Environment springEnvironment) {
        return new SpringMongockBuilder(this.mongoTemplate, "com.techstack.mongo.model")
                .setSpringEnvironment(springEnvironment)
                .setLockQuickConfig() // OR .setLockConfig(3, 4, 3)
                // ...other setters
                .build();
    }
}
