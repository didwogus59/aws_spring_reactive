package com.example.reactive.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("")
public class testControll {

    @Autowired
    asynService service;

    @GetMapping(value = "/")
    String test() {

        return "test";
    }

    @GetMapping("/future")
    int[] testing() throws InterruptedException {
        int[] arr = new int[10];

        for(int i = 0; i < 10; i++) {
            service.test();
            arr[i] = service.getNum();
        }
        return arr;
    }

    @GetMapping("/void")
    int[] testing_void() throws InterruptedException {
        int[] arr = new int[10];

        for(int i = 0; i < 10; i++) {
            service.test_void();
            arr[i] = service.getNum();
        }
        return arr;
    }

    @GetMapping("/test")
    Flux<Integer> testing_flux() throws InterruptedException {
        return Flux.just(10);
    }

}
