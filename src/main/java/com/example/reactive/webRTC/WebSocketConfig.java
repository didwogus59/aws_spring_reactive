package com.example.reactive.webRTC;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;

@Configuration
public class WebSocketConfig {

    @Value("${socket.host}")
    private String host;

    @Value("${socket.port}")
    private int port;

    @Value("${server.ssl.key-store-password}")
    private String password;

    @Value("${ssl.path}")
    private String path;
    @Bean
    public SocketIOServer socketIOServer() throws Exception {
        com.corundumstudio.socketio.Configuration config =
                new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        config.setKeyStorePassword(password);
        ClassPathResource resource = new ClassPathResource(path);
        config.setKeyStore(resource.getInputStream());
        return new SocketIOServer(config);
    }
}