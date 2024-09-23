//package com.example.reactive.websocket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.HandlerMapping;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ServerConfiguration {
//
//
//    @Bean
//    public ServerHandler serverHandler() {
//        return new ServerHandler(1000);
//    }
//
//    @Bean
//    public HandlerMapping handlerMapping(ServerHandler serverHandler) {
//        Map<String, WebSocketHandler> handlerByPathMap = new HashMap<>();
//        handlerByPathMap.put("/wschat", serverHandler);
//
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        handlerMapping.setUrlMap(handlerByPathMap);
//        handlerMapping.setOrder(-1);
//
//        return handlerMapping;
//    }
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter();
//    }
//}