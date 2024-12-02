//package com.example.reactive.r2dbc;
//
// import lombok.NonNull;
// import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//
// import com.mongodb.reactivestreams.client.MongoClient;
// import com.mongodb.reactivestreams.client.MongoClients;
//
//import lombok.extern.slf4j.Slf4j;
//
// @Configuration
// @PropertySource("classpath:mongo.properties")
// @Slf4j
// public class reactiveMongoConfig extends AbstractReactiveMongoConfiguration {
//
//    @Value("${mongoURL}")
//    private String url;
//
//     @NonNull
//     @Override
//     protected String getDatabaseName() {
//         return "spring";
//     }
//
//     @NonNull
//     @Override
//     public MongoClient reactiveMongoClient() {
//         log.info("MongoDB URL: {}", url);
//        return MongoClients.create(url);
//     }
//
// }