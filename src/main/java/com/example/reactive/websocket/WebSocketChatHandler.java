package com.example.reactive.websocket;
 import org.springframework.web.reactive.socket.WebSocketMessage;
 import reactor.core.publisher.Flux;
 import reactor.core.publisher.Mono;
 import reactor.core.publisher.Sinks;
 import reactor.core.publisher.Sinks.Many;
 import org.springframework.web.reactive.socket.WebSocketHandler;
 import org.springframework.web.reactive.socket.WebSocketSession;

 public class WebSocketChatHandler implements WebSocketHandler {

     private Many<String> sink;

     public WebSocketChatHandler() {
         this.sink = Sinks.many().multicast().onBackpressureBuffer();
     }

     @Override
     public Mono<Void> handle(WebSocketSession session) {
         Flux<WebSocketMessage> outputMessages = sink.asFlux()
                 .map(session::textMessage);

         Mono<Void> input = session.receive()
                 .map(WebSocketMessage::getPayloadAsText)
                 .doOnNext(sink::tryEmitNext)
                 .then();

         return session.send(outputMessages).and(input);
     }
//        @Override
//        public Mono<Void> handle(WebSocketSession session) {
//            return session.send(
//                            session.receive()
//                                    .map(msg -> session.textMessage("Send: " + msg.getPayloadAsText()))
//                    )
//                    .doFinally(signalType -> {
//                        // 여기에서 세션이 종료될 때 처리할 로직을 작성
////                        System.out.println("Session closed with signal: " + signalType);
//                        cleanUpResources(session);
//                    });
//        }

     private void cleanUpResources(WebSocketSession session) {
         // 리소스 정리, 세션 해제 등의 작업 수행
//         System.out.println("Cleaning up resources for session: " + session.getId());
         // 예: 데이터베이스 연결 해제, 캐시 제거 등
     }

 }