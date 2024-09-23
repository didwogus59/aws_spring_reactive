package com.example.reactive.r2dbc;

import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/r2dbc/mongoDB")
public class reactiveMongoController {

    @Autowired
    reactiveMongoService service;

    @RequestMapping(method = RequestMethod.GET)
    public Flux<mongoDTO> all_data() {
        return service.all_data().map(item -> new mongoDTO(item));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Mono<reactiveData> create_data(@ModelAttribute reactiveData test, ServerWebExchange exchange) {
        String name = (String) exchange.getAttributes().get("username");
        log.info("name : {}", name);
        return service.create_data(test.getTitle(), test.getData(), name);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Mono<reactiveData> detail_data(@PathVariable ObjectId id) {
        return service.get_data(id);
    }

    @RequestMapping(path = "/child/{id}", method = RequestMethod.GET)
    public Flux<reactiveChild> child_data(@PathVariable ObjectId id) {
        return service.all_child(id);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public Mono<reactiveData> update_data(@PathVariable ObjectId id, @ModelAttribute reactiveData test, ServerWebExchange exchange) {
        String username = (String) exchange.getAttributes().get("username");
        return service.update_data(id, test.getTitle(), test.getData(), username);
    }




    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Mono<DeleteResult> delete_data(@PathVariable ObjectId id, ServerWebExchange exchange) {
        String username = (String) exchange.getAttributes().get("username");
        return service.delete_data(id, username);
    }
}
