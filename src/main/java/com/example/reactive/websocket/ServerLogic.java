//package com.example.reactive.websocket;
//
//
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class ServerLogic {
//
//    private final AtomicBoolean newClient;
//
//    public ServerLogic() {
//        newClient = new AtomicBoolean(true);
//    }
//
//    public Mono<Void> doLogic(WebSocketSession session, long interval) {
//        return
//            session
//                .receive()
//                .map(WebSocketMessage::getPayloadAsText)
//                .filter(message -> newClient.get())
//                .doOnNext(message -> newClient.set(false))
//                .flatMap(message -> sendAtInterval(session, interval))
//                .then();
//    }
//
//    private Flux<Void> sendAtInterval(WebSocketSession session, long interval) {
//        return
//            Flux
//                .interval(Duration.ofMillis(interval))
//                .map(value -> Long.toString(value))
//                .flatMap(message ->
//                    session
//                        .send(Mono.fromCallable(() -> session.textMessage(message)))
//                );
//    }
//}