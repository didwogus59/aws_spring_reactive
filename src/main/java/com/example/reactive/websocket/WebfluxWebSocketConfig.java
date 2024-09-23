package com.example.reactive.websocket;


import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
 import org.springframework.web.reactive.socket.WebSocketHandler;
 import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

 import java.util.HashMap;
 import java.util.Map;

 @Configuration
 public class WebfluxWebSocketConfig {


     private final WebSocketChatHandler webSocketHandler = new WebSocketChatHandler();

     @Bean
     public SimpleUrlHandlerMapping webSocketHandlerMapping() {
         Map<String, WebSocketHandler> map = new HashMap<>();
         map.put("/wschat", webSocketHandler);

         SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping(map, -1);
         return mapping;
     }

     @Bean
     public WebSocketHandlerAdapter handlerAdapter() {
       return new WebSocketHandlerAdapter();
     }
 }