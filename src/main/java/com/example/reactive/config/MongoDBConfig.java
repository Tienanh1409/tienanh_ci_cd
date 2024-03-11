package com.example.reactive.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.example.reactive.repository")
public class MongoDBConfig extends AbstractReactiveMongoConfiguration {

    @Value("${port}")
    private String port;

    @Value("${dbname}")
    private String dbName;
    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(){
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}
