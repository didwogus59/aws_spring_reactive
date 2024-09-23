package com.example.reactive.r2dbc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface childRepository extends ReactiveMongoRepository<reactiveChild, ObjectId> {
    Flux<reactiveChild> findByParent(ObjectId parent);
}



