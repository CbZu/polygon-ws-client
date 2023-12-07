package com.io.polygon.stock.config;

import com.io.polygon.stock.handler.StockMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketConfiguration {

    @Value("${polygon.websocket.uri}")
    private String webSocketUri;

    @Autowired
    private StockMessageHandler stockMessageHandler;

    @Bean
    CustomWebSocketConnectionManager wsConnectionManager() {
        CustomWebSocketConnectionManager manager = new CustomWebSocketConnectionManager(client(), stockMessageHandler, this.webSocketUri);
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }

}
