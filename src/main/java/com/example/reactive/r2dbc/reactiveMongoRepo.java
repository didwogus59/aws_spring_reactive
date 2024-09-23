package com.example.reactive.r2dbc;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface reactiveMongoRepo extends ReactiveMongoRepository<reactiveData, ObjectId>{

    Mono<Void> deleteByIdAndUser(ObjectId id, String user);
}
