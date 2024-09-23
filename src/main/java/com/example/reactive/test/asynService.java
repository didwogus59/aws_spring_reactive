package com.example.reactive.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.Future;

@Service
public class asynService {

    static public int a = 10;
    @Async
    public Future<Integer> test() throws InterruptedException {
        a++;
        Future<Integer> test = null;
        Thread.sleep(100L);
        return test;
    }

    @Async
    public void test_void() throws InterruptedException {
        Thread.sleep(100L);
        a++;
    }
    public int getNum() {
        return a;
    }

    public Flux<Integer> getFlux() {
        return Flux.just(1);
    }
}
