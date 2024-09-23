package com.example.reactive.r2dbc;

import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class reactiveMongoService {

    @Autowired
    private reactiveMongoRepo repository;

    @Autowired childRepository repositoryC;

    @Autowired
    private ReactiveMongoTemplate template;

    public Flux<reactiveData> all_data() {
        return repository.findAll();
    }

    public Mono<reactiveData> get_data(ObjectId id) {
        return repository.findById(id);
    }


    public Mono<reactiveData> create_data(String title, String data, String user) {
        return repository.insert(new reactiveData(title, data, user));
    }
    public reactiveData create_data2(String title, String data, String user) {
        reactiveData test = new reactiveData(title, data, user);
        repository.save(test);
        return test;
    }



    public Mono<reactiveData> update_data(ObjectId id, String title, String data, String username) {
        Query query = Query.query(Criteria.where("_id").is(id).andOperator(
                new Criteria().orOperator(
                    Criteria.where("user").is(username),
                        Criteria.where("user").is("null")
                )
        ));
        //update할 필드와 값 설정
        Update update = new Update().set("title", title).set("data", data);
        //update 실행
        return template.findAndModify(query, update, reactiveData.class);
    }

    public Mono<DeleteResult> delete_data(ObjectId id, String username) {
        Query query = Query.query(Criteria.where("_id").is(id).andOperator(
                new Criteria().orOperator(
                        Criteria.where("user").is(username),
                        Criteria.where("user").is("null")
                )
        ));

        //update 실행
        return template.remove(query, reactiveData.class);
    }

    public Flux<reactiveChild> all_child(ObjectId id) {
        return repositoryC.findByParent(id);
    }
}
